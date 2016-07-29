package gui.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import gui.t3h.com.ailatrieuphu.R;

/**
 * Created by duyti on 7/22/2016.
 */
public class DialogConfirm extends Dialog {

    private Button btYes;
    private Button btNo;
    private TextView tvNotification;

    public DialogConfirm(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_confirm);
        this.setCancelable(false);
        initView();
    }

    private void initView() {
        tvNotification = (TextView) findViewById(R.id.tv_notification);
        btYes = (Button) findViewById(R.id.bt_yes);
        btNo = (Button) findViewById(R.id.bt_no);
    }

    public void setNotification(String notification) {
        tvNotification.setText(notification);
    }

    public void setYesNoButton(String textYes, String textNo, View.OnClickListener onClickListener) {
        btYes.setText(textYes);
        btNo.setText(textNo);

        btYes.setOnClickListener(onClickListener);
        btNo.setOnClickListener(onClickListener);
    }
}
