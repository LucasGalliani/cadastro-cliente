package cadastroclientes.casdastrodeclientes.repository;

import cadastroclientes.casdastrodeclientes.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    UserDetails findByLogin(String login);
}
