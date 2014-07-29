package pl.edu.agh.petrinet2nusmv.parser;

import java.io.FileNotFoundException;
import java.util.List;

import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.model.base.Place;
import pl.edu.agh.petrinet2nusmv.model.base.ReachabilityGraph;

/**
 * Interfejs dla parserów pliku do obiektu klasy grafu osiągalności ReachabilityGraph
 * @author abiernacka, jbiernacki
 *
 */
public interface CovGraphParserInterface {

	/**
	 * Ustawienie listy inicjalizacyjnej miejsc
	 * @param places Lista inicjalizacyjna miejsc
	 */
	void setInitPlacesList(final List<Place> places);
	/**
	 * Metoda parsuje podany plik do obiektu klasy grafu osiągalności
	 * @param filepath Ścieżka do parsowanego pliku
	 * @return Graf osiągalności
	 * @throws FileNotFoundException 
	 * @throws SyntaxException
	 */
	ReachabilityGraph parseFile(final String filepath) throws FileNotFoundException, SyntaxException;
	/**
	 * Ustawienia maxymalnej wartości znakowania - omega
	 * @param omega Maxymalna wartość znakowania
	 */
	void setOmega(final int omega);
}
