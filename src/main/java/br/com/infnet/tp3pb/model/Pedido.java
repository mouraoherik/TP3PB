package br.com.infnet.tp3pb.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class Pedido{
    private Long idPedido;
    private Long idCliente;
    private Long idInstrumento;
    private LocalDate dataEntrada;
    private LocalDate dataEntrega;
    @NotBlank(message = "Problema relatado e obrigatorio")
    private String problemaRelatado;
    private BigDecimal preco;
    private StatusPedido statusPedido;

}
