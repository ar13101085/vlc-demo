package com.aos.demo_vlc;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.aos.dynamiclib.PrintSomething;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final boolean USE_TEXTURE_VIEW = false;
    private static final boolean ENABLE_SUBTITLES = true;
    private static final String ASSET_FILENAME = "bbb.mp4";

    private VLCVideoLayout mVideoLayout = null;

    private LibVLC mLibVLC = null;
    private MediaPlayer mMediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.systeminfo)).setText(PrintSomething.print());

        final ArrayList<String> args = new ArrayList<>();
        args.add("-vvv");
        args.add(":http-user-agent='Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36'");
       // args.add("--aout=opensles");
       // args.add("--audio-time-stretch"); // time stretching
       // args.add("-vvv"); // verbosity
        mLibVLC = new LibVLC(this, args);
       // mLibVLC.setUserAgent("","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
        mMediaPlayer = new MediaPlayer(mLibVLC);



        mVideoLayout = findViewById(R.id.video_layout);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mLibVLC.release();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Uri uri=Uri.parse("https://edge4.bioscopelive.com/hls/428qKszRSGMNdxLazHoqmQ/1571749245/my_tv.m3u8");

        mMediaPlayer.attachViews(mVideoLayout, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);

        try {
            //final Media media = new Media(mLibVLC, getAssets().openFd(ASSET_FILENAME));
            final Media media = new Media(mLibVLC, uri);
            mMediaPlayer.setMedia(media);
            media.release();
        } catch (Exception e) {
            throw new RuntimeException("Invalid asset folder");
        }
        mMediaPlayer.play();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mMediaPlayer.stop();
        mMediaPlayer.detachViews();
    }

}
