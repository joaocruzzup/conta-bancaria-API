package com.catalisa.contaBancaria.dto;

import com.catalisa.contaBancaria.enums.TipoServico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TransferenciaDTO implements Serializable {

    @NotEmpty
    private BigDecimal valorFornecido;

    private TipoServico tipoServico;

}
