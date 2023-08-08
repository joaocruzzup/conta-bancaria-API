package com.catalisa.contaBancaria.model;

import com.catalisa.contaBancaria.enums.TipoServico;
import com.catalisa.contaBancaria.exception.OperacaoException;
import com.catalisa.contaBancaria.exception.ValorIncompativelException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bytecode.Throw;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String numeroConta;

    @Column(length = 10, nullable = false)
    private String agencia;

    @Column(length = 100, nullable = false)
    private String nomeUsuario;

    private BigDecimal saldo;

    private BigDecimal valorFornecido;

    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;


    public void calcularSaldoFinal() {
        if (tipoServico != TipoServico.SAQUE  && tipoServico != TipoServico.DEPOSITO){
            throw new OperacaoException("Tipo de operação inválida: escolha SAQUE ou DEPOSITO");
        } else if (tipoServico == TipoServico.SAQUE && !valorFornecido.equals(BigDecimal.ZERO) && saldo.compareTo(valorFornecido) >= 0) {
            saldo = saldo.subtract(valorFornecido);
        } else if (tipoServico == TipoServico.DEPOSITO && !valorFornecido.equals(BigDecimal.ZERO)) {
            saldo = saldo.add(valorFornecido);
        } else {
            throw new ValorIncompativelException("Valor incompatível para a operação: Digite um valor positivo");
        }

    }

}
