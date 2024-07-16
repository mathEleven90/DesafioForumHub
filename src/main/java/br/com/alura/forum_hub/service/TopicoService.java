package br.com.alura.forum_hub.service;

import br.com.alura.forum_hub.domain.Curso;
import br.com.alura.forum_hub.domain.Topico;
import br.com.alura.forum_hub.domain.Usuario;
import br.com.alura.forum_hub.dto.DadosCadastroTopico;
import br.com.alura.forum_hub.dto.DadosTopicoResponse;
import br.com.alura.forum_hub.infra.exception.UsuarioSemPermissaoException;
import br.com.alura.forum_hub.infra.exception.ValidacaoException;
import br.com.alura.forum_hub.infra.security.JWTTokenService;
import br.com.alura.forum_hub.repository.CursoRepository;
import br.com.alura.forum_hub.repository.TopicoRepository;
import br.com.alura.forum_hub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JWTTokenService tokenService;

    @Transactional
    public DadosTopicoResponse salvar(DadosCadastroTopico dados, String token) {
        validarDadosDoTopico(dados);

        Usuario autor = pegarAutorPeloToken(token);
        Curso curso = cursoRepository.getReferenceById(dados.curso_id());
        Topico topico = new Topico(dados.titulo(), dados.mensagem(), curso, autor);

        topico = topicoRepository.save(topico);

        return new DadosTopicoResponse(topico);
    }

    public DadosTopicoResponse buscar(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Tópico não encontrado"));
        return new DadosTopicoResponse(topico);
    }

    public List<DadosTopicoResponse> listar() {
        List<Topico> topicos = topicoRepository.findAllByOrderByDataCriacaoDesc();
        return topicos.stream()
                .map(DadosTopicoResponse::new)
                .toList();
    }

    @Transactional
    public DadosTopicoResponse atualizar(Long id, DadosCadastroTopico dados, String token) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Tópico não encontrado"));
        Usuario autor = pegarAutorPeloToken(token);

        if (!autor.equals(topico.getAutor())) {
            throw new UsuarioSemPermissaoException("Usuário sem permissão");
        }

        validarDadosDoTopico(dados);

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        Curso curso = cursoRepository.getReferenceById(dados.curso_id());
        topico.setCurso(curso);

        return new DadosTopicoResponse(topico);
    }

    @Transactional
    public void remover(Long id, String token) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Tópico não encontrado"));
        Usuario autor = pegarAutorPeloToken(token);

        if (!autor.equals(topico.getAutor())) {
            throw new UsuarioSemPermissaoException("Usuário sem permissão");
        }

        topicoRepository.deleteById(id);
    }

    private void validarDadosDoTopico(DadosCadastroTopico dados) {
        if (!cursoRepository.existsById(dados.curso_id())) {
            throw new ValidacaoException("Curso não encontrado");
        }
        boolean jaTemEsseTitulo = topicoRepository.existsByTitulo(dados.titulo());
        boolean jaTemEssaMensagem = topicoRepository.existsByMensagem(dados.mensagem());

        if (jaTemEsseTitulo || jaTemEssaMensagem) {
            throw new ValidacaoException("Já existe um tópico com esse título ou mensagem");
        }
    }

    public Usuario pegarAutorPeloToken(String token) {
        String email = tokenService.getSubject(token.replace("Bearer ", "").trim());
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + email));
    }
}
