
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ClientFrame extends javax.swing.JFrame {

    ArrayList<String> al = new ArrayList<>();
    ArrayList<String> alServer = new ArrayList<>();

    mytablemodel tm;
    mytablemodel1 tm2;

    public ClientFrame() {
        initComponents();
        setSize(1300, 650);
        tm = new mytablemodel();
        tm2 = new mytablemodel1();
        jTable1.setModel(tm);
        jTable2.setModel(tm2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Find = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        ss = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lbscreen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton1.setText("Click to Discover System!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 30, 130, 20);

        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 50, 130, 70);

        Find.setText("Find Server");
        Find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindActionPerformed(evt);
            }
        });
        getContentPane().add(Find);
        Find.setBounds(20, 120, 90, 23);

        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 150, 130, 100);

        ss.setText("Show Screen");
        ss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ssActionPerformed(evt);
            }
        });
        getContentPane().add(ss);
        ss.setBounds(20, 250, 100, 30);

        lbscreen.setText("Label");
        lbscreen.setPreferredSize(new java.awt.Dimension(130, 50));
        jScrollPane4.setViewportView(lbscreen);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(290, 10, 470, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SystemDiscovery discovery = new SystemDiscovery();
        Thread t = new Thread(discovery);
        t.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void FindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindActionPerformed
        Thread t = new Thread(new FindOnlineServer());
        t.start();

    }//GEN-LAST:event_FindActionPerformed

    private void ssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ssActionPerformed
        int value = jTable2.getSelectedRow();
        if (value == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first...");
        } else {
            String IP = alServer.get(value);
            try {
                Socket s = new Socket(IP, 4200);
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                DataInputStream dis = new DataInputStream(s.getInputStream());
                dos.writeBytes("Send Screen Size\r\n");
                String msg1 = dis.readLine();
                if (msg1.equals("Receive Screen Size")) {

                    int width = dis.readInt();
                    int height = dis.readInt();
                    Dimension d = new Dimension(width, height);
                    lbscreen.setText(width + " " + height);
                    lbscreen.setPreferredSize(d);
                    lbscreen.repaint();

                    ReceiveScreen rs = new ReceiveScreen(IP);
                    Thread t = new Thread(rs);
                    t.start();
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_ssActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Find;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbscreen;
    private javax.swing.JButton ss;
    // End of variables declaration//GEN-END:variables

    class SystemDiscovery implements Runnable {

        int count = 0;

        @Override
        public void run() {
            try {

                PingSingleIP psip[] = new PingSingleIP[51];
                Thread thread[] = new Thread[51];
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Slot " + (i));
                    for (int j = 0; j < psip.length; j++) {
                        String ip = "172.16.1." + count;
                        psip[j] = new PingSingleIP(ip);
                        thread[j] = new Thread(psip[j]);
                        thread[j].start();
                        count++;
                    }
                    for (int j = 0; j < thread.length; j++) {
                        thread[j].join();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        class PingSingleIP implements Runnable {

            String ip;

            PingSingleIP(String ip) {
                this.ip = ip;
            }

            @Override
            public void run() {
                try {
                    Process p = Runtime.getRuntime().exec("ping " + ip);
                    InputStreamReader isr = new InputStreamReader(p.getInputStream());
                    BufferedReader inputStream = new BufferedReader(isr);
                    String s = "";
                    // reading output stream of the command 
                    while (true) {
                        s = inputStream.readLine();
                        if (s == null) {
                            break;
                        } else if (s.contains("TTL")) {
                            al.add(ip);
                            tm.fireTableDataChanged();
                            break;
                        } else if (s.contains("timed out")) {
                            break;
                        } else if (s.contains("unreachable")) {
                            break;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class mytablemodel extends AbstractTableModel {

        @Override
        public String getColumnName(int column) {
            return "Online IP"; //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getRowCount() {
            return al.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return al.get(rowIndex);
        }
    }

    class mytablemodel1 extends AbstractTableModel {

        @Override
        public String getColumnName(int column) {
            return "Online Server"; //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getRowCount() {
            return alServer.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return alServer.get(rowIndex);
        }
    }

    class FindOnlineServer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < al.size(); i++) {
                try {
                    Socket sock = new Socket(al.get(i), 4200);
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                    dos.writeBytes("firstmessage\r\n");
                    alServer.add(al.get(i));
                    tm2.fireTableDataChanged();
                } catch (Exception ex) {
                }
            }
        }

    }

    class ReceiveScreen implements Runnable {

        String ip;
        Socket s;
        DataOutputStream dos;
        DataInputStream dis;

        public ReceiveScreen(String ip) {
            this.ip = ip;
        }

        @Override
        public void run() {
            try {
                s = new Socket(ip, 4200);
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());

                while (true) {
                    dos.writeBytes("sendscreen\r\n");
                    String msg = dis.readLine();

                    if (msg.equals("receivescreen")) {
                        long filelength = dis.readLong();
                        System.out.println(filelength);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
