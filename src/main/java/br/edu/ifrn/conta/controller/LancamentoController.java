package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.Lancamento;
import br.edu.ifrn.conta.service.ContaPatrimonioService;
import br.edu.ifrn.conta.service.DonoService;
import br.edu.ifrn.conta.service.LancamentoService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service of Lancamento.
 */
@RestController
@RequestMapping("/lancamento")
public class LancamentoController extends CrudController<Lancamento, Long> {

    @Autowired
    private LancamentoService lancamentoService;
    @Autowired
    private DonoService donoService;
    @Autowired
    private ContaPatrimonioService contaPatrimonioService;

    @GetMapping("/saldo")
    public BigDecimal saldo(@RequestParam String dono, @RequestParam String contaPatrimonio) {
        return lancamentoService.saldo(donoService.findByDescricao(dono),
                contaPatrimonioService.findByDescricao(contaPatrimonio));
    }
}
