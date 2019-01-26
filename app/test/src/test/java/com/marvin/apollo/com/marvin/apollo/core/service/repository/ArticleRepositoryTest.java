package com.marvin.apollo.com.marvin.apollo.core.service.repository;

import com.alibaba.fastjson.JSON;
import com.marvin.apollo.core.model.dto.ArticleDto;
import com.marvin.apollo.core.model.enums.InvisibleStatus;
import com.marvin.apollo.core.model.pagination.PageModel;
import com.marvin.apollo.core.service.repository.ArticleRepository;
import com.marvin.apollo.web.home.boot.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author hufeng
 * @version ArticleRepositoryTest.java, v 0.1 2019-01-13 22:20 Exp $
 */

public class ArticleRepositoryTest extends BaseTest {
    @Autowired
    private ArticleRepository articleRepository;

    private static final     int  NOTE_ID    = 2;
    private static final     int  BOOK_ID    = 1;
    private static transient long ARTICLE_ID = -1;


    @Test
    public void testA_save() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setRefNoteId(NOTE_ID);
        articleDto.setRefBookId(BOOK_ID);
        articleDto.setModifiedTime(new Date());
        articleDto.setTitle("save title");
        articleDto.setContentOfMd("markdown");
        articleDto.setViewCount(3);
        articleDto.setInvisibleStatus(InvisibleStatus.PUBLIC);
        ARTICLE_ID = articleRepository.save(articleDto);
        assertTrue(ARTICLE_ID > 0);
        System.out.println("ARTICLE_ID=" + ARTICLE_ID);
    }

    @Test
    public void testB_update() {
        ArticleDto articleDto = articleRepository.queryById(ARTICLE_ID);
        assertNotNull(articleDto);

        articleDto.setTitle("update title");
        int r = articleRepository.update(articleDto);
        assertTrue(r > 0);
    }


    @Test
    public void testC_pagingQuery() {
        PageModel<ArticleDto> pageModel = articleRepository.queryByPage(null, 1, 2);
        assertTrue(pageModel.getTotal() > 0);
        System.out.println(JSON.toJSONString(pageModel));
    }
}
