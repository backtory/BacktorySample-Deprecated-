package ir.pegahtech.backtorysample.network;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Headers;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientErrorHandling;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import ir.pegahtech.backtorysample.managers.BacktoryConfig;
import ir.pegahtech.backtorysample.models.EventRequest;
import ir.pegahtech.backtorysample.models.GetTopLeaderboardResponse;
import ir.pegahtech.backtorysample.models.LoginResponse;
import ir.pegahtech.backtorysample.models.RegisterRequest;
import ir.pegahtech.backtorysample.models.RegisterResponse;

/**
 * Created by SirGozal on 2/13/2016.
 */

@Rest(rootUrl = BacktoryConfig.SERVER_URL, converters = {GsonHttpMessageConverter.class,
        FormHttpMessageConverter.class}, interceptors = BacktoryInterceptor.class,
        requestFactory = HttpComponentsClientHttpRequestFactory.class)
public interface LeaderboardServiceInterface extends RestClientErrorHandling {

    @Post(BacktoryConfig.SEND_EVENT)
    @Headers({@Header(name = BacktoryConfig.X_BACKTORY_GAME_ID_TITLE, value = BacktoryConfig.X_BACKTORY_GAME_ID)})
    ResponseEntity<LoginResponse> sendEvent(@Body EventRequest event);

    @Get(BacktoryConfig.GET_TOP + "/{leaderboardId}?count={count}")
    @Headers({@Header(name = BacktoryConfig.X_BACKTORY_GAME_ID_TITLE, value = BacktoryConfig.X_BACKTORY_GAME_ID)})
    ResponseEntity<GetTopLeaderboardResponse> getTop(@Path String leaderboardId, @Path int count);
}
