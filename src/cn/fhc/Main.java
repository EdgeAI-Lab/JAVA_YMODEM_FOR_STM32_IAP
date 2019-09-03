package cn.fhc;

import cn.fhc.ymode.YModem;
import com.fazecast.jSerialComm.SerialPort;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {

    private static PrintInfo printInfo;

    public static void main(String[] args) {
        SerialPort serialPort = SerialPort.getCommPort("com5");
        serialPort.setBaudRate(115200);
        serialPort.openPort();
        OutputStream os = serialPort.getOutputStream();
        InputStream is = serialPort.getInputStream();

        YModem yModem = new YModem(is,os);

        startPrintInfo(is);

        new Thread(new Runnable() {
            Scanner sc = new Scanner(System.in);
            @Override
            public void run() {
                while (true){
                    String in = sc.next();
                    try {
                        os.write(Integer.valueOf(in.charAt(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if("1".equals(in)){
                        stopPrintInfo();
                        try {
                            yModem.send(new File("resources/app.bin").toPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        startPrintInfo(is);
                    }
                }
            }
        }).start();
    }

    private static void startPrintInfo(InputStream is) {
        if(printInfo==null){
            printInfo = new PrintInfo(is);
            printInfo.setRunning(true);
            printInfo.start();
        }
    }

    private static void stopPrintInfo(){
        if(printInfo!=null){
            printInfo.setRunning(false);
            printInfo.interrupt();
            printInfo=null;
        }
    }
}
