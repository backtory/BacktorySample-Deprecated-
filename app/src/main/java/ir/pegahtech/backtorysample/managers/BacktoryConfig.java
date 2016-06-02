package ir.pegahtech.backtorysample.managers;

/**
 * Created by SirGozal on 6/1/2016.
 */
public class BacktoryConfig {
    public static final String SERVER_URL = "http://api.backtory.com";
    public static final String LOGIN_PATH = "/auth/login";
    public static final String REGISTER_PATH = "/auth/users";
    public static final String SEND_EVENT = "/game/events";
    public static final String GET_TOP = "/game/leaderboards/top";

    // Headers
    public static final String X_BACKTORY_AUTHENTICATION_ID_TITLE = "X-Backtory-Authentication-Id";
    public static final String X_BACKTORY_AUTHENTICATION_KEY_TITLE = "X-Backtory-Authentication-Key";
    public static final String X_BACKTORY_GAME_ID_TITLE = "X-Backtory-Game-Id";
    public static final String AUTHORIZATION_TITLE = "Authorization";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    // Keys
    public static final String X_BACKTORY_AUTHENTICATION_ID = "5738186ae4b0179857153b63";
    public static final String X_BACKTORY_AUTHENTICATION_KEY = "5738186ae4b09a52993fe413";
    public static final String X_BACKTORY_GAME_ID = "5738186ae4b0179857153b67";

    // Ids
    public static final String LEADERBOARD_ID = "574f2632e4b0a7028dfea4fa";
    public static final String EVENT_NAME = "ScoreEvent";
    public static final String EVENT_FIELD = "field1";

}
