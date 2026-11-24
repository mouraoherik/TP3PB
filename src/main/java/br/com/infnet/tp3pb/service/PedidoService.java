package br.com.infnet.tp3pb.service;

import br.com.infnet.tp3pb.model.Pedido;
import br.com.infnet.tp3pb.model.StatusPedido;
import br.com.infnet.tp3pb.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository repository;
    private final ClienteService clienteService;
    private final InstrumentoService instrumentoService;

    public PedidoService(PedidoRepository repository, ClienteService clienteService, InstrumentoService instrumentoService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.instrumentoService = instrumentoService;
    }

    public List<Pedido> listarPedidos(){
        return repository.listar();
    }

    public Pedido buscarPorId(Long id){
        return repository.buscarPorId(id).orElseThrow();
    }


    public Pedido cadastrarPedido(Long idCliente, Long idInstrumento, LocalDate dataEntrada, LocalDate dataEntrega,
                                  String problemaRelatado, BigDecimal preco, StatusPedido statusPedido){
        validarCliente(idCliente);
        validarInstrumento(idInstrumento);

        return repository.adicionar(id -> new Pedido(id,
                idCliente,
                idInstrumento,
                dataEntrada,
                dataEntrega,
                problemaRelatado,
                preco,
                statusPedido));

    }

    public Pedido atualizar(Long id,Long idCliente, Long idInstrumento,LocalDate dataEntrada,LocalDate dataEntrega,
                            String problemaRelatado,BigDecimal preco,StatusPedido statusPedido){
        buscarPorId(id);
        validarCliente(idCliente);
        validarInstrumento(idInstrumento);

        Pedido atualizado = new Pedido(id,
                idCliente,
                idInstrumento,
                dataEntrada,
                dataEntrega,
                problemaRelatado,
                preco,
                statusPedido);

        repository.atualizar(atualizado);

        return atualizado;
    }

    public void remover(Long id){
        buscarPorId(id);
        repository.remover(id);

    }

    private void validarInstrumento(Long id){
        try {
            instrumentoService.buscarPorId(id);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Instrumento " + id + " nao existe.");
        }
    }

    private void validarCliente(Long id){
        try {
            clienteService.buscarPorId(id);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Cliente " + id + " nao existe.");
        }
    }
}
