package cadastroclientes.casdastrodeclientes.service;

import cadastroclientes.casdastrodeclientes.domain.Cliente;
import cadastroclientes.casdastrodeclientes.dto.ClienteDTO;
import cadastroclientes.casdastrodeclientes.exception.ClienteDuplicadoException;
import cadastroclientes.casdastrodeclientes.repository.ClientesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private ClienteMapper mapperService;


    public ResponseEntity<List<ClienteDTO>> consultarCadastro() {

        List<Cliente> cliente = clientesRepository.findAllByAtivoTrue();

        if (cliente.isEmpty()) {
            throw new EntityNotFoundException();
        }

        List<ClienteDTO> clienteDTO = cliente.stream()
                .map(mapperService::converteEntidadeParaDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clienteDTO);
    }


    public ResponseEntity<ClienteDTO> cadastrarUsuario(ClienteDTO dto) {

        boolean existeCampos = clientesRepository.existsByNomeOrEmailOrCpf(dto.nome(), dto.email(), dto.cpf());

        if (existeCampos) {
            throw new ClienteDuplicadoException("Já existe um cliente com CPF, nome ou e-mail informado.");
        }

        Cliente cliente = mapperService.converteDtoParaEntidade(dto);
        Cliente clienteSalvo = clientesRepository.save(cliente);

        var clienteDto = mapperService.converteEntidadeParaDto(clienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
    }


    public ResponseEntity<List<ClienteDTO>> consultarUsuarioPorCpf(String cpf) {

        Optional<Cliente> cliente = clientesRepository.findByCpf(cpf);

        if (cliente.isEmpty()) {
            throw new EntityNotFoundException();
        }

        List<ClienteDTO> clienteDTO = cliente.stream()
                .map(mapperService::converteEntidadeParaDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clienteDTO);
    }

    public ResponseEntity<ClienteDTO> atualizarCadastroCliente(String cpf,ClienteDTO clienteDTO) {

        Cliente cliente = clientesRepository.findByCpf(cpf)
                .orElseThrow(EntityNotFoundException::new);

        if (clienteDTO.nome() != null && !clienteDTO.nome().isBlank()) {
            cliente.setNome(clienteDTO.nome());
        }

        if (clienteDTO.email() != null && !clienteDTO.email().isBlank()) {
            cliente.setEmail(clienteDTO.email());
        }

        if (clienteDTO.telefone() != null && !clienteDTO.telefone().isBlank()) {
            cliente.setTelefone(clienteDTO.telefone());
        }

        if (clienteDTO.ativo() != null) {
            cliente.setAtivo(clienteDTO.ativo());
        }

        ClienteDTO clienteAtualizado = mapperService.converteEntidadeParaDto(cliente);

        return ResponseEntity.ok(clienteAtualizado);

    }

    public ResponseEntity<ClienteDTO> deletarCadastroCliente(String cpf){

        Cliente cliente = clientesRepository.findByCpf(cpf)
                .orElseThrow(EntityNotFoundException::new);

         cliente.setAtivo(false);

         return  ResponseEntity.noContent().build();
    }

}
