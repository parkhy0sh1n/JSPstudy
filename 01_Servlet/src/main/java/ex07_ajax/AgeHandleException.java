package ex07_ajax;

public class AgeHandleException extends Exception {

	private static final long serialVersionUID = 4534766161295571271L;
	private int errorCode;
	
	public AgeHandleException(String message, int errorCode) {
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
