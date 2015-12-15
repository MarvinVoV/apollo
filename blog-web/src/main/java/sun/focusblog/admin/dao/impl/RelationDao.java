package sun.focusblog.admin.dao.impl;

import org.springframework.stereotype.Repository;
import sun.focusblog.admin.dao.BaseDao;
import sun.focusblog.admin.dao.IRelationDao;
import sun.focusblog.admin.domain.Relation;

/**
 * Created by root on 2015/12/15.
 * <p/>
 * Implement class
 */
@Repository
public class RelationDao extends BaseDao implements IRelationDao {
    @Override
    public Relation getRelation(Relation relation) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "getRelation"), relation);
    }

    @Override
    public boolean save(Relation relation) {
        return getSqlSession().insert(buildStatement(NAMESPACE, "save"), relation) == 1;
    }


    @Override
    public int follows(String userId) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "follows"), userId);
    }
}
