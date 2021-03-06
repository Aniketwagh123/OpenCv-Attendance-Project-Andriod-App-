package com.example.myapplicationnew.Common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationnew.R;
import com.example.myapplicationnew.User.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIMER = 5000;
    //Variables
    ImageView lottieImg;
    TextView poweredBy,title;

    //Animations
    Animation sideAnim , bottomAnim;

    SharedPreferences onBoarding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //Hooks
        lottieImg = findViewById(R.id.lottie);
        poweredBy = findViewById(R.id.powered_by_line);
        title = findViewById(R.id.title);

        //Animations
         sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
         bottomAnim = AnimationUtils.loadAnimation(this,R.anim.buttom_anim);

         //Set animations on elements
        lottieImg.setAnimation(sideAnim);
        poweredBy.setAnimation(bottomAnim);
        title.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoarding = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoarding.getBoolean("firstTime",true);

                if(isFirstTime) {
                    SharedPreferences.Editor editor = onBoarding.edit();
                    editor.putBoolean("firstTime",false);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_TIMER);






    }
}