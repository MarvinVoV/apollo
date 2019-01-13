package com.marvin.apollo.biz.share.service;

import com.marvin.apollo.core.model.domain.Article;
import com.marvin.apollo.core.model.pagination.PageModel;

/**
 * @author hufeng
 * @version ArticleService.java, v 0.1 2019-01-13 23:26 Exp $
 */
public interface ArticleService {
    PageModel<Article> queryByPage(Long userId, Long categoryId, int pageNum, int pageSize);
}
