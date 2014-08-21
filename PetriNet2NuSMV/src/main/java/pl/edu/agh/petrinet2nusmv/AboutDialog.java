package pl.edu.agh.petrinet2nusmv;

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
        setTitle("About Petri2NuSMV");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        String text = "The Petri2NuSMV tool deals with the problem of translation of reachability graphs\n" +
                "for place-transition, coloured and real-time coloured Petri nets into the NuSMV language. Petri2NuSMV works\n" +
                "with reachability graphs generated respectively by the TINA, CPN Tools and RTCPNC software. Thus, it provides\n" +
                "the possibility of formal verification of Petri nets designed with these environments using\n" +
                "model checking techniques and a mainstream model checker for LTL and CTL temporal logics.\n"+
                "It also integrates cpn2rtcpn and rtcpnc software and allows to load RTCP nets modelled in CPN Tools\n"+
                "and generate their coverability graphs or simulators.";
        aboutLabel.setText(convertToMultiline(text));
        picLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo.png")));

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
