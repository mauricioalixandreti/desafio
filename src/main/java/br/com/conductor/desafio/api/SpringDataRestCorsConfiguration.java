package br.com.conductor.desafio.api;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SpringDataRestCorsConfiguration  extends RepositoryRestConfigurerAdapter {

	  @Override
	  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

	    config.getCorsRegistry().addMapping("api/clientes/**")
	      .allowedOrigins("http://localhost:8080")
	      .allowCredentials(false).maxAge(3600);
	  }
	}