package cn.sf.sculpture.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableJpaRepositories(basePackages="cn.sf.sculpture")
@EnableTransactionManagement
public class DataSourceConfig {

}
