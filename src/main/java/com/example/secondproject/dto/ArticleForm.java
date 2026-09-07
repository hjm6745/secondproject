package com.example.secondproject.dto;

import com.example.secondproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor     // title과 content를 매개변수로 하는 생성자를 자동생성
@ToString
public class ArticleForm {
    private String title;   // 제목을 받을 필드
    private String content; // 내용을 받을 필드


    /*       생성자 리팩터링
    // 전송받은 제목과 내용을 필드에 저장하는 생성자 추가
    public ArticleForm(String title, String content) {  // MBR -> Generate -> Constructor
        this.title = title;
        this.content = content;
    }
    */

    /*      ToString 리팩터링
    // 데이터를 잘 받았는지 확인할 toString() 메서드 추가
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    */


    public Article toEntity() {
        return new Article(null, title, content);
    }
}
