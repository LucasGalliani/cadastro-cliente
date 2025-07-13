package cadastroclientes.casdastrodeclientes.controller;

import cadastroclientes.casdastrodeclientes.domain.usuario.AutenticacaoDTO;
import cadastroclientes.casdastrodeclientes.domain.usuario.RegistroDTO;
import cadastroclientes.casdastrodeclientes.domain.usuario.Usuario;
import cadastroclientes.casdastrodeclientes.repository.UsuarioRepository;
import cadastroclientes.casdastrodeclientes.service.AutenticacaoService;
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
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO dto){

            return autenticacaoService.login(dto);
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistroDTO dto){

        return autenticacaoService.registrar(dto);
    }

}
