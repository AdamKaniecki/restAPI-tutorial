package pl.zajavka.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import pl.zajavka.infrastructure.petstore.ApiClient;
import pl.zajavka.infrastructure.petstore.api.PetApi;
import reactor.netty.http.client.HttpClient;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {

//    dla każdego jednego API jest skonfigurowany oddzielnie jeden WebClient, gdyby nasza aplikacja komunikowała się z
//    dwiema aplikacjami to musielibyśmy napisać dwie konfiguracje WebClienta
    @Value("${api.petStore.url}") //adres jaki ma to API
    private String petStoreUrl;

    @Bean //APIClient będzie dopisany do Springa jako bean i będzie zastępował curla czyli będzie wywoływał endpointy
    public ApiClient petStoreApiClient(final ObjectMapper objectMapper) {


//   zamiana zarejestrowanego beana Object Mapper na format obiektów json i odwrotnie
        final var exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer
                            .defaultCodecs()
                            .jackson2JsonEncoder(
                                    new Jackson2JsonEncoder(
                                            objectMapper, MediaType.APPLICATION_JSON
                                    )
                            );
                    configurer
                            .defaultCodecs()
                            .jackson2JsonEncoder(
                                    new Jackson2JsonEncoder(
                                            objectMapper, MediaType.APPLICATION_JSON
                                    )
                            );

                }).build();

//        tworzenie instancji tego Web Clienta
        final var webClient = WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .build();
//        tworzenie instancji API Client, bo Spring nie widzi wygenerowanych klas i podać WebClienta do środka
        ApiClient apiClient = new ApiClient(webClient);
        apiClient.setBasePath(petStoreUrl); //przekazanie basePath

return apiClient;
    }


    @Bean
    public PetApi petApi (final ObjectMapper objectMapper){
        return new PetApi(petStoreApiClient(objectMapper));
    }

}
