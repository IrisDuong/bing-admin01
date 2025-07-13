package com.bh.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.bh.admin.util.AppProperties;
import com.bh.admin.util.BaseAuditorAwareImpl;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableConfigurationProperties(AppProperties.class)
public class BingAdminBootBeApplication implements CommandLineRunner{
	@Autowired
	AppProperties appProps;
	
	public static void main(String[] args) {
		SpringApplication.run(BingAdminBootBeApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
        System.out.println("-------------Global variable:");
        System.out.println("Token Secret = "+appProps.getAuth().getToken().get("secretKey"));
        System.out.println("Token expInMilSec = "+appProps.getAuth().getToken().get("expInMilSec"));
	}
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//				.allowedMethods("GET", "POST","OPTIONS")
//				.allowedOrigins("http://localhost:3000");
//			}
//		};
//	}
	
	@Bean(name = "auditorAware")
	public AuditorAware<String> auditorAware(){
		return new BaseAuditorAwareImpl();
	}
}
