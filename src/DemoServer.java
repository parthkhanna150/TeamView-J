
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer {

    public static void main(String[] args) {
        
        MyServer job= new MyServer();
        Thread t1=new Thread(job);
        t1.start();
    }

}

class MyServer implements Runnable {

    ServerSocket serverSocket;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(4200);
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Server side connection accepted");
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                String msg = dis.readLine();
                System.out.println(msg);
                dos.writeBytes("hello client\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
