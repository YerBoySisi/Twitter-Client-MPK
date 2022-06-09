package com.sisiame.apps.twitterclientsisiamempk.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

    public String body, createdAt, comments, retweets, likes;
    public boolean isRetweeted, isLiked;
    public User user;

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {

        Tweet tweet = new Tweet();

        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
//      tweet.comments = String.valueOf(jsonObject.getInt("reply_count"));
        tweet.retweets = String.valueOf(jsonObject.getInt("retweet_count"));
        tweet.likes = String.valueOf(jsonObject.getInt("favorite_count"));
        tweet.isRetweeted = jsonObject.getBoolean("retweeted");
        tweet.isLiked = jsonObject.getBoolean("favorited");

        return tweet;

    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {

        List<Tweet> tweets = new ArrayList<>();

        for(int t = 0; t < jsonArray.length(); t++) {
            tweets.add(fromJson(jsonArray.getJSONObject(t)));
        }

        return tweets;

    }

}
