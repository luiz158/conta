package br.edu.ifrn.conta.controller;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * DTO interface.
 */
@SuperBuilder
@Data
@ToString
@NoArgsConstructor
public class ContaDTO implements Serializable {
    private String descricao;
    private CategoriaDTO categoria;
}
