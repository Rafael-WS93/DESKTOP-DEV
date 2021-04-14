package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.ButtonGroup;

public class TelaCadastroPessoa {

	private JFrame frmCadastroPessoa;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtNasc;
	private final ButtonGroup bGSexo = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPessoa window = new TelaCadastroPessoa();
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
	public TelaCadastroPessoa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroPessoa = new JFrame();
		frmCadastroPessoa.setTitle("Cadastro Pessoa");
		frmCadastroPessoa.setBounds(100, 100, 450, 352);
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
		
		JLabel lblDataDeNasciento = new JLabel("DATA DE NASCIENTO");
		lblDataDeNasciento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNasciento.setBounds(10, 132, 141, 14);
		frmCadastroPessoa.getContentPane().add(lblDataDeNasciento);
		
		try {
			MaskFormatter cpf = new MaskFormatter("###.###.###-##");
			txtCpf = new JFormattedTextField(cpf);
		} catch (ParseException e1) {
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
		
		JRadioButton rbMasc = new JRadioButton("MASCULINO");
		bGSexo.add(rbMasc);
		rbMasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbMasc.setBounds(10, 271, 109, 23);
		frmCadastroPessoa.getContentPane().add(rbMasc);
		
		JRadioButton rbFem = new JRadioButton("FEMININO");
		bGSexo.add(rbFem);
		rbFem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbFem.setBounds(121, 271, 109, 23);
		frmCadastroPessoa.getContentPane().add(rbFem);
		
		JComboBox cbCategoria = new JComboBox();
		cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbCategoria.setBounds(10, 209, 169, 20);
		frmCadastroPessoa.getContentPane().add(cbCategoria);
		
		txtNasc = new JTextField();
		txtNasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNasc.setColumns(10);
		txtNasc.setBounds(10, 157, 211, 20);
		frmCadastroPessoa.getContentPane().add(txtNasc);
	}
}
