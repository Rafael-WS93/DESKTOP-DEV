package br.sc.senac.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
						+ "2-Consultar Vacina\n"
						+ "3-Excluir Vacina\n"
						//+ "4-Atualizar Vacina\n"
						+ "Digite a opção:";
		
		
		int opcao = Integer.parseInt(JOptionPane.showInputDialog(message));
		
		switch(opcao) {
			case 1:{
				this.menuCadastrarVacina();
				break;
			}
			case 2: {
				//this.menuConsultarVacina();
				break;
			}
			case 3: {
				//this.menuExcluirVacina();
				break;
			}
			case 4: {
				//this.menuAtualizarVacina();
				break;
			}
			default: {
				JOptionPane.showInternalMessageDialog(null, "Opção invalida!");
				this.menuVacina();
			}
		}
		
	}

	private void menuCadastrarVacina() {
		String message = "";
		
		Vacina vacina = new Vacina();
		
		VacinaController controller = new VacinaController();
		
		vacina = this.cadastrarPesquisador(vacina, controller);
		
		do {
			try {
				vacina.setDataInicioPesquisa(LocalDate.parse(
						JOptionPane.showInputDialog("Digite a data de inicio da pesquisa (formato dia/mes/ano - DD/MM/AAAA):")
						, formatadorData));
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(null, "Informe a data no formato especificado (dia/mes/ano):\nex:02/12/2021");
			}
			
		} while (vacina.getDataInicioPesquisa() == null);
		

		
		vacina.setNome(JOptionPane.showInputDialog("Digite o nome da vacina: "));
		
		vacina.setNomePaisOrigem(JOptionPane.showInputDialog("Digite, por extenso o país de Origem: "));
		
		do {
			int estagioVacina = 0;
			try {
				estagioVacina = Integer.parseInt(JOptionPane.showInputDialog("Digite o estágio da vacina:\n"
						+ "1 - Inicial\n"
						+ "2- Teste\n"
						+ "3- Aplicação em Massa\n"));
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Digite um valor numerico");
			}

			
			switch(estagioVacina) {
			case 1: {
				vacina.setEstagioVacina(EstagioVacina.INICIAL);
				break;
			}
			case 2: {
				vacina.setEstagioVacina(EstagioVacina.TESTE);
				break;
			}
			case 3: {
				vacina.setEstagioVacina(EstagioVacina.APLICACAO_EM_MASSA);
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "Valor inválido, por favor insira um número correto:");
				break;
			}
			}
					
		} while (vacina.getEstagioVacina() == null);
		
		try {
			JOptionPane.showMessageDialog(null, controller.cadastrarVacinaController(vacina));
		} catch (NomeVacinaIndisponivelException e) {
			e.getMessage();
		}



	}

	private Vacina cadastrarPesquisador(Vacina vacina, VacinaController controller) {
		
		Pessoa pesquisador = new Pessoa();
		
		pesquisador.setCategoria(CategoriaPessoa.PESQUISADOR);
		
		
		pesquisador.setCpf(JOptionPane.showInputDialog("Digite o cpf do pesquisador"));
		while (pesquisador.getCpf().length() != 11) {
			pesquisador.setCpf(JOptionPane.showInputDialog("Digite o cpf do pesquisador\n deve conter 11 caracteres"));
		}
	
		try {
			pesquisador = controller.consultarPesquisadorController(pesquisador);
		} catch (PessoaNaoPesquisador e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		if(pesquisador.getIdPessoa() == 0 ) {
			JOptionPane.showMessageDialog(null, "Pesquisador não cadastrado, por favor informe os dados a seguir.");
			
			pesquisador.setNome(JOptionPane.showInputDialog("Digite o nome do pesquisador Responsável: "));
			while(pesquisador.getNome().equals("")) {
				JOptionPane.showMessageDialog(null, "Informe o nome corretamente.");
				pesquisador.setNome(JOptionPane.showInputDialog("Digite o nome do pesquisador Responsável: "));
			}
			
			do {
				try {
					pesquisador.setDataNascimento(LocalDate.parse(
							JOptionPane.showInputDialog("Digite a data de nascimento (formato dia/mes/ano): ")
							, formatadorData));
					
				} catch (DateTimeParseException e) {
					JOptionPane.showMessageDialog(null, "Informe a data no formato especificado (dia/mes/ano):\nex:02/12/2021");
				}
			} while (pesquisador.getDataNascimento() == null);
			
			

			
			char sexo = JOptionPane.showInputDialog("Digite o Sexo (M = masculino ou F = feminino):").charAt(0);
			
			if(sexo == 'F') {
				pesquisador.setSexo(SexoPessoa.F);
			} else {
				pesquisador.setSexo(SexoPessoa.M);
			}
			
			PessoaController pessoaController = new PessoaController();
			
			try {
				pessoaController.cadastrarPessoaController(pesquisador);
				
				vacina.setPesquisadorResponsavel(pessoaController.consultarPessoaPorCPFController(pesquisador.getCpf()));
				
			} catch (CpfIndisponivelException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				
				this.cadastrarPesquisador(vacina, controller);
			}
			
		} else vacina.setPesquisadorResponsavel(pesquisador);

		

		return vacina;
	}
	

}
