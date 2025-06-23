package com.itb.inf2am.pizzaria.controller;

import com.itb.inf2am.pizzaria.exceptions.BadRequest;
import com.itb.inf2am.pizzaria.model.Cliente;
import com.itb.inf2am.pizzaria.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class FuncionarioController {

    private final ClienteService clienteService;

    public FuncionarioController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodosClientes() {
        List<Cliente> clientes = clienteService.listarTodosClientes();
        return ResponseEntity.ok().body(clientes);
    }
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.salvarCliente(cliente);
        return ResponseEntity.ok().body(novoCliente);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable(value = "id") String id) {
        try{
            if(clienteService.deletarCliente(Integer.parseInt(id))) {
                return ResponseEntity.ok().body("Cliente com o id " + id + " excluído com sucesso");
            }
        }catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 15. ");
        }
        return ResponseEntity.ok().body("Não foi possível a exclusão do cliente com o id " + id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente, @PathVariable(value = "id") String id) {
        try{
            return ResponseEntity.ok().body(clienteService.atualizarCliente(cliente, Integer.parseInt(id)));

        }catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 15. ");
        }

    }

}




