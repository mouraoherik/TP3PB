package br.com.infnet.tp3pb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class Pedido{
    private Long idPedido;
    private Long idCliente;
    private Long idInstrumento;
    private Long idPedidoOriginal;
    private LocalDate dataEntrada;
    private String problemaRelatado;
    private LocalDate dataEntrega;
    private StatusPedido statusPedido;


    public boolean isRetorno(){
        return idPedidoOriginal != null;
    }
}
