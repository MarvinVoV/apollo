package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.Article;

/**
 * Created by root on 2015/11/24.
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

}
