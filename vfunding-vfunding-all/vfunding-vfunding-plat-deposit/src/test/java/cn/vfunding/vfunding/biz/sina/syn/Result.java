package cn.vfunding.vfunding.biz.sina.syn;

import java.io.Serializable;

/**
 * 
 * @author wang.zeyan
 * @date 2015年1月28日
 * @version 1.0
 */
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean _break;
	
	private String threadName;

	public boolean is_break() {
		return _break;
	}

	public void set_break(boolean _break) {
		this._break = _break;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public Result(boolean _break, String threadName) {
		super();
		this._break = _break;
		this.threadName = threadName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Result [_break=");
		builder.append(_break);
		builder.append(", threadName=");
		builder.append(threadName);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
