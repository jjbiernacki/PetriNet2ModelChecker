package pkowalski.rtcp.codefactory.java;

import pkowalski.rtcp.model.BoolColor;
import pkowalski.rtcp.model.EnumColor;
import pkowalski.rtcp.model.IntColor;
import pkowalski.rtcp.model.ScalarColor;



@SuppressWarnings({"UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration", "UnusedDeclaration"})
class ProductValue {
    private String _objecttype;

    public String getObjecttype() {
        return _objecttype;
    }

    private String _simpletype;

    public String getSimpletype() {
        return _simpletype;
    }

    public void setSimpletype(String value) {
        _simpletype = value;
    }

    private final int _number;

    public int getNumber() {
        return _number;
    }

    private final String _colorname;

    public String getColorname() {
        return _colorname;
    }


    public ProductValue(int number, ScalarColor color){
        // Add your code here:
        super();

        _number = number;
        _colorname = color.getName();

        if (color instanceof BoolColor){
            _objecttype = "Boolean";
            _simpletype = "boolean";
        }
        if (color instanceof EnumColor){
            _objecttype = color.getName()+"Enum";
            _simpletype = _objecttype;
        }
        if (color instanceof IntColor){
            _objecttype = "Integer";
            _simpletype = "int";
        }
        
    }

    
}
