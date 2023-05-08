package css_dto;

public class ListinhaDTO {
	
	private int codigo, cpf;
	private String nome, motivo, telefone;
		
	public ListinhaDTO () {	
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String descricao) {
		this.motivo = descricao;
	}
}
