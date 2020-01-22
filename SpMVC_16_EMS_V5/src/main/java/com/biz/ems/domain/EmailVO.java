package com.biz.ems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

/*
 * @Entity. javax.persistence.entity. import시 주의. hibernate(구현) 아님
 * - JPA interface에 정의된 속성 지정
 * - 지금부터 이 클래스는 Entity이니 물리테이블과 연동되게 standby를 JPA에 세팅
 * 
 * ORM으로부터 table을 만들거나 불러오도록 준비한다 
 * 
 * 
 * bean의 속성 중에 <prop key="hibernate.hbm2ddl.auto">create</prop> 항목이 지정되어 있으면 Entity에 지정한 속성에 따라 처리한다
 * create : drop and create. 있으면 데이터가 있는지 여부에 상관없이 지우고 생성. 그러므로 실무에서는 update로 지정해서 사용한다
 * update : 테이블이 없으면 생성하고, 물리적 테이블과 구조가 다르면 validate하고 오류가 발생(실무에서는 주의해야하므로 alter는 해주지 않는다)
 * 			테이블이 변경되면 VO도 변경해주어야한다
 * none : 아무것도 하지 않는다
 * create-only : 테이블을 새로 생성
 * drop : 삭제
 * create-drop : drop & create를 하고 session이 끝나면 다시 drop. 보통 잠시 테스트용으로 사용
 * validate : 검증만
 * 
 * ※ 지정하지 않으면 기본값은 create이므로 테이블이 지워지고 다시 생기므로 주의 ※
 */

@Entity // EmailVO는 Entity라는 선언 
@Table(name="tbl_ems", schema="emsDB") // schema에 있는 tbl_ems랑 연결. 없으면 생성. 오라클에서 schema는 사용자 이름을 명시해줘야한다
public class EmailVO { // hibernate로 테이블을 sts VO에서 만들었다. ORM. 객체를 만드는것처럼 DB를 생성 

	// 보내는Email
	// 받는Email
	// 보내는 사람 이름
	// 받는 사람 이름
	// 제목
	// 내용
	// 작성일자
	// 작성시각
	
	/*
	 * 필드변수에 @Id나 @Column 속성을 지정
	 * - 테이블의 칼럼으로 생성
	 * @Id : PRIMARY KEY 칼럼으로 생성
	 * @generatedValue() 속성을 AUTO로 지정하면 auto_increment를 수행하여 insert할 때 자동으로 값을 채워준다
	 * 
	 * @Column() 지정 속성
	 * nullable : not null의 반대. true면 null이 가능하다는 의미고 false면 not null
	 * length : 칼럼의 길이. 지정하지 않으면 DB기본값으로 255
	 * 칼럼을 생성할때 name을 따로 지정해주지 않으면 필드변수의 이름으로 생성된다. name을 설정해주면 필드변수 이름과 다르게 생성하고자 할 때 사용
	 * 
	 * columnDefinition : 칼럼의 type을 DB의 고유한 type으로 강제 적용시키고자 할 때 사용
	 * 
	 */
	
	@Id // primary key로 사용하는 선언
	@GeneratedValue(strategy = GenerationType.AUTO) // 자동 increment. 오라클에서는 문제가 된다
	@Column(name="ems_seq")
	private long ems_seq; // long = MySQL BIG INT. oracle은 number
	// table의 칼럼명은 ems_seq로 지정하고 vo클래스의 필드변수는 emsSeq로
	
	@Column(name="from_email", nullable=false, length = 20) // 칼럼 빈값 불가(NOT NULL), 길이 20(영어, 숫자). VARCHAR(특정 DB는 char)
	private String from_email; // 칼럼 이름으로 자동 생성
	
	@Column(name="to_email", nullable=false) // name을 별도로 설정해주면 칼럼이름으로 들어간다. 크기지정을 안해주면 기본값은 255
	private String to_email; // 자동메서드는 camel을 쓰므로 snake는 피해야한다. name을 쓰는 이유
	// hibernate 4까지는 camel을 snake로 자동으로 바꿔줬었는데 지금은 별도로 라이브러리로 사용
	
	@Column(name="from_name", nullable=true, columnDefinition = "nVARCHAR(20)") // 한글이 들어가는 칼럼은 nVARCHAR로 따로 명시 필수
	private String from_name;
	
	@Column(nullable=true, columnDefinition = "nVARCHAR(20)")
	private String to_name;
	
	@Column(nullable=false, columnDefinition = "nVARCHAR(100)")
	private String subject;
	
	@Column(nullable=true, columnDefinition = "nVARCHAR(1000)")
	private String content;
	
	@Column(nullable=true)
	private String send_date;
	
	@Column(nullable=true)
	private String send_time;
}
