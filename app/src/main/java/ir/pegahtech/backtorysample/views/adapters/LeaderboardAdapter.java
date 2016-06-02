package ir.pegahtech.backtorysample.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import ir.pegahtech.backtorysample.R;
import ir.pegahtech.backtorysample.models.UserProfile;

/**
 * Created by SirGozal on 6/2/2016.
 */

@EBean
public class LeaderboardAdapter extends BaseAdapter {
    private List<UserProfile> userProfileList = new ArrayList<>();

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return userProfileList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.view_leaderboard_item, null);
        UserProfile userProfile = userProfileList.get(position);
        TextView tvScore = (TextView) view.findViewById(R.id.textScoreLeaderboardItem);
        TextView tvName = (TextView) view.findViewById(R.id.textNameLeaderboardItem);
        String displayName =
                ((userProfile.getUserBriefProfile().getFirstName() != null) ? (userProfile.
                        getUserBriefProfile().getFirstName() + " ") : "") +
                        ((userProfile.getUserBriefProfile().getLastName() != null) ? userProfile.
                        getUserBriefProfile().getLastName() : "");
        tvName.setText(displayName);
        tvScore.setText(userProfile.getScores().get(0) + "");
        return view;
    }

    public List<UserProfile> getUserProfileList() {
        return userProfileList;
    }

    public void setUserProfileList(List<UserProfile> userProfileList) {
        this.userProfileList = userProfileList;
    }
}
