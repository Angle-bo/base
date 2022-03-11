package org.huluobo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class MyUdpClient {

    public static void main(String[] args) throws IOException {
        UDPclient();
    }
    /**
     * UDP客服端
     * @throws IOException
     */
    public static void  UDPclient() throws IOException {
        //192.168.2.14 8888
        // 1.定义服务器的地址、端口号、数据
        InetAddress address = InetAddress.getByName("192.168.2.87");
        int port = 8888;
        byte[] data ="我是客服端".getBytes();
        //2.创建数据报，包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(data, data.length,address,port);
        //3.创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket(null);
        //客户端绑定指定端口
        InetSocketAddress addr = new InetSocketAddress(InetAddress.getByName("192.168.2.87"), 9999);
        socket.bind(addr);
        //4.向服务器发送数据报
        socket.send(packet);

        /*
         * 接收服务器端的数据
         * */
        //1.创建数据报，用于接收服务器端响应的数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        //2.接收服务器响应的数据
        socket.receive(packet2);
        //3.读取数据
        String reply = new String(data2,0,packet2.getLength());
        System.out.println("服务器说:"+reply);
        //4.关闭资源
        socket.close();
    }
}
