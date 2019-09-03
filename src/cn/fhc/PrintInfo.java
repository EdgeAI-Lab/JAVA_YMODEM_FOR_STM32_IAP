package cn.fhc;

import java.io.IOException;
import java.io.InputStream;

public class PrintInfo extends Thread{

    private InputStream is;

    private boolean isRunning;
    public void setRunning(boolean running) {
        isRunning = running;
    }

    public PrintInfo(InputStream is){
        this.is = is;
    }

    int readlen;
    String getS;
    byte[] bytes = new byte[1024];

    @Override
    public void run() {
        while (isRunning && !Thread.currentThread().isInterrupted()){
            try {
                if((readlen = is.available())>0){
                    readlen = is.read(bytes,0,readlen);

                    if(readlen>0){
                        getS = new String(bytes,0,readlen);
                        System.out.print(getS);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
