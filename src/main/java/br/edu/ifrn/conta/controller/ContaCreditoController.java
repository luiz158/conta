package br.edu.ifrn.conta.controller;

import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.service.CategoriaService;
import br.edu.ifrn.conta.service.ContaCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service of conta credito.
 */
@RestController
@RequestMapping("/contaCredito")
public class ContaCreditoController extends CrudController<ContaCreditoDTO, ContaCredito, Long> {

    @Autowired
    private ContaCreditoService contaCreditoService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/findByDescricao")
    public ContaCreditoDTO findByDescricao(@RequestParam String descricao) {
        return toDTOCheckNull(contaCreditoService.findByDescricao(descricao));
    }

    @Override
    protected ContaCredito toEntity(ContaCreditoDTO dto) {
        return ContaCredito.builder()
            .descricao(dto.getDescricao())
            .categoria(categoriaService.findByDescricao(dto.getCategoria().getDescricao()))
            .build();
    }

    @Override
    protected ContaCreditoDTO toDTO(ContaCredito entity) {
        return ContaCreditoDTO.builder()
            .descricao(entity.getDescricao())
            .categoria(CategoriaDTO.builder().descricao(entity.getCategoria().getDescricao()).build())
            .build();
    }
}
