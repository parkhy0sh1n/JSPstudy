-- 시퀀스
DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ NOCACHE;

-- 테이블
/*
	CLOB : LOB이란 Large OBject의 약자로 대용량 데이터를 저장할 수 있는 데이터 타입이다. 
		   일반적으로 그래픽, 이미지, 사운드 등 비정형 데이터를 저장할 때 LOB타입을 사용한다. 
		   문자형 대용량 데이터는 CLOB, 그래픽, 이미지, 동영상 등의 대이터는 BLOB를 주로 사용한다.
 */
DROP TABLE BOARD;
CREATE TABLE BOARD (
	BOARD_NO 	  NUMBER NOT NULL,
	TITLE 		  VARCHAR2(1000 BYTE) NOT NULL,
	CONTENT 	  CLOB,
	MODIFIED_DATE DATE,
	CREATED_DATE  DATE NOT NULL,
	CONSTRAINT PK_BOARD PRIMARY KEY(BOARD_NO)
);

-- 행(ROW)
/*
	SYSDATE : 현재 날짜
 */
INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, '[공지]월요일알림', 'DBCP는 DataBase Connection Pool을 의미한다.', NULL, SYSDATE);
INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, '[협조]내일준비물', '물감, 리코더, 공책 가져오기', NULL, SYSDATE);
COMMIT