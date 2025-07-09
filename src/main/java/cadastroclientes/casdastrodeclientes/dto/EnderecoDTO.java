package cadastroclientes.casdastrodeclientes.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank
        String rua,
        @NotBlank
        String numero,
        @NotBlank
        String bairro,
        @NotBlank
        String cpf,
        @NotBlank
        String cidade,
        @NotBlank
        String estado) {
}
