package br.com.infnet.tp3pb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class Orcamento {
    private Long idOrcamento;
    private Long idPedido;
    private BigDecimal valorTotal;
    private String descricao;
    private LocalDate data;
    private StatusOrcamento statusOrcamento;
    private LocalDate dataResposta;
}
