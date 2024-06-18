package com.example.nammakarnataka.video_zoom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nammakarnataka.R;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;

public class Conference_activity extends AppCompatActivity {

    TextView meetinIdText;
    ImageView meetingIdShare;

    String meetingId,userId,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);
        meetingIdShare=findViewById(R.id.share_button);
        meetinIdText=findViewById(R.id.meetiing_id_textView);

        meetingId=getIntent().getStringExtra("meetingId");
        userId=getIntent().getStringExtra("userId");
        name=getIntent().getStringExtra("name");

        meetinIdText.setText("meeting id:"+meetingId);
        addFragment();
        meetingIdShare.setOnClickListener((v)->{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,"join the conference\n meeting id :"+meetingId);
            startActivity(Intent.createChooser(intent,"share via"));

        });
    }
    public void addFragment() {
        long appID = AppConstants.appid;
        String appSign = AppConstants.appsign;


        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(appID, appSign, userId, name,meetingId,config);
        config.turnOnCameraWhenJoining=false;
//        config.audioVideoViewConfig=false;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
}