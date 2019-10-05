package pl.edu.agh.alvis2modelchecker.exceptions;
/**
 * Błąd syntaktyczny przy próbie parsowania pliku 
 * @author abiernacka, jbiernacki
 *
 */
public class SyntaxException extends Exception {

	private static final long serialVersionUID = 105L;
	public SyntaxException(String message) {
		super(message);
	}
}
