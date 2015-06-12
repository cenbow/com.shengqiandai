package cn.vfunding.vfunding.biz.sina.syn;

public class SynResult {

	private boolean ok;
	
	private String result;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public SynResult(boolean ok, String result) {
		super();
		this.ok = ok;
		this.result = result;
	}
	
	
	
	
}
