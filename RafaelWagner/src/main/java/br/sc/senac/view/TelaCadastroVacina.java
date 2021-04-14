package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

import br.sc.senac.model.vo.EstagioVacina;

import java.awt.SystemColor;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;

public class TelaCadastroVacina {

	private JFrame frmCadastroVacina;
	private JTextField txtNomeVacina;
	private JTextField txtPaisVacina;
	private JTextField txtDataVacina;
	private JTextField txtNomePesq;
	private JTextField txtCpfPesq;
	private JTextField txtNascPesq;

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
		frmCadastroVacina.setBounds(100, 100, 344, 520);
		frmCadastroVacina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroVacina.getContentPane().setLayout(null);
		
		JPanel pnlVacina = new JPanel();
		pnlVacina.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlVacina.setBackground(SystemColor.menu);
		pnlVacina.setBounds(10, 190, 308, 276);
		frmCadastroVacina.getContentPane().add(pnlVacina);
		pnlVacina.setLayout(null);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(10, 11, 64, 14);
		pnlVacina.add(lblNome);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtNomeVacina = new JTextField();
		txtNomeVacina.setBounds(10, 34, 288, 20);
		pnlVacina.add(txtNomeVacina);
		txtNomeVacina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeVacina.setColumns(10);
		
		JLabel lblPaisDeOrigem = new JLabel("PAÍS DE ORIGEM");
		lblPaisDeOrigem.setBounds(10, 78, 198, 14);
		pnlVacina.add(lblPaisDeOrigem);
		lblPaisDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPaisVacina = new JTextField();
		txtPaisVacina.setBounds(10, 103, 288, 20);
		pnlVacina.add(txtPaisVacina);
		txtPaisVacina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPaisVacina.setColumns(10);
		
		JLabel lblDataDeIncio = new JLabel("DATA DE INÌCIO DA PESQUISA");
		lblDataDeIncio.setBounds(10, 146, 198, 14);
		pnlVacina.add(lblDataDeIncio);
		lblDataDeIncio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		try {
			MaskFormatter data = new MaskFormatter("##/##/####");
			txtDataVacina = new JFormattedTextField(data);
		} catch (ParseException e) {
			txtDataVacina = new JTextField();
		}
		txtDataVacina.setBounds(10, 169, 288, 20);
		pnlVacina.add(txtDataVacina);
		txtDataVacina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDataVacina.setColumns(10);

		
		
		JLabel lblEstgioDaPesquisa = new JLabel("ESTÁGIO DA PESQUISA");
		lblEstgioDaPesquisa.setBounds(10, 213, 152, 14);
		pnlVacina.add(lblEstgioDaPesquisa);
		lblEstgioDaPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox cbEstagioPesq = new JComboBox();
		cbEstagioPesq.setModel(new DefaultComboBoxModel(new String[] {EstagioVacina.INICIAL.name(), EstagioVacina.TESTE.name(), EstagioVacina.APLICACAO_EM_MASSA.name().replace('_', ' ')}));
		cbEstagioPesq.setBounds(10, 238, 198, 20);
		pnlVacina.add(cbEstagioPesq);
		cbEstagioPesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel pnlPesquisador = new JPanel();
		pnlPesquisador.setBackground(SystemColor.menu);
		pnlPesquisador.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlPesquisador.setBounds(10, 11, 308, 168);
		frmCadastroVacina.getContentPane().add(pnlPesquisador);
		pnlPesquisador.setLayout(null);
		
		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO");
		lblDataDeNascimento.setBounds(10, 118, 198, 14);
		pnlPesquisador.add(lblDataDeNascimento);
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 62, 198, 14);
		pnlPesquisador.add(lblCpf);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPesquisadorResponsvel = new JLabel("PESQUISADOR RESPONSÁVEL");
		lblPesquisadorResponsvel.setBounds(10, 11, 198, 14);
		pnlPesquisador.add(lblPesquisadorResponsvel);
		lblPesquisadorResponsvel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtNomePesq = new JTextField();
		txtNomePesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomePesq.setColumns(10);
		txtNomePesq.setBounds(10, 31, 288, 20);
		pnlPesquisador.add(txtNomePesq);
		
		txtCpfPesq = new JTextField();
		txtCpfPesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpfPesq.setColumns(10);
		txtCpfPesq.setBounds(10, 87, 288, 20);
		pnlPesquisador.add(txtCpfPesq);
		
		try {
			MaskFormatter data = new MaskFormatter("##/##/####");
			txtNascPesq = new JFormattedTextField(data);
		} catch (ParseException e) {
			txtNascPesq = new JTextField();
		}
		txtNascPesq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNascPesq.setColumns(10);
		txtNascPesq.setBounds(10, 137, 288, 20);
		pnlPesquisador.add(txtNascPesq);
	}
}
