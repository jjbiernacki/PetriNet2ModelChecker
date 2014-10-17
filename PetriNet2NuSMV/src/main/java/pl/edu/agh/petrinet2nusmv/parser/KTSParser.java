package pl.edu.agh.petrinet2nusmv.parser;

import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.logger.Logger;
import pl.edu.agh.petrinet2nusmv.model.base.Place;
import pl.edu.agh.petrinet2nusmv.model.base.ReachabilityGraph;
import pl.edu.agh.petrinet2nusmv.model.base.State;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
/**
 * Parser pliku .kts do obiektu klasy grafu osiągalności ReachabilityGraph
 * @author abiernacka, jbiernacki
 *
 */
public class KTSParser implements CovGraphParserInterface {

	private TreeSet<Place> places = new TreeSet<Place>();
	private List<State> states = new ArrayList<State>();
	private Map<State, String> transStrings = new TreeMap<State, String>();
	private int omega = 1000;
	
	/**
	 * Ustawienie listy inicjalizacyjnej miejsc
	 * @param places Lista inicjalizacyjna miejsc
	 */
	@Override
	public void setInitPlacesList(List<Place> places) {
		places.addAll(places);
	}
	
	/**
	 * Metoda parsuje podany plik .kts do obiektu klasy grafu osiągalności
	 * @param filepath Ścieżka do parsowanego pliku
	 * @return Graf osiągalności
	 * @throws FileNotFoundException 
	 * @throws SyntaxException
	 */
	@Override
	public ReachabilityGraph parseFile(final String filepath) throws FileNotFoundException, SyntaxException {
		Scanner in = new Scanner(new FileReader(filepath));

		findStates(in);
		for(State state: states) {
			Logger.d(state.toString());
		}
		
		ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
		reachabilityGraph.setPlaces(places);
		reachabilityGraph.setStates(states);
		reachabilityGraph.setOmega(omega);
		
		return reachabilityGraph;
		
	}

	/**
	 * Metoda w podanym pliku wyszukuje stany i zapisuje je do listy obiektów klasy State
	 * @param in Wejście (plik)
	 * @throws SyntaxException
	 */
	private void findStates(Scanner in) throws SyntaxException {
		while(in.hasNextLine()) {
			String line = in.nextLine();
			try {
				if(line.length() >=5 && line.substring(0, 5).equals("state")) {
					int id = Integer.valueOf(line.substring(6));
					State state = new State(id);
					states.add(state);
					if(!in.hasNextLine()) {
						throw new SyntaxException("No marking for state: " + line);
					} else {
						String nextLine = in.nextLine();
						if(!(nextLine.length() >=5 && nextLine.substring(0, 5).equals("props"))) {
							throw new SyntaxException("No marking for state: " + line);
						}
						findMarking(state, nextLine);
					}
					if(!in.hasNextLine()) {
						throw new SyntaxException("No trans for state: " + line);
					} else {
						String nextLine = in.nextLine();
						if(!(nextLine.length() >=5 && nextLine.substring(0, 5).equals("trans"))) {
							throw new SyntaxException("No trans for state: " + line);
						}
						transStrings.put(state, nextLine);
					}
				}
				
			}catch(Exception e) {
				throw new SyntaxException("Exception in line: " + line);
			}
		}
		
		findSuccessors();
		
	}
	private void findSuccessors() throws SyntaxException {
		for(State state: transStrings.keySet()) {
			String transText = transStrings.get(state);
			if(transText.length() < 7) {
				continue;
			}
			String transLine = transText.substring(6);
			String [] elements = transLine.split(" ");
			ArrayList<String> validElements = findValidElements(elements);
			for(String element: validElements) {
				int lastIndexOfSlash = element.lastIndexOf("/");
				int sucessorId = 0;
				try {
				sucessorId = Integer.valueOf(element.substring(lastIndexOfSlash + 1));
				} catch (Exception e) {
					throw new SyntaxException("Invalid successor id in line: " + transText);
				}
				for(State sucessor: states) {
					if(sucessor.getId() == sucessorId) {
						state.addSuccessor(sucessor, element.substring(0, lastIndexOfSlash));
						break;
					}
				}
			}
			
		}
		
	}
	private void findMarking(State state, String line) throws SyntaxException {

		if(line.length() < 7) {
			return;
		}
		line = line.substring(6);
		String [] elements = line.split(" ");
		ArrayList<String> validElements = findValidElements(elements);
		for(String element: validElements) {
			int lastIndexOfAsterisk = element.lastIndexOf("*");
			boolean placeHasValueBiggerThanOne = true;
			if(lastIndexOfAsterisk == -1) {
				placeHasValueBiggerThanOne = false;
			} else {
				int lastIndexOfBracket = element.lastIndexOf("}");
				if(lastIndexOfBracket != -1) {
					if(lastIndexOfBracket < lastIndexOfAsterisk) {
						placeHasValueBiggerThanOne = true;
					} else {
						placeHasValueBiggerThanOne = false;
					}
				}
			}
			
			if(!placeHasValueBiggerThanOne) {
				state.addMarking(new Place(element), 1);
				places.add(new Place(element));
			} else {
				String value = element.substring(lastIndexOfAsterisk + 1);
				if(value.equals("w")) {
					state.addMarking(new Place(element.substring(0, lastIndexOfAsterisk)), omega);
				} else {
					state.addMarking(new Place(element.substring(0, lastIndexOfAsterisk)), Integer.valueOf(value));
				}
				places.add(new Place(element.substring(0, lastIndexOfAsterisk)));
			}
		}
	}

	/**
	 * Metoda w podanej linijce wyszukuje elementy znakowania
	 * @param elements Elementy linijki
	 * @return
	 */
	private ArrayList<String> findValidElements(String[] elements) {
		ArrayList<String> validElements = new ArrayList<String>();
		
		for(int i = 0; i < elements.length; i++) {
			if(isLsccElement(elements[i])) {
				continue;
			}
			if(elements[i].indexOf("{") != -1 && elements[i].indexOf("}") == -1 && i < elements.length -1) {
				elements[i+1] = elements[i] + " " + elements[i+1];
			} else {
				validElements.add(elements[i]);
			}
			
		}
		return validElements;
	}

	/**
	 * Metoda sprawdza czy podany element linijki w której zapisane jest markowanie jest elementem początkowym L.scc
	 * @param element
	 * @return
	 */
	private boolean isLsccElement(String element) {
	
		if(element!= null && element.length() >= 5 && element.substring(0, 5).equals("L.scc")) {
			return true;
		}
		return false;
	}
	/**
	 * Ustawienia maxymalnej wartości znakowania - omega
	 * @param omega Maxymalna wartość znakowania
	 */
	@Override
	public void setOmega(int omega) {
		this.omega = omega;
		
	}


    public static void main(String... args) {
        KTSParser parser = new KTSParser();
        parser.setOmega(50);
        try {
            parser.parseFile("E:\\AGH\\Magisterka\\Praca\\pt_sieci\\bramka_logiczna.kts");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
