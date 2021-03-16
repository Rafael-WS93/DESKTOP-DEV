package br.sc.senac.model.vo;

import java.time.LocalDate;
import java.util.List;

public class Pessoa {
	
	private int idPessoa;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private SexoPessoa sexo;
	private CategoriaPessoa categoria;
	
	public Pessoa(int idPessoa, String nome, String cpf, LocalDate dataNascimento, SexoPessoa sexo, CategoriaPessoa categoria) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		
		this.categoria = categoria;
		this.setIdPessoa(idPessoa);
	}

	public Pessoa() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public SexoPessoa getSexo() {
		return sexo;
	}

	public void setSexo(SexoPessoa sexo) {
		this.sexo = sexo;
	}

	public CategoriaPessoa getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaPessoa categoria) {
		this.categoria = categoria;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	

}
