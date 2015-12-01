package sun.focusblog.admin.dao.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import sun.focusblog.admin.dao.BaseDao;
import sun.focusblog.admin.dao.IAttachmentDao;
import sun.focusblog.admin.domain.Attachment;

import java.util.List;

/**
 * Created by root on 2015/12/1.
 */
@Repository
public class AttachmentDao extends BaseDao implements IAttachmentDao {


    @Override
    public boolean save(Attachment attachment) {
        return getSqlSession().insert(buildStatement(NAMESPACE, "save"), attachment) == 1;
    }

    @Override
    public Attachment query(String id) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "query"), id);
    }

    @Override
    public List<Attachment> list(String userId, int start, int size) {
        return getSqlSession().selectList(buildStatement(NAMESPACE, "list"), userId,
                new PageBounds(start, size));
    }
}
