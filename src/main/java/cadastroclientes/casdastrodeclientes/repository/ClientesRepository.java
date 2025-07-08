package cadastroclientes.casdastrodeclientes.repository;

import cadastroclientes.casdastrodeclientes.domain.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente,Long> {
    boolean existsByNomeOrEmailOrCpf(@NotBlank String nome, @NotBlank @Email String email, @NotNull @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos") String cpf);
}
