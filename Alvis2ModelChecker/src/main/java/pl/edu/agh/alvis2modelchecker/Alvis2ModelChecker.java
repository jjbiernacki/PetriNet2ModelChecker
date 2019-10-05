package pl.edu.agh.alvis2modelchecker;

import org.apache.commons.io.FileUtils;
import org.oxbow.swingbits.dialog.task.TaskDialog;
import pl.edu.agh.alvis2modelchecker.exceptions.ExtensionFilter;
import pl.edu.agh.alvis2modelchecker.generator.nuxmv.NuXMVAlvisGenerator;
import pl.edu.agh.alvis2modelchecker.parser.alvis.AlvisDotParser;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JCheckBoxMenuItem;
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

public class Alvis2ModelChecker {

    private JPanel panel1;
    private JTextField pathField;
    private JButton openButton;
    private JTextArea nuXMVTextArea;
    private JButton parseButton;
    private JComboBox comboBoxNuXMV;
    private JButton saveButton;
    private JTabbedPane tabbedPane;
    private JCheckBoxMenuItem menuExtendedOutput;
    private JCheckBoxMenuItem menuAlvisOnlyPV;


    private JFrame frame;
    JMenuBar menuBar;
    JMenu menuFile, menuParser, menuHelp, menuRtcpSimulator, menuGenerator;
    JMenuItem menuClose, menuOpen, menuOmega, menuAbout, menuEndtime;
    String parsedFileName = "", simulatorName="";
    boolean extendedOutput = false;
    boolean alvisOnlyPV = true;



    private boolean isEnvironmentalRun = false;

    private String[] alvisParsers = {"Alvis"};


    public static void main(String[] args) {
        JFrame frame = new JFrame("Alvis2ModelChecker");
        Alvis2ModelChecker alvis2ModelChecker = new Alvis2ModelChecker();
        if(args.length > 0 && args[0].equals("-envRun"))
            alvis2ModelChecker.setEnvironmentalRun(true);
        alvis2ModelChecker.frame = frame;
        alvis2ModelChecker.initView();
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
        menuGenerator= new JMenu("Generator");
        menuBar.add(menuGenerator);
        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
        menuAbout = new JMenuItem("About Alvis2ModelChecker...", KeyEvent.VK_H);
        menuHelp.add(menuAbout);
        menuOpen = new JMenuItem("Open...", KeyEvent.VK_O);
        menuOpen.addActionListener(openFile);
        menuFile.add(menuOpen);
        menuClose = new JMenuItem("Exit", KeyEvent.VK_Q);
        menuClose.addActionListener(closeListener);
        menuFile.add(menuClose);


        menuExtendedOutput = new JCheckBoxMenuItem("Extended output");
        menuExtendedOutput.addChangeListener(extendedOutputChanged);
        menuExtendedOutput.setState(extendedOutput);
        menuAlvisOnlyPV = new JCheckBoxMenuItem("Alvis - Only PV");
        menuAlvisOnlyPV.setState(alvisOnlyPV);
        menuAlvisOnlyPV.addChangeListener(menuAlvisOnlyPVChanged);
        menuGenerator.add(menuExtendedOutput);
        menuGenerator.add(menuAlvisOnlyPV);
        frame.setJMenuBar(menuBar);
        openButton.addActionListener(openFile);
        parseButton.addActionListener(parse);
        menuAbout.addActionListener(showHelp);
        saveButton.setEnabled(false);
        saveButton.addActionListener(saveFile);
        tabbedPane.addChangeListener(tabChanged);

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

    ChangeListener extendedOutputChanged = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            extendedOutput = menuExtendedOutput.getState();
        }
    };

    ChangeListener menuAlvisOnlyPVChanged = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            alvisOnlyPV = menuAlvisOnlyPV.getState();
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
            } else {
                //menuOpen.addActionListener(openFile2Aut);
            }
        }
    };


    ActionListener openFile = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter;
            filter = new ExtensionFilter("DOT file (*.dot)", ".dot");
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


    ActionListener closeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    ActionListener parse = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            parsedFileName = pathField.getText();
            parsedFileName = parsedFileName.substring(0, parsedFileName.indexOf("."));
            try {
                AlvisDotParser alvisParser = new AlvisDotParser();
                NuXMVAlvisGenerator generator = new NuXMVAlvisGenerator(alvisParser.parseFile(pathField.getText()));
                nuXMVTextArea.setText(generator.generateNuXmvCode(extendedOutput, alvisOnlyPV));
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