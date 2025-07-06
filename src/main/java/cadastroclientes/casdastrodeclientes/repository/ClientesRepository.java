package cadastroclientes.casdastrodeclientes.repository;

import cadastroclientes.casdastrodeclientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente,Long> {
}
