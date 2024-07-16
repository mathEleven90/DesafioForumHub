package br.com.alura.forum_hub.dto;

import br.com.alura.forum_hub.domain.Topico;

import java.time.LocalDateTime;

public record DadosTopicoResponse(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String status,
        String nomeCurso,
        String nomeAutor) {

    public DadosTopicoResponse(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getCurso().getNome(),
                topico.getAutor().getNome());
    }
}