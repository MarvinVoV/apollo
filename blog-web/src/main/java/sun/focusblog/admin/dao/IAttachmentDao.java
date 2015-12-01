package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.Attachment;

import java.util.List;

/**
 * Created by root on 2015/12/1.
 */
public interface IAttachmentDao {
    String NAMESPACE = "sun.focusblog.admin.domain.Attachment";

    boolean save(Attachment attachment);

    Attachment query(String id);

    List<Attachment> list(String userId, int start, int size);
}
