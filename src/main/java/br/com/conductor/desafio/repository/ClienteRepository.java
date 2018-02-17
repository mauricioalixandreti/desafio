package br.com.conductor.desafio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.conductor.desafio.model.Cliente;

@RestResource
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@RestResource(path = "noneStartsWith", rel = "noneStartsWith")
	public Page findByNomeStartsWith(@Param("nome") String nome, Pageable p);

	public Page findAll(Pageable p);

	@Override
	@RestResource(exported = true)
	void delete(Long id);
	
	@Override
	public Cliente findOne(Long id);
	
	
}
