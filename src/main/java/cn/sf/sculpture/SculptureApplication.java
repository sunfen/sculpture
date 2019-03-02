package cn.sf.sculpture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages="cn.sf.sculpture.*")
@EnableScheduling
public class SculptureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SculptureApplication.class, args);
	}
	
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

