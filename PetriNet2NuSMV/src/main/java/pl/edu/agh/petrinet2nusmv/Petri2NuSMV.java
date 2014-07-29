package pl.edu.agh.petrinet2nusmv;

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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by agnieszka on 19.02.14.
 */

public class Petri2NuSMV {

    private JPanel panel1;
    private JTextField pathField;
    private JButton openButton;
    private JTextArea nuSMVTextArea;
    private JButton parseButton;
    private JFrame frame;
    Parser parser = Parser.CPNPARSER;
    String generatedNuSMVFileContent = "";
    JMenuBar menuBar;
    JMenu menuFile, menuParser, menuHelp;
    JMenuItem menuClose, menuOpen, menuSave, menuOmega, menuAbout;
    JRadioButtonMenuItem cpnMenuItem, simpleNetMenuItem;
    String parsedFileName = "";
    int omega = 1000;


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
        menuSave = new JMenuItem("Save...", KeyEvent.VK_T);
        menuSave.addActionListener(saveFile);
        menuFile.add(menuSave);
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
        menuSave.setEnabled(false);
        nuSMVTextArea.setEditable(false);
        menuOmega.setEnabled(false);
        menuAbout.addActionListener(showHelp);
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
                    menuSave.setEnabled(true);
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
                    menuSave.setEnabled(true);
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
}
