package gui.t3h.com.ailatrieuphu.util;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;

/**
 * Created by duyti on 7/25/2016.
 */
public class SoundManager {
    private Context mContext;
    private MediaPlayer player;
    private ArrayList<MediaPlayer> listPlayers = new ArrayList<>();

    public SoundManager(Context context) {
        mContext = context;
    }

    public void playBGSound(int idSound) {
        if (player != null) {
            player.release();
        }
        player = MediaPlayer.create(mContext, idSound);
        player.setLooping(true);
        player.start();
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void playListSound(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (player != null) {
                player.release();
            }
            player = MediaPlayer.create(mContext, list.get(i));
            player.start();
            while (player.isPlaying()) {

            }
        }
    }
}
