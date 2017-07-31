package id.co.telkom.pesonabeta2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.VideoView;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;


/**
 * Created by lailamunziah-pc on 30/07/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFormat(PixelFormat.UNKNOWN);



        videoView = (VideoView) findViewById(R.id.videoView);

        String path = "android.resource://"+getPackageName()+ "/"+R.raw.pesonasaja;
        Uri uri = Uri.parse(path);

        videoView.setVideoURI(uri);
        videoView.requestFocus();


        /*videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
             mp.setLooping(true);
            }
        });*/

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                jump();
            }

        });
        videoView.start();

    }

    private void jump() {
        if(isFinishing())
            return;
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }


}
