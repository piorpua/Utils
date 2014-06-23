package cn.piorpua.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 可旋转标签式 View
 * @author piorpua
 *
 */
public class RotateLabelView extends ViewGroup {
    
    private View mChildView;
    private float mDegree;
    
    private int mWidthMeasureActual;
    private int mHeightMeasureActual;
    private int mChildWidth;
    private int mChildHeight;
    
    public RotateLabelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public RotateLabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public RotateLabelView(Context context) {
        super(context);
    }
    
    /**
     * 设置旋转角度
     * @param degree (-90.0, 90.0)
     */
    public void setRotateDegree(float degree) {
        if (Float.compare(degree, -90.0f) > 0 && Float.compare(degree, 90.0f) < 0) {
            mDegree = degree;
            invalidate();
        } else {
            throw new IllegalArgumentException("Rotate degree out of range (-90.0, 90.0).");
        }
    }
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            final View childView = getChildView();
            childView.layout(
                    (mWidthMeasureActual - mChildWidth) / 2, (mHeightMeasureActual - mChildHeight) / 2,
                    (mWidthMeasureActual + mChildWidth) / 2, (mHeightMeasureActual + mChildHeight) / 2);
        }
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final View childView = getChildView();
        measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        mChildWidth = childView.getMeasuredWidth();
        mChildHeight = childView.getMeasuredHeight();
        mWidthMeasureActual = (int) (
                mChildWidth * Math.cos(mDegree * Math.PI / 180.0f) +
                mChildHeight * Math.sin(mDegree * Math.PI / 180.0f));
        mHeightMeasureActual = (int) (
                mChildWidth * Math.sin(mDegree * Math.PI / 180.0f) +
                mChildHeight * Math.cos(mDegree * Math.PI / 180.0f));
        
        setMeasuredDimension(mWidthMeasureActual, mHeightMeasureActual);
    }
    
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(mDegree, mWidthMeasureActual / 2.0f, mHeightMeasureActual / 2.0f);
        super.dispatchDraw(canvas);
        canvas.restore();
    }
    
    private View getChildView() {
        if (mChildView == null) {
            int childCnt = getChildCount();
            if (childCnt != 1) {
                throw new IllegalArgumentException("There should be only one child view.");
            }
            mChildView = getChildAt(0);
        }
        return mChildView;
    }
    
}