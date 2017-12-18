package code.xp.mysocialappteam.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.control.RecommendControl;
import code.xp.mysocialappteam.present.ArticalPresent;
import code.xp.mysocialappteam.present.HotRecommendPresent;
import code.xp.mysocialappteam.view.adapter.RecyclvAdapter;
import code.xp.mysocialappteam.view.bean.HotRecommendBean;
import code.xp.mysocialappteam.view.bean.MyArticleBean;

/**
 * Created by dell on 2017/12/18.
 */

public class RecommendFragment extends Fragment implements RecommendControl {
    private RecyclerView mRvRecommend;
    private SwipeRefreshLayout mSrlRecommend;
    private RecyclvAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommendfragment_layout, container, false);
        initView(view);

        mRvRecommend.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mSrlRecommend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        adapter = new RecyclvAdapter(getActivity());
        mRvRecommend.setAdapter(adapter);
        return view;
    }

    private void initView(@NonNull final View itemView) {
        mRvRecommend = itemView.findViewById(R.id.recommend_rv);
        mSrlRecommend = itemView.findViewById(R.id.recommend_srl);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void getMyArtical(MyArticleBean articleBean) {
        if (articleBean.getCode() != 200) {
            Toast.makeText(getActivity(), "" + articleBean.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            if (articleBean.getData().getArticle() != null) {
                adapter.getListData(articleBean.getData().getArticle());
            }

        }

    }

    @Override
    public void getHotRecommend(HotRecommendBean hotRecommendBean) {
        if (hotRecommendBean.getCode() != 200) {
            Toast.makeText(getActivity(), "" + hotRecommendBean.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            if (hotRecommendBean.getData().getTopic() != null) {
                adapter.getListHotData(hotRecommendBean.getData().getTopic());
            }

        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getFromUid(String s) {
        ArticalPresent articalPresent = new ArticalPresent(this);
        articalPresent.setArticalModel("surfer", "index2", s, "1");
        HotRecommendPresent hotRecommendPresent = new HotRecommendPresent(this);
        hotRecommendPresent.setHotRecommendModel("surfer", "index", s, "1", "5");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
