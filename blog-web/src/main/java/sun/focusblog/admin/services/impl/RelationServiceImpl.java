package sun.focusblog.admin.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.focusblog.admin.context.CacheNameSpace;
import sun.focusblog.admin.dao.IRelationDao;
import sun.focusblog.admin.domain.Relation;
import sun.focusblog.admin.domain.RelationType;
import sun.focusblog.admin.services.RelationService;
import sun.focusblog.framework.cache.annotation.CacheUpdate;
import sun.focusblog.framework.cache.annotation.Cacheable;

/**
 * Created by root on 2015/12/15.
 * <p/>
 * Relation service
 */
@Service
public class RelationServiceImpl implements RelationService {

    private static final Logger logger = LoggerFactory.getLogger(RelationServiceImpl.class);
    @Autowired
    private IRelationDao relationDao;

    @Override
    public RelationType getRelation(String userId, String followerId) {
        Relation relation = relationDao.getRelation(new Relation(userId, followerId));
        if (relation == null) {
            return RelationType.NOTHING;
        }
        return relation.getType() == 1 ? RelationType.FOLLOWER : RelationType.BLACKLIST;
    }

    @CacheUpdate(
            namespace = CacheNameSpace.CACHE_FOLLOWS,
            fieldsKey = {"#userId"},
            valueType = Integer.class,
            updateRetVal = true
    )
    @Transactional
    @Override
    public Integer follow(String userId, String followerId) {
        boolean result;
        result = relationDao.save(new Relation(userId, followerId, RelationType.FOLLOWER.getValue()));
        if (!result) {
            logger.error("follow failed.");
        }
        return follows(userId);
    }

    @Transactional
    @Override
    public boolean pullIntoBlackList(String userId, String followerId) {
        return relationDao.save(new Relation(userId, followerId, RelationType.BLACKLIST.getValue()));
    }

    @Cacheable(namespace = CacheNameSpace.CACHE_FOLLOWS, fieldsKey = {"#userId"})
    @Override
    public Integer follows(String userId) {
        return relationDao.follows(userId);
    }
}
