package guru.springframework.msscbreweryclient.web.config;

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

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer connectionRequestTimeout;
    private final Integer socketTimout;
    private final Integer maxTotalConnections;
    private final Integer defaultMaxTotalConnections;

    public BlockingRestTemplateCustomizer(@Value("${sfg.connectionrequesttimeout}") Integer connectionRequestTimeout,
                                          @Value("${sfg.sockettimeout}") Integer socketTimout,
                                          @Value("${sfg.maxtotalconnections}") Integer maxTotalConnections,
                                          @Value("${sfg.defaultmaxtotalconnections}") Integer defaultMaxTotalConnections) {
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.socketTimout = socketTimout;
        this.maxTotalConnections = maxTotalConnections;
        this.defaultMaxTotalConnections = defaultMaxTotalConnections;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        RequestConfig requestConfig = RequestConfig.custom()
                                        .setConnectionRequestTimeout(connectionRequestTimeout)
                                        .setSocketTimeout(socketTimout)
                                        .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(defaultMaxTotalConnections);

        CloseableHttpClient httpClient = HttpClients.custom()
                                            .setDefaultRequestConfig(requestConfig)
                                            .setConnectionManager(connectionManager)
                                            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                                            .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);

    }
}
