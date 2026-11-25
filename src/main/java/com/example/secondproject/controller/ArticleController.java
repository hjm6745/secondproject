package com.example.secondproject.controller;

import com.example.secondproject.dto.ArticleDto;
import com.example.secondproject.dto.CommentDto;
import com.example.secondproject.service.ArticleService;
import com.example.secondproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;  // 서비스 객체 주입
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @GetMapping("/articles/{id}")    // 데이터 조회 접수
    public String show(@PathVariable Long id, Model model, RedirectAttributes rttr) {
        log.info("id = " + id);   // id를 잘 받았는지 확인하는 로그 찍기
        // 1. id를 조회해 데이터 가져오기
        ArticleDto articleDto = articleService.show(id);
        // 1-1. 게시글이 없으면(삭제됨/잘못된 id) 목록 페이지로 리다이렉트
        if (articleDto == null) {
            rttr.addFlashAttribute("msg", "존재하지 않는 게시글입니다.");
            return "redirect:/articles";
        }
        List<CommentDto> commentDtos = commentService.comments(id);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleDto);
        model.addAttribute("commentDtos", commentDtos); // 댓글 목록을 모델에 등록
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        List<ArticleDto> articleDtoList = articleService.index();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleDtoList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model, RedirectAttributes rttr) {
        // 수정할 데이터 가져오기
        ArticleDto articleDto = articleService.show(id);
        // 1-1. 데이터가 없으면(삭제됨/잘못된 id) 목록 페이지로 리다이렉트
        if (articleDto == null) {
            rttr.addFlashAttribute("msg", "존재하지 않는 게시글입니다.");
            return "redirect:/articles";
        }
        // 모델에 데이터 등록하기
        model.addAttribute("article", articleDto);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }

}
