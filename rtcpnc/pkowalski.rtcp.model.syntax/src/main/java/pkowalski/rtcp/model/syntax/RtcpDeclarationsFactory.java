package pkowalski.rtcp.model.syntax;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import pkowalski.rtcp.model.*;
import pkowalski.rtcp.model.expression.*;
import pkowalski.rtcp.model.impl.MarkingImpl;
import pkowalski.rtcp.model.impl.RtcpNetFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    15:15:02
 *
 */
public class RtcpDeclarationsFactory {
    private HashMap<String, Color> _colorHashMap;
    private HashMap<String, Variable> _variableHashMap;


    public RtcpDeclarationsFactory(RtcpNetFactory rtcpNetFactory) {
        // Add your code here:
        super();
        _rtcpNetFactory = rtcpNetFactory;
        Init();
    }


    private void Init(){
        if (_rtcpNetFactory == null)
            _rtcpNetFactory = new RtcpNetFactory();
        _colorHashMap = new HashMap<String, Color>();
        _variableHashMap = new HashMap<String, Variable>();
    }

    private RtcpNetFactory _rtcpNetFactory;

    public void BuildRtcpDeclarations(RtcpNet rtcp, String declarations) throws RecognitionException, InvalidStructureException {
        System.out.println(">> Reading declaration:");
        DeclarationsParser.parse_return ats = ParseDeclarations(declarations);
        CommonTree tree = (CommonTree) ats.getTree();

        if (tree.getType() != 0){
            CommonTree treeRoot = new CommonTree();
            treeRoot.addChild(tree);
            tree = treeRoot;
        }

        int nodesCount = tree.getChildCount();
        Tree node  ;

        for(int i=0; i < nodesCount; i++){

            node = tree.getChild(i);

            switch (node.getType())
            {
                case DeclarationsParser.COLOR:
                    Color color = BuildColor(node);
                    rtcp.getColors().add(color);
                    _colorHashMap.put(color.getName(), color);
                    System.out.println("  >> Color declaration found: " + color.toString());
                    break;
                case DeclarationsParser.VAR:
                    List<Variable> variables = BuildVariables(node);
                    HashVariables(variables);
                    rtcp.getVariables().addAll(variables);
                    System.out.println("  >> Variables declaration found:");
                    for (Variable variable : variables) {
                        System.out.println("    >> Variable: " + variable.toString());
                    }
                    break;
                default:
                    break;
            }

        }



    }

    void HashVariables(List<Variable> variables){
        for (Variable variable : variables) {
            _variableHashMap.put(variable.getName(), variable);
        }
    }

    List<Variable> BuildVariables(Tree varNode) throws InvalidStructureException {
        ArrayList<Variable> variables = new ArrayList<Variable>();

        Tree varListNode = varNode.getChild(0);
        String colorName = varNode.getChild(1).getText();
        Color color;

        if (_colorHashMap.containsKey(colorName))
             color = _colorHashMap.get(colorName);
        else
            throw new InvalidStructureException(String.format(
                    "Invalid variable definition: color %s not defined",
                    colorName
            ));

        String varName;
        Variable variable;
        int varListCount = varListNode.getChildCount();

        for(int i = 0; i < varListCount; i++){
            varName = varListNode.getChild(i).getText();
            variable = _rtcpNetFactory.BuildVariable(varName, color);
            variables.add(variable);

        }

        return variables;
    }

    Color BuildColor(Tree colorNode){
        String colorName = colorNode.getChild(0).getText();
        Tree colorClassNode = colorNode.getChild(1);
        switch(colorClassNode.getType())
        {
            case DeclarationsParser.INTCOLOR:
                return BuildIntColor(colorName, colorClassNode);
            case DeclarationsParser.ENUMCOLOR:
                return BuildEnumColor(colorName, colorClassNode);
            case DeclarationsParser.ALIASCOLOR:
                return BuildAliasColor(colorName, colorClassNode);
            case DeclarationsParser.PRODUCTCOLOR:
                return BuildProductColor(colorName, colorClassNode);
            case DeclarationsParser.BOOLCOLOR:
                return BuildBoolColor(colorName, colorClassNode);
            default:
                return null;
        }
    }

    IntColor BuildIntColor(String name, Tree intNode) {
        int lowerBound = Integer.parseInt(intNode.getChild(0).getText());
        int upperBound = Integer.parseInt(intNode.getChild(1).getText());
        IntColor intColor;
        intColor = _rtcpNetFactory.BuildIntColor(name, lowerBound, upperBound);

        return intColor;
    }

    EnumColor BuildEnumColor(String name, Tree enumNode) {

        int identsCount = enumNode.getChildCount();
        ArrayList<String> identsList = new ArrayList<String>();
        for(int i=0; i < identsCount; i++){
            identsList.add(enumNode.getChild(i).getText());
        }

        EnumColor enumColor;
        enumColor = _rtcpNetFactory.BuildEnumColor(name, identsList);

        return enumColor;
    }

    Color BuildAliasColor(String name, Tree aliasNode) {
        String aliasedColorName = aliasNode.getChild(0).getText();
        Color aliasedColor = _colorHashMap.get(aliasedColorName);
        Color aliasColor;
        aliasColor = _rtcpNetFactory.BuildAliasColor(name, aliasedColor);

        return aliasColor;
    }

    ProductColor BuildProductColor(String name, Tree productNode) {
        ArrayList<ScalarColor> productColors = new ArrayList<ScalarColor>();
        String colorName;
        ScalarColor color;
        int productsCount = productNode.getChildCount();
        for(int i=0; i< productsCount; i++){
            colorName = productNode.getChild(i).getText();
            color = (ScalarColor) _colorHashMap.get(colorName);
            productColors.add(color);
        }
        ProductColor productColor;
        productColor = _rtcpNetFactory.BuildProductColor(name, productColors);

        return productColor;
    }

    BoolColor BuildBoolColor(String name, Tree boolNode) {
        String falseIdent = boolNode.getChild(0).getText();
        String trueIdent = boolNode.getChild(1).getText();
        BoolColor boolColor;
        boolColor = _rtcpNetFactory.BuildBoolColor(name, falseIdent, trueIdent);

        return boolColor;
    }


    DeclarationsParser.parse_return ParseDeclarations(String declarations) throws RecognitionException{
        ANTLRStringStream inputStream = new ANTLRStringStream(declarations);
        DeclarationsLexer lexer = new DeclarationsLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        DeclarationsParser parser = new DeclarationsParser(tokens);

        DeclarationsParser.parse_return ats  ;

        ats = parser.parse();

        return ats;
    }

    public Marking BuildMarking(String markingS, Color color) throws RecognitionException, InvalidStructureException {
        MultisetParser.parse_return ats = ParseMultiset(markingS);
        Tree rootNode = (Tree)ats.getTree();

        
        int itemsCount = rootNode.getChildCount();
        ArrayList<MarkingItem> markingItems = new ArrayList<MarkingItem>();

        if (rootNode.getType() == MultisetParser.ITEM)
        {
            MarkingItem markingItem = BuildMarkingItem(rootNode, color);
            markingItems.add(markingItem);
        }
        else{
            for(int i = 0; i < itemsCount; i++){
                MarkingItem markingItem = BuildMarkingItem(rootNode.getChild(i), color);
                markingItems.add(markingItem);
            }

        }

        Marking marking;
        marking = _rtcpNetFactory.BuildMarking(markingItems);

        return marking;
    }

    MarkingItem BuildMarkingItem(Tree itemNode, Color color) throws InvalidStructureException {
        int multiplicity;
        Tree itemPartsNode;

        Tree node;
        node = itemNode.getChild(0);
        if (node.getType() == MultisetParser.ITEMPARTS){
            multiplicity = 1;
            itemPartsNode = node;
        }
        else{
            multiplicity = Integer.parseInt(node.getText());
            itemPartsNode = itemNode.getChild(1);
        }

        StringBuilder sb = new StringBuilder();

        int itemPartsCount = itemPartsNode.getChildCount();
        for (int i=0; i < itemPartsCount; i++){
            if (sb.length()> 0)
                sb.append(",");

            sb.append(itemPartsNode.getChild(i).getText());
        }

        Variable variable;
        try {
            variable = color.Parse(sb.toString());
        } catch (OutOfRangeException e) {
            throw new InvalidStructureException(String.format("Unknown value %s for color %s", sb.toString(), color.getName()),e);
        }


        MarkingItem markingItem;
        markingItem = _rtcpNetFactory.BuildMarkingItem(multiplicity, variable);


        return markingItem;
    }

    MultisetParser.parse_return ParseMultiset(String multiset)throws RecognitionException{
        ANTLRStringStream inputStream = new ANTLRStringStream(multiset);
        MultisetLexer lexer = new MultisetLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MultisetParser parser = new MultisetParser(tokens);
        MultisetParser.parse_return ats  ;

        ats = parser.parse();

        return ats;

    }

    public Color GetDefinedColor(String colorName) throws InvalidStructureException{
        if (_colorHashMap.containsKey(colorName)){
            return _colorHashMap.get(colorName);
        }
        else{
            throw new InvalidStructureException("Undefined color: " + colorName);
        }
    }



    ExpressionParser.parse_return ParseExpression(String expression)throws RecognitionException{
        ANTLRStringStream inputStream = new ANTLRStringStream(expression);
        ExpressionLexer lexer = new ExpressionLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        ExpressionParser parser = new ExpressionParser(tokens);
        ExpressionParser.parse_return ats  ;

        ats = parser.parse();

        return ats;
    }

    public GuardExpression BuildGuardExpression(String guard) throws RecognitionException, InvalidStructureException {
        
        GuardExpression guardExpression;

        if (guard.isEmpty()){
            System.out.println("        >> No guard expression, default: true");
            guardExpression = _rtcpNetFactory.BuildGuardExpression(guard);
        }else{
            System.out.println("        >> Parsing guard expression: " + guard);
            Tree ats = (Tree) ParseExpression(guard).getTree();
            ExpressionNode expressionNode = BuildExpressionNode(ats,null);
            _rtcpNetFactory.ValidateExpressionNode(expressionNode);
            guardExpression = _rtcpNetFactory.BuildGuardExpression(guard, expressionNode);

        }


        
        return guardExpression;
    }

    public WeightExpression BuildWeightExpression(String expression, Color expectedColor) throws RecognitionException, InvalidStructureException {
        if (expression == null || expression.isEmpty())
            throw new InvalidStructureException("Weight expression must have a value.");

        Tree ats = (Tree)ParseExpression(expression).getTree();
        System.out.println("        >> Parsing weight expression: " + expression);
        ExpressionNode expressionNode = BuildExpressionNode(ats,expectedColor);
        _rtcpNetFactory.ValidateExpressionNode(expressionNode);


        WeightExpression weightExpression;
        weightExpression = _rtcpNetFactory.BuildWeightExpression(expression, expressionNode);


        return weightExpression;
    }

    Tree CompactTree(Tree tree){

        if (tree.getChildCount() == 0){
            return tree;
        }
        else if (tree.getChildCount() == 1){
            switch(tree.getType()){
                case ExpressionParser.ID:
                case ExpressionParser.NUM:
                //case ExpressionParser.PROD:
                case ExpressionParser.BLOCK:
                    Tree childTree = tree.getChild(0);
                    if (childTree.getType() == ExpressionParser.PROD)
                        return CompactTree(childTree);
                    else{
                        CompactTree compactTree = new CompactTree(tree.getType(), tree.getText());
                        compactTree.addChild(CompactTree(childTree));

                        return compactTree;
                    }
                default:
                    return CompactTree(tree.getChild(0));
            }

        }
        else {
            CompactTree compactTree = new CompactTree(tree.getType(), tree.getText());
            int childCount = tree.getChildCount();
            for(int i = 0; i<childCount; i++){
                compactTree.addChild(CompactTree(tree.getChild(i)));
            }
            return compactTree;
        }

    }

    public TimeExpression BuildTimeExpression(String expression) throws RecognitionException, InvalidStructureException {
        TimeExpression timeExpression;

        if (expression == null || expression.isEmpty()){
            expression = "0";   // Jeżeli wartość wyrażenia czasowego nie jest podana przyjmuje się, że jest równa "0"
            System.out.println("        >> tNo time expression, default: 0");
        }
        else{
            System.out.println("        >> Parsing time expression: " + expression);
        }

        Tree ats = (Tree)ParseExpression(expression).getTree();
        ExpressionNode expressionNode = BuildExpressionNode(ats, null);
        _rtcpNetFactory.ValidateExpressionNode(expressionNode);

        timeExpression = _rtcpNetFactory.BuildTimeExpression(expression, expressionNode);

        return timeExpression;
    }
    

    ExpressionNode BuildExpressionNode(Tree node, Color expectedColor) throws InvalidStructureException {

        node = CompactTree(node);
        NodeType nodeType;
        switch (node.getType()){
            case ExpressionParser.NUM:
                nodeType = NodeType.CONSTANT_INTEGER;
                break;
            case ExpressionParser.ID:
                nodeType = NodeType.VARIABLE;
                break;

            case ExpressionParser.PLUS:
            case ExpressionParser.MINUS:
            case ExpressionParser.MUL:
            case ExpressionParser.DIV:
            case ExpressionParser.MOD:
            case ExpressionParser.EQ:
            case ExpressionParser.NEQ:
            case ExpressionParser.LT:
            case ExpressionParser.LTE:
            case ExpressionParser.GT:
            case ExpressionParser.GTE:
            case ExpressionParser.NOT:
            case ExpressionParser.AND:
            case ExpressionParser.OR:
                nodeType = NodeType.OPERATOR;
                break;
            case ExpressionParser.ADDEXPR:
            case ExpressionParser.ANDEXPR:
            case ExpressionParser.ARITHEXPR:
            case ExpressionParser.BLOCK:
            case ExpressionParser.CMPEXPR:
            case ExpressionParser.CONDEXPR:
            case ExpressionParser.LOGICEXPR:
            case ExpressionParser.MODEXPR:
            case ExpressionParser.MULEXPR:
            case ExpressionParser.OREXPR:
            case ExpressionParser.UNARYEXPR:
            case ExpressionParser.PROD:
                nodeType = NodeType.EXPRESSION;
                break;
            default:
                throw new InvalidStructureException("Unknows AST node type");
        }
        ExpressionNode expressionNode;// = _rtcpNetFactory.BuildExpressionNode(nodeType);

        switch (nodeType) {

            case EXPRESSION:
                ExpressionType expressionType = ExtractExpressionType(node);
                expressionNode = _rtcpNetFactory.BuildExpressionNode(expressionType, expectedColor);

                int subNodesCount = node.getChildCount();
                for(int i = 0; i < subNodesCount; i++){
                    ExpressionNode subExpressionNode  = null;
                    if (expressionType == ExpressionType.PRODUCT){
                        ProductColor productColor = (ProductColor)expectedColor;
                        subExpressionNode = BuildExpressionNode(node.getChild(i), productColor.getScalarColors().get(i));
                    }
                    else{
                        subExpressionNode = BuildExpressionNode(node.getChild(i), expectedColor);
                    }

                    expressionNode.getSubNodes().add(subExpressionNode);
                }

                break;
            case CONSTANT_INTEGER:
                Integer intVal = ExtractIntegerValue(node);
                expressionNode = _rtcpNetFactory.BuildExpressionNode(intVal);
                break;
            case VARIABLE:
                Variable var = ExtractVariableValue(node, expectedColor);
                expressionNode = _rtcpNetFactory.BuildExpressionNode(var);
                break;
            case OPERATOR:
                OperatorType operatorType = ExtractOperatorType(node);
                expressionNode = _rtcpNetFactory.BuildExpressionNode(operatorType);
                break;
            default:
                throw new IllegalStateException("Unknows expression node type");
        }


        return expressionNode;

    }

    private ExpressionType ExtractExpressionType(Tree node) {

        switch(node.getType()){
            case ExpressionParser.ADDEXPR:
                return ExpressionType.ADDEXPR;
            case ExpressionParser.ANDEXPR:
                return ExpressionType.ANDEXPR;
            case ExpressionParser.ARITHEXPR:
                return ExpressionType.ARITHEXPR;
            case ExpressionParser.BLOCK:
                return ExpressionType.BLOCK;
            case ExpressionParser.CMPEXPR:
                return ExpressionType.CMPEXPR;
            case ExpressionParser.CONDEXPR:
                return ExpressionType.CONDEXPR;
            case ExpressionParser.LOGICEXPR:
                return ExpressionType.LOGICEXPR;
            case ExpressionParser.MODEXPR:
                return ExpressionType.MODEXPR;
            case ExpressionParser.MULEXPR:
                return ExpressionType.MULEXPR;
            case ExpressionParser.OREXPR:
                return ExpressionType.OREXPR;
            case ExpressionParser.UNARYEXPR:
                return ExpressionType.UNARYEXPR;
            case ExpressionParser.PROD:
                return ExpressionType.PRODUCT;
            default:
                throw new IllegalArgumentException("Nieoczekiwany typ węzła AST");
        }
    }

    private OperatorType ExtractOperatorType(Tree node) {

        switch(node.getType()){
            case ExpressionParser.PLUS:
                return OperatorType.PLUS;
            case ExpressionParser.MINUS:
                return OperatorType.MINUS;
            case ExpressionParser.MUL:
                return OperatorType.MUL;
            case ExpressionParser.DIV:
                return OperatorType.DIV;
            case ExpressionParser.MOD:
                return OperatorType.MOD;
            case ExpressionParser.EQ:
                return OperatorType.EQ;
            case ExpressionParser.NEQ:
                return OperatorType.NEQ;
            case ExpressionParser.LT:
                return OperatorType.LT;
            case ExpressionParser.LTE:
                return OperatorType.LTE;
            case ExpressionParser.GT:
                return OperatorType.GT;
            case ExpressionParser.GTE:
                return OperatorType.GTE;
            case ExpressionParser.NOT:
                return OperatorType.NOT;
            case ExpressionParser.AND:
                return OperatorType.AND;
            case ExpressionParser.OR:
                return OperatorType.OR;
            default:
                throw new IllegalArgumentException("Nieoczekiwany typ węzła AST");
        }
    }

    private Variable ExtractProductVariableValue(Tree node){
        if (node.getChildCount() < 2){
            throw new IllegalArgumentException("Podwęzeł AST posiada nieoczekiwaną ilość swoich podwęzłów");
        }



        StringBuilder product = new StringBuilder();

        int partsCount = node.getChildCount();
        for(int i=0; i<partsCount; i++){
            if (product.length() > 0)
                product.append(",");
            product.append(node.getChild(i).getText());
        }

        Variable var;
        for (Color color : _colorHashMap.values()) {
            try{
                var = color.Parse(product.toString());
            }catch(OutOfRangeException ex){
                continue;
            }
            catch(NumberFormatException ex){
                continue;
            }

            return var;
        }

        throw new IllegalArgumentException("Nierozpoznany identyfikator: " + product.toString());
        
    }

    private Variable ExtractVariableValue(Tree node, Color expectedColor) {
        if (node.getType() == ExpressionParser.PROD)
            return ExtractProductVariableValue(node);

        if (node.getChildCount() != 1)
            throw new IllegalArgumentException("Podwęzeł AST posiada nieoczekiwaną ilość swoich podwęzłów");


        Tree childNode = node.getChild(0);

        String idName = childNode.getText();

        if (_variableHashMap.containsKey(idName)){
            return _variableHashMap.get(idName);
        }

        Variable var = null;

        if (expectedColor == null){
            for (Color color : _colorHashMap.values()) {
                try{
                    var = color.Parse(idName);
                    break;
                }
                catch(OutOfRangeException ex){}
                catch(NumberFormatException ex){
                }
            }
        }
        else{
            try {
                var = expectedColor.Parse(idName);
            } catch (OutOfRangeException e) {
                throw new IllegalArgumentException("Nierozpoznany identyfikator: " + idName, e);
            }
        }

        if (var == null)
            throw new IllegalArgumentException("Nierozpoznany identyfikator: " + idName);

        return var;

        /*
        for (Color color : _colorHashMap.values()) {
            try{
                var = color.Parse(idName);
            }
            catch(OutOfRangeException ex){
                continue;
            }
            catch(NumberFormatException ex){
                continue;
            }

            return var;
        }
        */

        //throw new IllegalArgumentException("Nierozpoznany identyfikator: " + idName);


    }

    private Integer ExtractIntegerValue(Tree node) {

        if (node.getChildCount() != 1)
            throw new IllegalArgumentException("Podwęzeł AST posiada nieoczekiwaną ilość swoich podwęzłów");

        Tree childNode = node.getChild(0);
        Integer val;
        val = Integer.parseInt(childNode.getText());
        return val;
    }
}
