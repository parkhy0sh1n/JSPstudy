package ex07_ajax;

public class MyHandleException extends Exception {
	
	/*
		serialVersionUID : 모든 Class는 UID를 가지고 있는데, Class의 내용이 변경되면 UID값 역시 같이 바뀌어 버린다. 
						   직렬화하여 통신하고 UID값으로 통신한 게 정상인지 확인하는데, 그 값이 바뀌게 되면 다른 Class로 인식을 해버리게 된다.
						   이를 방지하기 위해 고유값으로 미리 명시를 해주는 것이다.
		객체 직렬화란?
			- 직렬화: Heap 영역에 위치한 객체를 출력 가능한 상태로 만드는 작업이다.
			- 역직렬화: 직렬화된 객체를 다시 Heap 영역에 넣기 위한 작업이다.
			- 직렬화의 대상은 객체의 Attribute의 값이다. (메소드는 그저 주소값만 필요할 뿐)
	 */
	private static final long serialVersionUID = 134063727195348483L;
	private int errorCode;
	
	public MyHandleException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
