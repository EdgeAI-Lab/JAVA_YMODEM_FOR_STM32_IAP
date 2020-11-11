package cn.fhc;

import java.io.IOException;
import java.io.OutputStream;

public class GetUpdate extends CMDTable{
    public GetUpdate(String cmdName) {
        super(cmdName);
    }

    @Override
    public void callback(String s,OutputStream os) {
        super.callback(s,null);
        System.out.println("Send Y");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    os.write(0x59);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.WAIT_FW_UPDATE_FLAG =1;
            }
        }).start();

    }
}
