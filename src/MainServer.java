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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class MainServer
{

    static boolean flag = true;
    static boolean flag2 = true;
    static boolean flag3 = false;

    //photohandler class is responsible for receiving and sending images
    public class PhotoServer implements Runnable
    {

        ServerSocket sersock;

        PhotoServer()
        {
            Thread t = new Thread(this);
            t.start();
        }

        public void run()
        {
            try
            {
                sersock = new ServerSocket(9700);
                while (flag)
                {
                    Socket sock = sersock.accept();
                    PhotoHandler obj = new PhotoHandler(sock);
                    flag3 = true;
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        class PhotoHandler implements Runnable
        {

            Socket sock1;
            DataOutputStream dos;
            DataInputStream dis;

            PhotoHandler(Socket sock)
            {
                this.sock1 = sock;
                try
                {
                    dos = new DataOutputStream(sock.getOutputStream());
                    dis = new DataInputStream(sock.getInputStream());
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                Thread t = new Thread(this);
                t.start();
            }

            public void run()
            {
                int i = 0;

                try
                {
                    while (flag)
                    {
                        File f = new File("F:\\screenshots");
                        if (f.exists() == false)
                        {
                            f.mkdir();
                        }
                        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                        dos.writeDouble(d.getHeight());
                        dos.writeDouble(d.getWidth());
                        Robot rbt = new Robot();
                        BufferedImage bimg = rbt.createScreenCapture(new Rectangle(d));
                        ImageIO.write(bimg, "jpg", new File("F:\\screenshots\\" + i + ".jpg"));
                        f = new File("F:\\screenshots\\" + i + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        dos.writeLong(f.length());
                        int k = 0;
                        byte b[] = new byte[10000];
                        while (f.length() >= k)
                        {
                            k = fis.read(b, 0, 10000);
                            if (k == -1)
                            {
                                break;
                            }
                            dos.write(b, 0, k);
                        }
                        String s = dis.readLine();
                        System.out.println(s);
                        fis.close();
                        f.delete();
                        i++;
                    }
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        }
    }

    public class ServerCode implements Runnable
    {

        ServerSocket sersock;
        NewJFrame5 obj;

        ServerCode()
        {
            Thread t = new Thread(this);
            t.start();
        }

        public void run()
        {
            try
            {
                sersock = new ServerSocket(9900);
                while (flag)
                {
                    Socket sock = sersock.accept();
                    ClientHandler obj = new ClientHandler(sock);
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        class ClientHandler implements Runnable
        {

            Socket sock1;
            DataOutputStream dos;
            DataInputStream dis;

            ClientHandler(Socket sock)
            {
                this.sock1 = sock;
                try
                {
                    dos = new DataOutputStream(sock.getOutputStream());
                    dis = new DataInputStream(sock.getInputStream());
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                Thread t = new Thread(this);
                t.start();
            }

            public void run()
            {
                try
                {
                    while (true)
                    {

                        if (flag2 == false)
                        {
                            dos.writeBytes("server closed\r\n");
                            flag = false;
                            break;
                        }

                        String s = dis.readLine();
                        System.out.println(s);
                        if (s.equals("mouse pressed"))
                        {
                            int x = dis.readInt();
                            int y = dis.readInt();
                            int Button = dis.readInt();
                            System.out.println("pressed x = " + x + " y = " + y);
                            if (Button == 1)
                            {
                                System.out.println("left click");
                            } else if (Button == 3)
                            {
                                System.out.println("right click");
                            }
                            Robot rbt = new Robot();
                            rbt.mouseMove(x, y);
                            if (Button == 1)
                            {
                                rbt.mousePress(MouseEvent.BUTTON1_MASK);
                            } else if (Button == 2)
                            {
                                rbt.mousePress(MouseEvent.BUTTON2_MASK);
                            } else if (Button == 3)
                            {
                                rbt.mousePress(MouseEvent.BUTTON3_MASK);
                            }
                        } else if (s.equals("mouse released"))
                        {
                            int x = dis.readInt();
                            int y = dis.readInt();
                            int Button = dis.readInt();
                            System.out.println("pressed x = " + x + " y = " + y);
                            if (Button == 1)
                            {
                                System.out.println("left click");
                            } else if (Button == 3)
                            {
                                System.out.println("right click");
                            }
                            Robot rbt = new Robot();
                            rbt.mouseMove(x, y);
                            if (Button == 1)
                            {
                                rbt.mouseRelease(MouseEvent.BUTTON1_MASK);
                            } else if (Button == 2)
                            {
                                rbt.mouseRelease(MouseEvent.BUTTON2_MASK);
                            } else if (Button == 3)
                            {
                                rbt.mouseRelease(MouseEvent.BUTTON3_MASK);
                            }
                        } else if (s.equals("key pressed"))
                        {
                            int keycode = dis.readInt();
                            System.out.println(keycode);
                            Robot rbt = new Robot();
                            rbt.keyPress(keycode);
                        } else if (s.equals("key released"))
                        {
                            int keycode = dis.readInt();
                            System.out.println(keycode);
                            Robot rbt = new Robot();
                            rbt.keyRelease(keycode);
                        } else if (s.equals("shutdown"))
                        {
                            Runtime.getRuntime().exec("shutdown -s -f");
                        } else if (s.equals("restart"))
                        {
                            Runtime.getRuntime().exec("shutdown -r -f");
                        } else if (s.equals("sending message"))
                        {
                            String msg = dis.readLine();
                            msg = msg.replace(":new", "\r");
                            msg = msg.replace("_new", "\n");
                            obj = new NewJFrame5();
                            obj.jTextArea1.setText(msg);
                            System.out.println(msg);
                            new Thread(new job()).start();
                        } else if (s.equals("get drives"))
                        {
                            File[] drives = File.listRoots();
//                            if (drives != null && drives.length > 0)

                            for (int i = 0; i < drives.length; i++)
                            {
                                System.out.println(drives[i]);
                            }

                            dos.writeBytes("get drive response\r\n");
                            dos.writeInt(drives.length);
                            for (int i = 0; i < drives.length; i++)
                            {
                                dos.writeBytes(drives[i] + "\r\n");
                            }
                        } else if (s.equals("get files"))
                        {
                            String path = dis.readLine();
                            File files[] = new File(path).listFiles();
                            File fp = new File(path);
                            ArrayList<FileShow> al = new ArrayList<>();
                            for (int i = 0; i < files.length; i++)
                            {
                                al.add(new FileShow(files[i].getName(), files[i].getParent(), files[i].length(), files[i].isFile() ? "file" : "folder"));
                            }
                            dos.writeBytes("get files response\r\n");
                            dos.writeInt(al.size());
                            dos.writeBytes(fp.getParent() + "\r\n");

                            for (int i = 0; i < al.size(); i++)
                            {
                                dos.writeBytes(al.get(i).filename + "\r\n");
                                dos.writeBytes(al.get(i).parent + "\r\n");
                                dos.writeLong(al.get(i).size);
                                dos.writeBytes(al.get(i).type + "\r\n");
                            }
                        } else if (s.equals("download file"))
                        {
                            String path = dis.readLine();
                            File f = new File(path);
                            FileInputStream fis = new FileInputStream(f);
                            String fname = f.getName();
                            long size = f.length();
                            dos.writeBytes("download\r\n");
                            dos.writeBytes(fname + "\r\n");
                            dos.writeLong(size);
                            byte b[] = new byte[1000000];
                            long count = 0;
                            while (true)
                            {
                                int r = fis.read(b, 0, 1000000);
                                count = count + r;
                                dos.write(b, 0, r);
                                if (size == count)
                                {
                                    break;
                                }
                            }
                            System.out.println(dis.readLine());

                        } else if (s.equals("checking server"))
                        {
                            break;
                        }
                    }
//                dos.writeBytes("Hello Client\r\n");
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }

        class job implements Runnable
        {

            public void run()
            {
                try
                {
                    Thread.sleep(5000);
                    obj.dispose();
                } catch (Exception e)
                {
                }
            }
        }

        public class NewJFrame5 extends javax.swing.JFrame
        {

            public NewJFrame5()
            {
                setUndecorated(true);
                initComponents();
                setAlwaysOnTop(true);
                setLayout(null);
                setVisible(true);
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                setLocation((d.width - 400) / 2, (d.height - 300) / 2);
                setSize(400, 300);
            }

            private void initComponents()
            {

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

    public static class FileShow
    {

        String filename;
        String parent;
        long size;
        String type;

        public FileShow(String filename, String parent, long size, String type)
        {
            this.filename = filename;
            this.parent = parent;
            this.size = size;
            this.type = type;
        }

    }

    public static void main(String[] args)
    {
        MainServer obj = new MainServer();
        PhotoServer obj1 = obj.new PhotoServer();
        ServerCode obj2 = obj.new ServerCode();

        try
        {
//            System.out.println("This is a demo for system tray icon");
            PopupMenu popMenu = new PopupMenu();
            MenuItem item1 = new MenuItem("Start Server");
            MenuItem item2 = new MenuItem("Stop Server");
//            item1.setEnabled(false);
            popMenu.add(item1);
            popMenu.add(item2);
            Image img = ImageIO.read(new File("dot.png"));
            TrayIcon trayIcon = new TrayIcon(img, "Team Viewer", popMenu);
            SystemTray.getSystemTray().add(trayIcon);

            item1.setEnabled(false);
            item2.setEnabled(true);

            item1.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        flag = true;
                        flag2 = true;
                        flag3 = false;
                        PhotoServer obj4 = obj.new PhotoServer();
                        ServerCode obj5 = obj.new ServerCode();
                        item1.setEnabled(false);
                        item2.setEnabled(true);
                    } catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });
            item2.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        String s = JOptionPane.showInputDialog("ENTER PASSWORD");
                        if (s.equals("12345"))
                        {
                            flag2 = false;
                            while (true)
                            {
                                if (flag == false || flag3 == false)
                                {
                                    obj2.sersock.close();
                                    obj1.sersock.close();
                                    item2.setEnabled(false);
                                    item1.setEnabled(true);
                                    break;
                                }
                            }
                        } else
                        {
                            JOptionPane.showMessageDialog(null, "ENTER CORRECT PASSWORD");
                        }
                    } catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}