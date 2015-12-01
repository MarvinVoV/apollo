package sun.focusblog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.focusblog.admin.components.ueditor.ConfigManager;
import sun.focusblog.admin.components.ueditor.define.ActionMap;
import sun.focusblog.admin.components.ueditor.define.AppInfo;
import sun.focusblog.admin.components.ueditor.define.BaseState;
import sun.focusblog.admin.components.ueditor.define.State;
import sun.focusblog.admin.domain.Attachment;
import sun.focusblog.admin.services.AttachmentService;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by root on 2015/12/1.
 */
@Controller
@RequestMapping("/editor")
public class UEditorController {
    @Autowired
    private ConfigManager configManager;

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/attachment/{id}")
    @ResponseBody
    public byte[] attachment(@PathVariable String id) {
        Attachment attachment = attachmentService.query(id);
        if (attachment != null) {
            return attachment.getContent();
        } else {
            return new byte[]{0};
        }
    }

    @RequestMapping(value = "/control", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String control(@RequestParam String action,
                          @RequestParam(required = false) String noCache,
                          @RequestParam(required = false) Integer start,
                          @RequestParam(required = false) Integer size,
                          HttpServletRequest request) {
        if (action == null || !ActionMap.mapping.containsKey(action)) {
            return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
        }

        if (this.configManager == null || !this.configManager.valid()) {
            return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
        }
        State state = null;

        int actionCode = ActionMap.getType(action);

        Map<String, Object> conf;

        switch (actionCode) {

            case ActionMap.CONFIG:
                return this.configManager.getAllConfig().toString();

            case ActionMap.UPLOAD_IMAGE:
            case ActionMap.UPLOAD_SCRAWL:
            case ActionMap.UPLOAD_VIDEO:
            case ActionMap.UPLOAD_FILE:
                conf = this.configManager.getConfig(actionCode);
                state = attachmentService.upload(conf, request);
                break;

            case ActionMap.CATCH_IMAGE:
                conf = configManager.getConfig(actionCode);
                String[] list = request.getParameterValues((String) conf.get("fieldName"));
//                state = new ImageHunter(conf).capture(list);
                break;

            case ActionMap.LIST_IMAGE:
            case ActionMap.LIST_FILE:
                state = attachmentService.listImages(request, start,size);
                break;

        }

        return state.toJSONString();
    }
}
