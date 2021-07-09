package com.codex.task.shop.config;

import com.codex.task.shop.BasePackageMarker;
import com.codex.task.shop.exception.handlers.HandlerExceptionResolverProxy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.HandlerExceptionResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = BasePackageMarker.class)
@EnableJpaRepositories(basePackages = "com.codex.task.shop.repository")
@EntityScan(basePackages = "com.codex.task.shop.model.entity")
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
