package com.sisiame.apps.twitterclientsisiamempk.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    public String name, screenName, profileImageUrl;

    public static User fromJson(JSONObject jsonObject) {

        User user = new User();

        try {

            user.name = jsonObject.getString("name");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("profile_image_url");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;

    }

}
