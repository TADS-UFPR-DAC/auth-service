package bantads.auth.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "tb_usuarios")
public class Usuario implements Serializable {
	private static final Long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id_usu")
	private Long id;
	@Column(name = "nome_usu")
	private String nome;
	@Column(name = "login_usu")
	private String login;
	@Column(name = "senha_usu")
	private String senha;
	@Column(name = "perfil_usu")
	private String perfil;

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nome, String login, String senha, String perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

}
