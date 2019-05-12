package com.marvin.apollo.com.marvin.apollo.core.service.repository.convert;

import com.alibaba.fastjson.JSON;
import com.marvin.apollo.common.dal.entity.ArticleEntity;
import com.marvin.apollo.core.model.dto.ArticleDto;
import com.marvin.apollo.core.service.repository.convert.ArticleConvert;
import org.junit.Test;

import java.util.Date;

/**
 * @author hufeng
 * @version ArticleConvertTest.java, v 0.1 2019-01-14 23:54 Exp $
 */

public class ArticleConvertTest {
    @Test
    public void testConvert() {
        ArticleEntity entity = new ArticleEntity();
        entity.setId(1L);
        entity.setTitle("tile");
        entity.setTag("tag");
        entity.setContentOfMd("hello".getBytes());
        entity.setTop(1);
        entity.setDigest("digest");
        entity.setInvisible(1);
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setStatus(1);
        ArticleDto articleDto = ArticleConvert.INSTANCE.entityToDto(entity);
        System.out.println(JSON.toJSONString(articleDto));

    }
}