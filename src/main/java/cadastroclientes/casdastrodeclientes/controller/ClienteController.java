package cadastroclientes.casdastrodeclientes.controller;

import cadastroclientes.casdastrodeclientes.dto.ClienteDTO;
import cadastroclientes.casdastrodeclientes.repository.ClientesRepository;
import cadastroclientes.casdastrodeclientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesRepository repository;

    @Autowired
    private ClienteService clientesService;

    @GetMapping
    public ResponseEntity listarClientes() {
        return clientesService.consultarCadastro();
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarClientes(@RequestBody @Valid ClienteDTO dto) {

        return clientesService.cadastrarUsuario(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarClientesPorId(@PathVariable Long id) {
        return clientesService.consultarUsuarioPorId(id);
    }

    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity atualizarClientesPorCpf(@PathVariable String cpf, @RequestBody ClienteDTO dto){
        return clientesService.atualizarCadastroCliente(cpf,dto);

    }


}
