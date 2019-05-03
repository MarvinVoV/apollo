package com.marvin.apollo.core.service.repository;

import com.marvin.apollo.core.model.dto.ArticleDto;
import com.marvin.apollo.core.model.pagination.PageModel;

/**
 * @author hufeng
 * @version ArticleRepository.java, v 0.1 2019-01-13 20:55 Exp $
 */

public interface ArticleRepository {
    /**
     * Pagination query
     *
     * @param categoryId category id
     * @param pageNum    page num 1-based
     * @param pageSize   page size
     * @return result
     */
    PageModel<ArticleDto> queryByPage(Long categoryId, int pageNum, int pageSize);

    /**
     * Query by ref identifier
     *
     * @param refNoteId ref note id
     * @param refBookId ref book id
     * @return dto
     */
    ArticleDto queryByRefIdentifier(Integer refNoteId, Integer refBookId);

    /**
     * Save
     *
     * @param articleDto article dto
     * @return effective count
     */
    int save(ArticleDto articleDto);

    /**
     * Update
     *
     * @param articleDto article dto
     * @return effective count
     */
    int update(ArticleDto articleDto);

    /**
     * query by articleId
     *
     * @param id articleId
     * @return dto
     */
    ArticleDto queryById(Long id);


}
