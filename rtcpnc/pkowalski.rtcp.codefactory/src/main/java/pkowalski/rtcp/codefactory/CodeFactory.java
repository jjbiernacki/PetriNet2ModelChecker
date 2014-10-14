package pkowalski.rtcp.codefactory;

import org.antlr.stringtemplate.StringTemplateGroup;
import pkowalski.rtcp.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public abstract class CodeFactory {
    private StringTemplateGroup _codeTemplates;

    protected StringTemplateGroup getCodeTemplates() {
        return _codeTemplates;
    }


    protected CodeFactory() throws FileNotFoundException {
        // Add your code here:
        super();
        _codeTemplates = InitializeTemplates();

    }

    protected abstract StringTemplateGroup InitializeTemplates() throws FileNotFoundException;

    public abstract String GenerateEnum(EnumColor color);

    public abstract String GenerateEnumToken(EnumColor color);

    public abstract String GenerateEnumFileName(EnumColor color);

    public abstract String GenerateTokenFileName(Color color);

    public abstract String GenerateBoolToken(BoolColor color);

    public abstract String GenerateIntToken(IntColor color);

    public abstract String GenerateProductToken(ProductColor color);

    public abstract String GeneratePlace(Place place) throws GenerationException;

    public abstract String GeneratePlaceFileName(Place place);

    public abstract String GenerateTransitionBinding(Transition transition) throws GenerationException;

    public abstract String GenerateTransitionBindingFileName(Transition transition);

    public abstract String GenerateTransition(Transition transition) throws GenerationException;

    public abstract String GenerateTransitionFileName(Transition transition);

    public abstract String GenerateArc(Arc arc) throws GenerationException;

    public abstract String GenerateArcFileName(Arc arc);

    public abstract String GenerateSimulator(List<Place> places, List<Transition> transitions, List<Arc> arcs) throws GenerationException;

    @SuppressWarnings({"SameReturnValue"})
    public abstract String GenerateSimulatorFileName();

    public abstract void PrepareExecutable(String outputDir) throws IOException;
}
