package example.soufiane.plateformepedagogique;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Soufiane on 16/07/2016.
 */
public class Video extends Activity {
    String TAG = "com.ebookfrenzy.videoplayer";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        final VideoView videoView = (VideoView) findViewById(R.id.videoView);

        videoView.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4");

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "Duration = " + videoView.getDuration());
            }
        });

        videoView.start();

    }
    }


