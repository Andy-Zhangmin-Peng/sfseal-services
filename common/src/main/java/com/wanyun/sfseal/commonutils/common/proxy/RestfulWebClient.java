/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.proxy;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This web client is used to send request to other services.
 *
 * @author WanYun
 */
@Component("wechatRestfulWebClient")
public class RestfulWebClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(RestfulWebClient.class);

    @Getter
    @Setter
    private WebClient webClient;

    /**
     * Constructor
     *
     * @param baseUrl
     * @param headersConsumer
     */
    public RestfulWebClient(String baseUrl, Consumer<HttpHeaders> headersConsumer) {
        this.setWebClient(WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(headersConsumer)
                .filter(logRequest())
                .build());
    }

    /**
     * Used to record logs
     *
     * @return
     */
    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            LOGGER.info("Request method: [{}], Request url: [{}]", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> LOGGER.info("Header name:[{}], Header value:[{}]", name, value)));
            return next.exchange(clientRequest);
        };
    }

    /**
     * Used to send get request
     *
     * @param uriFunction
     * @param responseClass
     * @param <R>
     * @return
     */
    public <R> Mono<R> getRequest(Function<UriBuilder, URI> uriFunction, Class<R> responseClass) {
        return this.getWebClient().get()
                .uri(uriFunction)
                .retrieve()
                .bodyToMono(responseClass);
    }

    /**
     * Used to send post request
     *
     * @param uriFunction
     * @param requestBodyInserter
     * @param responseClass
     * @param <R>
     * @return
     */
    public <R> Mono<R> postRequest(Function<UriBuilder, URI> uriFunction, BodyInserter<?, ? super ClientHttpRequest> requestBodyInserter, Class<R> responseClass) {
        return webClient.post()
                .uri(uriFunction)
                .body(requestBodyInserter)
                .retrieve()
                .bodyToMono(responseClass);
    }

    /**
     * Used to send put request
     *
     * @param uriFunction
     * @param requestBodyInserter
     * @param responseClass
     * @param <R>
     * @return
     */
    public <R> Mono<R> putRequest(Function<UriBuilder, URI> uriFunction, BodyInserter<?, ? super ClientHttpRequest> requestBodyInserter, Class<R> responseClass) {
        return webClient.put()
                .uri(uriFunction)
                .body(requestBodyInserter)
                .retrieve()
                .bodyToMono(responseClass);
    }

    /**
     * Used to send delete request
     *
     * @param uriFunction
     * @param responseClass
     * @param <R>
     * @return
     */
    public <R> Mono<R> deleteRequest(Function<UriBuilder, URI> uriFunction, Class<R> responseClass) {
        return webClient.delete()
                .uri(uriFunction)
                .retrieve()
                .bodyToMono(responseClass);
    }
}
