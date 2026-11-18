package br.com.infnet.tp3pb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class Garantia {
    private Long idGarantia;
    private Long idPedido;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int prazoDias;
    private String escopo;
}
