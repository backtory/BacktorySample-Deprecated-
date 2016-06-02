package ir.pegahtech.backtorysample.models;

import java.util.List;

/**
 * Created by SirGozal on 6/1/2016.
 */
public class GetTopLeaderboardResponse {
    private List<UserProfile> usersProfile;
    private String message;

    public List<UserProfile> getUsersProfile() {
        return usersProfile;
    }

    public void setUsersProfile(List<UserProfile> usersProfile) {
        this.usersProfile = usersProfile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
