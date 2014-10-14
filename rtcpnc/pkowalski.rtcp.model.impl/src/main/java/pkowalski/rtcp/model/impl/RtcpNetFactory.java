package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.*;
import pkowalski.rtcp.model.expression.*;
import pkowalski.rtcp.model.impl.expression.impl.ExpressionNodeImpl;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    14:29:11
 *
 */
public class RtcpNetFactory {


    public RtcpNetFactory(){
        // Add your code here:
        super();

    }




    public RtcpNet BuildRtcpNet(){
        return new RtcpNetImpl();
    }

    public IntColor BuildIntColor(String name, int lowerBound, int upperBound){
        IntColorImpl intColor = new IntColorImpl();
        intColor.setName(name);
        intColor.setLowerBound(lowerBound);
        intColor.setUpperBound(upperBound);

        return intColor;
    }

    public BoolColor BuildBoolColor(String name, String falseIdent, String trueIdent) {
        BoolColorImpl boolColor = new BoolColorImpl();
        boolColor.setName(name);
        boolColor.setFalseIdent(falseIdent);
        boolColor.setTrueIdent(trueIdent);
        return boolColor;
    }

    public EnumColor BuildEnumColor(String name, List<String> enumIdentList) {
        EnumColorImpl enumColor = new EnumColorImpl();
        enumColor.setName(name);
        enumColor.getEnumIdents().addAll(enumIdentList);
        return enumColor;
    }

    public Color BuildAliasColor(String name, Color color) {
        Color newColor = (Color) color.clone();
        newColor.setName(name);

        return newColor;
    }

    public ProductColor BuildProductColor(String name, List<ScalarColor> scalarColors) {
        ProductColorImpl productColor = new ProductColorImpl();
        productColor.setName(name);
        productColor.getScalarColors().addAll(scalarColors);
        return productColor;
    }

    public Variable BuildVariable(String name, Color color) {
        Variable variable = color.CreateEmpty();
        variable.setName(name);
        variable.setColor(color);
        
        return variable;
    }

    public MarkingItem BuildMarkingItem(int multiplicity, Variable variable) {
        MarkingItemImpl markingItem = new MarkingItemImpl();
        markingItem.setMultiplicity(multiplicity);
        markingItem.setVariable(variable);
        return markingItem;
    }

    public Marking BuildMarking(List<MarkingItem> markingItems) {
        MarkingImpl marking = new MarkingImpl();
        marking.getItemsSet().addAll(markingItems);
        return marking;
    }

    public Fusion BuildFusion(String fusionName) {

        FusionImpl fusion = new FusionImpl();
        fusion.setName(fusionName);

        return fusion;
        
    }

    public Place BuildPlace(Place place) {
        PlaceImpl placeImpl = new PlaceImpl();
        placeImpl.setColor(place.getColor());
        placeImpl.setFusion(place.getFusion());
        placeImpl.setMarking(place.getMarking());
        placeImpl.setName(place.getName());
        placeImpl.setPage(place.getPage());
        //placeImpl.setPortType(place.getPortType());
        placeImpl.setTime(place.getTime());
        return placeImpl;
    }

    public Place BuildPlace(String name, Color color, Fusion fusion, Marking marking, Page page, /*PortType portType,*/ Integer time){
        PlaceImpl place = new PlaceImpl();

        place.setColor(color);
        place.setFusion(fusion);
        place.setMarking(marking);
        place.setName(name);
        place.setPage(page);
        //place.setPortType(portType);
        place.setTime(time);




        return place;
    }

    public Page BuildPage(String name, int id){
        PageImpl  page = new PageImpl();

        page.setId(id);
        page.setName(name);
                
        return page;
    }

    public Transition BuildTransition(String name, GuardExpression guardExpression, Integer priority, boolean isSubstituted){
        TransitionImpl transition = new TransitionImpl();
        transition.setName(name);
        transition.setGuardExpression(guardExpression);
        transition.setPriority(priority);
        transition.setSubstituted(isSubstituted);


        
        return transition;
    }

    public ExpressionNode BuildExpressionNode(Integer intVal){
        ExpressionNodeImpl expressionNode = new ExpressionNodeImpl(NodeType.CONSTANT_INTEGER);

        expressionNode.setConstantInteger(intVal);
        return expressionNode;
    }

    public ExpressionNode BuildExpressionNode(Variable variable) {
        ExpressionNodeImpl expressionNode = new ExpressionNodeImpl(NodeType.VARIABLE);
        expressionNode.setVariable(variable);
        return expressionNode;
    }

    public ExpressionNode BuildExpressionNode(OperatorType operatorType) {
        ExpressionNodeImpl expressionNode = new ExpressionNodeImpl(NodeType.OPERATOR);
        expressionNode.setOperatorType(operatorType);

        return expressionNode;
    }

    public ExpressionNode BuildExpressionNode(ExpressionType expressionType, Color expectedColor) {
        ExpressionNodeImpl expressionNode = new ExpressionNodeImpl(NodeType.EXPRESSION);

        expressionNode.setExpressionType(expressionType);
        expressionNode.setColor(expectedColor);


        return expressionNode;
    }

    public GuardExpression BuildGuardExpression(String expressionString) {
        GuardExpressionImpl guardExpression;
        guardExpression = new GuardExpressionImpl(expressionString);

        return guardExpression;
    }

    public GuardExpression BuildGuardExpression(String expressionString, ExpressionNode expressionNode) {

        if (expressionNode.getExpressionResultType() != ExpressionResultType.BOOLEAN)
            throw new IllegalArgumentException("Expression result is not BOOLEAN like type!");
        GuardExpressionImpl guardExpression;
        guardExpression = new GuardExpressionImpl(expressionString, expressionNode);

        return guardExpression;
    }

    public WeightExpression BuildWeightExpression(String expressionString, ExpressionNode expressionNode) {
        WeightExpressionImpl weightExpression;
        weightExpression = new WeightExpressionImpl(expressionString, expressionNode);

        return weightExpression;
    }

    public TimeExpression BuildTimeExpression(String expressionString, ExpressionNode expressionNode) {
        if (expressionNode.getExpressionResultType() != ExpressionResultType.INTEGER)
            throw new IllegalArgumentException("Wynik wyrażenia nie jest wartością typu INTEGER");
        
        TimeExpressionImpl timeExpression;
        timeExpression = new TimeExpressionImpl(expressionString, expressionNode);

        return timeExpression;
    }


    public void ValidateExpressionNode(ExpressionNode expressionNode){
        if (expressionNode instanceof ExpressionNodeImpl){
            ((ExpressionNodeImpl)expressionNode).Validate();
        }
        

    }

    public void BuildArc(/*Page page,*/ Place place, Transition transition, ArcDirection arcDirection, WeightExpression inWeightExpression, WeightExpression outWeightExpression, TimeExpression inTimeExpression, TimeExpression outTimeExpression) {
        ArcImpl arc = new ArcImpl();
        //arc.setPage(page);
        arc.setPlace(place);
        arc.setTransition(transition);
        arc.setArcDirection(arcDirection);
        arc.setInExpression(inWeightExpression);
        arc.setOutExpression(outWeightExpression);
        arc.setInTimeExpression(inTimeExpression);
        arc.setOutTimeExpression(outTimeExpression);

        place.getArcs().add(arc);
        transition.getArcs().add(arc);


        
        
    }

    public Assignment BuildAssignment(Place socket, Place port) {
        AssignmentImpl assignment;
        assignment = new AssignmentImpl(socket, port);

        return assignment;
    }

}
