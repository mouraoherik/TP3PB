package br.com.infnet.tp3pb.model;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Tipo e obrigatorio")
    private String tipoInstrumento;
    @NotBlank(message = "Tipo e obrigatorio")
    private String marca;
    @NotBlank(message = "Marca e obrigatorio")
    private String Modelo;
    @NotBlank(message = "Numero de serie e obrigatorio")
    private String numeroSerie;

}
