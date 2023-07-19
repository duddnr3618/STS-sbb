package com.mysite.sbb;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Question {
	//@Entity : 클래스 위에 부여 ,DB의 테이블과 매칭되는 자바 클래스 
		//-> Question : 테이블명 / 변수 : 컬럼명

	//JPA를 사용해서 테이블의 컬럼을 정의할시 반드시 primary key컬럼 정의해야함.
	@Id		//primary key컬럼 부여 ( 중복된 데이터를 넣을수 없다)
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//자동으로 값을 증가(시퀀스)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "Text")
	private String content;
	
	private LocalDateTime createDate;
	
	/*@Column(length =100)
	private String name ;
	*/
	
	//해당 질뭉에 대한 모든 답변을 불러오는 컬럼 -> 실제 테이블에는 적용되지 않음
	//Question 객체의 id필드의 들어오는 값에 대한 Answer 테이블의 모든 값을 List에 담아서 온다
	//getAnswerList()
	@OneToMany (mappedBy="question", cascade=CascadeType.REMOVE)	
	//Question : one , Answer : many
	//cascade=CascadeType.REMOVE : 질문이 제거되면 answer(답변)도 제거
	private List<Answer> answerList;
	
	
}
