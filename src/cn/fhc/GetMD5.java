package cn.fhc;

import java.io.IOException;
import java.io.OutputStream;

public class GetMD5 extends CMDTable {

    public GetMD5(String cmdName) {
        super(cmdName);
    }

    @Override
    public void callback(String s,OutputStream os) {
        super.callback(s,null);
        String[] strings = s.split(" ");
        if(strings[strings.length-1].equals(CalMD5.getMD5("C:\\Workspace\\MyWorkspace\\JavaWorkspace\\JAVA_YMODEM_FOR_STM32_IAP\\resources\\app.bin"))){
            System.out.println("MD5 is Right,so don't upgrade!");
        }else {
            System.out.println("Send Reboot");
            String cmd = "CMD0001 REBOOT\r\n";
            try {
                os.write(cmd.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
