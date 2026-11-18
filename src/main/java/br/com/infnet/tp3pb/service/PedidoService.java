package br.com.infnet.tp3pb.service;

import br.com.infnet.tp3pb.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PedidoService {
    private final ClienteService clienteService;
    private final InstrumentoService instrumentoService;

    private final ArrayList<Pedido> pedidos = new ArrayList<>();

    private Long nextId = 1L;

    public PedidoService(ClienteService clienteService, InstrumentoService instrumentoService) {
        this.clienteService = clienteService;
        this.instrumentoService = instrumentoService;
    }

    public void cadastrarPedido(Pedido pedido){
        if (clienteService.buscarPorId(pedido.getIdCliente()) != null
                && instrumentoService.buscarPorId(pedido.getIdInstrumento()) != null){
            pedido.setIdCliente(nextId++);
            pedidos.add(pedido);
        }
    }

    public Pedido buscarPorId(Long id){
        for (Pedido pedido : pedidos){
            if (pedido.getIdPedido().equals(id)){
                return pedido;
            }
        }
        return null;
    }

    public void Garantia(Pedido pedido){

    }

}
