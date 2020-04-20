package com.lux.eventmanagementApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {
    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                //The following code will execute after the 5 seconds.

                try {
                    // List<User> mUser = User.listAll(User.class);
                    //Go to next page i.e, start the next activity.

                            String email = Utils.getUserGmail(getApplicationContext());
                    //if(mUser.size()>0 && isUserLogedIn) {
                    if(!email.equals("")) {
                        Intent intent = new Intent(getApplicationContext(), HomeMainActivity.class);
                        startActivity(intent);

                        //Let's Finish Splash Activity since we don't want to show this when user press back button.
                        finish();
                    }else{
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, 1000);  // Give a 5 seconds delay.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Remove all the callbacks otherwise navigation will execute even after activity is killed or closed.
        mWaitHandler.removeCallbacksAndMessages(null);
    }
}