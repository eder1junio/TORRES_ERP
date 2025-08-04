package com.empresatorressntos.inicio.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class webConfiguracao implements WebMvcConfigurer{
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://152.67.49.156") 
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
        .allowedHeaders("*"); ;
	}

}
