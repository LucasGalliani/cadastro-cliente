package cadastroclientes.casdastrodeclientes.repository;

import cadastroclientes.casdastrodeclientes.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
