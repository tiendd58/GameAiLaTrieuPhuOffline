package gui.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import gui.t3h.com.ailatrieuphu.R;

/**
 * Created by duyti on 7/22/2016.
 */
public class DialogCall extends Dialog implements View.OnClickListener {

    private int mTrueAnswer;
    private DialogTrueAnswer trueAnswerDialog;

    public DialogCall(Context context) {
        super(context);
    }


    private ImageView ivDoctor;
    private ImageView ivProfessor;
    private ImageView ivEngineer;
    private ImageView ivMaster;
    private int trueAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_call);
        this.setCancelable(false);
        initView();
    }

    private void initView() {
        ivDoctor = (ImageView) findViewById(R.id.iv_doctor);
        ivEngineer = (ImageView) findViewById(R.id.iv_engineer);
        ivMaster = (ImageView) findViewById(R.id.iv_master);
        ivProfessor = (ImageView) findViewById(R.id.iv_professor);

        ivDoctor.setOnClickListener(this);
        ivEngineer.setOnClickListener(this);
        ivMaster.setOnClickListener(this);
        ivProfessor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_doctor:
                trueAnswerDialog = new DialogTrueAnswer(getContext());
                trueAnswerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                trueAnswerDialog.show();
                trueAnswerDialog.setIvPro(R.drawable.bg_bac_si);
                trueAnswerDialog.setAnswer(mTrueAnswer);
                this.dismiss();
                break;
            case R.id.iv_master:
                trueAnswerDialog = new DialogTrueAnswer(getContext());
                trueAnswerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                trueAnswerDialog.show();
                trueAnswerDialog.setIvPro(R.drawable.bg_giao_su);
                trueAnswerDialog.setAnswer(mTrueAnswer);
                this.dismiss();
                break;
            case R.id.iv_professor:
                trueAnswerDialog = new DialogTrueAnswer(getContext());
                trueAnswerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                trueAnswerDialog.show();
                trueAnswerDialog.setIvPro(R.drawable.bg_nhan_vien);
                trueAnswerDialog.setAnswer(mTrueAnswer);
                this.dismiss();
                break;
            case R.id.iv_engineer:
                trueAnswerDialog = new DialogTrueAnswer(getContext());
                trueAnswerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                trueAnswerDialog.show();
                trueAnswerDialog.setIvPro(R.drawable.bg_ki_su);
                trueAnswerDialog.setAnswer(mTrueAnswer);
                this.dismiss();
                break;
            default:
                break;
        }
    }

    public void setTrueAnswer(int trueAnswer) {
        this.mTrueAnswer = trueAnswer;
    }
}
