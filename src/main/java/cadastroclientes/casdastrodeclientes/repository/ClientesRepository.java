package cadastroclientes.casdastrodeclientes.repository;

import cadastroclientes.casdastrodeclientes.domain.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Cliente,Long> {
    boolean existsByNomeOrEmailOrCpf(@NotBlank String nome, @NotBlank @Email String email, @NotNull @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos") String cpf);

    Optional<Cliente> findByCpf(String cpf);

    List<Cliente> findAllByAtivoTrue();
}
