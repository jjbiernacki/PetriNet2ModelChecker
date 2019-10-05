package pl.edu.agh.petrinet2modelchecker;

import org.apache.commons.cli.*;
import pl.edu.agh.petrinet2modelchecker.exceptions.SyntaxException;
import pl.edu.agh.petrinet2modelchecker.generator.nuxmv.NuXMVCPNGenerator;
import pl.edu.agh.petrinet2modelchecker.logger.Logger;
import pl.edu.agh.petrinet2modelchecker.parser.formats.CPNToolsParser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CPNConsoleApplication {
	
    public static void main(String[] args){
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("v", false, "verbose logging");
        options.addOption("f", true, "file to parse");
        options.addOption("o", true, "output file name");
        HelpFormatter formatter = new HelpFormatter();
       // formatter.printHelp( "ConsoleApplication", options );
        System.out.println();
        try {
            CommandLine line = parser.parse( options, args );
            Logger.verbose = line.hasOption("v");
            if( line.hasOption( "f" ) ) {
                String file = line.getOptionValue( "f" );
                if(file == null) {
                	throw new Exception("Proszę podać plik do parsowania!");
                }

                CPNToolsParser cpnToolsParser = new CPNToolsParser();
                Logger.d("Parsowanie pliku " + file + "...");
                try {

            		
        			NuXMVCPNGenerator generator = new NuXMVCPNGenerator(cpnToolsParser.parseFile(file));
        			String nuXMV = generator.generateNuXMVModule();
        			Logger.d("==============================================================");
        			Logger.i(nuXMV);
        			Logger.d("==============================================================");
        			if( line.hasOption( "o" ) ) {
                        String outFile = line.getOptionValue( "o" );
                        if(outFile == null) {
                        	throw new Exception("Nie udało się zapisać do pliku!");
                        }
                        PrintWriter out = new PrintWriter(outFile);
                        out.println(nuXMV);
                        out.close();
                        Logger.i("Zapisano do pliku "+outFile);
        			}
        		} catch (FileNotFoundException e) {
        			throw new Exception("Nie znaleziono podanego pliku!");
        		} catch (SyntaxException e) {
        			throw new Exception(e.getMessage());
        		} 
            } else {
            	throw new Exception("Proszę podać plik do parsowania!");
            }
            
            
        }
        catch(ParseException exp) {
            Logger.i("Wystąpił błąd przy parsowaniu lini komend. Powód:");
            Logger.i(exp.getMessage());
            formatter.printHelp( "ConsoleApplication", options );
            return;
        } catch(Exception exp) {
        	Logger.i(exp.getMessage());
            return;
        }
    }
    

}
