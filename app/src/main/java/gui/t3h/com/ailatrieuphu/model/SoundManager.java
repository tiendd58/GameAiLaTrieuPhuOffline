package gui.t3h.com.ailatrieuphu.model;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by duyti on 7/19/2016.
 */
public class SoundManager {
    private Context mContext;
    private MediaPlayer mPlayer;
    private MediaPlayer mPlayerBG;

    public SoundManager(Context context) {
        this.mContext = context;
    }

    public void playSoundBG(int idSound){
        mPlayer = MediaPlayer.create(mContext, idSound);
        mPlayer.setLooping(true);
        mPlayer.start();
    }

    private void playSound(int idSound, MediaPlayer.OnCompletionListener event){
        if(mPlayerBG!=null){
            mPlayerBG.release();
        }
        mPlayer = MediaPlayer.create(mContext, idSound);
        mPlayer.setOnCompletionListener(event);
        mPlayer.start();
    }

}
