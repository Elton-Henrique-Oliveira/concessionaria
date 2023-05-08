package css_listinha;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import css_dao.ListinhaDAO;
import css_dto.ListinhaDTO;

import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ListinhaCAD extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_nome;
	private JTextField txt_cpf;
	private JTextField txt_telefone;

	private JScrollPane scrollPane;
	private JTextPane txt_motivo;
	private JPanel painel_central;
	private JLabel lblnome;
	private JLabel lblCpf;
	private JLabel lblTelefone;
	private JLabel lblMotivo;
	private JButton btb_adicionar;

	private ListinhaDTO objlistinhaDTO;
	private ListinhaDAO objlistinhaDAO;
	private ArrayList<ListinhaDTO> objlista = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListinhaCAD frame = new ListinhaCAD();
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
	public ListinhaCAD() {
		setIconifiable(true);
		setTitle("Cadastrar na lista");
		setClosable(true);
		setBounds(100, 100, 445, 456);

		painel_central = new JPanel();
		painel_central.setBackground(Color.DARK_GRAY);
		getContentPane().add(painel_central, BorderLayout.CENTER);

		lblnome = new JLabel("Nome");
		lblnome.setBounds(16, 61, 92, 28);
		lblnome.setForeground(SystemColor.info);
		lblnome.setFont(new Font("Arial", Font.ITALIC, 16));

		txt_nome = new JTextField();
		txt_nome.setBounds(112, 62, 292, 28);
		txt_nome.setBackground(Color.GRAY);
		txt_nome.setForeground(SystemColor.info);
		txt_nome.setToolTipText("Nome da pessoa para adicionar");
		txt_nome.setFont(new Font("Arial", Font.ITALIC, 16));
		txt_nome.setColumns(10);

		lblCpf = new JLabel("Cpf");
		lblCpf.setBounds(16, 100, 92, 28);
		lblCpf.setForeground(SystemColor.info);
		lblCpf.setFont(new Font("Arial", Font.ITALIC, 16));

		txt_cpf = new JTextField();
		txt_cpf.setBounds(112, 100, 292, 28);
		txt_cpf.setBackground(Color.GRAY);
		txt_cpf.setForeground(SystemColor.info);
		txt_cpf.setToolTipText("Cpf da pessoa para adicionar");
		txt_cpf.setFont(new Font("Arial", Font.ITALIC, 16));
		txt_cpf.setColumns(10);

		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(16, 139, 92, 34);
		lblTelefone.setForeground(SystemColor.info);
		lblTelefone.setFont(new Font("Arial", Font.ITALIC, 16));

		btb_adicionar = new JButton("Adicionar");
		btb_adicionar.setBounds(151, 357, 131, 33);
		btb_adicionar.setBackground(Color.GRAY);
		btb_adicionar.setForeground(SystemColor.desktop);
		btb_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarListinha();
			}
		});

		txt_telefone = new JTextField();
		txt_telefone.setBounds(112, 139, 292, 25);
		txt_telefone.setBackground(Color.GRAY);
		txt_telefone.setForeground(SystemColor.info);
		txt_telefone.setToolTipText("Telefone da pessoa para adicionar");
		txt_telefone.setFont(new Font("Arial", Font.ITALIC, 16));
		txt_telefone.setColumns(10);

		lblMotivo = new JLabel("Motivo");
		lblMotivo.setBounds(16, 208, 92, 19);
		lblMotivo.setForeground(SystemColor.info);
		lblMotivo.setFont(new Font("Arial", Font.ITALIC, 16));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 208, 292, 120);

		txt_motivo = new JTextPane();
		txt_motivo.setBackground(Color.GRAY);
		txt_motivo.setForeground(SystemColor.desktop);
		txt_motivo.setToolTipText("Motivo para o qual voc\u00EA vai adicionar");
		scrollPane.setViewportView(txt_motivo);
		txt_motivo.setFont(new Font("Arial", Font.ITALIC, 16));
		btb_adicionar.setToolTipText("Bot\u00E3o de adicionar");
		btb_adicionar.setFont(new Font("Arial", Font.ITALIC, 20));
		painel_central.setLayout(null);
		painel_central.add(lblnome);
		painel_central.add(txt_nome);
		painel_central.add(lblCpf);
		painel_central.add(txt_cpf);
		painel_central.add(lblTelefone);
		painel_central.add(txt_telefone);
		painel_central.add(lblMotivo);
		painel_central.add(scrollPane);
		painel_central.add(btb_adicionar);

	}

	private void adicionarListinha() {

		int cpf, verifica;
		String nome = "", telefone = "", motivo = "", mensagem_validacao = "";

		try {

			cpf = Integer.parseInt(txt_cpf.getText());
			nome = txt_nome.getText();
			telefone = txt_telefone.getText();
			motivo = txt_motivo.getText();
			verifica = consultaListinha(cpf);

			mensagem_validacao = validaValores(cpf, nome, telefone, motivo);

			if (verifica == 1) {
				JOptionPane.showMessageDialog(null, "Usuário já está na lista!!");
			}

			if (mensagem_validacao.trim().equals("") == true && verifica == 0) {

				objlistinhaDTO = new ListinhaDTO();
				objlistinhaDTO.setCpf(cpf);
				objlistinhaDTO.setNome(nome);
				objlistinhaDTO.setTelefone(telefone);
				objlistinhaDTO.setMotivo(motivo);

				objlistinhaDAO = new ListinhaDAO();
				objlistinhaDAO.adicionaListinha(objlistinhaDTO);

				limpaCampos();

			} else {
				JOptionPane.showMessageDialog(null, "Erro: " +  mensagem_validacao);
			}

		} catch (Exception error2) {
			JOptionPane.showMessageDialog(null, error2);
		}

	}

	private String validaValores(int cpf, String nome, String telefone, String motivo) {
		String mensagem_validacao = "";

		if (cpf == 0 || Integer.toString(cpf).trim().equals("") == true) {
			mensagem_validacao += "Adicione o cpf da pessoa\n";
		}
		if (nome.trim().equals("") == true) {
			mensagem_validacao += "Adicione o nome da pessoa\n";
		}
		if (motivo.trim().equals("") == true) {
			mensagem_validacao += "Adicione o motivo para querer adicionar a pessoa\n";
		}

		return mensagem_validacao;
	}

	private void limpaCampos() {

		txt_cpf.setText("");
		txt_nome.setText("");
		txt_telefone.setText("");
		txt_motivo.setText("");

	}

	private int consultaListinha(int cpf) {
		int lbl_resposta = 0;

		objlista.clear();

		objlistinhaDAO = new ListinhaDAO();
		objlista = objlistinhaDAO.verificaListinha(cpf,"");

		if (objlista.size() != 0) {

			lbl_resposta = 1;

		}

		return lbl_resposta;
	}
}
