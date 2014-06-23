package cn.piorpua.android.ui;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 屏幕密度辅助类<br>
 * 注：使用前请先调用 {@link#init} 进行初始化
 * @author piorpua
 * @version 2013/09/11
 * @cloud_sync
 */
public final class DensityHelper {
    
    // >>>
    // * 说明
    // * 根据 mScaledDensity（DisplayMetrics.scaledDensity）的取值，来选择 res/drawable-* 目录下的图片，具体分界值如下：
    // * drawable-ldpi: 0.75
    // * drawable-mdpi: 1.0
    // * drawable-hdpi: 1.5
    // * drawable-xdpi: 2.0
    // <<<
    
    private static Context sContext;
    private static DensityHelper sIns;
    
    /**
     * 初始化
     * @param context
     */
    synchronized public static void init(Context context) {
        if (sContext == null) {
            sContext = context;
        }
    }
    
    synchronized public static DensityHelper getInstance() {
        if (sIns == null) {
            sIns = new DensityHelper();
        }
        return sIns;
    }
    
    public float getDensityDpi() {
        return mDensityDpi;
    }
    
    public float getScaledDensity() {
        return mScaledDensity;
    }
    
    private float mDensityDpi;
    private float mScaledDensity;
    
    private DensityHelper() {
        if (sContext == null) {
            throw new IllegalStateException("Please do DensityHelper.init first!");
        }
        
        DisplayMetrics displayMetrics = sContext.getResources().getDisplayMetrics();
        mDensityDpi = displayMetrics.densityDpi;
        mScaledDensity = displayMetrics.scaledDensity;   // >>> (displayMetrics.densityDpi / 160)
    }
    
}
