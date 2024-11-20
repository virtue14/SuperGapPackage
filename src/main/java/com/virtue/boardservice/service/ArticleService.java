package com.virtue.boardservice.service;

import com.virtue.boardservice.domain.Article;
import com.virtue.boardservice.domain.type.SearchType;
import com.virtue.boardservice.dto.ArticleDto;
import com.virtue.boardservice.dto.ArticleWithCommentsDto;
import com.virtue.boardservice.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {

        if (searchKeyword == null || searchKeyword.isBlank()) { // searchKeyword가 null이거나 공백이면
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
        };

    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }


    /**
     *  게시글 저장
     */
    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    /**
     * 게시글 수정
     */
    public void updateArticle(ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.id()); // 아티클 정보 가져오기

            if (dto.title() != null) article.setTitle(dto.title());
            if (dto.title() != null) article.setContent(dto.content());
            article.setHashtag(dto.hashtag());
//        articleRepository.save(article); // 클래스레벨 @Transactional로 대체

        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패, 게시글을 찾을 수 없습니다 - dto: {}" + dto);
        }

    }

    /**
     * 게시글 삭제
     * @param articleId 삭제할 게시글 아이디
     */
    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }

}
