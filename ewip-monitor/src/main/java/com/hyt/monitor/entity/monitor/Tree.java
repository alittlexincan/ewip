package com.hyt.monitor.entity.monitor;

import java.io.Serializable;

/**
 * 树的实体类
 * 
 */
public class Tree implements Serializable{

	private static final long serialVersionUID = -777128314209165896L;
	private String id; // 节点id
	private String pId; // 节点父id
	private String name; // 节点名称
	private boolean isParent; // 该节点是否是父节点（表示该节点是否有子类）
	private boolean isOpen; // 该节点是否展开
	private String operate; // 操作是否允许点击
	private String code; // 编码
	private boolean chkDisabled; // 是否禁用checkbox或radio
	private String moduleUrl; // 模块路径
	private String disasterCode; // 模块编码
	private boolean nocheck=false; // 是否禁用checkbox或radio 直接数据库返回
	private String sourceAreaCode;
	private boolean  checked;
	


	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public boolean isChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return moduleUrl
	 */
	public String getModuleUrl() {
		return moduleUrl;
	}

	/**
	 * @param moduleUrl
	 *            the moduleUrl to set
	 */
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getDisasterCode() {
		return disasterCode;
	}

	public void setDisasterCode(String disasterCode) {
		this.disasterCode = disasterCode;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(String nocheck) {
		if ("true".equals(nocheck)) {
			this.nocheck = true;
		} else if ("false".equals(nocheck)) {
			this.nocheck = false;
		} 
	}

    public String getSourceAreaCode() {
        return sourceAreaCode;
    }

    public void setSourceAreaCode(String sourceAreaCode) {
        this.sourceAreaCode = sourceAreaCode;
    }
	
	
}