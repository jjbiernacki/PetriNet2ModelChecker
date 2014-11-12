package pl.edu.agh.petrinet2modelchecker.model.color;

/**
 * Created with IntelliJ IDEA.
 * User: abiernacka
 * Date: 14.02.14
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class Color {
    private String name;
    private ColorType colorType;

    public ColorType getColorType() {
        return colorType;
    }

    public void setColorType(ColorType colorType) {
        this.colorType = colorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
