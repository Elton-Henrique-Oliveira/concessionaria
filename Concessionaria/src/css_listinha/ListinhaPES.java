package css_listinha;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import css_dao.ListinhaDAO;
import css_dto.ListinhaDTO;
import css_modelos_tabelas.TableModelListinha;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListinhaPES extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_cpf;
	private JButton btn_busca;
	private JLabel lblcpf;
	private JPanel painel_principal;
	private JTextPane lbl_resposta;

	private ListinhaDAO objlistinhaDAO;
	private ArrayList<ListinhaDTO> objlista = new ArrayList<>();
	private JTable table_listinha;
	private JScrollPane scrollPane_1;
	private JLabel lbl_cpf;
	private JTextField txt_nome;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListinhaPES frame = new ListinhaPES();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListinhaPES() {
		setForeground(Color.BLACK);
		setTitle("Procurar na lista");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 699, 536);

		painel_principal = new JPanel();
		painel_principal.setBackground(Color.DARK_GRAY);
		getContentPane().add(painel_principal, BorderLayout.CENTER);
		painel_principal.setLayout(null);

		lblcpf = new JLabel("CPF");
		lblcpf.setForeground(SystemColor.info);
		lblcpf.setFont(new Font("Arial", Font.BOLD, 15));
		lblcpf.setBounds(114, 24, 54, 28);
		painel_principal.add(lblcpf);

		txt_cpf = new JTextField();
		txt_cpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {

				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					consultaListinha();
					carregaUsuários();
				}

			}
		});
		txt_cpf.setBackground(Color.GRAY);
		txt_cpf.setForeground(SystemColor.info);
		txt_cpf.setFont(new Font("Arial", Font.BOLD, 15));
		txt_cpf.setBounds(176, 24, 123, 28);
		painel_principal.add(txt_cpf);
		txt_cpf.setColumns(10);

		btn_busca = new JButton("Buscar");
		btn_busca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaListinha();
				carregaUsuários();
			}
		});
		btn_busca.setBackground(Color.GRAY);
		btn_busca.setForeground(SystemColor.desktop);
		btn_busca.setVerticalAlignment(SwingConstants.BOTTOM);
		btn_busca.setFont(new Font("Arial", Font.ITALIC, 16));
		btn_busca.setBounds(451, 46, 144, 28);
		painel_principal.add(btn_busca);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.GRAY);
		scrollPane_1.setBounds(40, 256, 605, 225);
		painel_principal.add(scrollPane_1);

		table_listinha = new JTable();
		table_listinha.setForeground(SystemColor.info);
		table_listinha.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		table_listinha.setBackground(Color.GRAY);
		scrollPane_1.setViewportView(table_listinha);

		lbl_cpf = new JLabel("Nome");
		lbl_cpf.setForeground(SystemColor.info);
		lbl_cpf.setFont(new Font("Arial", Font.BOLD, 15));
		lbl_cpf.setBounds(114, 63, 54, 29);
		painel_principal.add(lbl_cpf);

		txt_nome = new JTextField();
		txt_nome.setForeground(SystemColor.info);
		txt_nome.setFont(new Font("Arial", Font.BOLD, 15));
		txt_nome.setColumns(10);
		txt_nome.setBackground(Color.GRAY);
		txt_nome.setBounds(175, 63, 241, 29);
		painel_principal.add(txt_nome);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 128, 605, 117);
		painel_principal.add(scrollPane);

		lbl_resposta = new JTextPane();
		lbl_resposta.setFont(new Font("Arial", Font.BOLD, 15));
		lbl_resposta.setForeground(SystemColor.info);
		lbl_resposta.setBackground(Color.GRAY);
		scrollPane.setViewportView(lbl_resposta);

		carregaUsuários();
	}

	private void consultaListinha() {
		int cpf = 0;
		String nome = "";

		objlista.clear();
		lbl_resposta.setText("");

		if (txt_cpf.getText().trim().equals("") == false) {
			cpf = Integer.parseInt(txt_cpf.getText());
		}

		if (txt_nome.getText().trim().equals("") == false) {
			nome = txt_nome.getText();
		}
		
		objlistinhaDAO = new ListinhaDAO();
		objlista = objlistinhaDAO.verificaListinha(cpf, nome);
		
		for(int i = 0; i < objlista.size(); i++) {
			lbl_resposta.setText("Usuário " + objlista.get(i).getNome() + " encontrado na lista, ele está aqui porque "
					+ objlista.get(i).getMotivo());	
		}
		
		if(objlista.size() == 0) {
			lbl_resposta.setText("Usuário não encontrado!!");	
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
}
