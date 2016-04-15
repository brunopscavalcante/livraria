package br.com.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.model.Cliente;
import br.com.service.IClienteService;
import br.com.service.exception.ServiceException;
import utils.FormatUtils;
import utils.message.MessageUtils;

@ManagedBean @SessionScoped
public class ClienteBean extends BaseBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6533572478688203204L;
	@Inject @Named("clienteService")
	private IClienteService clienteService;
	private Cliente model;
	private Cliente clienteSelecioando;
	private List<Cliente> listaClientes;
	private boolean selecionado = false;
	
	private static String PAGE_HOME = "cliente";
	private static String PAGE_INCLUSAO = "incluirCliente";
	private static String PAGE_ALTERAR = "alteraCliente";
	
	@Override
	public String prepararConsulta() {
		this.model = new Cliente();
		return PAGE_HOME;
	}
	
	@Override
	public String consultar() {
		try {
			this.listaClientes = this.clienteService.consultar(this.model);
			if (FormatUtils.isEmptyOrNull(this.listaClientes)){
				MessageUtils.addGlobalAlertMessage(MessageUtils.getLocalizedMessage("mensagem_consulta_sem_resultado"));
			}
		} catch (ServiceException e) {
			MessageUtils.addGlobalErrorMessage("Erro ao consultar: " + e.getMessage());
		}
		return PAGE_HOME;
	}
	
	@Override
	public String prepararInclusao() {
		this.model = new Cliente();
		return PAGE_INCLUSAO;
	}
	
	public String inserir(){
		try {
			if (validarInclusao()){
				this.clienteService.inserir(this.model);
			}
			MessageUtils.addGlobalInfoMessage(MessageUtils.getLocalizedMessage("mensagem_sucesso_operacao"));
		} catch (ServiceException e) {
			MessageUtils.addGlobalErrorMessage("Erro ao salvar: " + e.getMessage());
		}
		return PAGE_INCLUSAO;
	}

	private boolean validarInclusao() {
		boolean valida = true;
		if (FormatUtils.isEmptyOrNull(this.model.getNome())){
			MessageUtils.addGlobalErrorMessage("Favor informar Nome");
			valida = false;
		}
		if (FormatUtils.isEmptyOrNull(this.model.getEmail())){
			MessageUtils.addGlobalErrorMessage("Favor informar E-mail");
			valida = false;
		}
		if (FormatUtils.isEmptyOrNull(this.model.getEndereco())){
			MessageUtils.addGlobalErrorMessage("Favor informar Endereço");
			valida = false;
		}
		if (null == this.model.getDataNascimento()){
			MessageUtils.addGlobalErrorMessage("Favor informar Data de Nascimento");
			valida = false;
		} else {
			Calendar calendar = Calendar.getInstance();
			int anoAtual = calendar.get(Calendar.YEAR);
			calendar.setTime(this.model.getDataNascimento());
			int anoNasc = calendar.get(Calendar.YEAR);
			int diferenca = anoAtual - anoNasc;
			if (diferenca < 15 || diferenca > 100){
				MessageUtils.addGlobalErrorMessage("Favor informar uma Data de Nascimento válida.");
				valida = false;
			}
		}
		return valida;
	}
	
	@Override
	public String prepararAlteracao() {
		return PAGE_ALTERAR;
	}
	
	public String alterar(){
		try {
			if (validarAlteracao()){
				this.clienteService.inserir(this.model);
			}
			MessageUtils.addGlobalInfoMessage(MessageUtils.getLocalizedMessage("mensagem_sucesso_operacao"));
		} catch (ServiceException e) {
			MessageUtils.addGlobalErrorMessage("Erro ao salvar: " + e.getMessage());
		}
		return PAGE_HOME;
	}

	
	private boolean validarAlteracao() {
			boolean valida = true;
			if (FormatUtils.isEmptyOrNull(this.model.getEmail())){
				MessageUtils.addGlobalErrorMessage("Favor informar E-mail");
				valida = false;
			}
			if (FormatUtils.isEmptyOrNull(this.model.getEndereco())){
				MessageUtils.addGlobalErrorMessage("Favor informar Endereço");
				valida = false;
			}
			return valida;
		}

	@Override
	public String excluir(int id) {
		try{
			if (id > 0){
				this.clienteService.excluir(id);
				MessageUtils.addGlobalInfoMessage(MessageUtils.getLocalizedMessage("mensagem_sucesso_operacao"));
			}
		} catch (ServiceException e) {
			MessageUtils.addGlobalErrorMessage("Erro ao excluir: " + e.getMessage());
		}
		return PAGE_HOME;
	}
	
	public void selecionar(){
		FacesContext context = FacesContext.getCurrentInstance();
		Object obj = context.getApplication().evaluateExpressionGet(context, "#{cliente}", Cliente.class);
		if (obj != null && obj instanceof Cliente){
			this.clienteSelecioando = (Cliente) obj; 
		}
	}
	
	public String limpar(){
		this.model.limpar();
		return PAGE_HOME;
	}

	public Cliente getModel() {
		if (model == null){
			model = new Cliente();
		}
		return model;
	}

	public void setModel(Cliente model) {
		this.model = model;
	}

	public IClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(IClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
