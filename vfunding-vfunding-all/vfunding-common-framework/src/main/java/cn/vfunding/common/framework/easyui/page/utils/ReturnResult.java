package cn.vfunding.common.framework.easyui.page.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回结果
 * @author Liu Jun
 *
 * @param <T>
 */
public class ReturnResult<T> implements java.io.Serializable {


	private static final long serialVersionUID = 8605505931393275323L;

	private boolean success = true;
	private Map<String, String> errors = new HashMap<String, String>();
	private T entity = null;
	private List<T> entities = new ArrayList<T>();
	private String error;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
