package tv.lesports.com.tvrecyclerview;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuyu on 17/2/8.
 * fix issue: RecyclerView 进行item定位的时候,item没有获取焦点
 */
public class TvGridLayoutManager extends GridLayoutManager {


    public TvGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public TvGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public TvGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    /**
     * Base class which scrolls to selected view in onStop().
     */
    abstract class GridLinearSmoothScroller extends LinearSmoothScroller {


        public GridLinearSmoothScroller(Context context) {
            super(context);
        }

        /**
         * 滑动完成后,让该targetPosition 处的item获取焦点
         */
        @Override
        protected void onStop() {
            super.onStop();
            View targetView = findViewByPosition(getTargetPosition());

            if (targetView != null) {
                targetView.requestFocus();
            }
            super.onStop();
        }

    }

    /**
     * RecyclerView的smoothScrollToPosition方法最终会执行smoothScrollToPosition
     * @param recyclerView
     * @param state
     * @param position
     */
    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state,
                                       int position) {
        GridLinearSmoothScroller linearSmoothScroller =
                new GridLinearSmoothScroller(recyclerView.getContext()) {
                    @Override
                    public PointF computeScrollVectorForPosition(int targetPosition) {
                        return computeVectorForPosition(targetPosition);
                    }
                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }


    public PointF computeVectorForPosition(int targetPosition) {
        return super.computeScrollVectorForPosition(targetPosition);
    }



}
