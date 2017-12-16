package code.xp.mysocialappteam.control;




import code.xp.mysocialappteam.view.bean.YKBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 徐宏福 on 2017/12/15.
 */

public interface ApiInterface {
    @POST("api/surfers")
    Observable<YKBean> ykBean(@Field("deviceId") String deviceId);
}
