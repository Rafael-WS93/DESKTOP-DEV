package br.sc.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.sc.senac.controller.VacinaController;
import br.sc.senac.model.vo.Vacina;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class TelaListaVacinas {

	private JFrame frmTelaListaVacinas;

	private JTable tableVacinas;
	
	private JButton btnExcluir;
	private JButton btnEditar;
	
	private List<Vacina> vacinas;
	private Vacina vacina;
	
	private JLabel lblNomeSelecionado;
	private JLabel lblPaisSelecionado;
	private JLabel lblCpfSelecionado;
	private JLabel lblPesqSelecionado;
	
	private JPanel panelTabela;
	
	private String[] nomesColunas = {"NOME" ,"PAÍS" ,"PESQUISADOR" ,"ESTÁGIO" ,"DATA"};

	private DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JLabel lblNomeTitle;
	private JLabel lblPaisTitle;
	private JLabel lblPesquisador;
	private JLabel lblCpf;
	private JButton btnMenuPrinc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaVacinas window = new TelaListaVacinas();
					window.frmTelaListaVacinas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListaVacinas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTelaListaVacinas = new JFrame();
		frmTelaListaVacinas.setTitle("LISTA DE VACINAS");
		frmTelaListaVacinas.setBounds(100, 100, 626, 609);
		frmTelaListaVacinas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTelaListaVacinas.getContentPane().setLayout(null);
		
		btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VacinaController controller = new VacinaController();
				
				String msg = "TEM CERTEZA QUE DESEJA EXCLUIR A VACINA:\nNOME: "+ vacina.getNome()+"\nPAÍS: "+vacina.getNomePaisOrigem();
				
				if (JOptionPane.showConfirmDialog(null, msg, "AVISO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, controller.excluirVacina(vacina), "AVISO", JOptionPane.INFORMATION_MESSAGE);
					
					atualizarTabelaVacinas();
				}
				
				atualizarTabelaVacinas();
				
			}
		});
		btnExcluir.setBounds(301, 527, 122, 32);
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmTelaListaVacinas.getContentPane().add(btnExcluir);
		
		btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroVacina telaCadastroVacina = new TelaCadastroVacina(vacina);
				telaCadastroVacina.getFrmCadastroVacina().setVisible(true);
				
				getFrmTelaListaVacinas().dispose();
			}
		});
		btnEditar.setBounds(169, 527, 122, 32);
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmTelaListaVacinas.getContentPane().add(btnEditar);
		
		panelTabela = new JPanel();
		panelTabela.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelTabela.setBounds(10, 11, 591, 311);
		frmTelaListaVacinas.getContentPane().add(panelTabela);
		panelTabela.setLayout(null);
		
		
		
		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setBounds(452, 11, 122, 32);
		panelTabela.add(btnConsultar);
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tableVacinas = new JTable();
		this.limparTabelaVacinas();
		
		tableVacinas = new JTable(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				}
			) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		});
		tableVacinas.setBounds(10, 54, 564, 237);
		panelTabela.add(tableVacinas);
		
		tableVacinas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tableVacinas.getSelectedRow();
				
				if (indiceSelecionado > 0) {
					setVacina(vacinas.get(indiceSelecionado -1));
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					
					lblCpfSelecionado.setText(getVacina().getPesquisadorResponsavel().getCpf());
					lblPesqSelecionado.setText(getVacina().getPesquisadorResponsavel().getNome());
					
					lblNomeSelecionado.setText(getVacina().getNome());
					lblPaisSelecionado.setText(getVacina().getNomePaisOrigem());
				} else {
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);
					
					lblCpfSelecionado.setText("----------");
					lblPesqSelecionado.setText("----------");
					
					lblNomeSelecionado.setText("----------");
					lblPaisSelecionado.setText("----------");
				}
			}
		});
		tableVacinas.setCellSelectionEnabled(true);
		tableVacinas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableVacinas.setColumnSelectionAllowed(true);
		

		
		JLabel lblVacinas = new JLabel("VACINAS");
		lblVacinas.setBounds(10, 15, 102, 25);
		panelTabela.add(lblVacinas);
		lblVacinas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnMenuPrinc = new JButton("MENU PRINCIPAL");
		btnMenuPrinc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaBoasVindas telaBoasVindas = new TelaBoasVindas();
				
				telaBoasVindas.getFrmBemVindo().setVisible(true);
				getFrmTelaListaVacinas().dispose();
				
			}
		});
		btnMenuPrinc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMenuPrinc.setBounds(295, 11, 147, 30);
		panelTabela.add(btnMenuPrinc);
		
		JPanel panelVacina = new JPanel();
		panelVacina.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelVacina.setBounds(10, 333, 261, 176);
		frmTelaListaVacinas.getContentPane().add(panelVacina);
		panelVacina.setLayout(null);
		
		lblNomeTitle = new JLabel("VACINA SELECIONADA");
		lblNomeTitle.setBounds(10, 11, 219, 25);
		panelVacina.add(lblNomeTitle);
		lblNomeTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblPaisTitle = new JLabel("PAÍS");
		lblPaisTitle.setBounds(10, 95, 156, 25);
		panelVacina.add(lblPaisTitle);
		lblPaisTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblNomeSelecionado = new JLabel("----------");
		lblNomeSelecionado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeSelecionado.setBounds(20, 45, 231, 25);
		panelVacina.add(lblNomeSelecionado);
		
		lblPaisSelecionado = new JLabel("----------");
		lblPaisSelecionado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaisSelecionado.setBounds(20, 131, 231, 25);
		panelVacina.add(lblPaisSelecionado);
		
		
		JPanel panelPesquisador = new JPanel();
		panelPesquisador.setLayout(null);
		panelPesquisador.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelPesquisador.setBounds(281, 334, 320, 176);
		frmTelaListaVacinas.getContentPane().add(panelPesquisador);
		
		lblPesquisador = new JLabel("PESQUISADOR");
		lblPesquisador.setBounds(10, 11, 156, 25);
		panelPesquisador.add(lblPesquisador);
		lblPesquisador.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 88, 156, 25);
		panelPesquisador.add(lblCpf);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblCpfSelecionado = new JLabel("----------");
		lblCpfSelecionado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpfSelecionado.setBounds(20, 124, 231, 25);
		panelPesquisador.add(lblCpfSelecionado);
		
		lblPesqSelecionado = new JLabel("----------");
		lblPesqSelecionado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPesqSelecionado.setBounds(20, 47, 231, 25);
		panelPesquisador.add(lblPesqSelecionado);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				atualizarTabelaVacinas();
			}
		});

		
		
	}
	
	
	private void atualizarTabelaVacinas() {
		VacinaController vacinaController = new VacinaController();
		
		vacinas = vacinaController.consultarTodasVacinas();
		
		this.limparTabelaVacinas();

		DefaultTableModel model = (DefaultTableModel) this.tableVacinas.getModel();
		
		for(Vacina vac: vacinas) {
			Object[] novaLinhaTabela = new Object[5];
			
			novaLinhaTabela[0] = vac.getNome();
			novaLinhaTabela[1] = vac.getNomePaisOrigem();
			novaLinhaTabela[2] = vac.getPesquisadorResponsavel().getNome();
			novaLinhaTabela[3] = vac.getEstagioVacina().toString().replace("_", " ");
			novaLinhaTabela[4] = vac.getDataInicioPesquisa().format(formatadorData);
			
			model.addRow(novaLinhaTabela);
		}
		
	}
	
	private void limparTabelaVacinas() {

		tableVacinas.setModel(new DefaultTableModel(new Object[][] {nomesColunas, }, nomesColunas));
		
		
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public JFrame getFrmTelaListaVacinas() {
		return frmTelaListaVacinas;
	}
	
	
}
