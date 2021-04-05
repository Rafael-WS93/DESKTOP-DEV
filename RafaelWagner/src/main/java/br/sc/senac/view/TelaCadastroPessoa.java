package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class TelaCadastroPessoa {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroupSexo = new ButtonGroup();
	private final ButtonGroup buttonGroupCat = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPessoa window = new TelaCadastroPessoa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroPessoa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 503, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(10, 96, 297, 28);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 175, 297, 28);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(10, 252, 297, 28);
		frame.getContentPane().add(textField_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Data de Nascimento : ");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 224, 193, 17);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("CPF :");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 147, 282, 17);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		lblNome.setBounds(10, 68, 193, 17);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCadastroDePessoa = new JLabel("Cadastro de Pessoa");
		lblCadastroDePessoa.setFont(new Font("Arial", Font.PLAIN, 24));
		lblCadastroDePessoa.setBounds(10, 11, 282, 46);
		frame.getContentPane().add(lblCadastroDePessoa);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(30, 443, 133, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBounds(179, 443, 133, 38);
		frame.getContentPane().add(btnCancelar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVoltar.setBounds(325, 443, 133, 38);
		frame.getContentPane().add(btnVoltar);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Sexo :");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(10, 291, 193, 17);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroupSexo.add(rdbtnFeminino);
		rdbtnFeminino.setBounds(132, 315, 109, 23);
		frame.getContentPane().add(rdbtnFeminino);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		buttonGroupSexo.add(rdbtnMasculino);
		rdbtnMasculino.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnMasculino.setBounds(20, 316, 109, 23);
		frame.getContentPane().add(rdbtnMasculino);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Categoria :");
		lblNewLabel_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(10, 365, 193, 17);
		frame.getContentPane().add(lblNewLabel_1_2_1_1);
		
		JRadioButton rdbtnPesquisador = new JRadioButton("Pesquisador");
		buttonGroupCat.add(rdbtnPesquisador);
		rdbtnPesquisador.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnPesquisador.setBounds(20, 389, 109, 23);
		frame.getContentPane().add(rdbtnPesquisador);
		
		JRadioButton rdbtnVoluntrio = new JRadioButton("Voluntário");
		buttonGroupCat.add(rdbtnVoluntrio);
		rdbtnVoluntrio.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnVoluntrio.setBounds(139, 389, 109, 23);
		frame.getContentPane().add(rdbtnVoluntrio);
		
		JRadioButton rdbtnFeminino_1_1 = new JRadioButton("Publico em Geral");
		buttonGroupCat.add(rdbtnFeminino_1_1);
		rdbtnFeminino_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnFeminino_1_1.setBounds(260, 389, 158, 23);
		frame.getContentPane().add(rdbtnFeminino_1_1);
	}
}
