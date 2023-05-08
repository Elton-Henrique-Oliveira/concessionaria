package Apricacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import css_listinha.ListinhaCAD;
import css_listinha.ListinhaPES;
import css_listinha.ListinhaREM;

import java.awt.Font;

import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager.*;
import java.awt.Color;

public class Gerenciador {

	public JFrame frmGerenciador;
	private JDesktopPane desktopPane;

	// menus
	private JMenu menuTransferencia;
	private JMenu menuEspaco;
	private JMenuBar menuBar;
	private JMenu menuListinha;
	private JMenuItem menuItemAdicionar;
	private JMenuItem menuItemRemover;
	private JMenuItem menuItemPesquisar;
	
	private ListinhaPES objlistinhaPES;
	private ListinhaREM objlistinhaREM;
	private ListinhaCAD objlistinhaCAD;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gerenciador window = new Gerenciador();
					window.frmGerenciador.setVisible(true);
					window.frmGerenciador.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gerenciador() {
		initialize();
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmGerenciador = new JFrame();
		frmGerenciador.setForeground(Color.WHITE);
		frmGerenciador.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 16));
		frmGerenciador.setBackground(Color.DARK_GRAY);
		frmGerenciador.setTitle("CONCESSIONARIA");
		frmGerenciador.setBounds(100, 100, 1200, 900);
		frmGerenciador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.GRAY);
		desktopPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		frmGerenciador.getContentPane().add(desktopPane, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(SystemColor.inactiveCaptionBorder);
		frmGerenciador.setJMenuBar(menuBar);

		menuEspaco = new JMenu("");
		menuEspaco.setForeground(Color.GRAY);
		menuEspaco.setFont(new Font("Broadway", Font.BOLD, 15));
		menuEspaco.setBackground(SystemColor.textInactiveText);
		menuBar.add(menuEspaco);

		menuTransferencia = new JMenu("Transferencia");
		menuTransferencia.setBackground(Color.BLACK);
		menuTransferencia.setForeground(SystemColor.desktop);
		menuTransferencia.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuBar.add(menuTransferencia);

		menuListinha = new JMenu("Z\u00E9 listinhas");
		menuListinha.setBackground(Color.WHITE);
		menuListinha.setForeground(SystemColor.desktop);
		menuListinha.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuTransferencia.add(menuListinha);

		menuItemAdicionar = new JMenuItem("Adicionar");
		menuItemAdicionar.setBackground(Color.WHITE);
		menuItemAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarListinhaPessoa();
			}
		});
		menuItemAdicionar.setForeground(SystemColor.desktop);
		menuItemAdicionar.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuListinha.add(menuItemAdicionar);

		menuItemRemover = new JMenuItem("Remover");
		menuItemRemover.setBackground(Color.WHITE);
		menuItemRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					removerListinhaPessoa();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItemRemover.setForeground(SystemColor.desktop);
		menuItemRemover.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuListinha.add(menuItemRemover);

		menuItemPesquisar = new JMenuItem("Pesquisar");
		menuItemPesquisar.setBackground(Color.WHITE);
		menuItemPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarListinhaPessoa();
			}
		});
		menuItemPesquisar.setForeground(SystemColor.desktop);
		menuItemPesquisar.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuListinha.add(menuItemPesquisar);
		
		JMenu menuLeilao = new JMenu("Leil\u00E3o");
		menuLeilao.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuTransferencia.add(menuLeilao);
		
		JMenuItem menuItemAdicionarLeilao = new JMenuItem("Adicionar");
		menuItemAdicionarLeilao.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuLeilao.add(menuItemAdicionarLeilao);
		
		JMenuItem menuItemRemoverLeilao = new JMenuItem("Remover");
		menuItemRemoverLeilao.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuLeilao.add(menuItemRemoverLeilao);
		
		JMenuItem menuItemBuscarLeilao = new JMenuItem("Pesquisar");
		menuItemBuscarLeilao.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuLeilao.add(menuItemBuscarLeilao);
	}

	// metodos
	private void adicionarListinhaPessoa() {

		objlistinhaCAD = new ListinhaCAD();
		desktopPane.add(objlistinhaCAD);
		objlistinhaCAD.setVisible(true);

	}

	private void removerListinhaPessoa() throws MalformedURLException {

		objlistinhaREM = new ListinhaREM();
		desktopPane.add(objlistinhaREM);
		objlistinhaREM.setVisible(true);

	}

	private void pesquisarListinhaPessoa() {

		objlistinhaPES = new ListinhaPES();
		desktopPane.add(objlistinhaPES);
		objlistinhaPES.setVisible(true);

	}
}
