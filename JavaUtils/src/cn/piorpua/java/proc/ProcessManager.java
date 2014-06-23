package cn.piorpua.java.proc;

import java.io.IOException;

/**
 * Process 管理类<br>
 * 该类主要是对 {@link java.lang.Process} 的简单封装<br>
 * 注：<b>当前仅测试于 Windows 系统</b>
 * @author piorpua
 * @version 2013/09/02
 * @cloud_sync
 */
public class ProcessManager {
    
    private static ProcessManager sIns;
    
    synchronized public static ProcessManager getInstance() {
        if (sIns == null) {
            sIns = new ProcessManager();
        }
        return sIns;
    }
    
    private Runtime mRunTime;
    
    /**
     * 执行指定运行时命令
     * @param cmd
     * @return 异常返回 null，否则返回 {@link#CmdOutput}
     */
    public CmdOutput execCmd(String cmd) {
        if (cmd == null || cmd.isEmpty()) {
            return null;
        }
        
        Process proc = null;
        try {
            proc = mRunTime.exec(cmd);
//            proc.waitFor();
            CmdOutput cmdOut = new CmdOutput();
            if (cmdOut.initOutput(proc)) {
                return cmdOut;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (proc != null) {
                proc.destroy();
            }
        }
        return null;
    }
    
    private ProcessManager() {
        mRunTime = Runtime.getRuntime();
    }
    
}
