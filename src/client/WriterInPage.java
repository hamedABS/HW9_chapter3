package client;
import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Hamed-Abbaszadeh on 1/13/2018.
 */
public class WriterInPage extends JPanel implements Runnable
{
    DataOutputStream out;

    public WriterInPage(DataOutputStream out)
    {
        this.out = out;
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea2 = new javax.swing.JTextArea();
        sendBtn = new javax.swing.JButton();
        messageTxt = new javax.swing.JTextField();
    }

    public JTextArea getTxtArea2()
    {
        return txtArea2;
    }

    public void initComponents() {
        lbl1.setText("Me:");
        lbl2.setText("SomeOne :");
        txtArea1.setEditable(false);
        txtArea1.setColumns(20);
        txtArea1.setRows(5);
        txtArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtArea1);
        txtArea2.setEditable(false);
        txtArea2.setColumns(20);
        txtArea2.setRows(5);
        jScrollPane2.setViewportView(txtArea2);

        sendBtn.setText("Send");
        sendBtn.addActionListener(evt -> sendBtnActionPerformed(evt));

        messageTxt.setFont(new java.awt.Font("Tahoma", 0, 14));
        messageTxt.setToolTipText("press Send to send your message...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(messageTxt)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                        .addComponent(messageTxt))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {
        synchronized(this) {
            notify();
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton sendBtn;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtArea1;
    private javax.swing.JTextArea txtArea2;
    private javax.swing.JTextField messageTxt;
    // End of variables declaration

    @Override
    public synchronized void run()
    {
        try
        {
            while (true)
            {
                wait();
                System.out.println(messageTxt.getText());
                out.writeUTF(messageTxt.getText());
                txtArea1.append(messageTxt.getText()+"\n");
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
