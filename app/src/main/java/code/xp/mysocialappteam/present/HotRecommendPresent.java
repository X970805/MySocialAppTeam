package code.xp.mysocialappteam.present;

import android.content.Context;
import android.widget.Toast;

import code.xp.mysocialappteam.control.MyControl;
import code.xp.mysocialappteam.control.RecommendControl;
import code.xp.mysocialappteam.model.HotRecommendModel;
import code.xp.mysocialappteam.view.bean.HotRecommendBean;
import code.xp.mysocialappteam.view.bean.MyArticleBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2017/12/18.
 */

public class HotRecommendPresent {
    private HotRecommendModel hotRecommendModel;
    private RecommendControl myControl;
    public Context context;

    public HotRecommendPresent(Context context, RecommendControl c) {
        this.myControl = c;
        this.context = context;
        hotRecommendModel = new HotRecommendModel();
    }

    public void setHotRecommendModel(String user, String flag, String fromUid, String page, String limit) {
        Observable<HotRecommendBean> hotRecommendBeanObservable = hotRecommendModel.getHotRecommend(user, flag, fromUid, page, limit);
        if (hotRecommendBeanObservable == null) {
            return;
        } else {
            hotRecommendBeanObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<HotRecommendBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(HotRecommendBean hotRecommendBean) {
                            myControl.getHotRecommend(hotRecommendBean);
                            //  myControl.getHotRecommendData(hotRecommendBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            //   System.out.println("setHotRecommendModel onError "+e);
                            //Toast.makeText(context, "请打开你的网络或wife", Toast.LENGTH_SHORT).show();
                            myControl.getHotRecommend(null);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
