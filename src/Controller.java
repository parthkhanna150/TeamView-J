
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class Controller extends javax.swing.JFrame
{
    //these variables are declared globally so that we can use their refernce 
    //any where in the program 

    ArrayList<String> al = new ArrayList<>();
    //we add the ipaddress in the array list so that we can use them in table
    //displayed in the controller frame which has the only one column and multiple rows
    //accrodingly the no of ips in the array list
    MyTableModel tm;
    NewJFrame4 obj1;
    CommandClient obj;

    public Controller()
    {
//        int i=0;
        setLayout(null);
        setVisible(true);
        initComponents();

        //meomoeies to the component is to be given after the init components
        setSize(500, 500);
        tm = new MyTableModel();
        tb1.setModel(tm);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        bt2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt1.setText("DETECT HELPER SYSTEM");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(tb1);

        bt2.setText("GET SCREEN");
        bt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(81, 81, 81)
                .addComponent(bt2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    class MyTableModel extends AbstractTableModel
    {

        //overriding the AbstarctTableModel here 
        public String getColumnName(int col)
        {
            String name = "IP ADDRESS";
            return name;
        }

        //specifies the row count ie the no of rows that we want in the table
        //here in this case the no of the rows is infinite as we add the array
        //list elements in the table which can vary acccordingly
        public int getRowCount()
        {
            return al.size();
        }

        //as in this case there is only one coloumn required we have passed the 
        //string value to the column count which is one in this case
        public int getColumnCount()
        {
            return 1;
        }

        //here we use object type as parameter to fill the elements in the table
        public Object getValueAt(int row, int col)
        {
            return al.get(row);
        }
    }
    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed

        //on the click of the button we give add the ip available in the network 
        //a string value is passed and it ia varied accordingly
        String s = "192.168.43.";
        for (int i = 0; i <= 255; i++)
        {
            Client obj = new Client(s + i);
            bt1.setEnabled(false);
            //we have disabled the button on one click of the button
        }
    }//GEN-LAST:event_bt1ActionPerformed

    private void bt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt2ActionPerformed
        if (tb1.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "Select an IP ADDRESS first");
        } else
        {
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
            obj1.jScrollPane3.setBounds(10, 150, d.width - 50, d.height - 200);

//            al.get(tb1.getSelectedRow());
        }
    }//GEN-LAST:event_bt2ActionPerformed
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JButton bt2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb1;
    // End of variables declaration//GEN-END:variables
    public class PhotoClient implements Runnable
    {

        String ipaddress;

        PhotoClient(String ip)
        {
            ipaddress = ip;
            Thread t = new Thread(this);
            t.start();
        }

        public void run()
        {
            try
            {
                Socket sock = new Socket(ipaddress, 9700);
                DataInputStream dis = new DataInputStream(sock.getInputStream());
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                int i = 0;
                while (true)
                {
                    FileOutputStream fos = new FileOutputStream("e:\\Screenshots\\" + i + ".jpg");
                    double height = dis.readDouble();
                    double width = dis.readDouble();
                    System.out.println("Height of Screen" + height);
                    System.out.println("Width of Screen" + width);
                    long length = dis.readLong();
                    int l;
                    int count = 0;
                    byte b[] = new byte[10000];
                    while (true)
                    {
                        l = dis.read(b, 0, 10000);
                        count = count + l;
                        fos.write(b, 0, l);
                        if (count == length)
                        {
                            break;
                        }
                    }
                    dos.writeBytes("Received\r\n");
                    fos.close();
                    obj1.jLabel2.setPreferredSize(new Dimension((int) width, (int) height));
                    obj1.jLabel2.setIcon(new ImageIcon("e:\\Screenshots\\" + i + ".jpg"));
                    File f = new File("e:\\Screenshots\\" + i + ".jpg");
                    f.delete();
                    i++;
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public class Client implements Runnable
    {

        String ipaddress;

        Client(String ip)
        {
            ipaddress = ip;
            Thread t = new Thread(this);
            t.start();
        }

        public void run()
        {
            try
            {
                Socket sock = new Socket();
                sock.connect(new InetSocketAddress(ipaddress, 9900), 5000);
                DataInputStream dis = new DataInputStream(sock.getInputStream());
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                dos.writeBytes("checking server\r\n");
                al.add(ipaddress);
                tm.fireTableDataChanged();

            } catch (Exception ex)
            {
                System.out.println("not connected to " + ipaddress);
//                ex.printStackTrace();
            }

        }
    }

    public class CommandClient implements Runnable
    {

        String ipaddress;
        DataOutputStream dos;
        DataInputStream dis;

        //command client is written in order to take the control of the server computer
        //photo client and the command client class is made separate so that the two
        //tasks dont get mixed with each other an drun separately
        CommandClient(String ip)
        {
            ipaddress = ip;
            try
            {
                Socket sock = new Socket(ipaddress, 9900);
                dos = new DataOutputStream(sock.getOutputStream());
                dis = new DataInputStream(sock.getInputStream());
                Thread t = new Thread(this);
                t.start();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void run()
        {
            try
            {
                while (true)
                {
                    //place while true always in try catch so that when exception 
                    //comes its not printed again and again
                    //dis.readLine();
                    String s = dis.readLine();
                    System.out.println(s);
                    if (s.equalsIgnoreCase("server closed"))
                    {
                        JOptionPane.showMessageDialog(null, "Server switched off");
                        obj1.dispose();
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    public class NewJFrame4 extends javax.swing.JFrame implements MouseListener,
            KeyListener, ActionListener
    {

        JButton bt1, bt2, bt3;

        public NewJFrame4()
        {
            initComponents();
            setVisible(true);
            setSize(1366, 768);
            bt1 = new JButton("SHUTDOWN");
            bt2 = new JButton("RESTART");
            bt3 = new JButton("SEND MESSAGE");
            bt1.setBounds(20, 20, 160, 60);
            bt2.setBounds(200, 20, 160, 60);
            bt3.setBounds(380, 20, 160, 60);
            add(bt1);
            add(bt2);
            add(bt3);
            bt1.addActionListener(this);
            bt2.addActionListener(this);
            bt3.addActionListener(this);

            //we have added a mouse listener event to the button click
            jLabel2.addMouseListener(this);

            this.addKeyListener(this);
            this.requestFocus();
        }

        private void initComponents()
        {

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
        public void mouseClicked(MouseEvent e)
        {
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            e.getX();
            e.getY();
            try
            {
                obj.dos.writeBytes("mouse pressed\r\n");
                obj.dos.writeInt(e.getX());
                obj.dos.writeInt(e.getY());
                obj.dos.writeInt(e.getButton());
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
            System.out.println("X cordinates =" + e.getX());
            System.out.println("Y cordinates =" + e.getY());
        }

        public void mouseReleased(MouseEvent e)
        {
            e.getX();
            e.getY();
            try
            {
                obj.dos.writeBytes("mouse released\r\n");
                obj.dos.writeInt(e.getX());
                obj.dos.writeInt(e.getY());
                obj.dos.writeInt(e.getButton());
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
            System.out.println("X1 cordinates =" + e.getX());
            System.out.println("Y1 cordinates =" + e.getY());
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
        }

        public void mouseExited(MouseEvent e)
        {
        }

        //implementing key listener to take the keyboard control of server
        //we have added the key listener on this frame so that we can alter the 
        //screen of the server computer using ht keyboard
        @Override
        public void keyTyped(KeyEvent e)
        {
        }

        @Override
        public void keyPressed(KeyEvent e)
        {

            try
            {
                obj.dos.writeBytes("key pressed\r\n");
                obj.dos.writeInt(e.getKeyCode());
            } catch (IOException ex)
            {
            }

        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            try
            {
                obj.dos.writeBytes("key released\r\n");
                obj.dos.writeInt(e.getKeyCode());
            } catch (IOException ex)
            {
            }

            System.out.println(e.getKeyCode());

        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == bt1)
            {
                int ans = JOptionPane.showConfirmDialog(this, "Are You Sure"
                        + " you want to Shutdown the Computer",
                        "SHUTDOWN FRAME", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION)
                {
                    System.out.println("yes");
                    try
                    {
                        obj.dos.writeBytes("shutdown\r\n");
                    } catch (Exception ew)
                    {
                        ew.printStackTrace();
                    }
                } else
                {
                    System.out.println("no");
                }
            } else if (e.getSource() == bt2)
            {
                int ans = JOptionPane.showConfirmDialog(this, "Are You Sure "
                        + "you want to Restart the Computer",
                        " RESTARTFRAME", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION)
                {
                    System.out.println("yes");
                    try
                    {
                        obj.dos.writeBytes("restart\r\n");
                    } catch (Exception ew)
                    {
                        ew.printStackTrace();
                    }
                } else
                {
                    System.out.println("no");
                }
            } else if (e.getSource() == bt3)
            {
                NewJFrame5 obj = new NewJFrame5();
            }
            this.requestFocus();
            //this shifts the focus back on the frame after the buuton click 
        }
    }

    public class NewJFrame5 extends javax.swing.JFrame
    {

        public NewJFrame5()
        {
            initComponents();
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
            jButton1.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    jButton1ActionPerformed(evt);
                }
            });
            getContentPane().add(jButton1);
            jButton1.setBounds(145, 201, 87, 38);

            pack();
        }

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
        {
            if (jTextArea1.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "TYPE A MESSAGE FIRST");
            } else
            {
                String s = jTextArea1.getText();
                s = s.replace("\r", ":new");
                s = s.replace("\n", "_new");
                try
                {
                    obj.dos.writeBytes("sending message\r\n");
                    obj.dos.writeBytes(s + "\r\n");
                } catch (Exception e)
                {
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
}
