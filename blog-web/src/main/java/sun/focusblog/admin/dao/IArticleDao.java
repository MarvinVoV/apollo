package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.Article;

import java.util.List;

/**
 * Created by root on 2015/11/24.
 * <p/>
 * Article DAO
 */
public interface IArticleDao {
    String NAMESPACE = "sun.focusblog.admin.domain.Article";

    /**
     * Persist article entity
     *
     * @param article article entity
     * @return boolean
     */
    boolean save(Article article);

    /**
     * Query by unique id
     *
     * @param id unique id
     * @return article
     */
    Article query(String id);


    /**
     * Query article list order by date
     *
     * @param userId userId
     * @param start  start
     * @param size   page size
     * @return list
     */
    List<Article> listOrderByDate(String userId, int start, int size);


    /**
     * Count all article by user_id
     *
     * @param userId userId
     * @return int
     */
    int countAll(String userId);

    /**
     * update article
     *
     * @param article article
     * @return int
     */
    int update(Article article);

}
