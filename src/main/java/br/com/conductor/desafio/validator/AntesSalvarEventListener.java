package br.com.conductor.desafio.validator;

import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

public class AntesSalvarEventListener extends AbstractRepositoryEventListener<Object> {
	  
	  @Override
	  public void onBeforeSave(Object entity) {
	   
	  }

	  @Override
	  public void onAfterDelete(Object entity) {
	     
	  }
}
