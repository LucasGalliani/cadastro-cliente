package cadastroclientes.casdastrodeclientes.service;


import cadastroclientes.casdastrodeclientes.domain.usuario.AutenticacaoDTO;
import cadastroclientes.casdastrodeclientes.domain.usuario.LoginResponseDTO;
import cadastroclientes.casdastrodeclientes.domain.usuario.RegistroDTO;
import cadastroclientes.casdastrodeclientes.domain.usuario.Usuario;
import cadastroclientes.casdastrodeclientes.infra.security.TokenService;
import cadastroclientes.casdastrodeclientes.repository.UsuarioRepository;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;


    public ResponseEntity login(AutenticacaoDTO dto){

        var senhaUsuario = new UsernamePasswordAuthenticationToken(dto.login(),dto.senha());
        var auth = this.manager.authenticate(senhaUsuario);

        var token = tokenService.geraToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public ResponseEntity registrar(RegistroDTO dto){

        if(this.usuarioRepository.findByLogin(dto.login()) != null) return ResponseEntity.badRequest().build();

        String senha = new BCryptPasswordEncoder().encode(dto.senha());
        Usuario novoUsuario = new Usuario(dto.login(),senha, dto.role());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

}
