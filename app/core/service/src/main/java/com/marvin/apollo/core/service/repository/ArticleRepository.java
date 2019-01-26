package com.marvin.apollo.core.service.repository;

import com.marvin.apollo.core.model.dto.ArticleDto;
import com.marvin.apollo.core.model.pagination.PageModel;

/**
 * @author hufeng
 * @version ArticleRepository.java, v 0.1 2019-01-13 20:55 Exp $
 */

public interface ArticleRepository {
    PageModel<ArticleDto> queryByPage(Long categoryId, int pageNum, int pageSize);

    ArticleDto queryByRefIdentifier(Integer refNoteId, Integer refBookId);

    int save(ArticleDto articleDto);

    int update(ArticleDto articleDto);

    ArticleDto queryById(Long id);


}
