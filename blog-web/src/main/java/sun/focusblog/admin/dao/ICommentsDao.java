package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.Comment;

import java.util.List;

/**
 * Created by root on 2015/12/16.
 */
public interface ICommentsDao {

    String NAMESPACE = "sun.focusblog.admin.domain.Comment";

    /**
     * List article comments with pagination
     *
     * @param articleId article id
     * @param start     page start
     * @param size      page size
     * @return list
     */
    List<Comment> list(String articleId, int start, int size);

    /**
     * delete comments by id
     *
     * @param id comment id
     * @return boolean
     */
    boolean delete(String id);

    /**
     * Query comment by id
     *
     * @param id comment id
     * @return comment
     */
    Comment query(int id);

    /**
     * Count comments
     *
     * @param articleId article id
     * @return int
     */
    int count(String articleId);
}
