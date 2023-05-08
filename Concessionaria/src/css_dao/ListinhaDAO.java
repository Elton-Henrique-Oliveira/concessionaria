package css_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import css_dto.ListinhaDTO;

public class ListinhaDAO {

	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;

	private String sql = "";
	private ListinhaDTO objlistinhaDTO; 
	private ArrayList<ListinhaDTO> objlista = new ArrayList<>();
	
	public void adicionaListinha (ListinhaDTO objlistinhaDTO) {
		
		sql = "insert into";
		sql += "	css_listinha";
		sql += "		(css_listinha_cpf, css_listinha_nome, css_listinha_telefone, css_listinha_motivo)";
		sql += "	values ";
		sql += "		(?,?,?,?);";

		conn = new ConexaoDAO().conectaBD();
		
		try {

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, objlistinhaDTO.getCpf());
			pstm.setString(2, objlistinhaDTO.getNome());
			pstm.setString(3, objlistinhaDTO.getTelefone());
			pstm.setString(4, objlistinhaDTO.getMotivo());
			pstm.execute();
			pstm.close();
			
			JOptionPane.showMessageDialog(null, "Pessoa adicionada na lista!!");
			
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Erro ao adicionar uma pessoa aos zé listinhas: " + error);
		}
		
	}
	
	public void removerListinha(int cpf) {

		sql = "delete";
		sql += "	from";
		sql += "		css_listinha";
		sql += "	where";
		sql += "		css_listinha_cpf = ?";

		conn = new ConexaoDAO().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cpf);
			pstm.execute();
			pstm.close();
			
			JOptionPane.showMessageDialog(null, "Pessoa removida com sucesso!!");

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Erro ao remover uma pessoa dos zé listinhas: " + error);
		}
		
	}
	
	public ArrayList<ListinhaDTO> verificaListinha(int cpf, String nome) {

		sql = "select *";
		sql += "	from";
		sql += "		css_listinha";
		sql += "	where";
		if(cpf != 0) {
			sql += "		css_listinha_cpf = ?";
		}else {
			sql += "		css_listinha_nome = ?";
		}
		

		conn = new ConexaoDAO().conectaBD();
		objlista.clear();
		
		try {
			
			pstm = conn.prepareStatement(sql);
			if(cpf != 0) {
				pstm.setInt(1, cpf);
			}else {
				pstm.setString(1, nome);
			}			
			rs = pstm.executeQuery();

			while (rs.next()) {
				
				objlistinhaDTO = new ListinhaDTO();
				objlistinhaDTO.setCodigo(rs.getInt("css_listinha_codigo"));
				objlistinhaDTO.setCpf(rs.getInt("css_listinha_cpf"));
				objlistinhaDTO.setNome(rs.getString("css_listinha_nome"));
				objlistinhaDTO.setTelefone(rs.getString("css_listinha_telefone"));
				objlistinhaDTO.setMotivo(rs.getString("css_listinha_motivo"));
				objlista.add(objlistinhaDTO);
				
			}
			rs.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Erro ao remover uma pessoa dos zé listinhas: " + error);
		}
		
		return objlista;
		
	}
	
	public ArrayList<ListinhaDTO> buscaListinha() {

		sql = "select * from css_listinha;";

		conn = new ConexaoDAO().conectaBD();
		objlista.clear();
		
		try {

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				
				objlistinhaDTO = new ListinhaDTO();
				objlistinhaDTO.setCodigo(rs.getInt("css_listinha_codigo"));
				objlistinhaDTO.setCpf(rs.getInt("css_listinha_cpf"));
				objlistinhaDTO.setNome(rs.getString("css_listinha_nome"));
				objlistinhaDTO.setTelefone(rs.getString("css_listinha_telefone"));
				objlistinhaDTO.setMotivo(rs.getString("css_listinha_motivo"));
				objlista.add(objlistinhaDTO);
				
			}
			rs.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar as pessoas que estão na listinha dos zé listinhas:" + error);
		}

		return objlista;
		
	}
	
}
