package bantads.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bantads.auth.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	// nome do método deve conter o atributo da classe
	public Usuario findByLogin(String login);

	// consulta por parâmetros nomeados
	@Query("from Usuario where login = :login and senha = :senha")
	public Usuario findByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);
}