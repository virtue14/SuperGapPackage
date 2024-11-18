package com.virtue.boardservice.service;

import com.virtue.boardservice.domain.type.SearchType;
import com.virtue.boardservice.dto.ArticleDTO;
import com.virtue.boardservice.dto.ArticleUpdateDTO;
import com.virtue.boardservice.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDTO> searchArticles(SearchType searchType, String searchKeyword) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleDTO searchArticle(long l) {
        return null;
    }


    public void saveArticle(ArticleDTO articleDTO) {

    }

    public void updateArticle(long articleId, ArticleUpdateDTO articleUpdateDTO) {
    }

    public void deleteArticle(long articleId) {
    }
}
