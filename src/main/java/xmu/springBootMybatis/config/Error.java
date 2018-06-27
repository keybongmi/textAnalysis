package xmu.springBootMybatis.config;

/**
 * Error messages for our RequestException
 * 
 * @author 曹将将
 */
public enum Error {
	YOU_DO_NOT_HAVE_PERMISSION("1", "You do not have permission"),
	NO_VALID_TOKEN("2", "Token is not valid");
	
	public final String errorCode;
	public final String errorMessage;
	
	private Error(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() { return errorCode; }
	
	public String getErrorMessage() { return errorMessage; }
}