package br.sc.senac.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JOptionPane;

import br.sc.senac.controller.PessoaController;
import br.sc.senac.controller.VacinaController;
import br.sc.senac.exception.CpfIndisponivelException;
import br.sc.senac.exception.NomeVacinaIndisponivelException;
import br.sc.senac.exception.PessoaNaoPesquisador;
import br.sc.senac.model.vo.CategoriaPessoa;
import br.sc.senac.model.vo.EstagioVacina;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.SexoPessoa;
import br.sc.senac.model.vo.Vacina;

public class CadastroVacina {
	
	DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	public void menuVacina() {
		String message = "1- Cadastrar Vacina\n"
						+ "2-Consultar Todas Vacinas\n"
						+ "3-Excluir Vacina\n"
						+ "4-Sair\n"
						+ "Escolha a opção:";

		Integer[] opcoes = {1 ,2 ,3, 4};
		
		Integer opcao = (Integer) JOptionPane.showInputDialog(null, message, "Menu Vacina",  JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
		
		switch(opcao) {
			case 1:{
				this.menuCadastrarVacina();
				this.menuVacina();
				break; 
			}
			case 2: {
				this.menuConsultarTodasVacinas();
				this.menuVacina();
				break;
			}
			case 3: {
				this.menuExcluirVacina();
				this.menuVacina();
				break;
			}
			case 4: {
				JOptionPane.showMessageDialog(null, "Fechando", "Bye", JOptionPane.PLAIN_MESSAGE);
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "Opção invalida!");
				this.menuVacina();
			}
		}
		
	}



	private void menuCadastrarVacina() {
		String titulo = "Cadastro Vacina";
		
		Vacina vacina = new Vacina();
		
		VacinaController controller = new VacinaController();
		
		vacina = this.menuConsultarPesquisador(vacina, controller);
		
		do {
			try {
				vacina.setDataInicioPesquisa(LocalDate.parse(
						JOptionPane.showInputDialog(null 
							, "Digite a data de inicio da pesquisa (DD/MM/AAAA):"
							, titulo
							, JOptionPane.QUESTION_MESSAGE)
						, formatadorData));
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(null, "Informe a data no formato especificado (dia/mes/ano):\nex:02/12/2021");
			}
			
		} while (vacina.getDataInicioPesquisa() == null);
		

		vacina.setNome(JOptionPane.showInputDialog(null ,"Digite o nome da vacina: " ,titulo ,JOptionPane.QUESTION_MESSAGE));
		while (vacina.getNome().equals("")) {
			JOptionPane.showMessageDialog(null, "nome digitado incorretamente\n digite um nome", "Aviso", JOptionPane.WARNING_MESSAGE);
			vacina.setNome(JOptionPane.showInputDialog(null ,"Digite o nome da vacina: " ,titulo ,JOptionPane.QUESTION_MESSAGE));
		}
		
		vacina.setNomePaisOrigem(JOptionPane.showInputDialog(null, "Digite, por extenso o país de Origem: " ,titulo ,JOptionPane.QUESTION_MESSAGE));
		while (vacina.getNomePaisOrigem().equals("")) {
			JOptionPane.showMessageDialog(null, "nome digitado incorretamente\n digite um nome", "Aviso", JOptionPane.WARNING_MESSAGE);
			vacina.setNomePaisOrigem(JOptionPane.showInputDialog(null, "Digite, por extenso o país de Origem: " ,titulo ,JOptionPane.QUESTION_MESSAGE));
		}
		
		EstagioVacina[] estagios = {EstagioVacina.INICIAL ,EstagioVacina.TESTE ,EstagioVacina.APLICACAO_EM_MASSA};
		vacina.setEstagioVacina((EstagioVacina) JOptionPane.showInputDialog(null, "Selecione o estágio da vacina:\n", titulo,  JOptionPane.QUESTION_MESSAGE, null, estagios, estagios[0]));
		
		
		try {
			JOptionPane.showMessageDialog(null, controller.cadastrarVacinaController(vacina));
		} catch (NomeVacinaIndisponivelException e) {
			e.getMessage();
		}



	}

	private void menuConsultarTodasVacinas() {
		String message = "";
		VacinaController vacinaController = new VacinaController();
		
		List<Vacina> lista = vacinaController.consultarTodasVacinas();
		
		if(!lista.isEmpty()) {
			for(Vacina vacina : lista) {
				message += vacina.toString();
			}
			
			JOptionPane.showMessageDialog(null, message, "Lista de vacinas", JOptionPane.INFORMATION_MESSAGE, null);
		}
		
		
	}

	private void menuExcluirVacina() {
		Vacina vacina = new Vacina();
		String titulo = "Excluir Vacina";
		
		vacina.setNome(JOptionPane.showInputDialog(null ,"Digite o nome da vacina: " ,titulo ,JOptionPane.QUESTION_MESSAGE));
		while (vacina.getNome().equals("")) {
			JOptionPane.showMessageDialog(null, "nome digitado incorretamente\n digite um nome", "Aviso", JOptionPane.WARNING_MESSAGE);
			vacina.setNome(JOptionPane.showInputDialog(null ,"Digite o nome da vacina: " ,titulo ,JOptionPane.QUESTION_MESSAGE));
		}
		
		vacina.setNomePaisOrigem(JOptionPane.showInputDialog(null, "Digite, por extenso o país de Origem: " ,titulo ,JOptionPane.QUESTION_MESSAGE));
		while (vacina.getNomePaisOrigem().equals("")) {
			JOptionPane.showMessageDialog(null, "nome digitado incorretamente\n digite um nome", "Aviso", JOptionPane.WARNING_MESSAGE);
			vacina.setNomePaisOrigem(JOptionPane.showInputDialog(null, "Digite, por extenso o país de Origem: " ,titulo ,JOptionPane.QUESTION_MESSAGE));
		}
		
		VacinaController vacinaController = new VacinaController();
		
		JOptionPane.showMessageDialog(null, vacinaController.excluirVacina(vacina), titulo, JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	
	private Vacina menuConsultarPesquisador(Vacina vacina, VacinaController controller) {
		String title = "Cadastro Pesquisador";
		Pessoa pesquisador = new Pessoa();
		
		pesquisador.setCategoria(CategoriaPessoa.PESQUISADOR);
		
		
		
		pesquisador.setCpf(JOptionPane.showInputDialog(null, "Digite o cpf do pesquisador (somente numeros): ", title ,JOptionPane.QUESTION_MESSAGE));
		while (!pesquisador.getCpf().matches("^[0-9]{11}+$")) {
			JOptionPane.showMessageDialog(null, "Aviso: preencha somente com 11 (onze) números: ", "Aviso" ,JOptionPane.WARNING_MESSAGE);
			pesquisador.setCpf(JOptionPane.showInputDialog(null, "Digite o cpf do pesquisador (somente numeros): ", title ,JOptionPane.QUESTION_MESSAGE));
		}
	
		try {
			pesquisador = controller.consultarPesquisadorController(pesquisador);
		} catch (PessoaNaoPesquisador e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		if(pesquisador.getIdPessoa() == 0 ) {
			
			if (JOptionPane.showConfirmDialog(null 
					,"Pesquisador não cadastrado, por favor informe os dados a seguir." 
					,title ,JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						
				this.menuCadastrarNovoPesquisador(vacina, controller);
					
			} else {
				this.menuVacina();
			}
			
			
			
		} else {
			if (JOptionPane.showConfirmDialog(null 
					,"Pesquisador encontrado.\n"+ pesquisador.getNome() + "\nNasc: "+ pesquisador.getDataNascimento().format(formatadorData) 
					,title ,JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						vacina.setPesquisadorResponsavel(pesquisador);
			} else {
				this.menuVacina();
			}


		}
	

		return vacina;
	}
	
	private Vacina menuCadastrarNovoPesquisador(Vacina vacina, VacinaController controller) {
		String title = "Cadastro novo pesquisador";
		Pessoa pesquisador = new Pessoa();

		pesquisador.setNome(JOptionPane.showInputDialog(null ,"Digite o nome do pesquisador Responsável: ", title ,JOptionPane.QUESTION_MESSAGE));
		while(pesquisador.getNome().equals("")) {
			JOptionPane.showMessageDialog(null, "nome digitado incorretamente\n digite um nome", "Aviso", JOptionPane.WARNING_MESSAGE);
			pesquisador.setNome(JOptionPane.showInputDialog(null ,"Digite o nome do pesquisador Responsável: ", title ,JOptionPane.QUESTION_MESSAGE));
		}
		
		do {
			try {					
				pesquisador.setDataNascimento(LocalDate.parse(
						JOptionPane.showInputDialog(null 
							, "Digite a data de nascimento (DD/MM/AAAA):"
							, title
							, JOptionPane.QUESTION_MESSAGE)
						, formatadorData));
				
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(null, "Informe a data no formato especificado (dia/mes/ano):\nex:02/12/2021", "Aviso",JOptionPane.WARNING_MESSAGE);
			}
		} while (pesquisador.getDataNascimento() == null);
		
		
		SexoPessoa[] sexoOpcoes = {SexoPessoa.F, SexoPessoa.M};		
		pesquisador.setSexo( (SexoPessoa) JOptionPane.showInputDialog(null, "Selecione Sexo:\n", title,  JOptionPane.QUESTION_MESSAGE, null, sexoOpcoes, sexoOpcoes[0]));
		
		PessoaController pessoaController = new PessoaController();
		
		try {
			pessoaController.cadastrarPessoaController(pesquisador);
			
			JOptionPane.showMessageDialog(null, "Pesquisador cadastrado com sucesso!", title, JOptionPane.PLAIN_MESSAGE);
			
			vacina.setPesquisadorResponsavel(pessoaController.consultarPessoaPorCPFController(pesquisador.getCpf()));
			
		} catch (CpfIndisponivelException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			
			this.menuConsultarPesquisador(vacina, controller);
		}
		
		return vacina;
		
	}


	

}
