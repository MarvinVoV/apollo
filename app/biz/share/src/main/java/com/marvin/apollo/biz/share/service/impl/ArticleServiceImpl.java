package com.marvin.apollo.biz.share.service.impl;

import com.marvin.apollo.biz.share.service.ArticleService;
import com.marvin.apollo.core.model.domain.Article;
import com.marvin.apollo.core.model.dto.ArticleDTO;
import com.marvin.apollo.core.model.dto.CategoryDTO;
import com.marvin.apollo.core.model.dto.UserDTO;
import com.marvin.apollo.core.model.pagination.PageModel;
import com.marvin.apollo.core.service.repository.ArticleRepository;
import com.marvin.apollo.core.service.repository.CategoryRepository;
import com.marvin.apollo.core.service.repository.UserRepository;
import com.marvin.apollo.core.service.repository.convert.CategoryConvert;
import com.marvin.apollo.core.service.repository.convert.UserConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hufeng
 * @version ArticleServiceImpl.java, v 0.1 2019-01-13 23:31 Exp $
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ArticleRepository  articleRepository;
    @Autowired
    private UserRepository     userRepository;

    @Override
    public PageModel<Article> queryByPage(final Long userId, final Long categoryId, int pageNum, int pageSize) {
        PageModel<ArticleDTO> dtoPageModel = articleRepository.queryByPage(userId, categoryId, pageNum, pageSize);

        if (dtoPageModel.getPageSize() == 0) {
            return new PageModel<>();
        }

        UserDTO userDTO = userRepository.queryById(userId);
        List<CategoryDTO> categoryDTOS = categoryRepository.queryAllByUserId(userId);
        Map<Long, CategoryDTO> categoryMap =
                categoryDTOS.stream().collect(Collectors.toMap(CategoryDTO::getId, item -> item));

        return dtoPageModel.to((articleDTOList) -> {
            List<Article> targetList = new ArrayList<>();
            articleDTOList.forEach(item -> {
                Article article = new Article();
                article.setTitle(item.getTitle());
                article.setContent(item.getContent());
                article.setTag(item.getTag());
                article.setTop(item.isTop());
                article.setInvisibleStatus(item.getInvisibleStatus());
                article.setRecordStatus(item.getRecordStatus());
                article.setCreateTime(item.getCreateTime());
                article.setModifiedTime(item.getModifiedTime());
                article.setUser(UserConvert.convert(userDTO));
                article.setCategory(CategoryConvert.convert(categoryMap.get(item.getCategoryId())));
                targetList.add(article);
            });

            return targetList;
        });
    }
}
