package com.eliab.sistemas.sgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin
@EnableWebMvc
@EnableFeignClients
@SpringBootApplication
public class SgpApplication {

	@Bean
	public WebMvcConfigurer corsConfigurerTest() {
		return new WebMvcConfigurerAdapter() {
			//@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}



	public static void main(String[] args) {
		SpringApplication.run(SgpApplication.class, args);
	}

}
