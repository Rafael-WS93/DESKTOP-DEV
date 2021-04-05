package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroVacina {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVacina window = new TelaCadastroVacina();
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
	public TelaCadastroVacina() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Vacinas");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		lblNewLabel.setBounds(19, 11, 282, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNomeDaVacina = new JLabel("Nome da Vacina :");
		lblNomeDaVacina.setFont(new Font("Arial", Font.BOLD, 14));
		lblNomeDaVacina.setBounds(19, 68, 193, 17);
		frame.getContentPane().add(lblNomeDaVacina);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(19, 96, 297, 28);
		frame.getContentPane().add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome do Pesquisador Responsável :");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(19, 147, 282, 17);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(19, 175, 297, 28);
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Data de Início da pesquisa :");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(19, 224, 193, 17);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(19, 252, 297, 28);
		frame.getContentPane().add(textField_2);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Selecione o Estágio :");
		lblNewLabel_1_3_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_3_1.setBounds(19, 310, 207, 17);
		frame.getContentPane().add(lblNewLabel_1_3_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Inicial");
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnNewRadioButton.setBounds(19, 334, 155, 38);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnTeste = new JRadioButton("Teste");
		rdbtnTeste.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnTeste.setBounds(19, 375, 155, 38);
		frame.getContentPane().add(rdbtnTeste);
		
		JRadioButton rdbtnAplicaaoEmMassa = new JRadioButton("Aplicaçao em massa");
		rdbtnAplicaaoEmMassa.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnAplicaaoEmMassa.setBounds(19, 416, 155, 38);
		frame.getContentPane().add(rdbtnAplicaaoEmMassa);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		btnNewButton.setBounds(19, 481, 133, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBounds(168, 481, 133, 38);
		frame.getContentPane().add(btnCancelar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVoltar.setBounds(314, 481, 133, 38);
		frame.getContentPane().add(btnVoltar);
	}

}
