
import java.net.*;
import java.io.*;

public class Client {

    Client() {
        try {
            Socket sock = new Socket("127.0.0.1", 9900);
            DataInputStream dis = new DataInputStream(sock.getInputStream());
            String s = dis.readLine();
            System.out.println(s);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client obj = new Client();
    }
}
