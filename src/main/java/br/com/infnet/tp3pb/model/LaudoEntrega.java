package br.com.infnet.tp3pb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter

public class LaudoEntrega {
    private Long idLaudo;
    private Long idPedido;
    private LocalDate data;
    private String descricaoCondicao;
}
