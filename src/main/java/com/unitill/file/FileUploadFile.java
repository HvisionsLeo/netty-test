package com.unitill.file;

import java.io.File;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Leo
 * @Date: 2018-05-02 上午 11:31
 */
public class FileUploadFile implements Serializable {


    private static final long serialVersionUID = 1L;
    private File file; //文件
    private String fileMd5;  //文件名
    private int startPos;  //开始位置
    private byte[] bytes;  //文件字节数组
    private int endPos;  //结束位置

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }
}
