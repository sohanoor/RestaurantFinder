package com.sohan.restaurantfinder.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.sohan.restaurantfinder.R;
import com.sohan.restaurantfinder.activity.TabsViewActivity;

public class SplashScreenActivity extends AppCompatActivity implements Animation.AnimationListener {

    CountDownTimer mSplashScreenTimer = null;
    TextView textTitle;
    Animation animFadein;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textTitle = (TextView) findViewById(R.id.textView);

        // load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        // set animation listener
        animFadein.setAnimationListener(this);


        //replace 2000 by 10000

        mSplashScreenTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textTitle.setVisibility(View.VISIBLE);

                // start the animation
                textTitle.startAnimation(animFadein);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashScreenActivity.this, TabsViewActivity.class);
                startActivity(intent);
                finish();
            }
        };
        mSplashScreenTimer.start();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
