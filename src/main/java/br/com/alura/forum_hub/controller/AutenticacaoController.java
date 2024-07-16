package br.com.alura.forum_hub.controller;


import br.com.alura.forum_hub.infra.security.DadosTokenJWT;
import br.com.alura.forum_hub.infra.security.JWTTokenService;
import br.com.alura.forum_hub.domain.Usuario;
import br.com.alura.forum_hub.dto.DadosAutenticacao;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTTokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> login(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            Authentication authentication = manager.authenticate(authenticationToken);

            var usuario = (Usuario) authentication.getPrincipal();
            var token = tokenService.gerarToken(usuario);

            return ResponseEntity.ok(new DadosTokenJWT(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Usuário inválido ou token expiradoo!");
        }
    }
}


