
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class NewJFrame111 extends javax.swing.JFrame {

    ArrayList<String> al = new ArrayList<>();
    MyTableModel tm;
    NewJFrame4 obj1;

    public NewJFrame111() {
//        int i=0;
        setLayout(null);
        setVisible(true);
        initComponents();
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

    class MyTableModel extends AbstractTableModel {

        public String getColumnName(int col) {
            String name = "IP ADDRESS";
            return name;
        }

        public int getRowCount() {
            return al.size();
        }

        public int getColumnCount() {
            return 1;
        }

        public Object getValueAt(int row, int col) {
            return al.get(row);
        }
    }
    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed
        String s = "192.168.43.";
        for (int i = 0; i <= 255; i++) {
            Client obj = new Client(s + i);
            bt1.setEnabled(false);
        }
    }//GEN-LAST:event_bt1ActionPerformed

    private void bt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt2ActionPerformed
        if (tb1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Select an IP ADDRESS first");
        } else {
            obj1 = new NewJFrame4();
            obj1.setVisible(true);
            PhotoClient pc = new PhotoClient(al.get(tb1.getSelectedRow()));
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            obj1.setSize(d.width, d.height);
            obj1.jScrollPane3.setBounds(10, 10, d.width - 100, d.height - 100);

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
                Socket sock = new Socket(ipaddress, 9700);
                DataInputStream dis = new DataInputStream(sock.getInputStream());
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                int i = 0;
                 File f = new File("e:\\Screenshots\\" + i + ".jpg");
                while (true) {
                    FileOutputStream fos = new FileOutputStream(f);
                    double height = dis.readDouble();
                    double width = dis.readDouble();
                    System.out.println("Height of Screen" + height);
                    System.out.println("Width of Screen" + width);
                    long length = dis.readLong();
                    int l;
                    int count = 0;
                    byte b[] = new byte[10000];
                    while (true) {
                        l = dis.read(b, 0, 10000);
                        count = count + l;
                        fos.write(b, 0, l);
                        if (count == length) {
                            break;
                        }
                    }
                    dos.writeBytes("Received\r\n");
                    fos.close();
                    obj1.jLabel2.setPreferredSize(new Dimension((int) width, (int) height));
                    obj1.jLabel2.setIcon(new ImageIcon("f"));
                   
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
                sock.connect(new InetSocketAddress(ipaddress, 9900), 5000);
                DataInputStream dis = new DataInputStream(sock.getInputStream());
                String s = dis.readLine();
                al.add(ipaddress);
                tm.fireTableDataChanged();
                System.out.println(ipaddress + ":" + s);

            } catch (Exception ex) {
//                 ex.printStackTrace();
            }

        }
    }

    public class NewJFrame4 extends javax.swing.JFrame implements MouseListener {

        public NewJFrame4() {
            initComponents();
            setVisible(true);
            setSize(1366, 768);
            jLabel2.addMouseListener(this);
        }

        private void initComponents() {
            jScrollPane3 = new javax.swing.JScrollPane();
            jLabel2 = new javax.swing.JLabel();
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            getContentPane().setLayout(null);
            jLabel2.setText("jLabel2");
            jScrollPane3.setViewportView(jLabel2);
            getContentPane().add(jScrollPane3);
            jScrollPane3.setBounds(0, 0, 408, 300);
            pack();
        }

        public javax.swing.JLabel jLabel2;
        public javax.swing.JScrollPane jScrollPane3;

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            e.getX();
            e.getY();
            System.out.println("X cordinates =" + e.getX());
            System.out.println("Y cordinates =" + e.getY());
        }

        public void mouseReleased(MouseEvent e) {
            e.getX();
            e.getY();
            System.out.println("X1 cordinates =" + e.getX());
            System.out.println("Y1 cordinates =" + e.getY());
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
}
