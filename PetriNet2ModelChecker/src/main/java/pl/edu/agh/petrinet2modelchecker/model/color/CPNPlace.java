package pl.edu.agh.petrinet2modelchecker.model.color;

/**
 * Created with IntelliJ IDEA.
 * User: abiernacka
 * Date: 14.02.14
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
public class CPNPlace implements Comparable<CPNPlace> {

    private String name;
    private Color color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.replace(" ", "_");
        name = name.replace("\n", "_");
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Place " + name + " color=" + color.getName();
    }

    @Override
    public int compareTo(CPNPlace o) {
        return name.compareTo(o.getName());
    }
}
