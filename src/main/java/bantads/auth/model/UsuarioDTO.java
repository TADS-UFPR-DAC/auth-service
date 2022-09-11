package bantads.auth.model;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

	private long id;
	private long idPessoa;
	private String nome;
	private String login;
	private String senha;
	private String perfil;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(long id, long idPessoa, String nome, String login, String senha, String perfil) {
		super();
		this.id = id;
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}
