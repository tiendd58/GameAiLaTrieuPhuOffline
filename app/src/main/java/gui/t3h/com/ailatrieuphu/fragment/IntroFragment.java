package gui.t3h.com.ailatrieuphu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import gui.t3h.com.ailatrieuphu.R;

/**
 * Created by duyti on 7/20/2016.
 */
public class IntroFragment extends Fragment {

    private LinearLayout fragIntro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro,container, false);
        fragIntro = (LinearLayout) view.findViewById(R.id.frag_intro);
        return view;
    }

}
