package pl.edu.agh.petrinet2nusmv;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.generator.nuxmv.NuXMVGenerator;
import pl.edu.agh.petrinet2nusmv.logger.Logger;
import pl.edu.agh.petrinet2nusmv.parser.KTSParser;

public class ConsoleApplication {
	
    public static void main(String[] args){
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("v", false, "verbose logging");
        options.addOption("f", true, "file to parse");
        options.addOption("w", true, "omega value");
        options.addOption("o", true, "output file name");
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "ConsoleApplication", options );
        System.out.println();
        try {
            CommandLine line = parser.parse( options, args );
            Logger.verbose = line.hasOption("v");
            if( line.hasOption( "f" ) ) {
                String file = line.getOptionValue( "f" );
                if(file == null) {
                	throw new Exception("Proszę podać plik do parsowania!");
                }

                KTSParser ktsParser = new KTSParser();
                if( line.hasOption( "w" ) ) {
                    String omega = line.getOptionValue( "w" );
                    if(omega == null) {
                    	throw new Exception("Niepoprawna wartość parametru omega!");
                    } else {
                    	try {
                    		int omegaNumber = Integer.parseInt(omega);
                    		ktsParser.setOmega(omegaNumber);
                    	} catch (NumberFormatException e) {
                        	throw new Exception("Niepoprawna wartość parametru omega!");
                    	}
                    }
                }
                Logger.i("Parsowanie pliku " + file + "...");
                try {

            		
        			NuXMVGenerator generator = new NuXMVGenerator(ktsParser.parseFile(file));
        			String nuSMV = generator.generateNuSMVModule(); 
        			Logger.i("==============================================================");
        			Logger.i(nuSMV);
        			Logger.i("==============================================================");
        			if( line.hasOption( "o" ) ) {
                        String outFile = line.getOptionValue( "o" );
                        if(outFile == null) {
                        	throw new Exception("Nie udało się zapisać do pliku!");
                        }
                        PrintWriter out = new PrintWriter(outFile);
                        out.println(nuSMV);
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
            return;
        } catch(Exception exp) {
        	Logger.i(exp.getMessage());
            return;
        }
    }
    

}
