package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    private TextView tv;
    Animation rotate;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tv = findViewById(R.id.Result);
        tv.setText(getIntent().getExtras().get("winner").toString());

        play = findViewById(R.id.playagain);

        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        play.startAnimation(rotate);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent play = new Intent(result.this, MainActivity.class);
                startActivity(play);
            }
        });
    }
}
