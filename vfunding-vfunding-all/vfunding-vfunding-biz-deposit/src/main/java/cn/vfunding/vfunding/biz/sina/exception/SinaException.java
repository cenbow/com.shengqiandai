package cn.vfunding.vfunding.biz.sina.exception;


/**
 * 
 * @author wang.zeyan
 * @date 2015年2月9日
 * @version 1.0
 */
public class SinaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4451792614751741416L;
	
	public final static SinaExceptionCode SINA_EXCEPTION = new SinaExceptionCode(101 , "Sina 异常!");
	public final static SinaExceptionCode SINA_SET_REALNAME_EXCEPTION = new SinaExceptionCode(102 , "Sina实名认证异常!");
	
	public SinaException(String message){
		super(message);
	} 
	
	public SinaException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public SinaException(SinaExceptionCode exceptionCode , Throwable cause){
		
		this(exceptionCode.getCode()+":"+exceptionCode.getMessage() , cause);
	}
	
	static class SinaExceptionCode{
		
		private int code;
		
		private String message;
		
		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		public SinaExceptionCode(int code, String message) {
			super();
			this.code = code;
			this.message = message;
		}
	}
	
	
	
	
}
