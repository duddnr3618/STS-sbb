package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

	//QuestionRepository 객체 주입
	@Autowired
	private QuestionRepository qr;
		
	//AnswerRepository : Answer 테이블의 CRUD 
	@Autowired
	private AnswerRepository ar;
		
	
	//@Test
	void contextLoads() {
		Question q1 = new Question ();
		q1.setSubject("오늘의 날씨는 ?");
		q1.setContent("오늘의 날씨가 궁금합니다");
		q1.setCreateDate(LocalDateTime.now());
		
		qr.save(q1);
		
		Question q2 = new Question ();
		q2.setSubject("스프링 부트 질문입니다");
		q2.setContent("스프링 부트가 궁금합니다.");
		q2.setCreateDate(LocalDateTime.now());
		
		qr.save(q2);
		
	}
	
	//@Test
	void contextLoads1() {
		Question q3 = new Question ();
		q3.setSubject("오늘의 스포츠 ?");
		q3.setContent("오늘의 스포츠가 궁금합니다");
		q3.setCreateDate(LocalDateTime.now());
		
		qr.save(q3);
		
		Question q4 = new Question ();
		q4.setSubject("미국의 날씨는?");
		q4.setContent("미국의 날씨가 궁금합니다.");
		q4.setCreateDate(LocalDateTime.now());
		
		qr.save(q4);
		
	}
	
	//@Test
	void recodeCount () {
		List <Question> all = qr.findAll();
		//qr : Question 테이블과 연결
		//findAll(); : select * from question;
		
		//assertEquals(기대치,값) : 기대치 = 값 (초록색) / 기대치 != 값  (빨강)
		assertEquals(4,all.size());
		
	}

	//@Test
	void subjectTest() {
		List <Question> all = qr.findAll(); 
		Question q = all.get(0);
		
		assertEquals("오늘의 날씨는 ?",q.getSubject());
		
		
	}
	
	//@Test
	void idSearch () {
		//Optional null을 쉽게 처리해주는 객체
		//qr.findById(1) -> select * from question where id =1;
		Optional<Question> o = qr.findById(1);
		
		//isPresent() : Optional에 저장된 객체가 null이 아닐때
		if (o.isPresent()) {
			Question q = o.get();	//Optional에 저장된 객체를 끄집어내서 q 변수에 할당
			
			//asserEquals(1,getId() );
			assertEquals("오늘의 날씨는 ?",q.getSubject());		
	}	
}
	//제목으로 검색 test
	//@Test	
	void searchSubject() {
		Optional<Question> all = qr.findBySubject("오늘의 날씨는 ?");
		
		Question q = all.get();
		
		assertEquals("오늘의 날씨는 ?",q.getSubject());
		
	}
	
	//@Test	
	void searchSubject2() {
		List<Question> all2 = qr.findByContent("임의로 등록");
		
		Question q = all2.get(0);
		
		assertEquals("임의로 등록",q.getContent());
		
	}
	
	//@Test
	void searchSubjectLike () {
		//select * from question where subject like '%날씨%';
		List<Question> all = qr.findBySubjectLike("%날씨%");
		
		Question q = all.get(1);
		
		assertEquals(2,all.size());
		assertEquals(4,q.getId());
		
	}
	
	void searchContentLike() {
		List<Question> all = qr.findByContentLike("%궁금%");
		
		Question q = all.get(3);
		assertEquals(4,all.size());
		assertEquals(4, q.getId());
	}
	
	//제목과 내용을 동시에 검색
	//@Test
	void searchSubjectContentLike() {
		//select * from question where subject like '%?%' or content '%?%'
		List<Question> all = qr.findBySubjectLikeOrContent("%날씨%", "%부트%");
		
		Question q = all.get(0);
		assertEquals(4,all.size());
		assertEquals("오늘의 날씨가 궁금합니다" , q.getContent() );
	}
	
	//@Test
	void sortCreateDateAsc () {
		//날짜를 기준으로 오름차순 정렬
		List<Question> all = qr.FindAllByOrederByCreateDateAsc();
		
		System.out.println("============ 날짜를 오름차순으로 정렬 츨력 : 시작 ============");
		for (int i = 0 ; i < all.size() ; i++) {
			System.out.println("==========" + i + "=========");
			Question q = all.get(i);
			
			System.out.println(q.getId());
			System.out.println(q.getCreateDate());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
			
			
		}
		System.out.println("============== 날짜를 오름차순으로 정렬 츨력 : 종료 =====================");
		
	}
	
	
	//select * from question where subject Like '%?%' order by create_date asc;
	//@Test
	void searchSubjectAndSort () {
		List<Question> all = qr.findBySubjectLikeOrderByCreateDateAsc("%날씨%");
	
		assertEquals(2,all.size());
		
		System.out.println("==== 콘솔 출력 시작");
		for (int i = 0 ; i < all.size(); i++) {
			System.out.println("==== Question : " + i + "===== ");
			Question q = all.get(i);
			System.out.println(q.getId());
			System.out.println(q.getCreateDate());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
		}
		
		System.out.println("==== 콘솔 출력 종료");
	}
	
	
	//@Test
	void searchSubjectAndSortDesc () {
		List<Question> all = qr.findBySubjectLikeOrderByCreateDateDesc("%날씨%");
	
		assertEquals(2,all.size());
		
		System.out.println("==== 콘솔 출력 시작");
		for (int i = 0 ; i < all.size(); i++) {
			System.out.println("==== Question : " + i + "===== ");
			Question q = all.get(i);
			System.out.println(q.getId());
			System.out.println(q.getCreateDate());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
		}
		
		System.out.println("==== 콘솔 출력 종료 =====");
	}
	
	//값 넣기 : save() , insert
	//@Test
	void insertQuestion () {
		Question q = new Question ();
		
		q.setSubject("제목 : 스프링부트는 프레임워크 입니까?");
		q.setContent("내용 : 스프링부트는 프레임워크 입니다. Ioc/Di , AOP , PSA 기능이 적용");
		q.setCreateDate(LocalDateTime.now());
		
		qr.save(q); 
	}
	

	//update : 수정 , save() 
	//@Test
	void updateQuestion () {
		Question q = new Question () ; 
		
		q.setId(5);
		
		q.setContent("수정된 내용2 입니다");
		q.setSubject("수정된 제목 2 입니다.");
		q.setCreateDate(LocalDateTime.now());
		
		qr.save(q); 	
		
	}
	//@Test
	void deleteQuestion () {
		Question q = new Question() ; 
		
		q.setId(5);
		
		qr.delete(q);	
		
	}
	
	//Answer 테이블에 값을 입력 : Question 테이블을 참좀해서 값을 넣어야 한다.  
	//@Test
	void insertAnswer() {
		
		Question q = new Question();
		q.setId(1);
		
		Answer a = new Answer(); 
		
		a.setContent("오늘은 날씨가 매우 맑습니다");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);
		
		ar.save(a); 	
		
	}
	
	//@Test
	void insertAnswer2() {
		Question q = new Question(); 
		q.setId(2);
		
		Answer a = new Answer(); 
		a.setContent("스프링 부트 질문의 답변 1");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);
		
		ar.save(a); 
		
		Answer aa = new Answer(); 
		aa.setContent("스프링 부트 질문의 답변 2");
		aa.setCreateDate(LocalDateTime.now());
		aa.setQuestion(q);
		
		ar.save(aa); 
		
	}
	
	@Test
	void insertAnswer3() {
		Question q = new Question(); 
		q.setId(3);
		
		Answer a = new Answer(); 
		a.setContent("야구");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);
		
		ar.save(a); 
		
		Answer aa = new Answer(); 
		aa.setContent("축구");
		aa.setCreateDate(LocalDateTime.now());
		aa.setQuestion(q);
		
		ar.save(aa); 
		
		Answer aaa = new Answer(); 
		aaa.setContent("농구");
		aaa.setCreateDate(LocalDateTime.now());
		aaa.setQuestion(q);
		
		ar.save(aaa); 
				
		
	}
	
	
}
