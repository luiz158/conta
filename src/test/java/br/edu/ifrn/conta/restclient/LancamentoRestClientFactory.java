package br.edu.ifrn.conta.restclient;

import br.edu.ifrn.conta.domain.Lancamento;
import lombok.Builder;
import lombok.Data;

import org.springframework.web.client.RestTemplate;

@Builder
@Data
public class LancamentoRestClientFactory {

    private String protocol;
    
    private String servername;
    
    private int port;

    private RestTemplate restTemplate;

    public LancamentoRestClient lancamentoRestClient() {
        return LancamentoRestClient.builder()
                .entityRestHelper(RestClientHelper.<Lancamento>builder()
                    .endpoint("lancamento")
                    .protocol(protocol)
                    .servername(servername)
                    .port(port)
                    .restTemplate(restTemplate)
                    .build())
                .build();
    }

}
