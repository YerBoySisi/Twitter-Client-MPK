package com.sisiame.apps.twitterclientsisiamempk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sisiame.apps.twitterclientsisiamempk.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // inflate layout for each row
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // bind values based on element position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage, ivMedia, ivMedia2, ivMedia3;
        TextView tvBody, tvName, tvHandle, tvCreatedAt, tvComments, tvRetweets, tvLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            ivMedia = itemView.findViewById(R.id.ivMedia);
            ivMedia2 = itemView.findViewById(R.id.ivMedia2);
            ivMedia3 = itemView.findViewById(R.id.ivMedia3);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvName = itemView.findViewById(R.id.tvName);
            tvHandle = itemView.findViewById(R.id.tvHandle);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvComments = itemView.findViewById(R.id.tvComments);
            tvRetweets = itemView.findViewById(R.id.tvRetweets);
            tvLikes = itemView.findViewById(R.id.tvLikes);
        }


        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvName.setText(tweet.user.name);
            tvHandle.setText(context.getString(R.string.twitter_handle, "@", tweet.user.screenName));
            tvCreatedAt.setText(RelativeDate.getRelativeTimeAgo(tweet.createdAt));

            Glide
                .with(context)
                .load(tweet.user.profileImageUrl)
                .circleCrop()
                .into(ivProfileImage);

            loadAllMedia(tweet);

//          tvComments.setText(tweet.comments);
            tvRetweets.setText(tweet.retweets);
            tvLikes.setText(tweet.likes);
        }

        private void loadAllMedia(Tweet tweet) {

            if(tweet.mediaURLs[0] != null) {

                if(tweet.mediaURLs[0].contains(".gif")) {

                    Glide
                        .with(context)
                        .asGif()
                        .load(tweet.mediaURLs[0])
                        .fitCenter()
                        .into(ivMedia);

                    return;
                }

                if (tweet.mediaURLs[1] == null) {

                    Glide
                        .with(context)
                        .load(tweet.mediaURLs[0])
                        .fitCenter()
                        .into(ivMedia);

                } else {

                    Glide
                        .with(context)
                        .load(tweet.mediaURLs[0])
                        .centerCrop()
                        .into(ivMedia);

                    Glide
                        .with(context)
                        .load(tweet.mediaURLs[1])
                        .centerCrop()
                        .into(ivMedia2);

                    ((ViewGroup.MarginLayoutParams)ivMedia.getLayoutParams()).setMarginEnd(2);
                    itemView.findViewById(R.id.containerExtendedMedia).setVisibility(View.VISIBLE);

                    if(tweet.mediaURLs[2] != null) {

                        Glide
                                .with(context)
                                .load(tweet.mediaURLs[2])
                                .centerCrop()
                                .into(ivMedia3);

                        ((ViewGroup.MarginLayoutParams)ivMedia2.getLayoutParams()).bottomMargin = 2;
                        ivMedia3.setVisibility(View.VISIBLE);

                    }

                }

                itemView.findViewById(R.id.containerMedia).setVisibility(View.VISIBLE);

            }

        }
    }

}
