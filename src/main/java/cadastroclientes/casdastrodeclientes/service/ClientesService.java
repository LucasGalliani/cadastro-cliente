package cadastroclientes.casdastrodeclientes.service;

import cadastroclientes.casdastrodeclientes.domain.Cliente;
import cadastroclientes.casdastrodeclientes.dto.ClienteDTO;
import cadastroclientes.casdastrodeclientes.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private ClienteMapper mapperService;


    public ResponseEntity<ClienteDTO> cadastrar(ClienteDTO dto){

        Cliente cliente = mapperService.converteDtoParaEntidade(dto);
        var save = clientesRepository.save(cliente);
        var clienteDto = mapperService.converteEntidadeParaDto(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);

    }
}
