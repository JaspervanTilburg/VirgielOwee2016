package com.example.jasper.virgielowee2016;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private VideoView videoPlayer;
    private MediaController mediaController;
    private int position = 0;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton button = (ImageButton) rootView.findViewById(R.id.video_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation bottomUp = AnimationUtils.loadAnimation(getContext(),
                        R.anim.bottom_up);
                RelativeLayout hiddenPanel = (RelativeLayout) rootView.findViewById(R.id.video_view);
                hiddenPanel.startAnimation(bottomUp);
                hiddenPanel.setVisibility(View.VISIBLE);

                videoPlayer = (VideoView) rootView.findViewById(R.id.videoPlayer);
                if (mediaController == null) {
                    mediaController = new MediaController(getContext());

                    // Set the videoView that acts as the anchor for the MediaController.
                    mediaController.setAnchorView(videoPlayer);


                    // Set MediaController for VideoView
                    videoPlayer.setMediaController(mediaController);
                }

                try {
                    // ID of video file.
                    int id = R.raw.match;
                    videoPlayer.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + id));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                videoPlayer.requestFocus();


                // When the video file ready for playback.
                videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer mediaPlayer) {


                        videoPlayer.seekTo(position);
                        if (position == 0) {
                            videoPlayer.start();
                        }

                        // When video Screen change size.
                        mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                                // Re-Set the videoView that acts as the anchor for the MediaController
                                mediaController.setAnchorView(videoPlayer);
                            }
                        });
                    }
                });
            }
        });

        return rootView;
    }

}
