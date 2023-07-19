package com.mysite.sbb;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//QuestionRepository : Question 테이블을 CRUD하는 메소드 구현
public interface QuestionRepository extends JpaRepository<Question,Integer> {

	//client -> controller -> service (비즈니스 로직처리)
	//-> repository(DAO : DB를 직접 접근) -> Entity -> DB 
	//repository : 메소드를 사용해서 DB의 테이블을 select,insert,update,delete
	// Jparepository <Question (->Entity class) ,Integer (->Question Entity class의 primary key 컬럼의 데이터타입) > 
	//	인터페이스를 구현해서 생성해야한다. 
	
	/*
	 JPA 메소드
		findAll() : select
		save() : insert , update
		delete() : delete
	
	 테이블 검색 -> 기본적으로 2개 메소드가 자동으로 등록되어있음
	 findAll()
	 findById()
	 
	 그외는 등록해야한다. -> 간단하고 자주사용하는것 / 복잡한 쿼리 -> JPQL ,QueryDSL
	 
	 
	 */
	
	//검색된 레코드 1개 일때 Optional에 저장
	//select * from question where subject = '인자'
	Optional<Question> findBySubject(String subject); 
	
	//검색된 레코드가 여러개일시 List에 저장
	List<Question> findByContent(String content);
	
	//특정 컬럼의 값을 검색 : Like -> 레코드가 여러개가 검색 : List
	//select * from question where subject like '%?%?';
	List<Question> findBySubjectLike(String subject);
	
	//select * from question where content like '%?%?';
	List<Question> findByContentLike(String content);
	
	//제목과 내용컬럼에서 검색
	//select * from question where subject like '%?%' or content like '%?%'
	List<Question> findBySubjectLikeOrContent(String subject , String content);
	
	//정렬 해서 출력 하는 메소드 생성  <== 간단하고 자주 사용하는것 ,  복잡한 쿼리 : JPQL, QueryDSL 
	// 날짜를 기준으로 오름 차순 정렬 (Asc) : 1 ---> 9 , A ----> Z , ㄱ ---> ㅎ 
	// 날짜를 기준으로 내림 차순 정렬 (Desc) : 9---> 1,  Z-----> A , ㅎ ---> ㄱ
	
	//정렬해서 출력하는 메소드 생성 
	//select * from question order by create_Date asc; 
	List<Question> FindAllByOrederByCreateDateAsc();
		
	//select * from question order by create_Date desc; 
	List<Question> FindAllByOrderByCreateDateDesc();
	
	//제목을 기준으로 검색후 날짜를 기준으로 오름 차순 정렬 후 출력 
		// select * from question where subject Like '%?%' order by create_date asc; 
		List<Question> findBySubjectLikeOrderByCreateDateAsc(String subject); 
		
		// select * from question where subject Like '%?%' order by create_date desc; 
		List<Question> findBySubjectLikeOrderByCreateDateDesc(String subject); 

		// 검색기능을 사용 (select , find ) 
		
		
		// save ()    : insert, update 
		
		// delete()  : delete 

	
	
}
