package ir.pegahtech.backtorysample.network;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.api.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by SirGozal on 1/23/2016.
 */

@EBean
public class BacktoryErrorHandler implements RestErrorHandler {
    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        e.printStackTrace();
        if (e instanceof HttpClientErrorException) {
            throw e;
        } else {
            throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
