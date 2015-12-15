package sun.focusblog.admin.services;

import sun.focusblog.admin.domain.RelationType;

/**
 * Created by root on 2015/12/15.
 * <p/>
 * relation service interface
 */
public interface RelationService {

    /**
     * Get relation type between this two guys
     *
     */
    RelationType getRelation(String userId, String followerId);

    /**
     * follow this guy
     *
     */
    Integer follow(String userId, String followerId);

    /**
     * pull this guy into blacklist
     *
     */
    boolean pullIntoBlackList(String userId, String followerId);


    Integer follows(String userId);
}
