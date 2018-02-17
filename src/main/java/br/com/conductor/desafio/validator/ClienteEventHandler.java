package br.com.conductor.desafio.validator;

import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import br.com.conductor.desafio.model.Cliente;

@RepositoryEventHandler
public class ClienteEventHandler {

	  @HandleBeforeSave
	  public void handlePersonSave(Cliente c) throws Exception {
	    
	  }

	  @HandleBeforeSave
	  public void handleProfileSave(Cliente p) throws Exception {
	    
	  }
	
}
