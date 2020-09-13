package com.example.spiderhackathonui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RelativeLayout[] relativeLayouts;

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        relativeLayouts = new RelativeLayout[4];
        relativeLayouts[0] = findViewById(R.id.relativeLayout);
        relativeLayouts[1] = findViewById(R.id.relativeLayout2);
        relativeLayouts[2] = findViewById(R.id.relativeLayout3);
        relativeLayouts[3] = findViewById(R.id.relativeLayout4);

        for (int i = 0; i < 4; i++) {
            relativeLayouts[i].setVisibility(View.VISIBLE);
            relativeLayouts[i].setOnClickListener(viewClicked);
        }
    }

    View.OnClickListener viewClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RelativeLayout layout = (RelativeLayout) v;
            final Animation leftOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_left);
            final Animation rightOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_right);
            final Animation setTop = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_to_top_1);
            final Animation setTop2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_to_top_2);
            final Animation setTop3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_to_top_3);
            final Animation setTop4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_to_top_4);
            switch (layout.getId()) {
                case R.id.relativeLayout:
                    position = 0;
                    relativeLayouts[0].setVisibility(View.INVISIBLE);
                    relativeLayouts[0].setAnimation(setTop);
                    relativeLayouts[1].setVisibility(View.INVISIBLE);
                    relativeLayouts[1].setAnimation(rightOut);
                    relativeLayouts[2].setVisibility(View.INVISIBLE);
                    relativeLayouts[2].setAnimation(rightOut);
                    relativeLayouts[3].setVisibility(View.INVISIBLE);
                    relativeLayouts[3].setAnimation(rightOut);
                    break;
                case R.id.relativeLayout2:
                    position = 1;
                    relativeLayouts[0].setVisibility(View.INVISIBLE);
                    relativeLayouts[0].setAnimation(leftOut);
                    relativeLayouts[1].setVisibility(View.INVISIBLE);
                    relativeLayouts[1].setAnimation(setTop2);
                    relativeLayouts[2].setVisibility(View.INVISIBLE);
                    relativeLayouts[2].setAnimation(rightOut);
                    relativeLayouts[3].setVisibility(View.INVISIBLE);
                    relativeLayouts[3].setAnimation(rightOut);
                    break;
                case R.id.relativeLayout3:
                    position = 2;
                    relativeLayouts[0].setVisibility(View.INVISIBLE);
                    relativeLayouts[0].setAnimation(leftOut);
                    relativeLayouts[1].setVisibility(View.INVISIBLE);
                    relativeLayouts[1].setAnimation(leftOut);
                    relativeLayouts[2].setVisibility(View.INVISIBLE);
                    relativeLayouts[2].setAnimation(setTop3);
                    relativeLayouts[3].setVisibility(View.INVISIBLE);
                    relativeLayouts[3].setAnimation(rightOut);
                    break;
                case R.id.relativeLayout4:
                    position = 3;
                    relativeLayouts[0].setVisibility(View.INVISIBLE);
                    relativeLayouts[0].setAnimation(leftOut);
                    relativeLayouts[1].setVisibility(View.INVISIBLE);
                    relativeLayouts[1].setAnimation(leftOut);
                    relativeLayouts[2].setVisibility(View.INVISIBLE);
                    relativeLayouts[2].setAnimation(leftOut);
                    relativeLayouts[3].setVisibility(View.INVISIBLE);
                    relativeLayouts[3].setAnimation(setTop4);
                    break;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("headingPosition", position);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }, 1000);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}