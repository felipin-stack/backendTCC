package com.itb.inf2am.pizzaria.services;

import com.itb.inf2am.pizzaria.model.Cliente;

import java.util.List;

public interface ClienteService {

    // Salva um cliente
    Cliente salvarCliente(Cliente cliente);

    // Deleta um cliente pelo ID
    boolean deletarCliente(Integer id);

    // Lista todos os clientes
    List<Cliente> listarTodosClientes();

    // Lista um cliente por ID
    Cliente listarClientePorId(Long id);

    // Atualiza um cliente pelo ID
    Cliente atualizarCliente(Cliente cliente, Integer id);
}
