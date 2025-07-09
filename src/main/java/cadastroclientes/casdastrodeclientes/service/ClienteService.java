package cadastroclientes.casdastrodeclientes.service;

import cadastroclientes.casdastrodeclientes.domain.Cliente;
import cadastroclientes.casdastrodeclientes.dto.ClienteDTO;
import cadastroclientes.casdastrodeclientes.exception.ClienteDuplicatedException;
import cadastroclientes.casdastrodeclientes.exception.ClienteNoDataFoundException;
import cadastroclientes.casdastrodeclientes.repository.ClientesRepository;
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

        List<Cliente> cliente = clientesRepository.findAll();

        if (cliente.isEmpty()) {
            throw new ClienteNoDataFoundException("Nenhum cliente encontrado.");
        }

        List<ClienteDTO> clienteDTO = cliente.stream()
                .map(mapperService::converteEntidadeParaDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clienteDTO);
    }


    public ResponseEntity<ClienteDTO> cadastrarUsuario(ClienteDTO dto) {

        boolean existeCampos = clientesRepository.existsByNomeOrEmailOrCpf(dto.nome(), dto.email(), dto.cpf());

        if (existeCampos) {
            throw new ClienteDuplicatedException("Já existe um cliente com CPF, nome ou e-mail informado.");
        }

        Cliente cliente = mapperService.converteDtoParaEntidade(dto);
        var save = clientesRepository.save(cliente);
        var clienteDto = mapperService.converteEntidadeParaDto(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
    }


    public ResponseEntity<List<ClienteDTO>> consultarUsuarioPorCpf(String cpf) {

        Optional<Cliente> cliente = clientesRepository.findByCpf(cpf);

        if (cliente.isEmpty()) {
            throw new ClienteNoDataFoundException("Nenhum cliente encontrado.");
        }

        List<ClienteDTO> clienteDTO = cliente.stream()
                .map(mapperService::converteEntidadeParaDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clienteDTO);
    }

    public ResponseEntity<ClienteDTO> atualizarCadastroCliente(String cpf,ClienteDTO clienteDTO) {

        Cliente cliente = clientesRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNoDataFoundException("Nenhum cliente encontrado."));

        if (clienteDTO.nome() != null && !clienteDTO.nome().isBlank()) {
            cliente.setNome(clienteDTO.nome());
        }

        if (clienteDTO.email() != null && !clienteDTO.email().isBlank()) {
            cliente.setEmail(clienteDTO.email());
        }

        if (clienteDTO.telefone() != null && !clienteDTO.telefone().isBlank()) {
            cliente.setTelefone(clienteDTO.telefone());
        }

        Cliente clienteSave = clientesRepository.save(cliente);
        ClienteDTO clienteAtualizado = mapperService.converteEntidadeParaDto(clienteSave);

        return ResponseEntity.ok(clienteAtualizado);

    }

}
