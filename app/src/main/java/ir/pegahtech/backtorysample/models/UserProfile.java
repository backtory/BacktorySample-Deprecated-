package ir.pegahtech.backtorysample.models;

import java.util.List;

/**
 * Created by SirGozal on 6/1/2016.
 */
public class UserProfile {
    private UserBriefProfile userBriefProfile;
    private List<Long> scores;

    public UserBriefProfile getUserBriefProfile() {
        return userBriefProfile;
    }

    public void setUserBriefProfile(UserBriefProfile userBriefProfile) {
        this.userBriefProfile = userBriefProfile;
    }

    public List<Long> getScores() {
        return scores;
    }

    public void setScores(List<Long> scores) {
        this.scores = scores;
    }
}
