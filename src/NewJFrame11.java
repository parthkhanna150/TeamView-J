
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class NewJFrame11 extends javax.swing.JFrame {

    ArrayList<String> al = new ArrayList<>();
    MyTableModel tm;

    public NewJFrame11() {
        setLayout(null);
        setVisible(true);
        setSize(800, 800);
        initComponents();
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        String s = "172.16.3.";
        for (int i = 0; i <= 255; i++) {
            Client obj = new Client(s + i);
            bt1.setEnabled(false);
        }
    }//GEN-LAST:event_bt1ActionPerformed

    private void bt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt2ActionPerformed
        if (tb1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Select an IP ADDRESS first");
        } else {
            PhotoClient pc = new PhotoClient(al.get(tb1.getSelectedRow()));
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
//                String s = dis.readLine();
                while (true) {
                    Double height = dis.readDouble();
                    Double width = dis.readDouble();
                    System.out.println("Height of Screen" + height);
                    System.out.println("Width of Screen" + width);
                }

            } catch (Exception ex) {
                System.out.println("Not connected" + ipaddress);
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
                System.out.println("Not connected" + ipaddress);
            }

        }
    }
}
