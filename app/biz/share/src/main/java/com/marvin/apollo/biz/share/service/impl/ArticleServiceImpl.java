package com.marvin.apollo.biz.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.marvin.apollo.biz.share.constants.NoteFieldsDefine;
import com.marvin.apollo.biz.share.model.Note;
import com.marvin.apollo.biz.share.service.ArticleService;
import com.marvin.apollo.common.utils.helper.JsonHelper;
import com.marvin.apollo.core.model.dto.ArticleDto;
import com.marvin.apollo.core.model.enums.InvisibleStatus;
import com.marvin.apollo.core.model.pagination.PageModel;
import com.marvin.apollo.core.service.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * @author hufeng
 * @version ArticleServiceImpl.java, v 0.1 2019-01-13 23:31 Exp $
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger              LOGGER = LoggerFactory.getLogger(ArticleService.class);
    @Autowired
    private              ArticleRepository   articleRepository;
    @Autowired
    private              TransactionTemplate transactionTemplate;

    @Override
    public PageModel<ArticleDto> queryByPage(final Long categoryId, int pageNum, int pageSize) {
        PageModel<ArticleDto> dtoPageModel = articleRepository.queryByPage(categoryId, pageNum, pageSize);

        if (dtoPageModel.getPageSize() == 0) {
            return new PageModel<>();
        }
        return articleRepository.queryByPage(categoryId, pageNum, pageSize);
    }

    @Override
    public void syncArticle(Map<String, Object> payload) {
        if (CollectionUtils.isEmpty(payload)) {
            LOGGER.warn("Payload was empty.");
            return;
        }
        Note note = parseNote(payload);
        if (note == null) {
            throw new RuntimeException("Convert payload to Note failed.");
        }

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                ArticleDto repoArticle = articleRepository.queryByRefIdentifier(note.getNoteId(), note.getBookId());
                if (repoArticle == null) {
                    ArticleDto articleDto = new ArticleDto();
                    updateArticleFields(articleDto, note, true);
                    int articleId = articleRepository.save(articleDto);
                    LOGGER.info("Save note success. ID={0}, NoteId={1}, BookId={2}",
                            articleId, note.getNoteId(), note.getBookId());
                } else {
                    updateArticleFields(repoArticle, note, false);
                    articleRepository.update(repoArticle);
                    LOGGER.info("Update note success. ID={0}, noteId={1}, BookId={2}",
                            repoArticle.getId(), note.getNoteId(), note.getBookId());
                }
            }
        });
    }

    private Note parseNote(Map<String, Object> payload) {
        if (CollectionUtils.isEmpty(payload)) {
            return null;
        }
        JSONObject jsonObject = JsonHelper.getJsonFromMap(payload);
        JSONObject data = jsonObject.getJSONObject(NoteFieldsDefine.DATA);

        String title = (String) data.get(NoteFieldsDefine.TITLE);
        String contentOfMd = (String) data.get(NoteFieldsDefine.BODY);
        String contentOfHtml = (String) data.get(NoteFieldsDefine.BODY_HTML);
        int bookId = (Integer) data.get(NoteFieldsDefine.BOOK_ID);
        int noteId = (Integer) data.get(NoteFieldsDefine.ID);
        Date modifiedTime = Date.from(Instant.parse((String) data.get(NoteFieldsDefine.CONTENT_UPDATED_AT)));

        return Note.builder()
                .title(title)
                .contentOfHtml(contentOfHtml)
                .contentOfMd(contentOfMd)
                .bookId(bookId)
                .noteId(noteId)
                .updateTime(modifiedTime)
                .build();
    }

    private void updateArticleFields(ArticleDto dto, Note note, boolean create) {
        if (create) {
            dto.setInvisibleStatus(InvisibleStatus.PUBLIC);
        }

        dto.setTitle(note.getTitle());
        dto.setContentOfHtml(note.getContentOfHtml());
        dto.setContentOfMd(note.getContentOfMd());
        dto.setModifiedTime(note.getUpdateTime());
        dto.setRefNoteId(note.getNoteId());
        dto.setRefBookId(note.getBookId());
    }

}
