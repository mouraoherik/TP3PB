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
    public List<Cliente> listarClientes(@RequestParam(required = false)String email){
        return clienteService.listarClientes();
    }

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody Cliente cliente){
        for (Cliente checagem : clienteService.listarClientes()){
            if (cliente.getEmail().equals(checagem.getEmail())){
                return ResponseEntity.badRequest().body(new ErrorResponse("E-mail ja cadastrado"));
            }
        }

        clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long  id,
                                    @RequestBody Cliente entrada){
        if(id <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Id invalido"));
        }

        Cliente existente = null;
        for(Cliente cliente : clienteService.listarClientes()){
            if(cliente.getIdCliente().equals(id)){
                existente = cliente;
                break;
            }
        }
        if(existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Cliente nao localizado"));
        }
        existente.setNome(entrada.getNome());
        existente.setTelefone(entrada.getTelefone());
        existente.setEmail(entrada.getEmail());
        existente.setEndereco(entrada.getEndereco());
        return ResponseEntity.ok().body(existente);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        if (id <= 0 ){
            return ResponseEntity.badRequest().body(new ErrorResponse("Id invalido"));
        }
        boolean removeu = clienteService.remover(id);
        if (removeu){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Id nao localizado"));
    }
}
