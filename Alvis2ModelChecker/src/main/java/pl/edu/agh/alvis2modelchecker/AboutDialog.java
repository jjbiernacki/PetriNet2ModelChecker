package pl.edu.agh.alvis2modelchecker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel aboutLabel;
    private JLabel picLabel;

    public AboutDialog() {
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        setTitle("About Alvis2ModelChecker");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        String text = "The Alvis2ModelChecker tool deals with the problem of translation of Alvis LTS\n" +
                "into the SMV language supported by nuXmv / NuSMV tools";
        aboutLabel.setText(convertToMultiline(text));
        //picLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo.png")));

    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    private void onOK() {
// add your code here
        dispose();
    }

    public static void main(String[] args) {
        AboutDialog dialog = new AboutDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
