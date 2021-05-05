package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.sc.senac.controller.PessoaController;
import br.sc.senac.exception.CamposInvalidosPessoa;
import br.sc.senac.exception.CpfIndisponivelException;
import br.sc.senac.model.vo.CategoriaPessoa;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.SexoPessoa;
import br.sc.senac.model.vo.Vacina;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroPessoa {

	private JFrame frmCadastroPessoa;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtNasc;
	private final ButtonGroup bGSexo = new ButtonGroup();
	
	private JComboBox cbCategoria = new JComboBox();
	private JRadioButton rbMasc = new JRadioButton("MASCULINO");
	private JRadioButton rbFem = new JRadioButton("FEMININO");
	
	private DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Pessoa pessoa;
	private Vacina vacina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPessoa window = new TelaCadastroPessoa(null);
					window.frmCadastroPessoa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroPessoa = new JFrame();
		frmCadastroPessoa.setTitle("Cadastro Pessoa");
		frmCadastroPessoa.setBounds(100, 100, 422, 440);
		frmCadastroPessoa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroPessoa.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOME");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 70, 88, 14);
		frmCadastroPessoa.getContentPane().add(lblNewLabel);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(10, 11, 88, 14);
		frmCadastroPessoa.getContentPane().add(lblCpf);
		
		JLabel lblSexo = new JLabel("SEXO");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSexo.setBounds(10, 250, 88, 14);
		frmCadastroPessoa.getContentPane().add(lblSexo);
		
		JLabel lblCategoria = new JLabel("CATEGORIA");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setBounds(10, 190, 88, 14);
		frmCadastroPessoa.getContentPane().add(lblCategoria);
		
		JLabel lblDataDeNasciento = new JLabel("DATA DE NASCIMENTO");
		lblDataDeNasciento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNasciento.setBounds(10, 132, 169, 14);
		frmCadastroPessoa.getContentPane().add(lblDataDeNasciento);
		
		try {
			MaskFormatter cpf = new MaskFormatter("###.###.###-##");
			txtCpf = new JFormattedTextField(cpf);
		} catch (ParseException exceptionMaskCpf) {
			txtCpf = new JTextField();
		}
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpf.setBounds(10, 36, 211, 20);
		frmCadastroPessoa.getContentPane().add(txtCpf);
		txtCpf.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setColumns(10);
		txtNome.setBounds(10, 95, 211, 20);
		frmCadastroPessoa.getContentPane().add(txtNome);
		

		bGSexo.add(rbMasc);
		rbMasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbMasc.setBounds(10, 271, 109, 23);
		frmCadastroPessoa.getContentPane().add(rbMasc);
		

		bGSexo.add(rbFem);
		rbFem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbFem.setBounds(121, 271, 109, 23);
		frmCadastroPessoa.getContentPane().add(rbFem);
		
		cbCategoria.setModel(new DefaultComboBoxModel(new String[] {"PESQUISADOR", "VOLUNTÁRIO", "PUBLICO GERAL"}));
		cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbCategoria.setBounds(10, 209, 169, 20);
		frmCadastroPessoa.getContentPane().add(cbCategoria);
		
		
		try {
			MaskFormatter data = new MaskFormatter("##/##/####");
			txtNasc = new JFormattedTextField(data);
		} catch (ParseException exceptionMaskDate) {
			txtNasc = new JTextField();
		}

		txtNasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNasc.setColumns(10);
		txtNasc.setBounds(10, 157, 211, 20);
		frmCadastroPessoa.getContentPane().add(txtNasc);
		
		JButton btnMenuPrinc = new JButton("MENU PRINCIPAL");
		btnMenuPrinc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaBoasVindas telaboasVindas = new TelaBoasVindas();
				telaboasVindas.getFrmBemVindo().setVisible(true);
				getFrmCadastroPessoa().dispose();
				
			}
		});
		btnMenuPrinc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMenuPrinc.setBounds(255, 11, 141, 23);
		frmCadastroPessoa.getContentPane().add(btnMenuPrinc);
		
		JButton btnCadastrarPesq = new JButton("CADASTRAR PESQUISADOR");
		btnCadastrarPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pessoa pesquisadorCadastro = preencherPessoa();
				
				if (pesquisadorCadastro != null) {
					
					try {
						PessoaController pessoaController = new PessoaController();
						JOptionPane.showMessageDialog(null, pessoaController.cadastrarPessoaController(pesquisadorCadastro), "AVISO", JOptionPane.INFORMATION_MESSAGE);
						
						Vacina novaVacina = vacina;
						novaVacina.setPesquisadorResponsavel(pesquisadorCadastro);
						TelaCadastroVacina telaCadastroVacina = new TelaCadastroVacina(novaVacina);
						
						
						
						telaCadastroVacina.getFrmCadastroVacina().setVisible(true);
						
						getFrmCadastroPessoa().dispose();
						
					} catch (CpfIndisponivelException exceptionCpf) {
						JOptionPane.showMessageDialog(null, exceptionCpf.getMessage(), "AVISO", JOptionPane.WARNING_MESSAGE);
					}  catch (CamposInvalidosPessoa exceptionCadastro) {
						JOptionPane.showMessageDialog(null, exceptionCadastro.getMessage(), "AVISO", JOptionPane.WARNING_MESSAGE);
					}
					
				}
				
				
			}
		});
		btnCadastrarPesq.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrarPesq.setBounds(85, 340, 239, 36);

		
		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa pessoaAtualizacao = preencherPessoa();
				
				if (pessoaAtualizacao != null) {
					

					try {
						PessoaController pessoaController = new PessoaController();
						JOptionPane.showMessageDialog(null, pessoaController.atualizarPessoaController(pessoaAtualizacao), "AVISO", JOptionPane.INFORMATION_MESSAGE);
						
						TelaBoasVindas telaboasVindas = new TelaBoasVindas();
						telaboasVindas.getFrmBemVindo().setVisible(true);
						getFrmCadastroPessoa().dispose();
						
					} catch (CpfIndisponivelException exceptionAtualizar) {
						JOptionPane.showMessageDialog(null, exceptionAtualizar.getMessage(), "AVISO", JOptionPane.WARNING_MESSAGE);	
					}
					
				}
				
	
				
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtualizar.setBounds(130, 332, 169, 39);

		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pessoa pessoaCadastro = preencherPessoa();
				
				if (pessoaCadastro != null) {
					
					try {
						PessoaController pessoaController = new PessoaController();
						JOptionPane.showMessageDialog(null, pessoaController.cadastrarPessoaController(pessoaCadastro), "AVISO", JOptionPane.INFORMATION_MESSAGE);
						
						TelaBoasVindas telaboasVindas = new TelaBoasVindas();
						telaboasVindas.getFrmBemVindo().setVisible(true);
						getFrmCadastroPessoa().dispose();
						
					} catch (CpfIndisponivelException exceptionCpf) {
						JOptionPane.showMessageDialog(null, exceptionCpf.getMessage(), "AVISO", JOptionPane.WARNING_MESSAGE);
					} catch (CamposInvalidosPessoa exceptionCadastro) {
						JOptionPane.showMessageDialog(null, exceptionCadastro.getMessage(), "AVISO", JOptionPane.WARNING_MESSAGE);
					}
					
				}
				


				
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrar.setBounds(86, 331, 168, 33);

		
		
		if(getPessoa() != null) {
			
			if(getPessoa().getIdPessoa() > 0) {
				// ATUALIZAR PESSOA
				frmCadastroPessoa.setTitle("Atualizar Pessoa");
				txtCpf.setText(getPessoa().getCpf());
				txtNasc.setText(getPessoa().getDataNascimento().format(formatadorData));
				txtNome.setText(getPessoa().getNome());
				
				switch (getPessoa().getCategoria()) {
					case PESQUISADOR: {
						cbCategoria.setSelectedIndex(0);
						break;
					}
					case PUBLICO_GERAL: {
						cbCategoria.setSelectedIndex(2);
						break;
					}
					case VOLUNTARIO: {
						cbCategoria.setSelectedIndex(1);
						break;
					}	
				}
				
				if (getPessoa().getSexo() == SexoPessoa.F) {
					rbFem.setSelected(true);
				} else {
					rbMasc.setSelected(true);
				}//FIM SWITCH

				
				frmCadastroPessoa.getContentPane().add(btnAtualizar);
				
		} else if (getPessoa().getCpf() != null) {
			// NOVO PESQUISADOR
			frmCadastroPessoa.setTitle("Cadastrar Pesquisador");
			
			txtCpf.setText(getPessoa().getCpf());
			cbCategoria.setSelectedIndex(0);
			cbCategoria.setEnabled(false);
			
			frmCadastroPessoa.getContentPane().add(btnCadastrarPesq);

		}
		} else {
			// CADASTRO GENERICO
			
			frmCadastroPessoa.getContentPane().add(btnCadastrar);
		}// FIM CONDICIONAIS
	
	
	}
	
	private Pessoa preencherPessoa() {
		
		
		Pessoa novaPessoa = new Pessoa();
		
		try {
			novaPessoa.setDataNascimento(LocalDate.parse(txtNasc.getText(), formatadorData));
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(null, "Informe a data corretamente", "AVISO", JOptionPane.WARNING_MESSAGE);
			
			return null;
		}
		
		novaPessoa.setNome(txtNome.getText().trim());
		
		String cpfUnformatted = txtCpf.getText().replace(".", "");
		cpfUnformatted = cpfUnformatted.replace("-", "");
		novaPessoa.setCpf(cpfUnformatted);
		
		CategoriaPessoa[] categorias = {CategoriaPessoa.PESQUISADOR ,CategoriaPessoa.VOLUNTARIO ,CategoriaPessoa.PUBLICO_GERAL};
		novaPessoa.setCategoria(categorias[cbCategoria.getSelectedIndex()]);
		
		
		
		
		if (bGSexo.getSelection() == rbFem.getModel()) {
			novaPessoa.setSexo(SexoPessoa.F);
		} else if (bGSexo.getSelection() == rbMasc.getModel()) {
			novaPessoa.setSexo(SexoPessoa.M);
		}
		
		return novaPessoa;
		
	}

	
	public JFrame getFrmCadastroPessoa() {
		return this.frmCadastroPessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}
	
	
}
