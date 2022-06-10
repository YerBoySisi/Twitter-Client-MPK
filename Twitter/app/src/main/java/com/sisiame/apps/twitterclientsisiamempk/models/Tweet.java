package com.sisiame.apps.twitterclientsisiamempk.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tweet {

    public String body, createdAt, comments, retweets, likes;
    public String[] mediaURLs = {null, null, null};
    public boolean isRetweeted, isLiked;
    public User user;

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {

        Tweet tweet = new Tweet();

        tweet.body = jsonObject.getString("full_text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
//      tweet.comments = String.valueOf(jsonObject.getInt("reply_count"));
        tweet.retweets = String.valueOf(jsonObject.getInt("retweet_count"));
        tweet.likes = String.valueOf(jsonObject.getInt("favorite_count"));
        tweet.isRetweeted = jsonObject.getBoolean("retweeted");
        tweet.isLiked = jsonObject.getBoolean("favorited");

        JSONObject entities;

        if(jsonObject.has("extended_entities")) {
            entities = jsonObject.getJSONObject("extended_entities");
        } else {
            entities = jsonObject.getJSONObject("entities");
        }

        if(entities.has("media")) {

            final int mediaArrayLen = entities.getJSONArray("media").length();
            int tracker = 0;

            while (tracker < mediaArrayLen) {

                tweet.mediaURLs[tracker] = entities.getJSONArray("media")
                        .getJSONObject(tracker)
                        .getString("media_url");

                tracker++;

            }

        }

        if(tweet.mediaURLs.length > 1) {
            System.out.println(Arrays.toString(tweet.mediaURLs));
        }

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
