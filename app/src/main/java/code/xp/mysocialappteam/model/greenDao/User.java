package code.xp.mysocialappteam.model.greenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

import code.xp.mysocialappteam.view.bean.HotRecommendBean;

/**
 * Created by 徐宏福 on 2017/12/19.
 */

@Entity
public class User {
    @Id
    private String list;
    private Long id;
    @Transient
    private int tempUsageCount; // not persisted
    public String getList() {
        return this.list;
    }
    public void setList(String list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 873297011)
    public User(Long id, String list) {
        this.id=id;
        this.list = list;
    }
}
