package cadastroclientes.casdastrodeclientes.service;

import cadastroclientes.casdastrodeclientes.domain.Cliente;
import cadastroclientes.casdastrodeclientes.dto.ClienteDTO;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    public Cliente converteDtoParaEntidade(ClienteDTO dto){

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setCpf(dto.cpf());
        cliente.setAtivo(dto.ativo());
        cliente.setEndereco(dto.endereco());

        return cliente;
    }

    public ClienteDTO converteEntidadeParaDto(Cliente entidade){

        ClienteDTO clienteDTO = new ClienteDTO(entidade.getNome(), entidade.getEmail(), entidade.getTelefone(),entidade.getCpf(),entidade.getAtivo(),entidade.getEndereco());
        return clienteDTO;
    }

}
