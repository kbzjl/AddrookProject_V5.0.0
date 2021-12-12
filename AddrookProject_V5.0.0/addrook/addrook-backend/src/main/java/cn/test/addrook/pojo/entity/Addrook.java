package cn.test.addrook.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 18:52
 * @Version 1.0
 */
@TableName("sys_addrooks")
public class Addrook implements Serializable {
	private static final long serialVersionUID = -6001188822149165951L;
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;                    //主键
	@TableField("name")
	private String name;                //姓名
	@TableField("cellphone")
	private String cellphone;           //手机号码
	@TableField("password")
	private String password;            //登录密码
	@TableField("email")
	private String email;               //电子邮箱
	@TableField("address")
	private String address;             //联系地址
	@TableField("avatar")
	private String avatar;              //头像
	@TableField("create_time")
	private Date create_time;           //创建时间
	@TableField("modified_time")
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
