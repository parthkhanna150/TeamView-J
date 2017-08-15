
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class NewJFrame extends javax.swing.JFrame {
    //these variables are declared globally so that we can use their refernce 
    //any where in the program 

    ArrayList<String> al = new ArrayList<>();
    //we add the ipaddress in the array list so that we can use them in table 
    //displayed in the controller frame which has the only one column and multiple rows
    //accrodingly the no of ips in the array list
    MyTableModel tm;
    NewJFrame4 obj1;
    CommandClient obj;
    NewJFrame6 obj2;

    public NewJFrame() {
//        int i=0;
        setLayout(null);
        initComponents();

        //meomoeies to the component is to be given after the init components
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((d.width - 445) / 2, (d.height - 320) / 2);
        setSize(445, 320);
        setResizable(false);
        tm = new MyTableModel();
        tb1.setModel(tm);
        setVisible(true);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        bt2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        bt1.setText("DETECT HELPER SYSTEM");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });
        getContentPane().add(bt1);
        bt1.setBounds(29, 22, 180, 36);

        tb1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "IP ADDRESSES"
            }
        ));
        tb1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tb1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jScrollPane1.setViewportView(tb1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(29, 96, 380, 170);

        bt2.setText("GET SCREEN");
        bt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt2ActionPerformed(evt);
            }
        });
        getContentPane().add(bt2);
        bt2.setBounds(225, 22, 180, 36);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Free-Screensavers-Nature-Wallpapers-for-Desktop.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 440, 290);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    class MyTableModel extends AbstractTableModel {

        //overriding the AbstarctTableModel here 
        public String getColumnName(int col) {
            String name = "IP ADDRESS";
            return name;
        }

        //specifies the row count ie the no of rows that we want in the table
        //here in this case the no of the rows is infinite as we add the array
        //list elements in the table which can vary acccordingly
        public int getRowCount() {
            return al.size();
        }

        //as in this case there is only one coloumn required we have passed the 
        //string value to the column count which is one in this case
        public int getColumnCount() {
            return 1;
        }

        //here we use object type as parameter to fill the elements in the table
        public Object getValueAt(int row, int col) {
            return al.get(row);
        }
    }
    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed

        try {
            //on the click of the button we give add the ip available in the network
            //a string value is passed and it ia varied accordingly
//        String s = JOptionPane.showInputDialog(rootPane, "Enter the IP range");
//        String s= "192.168.16.";
//        for (int i = 0; i <= 255; i++) {
//            Client obj = new Client(s + i);
//            bt1.setEnabled(false);
            String ip = JOptionPane.showInputDialog("Enter server ip address");

            
            
            if (ip == null || ip.equals("") || ip.indexOf(".") == -1) {

            } else {
                ip = ip.substring(0, ip.lastIndexOf(".")+1);
                String s = ip;
                System.out.println(s);
                for (int i = 0; i <= 255; i++) {
                    Client obj = new Client(s + i);
                    bt1.setEnabled(false);
                    //we have disabled the button on one click of the button  
                }
            }

//       
        } catch (Exception ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt1ActionPerformed

    private void bt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt2ActionPerformed
        if (tb1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Select an IP ADDRESS first");
        } else {
            //we want to open a new frame on the button click so we given memory
            //to new frame on the button click 
            obj1 = new NewJFrame4();
            obj1.setVisible(true);
            //photoclient works as to straem the scrrenshots of the server computer
            //live streaming will be done due this class
            PhotoClient pc = new PhotoClient(al.get(tb1.getSelectedRow()));
            //command client functions so as to serve the function of taking the 
            //control of the connected serever computer
            obj = new CommandClient(al.get(tb1.getSelectedRow()));
            //dimension class fuctions to get the dimensions of the serever computer
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            obj1.setSize(d.width, d.height);
            obj1.jScrollPane3.setBounds(5, 100, d.width - 25, d.height - 200);

//            al.get(tb1.getSelectedRow());
        }
    }//GEN-LAST:event_bt2ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JButton bt2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb1;
    // End of variables declaration//GEN-END:variables
    public class PhotoClient implements Runnable {

        String ipaddress;

        PhotoClient(String ip) {
            ipaddress = ip;
            Thread t = new Thread(this);
            t.start();
        }

        public void run() {
            try {
                //socket is available for the further connections 
                //photo server sends the ocntinous screenshots of server computer 
                //to the client and streams the contionus work done by server
                Socket sock = new Socket(ipaddress, 9700);
                DataInputStream dis = new DataInputStream(sock.getInputStream());
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                int i = 0;
                while (true) {

                    FileOutputStream fos = new FileOutputStream("Downloads/screenshots" + i + ".jpg");
                    //before sending the photos the server sends the size of the image 
                    //which is equal to the size of the screen 
                    double height = dis.readDouble();
                    double width = dis.readDouble();
//
//                    System.out.println("Height of Screen" + height);
//                    System.out.println("Width of Screen" + width);

                    //here lenght is size of the image sent by the server
                    long length = dis.readLong();
                    int l;
                    int count = 0;
                    byte b[] = new byte[10000];
                    while (true) {
                        //l is the bytes read by the client in one time
                        l = dis.read(b, 0, 10000);
                        //count is intialized to zero
                        //count is continously incremented according to size of file 
                        //recieved in one time and is incremented again and again
                        count = count + l;
                        //file(photo) sent by server is written on client1 computer 
                        fos.write(b, 0, l);
                        if (count == length) {
                            break;
                            //when the count is equal to the size of the photo recieved 
                            //then we break the loop for further recival of photos
                        }
                    }

                    dos.writeBytes("Received\r\n");
                    fos.close();
                    obj1.jLabel2.setPreferredSize(new Dimension((int) width, (int) height));
                    obj1.jLabel2.setIcon(new ImageIcon("Downloads/screenshots" + i + ".jpg"));

                    File f = new File("Downloads/screenshots" + i + ".jpg");
                    f.delete();
                    i++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public class Client implements Runnable {

        String ipaddress;

        Client(String ip) {
            ipaddress = ip;
            Thread t = new Thread(this);
            t.start();
        }

        public void run() {
            try {
                Socket sock = new Socket();
                //we have limited the time for which an sockeet accepts an connection
                //to 5 seconds (5000 ms)
                sock.connect(new InetSocketAddress(ipaddress, 9900), 2000);
                DataInputStream dis = new DataInputStream(sock.getInputStream());
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                dos.writeBytes("checking server\r\n");
                al.add(ipaddress);
                tm.fireTableDataChanged();

            } catch (Exception ex) {
//                ex.printStackTrace();
//                 System.out.println("not connected to " + ipaddress);
//                ex.printStackTrace();
            }

        }
    }

    public class CommandClient implements Runnable {

        String ipaddress;
        DataOutputStream dos;
        DataInputStream dis;

        //command client is written in order to take the control of the server computer  
        //photo client and the command client class is made separate so that the two
        //tasks dont get mixed with each other an drun separately
        CommandClient(String ip) {
            ipaddress = ip;
            try {
                Socket sock = new Socket(ipaddress, 9900);
                dos = new DataOutputStream(sock.getOutputStream());
                dis = new DataInputStream(sock.getInputStream());
                Thread t = new Thread(this);
                t.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                while (true) {
                    //place while true always in try catch so that when exception 
                    //comes its not printed again and again
                    //dis.readLine();
                    String s = dis.readLine();
                    System.out.println(s);
                    if (s.equals("get drive response")) {
                        int size = dis.readInt();
                        obj2.al.clear();
                        for (int i = 0; i < size; i++) {
                            String dd = dis.readLine();
                            obj2.al.add(obj2.new FileShow(dd, "", 0, "drive"));
                        }
                        obj2.GetFileDetails();
                    } else if (s.equals("get files response")) {
                        int size = dis.readInt();
                        obj2.parent = dis.readLine();
                        obj2.al.clear();
                        for (int i = 0; i < size; i++) {
                            String filename = dis.readLine();
                            String parent = dis.readLine();
                            long length = dis.readLong();
                            String type = dis.readLine();
                            obj2.al.add(obj2.new FileShow(filename, parent, length, type));
                        }
                        obj2.GetFileDetails();

                    } else if (s.equals("download")) {
                        String fn = dis.readLine();
                        long size = dis.readLong();

                        File f = new File("Desktop/downloaded_files");
                        if (!f.exists()) {
                            f.mkdir();
                        }
                        FileOutputStream fos = new FileOutputStream("Desktop/downloaded_files" + fn);
                        int l;
                        int count = 0;
                        byte b[] = new byte[1000000];
                        while (true) {
                            l = dis.read(b, 0, 1000000);
                            count = count + l;
                            fos.write(b, 0, l);
                            if (count == size) {
                                break;
                            }
                        }
                        fos.close();
                        dos.writeBytes("file recieved\r\n");
                        int n = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO PLAY IT");
//                        JOptionPane.showMessageDialog(null, "FILE DOWNLOADED SUCCEFULLY");
                        if (n == JOptionPane.YES_OPTION) {
                            Desktop.getDesktop().open(new File("Desktop/downloaded_files" + fn));
                        }
                    } else if (s.equalsIgnoreCase("server closed")) {
                        JOptionPane.showMessageDialog(null, "SERVER SWITCHED OFF");
                        obj1.dispose();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public class NewJFrame4 extends javax.swing.JFrame implements MouseListener,
            KeyListener, ActionListener {

        JButton bt1, bt2, bt3, bt4;

        public NewJFrame4() {
            initComponents();
            setVisible(true);
            setSize(1366, 768);
            bt1 = new JButton("SHUTDOWN");
            bt2 = new JButton("RESTART");
            bt3 = new JButton("SEND MESSAGE");
            bt4 = new JButton("OPEN EXPLORER");
//            bt1.setBounds(20, 20, 180, 60);
//            bt2.setBounds(220, 20, 180, 60);
//            bt3.setBounds(420, 20, 180, 60);
//            bt4.setBounds(620, 20, 180, 60);
            bt1.setBounds(20, 20, 180, 60);
            bt2.setBounds(220, 20, 180, 60);
            bt3.setBounds(420, 20, 180, 60);
            bt4.setBounds(620, 20, 180, 60);
            add(bt1);
            add(bt2);
            add(bt3);
            add(bt4);
            bt1.addActionListener(this);
            bt2.addActionListener(this);
            bt3.addActionListener(this);
            bt4.addActionListener(this);

            //we have added a mouse listener event to the button click
            jLabel2.addMouseListener(this);

            this.addKeyListener(this);
            this.requestFocus();
        }

        private void initComponents() {

            //here we have used the jscrollpane and jlabel components of the new 
            //frame that we want to open on the button click
            jScrollPane3 = new javax.swing.JScrollPane();
            jLabel2 = new javax.swing.JLabel();
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            getContentPane().setLayout(null);
            jLabel2.setText("LIVE STREAMING OF THE SERVER COMPUTER WILL BE SHOWN HERE");

            //we have placed the label on a scroll pane so that if the size of the image 
            //recieved is large its gets adjusted accordingly
            jScrollPane3.setViewportView(jLabel2);
            getContentPane().add(jScrollPane3);
            jScrollPane3.setBounds(0, 0, 408, 300);
            pack();//this symobolises the code is end at this place
        }
        //frame componets of the new frame that is to be opened on the button 
        //click are listed here

        public javax.swing.JLabel jLabel2;
        public javax.swing.JScrollPane jScrollPane3;

        //here we override the mouse listener event that we have used in this frame 
        //we write the code on the mouse pressed and mouse released events
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            e.getX();
            e.getY();
            try {
                obj.dos.writeBytes("mouse pressed\r\n");
                obj.dos.writeInt(e.getX());
                obj.dos.writeInt(e.getY());
                obj.dos.writeInt(e.getButton());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("X cordinates =" + e.getX());
            System.out.println("Y cordinates =" + e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            e.getX();
            e.getY();
            try {
                obj.dos.writeBytes("mouse released\r\n");
                obj.dos.writeInt(e.getX());
                obj.dos.writeInt(e.getY());
                obj.dos.writeInt(e.getButton());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("X1 cordinates =" + e.getX());
            System.out.println("Y1 cordinates =" + e.getY());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        //implementing key listener to take the keyboard control of server
        //we have added the key listener on this frame so that we can alter the 
        //screen of the server computer using ht keyboard
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            try {
                obj.dos.writeBytes("key pressed\r\n");
                obj.dos.writeInt(e.getKeyCode());
            } catch (IOException ex) {
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            try {
                obj.dos.writeBytes("key released\r\n");
                obj.dos.writeInt(e.getKeyCode());
            } catch (IOException ex) {
            }

            System.out.println(e.getKeyCode());

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == bt1) {
                int ans = JOptionPane.showConfirmDialog(this, "Are You Sure"
                        + " you want to Shutdown the Computer",
                        "SHUTDOWN FRAME", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    System.out.println("yes");
                    try {
                        obj.dos.writeBytes("shutdown\r\n");
                    } catch (Exception ew) {
                        ew.printStackTrace();
                    }
                } else {
                    System.out.println("no");
                }
            } else if (e.getSource() == bt2) {
                int ans = JOptionPane.showConfirmDialog(this, "Are You Sure "
                        + "you want to Restart the Computer",
                        " RESTARTFRAME", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    System.out.println("yes");
                    try {
                        obj.dos.writeBytes("restart\r\n");
                    } catch (Exception ew) {
                        ew.printStackTrace();
                    }
                } else {
                    System.out.println("no");
                }
            } else if (e.getSource() == bt3) {
                NewJFrame5 obj = new NewJFrame5();
            } else if (e.getSource() == bt4) {
                try {
                    obj2 = new NewJFrame6();
                    obj2.setSize(600, 500);
                    obj2.setVisible(true);
                    obj.dos.writeBytes("get drives\r\n");

                } catch (Exception g) {
                    g.printStackTrace();
                }
            }
            this.requestFocus();
            //this shifts the focus back on the frame after the buuton click 
        }
    }

    public class NewJFrame5 extends javax.swing.JFrame {

        public NewJFrame5() {
            initComponents();
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
            jButton1 = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);

            jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
            jLabel1.setText("SEND MESSAGE");
            getContentPane().add(jLabel1);
            jLabel1.setBounds(128, 11, 139, 41);

            jTextArea1.setColumns(20);
            jTextArea1.setRows(5);
            jScrollPane1.setViewportView(jTextArea1);

            getContentPane().add(jScrollPane1);
            jScrollPane1.setBounds(56, 63, 280, 120);

            jButton1.setText("SEND");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            getContentPane().add(jButton1);
            jButton1.setBounds(145, 201, 87, 38);

            pack();
        }

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            if (jTextArea1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "TYPE A MESSAGE FIRST");
            } else {
                String s = jTextArea1.getText();
                s = s.replace("\r", ":new");
                s = s.replace("\n", "_new");
                try {
                    obj.dos.writeBytes("sending message\r\n");
                    obj.dos.writeBytes(s + "\r\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.dispose();
            }
        }

        private javax.swing.JButton jButton1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextArea jTextArea1;

    }

    public class NewJFrame6 extends javax.swing.JFrame {

        ArrayList<FileShow> al = new ArrayList<>();
        String parent;
        String forward;
        String in;
        JLabel jLabel1;

        public NewJFrame6() {
            initComponents();
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation((d.width - 595) / 2, (d.height - 388) / 2);
            setLayout(null);
            setTitle("FILE EXPLORER");
            setSize(595, 388);
            setResizable(false);

        }

        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            jScrollPane1 = new javax.swing.JScrollPane();
            jPanel1 = new javax.swing.JPanel();
//            jButton4 = new javax.swing.JButton();
            jButton3 = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);

            jPanel1.setBackground(new java.awt.Color(255, 255, 255));
            jPanel1.setLayout(null);
            jScrollPane1.setViewportView(jPanel1);

            getContentPane().add(jScrollPane1);
            jScrollPane1.setBounds(0, 50, 590, 310);

//            jButton4.setText("FORWARD");
//            jButton4.addActionListener(new java.awt.event.ActionListener() {
//                public void actionPerformed(java.awt.event.ActionEvent evt) {
//                    jButton4ActionPerformed(evt);
//                }
//            });
//            getContentPane().add(jButton4);
//            jButton4.setBounds(140, 20, 100, 23);
            jButton3.setText("BACK");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });
            jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jButton3KeyTyped(evt);
                }
            });
            getContentPane().add(jButton3);
            jButton3.setBounds(10, 20, 110, 23);

            pack();
        }// </editor-fold>                        

        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
            if (obj2.parent.equals("null")) {
                try {
                    obj.dos.writeBytes("get drives\r\n");
                } catch (Exception i) {
                    i.printStackTrace();
                }

            } else {
                try {
                    obj.dos.writeBytes("get files\r\n");
                    obj.dos.writeBytes(obj2.parent + "\r\n");
                } catch (Exception j) {
                    j.printStackTrace();
                }

            }
        }

        private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {

        }

        private void jButton3KeyTyped(java.awt.event.KeyEvent evt) {

        }

        // Variables declaration - do not modify                     
        private javax.swing.JButton jButton3;
        private javax.swing.JButton jButton4;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        // End of variables declaration                   

        private class FileShow {

            String filename;
            String parent;
            long size;
            String type;

            public FileShow(String filename, String parent, long size, String type) {
                this.filename = filename;
                this.parent = parent;
                this.size = size;
                this.type = type;
            }

        }

        public void GetFileDetails() {
            jPanel1.removeAll();
            jPanel1.repaint();
            int x = 10, y = 10;
            int count = 1;
            System.out.println("alsize :: " + al.size());
            for (int i = 0; i < al.size(); i++) {
                jLabel1 = new JLabel();
                jLabel1.setOpaque(true);
                jLabel1.setBackground(Color.white);
                jPanel1.add(jLabel1);
                jLabel1.setToolTipText(al.get(i).filename);
                jLabel1.setBounds(x, y, 50, 70);
                x = x + 115;
                if (count % 5 == 0 && i != 0) {
                    y = y + 120;
                    x = 10;
                }
                System.out.println("Filename :: " + al.get(i).filename);
                count++;
                if (al.get(i).filename.endsWith(".pdf")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/pdf.png"));
                } else if (al.get(i).filename.endsWith(".doc")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/doc.png"));
                } else if (al.get(i).filename.endsWith(".docx")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/doc.png"));
                } else if (al.get(i).filename.endsWith(".ppt")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/doc.png"));
                } else if (al.get(i).filename.endsWith(".xlsx")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/excel.png"));
                } else if (al.get(i).filename.endsWith(".txt")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/txt.png"));
                } else if (al.get(i).filename.endsWith(".java")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/java.png"));
                } else if (al.get(i).filename.endsWith(".html")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/doc.png"));
                } else if (al.get(i).type.equals("folder")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/Untitled.png"));
                } else if (al.get(i).type.equals("drive")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/drive.png"));
                } else if (al.get(i).type.equals("")) {
                    jLabel1.setIcon(new javax.swing.ImageIcon("Desktop/TeamView-J/drive.png"));
                }
                String s = al.get(i).filename;

                if (s.length() <= 10) {
                    jLabel1.setText(s);
                } else {
                    jLabel1.setText(s.substring(0, 10));
                }

                jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

                jPanel1.setPreferredSize(new Dimension(560, y + 150));
                jScrollPane1.setPreferredSize(null);
                jLabel1.addMouseListener(new MyMouseListener());
            }
        }

        class MyMouseListener implements MouseListener {

            int count = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                jLabel1 = (JLabel) e.getSource();
                Component cc[] = jPanel1.getComponents();
                for (int i = 0; i < cc.length; i++) {
                    cc[i].setBackground(Color.white);
                }
                if (e.getClickCount() == 1) {

                    for (int i = 0; i < cc.length; i++) {
                        if (cc[i].equals(e.getSource())) {
                            jLabel1.setBackground(Color.GREEN);
                        }
                    }
                } else if (e.getClickCount() == 2) {
                    for (int i = 0; i < al.size(); i++) {

                        if (jLabel1.getToolTipText().equals(al.get(i).filename)) {
                            String type = al.get(i).type;

                            if (type.equals("file")) {
                                int n = JOptionPane.showConfirmDialog(null, "Do you want to Download this file?");
                                if (n == JOptionPane.YES_OPTION) {
                                    try {
                                        obj.dos.writeBytes("download file\r\n");
                                        obj.dos.writeBytes(al.get(i).parent + "/" + al.get(i).filename + "\r\n");
                                    } catch (Exception y) {
                                        y.printStackTrace();
                                    }
                                }
                            } else if (type.equals("drive")) {
                                try {

                                    obj.dos.writeBytes("get files\r\n");
                                    String path = al.get(i).filename;
                                    obj.dos.writeBytes(path + "\r\n");
                                    obj.dos.writeBytes(jLabel1.getToolTipText() + "\r\n");
                                } catch (Exception p) {
                                    p.printStackTrace();
                                }
                            } else if (type.equals("folder")) {
                                try {

                                    obj.dos.writeBytes("get files\r\n");
//                                    String path = al.get(i).filename;
                                    obj.dos.writeBytes(al.get(i).parent + "/" + al.get(i).filename + "\r\n");

                                } catch (Exception r) {
                                    r.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        }

    }
}
