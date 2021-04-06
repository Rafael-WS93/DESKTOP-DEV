package br.sc.senac.model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Pessoa {
	
	private int idPessoa;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private SexoPessoa sexo;
	private CategoriaPessoa categoria;
	private List<AplicacaoVacina> vacinacoes;
	
	private DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Pessoa(int idPessoa, String nome, String cpf, LocalDate dataNascimento, SexoPessoa sexo,
			CategoriaPessoa categoria, List<AplicacaoVacina> vacinacoes) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.categoria = categoria;
		this.vacinacoes = vacinacoes;
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

	public List<AplicacaoVacina> getVacinacoes() {
		return vacinacoes;
	}

	public void setVacinacoes(List<AplicacaoVacina> vacinacoes) {
		this.vacinacoes = vacinacoes;
	}
	
	

	

}
