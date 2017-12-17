package code.xp.mysocialappteam.model;


import code.xp.mysocialappteam.control.ApiInterface;
import code.xp.mysocialappteam.utils.MyApp;
import code.xp.mysocialappteam.view.bean.YKBean;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by 贾浪吉 on 2017/12/14.
 */

public class MyModel {

    //通过设备号得到游客的Uid
    public Observable<YKBean> getequipment(String s) {
        Retrofit myrxtrofit = MyApp.myrxtrofit();

        ApiInterface apiInterface = myrxtrofit.create(ApiInterface.class);
        Observable<YKBean> ykBean = apiInterface.ykBean(s);

        return ykBean;
    }
}
