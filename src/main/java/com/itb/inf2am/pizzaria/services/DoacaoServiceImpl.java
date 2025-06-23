package com.itb.inf2am.pizzaria.services;

import com.itb.inf2am.pizzaria.exceptions.BadRequest;
import com.itb.inf2am.pizzaria.exceptions.NotFound;
import com.itb.inf2am.pizzaria.model.Doacao;
import com.itb.inf2am.pizzaria.repository.DoacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoServiceImpl implements DoacaoService {

    private final DoacaoRepository doacaoRepository;

    public DoacaoServiceImpl(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }


    @Override
    @Transactional
    public Doacao salvarDoacao(Doacao doacao) {
        if (!doacao.validarDoacao()) {
            throw new BadRequest(doacao.getMessage());
        }
        return doacaoRepository.save(doacao);
    }


    @Transactional
    public boolean deletarDoacao(Integer id) { // Alterado para Long
        if (doacaoRepository.existsById(id)) {
            doacaoRepository.deleteById(id);
            return true;
        } else {
            throw new NotFound("Cliente não encontrado com o id " + id); // Corrigido para "Cliente"
        }
    }

    @Override
    public List<Doacao> listarTodosDoacao() {
        return doacaoRepository.findAll();
    }

    public Doacao listarDoacaoPorId(Long id) {
        return null;
    }

    public Doacao listarDoacaoPorId(Integer id) { // Alterado para Long
        return doacaoRepository.findById(id)
                .orElseThrow(() -> new NotFound("Cliente não encontrado com o id " + id)); // Melhor tratamento de exceção
    }

    @Override
    @Transactional
    public Doacao atualizarDoacao(Doacao doacao, Integer id) {
        try {
            if(!doacao.validarDoacao()){
                throw new BadRequest(doacao.getMessage());
            }
            Doacao doacaoBd = doacaoRepository.findById(id).get();
            doacaoBd.setNome(doacao.getNome());
            doacaoBd.setTitulo(doacao.getTitulo());
            doacaoBd.setAutor(doacao.getAutor());
            doacaoBd.setDescricao(doacao.getDescricao());
            return doacaoRepository.save(doacaoBd); // quando é o mesmo objeto o "save" atualiza (update)
        } catch (Exception e){
            throw new NotFound("Doação não encontrada com o id " + id);
        }
    }


}