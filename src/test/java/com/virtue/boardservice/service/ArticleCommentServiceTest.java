package com.virtue.boardservice.service;

import com.virtue.boardservice.domain.Article;
import com.virtue.boardservice.domain.ArticleComment;
import com.virtue.boardservice.dto.ArticleCommentDTO;
import com.virtue.boardservice.repository.ArticleCommentRepository;
import com.virtue.boardservice.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;
    @Mock
    private ArticleCommentRepository articleCommentRepository;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsArticleComments() {
        // given
        Long articleId = 1L;
        given(articleRepository.findById(articleId))
                .willReturn(Optional.of(Article.of("title", "content", "#hashtag")));

        // when
        List<ArticleCommentDTO> articleComments =  sut.searchArticleComment(1L);

        // then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        // Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);
        // When
        sut.saveArticleComment(ArticleCommentDTO.of(LocalDateTime.now(), "Uno", LocalDateTime.now(), "Uno", "comment"));
        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}
