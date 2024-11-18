package com.virtue.boardservice.dto;

import java.time.LocalDateTime;

public record ArticleCommentDTO(
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        String content) {

    public static ArticleCommentDTO of(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new ArticleCommentDTO(createdAt, createdBy, modifiedAt, modifiedBy, content);
    }
}
