package br.com.infnet.tp3pb.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor


public class Instrumento {
    private Long idInstrumento;
    private Long idCliente;
    private String tipoInstrumento;
    private String marca;
    private String Modelo;
    private String numeroSerie;

}
