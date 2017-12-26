package code.xp.mysocialappteam.present;

import code.xp.mysocialappteam.control.MyControl;
import code.xp.mysocialappteam.model.MyModel;
import code.xp.mysocialappteam.view.bean.YKBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2017/12/14.
 */

public class MyPresent {

    private final MyModel myModel;
    private MyControl c;

    public MyPresent(MyControl c) {
        myModel = new MyModel();
        this.c = c;
    }

    //通过设备号得到uuid  s为设备号
    public void setequipment(String uuid) {
        Observable<YKBean> getequipment = myModel.getequipment(uuid);
        if (getequipment == null) {
            return;
        } else {
            getequipment.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<YKBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                        @Override
                        public void onNext(YKBean ykBean) {
                            c.equipment(ykBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                           // System.out.println("onError"+e);
                            c.equipment(null);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}