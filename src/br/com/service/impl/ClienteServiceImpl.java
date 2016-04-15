package br.com.service.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.data.IClienteDAO;
import br.com.data.exception.DAOException;
import br.com.model.Cliente;
import br.com.service.IClienteService;
import br.com.service.exception.ServiceException;

@Dependent @Named("clienteService")
public class ClienteServiceImpl implements IClienteService {
	
	@Inject @Named("clienteDAO")
	private IClienteDAO clienteDAO;

	@Override
	public List<Cliente> consultar(Cliente model) throws ServiceException {
		try {
			return this.clienteDAO.consultar(model);
		} catch (DAOException e) {
			System.out.println("Erro ao consultar: " + e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public void inserir(Cliente model) throws ServiceException {
		try {
			this.clienteDAO.inserir(model);
		} catch (DAOException e) {
			System.out.println("Erro ao inserir: " + e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public void alterar(Cliente model) throws ServiceException {
		try {
			this.clienteDAO.alterar(model);
		} catch (DAOException e) {
			System.out.println("Erro ao alterar: " + e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public void excluir(int id) throws ServiceException {
		try {
			this.clienteDAO.excluir(id);
		} catch (DAOException e) {
			System.out.println("Erro ao excluir: " + e.getMessage());
			throw new ServiceException(e);
		}
	}

	public IClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(IClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

}
