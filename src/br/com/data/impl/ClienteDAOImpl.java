package br.com.data.impl;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.data.AbstractDAO;
import br.com.data.IClienteDAO;
import br.com.data.enums.DatabaseStrings;
import br.com.data.exception.DAOException;
import br.com.model.Cliente;

@Dependent @Named("clienteDAO")
public class ClienteDAOImpl extends AbstractDAO implements IClienteDAO {

	private static final String CLIENTE_CONSULTA = "pSeltCliente01";
	private static final String CLIENTE_INCLUSAO = "pInstCliente01";
	private static final String CLIENTE_ALTERACAO = "pUpdtCliente01";
	private static final String CLIENTE_EXCLUSAO = "pDeltCliente01";
	
	@Override
	public List<Cliente> consultar(Cliente model) throws DAOException {
		List<Cliente> retorno = new ArrayList<>();
		try {
			con = getConnection(DatabaseStrings.MYSQL_livraria);
			CallableStatement cs = con.prepareCall(buildProc(CLIENTE_CONSULTA, 1));
			
			setCsString(cs, model.getNome(), 1);
			resultado = cs.executeQuery();
			Cliente cliente = null;
			while (hasNext()){
				cliente = new Cliente();
				cliente.setId(resultado.getInt("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setEmail(resultado.getString("email"));
				cliente.setEndereco(resultado.getString("endereco"));
				cliente.setDataNascimento(resultado.getDate("dataNascimento"));
				retorno.add(cliente);
			}
		} catch(Exception e){
			System.out.println("Erro ao consultar: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
		return retorno;
	}


	@Override
	public void inserir(Cliente model) throws DAOException {
		try {
			con = getConnection(DatabaseStrings.MYSQL_livraria);
			CallableStatement cs = con.prepareCall(buildProc(CLIENTE_INCLUSAO, 4));
			
			int i = 0;
			setCsString(cs, model.getNome(), ++i);
			setCsString(cs, model.getEmail(), ++i);
			setCsString(cs, model.getEndereco(), ++i);
			setCsDate(cs, model.getDataNascimento(), ++i);
			cs.executeUpdate();
		} catch(Exception e){
			System.out.println("Erro ao inserir: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void alterar(Cliente model) throws DAOException {
		try {
			
		} catch(Exception e){
			System.out.println("Erro ao alterar: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void excluir(int id) throws DAOException {
		try {
			
		} catch(Exception e){
			System.out.println("Erro ao excluir: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	
}
