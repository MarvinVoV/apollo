package sun.focusblog.admin.services;

import sun.focusblog.admin.domain.Article;
import sun.focusblog.admin.domain.auth.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by root on 2015/12/3.
 * <p/>
 * Article service
 */
public interface ArticleService {
    /**
     * Persist article entity
     *
     * @param article mapping entity
     * @return boolean
     */
    boolean save(Article article);

    /**
     * Inflate article entity with userId,create date, update date fields.
     *
     * @param httpSession session
     * @param article     article entity
     * @return boolean
     */
    boolean saveWithInnerInfo(HttpSession httpSession, Article article);

    /**
     * Query article by id
     *
     * @param id unique id
     * @return Article
     */
    Article query(String id);


    /**
     * Query article list order by date
     *
     * @param user  user
     * @param start page start
     * @param size  page size
     * @return list
     */
    List<Article> listOrderByDate(User user, int start, int size);


    /**
     * Count all article number
     *
     * @param user user
     * @return int
     */
    int countAll(User user);

    /**
     * Get config properties
     *
     * @return page size
     */
    String getPageSize();
}
