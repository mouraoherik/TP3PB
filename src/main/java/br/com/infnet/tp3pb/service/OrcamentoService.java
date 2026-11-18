package br.com.infnet.tp3pb.service;

import br.com.infnet.tp3pb.model.Orcamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrcamentoService {
    private final PedidoService pedidoService;
    private Long nextId = 1L;

    private final ArrayList<Orcamento> orcamentos = new ArrayList<>();

    public OrcamentoService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void cadastrarOrcamento(Orcamento orcamento){
        if (pedidoService.buscarPorId(orcamento.getIdPedido()) != null){
            orcamento.setIdOrcamento(nextId);
            orcamentos.add(orcamento);
        }
    }

    public Orcamento buscarPorId(Long id){
        for (Orcamento orcamento : orcamentos){
            if (orcamento.getIdOrcamento().equals(id)){
                return orcamento;
            }
        }
        return null;
    }
}
