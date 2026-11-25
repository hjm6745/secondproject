package com.example.secondproject.service;
import com.example.secondproject.dto.ArticleDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 1. 예상 데이터
        ArticleDto a = new ArticleDto(1L, "가가가가", "1111");
        ArticleDto b = new ArticleDto(2L, "나나나나", "2222");
        ArticleDto c = new ArticleDto(3L, "다다다다", "3333");
        List<ArticleDto> expected = new ArrayList<ArticleDto>(Arrays.asList(a, b, c));
        // 2. 실제 데이터
        List<ArticleDto> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공() {
        // 1. 예상 데이터
        Long id = 1L;
        ArticleDto expected = new ArticleDto(id, "가가가가", "1111");
        // 2. 실제 데이터
        ArticleDto article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_실패() {
        // 1. 예상 데이터
        Long id = -1L;
        ArticleDto expected = null;
        // 2. 실제 데이터
        ArticleDto article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "라라라라";
        String content = "4444";
        ArticleDto dto = new ArticleDto(null, title, content);
        ArticleDto expected = new ArticleDto(4L, title, content);
        // 2. 실제 데이터
        ArticleDto article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleDto dto = new ArticleDto(id, title, content);
        ArticleDto expected = null;
        // 2. 실제 데이터
        ArticleDto article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }
}