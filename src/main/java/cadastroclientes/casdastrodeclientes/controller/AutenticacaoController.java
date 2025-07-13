package cadastroclientes.casdastrodeclientes.controller;

import cadastroclientes.casdastrodeclientes.domain.usuario.AutenticacaoDTO;
import cadastroclientes.casdastrodeclientes.domain.usuario.RegistroDTO;
import cadastroclientes.casdastrodeclientes.domain.usuario.Usuario;
import cadastroclientes.casdastrodeclientes.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO dto){

        var senhaUsuario = new UsernamePasswordAuthenticationToken(dto.login(),dto.senha());
        var auth = this.manager.authenticate(senhaUsuario);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistroDTO dto){

        if(this.usuarioRepository.findByLogin(dto.login()) != null) return ResponseEntity.badRequest().build();

        String senha = new BCryptPasswordEncoder().encode(dto.senha());
        Usuario novoUsuario = new Usuario(dto.login(),senha, dto.role().getRole());

    }

}
