package br.com.bean;

import java.util.List;

import javax.faces.component.html.HtmlDataTable;

public abstract class BaseBean {
	
	private HtmlDataTable dataTable;
	
	public abstract String prepararConsulta();
		
	public abstract String prepararInclusao();
	
	public abstract String prepararAlteracao();
	
	public abstract String consultar();
	
	public abstract String inserir();
	
	public abstract String alterar();
	
	public abstract String excluir(int id);
	
	protected boolean isEmpty(Object obj) {
		if (null == obj){
			return true;
		} else {
			if (obj instanceof String){
				if(((String) obj).length() == 0){
					return true;
				}
			} else if(obj instanceof List){
				if (((List) obj).isEmpty()){
					return true;
				}
			}
		}
		return false;
	}

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
	
}
