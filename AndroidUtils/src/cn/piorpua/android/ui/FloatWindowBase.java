package cn.piorpua.android.ui;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * 浮动窗口基类<br>
 * 注: 应用需要 <b>android.permission.SYSTEM_ALERT_WINDOW</b> 权限
 * @author piorpua
 * @version 2013/09/10
 * @cloud_sync
 */
public abstract class FloatWindowBase {
    
    protected Context       mContext;
    protected WindowManager mWindowManager;
    
    /**
     * 子类可重新生成该布局参数，否则使用默认布局参数 {@link#getDefaultWinLayoutParam}
     */
    protected WindowManager.LayoutParams mLayoutParams;
    
    /**
     * 浮动窗中显示的视图
     */
    protected View mView;
    
    /**
     * 浮动窗的显示状态
     */
    protected boolean mShow;
    
    /**
     * 子类负责创建具体视图 {@link#mView}
     */
    public abstract void attachView();
    
    public FloatWindowBase(Context context) {
        init(context);
    }
    
    public boolean isShow() {
        return mShow;
    }
    
    /**
     * 显示浮动窗
     */
    public void show() {
        if (!mShow) {
            if (mWindowManager != null && mView != null && mLayoutParams != null) {
                try {
                    mWindowManager.addView(mView, mLayoutParams);
                    mShow = true;
                } catch (Exception e) {
                }
            }
        }
    }
    
    /**
     * 隐藏浮动窗
     */
    public void hide() {
        if (mShow) {
            if (mWindowManager != null && mView != null) {
                try {
                    mWindowManager.removeView(mView);
                    mShow = false;
                } catch (Exception e) {
                }
            }
        }
    }
    
    /**
     * 更新浮动窗状态（如位置，具体根据 {@link#mLayoutParams} 配置）
     */
    public void update() {
        if (mShow) {
            if (mWindowManager != null && mView != null && mLayoutParams != null) {
                try {
                    mWindowManager.updateViewLayout(mView, mLayoutParams);
                } catch (Exception e) {
                }
            }
        }
    }
    
    private void init(Context context) {
        mContext       = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams  = getDefaultWinLayoutParam();
        mShow          = false;
        attachView();
    }
    
    /**
     * 获取默认布局参数
     * @return
     */
    private WindowManager.LayoutParams getDefaultWinLayoutParam() {
        WindowManager.LayoutParams winLayoutParam = new WindowManager.LayoutParams();
        
        winLayoutParam.type    = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;  // android.permission.SYSTEM_ALERT_WINDOW
        winLayoutParam.flags   = LayoutParams.FLAG_NOT_FOCUSABLE;
        winLayoutParam.width   = LayoutParams.WRAP_CONTENT;
        winLayoutParam.height  = LayoutParams.WRAP_CONTENT;
        winLayoutParam.gravity = Gravity.CENTER;
        winLayoutParam.format  = PixelFormat.RGBA_8888;   // 设置之后可以看到图片的显示效果就和在PC上看到一样，不会出现带状的轮廓线
        
        return winLayoutParam;
    }
    
}
