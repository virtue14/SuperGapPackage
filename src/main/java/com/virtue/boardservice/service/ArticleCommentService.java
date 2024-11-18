package com.virtue.boardservice.service;

import com.virtue.boardservice.dto.ArticleCommentDTO;
import com.virtue.boardservice.repository.ArticleCommentRepository;
import com.virtue.boardservice.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDTO> searchArticleComment(long articleId) {
        return List.of();
    }

    public void saveArticleComment(ArticleCommentDTO articleCommentDTO) {
    }
}
