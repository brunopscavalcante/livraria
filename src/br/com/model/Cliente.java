package br.com.model;

import java.util.Date;

import javax.faces.bean.ViewScoped;

@ViewScoped
public class Cliente {
	
	private int id;
	
	private String nome;
	
	private String email;
	
	private String endereco;
	
	private Date dataNascimento;
	
	private boolean selecionado;
	
	public Cliente(int id, String nome, String email, String endereco, Date dataNascimento, boolean selecionado) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.selecionado = selecionado;
	}
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public void limpar(){
		this.nome = "";
		this.email = "";
		this.endereco = "";
		this.dataNascimento = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco
				+ ", dataNascimento=" + dataNascimento + "]";
	}

}
