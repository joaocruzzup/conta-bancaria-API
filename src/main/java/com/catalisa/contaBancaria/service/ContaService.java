package com.catalisa.contaBancaria.service;

import com.catalisa.contaBancaria.dto.ContaCriarDTO;
import com.catalisa.contaBancaria.dto.TransferenciaDTO;
import com.catalisa.contaBancaria.enums.TipoServico;
import com.catalisa.contaBancaria.exception.ContaNaoEncontradaException;
import com.catalisa.contaBancaria.model.ContaModel;
import com.catalisa.contaBancaria.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    public List<ContaModel> buscarTodos(){
        return contaRepository.findAll();
    }

    public Optional<ContaModel> buscarPorId(Long id){
        return contaRepository.findById(id);
    }

    public ContaCriarDTO cadastrar(ContaCriarDTO contaDTO) {
        System.out.println(contaDTO.getAgencia());
        ContaModel savedConta = contaRepository.save(contaDTO.toContaModel());
        return new ContaCriarDTO(savedConta);
    }

    public TransferenciaDTO atualizarValor(Long id, TransferenciaDTO contaValorFornecidoDTO) {
        Optional<ContaModel> contaModelOptional = contaRepository.findById(id);
        if (contaModelOptional.isPresent()) {
            ContaModel contaModel = contaModelOptional.get();
            BigDecimal valorFornecidoDTO = contaValorFornecidoDTO.getValorFornecido();
            TipoServico tipoServico = contaValorFornecidoDTO.getTipoServico();

            contaModel.setValorFornecido(valorFornecidoDTO);
            contaModel.setTipoServico(tipoServico);

            contaModel.calcularSaldoFinal();

            contaRepository.save(contaModel);

            return contaValorFornecidoDTO;
        } else {
            throw new ContaNaoEncontradaException("Conta com ID " + id + " não encontrada.");
        }
    }

    public void deletar(Long id) {
        verificarId(id);
        contaRepository.deleteById(id);
    }

    public void verificarId(Long id){
        Optional<ContaModel> contaOptional = contaRepository.findById(id);
        if (contaOptional.isEmpty()) {
            throw new ContaNaoEncontradaException("Conta com ID " + id + " não encontrada.");
        }
    }
}
