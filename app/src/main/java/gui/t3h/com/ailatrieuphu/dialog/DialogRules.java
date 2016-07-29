package gui.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import gui.t3h.com.ailatrieuphu.R;

/**
 * Created by duyti on 7/23/2016.
 */
public class DialogRules extends Dialog {

    public DialogRules(Context context) {
        super(context, R.style.full_screen_dialog);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_level);
    }

}
