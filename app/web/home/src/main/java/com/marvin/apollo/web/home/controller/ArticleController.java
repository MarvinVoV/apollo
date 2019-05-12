package com.marvin.apollo.web.home.controller;

import com.marvin.apollo.biz.share.service.ArticleService;
import com.marvin.apollo.core.model.dto.ArticleDto;
import com.marvin.apollo.core.model.pagination.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author hufeng
 * @version ArticleController.java, v 0.1 2019-01-19 02:28 Exp $
 */

@RestController
public class ArticleController {
    private static final Logger         LOGGER = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private              ArticleService articleService;

    @RequestMapping("/article")
    public PageModel<ArticleDto> articlePageQuery(Long categoryId,
                                                  @RequestParam(defaultValue = "1") int pageNum,
                                                  @RequestParam(defaultValue = "5") int pageSize) {
        return articleService.queryByPage(categoryId, pageNum, pageSize);
    }

    @RequestMapping("/article/p/{id}")
    public ArticleDto queryById(@PathVariable("id") Long id) {
        return articleService.queryById(id);
    }

    @PostMapping("/callback")
    public ResponseEntity syncNote(@RequestBody Map<String, Object> payload) {
        try {
            articleService.syncArticle(payload);
        } catch (Exception e) {
            LOGGER.error("Sync Note Error", e);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
