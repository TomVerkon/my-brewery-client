package com.digits.mybreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// This sets up an Apache request factory
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    @Value("${max.total.connections}")
    private int maxTotalConnections;

    @Value("${max.default.per.route.connections}")
    private int maxDefaultPerRouteConnections;

    @Value("${connection.request.timeout}")
    private int connectionRequestTimeout;

    @Value("${connection.socket.timeout}")
    private int connectionSocketTimeout;

    public ClientHttpRequestFactory clientHttpRequestFactory() {
	PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
	connectionManager.setMaxTotal(maxTotalConnections);
	connectionManager.setDefaultMaxPerRoute(maxDefaultPerRouteConnections);

	RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
		.setSocketTimeout(connectionSocketTimeout).build();
	CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
		.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).setDefaultRequestConfig(requestConfig)
		.build();
	return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
	restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }

}
