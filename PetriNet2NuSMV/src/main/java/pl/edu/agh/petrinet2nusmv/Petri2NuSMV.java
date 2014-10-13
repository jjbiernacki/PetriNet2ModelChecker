package pl.edu.agh.petrinet2nusmv;

import org.apache.commons.io.FileUtils;
import pl.edu.agh.cpn2rtcpn.XmlParse;
import pl.edu.agh.petrinet2nusmv.exceptions.ExtensionFilter;
import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.generator.NuSMVCPNGenerator;
import pl.edu.agh.petrinet2nusmv.generator.NuSMVGenerator;
import pl.edu.agh.petrinet2nusmv.generator.NuSMVRTCPGenerator;
import pl.edu.agh.petrinet2nusmv.model.Parser;
import pl.edu.agh.petrinet2nusmv.parser.CPNParser;
import pl.edu.agh.petrinet2nusmv.parser.KTSParser;
import pl.edu.agh.petrinet2nusmv.parser.RTCPParser;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private JTextField rtcpPathField;
    private JButton rtcpOpenButton;
    private JButton rtcpParseButton;
    private JTextArea rtcpTextArea;
    private JComboBox comboBoxRTCP;
    private JComboBox comboBoxNuSMV;
    private JButton saveButton;
    private JButton rtcpSaveButton;
    private JTabbedPane tabbedPane;

    private JFrame frame;
    Parser parser = Parser.CPNPARSER;
    JMenuBar menuBar;
    JMenu menuFile, menuParser, menuHelp, menuRtcpSimulator;
    JMenuItem menuClose, menuOpen, menuOmega, menuAbout, menuEndtime;
    JRadioButtonMenuItem cpnMenuItem, simpleNetMenuItem;
    String parsedFileName = "", parsedRTCPFileName = "";
    int omega = 1000;
    int rtcpSimulatorEndTime = 1000;



    private boolean isEnvironmentalRun = false;

    private String[] rtcpParsers = {"CPN Tools >> RTCP", "RTCP Net >> RTCP Simulator", "RTCP Net >> Coverability Graph"};
    private String[] cg2nsParsers = {"RTCP Nets","Coloured Petri Nets", "Place/transition Petri Nets"};


    public static void main(String[] args) {
        JFrame frame = new JFrame("PetriNet2NuSMV");
        Petri2NuSMV petri2NuSMV = new Petri2NuSMV();
        if(args.length > 0 && args[0].equals("-envRun"))
            petri2NuSMV.setEnvironmentalRun(true);
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
        menuRtcpSimulator = new JMenu("RTCP Simulator");
        menuBar.add(menuRtcpSimulator);
        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
        menuAbout = new JMenuItem("About Petri2NuSMV...", KeyEvent.VK_H);
        menuHelp.add(menuAbout);
        menuOpen = new JMenuItem("Open...", KeyEvent.VK_O);
        menuOpen.addActionListener(openFile);
        menuFile.add(menuOpen);
        menuOmega = new JMenuItem("Omega...", KeyEvent.VK_M);
        menuOmega.addActionListener(setOmega);
        menuClose = new JMenuItem("Exit", KeyEvent.VK_Q);
        menuClose.addActionListener(closeListener);
        menuFile.add(menuClose);
        menuParser.add(menuOmega);
        menuEndtime = new JMenuItem("Endtime...", KeyEvent.VK_M);
        menuEndtime.addActionListener(setEndtime);
        menuRtcpSimulator.add(menuEndtime);
        frame.setJMenuBar(menuBar);
        openButton.addActionListener(openFile);
        parseButton.addActionListener(parse);
        nuSMVTextArea.setEditable(false);
        menuOmega.setEnabled(false);
        menuAbout.addActionListener(showHelp);
        saveButton.setEnabled(false);
        saveButton.addActionListener(saveFile);
        tabbedPane.addChangeListener(tabChanged);

        //parse2NuSMV
        for (String item: cg2nsParsers) {
            comboBoxNuSMV.addItem(item);
        }
        parser = Parser.RTCPPARSER;
        menuOmega.setEnabled(false);
        comboBoxNuSMV.addActionListener(parserChosen);

        //rtcp
        for (String item: rtcpParsers) {
            comboBoxRTCP.addItem(item);
        }
        rtcpOpenButton.addActionListener(openFileRTCP);
        rtcpParseButton.addActionListener(parseRTCP);
        rtcpSaveButton.setEnabled(false);
        rtcpSaveButton.addActionListener(saveRTCPFile);
    }

    public boolean isEnvironmentalRun() {
        return isEnvironmentalRun;
    }

    public void setEnvironmentalRun(boolean isEnvironmentalRun) {
        this.isEnvironmentalRun = isEnvironmentalRun;
    }

    ActionListener showHelp = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AboutDialog aboutDialog = new AboutDialog();
            aboutDialog.pack();
            aboutDialog.setVisible(true);
        }
    };

    ChangeListener tabChanged = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            int selItem = tabbedPane.getSelectedIndex();
            for(ActionListener al: menuOpen.getActionListeners()){
                menuOpen.removeActionListener(al);
            }
            if (selItem  == 0){

                menuOpen.addActionListener(openFile);
            }
            else
            {
                menuOpen.addActionListener(openFileRTCP);
            }
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

    ActionListener setEndtime = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String endtimeStr = (String) JOptionPane.showInputDialog(null, "Set endtime parameter: ", "Options", JOptionPane.QUESTION_MESSAGE, null, null, "" + rtcpSimulatorEndTime);
            try {
                rtcpSimulatorEndTime = Integer.valueOf(endtimeStr);
            } catch (NumberFormatException exc) {
                exc.printStackTrace();
            }
        }
    };

    ActionListener parserChosen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            int selItem = comboBoxNuSMV.getSelectedIndex();
            switch (selItem) {
                case 0://RTCP nets
                    parser = Parser.RTCPPARSER;
                    menuOmega.setEnabled(false);
                    break;
                case 1://CP Nets
                    parser = Parser.CPNPARSER;
                    menuOmega.setEnabled(false);
                    break;
                case 2://PT Nets
                    parser = Parser.SIMPLEPARSER;
                    menuOmega.setEnabled(true);
                    break;
                default:
                    throw new IllegalStateException("No items to select");
            }
        }
    };


    ActionListener openFile = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter;
            if(parser == Parser.RTCPPARSER){
                filter = new ExtensionFilter("DOT file (*.dot)", ".dot");
            }else if(parser == Parser.CPNPARSER) {
                filter = new ExtensionFilter("CPN Tools file  (*.cpn)", ".cpn");
            } else{
                filter = new ExtensionFilter("TINA kts file (*.kts)", ".kts");
            }
            fc.addChoosableFileFilter(filter);
            fc.setFileFilter(filter);
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
                case 2://rtcp net to coverability graph
                    filter = new ExtensionFilter("RTCP XML file  (*.xml)", ".xml");
                    break;
                default:
                    throw new IllegalStateException("No items to select");
            }
            fc.addChoosableFileFilter(filter);
            fc.setFileFilter(filter);
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
            fc.setFileFilter(filter);
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fc.setSelectedFile(new File(parsedFileName + ".smv"));
            int returnVal = fc.showSaveDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                PrintWriter out = null;
                try {
                    out = new PrintWriter(file.getAbsolutePath());
                    out.println(nuSMVTextArea.getText());
                    out.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else {
                System.out.print("Save command cancelled by user.");
            }
        }
    };

    ActionListener saveRTCPFile = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            int selectedItem = comboBoxRTCP.getSelectedIndex();

            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

            String extensionFileHint;
            String extension;
            switch (selectedItem) {
                case 2://rtcp net to coverability graph
                    extensionFileHint = "DOT file (*.dot)";
                    extension = ".dot";
                    break;
                default:
                    extensionFileHint = "XML file (*.xml)";
                    extension = ".xml";
                    break;
            }
            FileFilter filter = new ExtensionFilter(extensionFileHint, extension);
            fc.addChoosableFileFilter(filter);
            fc.setFileFilter(filter);
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fc.setSelectedFile(new File(parsedRTCPFileName + extension));
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
                System.out.print("Save command cancelled by user.");
            }
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
            if(parser == Parser.RTCPPARSER) {
                parsedFileName = pathField.getText();
                parsedFileName = parsedFileName.substring(0, parsedFileName.indexOf("."));
                try {
                    RTCPParser rtcpParser = new RTCPParser();
                    NuSMVRTCPGenerator generator = new NuSMVRTCPGenerator(rtcpParser.parseFile(pathField.getText()));
                    nuSMVTextArea.setText(generator.generateNuSMVModule());
                    saveButton.setEnabled(true);
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
            }else if(parser == Parser.CPNPARSER) {
                try {
                    CPNParser cpnParser = new CPNParser();
                    NuSMVCPNGenerator generator = new NuSMVCPNGenerator(cpnParser.parseFile(pathField.getText()));
                    nuSMVTextArea.setText(generator.generateNuSMVModule());
                    saveButton.setEnabled(true);
                    parsedFileName = pathField.getText();
                    parsedFileName = parsedFileName.substring(0, parsedFileName.indexOf("."));
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
                    nuSMVTextArea.setText(generator.generateNuSMVModule());
                    saveButton.setEnabled(true);
                    parsedFileName = pathField.getText();
                    parsedFileName = parsedFileName.substring(0, parsedFileName.indexOf("."));
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
            String currentDir;

            try {
                if(isEnvironmentalRun){
                    currentDir = Paths.get("").toAbsolutePath().toString() + "/";                    //Dla środowiska
                }
                else{
                    URL url = null;
                    url = getClass().getResource("").toURI().toURL();

                    currentDir = url.getPath() + "/";                                                // Dla konsoli

                    if (url.getProtocol().equalsIgnoreCase("jar")) {
                        currentDir = new File(((JarURLConnection)url.openConnection()).getJarFileURL().getFile()).getParent() + "/";
                    }
                    currentDir = currentDir.replaceAll("%20"," ");
                }

                switch (i) {
                    case 0://cpntools to rtcp
                        try {
                            String parsedFile = new XmlParse().parse(path);
                            rtcpTextArea.setText(parsedFile);
                            rtcpSaveButton.setEnabled(true);
                            parsedRTCPFileName = rtcpPathField.getText();
                            parsedRTCPFileName = parsedRTCPFileName.substring(0, parsedRTCPFileName.indexOf("."));
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(frame,
                                    "Error occurred. You probably chose the wrong file to convert.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);

                        }
                        break;
                    case 1://rtcp net to rtcp simulator
                        try {

                            ProcessBuilder createSimulatorPB = new ProcessBuilder("java", "-jar", "rtcpnc.jar", "\"" + path + "\"", "simulator");
                            createSimulatorPB.directory(new File(currentDir + "rtcpnc"));
                            createSimulatorPB.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                            createSimulatorPB.redirectErrorStream(true);
                            createSimulatorPB.start().waitFor();

                            final JFileChooser dirChooser = new JFileChooser();
                            dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            dirChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

                            int returnVal = dirChooser.showSaveDialog(frame);
                            if (returnVal == JFileChooser.APPROVE_OPTION) {
                                File selectedDir = dirChooser.getSelectedFile();
                                FileUtils.copyDirectory(new File(currentDir + "rtcpnc/simulator"), new File(selectedDir.getAbsolutePath() + "/simulator"));
                                FileUtils.deleteDirectory(new File(currentDir + "rtcpnc/simulator"));
                            } else {
                                System.out.print("Open command cancelled by user.");
                            }
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(frame,
                                    "Error occurred. Either you chose the wrong file to convert or you do not have tools.jar library in '<JRE path>/lib' folder.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case 2://rtcp net to coverability graph
                        parsedRTCPFileName = rtcpPathField.getText();
                        parsedRTCPFileName = parsedRTCPFileName.substring(0, parsedRTCPFileName.indexOf("."));
                        try {
                            ProcessBuilder createSimulatorPB = new ProcessBuilder("java", "-jar", "rtcpnc.jar", "\"" + path + "\"", "simulator");
                            createSimulatorPB.directory(new File(currentDir + "rtcpnc"));
                            createSimulatorPB.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                            createSimulatorPB.redirectError(ProcessBuilder.Redirect.INHERIT);
                            createSimulatorPB.start().waitFor();

                            ProcessBuilder getCoverabilityGraphPB = new ProcessBuilder("java", "-jar", "simulator.jar" , String.valueOf(rtcpSimulatorEndTime),  "-cg");
                            getCoverabilityGraphPB.directory(new File(currentDir + "rtcpnc/simulator"));
                            getCoverabilityGraphPB.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                            getCoverabilityGraphPB.redirectError(ProcessBuilder.Redirect.INHERIT);
                            getCoverabilityGraphPB.start().waitFor();

                            String coverabilityGraphText = new String(Files.readAllBytes(Paths.get(currentDir + "rtcpnc/simulator", "coverability-graph.dot")));
                            rtcpTextArea.setText(coverabilityGraphText);
                            rtcpSaveButton.setEnabled(true);

                            FileUtils.deleteDirectory(new File(currentDir + "rtcpnc/simulator"));
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(frame,
                                    "Error occurred. Either you chose the wrong file to convert or you do not have tools.jar library in '<JRE path>/lib' folder.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    default:
                        throw new IllegalStateException("No action for this item");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(frame,
                        "Application directory not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    };

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
