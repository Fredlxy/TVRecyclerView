package tv.lesports.com.tvrecyclerview;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * @创建人 weishukai
 * @创建时间 16/10/20 下午7:44
 * @类描述 操作获取和失去焦点的动画
 */
public class FocusUtil {

    private final static int duration = 140;

    private final static float startScale = 1.0f;

    //private final static float endScale = 1.14f;

    public static final float SCALE_RATE = 1.045f;//一

   // public static final float SCALE_RATE = 1.0571f;

    /**
     * 当焦点发生变化
     *
     * @param view
     * @param gainFocus
     */
    public static void onFocusChange(View view, boolean gainFocus) {
        if (gainFocus) {
            onFocusIn(view,SCALE_RATE);
        } else {
            onFocusOut(view,SCALE_RATE);
        }
    }

    /**
     * 当view获得焦点
     *
     * @param view
     */
    public static void onFocusIn(final View view,float endScale) {

        ValueAnimator animIn = ValueAnimator.ofFloat(startScale, endScale);
        animIn.setDuration(duration);
        animIn.setInterpolator(new DecelerateInterpolator());
        animIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                view.setScaleX(value);
                view.setScaleY(value);
            }
        });
        animIn.start();

    }

    /**
     * 当view失去焦点
     *
     * @param view
     */
    public static void onFocusOut(final View view,float endScale) {
        ValueAnimator animOut = ValueAnimator.ofFloat(endScale, startScale);
        animOut.setDuration(duration);
        animOut.setInterpolator(new DecelerateInterpolator());
        animOut.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                view.setScaleX(value);
                view.setScaleY(value);
            }
        });

        animOut.start();
    }

}
