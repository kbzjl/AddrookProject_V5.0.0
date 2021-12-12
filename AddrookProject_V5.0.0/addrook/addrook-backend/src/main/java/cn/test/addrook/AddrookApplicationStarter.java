package cn.test.addrook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/10 23:08
 * @Version 1.0
 */

@MapperScan("cn.test.addrook.dao")
@SpringBootApplication
public class AddrookApplicationStarter {
	public static void main(String[] args) {
		SpringApplication.run(AddrookApplicationStarter.class, args);
	}
}
