package br.com.infnet.tp3pb.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor

public class Cliente {
    private Long idCliente;
    @NotBlank(message = "Nome e obrigatorio")
    private String nome;
    @NotBlank(message = "Telefone e obrigatorio")
    private String telefone;
    @NotBlank(message = "E-mail e obrigatorio")
    private String email ;
    @NotBlank(message = "Endereco e obrigatorio")
    @Size(min = 5,message = "Endereco deve ter ao menos 5 caracteres")
    private String endereco;
}
