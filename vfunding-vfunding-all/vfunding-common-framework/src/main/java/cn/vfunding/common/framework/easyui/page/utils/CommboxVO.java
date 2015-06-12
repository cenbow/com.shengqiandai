package cn.vfunding.common.framework.easyui.page.utils;

import java.io.Serializable;

public class CommboxVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4516092343926391694L;

	private String id;

	private String text;

	private boolean selected;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
