package com.imi.ktv.chatrobot.entity;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class SocketClient_2 {
    public static String getmessage(String message) {
        System.out.println("正在向服务器请求连接。。。");
        try {
            Socket clientSocket = new Socket("192.168.10.1", 82);
            System.out.println("Client1:" + clientSocket);

            DataInputStream dataIS = new DataInputStream(clientSocket.getInputStream());
            InputStreamReader inSR = new InputStreamReader(dataIS, "UTF-8");
            BufferedReader br = new BufferedReader(inSR);

            DataOutputStream dataOS = new DataOutputStream(clientSocket.getOutputStream());
            OutputStreamWriter outSW = new OutputStreamWriter(dataOS, "UTF-8");
            BufferedWriter bw = new BufferedWriter(outSW);

            //输入信息

            while(true) {
                System.out.println("----------------------------------");


                //发送数据
                bw.write(message + "\r\n");		//加上分行符，以便服务器按行读取
                bw.flush();


                //接收数据
                String str="";
                while((str = br.readLine()) != null) {
                    str = str.trim();
                    System.out.println("服务器回复：" + str);
                    inSR.close();
                    dataIS.close();
                    dataOS.close();
                    clientSocket.close();
                    return str;
                }

            }


        } catch(UnknownHostException uhe) {
            System.out.println("Error:" + uhe.getMessage());
            return "出现bug";
        } catch(ConnectException ce) {
            System.out.println("Error:" + ce.getMessage());
            return "出现bug";
        } catch(IOException ioe) {
            System.out.println("Error:" + ioe.getMessage());
            return "出现bug";
        } finally {
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
