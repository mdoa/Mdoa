package com.mdoa.framework.model;

import java.util.List;

public class FrameworkTree {
	/**
	 * 节点Id,用于进行标识
	 */
	private String id;
	
	/**
	 * 该节点的数据库实际Id
	 */
	private String thisId;
	
	/**
	 * 节点名称
	 */
	private String text;
	
	/**
	 * 节点状态，打开或者关闭
	 */
	private String state = "close";

	/**
	 * 是否被选中
	 */
	private String checked;

	/**
	 * 一些额外的属性
	 */
	private String attributes;

	/**
	 * 子节点列表
	 */
	private List<FrameworkTree> children;
	
	/**
	 * 节点所属的类样式，默认为无样式，如果setList时存在子节点，启用样式“icon-blank icon-dept”
	 */
	private String iconCls;
	
	public FrameworkTree(){
		
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public List<FrameworkTree> getChildren() {
		return children;
	}

	public void setChildren(List<FrameworkTree> children) {
		this.children = children;
//		if(children == null || children.size() == 0){
//			this.iconCls = "icon-blank icon-dept";
//		}
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getThisId() {
		return thisId;
	}

	public void setThisId(String thisId) {
		this.thisId = thisId;
	}
}
