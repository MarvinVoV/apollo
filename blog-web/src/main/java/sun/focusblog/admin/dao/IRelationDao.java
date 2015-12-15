package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.Relation;

/**
 * Created by root on 2015/12/15.
 */
public interface IRelationDao {

    String NAMESPACE = "sun.focusblog.admin.domain.Relation";

    /**
     * has relation between this two guys
     *
     * @param relation entity
     * @return boolean
     */
    Relation getRelation(Relation relation);

    /**
     * Save relation
     *
     * @param relation entity
     * @return boolean
     */
    boolean save(Relation relation);


    /**
     * Get follow number
     * @param userId
     * @return
     */
    int follows(String userId);


}
