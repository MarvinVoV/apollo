package com.marvin.apollo.biz.share.service.impl;

import com.marvin.apollo.biz.share.service.ArticleService;
import com.marvin.apollo.core.model.dto.ArticleDto;
import com.marvin.apollo.core.model.pagination.PageModel;
import com.marvin.apollo.core.service.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hufeng
 * @version ArticleServiceImpl.java, v 0.1 2019-01-13 23:31 Exp $
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public PageModel<ArticleDto> queryByPage(final Long categoryId, int pageNum, int pageSize) {
        PageModel<ArticleDto> dtoPageModel = articleRepository.queryByPage(categoryId, pageNum, pageSize);

        if (dtoPageModel.getPageSize() == 0) {
            return new PageModel<>();
        }

        return articleRepository.queryByPage(categoryId, pageNum, pageSize);
    }
}
