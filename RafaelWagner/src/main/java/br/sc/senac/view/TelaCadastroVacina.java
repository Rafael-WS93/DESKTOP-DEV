package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

import br.sc.senac.controller.PessoaController;
import br.sc.senac.controller.VacinaController;
import br.sc.senac.exception.CampoInvalidoVacina;
import br.sc.senac.exception.NomeVacinaIndisponivelException;
import br.sc.senac.model.vo.CategoriaPessoa;
import br.sc.senac.model.vo.EstagioVacina;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;

import java.awt.SystemColor;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroVacina {

	private JFrame frmCadastroVacina;

	private JTextField txtNomeVacina;
	private JTextField txtPaisVacina;
	private JTextField txtDataVacina;
	private JTextField txtCpfPesq;
	
	private Vacina vacina;
	
	JComboBox cbEstagioPesq = new JComboBox();
	
	private DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVacina window = new TelaCadastroVacina(null);
					window.frmCadastroVacina.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaCadastroVacina(Vacina vacina) {
		this.vacina = vacina;
		initialize();
	}


	private void initialize() {
		frmCadastroVacina = new JFrame();
		frmCadastroVacina.setTitle("Cadastro Vacina");
		frmCadastroVacina.setBounds(100, 100, 385, 641);
		frmCadastroVacina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroVacina.getContentPane().setLayout(null);
		

		
		//PESQUISADOR
		JPanel pnlPesquisador = new JPanel();
		pnlPesquisador.setBackground(SystemColor.menu);
		pnlPesquisador.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlPesquisador.setBounds(10, 38, 346, 199);
		frmCadastroVacina.getContentPane().add(pnlPesquisador);
		pnlPesquisador.setLayout(null);
		
		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO");
		lblDataDeNascimento.setBounds(10, 141, 198, 14);
		pnlPesquisador.add(lblDataDeNascimento);
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 36, 198, 14);
		pnlPesquisador.add(lblCpf);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPesquisadorResponsvel = new JLabel("PESQUISADOR RESPONSÁVEL");
		lblPesquisadorResponsvel.setBounds(10, 11, 231, 14);
		pnlPesquisador.add(lblPesquisadorResponsvel);
		lblPesquisadorResponsvel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		try {
			MaskFormatter cpf = new MaskFormatter("###.###.###-##");
			txtCpfPesq = new JFormattedTextField(cpf);
		} catch (ParseException e1) {
			txtCpfPesq = new JTextField();
		}

		txtCpfPesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpfPesq.setColumns(10);
		txtCpfPesq.setBounds(10, 61, 185, 20);
		pnlPesquisador.add(txtCpfPesq);
		
		JLabel lblNomePesq = new JLabel("NOME");
		lblNomePesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomePesq.setBounds(10, 92, 64, 14);
		pnlPesquisador.add(lblNomePesq);
		
		final JLabel lblNomeConsulta  = new JLabel("-----");
		lblNomeConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeConsulta.setBounds(20, 117, 316, 14);
		pnlPesquisador.add(lblNomeConsulta);
		
		final JLabel lblDtNascConsulta = new JLabel("-----");
		lblDtNascConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDtNascConsulta.setBounds(20, 166, 185, 14);
		pnlPesquisador.add(lblDtNascConsulta);
		
		JButton btnBuscarPesq = new JButton("Buscar");
		btnBuscarPesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pessoa consultaPesquisador = new Pessoa();
				consultaPesquisador.setCategoria(CategoriaPessoa.PESQUISADOR);
				
				String cpfUnformatted = txtCpfPesq.getText().replace(".", ""); 
				cpfUnformatted = cpfUnformatted.replace("-", "");
				consultaPesquisador.setCpf(cpfUnformatted);
				
				PessoaController pessoaController = new PessoaController();
				
				consultaPesquisador = pessoaController.consultarPessoaPorCPFController(consultaPesquisador.getCpf());
				
				if (consultaPesquisador != null) {
					lblNomeConsulta.setText(consultaPesquisador.getNome());
					lblDtNascConsulta.setText(consultaPesquisador.getDataNascimento().format(formatadorData));
					vacina.setPesquisadorResponsavel(consultaPesquisador);
				} else {
					
					if (JOptionPane.showConfirmDialog(null, "Pesquisador não encontrado, deseja cadastrar?","aviso"
							,JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						
						consultaPesquisador = new Pessoa();
						consultaPesquisador.setCpf(cpfUnformatted);
						

						TelaCadastroPessoa cadastroPesquisador = new TelaCadastroPessoa(consultaPesquisador);
						
						if(vacina.getIdVacina() > 0) {
							cadastroPesquisador.setVacina(vacina);
						}
						
						cadastroPesquisador.getFrmCadastroPessoa().setVisible(true);
						
						getFrmCadastroVacina().dispose();
					}
				
				
				}

			}
		});
		btnBuscarPesq.setBounds(247, 162, 89, 23);
		pnlPesquisador.add(btnBuscarPesq);
		
		
		// VACINA
		
		JPanel pnlVacina = new JPanel();
		pnlVacina.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlVacina.setBackground(SystemColor.menu);
		pnlVacina.setBounds(10, 248, 346, 343);
		frmCadastroVacina.getContentPane().add(pnlVacina);
		pnlVacina.setLayout(null);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(10, 36, 64, 14);
		pnlVacina.add(lblNome);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtNomeVacina = new JTextField();
		txtNomeVacina.setBounds(10, 59, 288, 20);
		pnlVacina.add(txtNomeVacina);
		txtNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeVacina.setColumns(10);
		
		JLabel lblPaisDeOrigem = new JLabel("PAÍS DE ORIGEM");
		lblPaisDeOrigem.setBounds(10, 103, 198, 14);
		pnlVacina.add(lblPaisDeOrigem);
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPaisVacina = new JTextField();
		txtPaisVacina.setBounds(10, 128, 288, 20);
		pnlVacina.add(txtPaisVacina);
		txtPaisVacina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPaisVacina.setColumns(10);
		
		JLabel lblDataDeIncio = new JLabel("DATA DE INÍCIO DA PESQUISA");
		lblDataDeIncio.setBounds(10, 171, 198, 14);
		pnlVacina.add(lblDataDeIncio);
		lblDataDeIncio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		try {
			MaskFormatter data = new MaskFormatter("##/##/####");
			txtDataVacina = new JFormattedTextField(data);
		} catch (ParseException e) {
			txtDataVacina = new JTextField();
		}
		txtDataVacina.setBounds(10, 194, 288, 20);
		pnlVacina.add(txtDataVacina);
		txtDataVacina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDataVacina.setColumns(10);
		
		JLabel lblEstgioDaPesquisa = new JLabel("ESTÁGIO DA PESQUISA");
		lblEstgioDaPesquisa.setBounds(10, 238, 152, 14);
		pnlVacina.add(lblEstgioDaPesquisa);
		lblEstgioDaPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		cbEstagioPesq.setModel(new DefaultComboBoxModel(new String[] {"INICIAL", "TESTE", "APLICAÇÃO EM MASSA"}));
		cbEstagioPesq.setBounds(10, 263, 198, 20);
		pnlVacina.add(cbEstagioPesq);
		cbEstagioPesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblVacina = new JLabel("VACINA");
		lblVacina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVacina.setBounds(10, 11, 231, 14);
		pnlVacina.add(lblVacina);
		
		JButton btnMenuPrinc = new JButton("MENU PRINCIPAL");
		btnMenuPrinc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
				telaBoasVindas.getFrmBemVindo().setVisible(true);
				getFrmCadastroVacina().dispose();
				
			}
		});
		btnMenuPrinc.setBounds(215, 11, 141, 23);
		frmCadastroVacina.getContentPane().add(btnMenuPrinc);
		btnMenuPrinc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Vacina vacinaAtualizar = preencherVacina();
				
				if (vacinaAtualizar != null) {
					vacinaAtualizar.setIdVacina(getVacina().getIdVacina());
					

					
					try {
						VacinaController vacinaController = new VacinaController();
						JOptionPane.showMessageDialog(null, vacinaController.atualizarvacinaController(vacinaAtualizar), "AVISO", JOptionPane.INFORMATION_MESSAGE);
						
						TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
						telaBoasVindas.getFrmBemVindo().setVisible(true);
						getFrmCadastroVacina().dispose();
						
					} catch (CampoInvalidoVacina exceptionCadastro) {
						JOptionPane.showMessageDialog(null, exceptionCadastro.getMessage(), "AVISO",JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtualizar.setBounds(100, 303, 143, 29);

		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Vacina cadastroVacina = preencherVacina();

				if (cadastroVacina != null) {
					
					try {
						VacinaController vacinaController = new VacinaController();
						JOptionPane.showMessageDialog(null, vacinaController.cadastrarVacinaController(cadastroVacina), "AVISO", JOptionPane.INFORMATION_MESSAGE);
						
						TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
						telaBoasVindas.getFrmBemVindo().setVisible(true);
						getFrmCadastroVacina().dispose();
						
					} catch (NomeVacinaIndisponivelException exceptionVacina) {
						JOptionPane.showMessageDialog(null, exceptionVacina.getMessage(), "AVISO",JOptionPane.WARNING_MESSAGE);
					} catch (CampoInvalidoVacina exceptionCadastro) {
						JOptionPane.showMessageDialog(null, exceptionCadastro.getMessage(), "AVISO",JOptionPane.WARNING_MESSAGE);
					} 
					
				}

			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrar.setBounds(102, 303, 139, 29);

		
		if (vacina == null) {
			vacina = new Vacina();
			pnlVacina.add(btnCadastrar);
			
		} else if(vacina.getIdVacina() > 0){
			

			txtDataVacina.setText(getVacina().getDataInicioPesquisa().format(formatadorData));
			txtNomeVacina.setText(getVacina().getNome());
			txtPaisVacina.setText(getVacina().getNomePaisOrigem());
			
			txtCpfPesq.setText(getVacina().getPesquisadorResponsavel().getCpf());
			lblNomeConsulta.setText(getVacina().getPesquisadorResponsavel().getNome());
			lblDtNascConsulta.setText(getVacina().getPesquisadorResponsavel().getDataNascimento().format(formatadorData));
			
			switch (getVacina().getEstagioVacina()) {
			case INICIAL: {
				cbEstagioPesq.setSelectedIndex(0);
				break;
			}
			case TESTE: {
				cbEstagioPesq.setSelectedIndex(1);
				break;
			}
			case APLICACAO_EM_MASSA: {
				cbEstagioPesq.setSelectedIndex(2);
				break;
			}
			}
			
			frmCadastroVacina.setTitle("Atualizar Vacina");
			
			pnlVacina.add(btnAtualizar);
			
		} else if (vacina.getPesquisadorResponsavel() != null){
			
			txtCpfPesq.setText(getVacina().getPesquisadorResponsavel().getCpf());
			lblNomeConsulta.setText(getVacina().getPesquisadorResponsavel().getNome());
			lblDtNascConsulta.setText(getVacina().getPesquisadorResponsavel().getDataNascimento().format(formatadorData));
			
			pnlVacina.add(btnCadastrar);
			
		} else {
			
			pnlVacina.add(btnCadastrar);
			
		}
		

	}
	
	private Vacina preencherVacina() {
		Vacina novaVacina = new Vacina();
		

		
		try {
			novaVacina.setDataInicioPesquisa(LocalDate.parse(txtDataVacina.getText(), formatadorData));
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(null, "Informe a data corretamente", "AVISO", JOptionPane.WARNING_MESSAGE);
			
			return null;
		}
		
		EstagioVacina[] estagios = {EstagioVacina.INICIAL ,EstagioVacina.TESTE ,EstagioVacina.APLICACAO_EM_MASSA};
		
		novaVacina.setNome(txtNomeVacina.getText().trim());
		novaVacina.setNomePaisOrigem(txtPaisVacina.getText().trim());
		novaVacina.setPesquisadorResponsavel(vacina.getPesquisadorResponsavel());

		
		
		novaVacina.setEstagioVacina(estagios[cbEstagioPesq.getSelectedIndex()]);
		
		
		return novaVacina;
	}
	
	


	public JFrame getFrmCadastroVacina() {
		return frmCadastroVacina;
	}


	public void setFrmCadastroVacina(JFrame frmCadastroVacina) {
		this.frmCadastroVacina = frmCadastroVacina;
	}


	public Vacina getVacina() {
		return vacina;
	}


	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}
	


}
