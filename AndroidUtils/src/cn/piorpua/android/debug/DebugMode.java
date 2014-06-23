package cn.piorpua.android.debug;

import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;
import android.util.Log;

/**
 * Debug 工具类
 * @author piorpua
 * @version 2013/08/20
 * @cloud_sync
 */
public final class DebugMode {
    
    /**
     * Debug 日志开关
     */
    public static boolean EnableLog = false;
    
    /**
     * Debug 标签
     */
    public static String   DebugTag = "DebugMode";
    
    /**
     * 计时开始
     * @param tag !TextUtils.isEmpty(tag)
     */
    public static void Tick(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            sTickTockMap.put(tag, System.currentTimeMillis());
        }
    }
    
    /**
     * 计时结束，并且日志输出(Verbose)
     * @param tag !TextUtils.isEmpty(tag) 并且与 {@link #Tick(String tag)} 联用(tag一致)
     */
    public static void Tock_V(String tag) {
        if (!TextUtils.isEmpty(tag) && sTickTockMap.containsKey(tag)) {
            LOG_V(tag, genTickTockLog(sTickTockMap.get(tag)));
            sTickTockMap.remove(tag);
        }
    }
    
    /**
     * 计时结束，并且日志输出(Debug)
     * @param tag !TextUtils.isEmpty(tag) 并且与 {@link #Tick(String tag)} 联用(tag一致)
     */
    public static void Tock_D(String tag) {
        if (!TextUtils.isEmpty(tag) && sTickTockMap.containsKey(tag)) {
            LOG_D(tag, genTickTockLog(sTickTockMap.get(tag)));
            sTickTockMap.remove(tag);
        }
    }
    
    /**
     * 计时结束，并且日志输出(Info)
     * @param tag !TextUtils.isEmpty(tag) 并且与 {@link #Tick(String tag)} 联用(tag一致)
     */
    public static void Tock_I(String tag) {
        if (!TextUtils.isEmpty(tag) && sTickTockMap.containsKey(tag)) {
            LOG_I(tag, genTickTockLog(sTickTockMap.get(tag)));
            sTickTockMap.remove(tag);
        }
    }
    
    /**
     * 计时结束，并且日志输出(Warn)
     * @param tag !TextUtils.isEmpty(tag) 并且与 {@link #Tick(String tag)} 联用(tag一致)
     */
    public static void Tock_W(String tag) {
        if (!TextUtils.isEmpty(tag) && sTickTockMap.containsKey(tag)) {
            LOG_W(tag, genTickTockLog(sTickTockMap.get(tag)));
            sTickTockMap.remove(tag);
        }
    }
    
    /**
     * 计时结束，并且日志输出(Error)
     * @param tag !TextUtils.isEmpty(tag) 并且与 {@link #Tick(String tag)} 联用(tag一致)
     */
    public static void Tock_E(String tag) {
        if (!TextUtils.isEmpty(tag) && sTickTockMap.containsKey(tag)) {
            LOG_E(tag, genTickTockLog(sTickTockMap.get(tag)));
            sTickTockMap.remove(tag);
        }
    }
    
    /**
     * Log Verbose
     * @param tag
     * @param log
     */
    public static void LOG_V(String tag, String log) {
        log_verbose(logWithTag(tag, log));
    }
    
    /**
     * Log Debug
     * @param tag
     * @param log
     */
    public static void LOG_D(String tag, String log) {
        log_debug(logWithTag(tag, log));
    }
    
    /**
     * Log Info
     * @param tag
     * @param log
     */
    public static void LOG_I(String tag, String log) {
        log_info(logWithTag(tag, log));
    }
    
    /**
     * Log Warn
     * @param tag
     * @param log
     */
    public static void LOG_W(String tag, String log) {
        log_warn(logWithTag(tag, log));
    }
    
    /**
     * Log Error
     * @param tag
     * @param log
     */
    public static void LOG_E(String tag, String log) {
        log_error(logWithTag(tag, log));
    }
    
    /**
     * 条件日志输出(Verbose)
     * @param tag
     * @param log
     * @param condition
     */
    public static void LOG_V_If(String tag, String log, boolean condition) {
        if (condition) {
            LOG_V(tag, log);
        }
    }
    
    /**
     * 条件日志输出(Debug)
     * @param tag
     * @param log
     * @param condition
     */
    public static void LOG_D_If(String tag, String log, boolean condition) {
        if (condition) {
            LOG_D(tag, log);
        }
    }
    
    /**
     * 条件日志输出(Info)
     * @param tag
     * @param log
     * @param condition
     */
    public static void LOG_I_If(String tag, String log, boolean condition) {
        if (condition) {
            LOG_I(tag, log);
        }
    }
    
    /**
     * 条件日志输出(Warn)
     * @param tag
     * @param log
     * @param condition
     */
    public static void LOG_W_If(String tag, String log, boolean condition) {
        if (condition) {
            LOG_W(tag, log);
        }
    }
    
    /**
     * 条件日志输出(Error)
     * @param tag
     * @param log
     * @param condition
     */
    public static void LOG_E_If(String tag, String log, boolean condition) {
        if (condition) {
            LOG_E(tag, log);
        }
    }
    
    /**
     * 输出当前 Pid & Tid(Verbose)
     * @param tag
     */
    public static void LOG_V_ID(String tag) {
        LOG_V(tag, getFormatID());
    }
    
    /**
     * 输出当前 Pid & Tid(Debug)
     * @param tag
     */
    public static void LOG_D_ID(String tag) {
        LOG_D(tag, getFormatID());
    }
    
    /**
     * 输出当前 Pid & Tid(Info)
     * @param tag
     */
    public static void LOG_I_ID(String tag) {
        LOG_I(tag, getFormatID());
    }
    
    /**
     * 输出当前 Pid & Tid(Warn)
     * @param tag
     */
    public static void LOG_W_ID(String tag) {
        LOG_W(tag, getFormatID());
    }
    
    /**
     * 输出当前 Pid & Tid(Error)
     * @param tag
     */
    public static void LOG_E_ID(String tag) {
        LOG_E(tag, getFormatID());
    }
    
    /**
     * 输出当前栈信息(new Exception)
     * @param tag
     */
    public static void printStack(String tag) {
        if (EnableLog) {
            new Exception(tag).printStackTrace();
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private static Map<String, Long> sTickTockMap = new HashMap<String, Long>();
    
    private static String genTickTockLog(long tickTime) {
        long tockTime = System.currentTimeMillis();
        return (tockTime - tickTime) + " ms";
    }
    
    /**
     * Pid & Tid
     * @return
     */
    private static String getFormatID() {
        return String.format(
                "pid:%d tid:%d", getPid(), getTid());
    }
    
    private static int getPid() {
        return android.os.Process.myPid();
    }
    
    private static int getTid() {
        return android.os.Process.myTid();
    }
    
    /**
     * 返回经处理过的包含 tag 的 log 信息字符串
     * @param tag
     * @param log
     * @return
     */
    private static String logWithTag(String tag, String log) {
        return TextUtils.isEmpty(tag) ? log : tag + " : " + log;
    }
    
    
    private static void log_verbose(String log) {
        if (EnableLog && !TextUtils.isEmpty(log)) {
            Log.v(DebugTag, log);
        }
    }
    
    private static void log_debug(String log) {
        if (EnableLog && !TextUtils.isEmpty(log)) {
            Log.d(DebugTag, log);
        }
    }
    
    private static void log_info(String log) {
        if (EnableLog && !TextUtils.isEmpty(log)) {
            Log.i(DebugTag, log);
        }
    }
    
    private static void log_warn(String log) {
        if (EnableLog && !TextUtils.isEmpty(log)) {
            Log.w(DebugTag, log);
        }
    }
    
    private static void log_error(String log) {
        if (EnableLog && !TextUtils.isEmpty(log)) {
            Log.e(DebugTag, log);
        }
    }
    
}
