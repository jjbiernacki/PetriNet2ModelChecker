package pl.edu.agh.petrinet2nusmv.logger;

/**
 * Klasa służy do wyświetlania logów programu
 * @author abiernacka, jbiernacki
 *
 */
public class Logger {

	/**
	 * Zmienna określa czy wyświetlać logi typu debug
	 */
	public static boolean verbose = false;
	
	/**
	 * Wyświtlanie logów typu debug (jeśli verbose = true)
	 * @param text wyświetlany tekst logu
	 */
	public static void d(final String text) {
		if(verbose) {
			System.out.println(text);
		}
	}
	/**
	 * Wyświetlanie logów typu info
	 * @param text Wyświetlany tekst logu
	 */
	public static void i(final String text) {
		System.out.println(text);
	}
}
