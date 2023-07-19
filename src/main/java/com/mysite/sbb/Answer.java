package com.mysite.sbb;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

//주의 : Entity 클래스에는 @getter, @setter 를 붙이지 않는다 (DTO생성후 붙인다)
@Getter
@Setter

@Entity
public class Answer {	// 답변글을 저장하는 테이블 -> 질문 테이블의 자식테이블
						// 답변테이블은 질문테이블을 참조

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "text")
	private String content;
	
	@CreatedDate
	private LocalDateTime createDate; // CREATE_DATE
	
	//Foreign Key 할당하는 컬럼
	//answer(답변) : question(질문) = many:one -> 질문에 관하여 답변은 많이 존재하다.
	@ManyToOne		
	private Question question;
}
