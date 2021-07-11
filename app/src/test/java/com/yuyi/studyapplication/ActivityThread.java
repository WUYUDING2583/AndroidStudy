package com.yuyi.studyapplication;


import com.yuyi.studyapplication.core.handler.Handler;
import com.yuyi.studyapplication.core.handler.Looper;
import com.yuyi.studyapplication.core.handler.Message;

import org.junit.Test;

public class ActivityThread {


    @Test
    public void main() {
        Looper.prepareMainLooper();
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(msg.obj.toString());
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what=1;
                msg.obj="asdf";
                handler.sendMessage(msg);
            }
        }).start();

        Looper.loop();
    }
}
