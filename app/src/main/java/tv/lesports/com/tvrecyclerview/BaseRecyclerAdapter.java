package tv.lesports.com.tvrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by liuyu on 17/2/15.
 * <p>
 * note:防止RecyclerView更新数据的时候焦点丢失
 * <p>
 * (1)adapter执行setHasStableIds(true)方法
 * (2)重写getItemId()方法,让每个view都有各自的id
 * (3)RecyclerView的动画必须去掉
 */
public abstract class BaseRecyclerAdapter<VH extends BaseRecyclerViewHolder, T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    public LayoutInflater mInflate;

    public List<T> mDataList;

    public BaseRecyclerAdapter(Context context, List<T> dataList) {
        this.mInflate = LayoutInflater.from(context);
        this.mDataList = dataList;
        setHasStableIds(true);
    }

    public void setDataList(List<T> dataList) {
        this.mDataList = dataList;
    }

    public void addDataList(List<T> dataList) {
        this.mDataList.addAll(dataList);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public List<T> getDataList() {
        return mDataList;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        onBindBaseViewHolder((VH) holder, position);
    }

    protected abstract void onBindBaseViewHolder(VH viewHolder, int position);

    /**
     * @param hasStableIds 有多个observer的话会报错
     */
    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

}
