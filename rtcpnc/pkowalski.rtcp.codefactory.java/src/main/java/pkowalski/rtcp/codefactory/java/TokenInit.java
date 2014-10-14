package pkowalski.rtcp.codefactory.java;

import pkowalski.rtcp.model.*;
import pkowalski.utils.Action;
import pkowalski.utils.Utils;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"UnusedDeclaration"})
class TokenInit {
    private final int _multiplicity;

    public int getMultiplicity() {
        return _multiplicity;
    }

    private final String _colorname;

    public String getColorname() {
        return _colorname;
    }

    private Object _value;

    public Object getValue() {
        return _value;
    }

    private boolean _product;

    public boolean isProduct() {
        return _product;
    }

    public void setProduct(boolean value) {
        _product = value;
    }


    public TokenInit(MarkingItem markingItem) throws VariableNotInitialized {
        // Add your code here:
        super();
        _multiplicity = markingItem.getMultiplicity();
        Color color = markingItem.getVariable().getColor();
        _colorname = color.getName();
        
        if (color instanceof EnumColor){
            _value = String.format("%sEnum.%s",color.getName(), markingItem.getVariable().getValue().toString());
        }else if (color instanceof ProductColor){
            ProductVariable productVariable = (ProductVariable) markingItem.getVariable();
            List<String> productInit = new ArrayList<String>();
            for (ScalarVariable scalarVariable : productVariable.getValue()) {
                if (scalarVariable instanceof EnumVariable)
                    productInit.add(String.format("%sEnum.%s", scalarVariable.getColor().getName(), scalarVariable.getValue()));
                else
                    productInit.add(scalarVariable.getValue().toString());
            }

            _value = productInit;
            _product = true;
        }else{
            _value = markingItem.getVariable().getValue().toString();
        }


    }

}
