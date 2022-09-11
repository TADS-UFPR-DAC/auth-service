package bantads.auth.repository;

import bantads.auth.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	// nome do método deve conter o atributo da classe
	Optional<Usuario> findByLogin(String login);

	// consulta por parâmetros nomeados
	@Query("from Usuario where login = :login and senha = :senha")
	Optional<Usuario> findByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);

	// consulta por id de cliente
	@Query("from Usuario where id_cliente = :idCliente")
	Optional<Usuario> findByClienteId(Long idCliente);

	Optional<Usuario> findByIdPessoa(Long idPessoa);
}