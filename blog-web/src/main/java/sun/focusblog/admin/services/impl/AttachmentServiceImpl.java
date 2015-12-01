package sun.focusblog.admin.services.impl;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.focusblog.admin.components.Helper;
import sun.focusblog.admin.components.ueditor.define.*;
import sun.focusblog.admin.dao.IAttachmentDao;
import sun.focusblog.admin.domain.Attachment;
import sun.focusblog.admin.services.AttachmentService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by root on 2015/12/1.
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private IAttachmentDao attachmentDao;

    private static final String ATTACHMENT_GET_PATH = "/editor/attachment/";

    @Transactional
    @Override
    public boolean save(Attachment attachment) {
        if (attachment == null)
            return false;
        if (StringUtils.isEmpty(attachment.getId())) {
            attachment.setId(UUID.randomUUID().toString());
        }
        return attachmentDao.save(attachment);
    }

    @Override
    public Attachment query(String id) {
        return attachmentDao.query(id);
    }

    @Override
    public State upload(Map<String, Object> conf, HttpServletRequest request) {
        State state;
        String filedName = (String) conf.get("fieldName");
        if ("true".equals(conf.get("isBase64"))) {
            state = uploadBase64(request, request.getParameter(filedName), conf);
        } else {
            state = uploadBinary(conf, request);
        }
        return state;
    }

    @Override
    public State listImages(HttpServletRequest request, int start, int size) {
        State state;
        List<Attachment> list = attachmentDao.list(Helper.getUser(request.getSession()).getUserId(), start, size);
        if (list == null || list.size() == 0) {
            return new BaseState(false, AppInfo.NOT_EXIST);
        }

        if (start < 0 || start > list.size()) {
            state = new MultiState(true);
        } else {
            MultiState multiState = new MultiState(true);
            for (Attachment attachment : list) {
                BaseState fileState = new BaseState(true);
                String attachmentPath = Helper.appURL(request) + ATTACHMENT_GET_PATH + attachment.getId();
                fileState.putInfo("url", attachmentPath);
                multiState.addState(fileState);
            }
            state = multiState;
        }
        state.putInfo("start", start);
        state.putInfo("total", list.size());
        return state;
    }

    private State uploadBase64(HttpServletRequest request, String content, Map<String, Object> conf) {
        byte[] data = Base64.decodeBase64(content);
        long maxSize = (Long) conf.get("maxSize");

        if (!validSize(data, maxSize)) {
            return new BaseState(false, AppInfo.MAX_SIZE);
        }
        String suffix = FileType.getSuffix("JPG");
        if (suffix.length() > 0 && suffix.indexOf(".") > 0) {
            suffix = suffix.substring(suffix.indexOf(".") + 1);
        }

        Attachment attachment = new Attachment();
        attachment.setContent(data);
        attachment.setFileName((String) conf.get("filename"));
        attachment.setFileType(suffix);
        attachment.setFileSize(data.length);
        attachment.setUserId(Helper.getUser(request.getSession()).getUserId());
        attachment.setUploadTime(new Date());
        save(attachment);
        String attachmentPath = Helper.appURL(request) + ATTACHMENT_GET_PATH + attachment.getId();
        State storageState = new BaseState();
        if (storageState.isSuccess()) {
            storageState.putInfo("url", attachmentPath);
            storageState.putInfo("type", suffix);
            storageState.putInfo("original", "");
        }
        return storageState;
    }

    private static boolean validSize(byte[] data, long length) {
        return data.length <= length;
    }


    private State uploadBinary(Map<String, Object> conf, HttpServletRequest request) {
        FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }

        try {
            FileItemIterator iterator = upload.getItemIterator(request);

            while (iterator.hasNext()) {
                fileStream = iterator.next();

                if (!fileStream.isFormField())
                    break;
                fileStream = null;
            }

            if (fileStream == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            String originFileName = fileStream.getName();
            String suffix = FileType.getSuffixByFilename(originFileName);
            if (suffix.length() > 0 && suffix.indexOf(".") > 0) {
                suffix = suffix.substring(suffix.indexOf(".") + 1);
            }

            originFileName = originFileName.substring(0, originFileName.length() - suffix.length());

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            InputStream is = fileStream.openStream();
            byte[] bytes = IOUtils.toByteArray(is);
            IOUtils.closeQuietly(is);

            Attachment attachment = new Attachment();
            attachment.setContent(bytes);
            attachment.setFileName(originFileName);
            attachment.setFileType(suffix);
            attachment.setFileSize(bytes.length);
            attachment.setUserId(Helper.getUser(request.getSession()).getUserId());
            attachment.setUploadTime(new Date());
            save(attachment);
            String attachmentPath = Helper.appURL(request) + ATTACHMENT_GET_PATH + attachment.getId();

            State storageState = new BaseState();
            if (storageState.isSuccess()) {
                storageState.putInfo("url", attachmentPath);
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
        } catch (FileUploadException e) {
            return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
        } catch (IOException ignored) {
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);
        return list.contains(type);
    }
}
