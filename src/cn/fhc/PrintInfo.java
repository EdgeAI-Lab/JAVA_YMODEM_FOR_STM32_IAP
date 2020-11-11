package cn.fhc;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrintInfo extends Thread{

    private BufferedReader bufferedReader;
    private OutputStream os;
    private boolean isRunning;
    public void setRunning(boolean running) {
        isRunning = running;
    }

    public PrintInfo(InputStream is, OutputStream os){
        bufferedReader = new BufferedReader(new InputStreamReader(is));
        this.os = os;
    }

    CMDTable GetMD5 = new GetMD5("fw md5sum");
    CMDTable GetUpdate = new GetUpdate("fw update");

    List<CMDTable> list = new ArrayList<>();

    @Override
    public void run() {

        list.add(GetMD5);
        list.add(GetUpdate);

        while (isRunning && !Thread.currentThread().isInterrupted()){
            try {
                String line = bufferedReader.readLine();
                if(!line.isEmpty()){
                    for (int i = 0; i < list.size(); i++) {
                        if(line.contains(list.get(i).cmdName)){
                            list.get(i).callback(line,os);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("exit");
    }
}
