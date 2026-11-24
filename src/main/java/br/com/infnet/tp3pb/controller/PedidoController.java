package br.com.infnet.tp3pb.controller;


import br.com.infnet.tp3pb.model.Pedido;
import br.com.infnet.tp3pb.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        Pedido pedido = pedidoService.buscarPorId(id);

        if (pedido != null) {
            return ResponseEntity.ok().body(pedido);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.listarPedidos();
    }

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody Pedido pedido){
        Pedido criado = pedidoService.cadastrarPedido(pedido.getIdCliente(),
                pedido.getIdInstrumento(),pedido.getDataEntrada(),pedido.getDataEntrega(),
                pedido.getProblemaRelatado(),pedido.getPreco(),pedido.getStatusPedido());

        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable Long id,@Valid @RequestBody Pedido entrada){

        pedidoService.atualizar(id,entrada.getIdCliente(),
                entrada.getIdInstrumento(),entrada.getDataEntrada(),
                entrada.getDataEntrega(),entrada.getProblemaRelatado(),
                entrada.getPreco(),entrada.getStatusPedido());

        return ResponseEntity.ok().body(entrada);
    }
}
