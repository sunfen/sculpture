package cn.sf.sculpture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages="cn.sf.sculpture.*")
@EnableScheduling
public class SculptureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SculptureApplication.class, args);
	}
}

