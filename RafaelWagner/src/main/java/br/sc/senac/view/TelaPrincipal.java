package br.sc.senac.view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class TelaPrincipal {

	private JFrame frame;
	private JTextField textField;
	private JMenuItem mntmNewMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
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
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 759, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		menuBar.setFont(new Font("Arial", Font.PLAIN, 12));
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("VACINAS");
		mnNewMenu.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/vacina.png")));
		mnNewMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("item");
		mntmNewMenuItem.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/vacinaAdicionar.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		mnNewMenu_1.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/sc/senac/view/icones/vacinaMini.png")));
		menuBar.add(mnNewMenu_1);
		frame.getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
