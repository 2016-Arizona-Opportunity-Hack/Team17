package com.readonchandler.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.readonchandler.R;

/**
 * Created by Lakshmisagar on 10/2/2016.
 */

public class DetailActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();

        setContentView(R.layout.layout_detail_view);

        VideoView videoView = (VideoView) findViewById(R.id.video_view);

        //Creating MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        //specify the location of media file
        //Uri uri = Uri.parse(b.getString("Link"));
        TextView title = (TextView) findViewById(R.id.detailTitle);
        title.setText(b.getString("Title"));

        TextView time = (TextView) findViewById(R.id.detailTime);
        title.setText(b.getString("Time"));

        TextView detail = (TextView) findViewById(R.id.detailtext);
        title.setText(b.getString("EventDetail"));

        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        //videoView.setVideoURI(uri);
       // videoView.requestFocus();
       // videoView.start();

    }
}