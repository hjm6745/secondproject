package com.example.secondproject.api;

import com.example.secondproject.dto.ArticleDto;
import com.example.secondproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController     // REST API용 컨트롤러 선언
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    // GET
    @GetMapping("/api/articles")
    public List<ArticleDto> index() {
        return articleService.index();
    }
    @GetMapping("/api/articles/{id}")
    public ArticleDto show(@PathVariable Long id){
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleDto> create(@RequestBody ArticleDto dto) {
        ArticleDto created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDto> update(@PathVariable Long id, @RequestBody ArticleDto dto) {        // ResponseEntity 는 상태코드를 같이 반환해준다. 위 코드들도 바꿔주는 것이 원칙.
        ArticleDto updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDto> delete(@PathVariable Long id) {
        ArticleDto deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<ArticleDto>> transactionTest(@RequestBody List<ArticleDto> dtos) {
        List<ArticleDto> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}