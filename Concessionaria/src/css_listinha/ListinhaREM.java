package css_listinha;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import css_dao.ListinhaDAO;
import css_dto.ListinhaDTO;
import css_modelos_tabelas.TableModelListinha;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.MediaTracker;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ListinhaREM extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_cpf;
	private JTable table_listinha;

	private JScrollPane scrollPane;
	private JButton btn_remover;
	private JPanel painel_principal;
	private JLabel lbl_cpf;

	private ListinhaDAO objlistinhaDAO;
	private ArrayList<ListinhaDTO> objlista = new ArrayList<>();
	private ArrayList<ListinhaDTO> objlista2 = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListinhaREM frame = new ListinhaREM();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws MalformedURLException
	 */
	public ListinhaREM() throws MalformedURLException {
		setTitle("Remover da lista");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 670, 447);

		painel_principal = new JPanel();
		painel_principal.setBackground(Color.DARK_GRAY);
		getContentPane().add(painel_principal, BorderLayout.CENTER);
		painel_principal.setLayout(null);

		lbl_cpf = new JLabel("CPF");
		lbl_cpf.setForeground(SystemColor.info);
		lbl_cpf.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lbl_cpf.setBounds(83, 33, 51, 29);
		painel_principal.add(lbl_cpf);

		txt_cpf = new JTextField();
		txt_cpf.setBackground(Color.GRAY);
		txt_cpf.setForeground(SystemColor.info);
		txt_cpf.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		txt_cpf.setBounds(144, 33, 139, 29);
		painel_principal.add(txt_cpf);
		txt_cpf.setColumns(10);

		btn_remover = new JButton("Remover");
		btn_remover.setBackground(Color.GRAY);
		btn_remover.setForeground(SystemColor.desktop);
		btn_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerListinha();
				carregaUsuários();
			}
		});
		btn_remover.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		btn_remover.setBounds(411, 61, 177, 29);
		painel_principal.add(btn_remover);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setBounds(23, 153, 605, 253);
		painel_principal.add(scrollPane);

		table_listinha = new JTable();
		table_listinha.setForeground(SystemColor.info);
		table_listinha.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		table_listinha.setBackground(Color.GRAY);
		scrollPane.setViewportView(table_listinha);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(46, 96, 46, 14);
		painel_principal.add(lblNewLabel);

		lbl_cpf.setIcon(verificaImagem());
		painel_principal.add(lbl_cpf);

		carregaUsuários();
	}

	private void removerListinha() {
		int cpf = 0;

		if (txt_cpf.getText().trim().equals("") == false) {
			cpf = Integer.parseInt(txt_cpf.getText());
		}

		objlistinhaDAO = new ListinhaDAO();
		objlista2 = objlistinhaDAO.verificaListinha(cpf, "");

		if (objlista2.size() == 1) {

			objlistinhaDAO = new ListinhaDAO();
			objlistinhaDAO.removerListinha(cpf);

		} else {
			JOptionPane.showMessageDialog(null, "Usuário não encontrado!!");
		}
	}

	private void carregaUsuários() {

		ArrayList<Object> dados = new ArrayList<>();
		String[] colunas = new String[] { "CPF", "Nome", "Motivo" };

		try {

			ListinhaDAO objlistinhaDAO = new ListinhaDAO();
			objlista = objlistinhaDAO.buscaListinha();

			if (objlista.size() != 0) {

				for (int num = 0; num < objlista.size(); num++) {
					dados.add(new Object[] { objlista.get(num).getCpf(), objlista.get(num).getNome(),
							objlista.get(num).getMotivo() });
				}

			}

			TableModelListinha modelo = new TableModelListinha(dados, colunas);
			table_listinha.setModel(modelo);
			table_listinha.getColumnModel().getColumn(0).setPreferredWidth(80);
			table_listinha.getColumnModel().getColumn(0).setResizable(false);
			table_listinha.getColumnModel().getColumn(1).setPreferredWidth(200);
			table_listinha.getColumnModel().getColumn(1).setResizable(false);
			table_listinha.getColumnModel().getColumn(2).setPreferredWidth(323);
			table_listinha.getColumnModel().getColumn(2).setResizable(false);
			table_listinha.getTableHeader().setReorderingAllowed(false);
			// table_listinha.setAutoResizeMode(table_listinha.AUTO_RESIZE_OFF);
			table_listinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		} catch (

		Exception error) {
			// JOptionPane.showMessageDialog(null, "Erro ao preencher tabela" + error);
		}

	}
	
	public ImageIcon verificaImagem() throws MalformedURLException {
		
		URL urlImg = new URL("https://i.imgur.com/PFTwOfJ.jpg");
		ImageIcon imgIcon = new ImageIcon(urlImg);
		while (imgIcon.getImageLoadStatus() == MediaTracker.LOADING)
			;
		
		return imgIcon;
	}
}
