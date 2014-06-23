package cn.piorpua.java.debug;

/**
 * Debug 工具类
 * @author piorpua
 * @version 2014/03/13
 * @cloud_sync
 */
public class DebugMode {
	
	/**
	 * Debug 日志开关
	 */
	private static final boolean DEBUG = false;
	
	/**
	 * Debug 标签
	 */
	private static final String TAG = "[DebugMode] ";
	
	public static void Debug(String log) {
		if (DEBUG) {
			println(attachTag(log));
		}
	}
	
	public static void BlankLines(int n) {
		if (n > 0) {
			for (int i = 0; i < n; ++i) {
				System.out.println();
			}
		}
	}
	
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void println(String log) {
		if (log != null && !log.isEmpty()) {
			System.out.println(log);
		}
	}
	
	private static String attachTag(String log) {
		if (log == null || log.isEmpty()) {
			return TAG;
		}
		return TAG + log;
	}
	
}
