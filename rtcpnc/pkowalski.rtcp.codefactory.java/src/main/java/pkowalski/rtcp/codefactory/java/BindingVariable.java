package pkowalski.rtcp.codefactory.java;

import pkowalski.rtcp.model.*;


@SuppressWarnings({"UnusedDeclaration", "UnusedDeclaration"})
public class BindingVariable {
    private final String _colorname;

    public String getColorname() {
        return _colorname;
    }

    private final String _name;

    public String getName() {
        return _name;
    }

    public String getCcname(){
        return _name.substring(0,1).toUpperCase() + _name.substring(1);
    }






    public BindingVariable(Variable variable){
        // Add your code here:
        super();
        Color color = variable.getColor();
        _colorname = color.getName();


        
        _name = variable.getName();

    }

}
