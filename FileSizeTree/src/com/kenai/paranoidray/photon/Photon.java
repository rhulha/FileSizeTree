/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Photon.java
 *
 * Created on 27.10.2009, 18:52:54
 */

package com.kenai.paranoidray.photon;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ray
 */
public class Photon extends javax.swing.JFrame {

    /** Creates new form Photon */
    public Photon() {
        initComponents();
        setLocationRelativeTo(null);
        jListResult.setModel(new DefaultListModel());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTop = new javax.swing.JPanel();
        jTextFieldPath = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jTextFieldPhrase = new javax.swing.JTextField();
        jScrollPaneResult = new javax.swing.JScrollPane();
        jListResult = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabelCurrent = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelTop.setPreferredSize(new java.awt.Dimension(572, 40));

        jTextFieldPath.setText("C:\\IDE\\Server\\jboss-seam-2.2.0.GA\\examples");

        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jTextFieldPhrase.setText("selectOneMenu");

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTextFieldPath, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPhrase, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSearch)
                .addContainerGap())
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPhrase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch)))
        );

        getContentPane().add(jPanelTop, java.awt.BorderLayout.NORTH);

        jListResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListResultMouseClicked(evt);
            }
        });
        jScrollPaneResult.setViewportView(jListResult);

        getContentPane().add(jScrollPaneResult, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(572, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCurrent, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCurrent, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        // TODO add your handling code here:

        jButtonSearch.setEnabled(false);

        final ArrayList<String> list = new ArrayList<String>();
        for( int i = 0; i < jListResult.getModel().getSize(); i++)
        {
            list.add(""+jListResult.getModel().getElementAt(i));
        }

        ((DefaultListModel) jListResult.getModel()).clear();
        Thread t = new Thread()
        {
            @Override
            public void run() {
                File f = new File(jTextFieldPath.getText());

                for( int i = 0; i < list.size(); i++)
                {
                    if( search( new File(list.get(i)), jTextFieldPhrase.getText().getBytes()))
                        addListItem(list.get(i));
                }
                if( list.size() == 0)
                    search( f);
                //addListItem("Done.");
                setCurrentItem("Done.");
                enableSearchButton();
            }
        };
        t.start();

    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jListResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListResultMouseClicked
        // TODO add your handling code here:
        if( evt.getClickCount() == 2)
        {
            try {
                Runtime.getRuntime().exec(new String[]{"C:\\Program Files\\Windows NT\\Accessories\\wordpad.exe", ""+jListResult.getSelectedValue() });
                //Desktop.getDesktop().edit(new File("" + jListResult.getSelectedValue()));
            } catch (Exception ex) {
                Logger.getLogger(Photon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jListResultMouseClicked

    public void enableSearchButton()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jButtonSearch.setEnabled(true);
            }
        });
    }

    public void addListItem( final String line)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((DefaultListModel) jListResult.getModel()).addElement(line);
            }
        });
    }

    public void setCurrentItem( final String line)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jLabelCurrent.setText(line);
            }
        });
    }

    public void search(File f)
    {
        File[] listFiles = f.listFiles();
        for (File file : listFiles) {
            if( file.isDirectory() )
                search( file);
            else if( search(file, jTextFieldPhrase.getText().getBytes()))
                addListItem(file.getPath());
        }
    }

    public boolean compare( byte[] buf, byte[] phrase, int pos )
    {
        for (int i = 0; i < phrase.length; i++) {
            if( phrase[i] != buf[(pos++)%buf.length])
                return false;
        }
        return true;
    }

    public boolean search( File f, byte[] phrase)
    {
        setCurrentItem(f.getPath());
        byte[] ringbuf = new byte[phrase.length];
        byte[] onebyte = new byte[1];
        int pos=0;
        try {
            InputStream is = new BufferedInputStream( new FileInputStream(f));

            while( is.read(onebyte) != -1 )
            {
                ringbuf[pos%ringbuf.length] = onebyte[0];
                pos++;
                //System.out.println(new String(ringbuf));
                if( compare( ringbuf, phrase, pos%ringbuf.length))
                    return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(Photon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Photon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabelCurrent;
    private javax.swing.JList jListResult;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPaneResult;
    private javax.swing.JTextField jTextFieldPath;
    private javax.swing.JTextField jTextFieldPhrase;
    // End of variables declaration//GEN-END:variables

}