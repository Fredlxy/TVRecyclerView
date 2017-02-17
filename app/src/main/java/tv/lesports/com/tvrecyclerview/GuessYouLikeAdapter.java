package tv.lesports.com.tvrecyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tv.lesports.com.tvrecyclerview.R;

/**
 * Created by liuyu on 17/1/20.
 * 防止RecyclerView更新数据的时候焦点丢失:
 * (1)adapter执行setHasStableIds(true)方法
 * (2)重写getItemId()方法,让每个view都有各自的id
 * (3)RecyclerView的动画必须去掉
 */
public class GuessYouLikeAdapter extends BaseRecyclerAdapter<GuessYouLikeAdapter.GuessYouLikeViewHolder,SearchResultModel> {


    private static final String TAG = "GuessYouLikeAdapter";
    private OnLeftEdgeListener mOnLeftEdgeListener;


    public interface OnLeftEdgeListener {
        void onLeftEdge();
    }

    public void setOnLeftEdgeListener(OnLeftEdgeListener listener) {
        this.mOnLeftEdgeListener = listener;
    }

    public GuessYouLikeAdapter(Context context, List<SearchResultModel> dataList) {
        super(context,dataList);
    }



    @Override
    public GuessYouLikeViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = mInflate.inflate(R.layout.lesports_search_result_layout, viewGroup, false);
        return new GuessYouLikeViewHolder(itemView);
    }


    @Override
    public void onBindBaseViewHolder(final GuessYouLikeViewHolder viewHolder, final int position) {

        if (getItemCount() > 0) {

            viewHolder.setData(mDataList.get(position));

            viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (hasFocus) {
                        viewHolder.focusIn();
                        FocusUtil.onFocusIn(view, 1.10f);
                    } else {
                        viewHolder.focusOut();
                        FocusUtil.onFocusOut(view, 1.10f);
                    }

                }
            });

          /*  viewHolder.itemView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_DPAD_LEFT:
                                if (position % 4 == 0 && mOnLeftEdgeListener != null) {
                                    mOnLeftEdgeListener.onLeftEdge();
                                    return true;
                                }
                        }
                    }
                    return false;
                }
            });*/
        }

    }

    public static class GuessYouLikeViewHolder extends BaseRecyclerViewHolder {
        public ImageView ivResultImage;
        public TextView tvResultTitle;


        protected GuessYouLikeViewHolder(View itemView) {
            super(itemView);
            ivResultImage = (ImageView) itemView.findViewById(R.id.iv_result_image);
            tvResultTitle = (TextView) itemView.findViewById(R.id.tv_result_title);
        }

        @Override
        public void focusIn() {
            tvResultTitle.setSelected(true);
        }

        @Override
        public void focusOut() {
            tvResultTitle.setSelected(false);
        }

        public void setData(final SearchResultModel model) {
            if (model == null) {
                return;
            }

            tvResultTitle.setSelected(false);

        }
    }
}
