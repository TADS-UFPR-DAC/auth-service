package bantads.auth.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bantads.auth.model.Login;
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
	ResponseEntity<UsuarioDTO> login(@RequestBody Login login) {
		// BD para verificar o login/senha
		if (login.getLogin().equals(login.getSenha())) {
			UsuarioDTO usu = new UsuarioDTO(1, login.getLogin(), login.getLogin(), "XXX", "ADMIN");
			return ResponseEntity.ok().body(usu);
		} else {
			return ResponseEntity.status(401).build();
		}
	}
}
