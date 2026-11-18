package br.com.infnet.tp3pb.service;

import br.com.infnet.tp3pb.model.Instrumento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstrumentoService {
    private final ClienteService clienteService;
    private final ArrayList<Instrumento> instrumentos = new ArrayList<>();

    private Long nextId = 1L;

    public InstrumentoService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void cadastrarInstrumento(Instrumento instrumento){
        if (clienteService.buscarPorId(instrumento.getIdCliente()) != null){
            instrumento.setIdInstrumento(nextId++);
            instrumentos.add(instrumento);
        }
    }

    public Instrumento buscarPorId(Long id){
        for (Instrumento instrumento : instrumentos){
            if (instrumento.getIdInstrumento().equals(id)){
                return instrumento;
            }
        }

        return null;
    }
}
