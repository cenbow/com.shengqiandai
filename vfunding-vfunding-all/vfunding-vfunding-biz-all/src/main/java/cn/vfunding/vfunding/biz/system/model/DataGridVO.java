package cn.vfunding.vfunding.biz.system.model;


import java.io.Serializable;
import java.util.List;


public class DataGridVO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3282859114019962733L;

	private List<T> inserted;
	
	private List<T> deleted;
	
	private List<T> updated;

	public List<T> getInserted() {
		return inserted;
	}

	public void setInserted(List<T> inserted) {
		this.inserted = inserted;
	}

	public List<T> getDeleted() {
		return deleted;
	}

	public void setDeleted(List<T> deleted) {
		this.deleted = deleted;
	}

	public List<T> getUpdated() {
		return updated;
	}

	public void setUpdated(List<T> updated) {
		this.updated = updated;
	}
	
	
}
