package gui.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import gui.t3h.com.ailatrieuphu.R;

/**
 * Created by duyti on 7/21/2016.
 */
public class DialogLoading extends Dialog {

    private ImageView effectLogo;

    private Context mContext;
    public DialogLoading(Context context) {
        super(context, R.style.full_screen_dialog);
        mContext = context;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading);
        initView();
        setEvents();
    }

    private void setEvents() {
        //set animation
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.anim_rotate);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(15000);
        effectLogo.setAnimation(animation);
    }

    private void initView() {
        effectLogo = (ImageView) findViewById(R.id.effect_logo);
    }

}
