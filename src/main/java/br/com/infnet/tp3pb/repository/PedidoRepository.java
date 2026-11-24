package br.com.infnet.tp3pb.repository;


import br.com.infnet.tp3pb.model.Pedido;
import br.com.infnet.tp3pb.model.StatusPedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;

@Repository
public class PedidoRepository extends CsvRepository<Pedido> {

    public PedidoRepository(@Value("${app.dados}") String pasta){
        super(Path.of(pasta,"pedidos.csv"),
                "idPedido;idCliente;idInstrumento;dataEntrada;dataEntrega;problemaRelatado;preco;statusPedido");
    }

    @Override
    protected Long id(Pedido p) {
        return p.getIdPedido();
    }

    @Override
    protected String[] paraCampos(Pedido p) {
        return new String[]{
                p.getIdPedido().toString(),p.getIdCliente().toString(),String.valueOf(p.getIdInstrumento()),
                        p.getDataEntrada().toString(), p.getDataEntrega() == null ? "" : p.getDataEntrega().toString(),
                p.getProblemaRelatado(),
                p.getPreco().toString(),p.getStatusPedido().toString()
        };
    }

    @Override
    protected Pedido deCampos(String[] campo) {
        return new Pedido(Long.parseLong(campo[0]),Long.parseLong(campo[1]),Long.parseLong(campo[2]),
                LocalDate.parse(campo[3]),campo[4].isBlank() ? null : LocalDate.parse(campo[4]),campo[5],new BigDecimal(campo[6]),
                StatusPedido.valueOf(campo[7]));
    }
}
