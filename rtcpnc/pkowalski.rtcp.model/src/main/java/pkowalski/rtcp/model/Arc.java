package pkowalski.rtcp.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-06
 * Time:    12:36:28
 *
 */
@SuppressWarnings({"UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration"})
public interface Arc {
    /*
    public Page getPage();
    public void setPage(Page value);
    */

    public Place getPlace();
    public void setPlace(Place value);

    public Transition getTransition();
    public void setTransition(Transition value);

    public ArcDirection getArcDirection();
    public void setArcDirection(ArcDirection value);

    public WeightExpression getInExpression();
    public void setInExpression(WeightExpression value);

    public WeightExpression getOutExpression();
    public void setOutExpression(WeightExpression value);

    public TimeExpression getInTimeExpression();
    public void setInTimeExpression(TimeExpression value);

    public TimeExpression getOutTimeExpression();
    public void setOutTimeExpression(TimeExpression value);
}
