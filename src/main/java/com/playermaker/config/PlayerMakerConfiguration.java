package com.playermaker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.playermaker.errorsHandler.GlobalExceptionHandler;

@Configuration
@Import(GlobalExceptionHandler.class)
public class PlayerMakerConfiguration {

//	@Bean
//	public IpFiltering ipfltering() {
//		return new IpFiltering();
//	}
	
}
