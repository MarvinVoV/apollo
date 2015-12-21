package sun.focusblog.admin.services;

import sun.focusblog.admin.domain.Comment;
import sun.focusblog.admin.domain.auth.User;

import java.util.List;

/**
 * Created by root on 2015/12/16.
 * <p/>
 * Article comments service
 */
public interface CommentsService {

    /**
     * Wrap list method using default page index and page size
     *
     * @param articleId article id
     * @return list
     */
    List<Comment> defaultList(String articleId);

    /**
     * List comments with tree structure
     *
     * @param articleId article id
     * @param start     page start
     * @param size      page size
     * @return list
     */
    List<Comment> list(String articleId, int start, int size);

    /**
     * Query comment by id
     *
     * @param id comment id
     * @return comment
     */
    Comment query(String id);

    /**
     * Count comments
     *
     * @param articleId article id
     * @return int
     */
    int count(String articleId);

    /**
     * Get comment pagination size
     *
     * @return page size
     */
    int getPageSize();


    /**
     * Persist comment entity
     *
     * @param comment comment
     * @return boolean
     */
    boolean save(Comment comment, User user);

}
