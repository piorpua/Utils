package cn.piorpua.java.proc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行命令返回值类（用于 {@link#ProcessManager} 运行命令后的返回值）
 * @author piorpua
 * @version 2013/09/02
 * @cloud_sync
 */
public class CmdOutput {
    
    private List<String> mNormalList;
    private List<String> mErrorList;
    private boolean     mFlagError;
    
    public CmdOutput() {
        mNormalList = new ArrayList<String>();
        mErrorList  = new ArrayList<String>();
        mFlagError  = false;
    }
    
    /**
     * 根据运行命令后的 {@link java.lang.Process} 初始化返回值（包括正常、异常命令返回值）
     * @param proc
     * @return 初始化成功返回 <b>true</b>，反之 <b>false</b>
     */
    public boolean initOutput(Process proc) {
        if (proc != null) {
            boolean normalRet = inStream2List(proc.getInputStream(), mNormalList);
            boolean errorRet  = inStream2List(proc.getErrorStream(), mErrorList);
            if (errorRet && !mErrorList.isEmpty()) {
                mFlagError = true;
            }
            return normalRet && errorRet;
        }
        return false;
    }
    
    /**
     * 获得运行命令后的返回值<br>
     * 注：每一行一个记录，<b>不包括空行</b>
     * @return 若出现错误，返回错误流返回值，否则返回正常流返回值
     */
    public List<String> getOutput() {
        if (mFlagError) {
            return mErrorList;
        }
        return mNormalList;
    }
    
    /**
     * 返回正常流返回值<br>
     * 注：每一行一个记录，<b>不包括空行</b>
     * @return
     */
    public List<String> getNormalOutput() {
        return mNormalList;
    }
    
    /**
     * 返回错误流返回值<br>
     * 注：每一行一个记录，<b>不包括空行</b>
     * @return
     */
    public List<String> getErrorOutput() {
        return mErrorList;
    }
    
    /**
     * 判断返回值中是否包含错误流
     * @return
     */
    public boolean isError() {
        return mFlagError;
    }
    
    /**
     * 清除返回值内容及其相关标志位
     */
    public void clear() {
        mNormalList.clear();
        mErrorList.clear();
        mFlagError = false;
    }
    
    private boolean inStream2List(InputStream inStream, List<String> list) {
        if (inStream != null && list != null) {
            try {
                String line = null;
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "GBK"));
                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }
                    list.add(line);
                }
                reader.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
}