package br.com.conductor.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;

import br.com.conductor.desafio.model.Cliente;

@SpringBootApplication
@EnableAutoConfiguration  
@ComponentScan({ "br.com.conductor"})
public class DesafioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    } 
    
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
      return new RepositoryRestConfigurerAdapter() {
        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
          config.setBasePath("/api");
          config.setDefaultMediaType(MediaType.APPLICATION_JSON); 
          config.exposeIdsFor(Cliente.class);          
        }
      };
    }
   
}
