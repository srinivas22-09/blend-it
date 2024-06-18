package com.example.nammakarnataka.video_zoom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.nammakarnataka.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;
import java.util.UUID;

public class Zoom_video extends AppCompatActivity {

    TextInputEditText meetingIdInput,nameInput;
    MaterialButton joinButton,createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_video);
        meetingIdInput=findViewById(R.id.meeting_id_input);
        nameInput=findViewById(R.id.name_input);
        joinButton=findViewById(R.id.join_btn);
        createButton=findViewById(R.id.create_button);


        joinButton.setOnClickListener((v)->{
            String meetingId=meetingIdInput.getText().toString();
            if(meetingId.length()!=10){
                meetingIdInput.setError("invalid id");
                meetingIdInput.requestFocus();
                return;
            }
            String name = nameInput.getText().toString();
            if(name.isEmpty()){
                nameInput.setError("name is required");
                nameInput.requestFocus();
                return;
            }
            startmeeting(meetingId,name);

        });
        createButton.setOnClickListener((v)->{
            String meetingIdCreate=meetingIdInput.getText().toString();
//            String meetingId = "1234567891";
            String name = nameInput.getText().toString();

            if(name.isEmpty()){
                nameInput.setError("name is required");
                nameInput.requestFocus();
                return;
            }
//            int a=Integer.parseInt(meetingId);
            startmeeting(meetingIdCreate,name);

        });

    }

    void startmeeting(String meetingId,String name){
        String userId= getRandomMeetingId();
//        \*meetingIdInput.getText().toString();
        Intent intent = new Intent(getApplicationContext(),Conference_activity.class);
        intent.putExtra("meetingId",meetingId);
        intent.putExtra("name",name);
        intent.putExtra("userId",userId);
        startActivity(intent);

    }
    String getRandomMeetingId(){
        StringBuilder id = new StringBuilder();
        while (id.length()!=10){
            int random = new Random().nextInt(10);
            id.append(random);
//        String id=meetingIdInput.getText().toString();
        }
        return id.toString();
    }
}