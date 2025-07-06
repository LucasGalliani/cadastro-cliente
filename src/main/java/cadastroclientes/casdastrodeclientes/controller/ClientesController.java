package cadastroclientes.casdastrodeclientes.controller;

import cadastroclientes.casdastrodeclientes.dto.ClienteDTO;
import cadastroclientes.casdastrodeclientes.repository.ClientesRepository;
import cadastroclientes.casdastrodeclientes.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private ClientesService clientesService;


    @PostMapping
    public ResponseEntity cadastrarCliente(@RequestBody @Valid ClienteDTO dto){

        return clientesService.cadastrar(dto);
    }

}
