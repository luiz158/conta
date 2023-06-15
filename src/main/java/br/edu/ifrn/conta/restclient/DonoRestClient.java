package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.controller.DonoDTO;
import lombok.experimental.SuperBuilder;
import lombok.Data;

@Data
@SuperBuilder
public class DonoRestClient {

    private RestClientHelper<DonoDTO> entityRestHelper;

    public void deleteByDescricao(String descricao) {
        entityRestHelper.deleteByDescricao(descricao);
    }

    public DonoDTO findByDescricao(String descricao) {
        return this.entityRestHelper.getRestTemplate().getForObject(
                entityRestHelper.findByDescricaoUri(descricao), DonoDTO.class);
    }

    public Iterable<DonoDTO> findAll() {
        return this.entityRestHelper.findAll();
    }

    public DonoDTO save(DonoDTO entity) {
        return this.entityRestHelper.getRestTemplate().postForObject(
                entityRestHelper.saveUri(), entity, DonoDTO.class);
    }

}
