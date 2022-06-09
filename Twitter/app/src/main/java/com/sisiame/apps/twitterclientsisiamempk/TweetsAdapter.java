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

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody, tvName, tvHandle, tvCreatedAt, tvComments, tvRetweets, tvLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
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

//          tvComments.setText(tweet.comments);
            tvRetweets.setText(tweet.retweets);
            tvLikes.setText(tweet.likes);
        }
    }

}
