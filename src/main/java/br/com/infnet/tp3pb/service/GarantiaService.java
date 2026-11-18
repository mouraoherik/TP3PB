package br.com.infnet.tp3pb.service;

import br.com.infnet.tp3pb.model.Garantia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GarantiaService {
    private final PedidoService pedidoService;

    private final ArrayList<Garantia> garantias = new ArrayList<>();

    public GarantiaService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
}
