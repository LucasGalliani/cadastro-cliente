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
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private ClienteMapper mapperService;


    public ResponseEntity<List<ClienteDTO>> consultar() {

        List<Cliente> cliente = clientesRepository.findAll();

        if (cliente.isEmpty()) {
            throw new ClienteNoDataFoundException("Nenhum cliente encontrado.");
        }

        List<ClienteDTO> clienteDTO = cliente.stream()
                .map(mapperService::converteEntidadeParaDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clienteDTO);
    }


    public ResponseEntity<ClienteDTO> cadastrar(ClienteDTO dto) {

        boolean existeCampos = clientesRepository.existsByNomeOrEmailOrCpf(dto.nome(), dto.email(), dto.cpf());

        System.out.println("nome: " + dto.nome());
        System.out.println("email: " + dto.email());
        System.out.println("cpf: " + dto.cpf());
        System.out.println("Chego aqui: " + existeCampos);

        if (existeCampos) {
            throw new ClienteDuplicatedException("Já existe um cliente com CPF, nome ou e-mail informado.");
        }

        Cliente cliente = mapperService.converteDtoParaEntidade(dto);
        var save = clientesRepository.save(cliente);
        var clienteDto = mapperService.converteEntidadeParaDto(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
    }

}
