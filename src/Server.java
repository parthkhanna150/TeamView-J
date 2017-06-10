
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;

public class Server {

    public class ServerCode implements Runnable {

        NewJFrame5 obj;

        ServerCode() {
            Thread t = new Thread(this);
            t.start();
        }

        public void run() {
            try {
                ServerSocket sersock = new ServerSocket(9900);
                while (true) {
                    Socket sock = sersock.accept();
                    ClientHandler obj = new ClientHandler(sock);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        class ClientHandler implements Runnable {

            Socket sock1;
            DataOutputStream dos;
            DataInputStream dis;

            ClientHandler(Socket sock) {
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
                try {
                    while (true) {
                        String s = dis.readLine();
                        System.out.println(s);
                        if (s.equals("mouse pressed")) {
                            int x = dis.readInt();
                            int y = dis.readInt();
                            int Button = dis.readInt();
                            System.out.println("pressed x = " + x + " y = " + y);
                            if (Button == 1) {
                                System.out.println("left click");
                            } else if (Button == 3) {
                                System.out.println("right click");
                            }
                            Robot rbt = new Robot();
                            rbt.mouseMove(x, y);
                            if (Button == 1) {
                                rbt.mousePress(MouseEvent.BUTTON1_MASK);
                            } else if (Button == 2) {
                                rbt.mousePress(MouseEvent.BUTTON2_MASK);
                            } else if (Button == 3) {
                                rbt.mousePress(MouseEvent.BUTTON3_MASK);
                            }
                        } else if (s.equals("mouse released")) {
                            int x = dis.readInt();
                            int y = dis.readInt();
                            int Button = dis.readInt();
                            System.out.println("pressed x = " + x + " y = " + y);
                            if (Button == 1) {
                                System.out.println("left click");
                            } else if (Button == 3) {
                                System.out.println("right click");
                            }
                            Robot rbt = new Robot();
                            rbt.mouseMove(x, y);
                            if (Button == 1) {
                                rbt.mouseRelease(MouseEvent.BUTTON1_MASK);
                            } else if (Button == 2) {
                                rbt.mouseRelease(MouseEvent.BUTTON2_MASK);
                            } else if (Button == 3) {
                                rbt.mouseRelease(MouseEvent.BUTTON3_MASK);
                            }
                        } else if (s.equals("key pressed")) {
                            int keycode = dis.readInt();
                            System.out.println(keycode);
                            Robot rbt = new Robot();
                            rbt.keyPress(keycode);
                        } else if (s.equals("key released")) {
                            int keycode = dis.readInt();
                            System.out.println(keycode);
                            Robot rbt = new Robot();
                            rbt.keyRelease(keycode);
                        } else if (s.equals("shutdown")) {
                            Runtime.getRuntime().exec("shutdown -s -f");
                        } else if (s.equals("restart")) {
                            Runtime.getRuntime().exec("shutdown -r -f");
                        } else if (s.equals("sending message")) {
                            String msg = dis.readLine();
                            msg = msg.replace(":new", "\r");
                            msg = msg.replace("_new", "\n");
                            obj = new NewJFrame5();
                            obj.jTextArea1.setText(msg);
                            System.out.println(msg);
                            new Thread(new job()).start();
                        } else if (s.equals("checking server")) {
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        class job implements Runnable {

            public void run() {
                try {
                    Thread.sleep(5000);
                    obj.dispose();
                } catch (Exception e) {
                }
            }
        }

        public class NewJFrame5 extends javax.swing.JFrame {

            public NewJFrame5() {
                setUndecorated(true);
                initComponents();
                setAlwaysOnTop(true);
                setLayout(null);
                setVisible(true);
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                setLocation((d.width - 400) / 2, (d.height - 300) / 2);
                setSize(400, 300);
            }
            
            private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTextArea1 = new javax.swing.JTextArea();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                getContentPane().setLayout(null);

                jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); 
                jLabel1.setText("MESSAGE FROM CONTROLLER");
                getContentPane().add(jLabel1);
                jLabel1.setBounds(77, 11, 250, 41);

                jTextArea1.setEditable(false);
                jTextArea1.setColumns(20);
                jTextArea1.setRows(5);
                jScrollPane1.setViewportView(jTextArea1);

                getContentPane().add(jScrollPane1);
                jScrollPane1.setBounds(56, 63, 280, 120);

                pack();
            }

            private javax.swing.JLabel jLabel1;
            private javax.swing.JScrollPane jScrollPane1;
            private javax.swing.JTextArea jTextArea1;

        }
    }

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
                        File f = new File("d:\\screenshots");
                        if (f.exists() == false) {
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
    }

    public static void main(String[] args) {
        Server obj = new Server();
        PhotoServer obj1 = obj.new PhotoServer();
        ServerCode obj2 = obj.new ServerCode();

        try {
            PopupMenu popMenu = new PopupMenu();
            MenuItem item1 = new MenuItem("Exit");
            popMenu.add(item1);
            Image img = ImageIO.read(new File("dot.png"));
            TrayIcon trayIcon = new TrayIcon(img, "Team Viewer", popMenu);
            SystemTray.getSystemTray().add(trayIcon);

            item1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        SystemTray.getSystemTray().remove(trayIcon);
                        System.exit(0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
