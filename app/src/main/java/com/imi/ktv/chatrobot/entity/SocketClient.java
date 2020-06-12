package com.imi.ktv.chatrobot.entity;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
public class SocketClient {
    public static String getmessage(String message) {
        System.out.println("正在向服务器请求连接。。。");
        Socket socket = null;
        Scanner inScanner = null;
        PrintWriter pwtoserver = null;
        try {
            socket = new Socket("192.168.10.1", 82);
            System.out.println("连接成功！");
            inScanner = new Scanner(socket.getInputStream());
            System.out.println(inScanner.nextLine());
            pwtoserver = new PrintWriter(socket.getOutputStream());
            pwtoserver.println(message);
            pwtoserver.flush();
            String indata = inScanner.nextLine();
            System.out.println(indata);
            return indata;
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "系统忙";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "系统忙";
        }
    }
    public static ChatMessage sendMessage(String message){
        ChatMessage chatMessage = new ChatMessage();
        String gsonResult = getmessage(message);
        Gson gson = new Gson();
        Result result = null;
        if (gsonResult != null) {
            try {
                //result = gson.fromJson(gsonResult, Result.class);
                //chatMessage.setMessage(result.getText());
                chatMessage.setMessage(gsonResult);
            } catch (Exception e) {
                chatMessage.setMessage("出现bug");
            }
        }
        chatMessage.setData(new Date());
        chatMessage.setType(ChatMessage.Type.INCOUNT);
        return chatMessage;
    }
}
