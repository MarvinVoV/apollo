package com.marvin.apollo.core.service.repository.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marvin.apollo.common.dal.entity.ArticleEntity;
import com.marvin.apollo.common.dal.mybatis.ArticleMapper;
import com.marvin.apollo.core.model.dto.ArticleDTO;
import com.marvin.apollo.core.model.pagination.PageModel;
import com.marvin.apollo.core.service.repository.ArticleRepository;
import com.marvin.apollo.core.service.repository.convert.ArticleConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hufeng
 * @version ArticleRepositoryImpl.java, v 0.1 2019-01-13 21:47 Exp $
 */
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleRepositoryImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public PageModel<ArticleDTO> queryByPage(Long userId, Long categoryId, int pageNum, int pageSize) {
        PageInfo<ArticleEntity> entityPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> articleMapper.queryList(userId, categoryId));
        List<ArticleDTO> articleDTOList = ArticleConvert.convertList(entityPageInfo.getList());
        PageModel<ArticleDTO> pageModel = new PageModel<>();
        pageModel.setList(articleDTOList);
        pageModel.setPageNo(entityPageInfo.getPageNum());
        pageModel.setPageSize(entityPageInfo.getPageSize());
        pageModel.setTotal(entityPageInfo.getTotal());
        return pageModel;
    }
}
