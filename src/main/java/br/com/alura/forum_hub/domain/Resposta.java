package br.com.alura.forum_hub.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @ManyToOne
    private Topico topico;

    private LocalDateTime dataCriacao;

    @ManyToOne
    private Usuario autor;
    private Boolean solucao;

}