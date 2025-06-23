package com.itb.inf2am.pizzaria.controller;


import com.itb.inf2am.pizzaria.dto.LoginRequestDTO;
import com.itb.inf2am.pizzaria.dto.RegisterRequestDTO;
import com.itb.inf2am.pizzaria.dto.ResponseDTO;
import com.itb.inf2am.pizzaria.infra.security.TokenService;
import com.itb.inf2am.pizzaria.model.Cliente;
import com.itb.inf2am.pizzaria.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClienteRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body ){
       Cliente cliente =this.repository.findByEmail(body.email()).orElseThrow(()-> new RuntimeException("User not found"));
       if(passwordEncoder.matches(cliente.getSenha(), body.senha())) {
           String token = this.tokenService.generateToken(cliente);
           return ResponseEntity.ok(new ResponseDTO(cliente.getNome(), token));
       }
       return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body ){
        Optional<Cliente> cliente =this.repository.findByEmail(body.email());

        if(cliente.isEmpty()){
            Cliente newCliente = new Cliente();
            newCliente.setSenha(passwordEncoder.encode(body.senha()));
            newCliente.setEmail(body.email());
            newCliente.setNome(body.nome());
            newCliente.setCep(body.cep());
            newCliente.setTelefone(body.telefone());
            this.repository.save(newCliente);

                String token = this.tokenService.generateToken(newCliente);
                return ResponseEntity.ok(new ResponseDTO(newCliente.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
