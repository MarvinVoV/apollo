package com.marvin.apollo.biz.share.service;

import com.marvin.apollo.core.model.dto.ArticleDto;
import com.marvin.apollo.core.model.pagination.PageModel;

import java.util.Map;

/**
 * @author hufeng
 * @version ArticleService.java, v 0.1 2019-01-13 23:26 Exp $
 */
public interface ArticleService {
    PageModel<ArticleDto> queryByPage(Long categoryId, int pageNum, int pageSize);


    void syncArticle(Map<String, Object> payload);

}
