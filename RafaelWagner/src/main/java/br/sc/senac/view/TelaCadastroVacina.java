package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

import br.sc.senac.controller.PessoaController;
import br.sc.senac.controller.VacinaController;
import br.sc.senac.model.vo.CategoriaPessoa;
import br.sc.senac.model.vo.EstagioVacina;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.SexoPessoa;
import br.sc.senac.model.vo.Vacina;

import java.awt.SystemColor;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
	
	private DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVacina window = new TelaCadastroVacina();
					window.frmCadastroVacina.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaCadastroVacina() {
		initialize();
	}


	private void initialize() {
		frmCadastroVacina = new JFrame();
		frmCadastroVacina.setTitle("Cadastro Vacina");
		frmCadastroVacina.setBounds(100, 100, 395, 650);
		frmCadastroVacina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroVacina.getContentPane().setLayout(null);
		
		//PESQUISADOR
		JPanel pnlPesquisador = new JPanel();
		pnlPesquisador.setBackground(SystemColor.menu);
		pnlPesquisador.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlPesquisador.setBounds(10, 11, 346, 199);
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
		txtCpfPesq.setBounds(10, 61, 288, 20);
		pnlPesquisador.add(txtCpfPesq);
		
		JLabel lblNomePesq = new JLabel("NOME");
		lblNomePesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomePesq.setBounds(10, 92, 64, 14);
		pnlPesquisador.add(lblNomePesq);
		
		final JLabel lblNomeConsulta  = new JLabel("-----");
		lblNomeConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeConsulta.setBounds(10, 117, 326, 14);
		pnlPesquisador.add(lblNomeConsulta);
		
		JLabel lblDtNascConsulta = new JLabel("-----");
		lblDtNascConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDtNascConsulta.setBounds(20, 166, 185, 14);
		pnlPesquisador.add(lblDtNascConsulta);
		
		JButton btnBuscarPesq = new JButton("Buscar");
		btnBuscarPesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pessoa pesquisador = new Pessoa();
				pesquisador.setCategoria(CategoriaPessoa.PESQUISADOR);
				
				String cpfUnformatted = txtCpfPesq.getText().replace(".", "");
				cpfUnformatted = txtCpfPesq.getText().replace("-", "");
				pesquisador.setCpf(cpfUnformatted);
				
				PessoaController pessoaController = new PessoaController();
				
				pesquisador = pessoaController.consultarPessoaPorCPFController(pesquisador.getCpf());
				
				if (pesquisador != null) {
					lblNomeConsulta.setText(pesquisador.getNome());
				} else {
					
					if (JOptionPane.showConfirmDialog(null, "Pesquisador não encontrado, favor cadastrar","aviso"
							,JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						// TODO CADASTRO PESSOA
						
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
		pnlVacina.setBounds(10, 221, 346, 343);
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
		
		JLabel lblDataDeIncio = new JLabel("DATA DE INÌCIO DA PESQUISA");
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
		
		final JComboBox cbEstagioPesq = new JComboBox();
		cbEstagioPesq.setModel(new DefaultComboBoxModel(new String[] {"INICIAL", "TESTE", "APLICAÇÃO EM MASSA"}));
		cbEstagioPesq.setBounds(10, 263, 198, 20);
		pnlVacina.add(cbEstagioPesq);
		cbEstagioPesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblVacina = new JLabel("VACINA");
		lblVacina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVacina.setBounds(10, 11, 231, 14);
		pnlVacina.add(lblVacina);
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				Vacina novaVacina = new Vacina();
				EstagioVacina[] estagios = {EstagioVacina.INICIAL ,EstagioVacina.TESTE ,EstagioVacina.APLICACAO_EM_MASSA};
				
				novaVacina.setNome(txtNomeVacina.getText());
				novaVacina.setNomePaisOrigem(txtPaisVacina.getText());
				//novaVacina.setPesquisadorResponsavel(pesquisador);
				novaVacina.setDataInicioPesquisa(LocalDate.parse(txtDataVacina.getText(), formatadorData));
				
				novaVacina.setEstagioVacina(estagios[cbEstagioPesq.getSelectedIndex()]);
				


				
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrar.setBounds(113, 309, 132, 23);
		pnlVacina.add(btnCadastrar);


	}
}
