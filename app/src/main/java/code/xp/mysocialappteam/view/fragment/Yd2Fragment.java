package code.xp.mysocialappteam.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.view.activity.ThridActivity;


/**
 * Created by dell on 2017/12/15.
 */

public class Yd2Fragment extends Fragment implements View.OnClickListener {


    /**
     * 第一次进入时的第二个碎片
     * 立即体验
     */
    private Button mExperienceBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yd2_fragment, null);
        initView(view);
        return view;
    }

    private void initView(@NonNull final View itemView) {
        mExperienceBtn = itemView.findViewById(R.id.btn_experience);
        mExperienceBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_experience:
                Intent intent =new Intent(getActivity(), ThridActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}
