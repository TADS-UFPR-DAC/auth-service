package bantads.auth.rest;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bantads.auth.exception.AuthException;
import bantads.auth.model.Login;
import bantads.auth.model.Usuario;
import bantads.auth.model.UsuarioDTO;
import bantads.auth.repository.UsuarioRepository;


@CrossOrigin
@RestController
public class AuthREST {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private ModelMapper mapper;

	@PostMapping("/login")
	public ResponseEntity<UsuarioDTO> login(@RequestBody Login login) {
		String usuario = login.getLogin();
		String senha = login.getSenha();
		
		Optional<Usuario> user = repo.findByLoginAndSenha(usuario, senha);
		if(user.isEmpty()) {
			throw new AuthException(HttpStatus.NOT_FOUND, "Usuário ou senha incorretos!");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(user, UsuarioDTO.class));
		}
	}

	@PostMapping("/usuarios")
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioDTO usuario) {
		String username = usuario.getLogin();
		
		Optional<Usuario> user = repo.findByLogin(username);
		
		if(!user.isEmpty()) {
			throw new AuthException(HttpStatus.CONFLICT, "Usuário já existe!");
		}else {
			repo.save(mapper.map(usuario, Usuario.class));
			Optional<Usuario> usu = repo.findByLogin(usuario.getLogin());
			return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(usu, UsuarioDTO.class));
		}
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> listarTodos() {
		
		List<Usuario> lista = repo.findAll();
		
		if(lista.isEmpty()) {
			throw new AuthException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado!");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(lista.stream().map(e -> mapper.map(e, UsuarioDTO.class)).collect(Collectors.toList()));
		}
	}
	
	@GetMapping("/usuarios/{idCliente}")
	public ResponseEntity<UsuarioDTO> listaUsuario(@PathVariable Long idCliente){
		
		Optional<Usuario> user = repo.findById(idCliente);
		if(user.isEmpty()) {
			throw new AuthException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(user, UsuarioDTO.class));
		}
	}
	
	@PutMapping("/usuarios/{idCliente}")
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long idCliente, @RequestBody UsuarioDTO usuario){
		
		Optional<Usuario> user = repo.findById(idCliente);
		if(user.isEmpty()) {
			throw new AuthException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
		}else {
			usuario.setId(idCliente);
			repo.save(mapper.map(usuario, Usuario.class));
			user = repo.findById(idCliente);
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(user, UsuarioDTO.class));
		}
	}
	
	@DeleteMapping("/usuarios/{idCliente}")
	public ResponseEntity deleteUsuario(@PathVariable Long idCliente){
		
		Optional<Usuario> usuario = repo.findById(idCliente);
		if(usuario.isEmpty()) {
			throw new AuthException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
		}else {
			repo.delete(mapper.map(usuario, Usuario.class));
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
}
