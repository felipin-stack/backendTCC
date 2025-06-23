package com.itb.inf2am.pizzaria.services;

import com.itb.inf2am.pizzaria.model.Doacao;

import java.util.List;

public interface DoacaoService {

    // Salva um cliente
    Doacao salvarDoacao(Doacao doacao);

    // Deleta um cliente pelo ID
    boolean deletarDoacao(Integer id);

    // Lista todos os clientes
    List<Doacao> listarTodosDoacao();

    // Lista um cliente por ID
    Doacao listarDoacaoPorId(Integer id);

    // Atualiza um cliente pelo ID
    Doacao atualizarDoacao(Doacao doacao, Integer id);
}
