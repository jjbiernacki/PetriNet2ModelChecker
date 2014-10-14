package pkowalski.rtcp.model.impl.expression.impl;

import pkowalski.rtcp.model.BoolColor;
import pkowalski.rtcp.model.Color;
import pkowalski.rtcp.model.IntColor;
import pkowalski.rtcp.model.Variable;
import pkowalski.rtcp.model.expression.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-13
 * Time:    20:19:33
 *
 */
public class ExpressionNodeImpl implements ExpressionNode{


    public ExpressionNodeImpl(NodeType nodeType) {
        // Add your code here:
        super();
        _nodeType = nodeType;
        Init();
    }


    private void Init(){
        _expressionResultType = null;
        _color = null;
        _expressionNodes = new ArrayList<ExpressionNode>();
        _expressionType = null;
        _integer = null;
        _operatorType = null;
        _variable = null;
    }

    private List<ExpressionNode> _expressionNodes;

    @Override
    public List<ExpressionNode> getSubNodes() {
        return _expressionNodes;
    }

    private final NodeType _nodeType;

    @Override
    public NodeType getNodeType() {
        return _nodeType;
    }

    private Integer _integer;

    @Override
    public Integer getConstantInteger() {
        return _integer;
    }
    public void setConstantInteger(Integer value){
        _integer = value;
    }

    private OperatorType _operatorType;

    @Override
    public OperatorType getOperatorType() {
        return _operatorType;
    }

    public void setOperatorType(OperatorType value){
        _operatorType = value;
    }

    private Variable _variable;

    @Override
    public Variable getVariable() {
        return _variable;
    }
    public void setVariable(Variable value) {
        _variable = value;
    }

    private Color _color;

    @Override
    public Color getColor() {
        return _color;
    }

    public void setColor(Color value){
        _color = value;
    }

    private ExpressionResultType _expressionResultType;

    @Override
    public ExpressionResultType getExpressionResultType() {
        return _expressionResultType;
    }

    private ExpressionType _expressionType;

    @Override
    public ExpressionType getExpressionType(){
        return _expressionType;
    }
    public void setExpressionType(ExpressionType value){
        _expressionType = value;
    }

    public void Validate(){
        ChildValidation();
        SelfValidation();
    }

    void ChildValidation(){
        for (Object expressionNode : _expressionNodes) {
            ((ExpressionNodeImpl)expressionNode).Validate();
        }
    }


    void SelfValidation(){
        if (_nodeType == null)
            throw new IllegalStateException("Nieokreślony typ węzła");

        switch (_nodeType){
            case CONSTANT_INTEGER:
                IntegerValidation();
                break;
            case OPERATOR:
                OperatorValidation();
                break;
            case VARIABLE:
                VariableValidation();
                break;
            case EXPRESSION:
                ExpressionValidation();
                break;
        }

        if (_expressionResultType == null)
            throw new IllegalStateException("Niepoprawna walidacja: zwracany typ pozostał nieustalony!");
    }

    void VariableValidation() {
        if (_variable == null)
            throw new IllegalStateException("Brak wartości Variable dla węzła typu VARIABLE");
        _color = _variable.getColor();

        if (_color instanceof BoolColor){
            _expressionResultType = ExpressionResultType.BOOLEAN;
        }else if(_color instanceof IntColor){
            _expressionResultType = ExpressionResultType.INTEGER;
        }else{
            _expressionResultType = ExpressionResultType.COLOR;
        }

    }

    void OperatorValidation() {
        if (_operatorType == null)
            throw new IllegalStateException("Brak wartości OperatorType dla węzła typu OPERATOR");
        _expressionResultType = ExpressionResultType.OPERATOR;
    }

    void IntegerValidation() {
        _expressionResultType = ExpressionResultType.INTEGER;
        if (_integer == null)
            throw new IllegalStateException("Brak wartości Integer dla węzła typu CONSTANT_INTEGER");
    }

    void ExpressionValidation() {
        if (_expressionType == null)
            throw new IllegalStateException("Brak wartości ExpressionType dla węzła typu EXPRESSION");

        switch (_expressionType) {
            case BLOCK:
            case UNARYEXPR:
                UnaryExpressionValidation();
                break;
            case MULEXPR:
            case MODEXPR:
            case ADDEXPR:
            case ARITHEXPR:
                ArithExpressionValidation();
                break;
            case CMPEXPR:
                CmpExpressionValidation();
                break;
            case OREXPR:
            case ANDEXPR:
            case LOGICEXPR:
                LogicExpressionValidation();
                break;
            case CONDEXPR:
                CondExpressionValidation();
                break;
            case PRODUCT:
                ProductExpressionValidation();
                break;

        }
        
    }

    private void ProductExpressionValidation() {        
        _expressionResultType = ExpressionResultType.COLOR;
    }

    private void CondExpressionValidation() {

        if (_expressionNodes.size() != 3)
            throw new IllegalStateException("Oczekiwano 3 podwęzłów");
        ExpressionNode conditionNode = _expressionNodes.get(0);
        ExpressionNode nodeTrue =  _expressionNodes.get(1);
        ExpressionNode nodeFalse = _expressionNodes.get(2);

        if (conditionNode.getExpressionResultType() != ExpressionResultType.BOOLEAN)
            throw new IllegalStateException("Oczekiwane wyrażenie zwracające wartość typ BOOLEAN");

        if (!AreComparable(nodeTrue, nodeFalse))
            throw new IllegalStateException("Niezgodne typy podwęzłów");

        
        _expressionResultType = nodeTrue.getExpressionResultType();
        if (_expressionResultType == ExpressionResultType.COLOR){
            _color = nodeTrue.getColor();
        }

    }


    private void LogicExpressionValidation() {

        ExpressionNode childNode;
        switch(_expressionNodes.size()){
            case 0:
                throw new IllegalStateException("Oczekiwano podwęzłów.");
            case 1:
                childNode = _expressionNodes.get(0);
                MakeNodeTransparent(childNode);
                break;
            default:
                LogicBodyExpressionValidation();
                _expressionResultType = ExpressionResultType.BOOLEAN;

        }


    }

    private void LogicBodyExpressionValidation() {

        ExpressionNode childNode;
        for (ExpressionNode expressionNode : _expressionNodes) {
            childNode = expressionNode;
            switch (childNode.getExpressionResultType()) {

                case OPERATOR:
                    break;
                case BOOLEAN:
                    break;
                case COLOR:
                case INTEGER:
                    throw new IllegalStateException("Nieprawidłowy typ podwęzła");
                
            }
        }
    }

    private void CmpExpressionValidation() {

        ExpressionNode childNode;
        switch(_expressionNodes.size()){
            case 1:
                childNode = _expressionNodes.get(0);
                MakeNodeTransparent(childNode);
                break;
            case 3:
                childNode = _expressionNodes.get(0);
                ExpressionNodeImpl operatorNode = (ExpressionNodeImpl) _expressionNodes.get(1);
                ExpressionNodeImpl secondChildNode = (ExpressionNodeImpl)_expressionNodes.get(2);
                if (operatorNode.getNodeType() != NodeType.OPERATOR){
                    throw new IllegalStateException("Nieprawidłowy typ podwęzła! Wymagany OPERATOR");
                }

                CmpBodyExpressionValidation(operatorNode.getOperatorType(), childNode, secondChildNode);
                _expressionResultType = ExpressionResultType.BOOLEAN;
                break;
            default:
                throw new IllegalStateException("Nieprawidłowa liczba pozdwęzłów.");
        }


    }

    private void CmpBodyExpressionValidation(OperatorType operatorType, ExpressionNode firstChildNode, ExpressionNode secondChildNode) {

        switch (operatorType) {

            case EQ:
            case NEQ:
                if (!AreComparable(firstChildNode, secondChildNode))
                    throw new IllegalStateException("Typy wyrażeń nie są porównywalne");
                break;
            case LTE:
            case LT:
            case GTE:
            case GT:
                if (!AreComparable(firstChildNode, secondChildNode))
                    throw new IllegalStateException("Typy wyrażeń nie są porównywalne");
                if (firstChildNode.getExpressionResultType() != ExpressionResultType.INTEGER || secondChildNode.getExpressionResultType() != ExpressionResultType.INTEGER)
                    throw new IllegalStateException("Wyrażenie nie zwracają wartości typu INTEGER");
                break;
            default:
                throw new IllegalStateException("Nieprawidłowy typ operatora.");
        }
    }


    @SuppressWarnings({"BooleanMethodIsAlwaysInverted"})
    boolean AreComparable(ExpressionNode node1, ExpressionNode node2){
        boolean comparable;

        comparable = node1.getExpressionResultType() == node2.getExpressionResultType();
        if (!comparable)
            return false;

        if (node1.getExpressionResultType() == ExpressionResultType.COLOR)
            comparable = node1.getColor().getName().equals(node2.getColor().getName());

        return comparable;
    }

    private void ArithExpressionValidation() {
        ExpressionNode childNode;
        if (_expressionNodes.size() == 1){
            childNode = _expressionNodes.get(0);
            MakeNodeTransparent(childNode);
        }
        else{
            for (ExpressionNode expressionNode : _expressionNodes) {
                childNode = expressionNode;
                switch (childNode.getExpressionResultType()) {

                    case OPERATOR:
                        break;
                    case COLOR:
                    case BOOLEAN:
                        throw new IllegalStateException("Nieprawidłowa wartość zwracana przez podwyrażenia.");
                }
            }

            MakeNodeInteger();
        }

    }

    private void MakeNodeInteger() {
        _expressionResultType = ExpressionResultType.INTEGER;
    }

    private void UnaryExpressionValidation() {

        ExpressionNode childNode;
        switch(_expressionNodes.size()){
            case 1:
                childNode = _expressionNodes.get(0);
                break;
            case 2:
                childNode = _expressionNodes.get(1);
                break;
            default:
                throw new IllegalStateException("Nieprawidłowa liczba podwęzłów dla węzła typu UNARY EXPRESSION. Wymagana liczba podwęzłów: 1 lub 2. Aktualna liczba węzłów: " + _expressionNodes.size());
        }

        MakeNodeTransparent(childNode);
    }

    void MakeNodeTransparent(ExpressionNode childNode) {
        this._color = childNode.getColor();
        this._expressionResultType = childNode.getExpressionResultType();
        this._integer = childNode.getConstantInteger();
        this._operatorType = childNode.getOperatorType();
        this._variable = childNode.getVariable();
    }
}
