package pkowalski.rtcp.runtime.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-04
 * Time:    13:20:54
 */

/**
 * Klasa reprezentuje wiązanie
 * Formalna definicja wiązania: M. Szpyrka: "Modelowanie i analiza systemów wbudowanych z zastosowaniem
 *  RTCP-sieci", str. 44
 * Cytat: "(...), wiązanie przejścia t jest takim podstawieniem wartości (odpowiednich typów) za zmienne ze
 *  zbioru V(t) [V(t) - oznacza zbiór zmiennych występujących w wyrażeniach łuków otaczających przejście t i w jego
 *  zastrzeżeniu - przyp. by PrKo], że wynikiem wartościowania zastrzeżenia przejścia t jest wartość prawda."
 */
@SuppressWarnings({"UnusedDeclaration"})
public abstract class Binding {
    public abstract boolean isTrivial();

    
}
