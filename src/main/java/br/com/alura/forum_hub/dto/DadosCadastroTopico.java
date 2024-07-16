package br.com.alura.forum_hub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
        @NotBlank(message = "titulo obrigatório")
        String titulo,
        @NotBlank(message = "mensagem obrigatória")
        String mensagem,
        @NotNull
        Long curso_id) {

}
