package com.codex.task.shop.config;

import com.codex.task.shop.BasePackageMarker;
import com.codex.task.shop.exception.handlers.HandlerExceptionResolverProxy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = BasePackageMarker.class)
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public HandlerExceptionResolverProxy handlerExceptionResolverProxy(@Autowired HandlerExceptionResolver handlerExceptionResolver) {
		return new HandlerExceptionResolverProxy(handlerExceptionResolver);
	}
}
