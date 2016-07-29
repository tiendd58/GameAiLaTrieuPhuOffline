package gui.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import gui.t3h.com.ailatrieuphu.R;

/**
 * Created by duyti on 7/22/2016.
 */
public class DialogTrueAnswer extends Dialog {

    private ImageView ivPro;
    private TextView tvAnswer;

    public DialogTrueAnswer(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_true_answer);
        initView();
    }

    private void initView() {
        tvAnswer = (TextView) findViewById(R.id.tv_true_answer);
        ivPro = (ImageView) findViewById(R.id.iv_pro);
    }

    public void setAnswer(int trueAnswer) {
        switch (trueAnswer) {
            case 1:
                tvAnswer.setText("A");
                break;
            case 2:
                tvAnswer.setText("B");
                break;
            case 3:
                tvAnswer.setText("C");
                break;
            case 4:
                tvAnswer.setText("D");
                break;
        }
    }

    public void setIvPro(int idIvPro) {
        ivPro.setImageResource(idIvPro);
    }
}
