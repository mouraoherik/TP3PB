package br.com.infnet.tp3pb.repository;

import br.com.infnet.tp3pb.model.Instrumento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;

@Repository
public class InstrumentoRepository extends CsvRepository<Instrumento> {

    public InstrumentoRepository (@Value("${app.dados}") String pasta) {
        super(Path.of(pasta, "instrumentos.csv"),
                "idInstrumento;idCliente;tipoInstrumento;marca;modelo;numeroSerie");
    }

    @Override
    protected Long id(Instrumento i) {
        return i.getIdInstrumento();
    }

    @Override
    protected String[] paraCampos(Instrumento i) {
        return new String[]{
                i.getIdInstrumento().toString(),i.getIdCliente().toString(),
                i.getTipoInstrumento(),i.getMarca(), i.getModelo(), i.getNumeroSerie()
        };
    }


    @Override
    protected Instrumento deCampos(String[] campo) {
        return new Instrumento (Long.parseLong(campo[0]),Long.parseLong(campo[1]),
                campo[2],
                campo[3],
                campo[4],
                campo[5]);
    }
}
