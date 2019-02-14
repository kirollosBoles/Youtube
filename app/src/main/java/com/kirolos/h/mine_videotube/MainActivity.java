package com.kirolos.h.mine_videotube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.API, this);
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean b) {
           if(!b)
           {
               youTubePlayer.cueVideo("1mJv4XxWlu8&index=8&list=PLzLFqCABnRQftQQETzoVMuteXzNiXmnj8");

           }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReson) {
         if(errorReson.isUserRecoverableError())
         {
             errorReson.getErrorDialog(this,RECOVERY_REQUEST).show();
         }
         else {
             String error=String.format(getString(R.string.player_error),errorReson.toString());
             Toast.makeText(this,error,Toast.LENGTH_LONG).show();
         }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.API, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}
