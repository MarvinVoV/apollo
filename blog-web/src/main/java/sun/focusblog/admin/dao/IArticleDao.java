package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.Article;

/**
 * Created by root on 2015/11/24.
 *
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

}
