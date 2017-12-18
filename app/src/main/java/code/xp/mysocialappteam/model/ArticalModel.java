package code.xp.mysocialappteam.model;


import code.xp.mysocialappteam.control.ApiInterface;
import code.xp.mysocialappteam.utils.MyApp;
import code.xp.mysocialappteam.view.bean.MyArticleBean;
import code.xp.mysocialappteam.view.bean.YKBean;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by dell on 2017/12/18.
 */

public class ArticalModel {

    public Observable<MyArticleBean> getMyArtical(String user, String flag, String fromUid, String page){
        Retrofit myrxtrofit = MyApp.myrxtrofit();
        ApiInterface apiInterface = myrxtrofit.create(ApiInterface.class);
        Observable<MyArticleBean> articalBean = apiInterface.getMyArticalBean(user,flag,fromUid,page);
        return articalBean;
    }
}
