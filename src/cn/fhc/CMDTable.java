package cn.fhc;

import java.io.OutputStream;

public class CMDTable {
    public String cmdName;

    public CMDTable(String cmdName){
        this.cmdName = cmdName;
    }

    public void callback(String s,OutputStream os){
        System.out.println(s);
    }
}
