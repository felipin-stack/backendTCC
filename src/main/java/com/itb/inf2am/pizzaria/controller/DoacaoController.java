package com.itb.inf2am.pizzaria.controller;

import com.itb.inf2am.pizzaria.exceptions.BadRequest;
import com.itb.inf2am.pizzaria.model.Doacao;
import com.itb.inf2am.pizzaria.services.DoacaoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    private final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Doacao>> listarTodosDoacao() {
        List<Doacao> doadores = doacaoService.listarTodosDoacao();
        return ResponseEntity.ok().body(doadores);
    }

    @PostMapping
    public ResponseEntity<Doacao> criarDoacao(@RequestBody Doacao doacao) {
        Doacao novoDoacao = doacaoService.salvarDoacao(doacao);
        return ResponseEntity.ok().body(novoDoacao);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDoacao(@PathVariable(value = "id") String id) {
        try {
            if (doacaoService.deletarDoacao(Integer.parseInt(id))) {
                return ResponseEntity.ok().body("Doação com o id " + id + " excluída com sucesso");
            } else {
                return ResponseEntity.status(404).body("Doação com o id " + id + " não encontrada");
            }
        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 15.");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Doacao> atualizarDoacao(@RequestBody Doacao doacao, @PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(doacaoService.atualizarDoacao(doacao, Integer.parseInt(id)));

        } catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 15. ");
        }

    }

}