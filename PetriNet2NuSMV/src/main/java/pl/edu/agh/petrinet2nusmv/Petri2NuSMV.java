package pl.edu.agh.petrinet2nusmv;

import org.apache.commons.io.FileUtils;
import pl.edu.agh.cpn2rtcpn.XmlParse;
import pl.edu.agh.petrinet2nusmv.exceptions.ExtensionFilter;
import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.generator.NuSMVCPNGenerator;
import pl.edu.agh.petrinet2nusmv.generator.NuSMVGenerator;
import pl.edu.agh.petrinet2nusmv.model.Parser;
import pl.edu.agh.petrinet2nusmv.parser.CPNParser;
import pl.edu.agh.petrinet2nusmv.parser.KTSParser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

/**
 * Created by agnieszka on 19.02.14.
 */

public class Petri2NuSMV {

    private JPanel panel1;
    private JTextField pathField;
    private JButton openButton;
    private JTextArea nuSMVTextArea;
    private JButton parseButton;
    private JTabbedPane tabbedPane1;
    private JTextField rtcpPathField;
    private JButton rtcpOpenButton;
    private JButton rtcpParseButton;
    private JTextArea rtcpTextArea;
    private JComboBox comboBoxRTCP;
    private JButton saveButton;
    private JButton rtcpSaveButton;
    private JFrame frame;
    Parser parser = Parser.CPNPARSER;
    String generatedNuSMVFileContent = "";
    JMenuBar menuBar;
    JMenu menuFile, menuParser, menuHelp;
    JMenuItem menuClose, menuOpen, menuOmega, menuAbout;
    JRadioButtonMenuItem cpnMenuItem, simpleNetMenuItem;
    String parsedFileName = "", parsedRTCPFileName = "";
    int omega = 1000;

    private String[] rtcpParsers = {"CPN Tools >> RTCP", "RTCP Net >> RTCP Simulator"};


    public static void main(String[] args) {
        JFrame frame = new JFrame("Petri2NuSMV");
        Petri2NuSMV petri2NuSMV = new Petri2NuSMV();
        petri2NuSMV.frame = frame;
        petri2NuSMV.initView();
    }

    public void initView() {
        initMenu();
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initMenu() {
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        menuParser = new JMenu("Parser");
        menuBar.add(menuParser);
        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
        menuAbout = new JMenuItem("About Petri2NuSMV...", KeyEvent.VK_T);
        menuHelp.add(menuAbout);
        menuOpen = new JMenuItem("Open...", KeyEvent.VK_T);
        menuOpen.addActionListener(openFile);
        menuFile.add(menuOpen);
        menuOmega = new JMenuItem("Omega...", KeyEvent.VK_T);
        menuOmega.addActionListener(setOmega);
        menuClose = new JMenuItem("Exit", KeyEvent.VK_T);
        menuClose.addActionListener(closeListener);
        menuFile.add(menuClose);
        ButtonGroup group = new ButtonGroup();
        cpnMenuItem = new JRadioButtonMenuItem("Coloured Petri Nets");
        cpnMenuItem.setSelected(true);
        group.add(cpnMenuItem);
        cpnMenuItem.addActionListener(cpnParserChoosed);
        menuParser.add(cpnMenuItem);
        simpleNetMenuItem = new JRadioButtonMenuItem("Place/transition Petri Nets");
        group.add(simpleNetMenuItem);
        simpleNetMenuItem.addActionListener(simpleParserChoosed);
        menuParser.add(simpleNetMenuItem);
        menuParser.addSeparator();
        menuParser.add(menuOmega);
        frame.setJMenuBar(menuBar);
        openButton.addActionListener(openFile);
        parseButton.addActionListener(parse);
        nuSMVTextArea.setEditable(false);
        menuOmega.setEnabled(false);
        menuAbout.addActionListener(showHelp);
        saveButton.setEnabled(false);
        saveButton.addActionListener(saveFile);

        //rtcp"
        for (String item: rtcpParsers) {
            comboBoxRTCP.addItem(item);
        }
        rtcpOpenButton.addActionListener(openFileRTCP);
        rtcpParseButton.addActionListener(parseRTCP);
        rtcpSaveButton.setEnabled(false);
        rtcpSaveButton.addActionListener(saveRTCPFile);
    }

    ActionListener showHelp = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AboutDialog aboutDialog = new AboutDialog();

            aboutDialog.pack();
            aboutDialog.setVisible(true);
        }
    };

    ActionListener setOmega = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String omegaStr = (String) JOptionPane.showInputDialog(null, "Set Omega parameter: ", "Options", JOptionPane.QUESTION_MESSAGE, null, null, "" + omega);
            try {
                omega = Integer.valueOf(omegaStr);
            } catch (NumberFormatException exc) {
                exc.printStackTrace();
            }
        }
    };

    ActionListener openFile = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter;
            if(parser == Parser.CPNPARSER) {
                filter = new ExtensionFilter("CPN Tools file  (*.cpn)", ".cpn");
            } else {
                filter = new ExtensionFilter("TINA kts file (*.kts)", ".kts");
            }
            fc.addChoosableFileFilter(filter);
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int returnVal = fc.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.print("Opening: " + file.getAbsolutePath());
                pathField.setText(file.getAbsolutePath());
            } else {
                System.out.print("Open command cancelled by user.");
            }
        }
    };

    ActionListener openFileRTCP = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter;
            int i = comboBoxRTCP.getSelectedIndex();

            switch (i) {
                case 0://cpntools to rtcp
                    filter = new ExtensionFilter("CPN Tools file  (*.cpn)", ".cpn");
                    break;
                case 1://rtcp net to rtcp simulator
                    filter = new ExtensionFilter("RTCP XML file  (*.xml)", ".xml");
                    break;
                default:
                    throw new IllegalStateException("No items to select");
            }
            fc.addChoosableFileFilter(filter);
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int returnVal = fc.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.print("Opening: " + file.getAbsolutePath());
                rtcpPathField.setText(file.getAbsolutePath());
            } else {
                System.out.print("Open command cancelled by user.");
            }
        }
    };

    ActionListener saveFile = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter = new ExtensionFilter("NuSMV file (*.smv)", ".smv");
            fc.addChoosableFileFilter(filter);
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fc.setSelectedFile(new File(parsedFileName + ".smv"));
            int returnVal = fc.showSaveDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                PrintWriter out = null;
                try {
                    out = new PrintWriter(file.getAbsolutePath());
                    out.println(generatedNuSMVFileContent);
                    out.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else {
                System.out.print("Open command cancelled by user.");
            }
        }
    };

    ActionListener saveRTCPFile = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter = new ExtensionFilter("XML file (*.xml)", ".xml");
            fc.addChoosableFileFilter(filter);
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fc.setSelectedFile(new File(parsedRTCPFileName + ".xml"));
            int returnVal = fc.showSaveDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                PrintWriter out = null;
                try {
                    out = new PrintWriter(file.getAbsolutePath());
                    out.println(rtcpTextArea.getText());
                    out.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else {
                System.out.print("Open command cancelled by user.");
            }
        }
    };

    ActionListener cpnParserChoosed = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            parser = Parser.CPNPARSER;
            menuOmega.setEnabled(false);
        }
    };

    ActionListener simpleParserChoosed = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            parser = Parser.SIMPLEPARSER;
            menuOmega.setEnabled(true);
        }
    };

    ActionListener closeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    ActionListener parse = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(parser == Parser.CPNPARSER) {
                try {
                    CPNParser cpnParser = new CPNParser();
                    NuSMVCPNGenerator generator = new NuSMVCPNGenerator(cpnParser.parseFile(pathField.getText()));
                    generatedNuSMVFileContent = generator.generateNuSMVModule();
                    nuSMVTextArea.setText(generatedNuSMVFileContent);
                    saveButton.setEnabled(true);
                    parsedFileName = pathField.getText();
                    parsedFileName = parsedFileName.substring(0, parsedFileName.indexOf(".cpn"));
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame,
                            "File not found.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (SyntaxException e2) {
                    JOptionPane.showMessageDialog(frame,
                            e2.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame,
                            e1.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    KTSParser ktsParser = new KTSParser();
                    ktsParser.setOmega(omega);
                    NuSMVGenerator generator = new NuSMVGenerator(ktsParser.parseFile(pathField.getText()));
                    generatedNuSMVFileContent = generator.generateNuSMVModule();
                    nuSMVTextArea.setText(generatedNuSMVFileContent);
                    saveButton.setEnabled(true);
                    parsedFileName = pathField.getText();
                    parsedFileName = parsedFileName.substring(0, parsedFileName.indexOf(".kts"));
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame,
                            "File not found.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (SyntaxException e2) {
                    JOptionPane.showMessageDialog(frame,
                            e2.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };
    ActionListener parseRTCP = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = comboBoxRTCP.getSelectedIndex();
            String path = rtcpPathField.getText();

            switch (i) {
                case 0://cpntools to rtcp
                    String parsedFile = new XmlParse().parse(path);
                    rtcpTextArea.setText(parsedFile);
                    rtcpSaveButton.setEnabled(true);
                    parsedRTCPFileName = rtcpPathField.getText();
                    parsedRTCPFileName = parsedRTCPFileName.substring(0, parsedRTCPFileName.indexOf(".cpn"));
                    break;
                case 1://rtcp net to rtcp simulator
                    try {
                        Process process = Runtime.getRuntime().exec("java -jar rtcpnc.jar \"" + path + "\" simulator", null, new File("rtcpnc"));
                        process.waitFor();
                        Scanner s1 = new Scanner(process.getInputStream()).useDelimiter("\\A");
                        System.out.println(s1.hasNext() ? s1.next() : "");
                        Scanner s2 = new Scanner(process.getErrorStream()).useDelimiter("\\A");
                        System.out.println(s2.hasNext() ? s2.next() : "");

                        final JFileChooser fc = new JFileChooser();
                        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

                        int returnVal = fc.showSaveDialog(frame);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            FileUtils.copyDirectory(new File("rtcpnc/simulator"), new File(file.getAbsolutePath() + "/simulator"));
                            FileUtils.deleteDirectory(new File("rtcpnc/simulator"));
                        } else {
                            System.out.print("Open command cancelled by user.");
                        }


                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    throw new IllegalStateException("No action for this item");
            }
        }
    };
}
