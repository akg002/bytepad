package in.silive.bo.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.octo.android.robospice.SpiceManager;
import in.silive.bo.R;
import in.silive.bo.adapter.SampleFragmentPagerAdapter;
import in.silive.bo.fragment.SearchPaper;
import in.silive.bo.listener.SearchViewListener;
import java.util.ArrayList;

import studios.codelight.smartloginlibrary.SmartCustomLoginListener;
import studios.codelight.smartloginlibrary.SmartLoginBuilder;
import studios.codelight.smartloginlibrary.SmartLoginConfig;
import studios.codelight.smartloginlibrary.manager.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartFacebookUser;
import studios.codelight.smartloginlibrary.users.SmartGoogleUser;
import studios.codelight.smartloginlibrary.users.SmartUser;


/**
 * Created by kartikey on 22/02/16.
 */
public class NavigationMain extends BaseActivity implements SearchView.OnQueryTextListener{
    public static SpiceManager spiceManager;
    public Toolbar toolbar;
    public SearchView searchView;
    public SampleFragmentPagerAdapter fragmentAdapter;
    public ViewPager viewPager;
    public SearchViewListener currentFragment ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        SmartUser currentUser;

        //get the current user details
        currentUser = UserSessionManager.getCurrentUser(this);
        String display = "no user";
        if(currentUser != null) {
            if (currentUser instanceof SmartFacebookUser) {
                SmartFacebookUser facebookUser = (SmartFacebookUser) currentUser;
                display = facebookUser.getProfileName() + " (FacebookUser)is logged in";
            } else if (currentUser instanceof SmartGoogleUser) {
                display = ((SmartGoogleUser) currentUser).getDisplayName() + " (GoogleUser) is logged in";
            } else {
                display = currentUser.getUsername() + " (Custom User) is logged in";
            }
            Log.d("BytePad","LogIn"+display);
        }

        else {

            SmartLoginBuilder loginBuilder = new SmartLoginBuilder();

            //Set facebook permissions
            ArrayList<String> permissions = new ArrayList<>();
            permissions.add("public_profile");
            permissions.add("email");
            permissions.add("user_birthday");
            permissions.add("user_friends");


            Intent intent = loginBuilder.with(getApplicationContext())
                    .setAppLogo(R.drawable.bytepad)
                    .isFacebookLoginEnabled(true)
                    .withFacebookAppId(getString(R.string.facebook_app_id)).withFacebookPermissions(permissions)
                   // .isGoogleLoginEnabled(true)
                    .build();

            startActivityForResult(intent, SmartLoginConfig.LOGIN_REQUEST);


            toolbar = (Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            SearchPaper.setSpiceManager(getSpiceManager());
            spiceManager=getSpiceManager();
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            fragmentAdapter = new SampleFragmentPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(fragmentAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    currentFragment =  ((SearchViewListener) fragmentAdapter.getItem(position));
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            // Give the PagerSlidingTabStrip the ViewPager
            PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
            // Attach the view pager to the tab strip
            tabsStrip.setViewPager(viewPager);
        }
    }
    public static SpiceManager getSpicemanager(){
        return getSpicemanager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        searchView = (SearchView)menu.findItem(R.id.menu_search).getActionView();
        searchView.setIconified(true); searchView.setBackgroundColor(Color.WHITE);
/*        final int id = searchView.getContext().getResources().getIdentifier("android:id/search_bar", null, null);
            LinearLayout layout = (LinearLayout) searchView.findViewById(id);
              layout.setLayoutTransition(new LayoutTransition());*/

    /*    LayoutTransition transition = new LayoutTransition();
        transition.setDuration(1500);
        searchView.setLayoutTransition(transition);*/

        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        currentFragment.searchTextSubmit(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        currentFragment.searchTextChanged(newText);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String fail = "Login Failed";
        if(resultCode == SmartLoginConfig.FACEBOOK_LOGIN_REQUEST){
            SmartFacebookUser user;
            try {
                user = data.getParcelableExtra(SmartLoginConfig.USER);
                String userDetails = user.getProfileName() + " " + user.getEmail() + " " + user.getBirthday();
               Log.d("BytePad",userDetails);
            }catch (Exception e){
                Log.d("BytePad", fail);
            }
        }
        else if(resultCode == SmartLoginConfig.GOOGLE_LOGIN_REQUEST){
            SmartGoogleUser user = data.getParcelableExtra(SmartLoginConfig.USER);
            String userDetails = user.getEmail() + " " + user.getBirthday() + " " + user.getAboutMe();
            Log.d("BytePad", userDetails);
        }

    }
}
