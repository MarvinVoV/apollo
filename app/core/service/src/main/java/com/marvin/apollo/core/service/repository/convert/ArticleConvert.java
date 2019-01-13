package com.marvin.apollo.core.service.repository.convert;

import com.marvin.apollo.common.dal.entity.ArticleEntity;
import com.marvin.apollo.core.model.dto.ArticleDTO;
import com.marvin.apollo.core.model.enums.InvisibleStatus;
import com.marvin.apollo.core.model.enums.RecordStatus;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hufeng
 * @version ArticleConvert.java, v 0.1 2019-01-13 21:11 Exp $
 */

public class ArticleConvert {
    public static ArticleDTO convert(ArticleEntity entity) {
        if (entity == null) {
            return null;
        }
        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        dto.setCreateTime(entity.getGmtCreate());
        dto.setModifiedTime(entity.getGmtModified());
        dto.setUserId(entity.getUserId());
        dto.setCategoryId(entity.getCategoryId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setInvisibleStatus(InvisibleStatus.getByCode(entity.getInvisible()));
        dto.setRecordStatus(RecordStatus.getByCode(entity.getStatus()));
        return dto;
    }

    public static List<ArticleDTO> convertList(List<ArticleEntity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return new ArrayList<>();
        }
        List<ArticleDTO> result = new ArrayList<>();
        entityList.forEach(item -> result.add(convert(item)));
        return result;
    }
}
