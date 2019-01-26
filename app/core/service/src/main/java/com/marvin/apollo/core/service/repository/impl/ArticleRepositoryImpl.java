package com.marvin.apollo.core.service.repository.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marvin.apollo.common.dal.entity.ArticleEntity;
import com.marvin.apollo.common.dal.mybatis.ArticleMapper;
import com.marvin.apollo.core.model.dto.ArticleDto;
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
    public PageModel<ArticleDto> queryByPage(Long categoryId, int pageNum, int pageSize) {
        PageInfo<ArticleEntity> entityPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> articleMapper.queryList(categoryId));

        List<ArticleDto> articleDtoList = ArticleConvert.INSTANCE.entitiesToDtos(entityPageInfo.getList());
        PageModel<ArticleDto> pageModel = new PageModel<>();
        pageModel.setList(articleDtoList);
        pageModel.setPageNo(entityPageInfo.getPageNum());
        pageModel.setPageSize(entityPageInfo.getPageSize());
        pageModel.setTotal(entityPageInfo.getTotal());
        return pageModel;
    }

    @Override
    public ArticleDto queryByRefIdentifier(Integer refNoteId, Integer refBookId) {
        ArticleEntity articleEntity = articleMapper.queryByRefIdentifier(refNoteId, refBookId);
        return ArticleConvert.INSTANCE.entityToDto(articleEntity);
    }

    @Override
    public int save(ArticleDto articleDto) {
        ArticleEntity entity = ArticleConvert.INSTANCE.dtoToEntity(articleDto);
        articleMapper.insert(entity);
        return (int) entity.getId();
    }

    @Override
    public int update(ArticleDto articleDto) {
        ArticleEntity entity = ArticleConvert.INSTANCE.dtoToEntity(articleDto);
        return articleMapper.updateByPrimaryKey(entity);
    }

    @Override
    public ArticleDto queryById(Long id) {
        if (id == null || id < 0) {
            return null;
        }
        ArticleEntity articleEntity = articleMapper.selectByPrimaryKey(id);
        return ArticleConvert.INSTANCE.entityToDto(articleEntity);
    }
}
