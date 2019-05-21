package com.example.demo;

import org.springframework.stereotype.Component;

/**
 * Created on 2019/5/9.
 *
 * @author yangsen
 */
@Component
public class MessageReceiver {

    /**接收消息的方法*/
    public void receiveMessage(String message){
        System.out.println("收到一条消息："+message);
    }

}
