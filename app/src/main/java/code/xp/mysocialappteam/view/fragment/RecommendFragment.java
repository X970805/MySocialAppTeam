package code.xp.mysocialappteam.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.control.RecommendControl;
import code.xp.mysocialappteam.model.greenDao.User;
import code.xp.mysocialappteam.model.greenDao.UserDao;
import code.xp.mysocialappteam.present.ArticalPresent;
import code.xp.mysocialappteam.present.HotRecommendPresent;
import code.xp.mysocialappteam.utils.MyApp;
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
    private User mUser;

    private UserDao userDao;
    private HotRecommendBean hotRecommendBean1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommendfragment_layout, container, false);
        initView(view);
        userDao = MyApp.getInstances().getDaoSession().getUserDao();
        mRvRecommend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mSrlRecommend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mSrlRecommend.setRefreshing(false);
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
            Gson gson = new Gson();
            String s = gson.toJson(hotRecommendBean);
            if (s != null) {


                if (userDao.loadAll().size() == 0) {
                    mUser = new User((Long.valueOf(0)), "" + s);
                    long insert = userDao.insert(mUser);
                    if (insert != -1) {
                        System.out.println("---------存儲成功");
                        Toast.makeText(getActivity(), "存储成功", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println("---------存儲失敗");
                        Toast.makeText(getActivity(), "存储失败", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    List<User> users = userDao.loadAll();
                    System.out.println("------長度" + users.size());
                    for (int i = 0; i < users.size(); i++) {

                        if (!users.get(i).getList().equals("" + mUser.getList())) {
                            User user = new User((Long.valueOf(0)), "" + users.get(i).getList());
                            userDao.update(user);
                            System.out.println("---------進行修改");
                        } else {
                            System.out.println("---------存的內容不變");
                            Toast.makeText(getActivity(), "值保持不变", Toast.LENGTH_SHORT).show();
                        }

                    }

//    }


                }

                List<User> users = userDao.loadAll();
                if (users.size() > 0) {
                    String list = users.get(0).getList();
                    hotRecommendBean1 = gson.fromJson(list, HotRecommendBean.class);
                    adapter.getListHotData(hotRecommendBean1.getData().getTopic());
                } else {
                    Toast.makeText(getActivity(), "数据库为空", Toast.LENGTH_SHORT).show();
                }


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
