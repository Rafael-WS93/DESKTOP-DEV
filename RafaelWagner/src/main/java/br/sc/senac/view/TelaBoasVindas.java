package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaBoasVindas {

	private JFrame frmBemVindo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBoasVindas window = new TelaBoasVindas();
					window.frmBemVindo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaBoasVindas() {
		initialize();
	}


	private void initialize() {
		frmBemVindo = new JFrame();
		frmBemVindo.setTitle("BEM VINDO");
		frmBemVindo.setBounds(100, 100, 426, 248);
		frmBemVindo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBemVindo.getContentPane().setLayout(null);
		
		JButton btnNovaVacina = new JButton("CADASTRAR NOVA VACINA");
		btnNovaVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastroVacina telaCadastroVacina = new TelaCadastroVacina(null);
				telaCadastroVacina.getFrmCadastroVacina().setVisible(true);
				getFrmBemVindo().dispose();
				
				
			}
		});
		btnNovaVacina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNovaVacina.setBounds(86, 42, 214, 38);
		frmBemVindo.getContentPane().add(btnNovaVacina);
		
		JLabel lblSaudacao = new JLabel("BEM VINDO SELECIONE UMA OPÇÃO ABAIXO :");
		lblSaudacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSaudacao.setBounds(10, 11, 414, 20);
		frmBemVindo.getContentPane().add(lblSaudacao);
		
		JButton btnCadastrarNovaPessoa = new JButton("CADASTRAR NOVA PESSOA");
		btnCadastrarNovaPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastroPessoa telaCadastroPessoa = new TelaCadastroPessoa(null);
				
				telaCadastroPessoa.getFrmCadastroPessoa().setVisible(true);
				getFrmBemVindo().dispose();
			}
		});
		btnCadastrarNovaPessoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrarNovaPessoa.setBounds(86, 91, 214, 38);
		frmBemVindo.getContentPane().add(btnCadastrarNovaPessoa);
		
		JButton btnListaDeVacinas = new JButton("LISTA DE VACINAS");
		btnListaDeVacinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaListaVacinas telaListaVacinas = new TelaListaVacinas();
				telaListaVacinas.getFrmTelaListaVacinas().setVisible(true);
				getFrmBemVindo().dispose();
				
			}
		});
		btnListaDeVacinas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListaDeVacinas.setBounds(86, 141, 214, 38);
		frmBemVindo.getContentPane().add(btnListaDeVacinas);
	}


	public JFrame getFrmBemVindo() {
		return frmBemVindo;
	}


	public void setFrmBemVindo(JFrame frmBemVindo) {
		this.frmBemVindo = frmBemVindo;
	}
	
	
}
