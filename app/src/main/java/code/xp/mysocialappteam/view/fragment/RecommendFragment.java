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
    private Gson gson;
    private User user;
    private List<User> greendaoall;
    private MyArticleBean myArticleBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommendfragment_layout, container, false);
        initView(view);

        userDao = MyApp.getInstances().getDaoSession().getUserDao();
        greendaoall = userDao.loadAll();
        for (int i = 0; i <greendaoall.size() ; i++) {
            System.out.println( "第一次------"+i+"--"+  greendaoall.get(i).getList());
        }
        mRvRecommend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mSrlRecommend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mSrlRecommend.setRefreshing(false);
            }
        });
        adapter = new RecyclvAdapter(getActivity());

        mRvRecommend.setAdapter(adapter);

        gson = new Gson();
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
        if (articleBean != null) {
            if (articleBean.getCode() != 200) {
                Toast.makeText(getActivity(), "" + articleBean.getMsg(), Toast.LENGTH_SHORT).show();
            } else {

                String s = gson.toJson(articleBean);
                user = new User(Long.valueOf(0), s);

                if (userDao.loadAll().size() == 0) {
                 userDao.insert(user);

              } else {
                   if (!user.getList().equals(greendaoall.get(0).getList())) {
                        userDao.update(user);
                    }
                }
                for (int i = 0; i <greendaoall.size() ; i++) {
                    System.out.println( "第二次--------"+  greendaoall.get(i).getList());
                }
            }

        }
    }

    @Override
    public void getHotRecommend(HotRecommendBean hotRecommendBean) {
        System.out.println("------code--------"+hotRecommendBean.getCode());
        if (hotRecommendBean != null) {
            if (hotRecommendBean.getCode() != 200) {
          return;
           } else {

                String s = gson.toJson( hotRecommendBean);
                if (s != null) {

                    mUser = new User((Long.valueOf(1)), s);
                    if (userDao.loadAll().size() ==1) {

                        userDao.insert(mUser);

                    } else {


                        for (int i = 0; i < greendaoall.size(); i++) {

                            if (!greendaoall.get(0).getList().equals(mUser.getList())) {
                                User user1 = new User((Long.valueOf(1)), greendaoall.get(i).getList());
                                userDao.update(user1);}

                        }


                    }
                    for (int i = 0; i <greendaoall.size() ; i++) {
                        System.out.println( "第三次--------"+  greendaoall.get(i).getList());
                    }
                    getMyWenZhang();
                    getAdapter();

                }

            }
        } else {
            if (greendaoall.size() > 0) {
                getMyWenZhang();
                getAdapter();
            } else {
                Toast.makeText(getActivity(), "请链接你的网络或者wife------为空", Toast.LENGTH_SHORT).show();
            }


        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getFromUid(String s) {
        ArticalPresent articalPresent = new ArticalPresent(this);
        articalPresent.setArticalModel("surfer", "index2", s, "1");
        HotRecommendPresent hotRecommendPresent = new HotRecommendPresent(getActivity(), this);
        hotRecommendPresent.setHotRecommendModel("surfer", "index", s, "1", "5");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void getAdapter() {

        List<User> users = userDao.loadAll();
        if (users.size() != 0) {
            String list = users.get(1).getList();

            hotRecommendBean1 = gson.fromJson(list, HotRecommendBean.class);
            adapter.getListHotData(hotRecommendBean1.getData().getTopic());
            adapter.notifyDataSetChanged();
        } else {
            for (int i=0;i<greendaoall.size();i++)
            {
                System.out.println("--------------------------------------------"+greendaoall.get(i).getList());
            }
            Toast.makeText(getActivity(), "数据库为空", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    public void getMyWenZhang() {


        if (greendaoall.size()>0) {

            String list = greendaoall.get(0).getList();
            MyArticleBean myArticleBean = gson.fromJson(list, MyArticleBean.class);
            adapter.getListData(myArticleBean.getData().getArticle());
            adapter.notifyDataSetChanged();
        } else {
           // Toast.makeText(getActivity(), "-------文章值不变", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}
