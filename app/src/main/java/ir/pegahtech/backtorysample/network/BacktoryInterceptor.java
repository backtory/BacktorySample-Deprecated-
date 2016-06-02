package ir.pegahtech.backtorysample.network;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import ir.pegahtech.backtorysample.managers.BacktoryConfig;
import ir.pegahtech.backtorysample.utils.Utils;

/**
 * Created by SirGozal on 2/18/2016.
 */

@EBean
public class BacktoryInterceptor implements ClientHttpRequestInterceptor {
    @Bean
    Utils utils;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (!utils.loadString(BacktoryConfig.ACCESS_TOKEN).equals(""))
            request.getHeaders().set(BacktoryConfig.AUTHORIZATION_TITLE, "bearer " + utils.loadString(BacktoryConfig.ACCESS_TOKEN));
        return execution.execute(request, body);
    }
}
