
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class PhotoServer implements Runnable {

    PhotoServer() {
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            ServerSocket sersock = new ServerSocket(9700);
            while (true) {
                Socket sock = sersock.accept();
                PhotoHandler obj = new PhotoHandler(sock);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class PhotoHandler implements Runnable {

        Socket sock1;
        DataOutputStream dos;
        DataInputStream dis;

        PhotoHandler(Socket sock) {
            this.sock1 = sock;
            try {
                dos = new DataOutputStream(sock.getOutputStream());
                dis = new DataInputStream(sock.getInputStream());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Thread t = new Thread(this);
            t.start();
        }

        public void run() {
            int i = 0;

            try {
                while (true) {
                    File f=new File("d:\\screenshots");
                    if(f.exists()==false){
                        f.mkdir();
                    }
                    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                    dos.writeDouble(d.getHeight());
                    dos.writeDouble(d.getWidth());
                    Robot rbt = new Robot();
                    BufferedImage bimg = rbt.createScreenCapture(new Rectangle(d));
                    ImageIO.write(bimg, "jpg", new File("d:\\screenshots\\" + i + ".jpg"));
                    f = new File("d:\\screenshots\\" + i + ".jpg");
                    FileInputStream fis = new FileInputStream(f);
                    dos.writeLong(f.length());
                    int k = 0;
                    byte b[] = new byte[10000];
                    while (f.length() >= k) {
                        k = fis.read(b, 0, 10000);
                        if (k == -1) {
                            break;
                        }
                        dos.write(b, 0, k);
                    }
                    String s = dis.readLine();
                    System.out.println(s);
                    f.delete();
                    i++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        PhotoServer obj = new PhotoServer();
    }
}
