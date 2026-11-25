package com.example.secondproject.service;

import com.example.secondproject.dto.ArticleDto;
import com.example.secondproject.entity.Article;
import com.example.secondproject.repository.ArticleRepository;
import com.example.secondproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service    // 서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;  // 게시글 리파지터리 객체 투입

    @Autowired
    private CommentRepository commentRepository;

    public List<ArticleDto> index() {
        return articleRepository.findAll()
                .stream()
                .map(article -> ArticleDto.createArticleDto(article))
                .collect(Collectors.toList());
    }

    public ArticleDto show(Long id) {
        return articleRepository.findById(id)
                .map(article -> ArticleDto.createArticleDto(article))
                .orElse(null);
    }

    public ArticleDto create(ArticleDto dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;    // 컨트롤러가 400 응답
        }
        Article created = articleRepository.save(article);
        return ArticleDto.createArticleDto(created);
    }

    public ArticleDto update(Long id, ArticleDto dto) {
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if (target == null || !id.equals(article.getId())) {      // != 을 사용하면 id가 -128 ~ 127 까진 정상 작동하지만 범위를 벗어나면 값이 같아도 다르다고 판단함, 고로 equals를 사용하는 것이 안전하다.
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ArticleDto.createArticleDto(updated);
    }

    @Transactional
    public ArticleDto delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return null;
        }
        // 3. 댓글 먼저 삭제하기
        commentRepository.deleteAll(commentRepository.findByArticleId(id));
        // 4. 대상 삭제하기
        articleRepository.delete(target);
        return ArticleDto.createArticleDto(target);
    }

    @Transactional      // (궁금점) 트랜잭션 사용처가 이거밖에 없나?
    public List<ArticleDto> createArticles(List<ArticleDto> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        // 4. 결과 값 반환하기
        return articleList.stream()
                .map(article -> ArticleDto.createArticleDto(article))
                .collect(Collectors.toList());
    }
}
