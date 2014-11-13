package pl.edu.agh.petrinet2modelchecker;

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
        setTitle("About PetriNet2ModelChecker");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        String text = "The PetriNet2ModelChecker tool deals with the problem of translation of reachability graphs\n" +
                "for place-transition, coloured and real-time coloured Petri nets into the NuXMV language\n" +
                "and Aldebaran format.\n" +
                "PetriNet2ModelChecker works with reachability graphs generated respectively by the TINA,\n" +
                "CPN Tools and RTCPNC software. Thus, it provides the possibility of formal verification\n" + "" +
                "of Petri nets designed with these environments using model checking techniques and mainstream\n" +
                "model checkers - nuXmv for LTL and CTL temporal logics, CADP Evaluator for \n" +
                "regular alternation-free mu-calculus.\n" +
                "It also integrates cpn2rtcpn and rtcpnc software and allows to load RTCP nets modelled in\n" +
                "CPN Tools and generate their coverability graphs or simulators.";
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
