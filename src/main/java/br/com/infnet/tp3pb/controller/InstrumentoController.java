package br.com.infnet.tp3pb.controller;

import br.com.infnet.tp3pb.model.Instrumento;
import br.com.infnet.tp3pb.repository.InstrumentoRepository;
import br.com.infnet.tp3pb.service.ClienteService;
import br.com.infnet.tp3pb.service.InstrumentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrumentos")
public class InstrumentoController {
    private final InstrumentoService instrumentoService;


    public InstrumentoController(InstrumentoService instrumentoService) {
        this.instrumentoService = instrumentoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        Instrumento instrumento = instrumentoService.buscarPorId(id);

        if(instrumento != null){
            return ResponseEntity.ok().body(instrumento);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Instrumento> listarInstrumentos(){
        return instrumentoService.listarInstrumentos();
    }

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody Instrumento instrumento){
        Instrumento criado = instrumentoService.cadastrarInstrumento
                (instrumento.getIdCliente(),
                instrumento.getTipoInstrumento(),
                instrumento.getMarca(),
                instrumento.getModelo(),
                instrumento.getNumeroSerie());

        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar( @PathVariable Long id,
                                     @Valid @RequestBody Instrumento entrada){
        instrumentoService.atualizar(id,entrada.getIdCliente(),entrada.getTipoInstrumento(),
                entrada.getMarca(),
                entrada.getModelo(),
                entrada.getNumeroSerie());

        return ResponseEntity.ok().body(entrada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        instrumentoService.remover(id);

        return ResponseEntity.noContent().build();
    }
}
