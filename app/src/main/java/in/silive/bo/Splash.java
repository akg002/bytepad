package in.silive.bo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import in.silive.bo.activity.NavigationMain;

public class Splash extends AppCompatActivity {
    int rectangleCount = 8;
    View[] rectangles = new View[rectangleCount];
    public int[] recResId={R.id.rect_one,R.id.rect_two,R.id.rect_three,R.id.rect_four,R.id.rect_five,R.id.rect_six,R.id.rect_seven,R.id.rect_eigth};
    public int[] imgResId={R.id.img_b,R.id.img_y,R.id.img_t,R.id.img_e,R.id.img_space,R.id.img_p,R.id.img_a,R.id.img_d};
    public int i=0,k=0;
    boolean flag=false;
    LinearLayout centerPlaceHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        centerPlaceHolder = (LinearLayout) findViewById(R.id.centre_placeholder);
        while (i < 8) {
            rectangles[i] = findViewById(recResId[i]);
            AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(Splash.this, R.animator.splash_animation);
            animatorSet.setTarget(rectangles[i]);
            animatorSet.setStartDelay(i * (500 - (50 * i)));
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if(i==8&&flag==false){
                        flag=true;
                        moveToActivity();

                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(findViewById(imgResId[i]), "alpha", 0f, 1f);
            fadeIn.setDuration(1000);
            fadeIn.setStartDelay(i * (500 - (50 * i)) + 500);
            fadeIn.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    findViewById(imgResId[k++]).setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            fadeIn.start();
            animatorSet.start();
            i++;
        }
    }
    public void moveToActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, NavigationMain.class);
                startActivity(i);

            }
        },2000);
    }
}




