package in.silive.bo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.octo.android.robospice.SpiceManager;

import in.silive.bo.services.RetrofitBytepadService;

/**
 * Created by kartikey on 22/02/16.
 */
public class BaseActivity extends AppCompatActivity{
    //    SpiceManager spiceManager
   static SpiceManager spiceManager = new SpiceManager(RetrofitBytepadService.class);

    @Override
    protected void onStart() {
        super.onStart();
        if(!spiceManager.isStarted())
        spiceManager.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(spiceManager.isStarted())
        spiceManager.shouldStop();
    }
    public SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
