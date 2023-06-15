package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.ContaDebito;
import br.edu.ifrn.conta.persistence.CategoriaFactory;
import br.edu.ifrn.conta.persistence.ContaDebitoFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaDebitoRestClientIT {

    private final String protocol = "http";
    private final String servername = "localhost";

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    
    private CategoriaRestClientFactory categoriaRestClientFactory() {
        return CategoriaRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .build();
    }

    private ContaDebitoRestClient contaDebitoRestClient() {
        return ContaDebitoRestClientFactory.builder()
                .protocol(protocol)
                .servername(servername)
                .port(port)
                .restTemplate(testRestTemplate.getRestTemplate())
                .categoriaRestClientFactory(categoriaRestClientFactory())
                .build()
                .contaDebitoRestClient();
    }

    @Test
    public void salvarUm() {        
        // executa a operacao a ser testada
        String descricao = ContaDebitoFactory.GASOLINA;
        String categoriaDescricao = CategoriaFactory.TRANSPORTE;
        
        ContaDebito estagio = ContaDebito.builder()
                .descricao(descricao)
                .categoria(this.categoriaRestClientFactory().categoria(categoriaDescricao))
                .build();

        // verifica a operacao save
        assertThat(contaDebitoRestClient().save(estagio))
        	.isNotNull();

        // verifica a operacao findByDescricao
        assertThat(contaDebitoRestClient().findByDescricao(descricao).getDescricao())
                .isEqualTo(estagio.getDescricao());
        
        // verifica a operacao findAll
        assertThat(contaDebitoRestClient().findAll())
                .isNotEmpty();        
    }

}
