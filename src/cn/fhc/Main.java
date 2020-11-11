package cn.fhc;

import cn.fhc.ymode.YModem;
import com.fazecast.jSerialComm.SerialPort;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

    private static PrintInfo printInfo;

    public static volatile int WAIT_FW_UPDATE_FLAG = 0;

    public static void main(String[] args) {
        SerialPort serialPort = SerialPort.getCommPort("com20");
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        serialPort.setBaudRate(115200);
        serialPort.openPort();
        OutputStream os = serialPort.getOutputStream();
        InputStream is = serialPort.getInputStream();
        YModem yModem = new YModem(is,os);
        startPrintInfo(is,os);

//        while (true){
            System.out.println("Get MD5 value");
            String cmd = "CMD0001 GETMD5\r\n";
            try {
                os.write(cmd.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            /* wait MCU send "fw update\r\n" */
            while (WAIT_FW_UPDATE_FLAG ==0);

            try {
                os.write(0x59);
            } catch (IOException e) {
                e.printStackTrace();
            }

            stopPrintInfo();

            try {
                yModem.send(new File("resources/app.bin").toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            startPrintInfo(is,os);
//        }
    }

    private static void startPrintInfo(InputStream is,OutputStream os) {
        if(printInfo==null){
            printInfo = new PrintInfo(is,os);
            printInfo.setRunning(true);
            printInfo.start();
        }
    }

    private static void stopPrintInfo(){
        if(printInfo!=null){
            printInfo.setRunning(false);
            printInfo.interrupt();


            try {
                printInfo.join(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printInfo.stop();
            printInfo=null;
        }
    }
}
