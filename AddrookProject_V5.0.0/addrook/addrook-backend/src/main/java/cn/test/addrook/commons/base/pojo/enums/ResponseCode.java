package cn.test.addrook.commons.base.pojo.enums;

public enum ResponseCode {
	RESPONSE_SUCCESS(20000,"系统业务处理完成响应成功"),
	RESPONSE_UNANTH(30000,"用户为进行系统认证"),
	RESPONSE_ERROR(40000,"系统业务处理逻辑错误"),
	RESPONSE_EXCEPTION(50000,"系统响应异常");
	private Integer code;
	private String remark;

	private ResponseCode(Integer code, String remark) {
		this.code = code;
		this.remark = remark;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
