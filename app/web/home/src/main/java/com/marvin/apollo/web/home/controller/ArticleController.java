package com.marvin.apollo.web.home.controller;

import com.marvin.apollo.biz.share.service.ArticleService;
import com.marvin.apollo.core.model.dto.ArticleDto;
import com.marvin.apollo.core.model.pagination.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hufeng
 * @version ArticleController.java, v 0.1 2019-01-19 02:28 Exp $
 */

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/article")
    public PageModel<ArticleDto> articlePageQuery(Long categoryId,
                                                  @RequestParam(defaultValue = "1") int pageNum,
                                                  @RequestParam(defaultValue = "5") int pageSize) {
        return articleService.queryByPage(categoryId, pageNum, pageSize);
    }

}
