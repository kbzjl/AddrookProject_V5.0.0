package cn.test.addrook.commons.base.util;

import org.springframework.transaction.annotation.Propagation;

import java.util.Properties;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 12:20
 * @Version 1.0
 */
public class BaseConstants {
	private static Properties props = new Properties();
	static {
		try {
			//加载配置文件
			props.load(BaseConstants.class.getClassLoader().getResourceAsStream("props/base.properties"));

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * <b>Swagger 配置信息: 文档标题</b>
	 */
	public static final String SWAGGER_TITLE = props.getProperty("swagger.title");
	/**
	 * <b>Swagger 配置信息: 文档描述</b>
	 */
	public static final String SWAGGER_DESCRIPTION = props.getProperty("swagger.description");

	/**
	 * <b>Swagger 配置信息: 文档版本</b>
	 */
	public static final String SWAGGER_VERSION = props.getProperty("swagger.version");

	/**
	 * <b>Swagger 配置信息: 联系人姓名</b>
	 */
	public static final String SWAGGER_CONTACT_NAME = props.getProperty("swagger.contact.name");

	/**
	 * <b>Swagger 配置信息: 联系人url 地址</b>
	 */
	public static final String SWAGGER_CONTACT_URL = props.getProperty("swagger.contact.url");

	/**
	 * <b>Swagger 配置信息: email 地址</b>
	 */
	public static final String SWAGGER_CONTACT_EMAIL = props.getProperty("swagger.contact.email");
	/**
	 * <b>Swagger 配置信息: 扫描基础类信息包</b>
	 */
	public static final String SWAGGER_BASE_PACKAGE = props.getProperty("swagger.base.package");
}

