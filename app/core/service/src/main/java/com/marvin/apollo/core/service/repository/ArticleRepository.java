package com.marvin.apollo.core.service.repository;

import com.marvin.apollo.core.model.dto.ArticleDTO;
import com.marvin.apollo.core.model.pagination.PageModel;

/**
 * @author hufeng
 * @version ArticleRepository.java, v 0.1 2019-01-13 20:55 Exp $
 */

public interface ArticleRepository {
    PageModel<ArticleDTO> queryByPage(Long userId, Long categoryId, int pageNum, int pageSize);
}
