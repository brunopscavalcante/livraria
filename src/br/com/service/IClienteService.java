package br.com.service;

import java.util.List;

import br.com.model.Cliente;
import br.com.service.exception.ServiceException;

public interface IClienteService {

	List<Cliente> consultar(Cliente model) throws ServiceException;
	
	void inserir(Cliente model) throws ServiceException;
	
	void alterar(Cliente model) throws ServiceException;
	
	void excluir(int id) throws ServiceException;
	
}
