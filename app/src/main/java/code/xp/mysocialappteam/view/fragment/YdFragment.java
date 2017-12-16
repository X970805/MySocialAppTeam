package code.xp.mysocialappteam.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import code.xp.mysocialappteam.R;

/**
 * Created by dell on 2017/12/15.
 * 第一次进入时的第一个碎片
 */

public class YdFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.yd1_fragment,null);
        return view;
    }
}
