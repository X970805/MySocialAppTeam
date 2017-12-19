package code.xp.mysocialappteam.model;

import code.xp.mysocialappteam.control.ApiInterface;
import code.xp.mysocialappteam.utils.MyApp;
import code.xp.mysocialappteam.view.bean.HotRecommendBean;
import code.xp.mysocialappteam.view.bean.MyArticleBean;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by dell on 2017/12/18.
 */

public class HotRecommendModel {
    public Observable<HotRecommendBean> getHotRecommend(String user, String flag, String fromUid, String page,String limit){
//jhjl
        Retrofit myrxtrofit = MyApp.myrxtrofit();
        ApiInterface apiInterface = myrxtrofit.create(ApiInterface.class);
        Observable<HotRecommendBean> hotRecommendBeanObservable = apiInterface.getHotRecommendBean(user,flag,fromUid,page,limit);
        return hotRecommendBeanObservable;
    }
}
