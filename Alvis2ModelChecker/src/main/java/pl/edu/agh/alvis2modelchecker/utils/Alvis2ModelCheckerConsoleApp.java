package pl.edu.agh.alvis2modelchecker.utils;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import pl.edu.agh.alvis2modelchecker.exceptions.SyntaxException;
import pl.edu.agh.alvis2modelchecker.generator.nuxmv.NuXMVAlvisGenerator;
import pl.edu.agh.alvis2modelchecker.logger.Logger;
import pl.edu.agh.alvis2modelchecker.parser.alvis.AlvisDotParser;

import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Alvis2ModelCheckerConsoleApp {

    public static void main(String[] args){
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("v", false, "verbose logging");
        options.addOption("t", false, "generate transitions");
        options.addOption("h", false, "help");
        options.addOption("a", false, "generate all variables (not only pv)");
        options.addOption("f", true, "file to parse");
        options.addOption("o", true, "output file name");

        try {
            CommandLine line = parser.parse( options, args );
            boolean generateTransitions = line.hasOption("t");
            boolean generateAllVariables = line.hasOption("a");
            boolean verbose = line.hasOption("v");
            Logger.verbose = verbose;
            if( line.hasOption( "f" ) ) {
                String file = line.getOptionValue( "f" );
                if(file == null) {
                    throw new Exception("Please provide a file to parse.");
                }

                AlvisDotParser alvisParser = new AlvisDotParser();
               
                Logger.i("File parsing " + file + "...");
                try {


                    NuXMVAlvisGenerator generator = new NuXMVAlvisGenerator(alvisParser.parseFile(file));
                    String nuXMV = generator.generateNuXmvCode(generateTransitions, !generateAllVariables);
                    Logger.d("==============================================================");
                    Logger.d(nuXMV);
                    Logger.d("==============================================================");
                    if( line.hasOption( "o" ) ) {
                        String outFile = line.getOptionValue( "o" );
                        if(outFile == null) {
                            throw new Exception("Couldn't save the file.");
                        }
                        PrintWriter out = new PrintWriter(outFile);
                        out.println(nuXMV);
                        out.close();
                        Logger.i("File saved successfully: "+outFile);
                    }
                } catch (FileNotFoundException e) {
                    throw new Exception("Couldn't find a file in the provided location.");
                } catch (SyntaxException e) {
                    throw new Exception(e.getMessage());
                }
            } else {
                throw new Exception("Please provide a file to parse.");
            }
        }
        catch(ParseException exp) {
            Logger.i("There was a problem with command line input parsing. Reason:");
            Logger.i(exp.getMessage());
            System.out.println();
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "a2mc", options );
            System.out.println();
            return;
        } catch(Exception exp) {
            Logger.i(exp.getMessage());
            System.out.println();
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "a2mc", options );
            System.out.println();
            return;
        }
    }


}
