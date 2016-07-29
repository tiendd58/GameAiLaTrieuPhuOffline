package gui.t3h.com.ailatrieuphu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import gui.t3h.com.ailatrieuphu.PlayGameActivity;
import gui.t3h.com.ailatrieuphu.R;
import gui.t3h.com.ailatrieuphu.util.SoundManager;

/**
 * Created by duyti on 7/20/2016.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{

    private Button btnPlay;
    private SoundManager sound;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu,container, false);
        btnPlay = (Button) rootView.findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(this);
        //set animation
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.anim_rotate);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(15000);
        rootView.findViewById(R.id.effect_logo).setAnimation(animation);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        sound = new SoundManager(getContext());
        sound.playBGSound(R.raw.background_music);
    }

    @Override
    public void onPause() {
        super.onPause();
        sound.getPlayer().release();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_play:
                Intent intent = new Intent(getContext(),PlayGameActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
