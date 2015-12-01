package sun.focusblog.admin.services;

import sun.focusblog.admin.components.ueditor.define.State;
import sun.focusblog.admin.domain.Attachment;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by root on 2015/12/1.
 */
public interface AttachmentService {

    boolean save(Attachment attachment);

    State upload(Map<String, Object> conf, HttpServletRequest request);

    Attachment query(String id);

    State listImages(HttpServletRequest request,int start, int size);
}
