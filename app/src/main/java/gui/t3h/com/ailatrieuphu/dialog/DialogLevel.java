package gui.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import gui.t3h.com.ailatrieuphu.R;

/**
 * Created by duyti on 7/23/2016.
 */
public class DialogLevel extends Dialog {

    private LinearLayout linearLayout;
    private TextView arrTvLevel[];
    private int level=0;
    private String money;

    public DialogLevel(Context context, int level) {
        super(context, R.style.full_screen_dialog);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_level);
        findViewByIDs();
        setLevel(level);
        setBackgroundForLevel();
    }

    private void setBackgroundForLevel() {
        if(level!=0){
            arrTvLevel[level-1].setBackground(getContext().getResources().getDrawable(R.drawable.atp__activity_player_image_money_milestone));
            if(level-2>0){
                arrTvLevel[level-2].setBackground(null);
            }
            if(level == 6 || level == 11){
                arrTvLevel[level-2].setBackground(getContext().getResources().getDrawable(R.drawable.atp__activity_player_image_money_curent));
            }
        }
    }

    private void findViewByIDs() {
        arrTvLevel = new TextView[15];
        arrTvLevel[0] = (TextView) findViewById(R.id.tv_score_level_1);
        arrTvLevel[1] = (TextView) findViewById(R.id.tv_score_level_2);
        arrTvLevel[2] = (TextView) findViewById(R.id.tv_score_level_3);
        arrTvLevel[3] = (TextView) findViewById(R.id.tv_score_level_4);
        arrTvLevel[4] = (TextView) findViewById(R.id.tv_score_level_5);
        arrTvLevel[5] = (TextView) findViewById(R.id.tv_score_level_6);
        arrTvLevel[6] = (TextView) findViewById(R.id.tv_score_level_7);
        arrTvLevel[7] = (TextView) findViewById(R.id.tv_score_level_8);
        arrTvLevel[8] = (TextView) findViewById(R.id.tv_score_level_9);
        arrTvLevel[9] = (TextView) findViewById(R.id.tv_score_level_10);
        arrTvLevel[10] = (TextView) findViewById(R.id.tv_score_level_11);
        arrTvLevel[11] = (TextView) findViewById(R.id.tv_score_level_12);
        arrTvLevel[12] = (TextView) findViewById(R.id.tv_score_level_13);
        arrTvLevel[13] = (TextView) findViewById(R.id.tv_score_level_14);
        arrTvLevel[14] = (TextView) findViewById(R.id.tv_score_level_15);
    }

    public void setLevel(int level) {
        this.level = level;
        setBackgroundForLevel();
    }

    public String getMoney() {
        money = arrTvLevel[level-2].getText().toString();
        return money;
    }
}
