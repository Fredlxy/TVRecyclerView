package tv.lesports.com.tvrecyclerview;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuyu on 17/2/20.
 * 能够进行焦点定位的LinearLayoutManager
 */
public class TvLinearLayoutManager extends LinearLayoutManager {
    public TvLinearLayoutManager(Context context) {
        super(context);
    }

    public TvLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public TvLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /**
     * Base class which scrolls to selected view in onStop().
     */
    abstract class TvLinearSmoothScroller extends LinearSmoothScroller {


        public TvLinearSmoothScroller(Context context) {
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
        TvLinearSmoothScroller linearSmoothScroller =
                new TvLinearSmoothScroller(recyclerView.getContext()) {
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
