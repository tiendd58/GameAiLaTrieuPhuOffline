package gui.t3h.com.ailatrieuphu;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import gui.t3h.com.ailatrieuphu.dialog.DialogCall;
import gui.t3h.com.ailatrieuphu.dialog.DialogConfirm;
import gui.t3h.com.ailatrieuphu.dialog.DialogLevel;
import gui.t3h.com.ailatrieuphu.dialog.DialogLoading;
import gui.t3h.com.ailatrieuphu.dialog.DialogRules;
import gui.t3h.com.ailatrieuphu.model.Question;
import gui.t3h.com.ailatrieuphu.util.DBManager;
import gui.t3h.com.ailatrieuphu.util.SoundManager;

public class PlayGameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int NEXT_QUESTION = 101;
    private static final int CHANGE_QUESTION = 102;
    private static final int DOWN_TIME = 103;
    private static final int LOSE = 104;
    ArrayList<Question> arrQuestion = new ArrayList<>();
    private DBManager dbManager;

    private DialogLoading dialogLoading;
    private DialogConfirm dialogConfirm;
    private DialogCall dialogCall;
    private DialogLevel dialogLevel;

    private TextView tvLevel, tvQuestion, tvTime, tvMoney;
    private Button btnCase[], btnChangeQuestion, btn5050, btnStop, btnCall;
    private boolean newQuestion = false;


    private int curLevel = 0;

    private ProgressBar time;

    private int curTime = 30;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEXT_QUESTION:
                    newQuestion = true;
                    curLevel = msg.arg1 + 1;
                    if (curLevel == 15) {
                        Toast.makeText(PlayGameActivity.this, "Ban da chien thang", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    setQuestion();
                    dialogLevel.setLevel(curLevel + 1);
                    dialogLevel.show();
                    tvMoney.setText(dialogLevel.getMoney());
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialogLevel.dismiss();
                        }
                    }, 1500);
                    break;
                case CHANGE_QUESTION:
                    dbManager.changeQuestionByLevel(msg.arg1 + 1);
                    arrQuestion.set(msg.arg1, dbManager.getQuestion(msg.arg1 + 1));
                    setQuestion();
                    btnChangeQuestion.setEnabled(false);
                    Drawable changeX = getResources().getDrawable(R.drawable.atp__activity_player_button_image_help_change_question_x);
                    btnChangeQuestion.setBackground(changeX);
                    break;
                case DOWN_TIME:
                    tvTime.setText(curTime + "");
                    break;
                case LOSE:
                    final DialogConfirm confirm = new DialogConfirm(PlayGameActivity.this);
                    confirm.show();
                    confirm.setNotification("Bạn có muốn lưu lại kết quả không?");
                    confirm.setYesNoButton("Có", "Không", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            switch (view.getId()) {
                                case R.id.bt_yes:
                                    //save tien vao csdl
                                    break;
                                case R.id.bt_no:
                                    confirm.dismiss();
                                    finish();
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        findViewByIds();
        final DialogRules dialogRules = new DialogRules(this);
        dialogRules.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SoundManager soundRules = new SoundManager(PlayGameActivity.this);
                ArrayList<Integer> listSound = new ArrayList<>();
                listSound.add(R.raw.luatchoi);
                listSound.add(R.raw.ready);
                listSound.add(R.raw.gofind);
                soundRules.playListSound(listSound);
                newQuestion = true;
            }
        }).start();
//        soundRules.playSoundContinue(R.raw.ready);
        Handler handlerRules = new Handler();
        handlerRules.postDelayed(new Runnable() {
            @Override
            public void run() {
               dialogRules.dismiss();
            }
        },16000);
        MyAsyntask myAsyntask = new MyAsyntask();
        myAsyntask.execute();
        setEvent();
    }

    private void setEvent() {
        btnCase[0].setOnClickListener(this);
        btnCase[1].setOnClickListener(this);
        btnCase[2].setOnClickListener(this);
        btnCase[3].setOnClickListener(this);
        btnChangeQuestion.setOnClickListener(this);
        btn5050.setOnClickListener(this);
        tvTime.setText(curTime + "");

        //downtime
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (curTime > 0 ) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(newQuestion){
                       curTime--;
                    }
                    Message msg = new Message();
                    msg.what = DOWN_TIME;
                    msg.arg1 = curLevel;
                    msg.setTarget(handler);
                    msg.sendToTarget();
                }
            }
        }).start();
        btnStop.setOnClickListener(this);
        btnCall.setOnClickListener(this);

    }

    private void findViewByIds() {
        tvMoney = (TextView) findViewById(R.id.tv_money);
        dialogLoading = new DialogLoading(this);
        btnCase = new Button[4];
        btnCase[0] = (Button) findViewById(R.id.btn_casea);
        btnCase[1] = (Button) findViewById(R.id.btn_caseb);
        btnCase[2] = (Button) findViewById(R.id.btn_casec);
        btnCase[3] = (Button) findViewById(R.id.btn_cased);
        tvLevel = (TextView) findViewById(R.id.tv_level);
        tvQuestion = (TextView) findViewById(R.id.tv_question);
        btnChangeQuestion = (Button) findViewById(R.id.btn_change_question);
        time = (ProgressBar) findViewById(R.id.time);
        tvTime = (TextView) findViewById(R.id.tv_time);
        btn5050 = (Button) findViewById(R.id.btn_5050);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnCall = (Button) findViewById(R.id.btn_call);
        dialogLevel = new DialogLevel(this, curLevel);
        dialogLevel.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogLevel.dismiss();
            }
        }, 2000);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btn_casea:
                if (getAnswer() == 1) {
                    handlerAnswerTrue(view);
                }else{
                    handlerAnswerFalse(view);
                }
                break;
            case R.id.btn_caseb:
                if (getAnswer() == 2) {
                    handlerAnswerTrue(view);
                }else{
                    handlerAnswerFalse(view);
                }
                break;
            case R.id.btn_casec:
                if (getAnswer() == 3) {
                    handlerAnswerTrue(view);
                }else{
                    handlerAnswerFalse(view);
                }
                break;
            case R.id.btn_cased:
                if (getAnswer() == 4) {
                    handlerAnswerTrue(view);
                }else{
                    handlerAnswerFalse(view);
                }
                break;
            case R.id.btn_change_question:
                Message msg = new Message();
                msg.what = CHANGE_QUESTION;
                msg.arg1 = curLevel;
                msg.setTarget(handler);
                msg.sendToTarget();
                break;
            case R.id.btn_5050:
                Random rand = new Random();
                int count = 0;
                int oldTemp = -1;
                while (count < 2) {
                    int temp = rand.nextInt(4) + 1;
                    if (temp != arrQuestion.get(curLevel).getTrueCase() && temp != oldTemp) {
                        count++;
                        oldTemp = temp;
                        btnCase[temp - 1].setText("");
                    }
                }
                btn5050.setEnabled(false);
                btn5050.setBackground(getResources().getDrawable(R.drawable.atp__activity_player_button_image_help_5050_x));
                break;
            case R.id.btn_stop:
                dialogConfirm = new DialogConfirm(this);
                dialogConfirm.show();
                dialogConfirm.setNotification("Bạn có muốn dừng cuộc chơi tại đây không?");
                dialogConfirm.setYesNoButton("Dừng", "Tiếp tục", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()) {
                            case R.id.bt_yes:
                                dialogConfirm.dismiss();
                                finish();
                                break;
                            case R.id.bt_no:
                                dialogConfirm.dismiss();
                                break;
                            default:
                                break;
                        }
                    }
                });
                break;
            case R.id.btn_call:
                dialogCall = new DialogCall(this);
                dialogCall.show();
                dialogCall.setTrueAnswer(arrQuestion.get(curLevel).getTrueCase());
                btnCall.setEnabled(false);
                btnCall.setBackground(getResources().getDrawable(R.drawable.atp__activity_player_button_image_help_call_x));
                break;
            default:
                break;
        }
    }

    private class MyAsyntask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            dialogLoading.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            dbManager = new DBManager(PlayGameActivity.this);
            dbManager.openDB();
            return dbManager.getQuestion();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                dialogLoading.dismiss();
                arrQuestion.addAll(dbManager.getListQuestion());
                setQuestion();
            }
        }
    }

    private void setQuestion() {
        tvLevel.setText("Câu " + arrQuestion.get(curLevel).getLevel() + "");
        tvQuestion.setText(arrQuestion.get(curLevel).getNameQuestion() + "");
        btnCase[3].setText(arrQuestion.get(curLevel).getCaseD());
        btnCase[2].setText(arrQuestion.get(curLevel).getCaseC());
        btnCase[1].setText(arrQuestion.get(curLevel).getCaseB());
        btnCase[0].setText(arrQuestion.get(curLevel).getCaseA());
    }

    private int getAnswer() {
        return arrQuestion.get(curLevel).getTrueCase();
    }

    private void handlerAnswerTrue(final View view) {
        view.setBackground(getResources().getDrawable(R.drawable.atp__activity_player_layout_play_answer_background_selected));
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackground(getResources().getDrawable(R.drawable.atp__activity_player_layout_play_answer_background_true));
                Animation fadeLoop = AnimationUtils.loadAnimation(PlayGameActivity.this, R.anim.fadeloop);
                view.setAnimation(fadeLoop);
                view.startAnimation(fadeLoop);
            }
        }, 1000);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = NEXT_QUESTION;
                msg.arg1 = curLevel;
                msg.setTarget(handler);
                msg.sendToTarget();
                curTime = 30;
                tvTime.setText(curTime + "");
                view.setBackground(getResources().getDrawable(R.drawable.btn_answer));
            }
        }, 2100);
    }

    private void handlerAnswerFalse(final View view) {
        view.setBackground(getResources().getDrawable(R.drawable.atp__activity_player_layout_play_answer_background_selected));
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackground(getResources().getDrawable(R.drawable.atp__activity_player_layout_play_answer_background_wrong));
                Animation fadeLoop = AnimationUtils.loadAnimation(PlayGameActivity.this, R.anim.fadeloop);
                view.setAnimation(fadeLoop);
                view.startAnimation(fadeLoop);
            }
        }, 1000);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = LOSE;
                msg.arg1 = curLevel;
                msg.setTarget(handler);
                msg.sendToTarget();
            }
        }, 2100);
    }

}
