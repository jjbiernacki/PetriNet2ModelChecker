package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    13:34:57
 *
 */
public class ProductColorImpl extends ColorImpl implements ProductColor{

    public ProductColorImpl(){
        // Add your code here:
        super();
        Init();
    }


    private void Init(){
        _scalarColors = new ArrayList<ScalarColor>();
    }


    private List<ScalarColor> _scalarColors;
    @Override
    public List<ScalarColor> getScalarColors() {
        return _scalarColors;
    }

    @Override
    public ProductVariable Parse(String s) throws OutOfRangeException {
        ProductVariableImpl productVariable = new ProductVariableImpl();
        productVariable.setColor(this);

        List<String> idents = new ArrayList<String>();

        for (StringTokenizer tokenizer = new StringTokenizer(s,",",false); tokenizer.hasMoreTokens();) {
            String ident = tokenizer.nextToken().trim();
            idents.add(ident);

        }

        if (idents.size() != _scalarColors.size()){
            throw new OutOfRangeException();
        }

        int identsCount = idents.size();

        Variable var;
        List<ScalarVariable> vars = new ArrayList<ScalarVariable>();

        for(int i=0; i < identsCount;i++){
            var = _scalarColors.get(i).Parse(idents.get(i));
            vars.add((ScalarVariable)var);
        }

        productVariable.setValue(vars);

        return productVariable;
        
    }

    @Override
    public ProductVariable CreateEmpty() {
        ProductVariableImpl productVariable = new ProductVariableImpl();
        productVariable.setColor(this);
        
        return productVariable;
    }

    @SuppressWarnings({"CloneDoesntCallSuperClone"})
    @Override
    public Object clone(){
        ProductColorImpl productColor = new ProductColorImpl();
        productColor._scalarColors = new ArrayList<ScalarColor>();
        productColor._scalarColors.addAll(_scalarColors);
        productColor.setName(this.getName());

        return productColor;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (ScalarColor scalarColor : _scalarColors) {
            if (builder.length() > 0)
                builder.append(" * ");
            builder.append(scalarColor.getName());
        }

        return String.format("[%s = product %s]", getName(), builder.toString());
    }
}
