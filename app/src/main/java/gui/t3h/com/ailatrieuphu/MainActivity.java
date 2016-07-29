package gui.t3h.com.ailatrieuphu;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import gui.t3h.com.ailatrieuphu.fragment.IntroFragment;
import gui.t3h.com.ailatrieuphu.fragment.MenuFragment;

public class MainActivity extends AppCompatActivity   {

    private FragmentManager manager;
    private IntroFragment fragIntro;
    private MenuFragment fragMenu;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        manager  = getSupportFragmentManager();
        showIntroFragment();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showMenuFragment();
            }
        },2000);
    }

    public void showIntroFragment() {
        fragIntro = new IntroFragment();
        manager.beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.layout_act_menu, fragIntro).commit();

    }

    public void showMenuFragment(){
        fragMenu = new MenuFragment();
        manager.beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).hide(fragIntro).commit();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                manager.beginTransaction().replace(R.id.layout_act_menu, fragMenu).commit();
            }
        },2000);
    }

}
