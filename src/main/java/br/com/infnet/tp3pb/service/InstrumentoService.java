package br.com.infnet.tp3pb.service;

import br.com.infnet.tp3pb.model.Instrumento;
import br.com.infnet.tp3pb.repository.InstrumentoRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstrumentoService {
    private final InstrumentoRepository repository;
    private final ClienteService clienteService;

    public InstrumentoService(InstrumentoRepository repository, ClienteService clienteService) {
        this.repository = repository;
        this.clienteService = clienteService;
    }


    public List<Instrumento> listarInstrumentos() {
        return repository.listar();
    }

    public Instrumento buscarPorId(Long id){
        return repository.buscarPorId(id).orElseThrow();
    }


    public Instrumento cadastrarInstrumento(Long idCliente,String tipoInstrumento,String marca,String modelo, String numeroSerie){
        validarCliente(idCliente);

        return repository.adicionar(id -> new Instrumento(id,
                idCliente,
                tipoInstrumento.trim(),
                marca.trim(),
                modelo.trim(),
                numeroSerie.trim()));
    }

    public Instrumento atualizar(Long id, Long idCliente,String tipoInstrumento,String marca,String modelo,String numeroSerie){
        buscarPorId(id);
        validarCliente(idCliente);
        Instrumento atualizado = new Instrumento(id,
                idCliente,
                tipoInstrumento,
                marca,
                modelo,
                numeroSerie);


        repository.atualizar(atualizado);

        return atualizado;

    }

    public void remover(Long id){
        buscarPorId(id);
        repository.remover(id);

    }

    private void validarCliente(Long id){
        try {
            clienteService.buscarPorId(id);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Cliente " + id + " nao existe.");
        }
    }
}
