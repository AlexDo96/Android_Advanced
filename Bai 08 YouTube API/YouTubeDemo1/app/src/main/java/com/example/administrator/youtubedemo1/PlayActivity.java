package com.example.administrator.youtubedemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {
    private YouTubePlayer YPlayer;
    private static final String YoutubeDeveloperKey = "AIzaSyDZLYcx62PmCA_ETuhcULOKqLNLA3cD61E";
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    String videoId;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_play);
            videoId=getIntent().getExtras().getString("videoId");
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(YoutubeDeveloperKey, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            YPlayer = youTubePlayer;
			/*
			 * Now that this variable YPlayer is global you can access it
			 * throughout the activity, and perform all the player actions like
			 * play, pause and seeking to a position by code.
			 */
            //YPlayer.cueVideo("JIoI6v49X6I");
            YPlayer.cueVideo(videoId);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(YoutubeDeveloperKey, this);
        }
    }
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }



    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer",
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
