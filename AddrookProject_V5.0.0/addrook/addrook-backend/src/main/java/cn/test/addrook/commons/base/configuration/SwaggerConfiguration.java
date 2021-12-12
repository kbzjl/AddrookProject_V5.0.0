package cn.test.addrook.commons.base.configuration;

import cn.test.addrook.commons.base.util.BaseConstants;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.nio.channels.SelectableChannel;


/**
 * <b>Swagger 配置类 API文档</b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 10:08
 * @Version 1.0
 */
@Configuration
public class SwaggerConfiguration {
	@Bean
	public Docket createRestAPI(){

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				//设定Sawgger 文档生成信息
				.apiInfo(apiInfo())
				//设定Sawgger 扫描接口的基础包信息
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build();
		return docket;
	}

	private ApiInfo apiInfo() {
		//设定生成文档的标题
		return new ApiInfoBuilder()
				//设定生成文档的标题
				.title(BaseConstants.SWAGGER_TITLE)
				//设定生成文档描述信息
				.description(BaseConstants.SWAGGER_DESCRIPTION)
				//设定文档版本
				.version(BaseConstants.SWAGGER_VERSION)
				//设定文档维护联系人信息
				.contact(new Contact(BaseConstants.SWAGGER_CONTACT_NAME,
						BaseConstants.SWAGGER_CONTACT_URL,
						BaseConstants.SWAGGER_CONTACT_EMAIL))
				.build();
	}
}
