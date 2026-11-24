package br.com.infnet.tp3pb.controller;

import br.com.infnet.tp3pb.model.Cliente;
import br.com.infnet.tp3pb.model.ErrorResponse;
import br.com.infnet.tp3pb.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        Cliente cliente = clienteService.buscarPorId(id);

        if (cliente != null){
            return ResponseEntity.ok().body(cliente);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody Cliente cliente){
        Cliente criado = clienteService.cadastrarCliente(cliente.getNome(),cliente.getTelefone(),
                cliente.getEmail(),cliente.getEndereco());


        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long  id,
                                    @Valid @RequestBody Cliente entrada){

        clienteService.atualizar(id,entrada.getNome(),entrada.getTelefone(), entrada.getEmail(),
                entrada.getEndereco());

       return ResponseEntity.ok().body(entrada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        clienteService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
