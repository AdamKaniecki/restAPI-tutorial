//package pl.zajavka.infrastructure.configuration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.reactive.ReactorClientHttpConnector;
//import org.springframework.http.codec.json.Jackson2JsonEncoder;
//
//
//import java.net.http.HttpClient;
//import java.time.Duration;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class WebClientConfiguration {
//
//    public static final String PET_STORE_URL = "https://petstore3.swagger.io/api/v3/";
//    public static final int TIMEOUT = 5000;
//
//    @Bean
//    public WebClient webClient(final ObjectMapper objectMapper) {
//        final var httpClient = HttpClient.create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
//                .responseTimeout(Duration.ofMillis(TIMEOUT))
//                .doOnConnected(conn ->
//                        conn.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS))
//                                .addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS)));
//
//        final var exchangeStrategies = ExchangeStrategies.builder()
//                .codecs(configurer -> {
//                    configurer
//                            .defaultCodecs()
//                            .jackson2JsonEncoder(
//                                    new Jackson2JsonEncoder(
//                                            objectMapper, MediaType.APPLICATION_JSON
//                                    )
//                            );
//                    configurer
//                            .defaultCodecs()
//                            .jackson2JsonEncoder(
//                                    new Jackson2JsonEncoder(
//                                            objectMapper, MediaType.APPLICATION_JSON
//                                    )
//                            );
//
//                }).build();
//        return WebClient.builder()
//                .baseUrl(PET_STORE_URL)
//                .exchangeStrategies(exchangeStrategies)
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .build();
//
//    }
//}
