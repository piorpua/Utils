package cn.piorpua.android.ui;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/**
 * 滑动到底部监听器
 * @author piorpua
 */
public final class OnScrollBottomListener implements OnScrollListener {
	
	public interface IScrollBottomCallback {
		void onBottom(boolean isFirst);
	}
	
	private final IScrollBottomCallback mCallback;
	private final int[] mLocs = new int[2];
	private int mLastVisiblePos;
	private int mLastLocY;
	
	public OnScrollBottomListener(IScrollBottomCallback callback) {
		mCallback = callback;
	}

	@Override
	public void onScrollStateChanged(
			AbsListView view, int scrollState) {
		
		if (view == null) {
			return ;
		}
		
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			int lastVisiblePos = view.getLastVisiblePosition();
			
            // 滚动到底部
            if (lastVisiblePos == (view.getCount() - 1)) {
            	int childCnt = view.getChildCount();
            	if (childCnt < 0) {
            		return ;
            	}
            	
                View childView = (View) view.getChildAt(childCnt - 1);
                if (childView == null) {
                	return ;
                }
                
                childView.getLocationOnScreen(mLocs);   // 获取在整个屏幕内的绝对坐标
                int locY = mLocs[1];
                
                if (lastVisiblePos != mLastVisiblePos && 
                		mLastLocY != locY) {   // 第一次拖至底部
                	
                	mLastVisiblePos = lastVisiblePos;
                	mLastLocY = locY;
                	if (mCallback != null) {
                		mCallback.onBottom(true);
                	}
                	
                    return ;
                    
                } else if (lastVisiblePos == mLastVisiblePos && 
                		mLastLocY == locY) {   // 第二次拖至底部
                	
                	if (mCallback != null) {
                		mCallback.onBottom(false);
                	}
                }
            }
            
            // 未滚动到底部，第二次拖至底部都初始化
            mLastVisiblePos = 0;
            mLastLocY = 0;
        }
		
	}

	@Override
	public void onScroll(
			AbsListView view, int firstVisibleItem, 
			int visibleItemCount, int totalItemCount) {
		
		// TODO Auto-generated method stub
	}

}
