package com.catalisa.contaBancaria.controller;

import com.catalisa.contaBancaria.dto.ContaCriarDTO;
import com.catalisa.contaBancaria.dto.TransferenciaDTO;
import com.catalisa.contaBancaria.model.ContaModel;
import com.catalisa.contaBancaria.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContaController {

    @Autowired
    ContaService contaService;

    @GetMapping(path = "/contas")
    @Operation(summary = "Busca todas as contas", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"))
    public List<ContaModel> buscaTodasContas(){
        return contaService.buscarTodos();
    }

    @GetMapping(path = "/contas/{id}")
    @Operation(summary = "Busca conta específica", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Busca criado com sucesso"))
    public Optional<ContaModel> buscaContaPorId(@PathVariable Long id){
        return contaService.buscarPorId(id);
    }

    @PostMapping(path = "/contas")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma nova conta", method = "POST")
    @ApiResponses(value = @ApiResponse(responseCode = "201", description = "Conta criada com sucesso"))
    public ContaCriarDTO cadastrarNovoLivro(@RequestBody ContaCriarDTO contaDTO){
        return contaService.cadastrar(contaDTO);
    }

    @PutMapping(path = "/contas/{id}/transferencia")
    @Operation(summary = "Atualiza o valor atual", method = "PUT")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Valor atualizado com sucesso"))
    public TransferenciaDTO atualizarValor(@PathVariable Long id, @RequestBody TransferenciaDTO contaValorFornecidoDTO){
        return contaService.atualizarValor(id, contaValorFornecidoDTO);
    }

    @DeleteMapping(path = "/contas/{id}")
    @Operation(summary = "Deleta uma conta ", method = "DELETE")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Conta deletada com sucesso"))
    public void deletarConta(@PathVariable Long id){
        contaService.deletar(id);
    }
}
