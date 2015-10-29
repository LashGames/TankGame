/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverclienttank;

import client.Client;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import map.Map;

/**
 *
 * @author hp pc
 */
public class TankClientGUI extends javax.swing.JFrame {
    Client client=null;
    JLabel labelMap[][];  
    /**
     * Creates new form TankClientGUI
     */
    public TankClientGUI() {
        try {
            initComponents();
            getContentPane().setBackground(new Color(255,255,255));
            setLocationRelativeTo(null);            
            initMapPane();
            //requestFocus();
            Socket socket = null;
            System.out.println("The server is running.");
            
            client = new Client();
            client.connectToServer();
            TankClientGUI.Handler handler = new TankClientGUI.Handler(socket, client,labelMap);
            handler.start();
        } catch (IOException ex) {
            Logger.getLogger(TankClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void initMapPane(){
        labelMap= new JLabel[10][10];
        mapPane.setLayout(new GridLayout(10, 10));
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                JLabel lbl =new JLabel();
                lbl.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
                //lbl.setOpaque(true);
                //lbl.setBackground(Color.red);
                labelMap[i][j] = lbl;
                mapPane.add(lbl);
            }
        }
    }
    
    private static class Handler extends Thread {
        private JLabel[][] labelmap;
        private String[][] stringMap;
        private Socket socket;
        private Client client;
        ServerSocket listener;
        Map map = new Map();
        public Handler(Socket socket, Client client, JLabel[][] labelMap) {
            this.socket = socket;
            this.client = client;
            this.labelmap=labelMap;
        }
        public void updateMap(String[][] stringMap ){
            for(int i = 0;i<10;i++){
                for(int j = 0;j<10;j++){
                    if(stringMap[i][j].equals("B")){
                        labelmap[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bricks.png")));
                    }else if(stringMap[i][j].equals("S")){
                        labelmap[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stones.png")));
                    }else if(stringMap[i][j].equals("W")){
                        labelmap[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/water.png")));
                    }else{
                        labelmap[i][j].setIcon(null);
                    }
                }
            }
        }
        public void updatePlayers(ArrayList<Player> playerList){
            updateMap(stringMap);
            for (Player player : playerList) {
                if(player.getDirection() == 0){
                    labelmap[player.getY()][player.getX()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greentank_up.png"))); 
                }else if(player.getDirection() == 1){
                    labelmap[player.getY()][player.getX()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greentank_right.png"))); 
                }else if(player.getDirection() == 2){
                    labelmap[player.getY()][player.getX()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greentank_down.png"))); 
                }else if(player.getDirection() == 3){
                    labelmap[player.getY()][player.getX()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/greentank_left.png"))); 
                }
                
            }
        }
        public void run() {
            
            try {
                listener = new ServerSocket(7000);
            while (true) {
                try {
                    socket = listener.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = reader.readLine();
                    System.out.println(message);
                    if(message.charAt(0)=='G' && message.charAt(1)==':'){
                        map.updateMap(message);
                        updatePlayers(map.getPlayers());
                    }else if(message.charAt(0)=='I' && message.charAt(1)==':'){
                        map.createMap(message);
                        updateMap(map.getMap());
                        stringMap = map.getMap();
                    }
//                    client.send("RIGHT#");
//                    client.send("SHOOT#");                              
                } catch (IOException ex) {
                }
            }
        }   catch (IOException ex) {
                Logger.getLogger(ServerClientTank.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    listener.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerClientTank.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        }
        public Client getClient(){
            return this.client;
        } 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        mapPane = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        right_btn = new javax.swing.JButton();
        down_btn = new javax.swing.JButton();
        left_btn = new javax.swing.JButton();
        up_btn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        shoot_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(807, 529));
        setPreferredSize(new java.awt.Dimension(810, 820));
        setResizable(false);
        setSize(new java.awt.Dimension(810, 820));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(810, 660));
        jPanel2.setPreferredSize(new java.awt.Dimension(810, 660));
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel2KeyTyped(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mapPane.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mapPaneLayout = new javax.swing.GroupLayout(mapPane);
        mapPane.setLayout(mapPaneLayout);
        mapPaneLayout.setHorizontalGroup(
            mapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mapPaneLayout.setVerticalGroup(
            mapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jPanel2.add(mapPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 770, 490));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1KeyReleased(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        right_btn.setBackground(new java.awt.Color(255, 255, 255));
        right_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right_arrow.png"))); // NOI18N
        right_btn.setFocusable(false);
        right_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                right_btnActionPerformed(evt);
            }
        });
        jPanel1.add(right_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 90, 50));

        down_btn.setBackground(new java.awt.Color(255, 255, 255));
        down_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/down_arrow.png"))); // NOI18N
        down_btn.setFocusable(false);
        down_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                down_btnActionPerformed(evt);
            }
        });
        jPanel1.add(down_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 90, 50));

        left_btn.setBackground(new java.awt.Color(255, 255, 255));
        left_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left_arrow.png"))); // NOI18N
        left_btn.setFocusable(false);
        left_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                left_btnActionPerformed(evt);
            }
        });
        jPanel1.add(left_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, 50));

        up_btn.setBackground(new java.awt.Color(255, 255, 255));
        up_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/up_arrow.png"))); // NOI18N
        up_btn.setFocusable(false);
        up_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                up_btnMouseClicked(evt);
            }
        });
        up_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_btnActionPerformed(evt);
            }
        });
        up_btn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                up_btnKeyPressed(evt);
            }
        });
        jPanel1.add(up_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        shoot_btn.setBackground(new java.awt.Color(255, 255, 255));
        shoot_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shoot.png"))); // NOI18N
        shoot_btn.setContentAreaFilled(false);
        shoot_btn.setFocusable(false);
        shoot_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shoot_btnActionPerformed(evt);
            }
        });
        shoot_btn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                shoot_btnKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shoot_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shoot_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 770, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void right_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_right_btnActionPerformed
        try {
            client.sendCommand("RIGHT#");
        } catch (IOException ex) {
            Logger.getLogger(TankClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_right_btnActionPerformed

    private void shoot_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shoot_btnActionPerformed
        try {
            client.sendCommand("SHOOT#");
        } catch (IOException ex) {
            Logger.getLogger(TankClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_shoot_btnActionPerformed

    private void up_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_btnActionPerformed
        try {
            client.sendCommand("UP#");
        } catch (IOException ex) {
            Logger.getLogger(TankClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_up_btnActionPerformed

    private void left_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_left_btnActionPerformed
        try {
            client.sendCommand("LEFT#");
        } catch (IOException ex) {
            Logger.getLogger(TankClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_left_btnActionPerformed

    private void down_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_down_btnActionPerformed
        try {
            client.sendCommand("DOWN#");
        } catch (IOException ex) {
            Logger.getLogger(TankClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_down_btnActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
      
    }//GEN-LAST:event_jPanel2KeyPressed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        
    }//GEN-LAST:event_jPanel1KeyPressed

    private void shoot_btnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shoot_btnKeyPressed
        keyPressed(evt);
    }//GEN-LAST:event_shoot_btnKeyPressed

    private void up_btnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_up_btnKeyPressed
        
    }//GEN-LAST:event_up_btnKeyPressed

    private void up_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_up_btnMouseClicked
       
    }//GEN-LAST:event_up_btnMouseClicked

    private void jPanel2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyTyped
        
    }//GEN-LAST:event_jPanel2KeyTyped

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        
    }//GEN-LAST:event_formKeyTyped

    private void jPanel2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyReleased
        keyPressed(evt);
    }//GEN-LAST:event_jPanel2KeyReleased

    private void jPanel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyReleased
        keyPressed(evt);
    }//GEN-LAST:event_jPanel1KeyReleased

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        keyPressed(evt);
    }//GEN-LAST:event_formKeyReleased
    public void keyPressed(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_UP ){
            up_btn.doClick();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN ){
            down_btn.doClick();
        }else if(evt.getKeyCode() == KeyEvent.VK_LEFT ){
            left_btn.doClick();
        }else if(evt.getKeyCode() == KeyEvent.VK_RIGHT ){
            right_btn.doClick();
        }else if(evt.getKeyCode() == KeyEvent.VK_SPACE ){
            shoot_btn.doClick();
        }
    }
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TankClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TankClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TankClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TankClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                try {
                    UIManager.setLookAndFeel(new WindowsLookAndFeel());
                    new TankClientGUI().setVisible(true);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(TankClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton down_btn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton left_btn;
    private javax.swing.JPanel mapPane;
    private javax.swing.JButton right_btn;
    private javax.swing.JButton shoot_btn;
    private javax.swing.JButton up_btn;
    // End of variables declaration//GEN-END:variables
}
