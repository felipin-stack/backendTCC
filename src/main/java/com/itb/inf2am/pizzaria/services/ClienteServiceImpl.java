package com.itb.inf2am.pizzaria.services;

import com.itb.inf2am.pizzaria.exceptions.BadRequest;
import com.itb.inf2am.pizzaria.exceptions.NotFound;
import com.itb.inf2am.pizzaria.model.Cliente;
import com.itb.inf2am.pizzaria.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        if (!cliente.validarCliente()) {
            throw new BadRequest(cliente.getMessage());
        }
        return clienteRepository.save(cliente);
    }


    @Transactional
    public boolean deletarCliente(Integer id) { // Alterado para Long
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        } else {
            throw new NotFound("Cliente não encontrado com o id " + id); // Corrigido para "Cliente"
        }
    }

    @Override
    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente listarClientePorId(Long id) {
        return null;
    }

    public Cliente listarClientePorId(Integer id) { // Alterado para Long
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFound("Cliente não encontrado com o id " + id)); // Melhor tratamento de exceção
    }

    @Override
    @Transactional
    public Cliente atualizarCliente(Cliente cliente, Integer id) {
        try {
            if(!cliente.validarCliente()){
                throw new BadRequest(cliente.getMessage());
            }
            Cliente clienteBd = clienteRepository.findById(id).get();
            clienteBd.setNome(cliente.getNome());
            clienteBd.setEmail(cliente.getEmail());
            clienteBd.setSenha(cliente.getSenha());
            clienteBd.setCep(cliente.getCep());
            clienteBd.setTelefone(cliente.getTelefone());
            return clienteRepository.save(clienteBd); // quando é o mesmo objeto o "save" atualiza (update)
        } catch (Exception e){
            throw new NotFound("Cliente não encontrada com o id " + id);
        }
    }


}
