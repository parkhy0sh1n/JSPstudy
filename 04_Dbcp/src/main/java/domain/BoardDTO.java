package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
	DTO  : 데이터를 전달하는 역할.
		   각각의 클래스의 field에 필요한 값을 저장해 놓고, 
		   다른 클래스에서 해당 값을 사용할때는 getter setter 메소드를 이용해서 메소드로 값을 수정하거나 호출 하는 방식을 사용한다.
	POJO : Plain Old Java Object.
		   간단히 POJO는 말 그대로 해석을 하면 '오래된 방식의 간단한 자바 오브젝트'라는 뜻이다.
*/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BoardDTO {
	
	// 컬럼명을 필드로 선언하여 식별자로 사용(DB 컬럼명과 일치하지 않아도 된다.)
	private int board_no;
	private String title;			// 안녕
	private String content;			// Hello
	private Date modified_date;
	private Date created_date;
	
}
