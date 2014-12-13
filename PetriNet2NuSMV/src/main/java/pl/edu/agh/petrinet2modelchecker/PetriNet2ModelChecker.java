package pl.edu.agh.petrinet2modelchecker;

import org.apache.commons.io.FileUtils;
import org.oxbow.swingbits.dialog.task.TaskDialog;
import pl.edu.agh.cpn2rtcpn.XmlParse;
import pl.edu.agh.petrinet2modelchecker.exceptions.ExtensionFilter;
import pl.edu.agh.petrinet2modelchecker.exceptions.SyntaxException;
import pl.edu.agh.petrinet2modelchecker.generator.aut.AutGenerator;
import pl.edu.agh.petrinet2modelchecker.generator.nuxmv.NuXMVAlvisGenerator;
import pl.edu.agh.petrinet2modelchecker.generator.nuxmv.NuXMVCPNGenerator;
import pl.edu.agh.petrinet2modelchecker.generator.nuxmv.NuXMVGenerator;
import pl.edu.agh.petrinet2modelchecker.generator.nuxmv.NuXMVRTCPGenerator;
import pl.edu.agh.petrinet2modelchecker.model.base.ReachabilityGraph;
import pl.edu.agh.petrinet2modelchecker.parser.Parser;
import pl.edu.agh.petrinet2modelchecker.parser.alvis.AlvisDotParser;
import pl.edu.agh.petrinet2modelchecker.parser.formats.CPNToolsParser;
import pl.edu.agh.petrinet2modelchecker.parser.formats.KTSParser;
import pl.edu.agh.petrinet2modelchecker.parser.formats.RTCPParser;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by agnieszka on 19.02.14.
 */

public class PetriNet2ModelChecker {

    private JPanel panel1;
    private JTextField pathField;
    private JButton openButton;
    private JTextArea nuXMVTextArea;
    private JButton parseButton;
    private JTextField rtcpPathField;
    private JButton rtcpOpenButton;
    private JButton rtcpParseButton;
    private JTextArea rtcpTextArea;
    private JComboBox comboBoxRTCP;
    private JComboBox comboBoxNuXMV;
    private JButton saveButton;
    private JButton rtcpSaveButton;
    private JTabbedPane tabbedPane;
    private JButton exportToPdfButton;
    private JTextField autFieldPath;
    private JButton openToAutButton;
    private JButton convertToAutButton;
    private JComboBox toAutComboBox;
    private JButton saveAutButton;
    private JTextArea autTextArea;

    private JFrame frame;
    Parser parser2NuXMV = Parser.ALVIS;
    Parser parser2aut = Parser.RTCPPARSER;
    JMenuBar menuBar;
    JMenu menuFile, menuParser, menuHelp, menuRtcpSimulator;
    JMenuItem menuClose, menuOpen, menuOmega, menuAbout, menuEndtime;
    JRadioButtonMenuItem cpnMenuItem, simpleNetMenuItem;
    String parsedFileName = "", parsedRTCPFileName = "";
    int omega = 1000;
    int rtcpSimulatorEndTime = 1000;



    private boolean isEnvironmentalRun = false;

    private String[] rtcpParsers = {"CPN Tools >> Coverability Graph", "CPN Tools >> RTCP", "RTCP Net >> RTCP Simulator", "RTCP Net >> Coverability Graph"};
    private String[] cg2nsParsers = {"RTCP Nets","Coloured Petri Nets", "Place/transition Petri Nets", "Alvis"};
    private String[] cg2autParsers = {"RTCP Nets","Coloured Petri Nets", "Place/transition Petri Nets"};


    public static void main(String[] args) {
        JFrame frame = new JFrame("PetriNet2ModelChecker");
        PetriNet2ModelChecker petriNet2ModelChecker = new PetriNet2ModelChecker();
        if(args.length > 0 && args[0].equals("-envRun"))
            petriNet2ModelChecker.setEnvironmentalRun(true);
        petriNet2ModelChecker.frame = frame;
        petriNet2ModelChecker.initView();
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
        menuAbout = new JMenuItem("About PetriNet2ModelChecker...", KeyEvent.VK_H);
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
        openToAutButton.addActionListener(openFile2Aut);
        parseButton.addActionListener(parse);
        convertToAutButton.addActionListener(convertToAutActionListener);
        saveAutButton.setEnabled(false);
        saveAutButton.addActionListener(saveAutFileActionListener);
        menuOmega.setEnabled(false);
        menuAbout.addActionListener(showHelp);
        saveButton.setEnabled(false);
        saveButton.addActionListener(saveFile);
        tabbedPane.addChangeListener(tabChanged);
        toAutComboBox.addActionListener(parser2AutChosen);

        //parse2NuXMV
        for (String item: cg2nsParsers) {
            comboBoxNuXMV.addItem(item);
        }
        menuOmega.setEnabled(false);
        comboBoxNuXMV.addActionListener(parserChosen);

        //rtcp
        for (String item: rtcpParsers) {
            comboBoxRTCP.addItem(item);
        }
        rtcpOpenButton.addActionListener(openFileRTCP);
        rtcpParseButton.addActionListener(parseRTCP);
        rtcpSaveButton.setEnabled(false);
        exportToPdfButton.setEnabled(false);
        exportToPdfButton.addActionListener(pdfListener);
        rtcpSaveButton.addActionListener(saveRTCPFile);
        //parse2aut
        for (String item: cg2autParsers) {
            toAutComboBox.addItem(item);
        }
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
            } else if (selItem  == 1){
                menuOpen.addActionListener(openFileRTCP);
            } else {
                menuOpen.addActionListener(openFile2Aut);
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


    ActionListener parser2AutChosen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            int selItem = toAutComboBox.getSelectedIndex();
            switch (selItem) {
                case 0://RTCP nets
                    parser2aut = Parser.RTCPPARSER;
                    break;
                case 1://CP Nets
                    parser2aut = Parser.CPNPARSER;
                    break;
                case 2://PT Nets
                    parser2aut = Parser.SIMPLEPARSER;
                    break;
                default:
                    throw new IllegalStateException("No items to select");
            }
        }
    };

    ActionListener parserChosen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            int selItem = comboBoxNuXMV.getSelectedIndex();
            switch (selItem) {

                case 0://RTCP nets
                    parser2NuXMV = Parser.RTCPPARSER;
                    menuOmega.setEnabled(false);
                    break;
                case 1://CP Nets
                    parser2NuXMV = Parser.CPNPARSER;
                    menuOmega.setEnabled(false);
                    break;
                case 2://PT Nets
                    parser2NuXMV = Parser.SIMPLEPARSER;
                    menuOmega.setEnabled(true);
                    break;
                case 3://Alvis
                    parser2NuXMV = Parser.ALVIS;
                    menuOmega.setEnabled(false);
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
            if(parser2NuXMV == Parser.RTCPPARSER || parser2NuXMV == Parser.ALVIS){
                filter = new ExtensionFilter("DOT file (*.dot)", ".dot");
            }else if(parser2NuXMV == Parser.CPNPARSER) {
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

    ActionListener openFile2Aut = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter;
            if(parser2aut == Parser.RTCPPARSER){
                filter = new ExtensionFilter("DOT file (*.dot)", ".dot");
            }else if(parser2aut == Parser.CPNPARSER) {
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
                autFieldPath.setText(file.getAbsolutePath());
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
                case 0://cpntools to coverability graph
                case 1://cpntools to rtcp
                    filter = new ExtensionFilter("CPN Tools file  (*.cpn)", ".cpn");
                    break;
                case 2://rtcp net to rtcp simulator
                case 3://rtcp net to coverability graph
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

    ActionListener saveAutFileActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter = new ExtensionFilter("Aut file (*.aut)", ".aut");
            fc.addChoosableFileFilter(filter);
            fc.setFileFilter(filter);
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fc.setSelectedFile(new File(parsedFileName + ".aut"));
            int returnVal = fc.showSaveDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                PrintWriter out = null;
                try {
                    out = new PrintWriter(file.getAbsolutePath());
                    out.println(autTextArea.getText());
                    out.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else {
                System.out.print("Save command cancelled by user.");
            }
        }
    };


    ActionListener saveFile = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter = new ExtensionFilter("nuXmv file (*.smv)", ".smv");
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
                    out.println(nuXMVTextArea.getText());
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
                case 0:
                case 3://rtcp net to coverability graph
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

    ActionListener pdfListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String destFileName = null;
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                String extensionFileHint = "PDF file (*.pdf)";
                String extension = ".pdf";
                FileFilter filter = new ExtensionFilter(extensionFileHint, extension);
                fc.addChoosableFileFilter(filter);
                fc.setFileFilter(filter);
                fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fc.setSelectedFile(new File(parsedRTCPFileName + extension));
                int returnVal = fc.showSaveDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    destFileName = file.getAbsolutePath();
                } else {
                    System.out.print("Save command cancelled by user.");
                    return;
                }
                File tmpDir = new File("tmp");
                if (!tmpDir.exists()) {
                    tmpDir.mkdir();
                }
                File rtcpFile = new File(tmpDir.getAbsolutePath(), "rtcp.dot");
                if (!rtcpFile.exists()) {
                    rtcpFile.createNewFile();
                }
                PrintWriter out = new PrintWriter(rtcpFile.getAbsolutePath());
                out.println(rtcpTextArea.getText());
                out.close();
                ProcessBuilder createSimulatorPB = new ProcessBuilder("dot", "-Tpdf",  "\""+rtcpFile.getAbsolutePath()+"\"","-o","\""+destFileName+"\"");
                createSimulatorPB.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                createSimulatorPB.redirectErrorStream(true);
                createSimulatorPB.start().waitFor();
                FileUtils.deleteDirectory(tmpDir);
            //otworzenie pliku:
                //new ProcessBuilder("\""+destFileName+"\"").start();
                Desktop.getDesktop().open(new File(destFileName));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
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
            if(parser2NuXMV == Parser.ALVIS) {
                parsedFileName = pathField.getText();
                parsedFileName = parsedFileName.substring(0, parsedFileName.indexOf("."));
                try {
                    AlvisDotParser alvisParser = new AlvisDotParser();
                    NuXMVAlvisGenerator generator = new NuXMVAlvisGenerator(alvisParser.parseFile(pathField.getText()));
                    nuXMVTextArea.setText(generator.generateNuXmvCode());
                    saveButton.setEnabled(true);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame,
                            "File not found.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame,
                            e1.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if(parser2NuXMV == Parser.RTCPPARSER) {
                parsedFileName = pathField.getText();
                parsedFileName = parsedFileName.substring(0, parsedFileName.indexOf("."));
                try {
                    RTCPParser rtcpParser = new RTCPParser();
                    NuXMVRTCPGenerator generator = new NuXMVRTCPGenerator(rtcpParser.parseFile(pathField.getText()));
                    nuXMVTextArea.setText(generator.generateNuXMVModule());
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
            }else if(parser2NuXMV == Parser.CPNPARSER) {
                try {
                    CPNToolsParser cpnToolsParser = new CPNToolsParser();
                    NuXMVCPNGenerator generator = new NuXMVCPNGenerator(cpnToolsParser.parseFile(pathField.getText()));
                    nuXMVTextArea.setText(generator.generateNuXMVModule());
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
                    NuXMVGenerator generator = new NuXMVGenerator(ktsParser.parseFile(pathField.getText()));
                    nuXMVTextArea.setText(generator.generateNuXMVModule());
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

    ActionListener convertToAutActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ReachabilityGraph reachabilityGraph;
                if(parser2aut == Parser.RTCPPARSER) {
                    RTCPParser rtcpParser = new RTCPParser();
                    reachabilityGraph = rtcpParser.parseFile(autFieldPath.getText());
                } else if(parser2aut == Parser.CPNPARSER) {
                    CPNToolsParser cpnToolsParser = new CPNToolsParser();
                    reachabilityGraph = cpnToolsParser.parseFile(autFieldPath.getText());
                } else {
                    KTSParser ktsParser = new KTSParser();
                    reachabilityGraph = ktsParser.parseFile(autFieldPath.getText());
                }
                AutGenerator generator = new AutGenerator(reachabilityGraph);
                parsedFileName = autFieldPath.getText();
                parsedFileName = parsedFileName.substring(0, parsedFileName.indexOf("."));
                autTextArea.setText(generator.generateAut());
                saveAutButton.setEnabled(true);
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

        }
    };
    ActionListener parseRTCP = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = comboBoxRTCP.getSelectedIndex();
            String path = rtcpPathField.getText();
            try {

                String currentDir = getCurrentDirectory();
                switch (i) {
                    case 0:
                        try {
                            String parsedFile = new XmlParse().parse(path);
                            parsedRTCPFileName = rtcpPathField.getText();
                            parsedRTCPFileName = parsedRTCPFileName.substring(0, parsedRTCPFileName.indexOf("."));
                            File tmpDir = new File("tmp");
                            if (!tmpDir.exists()) {
                                tmpDir.mkdir();
                            }
                            File rtcpFile = new File(tmpDir.getAbsolutePath(), "rtcp.xml");
                            if (!rtcpFile.exists()) {
                                rtcpFile.createNewFile();
                            }
                            PrintWriter out = new PrintWriter(rtcpFile.getAbsolutePath());
                            out.println(parsedFile);
                            out.close();
                            generateCGFromRTCPFile(rtcpFile.getAbsolutePath(), currentDir);
                            FileUtils.deleteDirectory(tmpDir);
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(frame,
                                    "Error occurred. You probably chose the wrong file to convert.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);

                        }
                        break;
                    case 1://cpntools to rtcp
                        try {
                            String parsedFile = new XmlParse().parse(path);
                            rtcpTextArea.setText(parsedFile);
                            rtcpSaveButton.setEnabled(true);
                            exportToPdfButton.setEnabled(false);
                            parsedRTCPFileName = rtcpPathField.getText();
                            parsedRTCPFileName = parsedRTCPFileName.substring(0, parsedRTCPFileName.indexOf("."));
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(frame,
                                    "Error occurred. You probably chose the wrong file to convert.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);

                        }
                        break;
                    case 2://rtcp net to rtcp simulator
                        exportToPdfButton.setEnabled(false);
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
                            showErrorDialog(frame, "Error","Error occurred. Either the chosen file is corrupted or you do not have tools.jar library in '<JRE path>/lib' folder.", "Details");
                        }
                        break;
                    case 3://rtcp net to coverability graph
                        parsedRTCPFileName = rtcpPathField.getText();
                        parsedRTCPFileName = parsedRTCPFileName.substring(0, parsedRTCPFileName.indexOf("."));
                        generateCGFromRTCPFile(path, currentDir);
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

    String getCurrentDirectory() throws URISyntaxException, IOException {
        String currentDir;
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
        return currentDir;
    }

    private void generateCGFromRTCPFile(String path, String currentDir) {

        StringBuilder logs = new StringBuilder();
        try {
            ProcessBuilder createSimulatorPB = new ProcessBuilder("java", "-jar", "rtcpnc.jar", "\"" + path + "\"", "simulator");
            createSimulatorPB.directory(new File(currentDir + "rtcpnc"));
            createSimulatorPB.redirectErrorStream(true);
            Process processGenerateSimulator = createSimulatorPB.start();
            BufferedReader outputGenerateSimulator = new BufferedReader(new InputStreamReader(processGenerateSimulator.getInputStream()));
            String line = "";
            while ((line = outputGenerateSimulator.readLine()) != null) {
                logs.append(line);
                logs.append("\n");
            }
            processGenerateSimulator.waitFor();

            ProcessBuilder getCoverabilityGraphPB = new ProcessBuilder("java", "-jar", "simulator.jar" , String.valueOf(rtcpSimulatorEndTime),  "-cg");
            getCoverabilityGraphPB.directory(new File(currentDir + "rtcpnc/simulator"));
            getCoverabilityGraphPB.redirectErrorStream(true);
            Process processSimulate = getCoverabilityGraphPB.start();
            BufferedReader outputCG = new BufferedReader(new InputStreamReader(processSimulate.getInputStream()));
            logs = new StringBuilder();
            while ((line = outputCG.readLine()) != null) {
                logs.append(line);
                logs.append("\n");
            }
            processSimulate.waitFor();

            String coverabilityGraphText = new String(Files.readAllBytes(Paths.get(currentDir + "rtcpnc/simulator", "coverability-graph.dot")));
            rtcpTextArea.setText(coverabilityGraphText);
            rtcpSaveButton.setEnabled(true);
            exportToPdfButton.setEnabled(true);

        } catch (Exception e1) {
            showErrorDialog(frame, "Error", "Error occurred. Either the chosen file is corrupted or you do not have tools.jar library in '[JRE path]/lib' folder.", logs.toString());
        } finally {
            try {
                FileUtils.deleteDirectory(new File(currentDir + "rtcpnc/simulator"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void showErrorDialog(Window parent, String title, String instruction, String details) {
        TaskDialog dlg = new TaskDialog(parent, title);
        dlg.setResizable(true);
        dlg.setInstruction(instruction);
        dlg.setIcon(TaskDialog.StandardIcon.ERROR);

        JScrollPane scrollPane = new JScrollPane(new JTextArea(details));

        scrollPane.setMaximumSize(new Dimension(300, 200));

        dlg.getDetails().setExpandableComponent(scrollPane);
        dlg.getDetails().setCollapsedLabel("Show details");

        dlg.getDetails().setExpandedLabel("Hide details");
        dlg.getDetails().setExpanded(false);
        dlg.show();

    }

}
