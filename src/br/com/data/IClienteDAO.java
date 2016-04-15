package br.com.data;

import java.util.List;

import br.com.data.exception.DAOException;
import br.com.model.Cliente;

public interface IClienteDAO {
	
	List<Cliente> consultar(Cliente model) throws DAOException;
	
	void inserir(Cliente model) throws DAOException;
	
	void alterar(Cliente model) throws DAOException;
	
	void excluir(int id) throws DAOException;
}
