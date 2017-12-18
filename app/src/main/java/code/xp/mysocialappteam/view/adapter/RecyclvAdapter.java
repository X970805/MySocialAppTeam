package code.xp.mysocialappteam.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.view.bean.HotRecommendBean;
import code.xp.mysocialappteam.view.bean.MyArticleBean;

/**
 * 作者-->蜕变~成蝶
 * 创建于-->2017/12/15
 * 作用-->
 */

public class RecyclvAdapter extends RecyclerView.Adapter<RecyclvAdapter.RecommendHolder> {

    private List<MyArticleBean.DataBean.ArticleBean> list = new ArrayList<>();
    private Context context;
    private HomeHotAdapter adapter;
    private List<HotRecommendBean.DataBean.TopicBean> listHot = new ArrayList<>();


    public RecyclvAdapter(Context context) {
        this.context = context;
        adapter = new HomeHotAdapter(context);
    }

    public void getListData(List<MyArticleBean.DataBean.ArticleBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void getListHotData(List<HotRecommendBean.DataBean.TopicBean> listHot) {
        this.listHot = listHot;
        adapter.setHotListData(listHot);
        notifyDataSetChanged();
    }

    @Override
    public RecommendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 0:
                view = View.inflate(context, R.layout.recommendfragment1_item, null);
                break;

            case 1:
                view = View.inflate(context, R.layout.recommendfragment2_item, null);
                break;

        }

        final RecommendHolder holder = new RecommendHolder(view);

        if (listner != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecommendHolder holder, int position) {

        int type = getItemViewType(position);
        switch (type) {
            case 0:
                Glide.with(context).load(list.get(position).getFace()).into(holder.mImgArtical);
                holder.mPingLunNum.setText(list.get(position).getAid());
                holder.mXiHuanNum.setText(list.get(position).getRead_count());
                holder.mTitleArtical.setText(list.get(position).getTitle());
                holder.mDate.setText(list.get(position).getCreate_time());
                break;

            case 1:
                holder.hot_recylv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
                holder.hot_recylv.setAdapter(adapter);
                break;

        }


    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position != 2) {
            return 0;
        }
        return 1;
    }

    class RecommendHolder extends RecyclerView.ViewHolder {
        private ImageView mImgArtical;
        private TextView mTitleArtical;
        private ImageView mPingLun;
        private TextView mPingLunNum;
        private ImageView mXiHuan;
        private TextView mXiHuanNum;
        private TextView mDate;
        private TextView mGuanZhu;
        private RecyclerView hot_recylv;

        public RecommendHolder(View itemView) {
            super(itemView);
            mImgArtical =  itemView.findViewById(R.id.artical_img);
            mTitleArtical =  itemView.findViewById(R.id.artical_title);
            mPingLun =  itemView.findViewById(R.id.pingLun);
            mPingLunNum =  itemView.findViewById(R.id.pingLunNum);
            mXiHuan =  itemView.findViewById(R.id.xiHuan);
            mXiHuanNum =  itemView.findViewById(R.id.xiHuanNum);
            mDate =  itemView.findViewById(R.id.date);
            mGuanZhu =  itemView.findViewById(R.id.guanZhu);
            hot_recylv= itemView.findViewById(R.id.hot_receclv);
        }
    }

    //点击事件；
    private OnClickRecyclerListner listner;

    //设置点击事件；
    public void setLisner(OnClickRecyclerListner lisner) {
        this.listner = lisner;
    }

    //创建一个接口的内部类 或者直接在外面创建也是可以的
    public interface OnClickRecyclerListner {
        //点击事件
        void onItemClick(View view, int position);
    }

}
