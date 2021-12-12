package cn.test.addrook.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 19:13
 * @Version 1.0
 */
@ApiModel(value = "通讯录视图信息", description = "通讯录视图信息")
public class AddrookVO implements Serializable {
	private static final long serialVersionUID = -2694168607977158496L;

	@ApiModelProperty(value = "主键")
	private Long id;                    //主键
	@ApiModelProperty(value = "姓名")
	private String name;                //姓名
	@ApiModelProperty(value = "手机号码")
	private String cellphone;           //手机号码
	@ApiModelProperty(value = "登录密码")
	private String password;            //登录密码
	@ApiModelProperty(value = "电子邮箱")
	private String email;               //电子邮箱
	@ApiModelProperty(value = "联系地址")
	private String address;             //联系地址
	@ApiModelProperty(value = "头像")
	private String avatar;              //头像
	@ApiModelProperty(value = "创建时间")
	private Date create_time;           //创建时间
	@ApiModelProperty(value = "修改时间")
	private Date modified_time;         //修改时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getModified_time() {
		return modified_time;
	}

	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
	}
}
