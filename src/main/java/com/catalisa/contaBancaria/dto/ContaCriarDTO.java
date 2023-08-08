package com.catalisa.contaBancaria.dto;

import com.catalisa.contaBancaria.model.ContaModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContaCriarDTO implements Serializable {

    private String numeroConta;

    private String agencia;

    private String nomeUsuario;

    private BigDecimal saldo;

    private BigDecimal valorFornecido;

    public ContaModel toContaModel() {
        ContaModel contaModel = new ContaModel();
        if (saldo == null) { saldo = BigDecimal.ZERO; }
        if (valorFornecido == null) { valorFornecido = BigDecimal.ZERO; }
        contaModel.setNumeroConta(numeroConta);
        contaModel.setAgencia(agencia);
        contaModel.setNomeUsuario(nomeUsuario);
        contaModel.setSaldo(saldo);
        contaModel.setValorFornecido(valorFornecido);
        return contaModel;
    }

    public ContaCriarDTO(ContaModel contaModel) {
        this.numeroConta = contaModel.getNumeroConta();
        this.agencia = contaModel.getAgencia();
        this.nomeUsuario = contaModel.getNomeUsuario();
        this.saldo = contaModel.getSaldo();
        this.valorFornecido = contaModel.getValorFornecido();
    }
}
