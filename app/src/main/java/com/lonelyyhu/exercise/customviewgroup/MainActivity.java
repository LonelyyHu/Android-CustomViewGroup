package com.lonelyyhu.exercise.customviewgroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick4CornerLayout(View view) {

        Intent intent = new Intent(this, FourCornerLayoutActivity.class);
        startActivity(intent);

    }

    public void onClickMoveableBallView(View view) {

        Intent intent = new Intent(this, MoveableBallActivity.class);
        startActivity(intent);

    }

    public void onClickRainbowBarView(View view) {

        Intent intent = new Intent(this, RainbowBarActivity.class);
        startActivity(intent);

    }
}
