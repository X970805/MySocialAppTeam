package code.xp.mysocialappteam.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.view.bean.HotRecommendBean;

/**
 * 作者-->蜕变~成蝶
 * 创建于-->2017/12/16
 * 作用-->
 */

public class HomeHotAdapter extends RecyclerView.Adapter<HomeHotAdapter.HomeHotHolder>{

    private List<HotRecommendBean.DataBean.TopicBean> list=new ArrayList<>();
    private Context context;

    public HomeHotAdapter(Context context) {
        this.context = context;
    }

    public void setHotListData(List<HotRecommendBean.DataBean.TopicBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public HomeHotHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=View.inflate(context, R.layout.recommend2_item_item,null);

        final HomeHotHolder holder=new HomeHotHolder(view);

        if(listner!=null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onItemClick(v,holder.getLayoutPosition());
                }
            });
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(HomeHotHolder holder, int position) {
        Glide.with(context).load(list.get(position).getLogo()).into(holder.hot_item);
        holder.hot_title.setText(list.get(position).getTitle());
        holder.hot_text.setText(list.get(position).getComment_count()+"人参与");
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    class HomeHotHolder extends RecyclerView.ViewHolder {

        private ImageView hot_item;
        private TextView hot_title;
        private TextView hot_text;

        public HomeHotHolder(View itemView) {
            super(itemView);

            hot_item = itemView.findViewById(R.id.hot_item);
            hot_title = itemView.findViewById(R.id.hot_title);
            hot_text = itemView.findViewById(R.id.hot_text);

        }
    }

    //点击事件；
    private RecyclvAdapter.OnClickRecyclerListner listner;
    //设置点击事件；
    public void setLisner(RecyclvAdapter.OnClickRecyclerListner lisner) {
        this.listner = lisner;
    }

    //创建一个接口的内部类 或者直接在外面创建也是可以的
    public interface OnClickRecyclerListner {
        //点击事件
        void onItemClick(View view, int position);
    }

}
