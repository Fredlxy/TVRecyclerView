package tv.lesports.com.tvrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuyu on 17/1/20.
 * 使用RecyclerView时,所有的ViewHolder需要继承该ViewHolder
 */
public abstract class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }



    public abstract void focusIn();

    public abstract void focusOut();
}
