package pkowalski.rtcp.codefactory.java;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import pkowalski.rtcp.codefactory.CodeFactory;
import pkowalski.rtcp.codefactory.GenerationException;
import pkowalski.rtcp.model.*;
import pkowalski.rtcp.model.MarkingItem;
import pkowalski.rtcp.model.expression.ExpressionNode;
import pkowalski.rtcp.model.expression.NodeType;
import pkowalski.rtcp.model.expression.OperatorType;
import pkowalski.utils.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.tools.*;
import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;


public class JavaCodeFactory extends CodeFactory{
    private String _packageName;
    

    String getPackageName() {
        return _packageName;
    }
    public JavaCodeFactory(String packageName) throws FileNotFoundException {
        super();
        _packageName = packageName;
    }

    @Override
    public StringTemplateGroup InitializeTemplates() throws FileNotFoundException {
        String templatesRootDirPath = "templates";
        File templatesRootDir = new File(templatesRootDirPath);
        if (!templatesRootDir.exists()){
            throw new FileNotFoundException(templatesRootDir.getAbsolutePath());
        }

        return new StringTemplateGroup("Java", templatesRootDir.getPath());
    }


    @Override
    public String GenerateEnum(EnumColor color){
        System.out.println("  >> Building enumeration for ENUM typed color: " + color.toString());
        return GenerateEnum(color,"EnumFile");
    }

    @Override
    public String GenerateEnumFileName(EnumColor color){
        return String.format("%sEnum.java", color.getName());
    }


    @Override
    public String GenerateEnumToken(EnumColor color) {
        System.out.println("  >> Building token class for ENUM typed color: " + color.toString());
        return GenerateEnum(color, "EnumTokenFile");
    }

    @Override
    public String GenerateTokenFileName(Color color){
        return String.format("%sToken.java", color.getName());
    }

    @Override
    public String GenerateBoolToken(BoolColor color){
        System.out.println("  >> Building token class for BOOL typed color: " + color.toString());
        StringTemplate template = getCodeTemplates().getInstanceOf("BoolTokenFile");
        template.setAttribute("package_name", getPackageName());
        template.setAttribute("color_name", color.getName());
        template.setAttribute("true_ident", color.getTrueIdent());
        template.setAttribute("false_ident", color.getFalseIdent());

        return template.toString();
    }

    @Override
    public String GenerateIntToken(IntColor color){
        System.out.println("  >> Building token class for INT typed color: " + color.toString());
        StringTemplate template = getCodeTemplates().getInstanceOf("IntTokenFile");
        template.setAttribute("package_name", getPackageName());
        template.setAttribute("color_name", color.getName());
        template.setAttribute("lower_bound", color.getLowerBound());
        template.setAttribute("upper_bound", color.getUpperBound());

        return template.toString();
    }

    @Override
    public String GenerateProductToken(ProductColor color){
        System.out.println("  >> Building token class for PRODUCT typed color: " + color.toString());
        StringTemplate template = getCodeTemplates().getInstanceOf("ProductTokenFile");

        List<ProductValue> productValues = new ArrayList<ProductValue>();
        int valuesCounter = 1;

        for (ScalarColor scalarColor : color.getScalarColors()) {
            productValues.add(new ProductValue(valuesCounter, scalarColor));
            valuesCounter++;
        }



        template.setAttribute("package_name", getPackageName());
        template.setAttribute("color_name", color.getName());
        template.setAttribute("values", productValues);

        
        

        return template.toString();
    }

    @Override
    public String GeneratePlace(Place place) throws GenerationException {
        System.out.println("  >> Building class for place: " + place.getName());
        StringTemplate template = getCodeTemplates().getInstanceOf("PlaceFile");

        template.setAttribute("package_name", getPackageName());
        template.setAttribute("place_name", place.getName());
        template.setAttribute("timestamp",place.getTime());

        List<TokenInit> tokensInit = new ArrayList<TokenInit>();

        for (MarkingItem markingItem : place.getMarking().getItemsSet()) {

            TokenInit tokenInit;
            try{
                tokenInit= new TokenInit(markingItem);
            }
            catch(VariableNotInitialized e){
                throw new GenerationException(String.format(
                        "Unable to build initial marking for place %s",
                        place.getName()
                ),e);
                        
            }


            tokensInit.add(tokenInit);
        }

        if (tokensInit.size() == 0)
            System.out.println("    >> No initial token marking...");
        else{
            for (TokenInit tokenInit : tokensInit) {
                System.out.println(String.format(
                        "    >> Initial token: %s x (%s)%s",
                        tokenInit.getMultiplicity(),
                        tokenInit.getColorname(),
                        tokenInit.isProduct() ?
                                "(" + Utils.JoinStrings(", ", (List<String>)tokenInit.getValue()) + ")" :
                                tokenInit.getValue()
                ));
            }
        }

        System.out.println("    >> Initial timestamp: " + place.getTime());

        template.setAttribute("tokens", tokensInit);


        return template.toString();
    }

    @Override
    public String GeneratePlaceFileName(Place place){
        return place.getName()+"Place.java";
    }

    @Override
    public String GenerateTransitionBinding(Transition transition) throws GenerationException {
        System.out.println("  >> Building binding class for transition: " + transition.getName());
        StringTemplate template = getCodeTemplates().getInstanceOf("BindingFile");
        template.setAttribute("package_name", getPackageName());
        template.setAttribute("transition_name", transition.getName());

        List<Variable> variables = new ArrayList<Variable>();

        if (!transition.getGuardExpression().IsDefault()){
            variables.addAll(transition.getGuardExpression().getVariables());
            System.out.println("    >> Variables found in guard expression: " + variables.size());
        }
        else{
            System.out.println("    >> No guard expression defined.");
        }



        for (Arc arc : transition.getArcs()) {
            if (arc.getArcDirection() == ArcDirection.In || arc.getArcDirection() == ArcDirection.InOut){
                variables.addAll(arc.getInExpression().getVariables());
                variables.addAll(arc.getInTimeExpression().getVariables());
            }
            if (arc.getArcDirection() == ArcDirection.Out || arc.getArcDirection() == ArcDirection.InOut){
                variables.addAll(arc.getOutExpression().getVariables());
                variables.addAll(arc.getOutTimeExpression().getVariables());
            }
        }





        

        List<BindingVariable> bindingVariables = PrepareVariables(variables);

        if (bindingVariables.size() > 0){
            try {
                System.out.println(String.format(
                        "    >> Binding variables: %s",
                        Utils.JoinStrings(
                                ", ",
                                Utils.ConvertAll(bindingVariables, new Converter<BindingVariable, String>() {
                                    @Override
                                    public String Convert(BindingVariable bindingVariable) {
                                        return bindingVariable.getName();
                                    }
                                })
                        )
                ));
            } catch (Exception e) {
                throw new GenerationException(e);
            }
        }
        else{
            System.out.println("    >> No binding variables - binding is trivial.");
        }

        template.setAttribute("variables", bindingVariables);
        template.setAttribute("trivial", bindingVariables.size() == 0);
        

        return template.toString();
    }

    @Override
    public String GenerateTransitionBindingFileName(Transition transition){
        return transition.getName()+"Binding.java";
    }


    List<BindingVariable> PrepareBindingVariables(List<BindingVariable> bindingVariables){
        bindingVariables = Utils.GetDistinct(bindingVariables, new ReadOnlyPropertySelector<BindingVariable, String>(){
            @Override
            public String getPropertyValue(BindingVariable target) {
                return target.getName();
            }
        });

        Collections.sort(bindingVariables, new Comparator<BindingVariable>() {
            @Override
            public int compare(BindingVariable o1, BindingVariable o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return bindingVariables;

    }

    List<BindingVariable> PrepareVariables(List<Variable> variables) throws GenerationException {

        List<BindingVariable> bindingVariables;
        try {
            bindingVariables = Utils.ConvertIf(variables, new Converter<Variable,  BindingVariable>(){
                @Override
                public BindingVariable Convert(Variable variable) {
                    return new BindingVariable(variable);
                }
            }, new Func<Boolean, Variable>(){
                @Override
                public Boolean Invoke(Variable variable) {
                    return variable.getName() != null;
                }
            });
        } catch (Exception e) {
            throw new GenerationException(e);
        }

        return PrepareBindingVariables(bindingVariables);
    }

    @Override
    public String GenerateTransition(Transition transition) throws GenerationException {
        System.out.println("  >> Building class for transition: " + transition.getName());
        StringTemplate template = getCodeTemplates().getInstanceOf("TransitionFile");

        List<Variable> guardVariables = null;
        if (!transition.getGuardExpression().IsDefault())
            guardVariables = transition.getGuardExpression().getVariables();

        List<BindingVariable> guardBindingVariables = null;
        if (guardVariables != null){
            guardBindingVariables = PrepareVariables(guardVariables);
        }

        if ( guardBindingVariables == null || guardBindingVariables.size()==0)
            System.out.println("    >> No guard variables.");
        else{
            try {
                System.out.println(String.format(
                        "    >> Guard variables found: %s",
                        Utils.JoinStrings(
                                ", ",
                                Utils.ConvertAll(guardBindingVariables, new Converter<BindingVariable, String>() {
                                    @Override
                                    public String Convert(BindingVariable bindingVariable) {
                                        return bindingVariable.getName();
                                    }
                                })
                        )
                ));
            } catch (Exception e) {
                throw new GenerationException(e);
            }
        }

        List<Variable> variables = new ArrayList<Variable>();

        for (Arc arc : transition.getArcs()) {
            if (arc.getArcDirection() == ArcDirection.In || arc.getArcDirection() == ArcDirection.InOut){
                variables.addAll(arc.getInExpression().getVariables());
                variables.addAll(arc.getInTimeExpression().getVariables());
            }
            if (arc.getArcDirection() == ArcDirection.Out || arc.getArcDirection() == ArcDirection.InOut){
                variables.addAll(arc.getOutExpression().getVariables());
                variables.addAll(arc.getOutTimeExpression().getVariables());
            }
        }

        List<BindingVariable> bindingVariables;
        try {
            bindingVariables = Utils.ConvertIf(variables, new Converter<Variable, BindingVariable>() {
                @Override
                public BindingVariable Convert(Variable variable) {
                    return new BindingVariable(variable);
                }
            }, new Func<Boolean, Variable>() {
                @Override
                public Boolean Invoke(Variable variable) {
                    return variable.getName() != null;
                }
            });
        } catch (Exception e) {
            throw new GenerationException(e);
        }

        if (guardBindingVariables != null)
            bindingVariables.addAll(guardBindingVariables);

        bindingVariables = PrepareBindingVariables(bindingVariables);

        if (bindingVariables.size() == 0)
            System.out.println("    >> No binding variables found.");
        else{
            try {
                System.out.println(String.format(
                        "    >> Bindings variables found: %s",
                        Utils.JoinStrings(
                                ", ",
                                Utils.ConvertAll(bindingVariables, new Converter<BindingVariable, String>() {
                                    @Override
                                    public String Convert(BindingVariable bindingVariable) {
                                        return bindingVariable.getName();
                                    }
                                })
                        )
                ));
            } catch (Exception e) {
                throw new GenerationException(e);
            }
        }

        template.setAttribute("package_name", getPackageName());
        template.setAttribute("transition_name", transition.getName());
        if (bindingVariables.size() > 0)
            template.setAttribute("variables", bindingVariables);
        if (guardBindingVariables != null && guardBindingVariables.size() >0)
            template.setAttribute("guard_variables", guardBindingVariables);

        if (!transition.getGuardExpression().IsDefault()) {
            System.out.println("    >> Building guard expression:");
            String guardExpressionString = BuildGuardExpression(transition.getGuardExpression());
            System.out.println("      >> Input expression: " + transition.getGuardExpression().toString());
            System.out.println("      >> Expression built: " + guardExpressionString);
            template.setAttribute("guard_expression", guardExpressionString); 
        }
        else
            System.out.println("    >> No guard expression defined.");
        System.out.println("    >> Transition priority: " + transition.getPriority());
        template.setAttribute("priority", transition.getPriority());

        return template.toString();
    }

    @Override
    public String GenerateTransitionFileName(Transition transition){
        return transition.getName() + "Transition.java";
    }

    @Override
    public String GenerateArc(Arc arc) throws GenerationException {
        System.out.println(String.format(
                "  >> Building class for arc between %s place and %s transition...",
                arc.getPlace().getName(),
                arc.getTransition().getName()
        ));
        StringTemplate template = getCodeTemplates().getInstanceOf("ArcFile");

        template.setAttribute("package_name", getPackageName());
        template.setAttribute("place_name", arc.getPlace().getName());
        template.setAttribute("transition_name", arc.getTransition().getName());
        template.setAttribute("inarc", arc.getArcDirection() == ArcDirection.In || arc.getArcDirection() == ArcDirection.InOut);
        template.setAttribute("outarc", arc.getArcDirection() == ArcDirection.Out || arc.getArcDirection() == ArcDirection.InOut);

        template.setAttribute("place_color_name", arc.getPlace().getColor().getName());

        template.setAttribute("product", arc.getPlace().getColor() instanceof ProductColor);

                

        if (arc.getArcDirection() == ArcDirection.In || arc.getArcDirection() == ArcDirection.InOut){
            List<BindingVariable> inTokenVariables;
            List<BindingVariable> inTimeVariables;


            String inTokenExpressionString;
            String inTimeExpressionString;
            try {
                System.out.println("    >> Building IN weight expression:");
                inTokenExpressionString = BuildExpression(arc.getInExpression().getExpressionTree());
                System.out.println("      >> Input expression: " + arc.getInExpression().toString());
                System.out.println("      >> Expression built: " + inTokenExpressionString);

                System.out.println("    >> Building IN time expression:");
                inTimeExpressionString = BuildExpression(arc.getInTimeExpression().getExpressionTree());
                System.out.println("      >> Input expression: " + arc.getInTimeExpression().toString());
                System.out.println("      >> Expression built: " + inTimeExpressionString);

            } catch (VariableNotInitialized variableNotInitialized) {
                throw new GenerationException(variableNotInitialized);
            }


            inTokenVariables = PrepareVariables(arc.getInExpression().getVariables());
            inTimeVariables = PrepareVariables(arc.getInTimeExpression().getVariables());

            template.setAttribute("in_token_variables", inTokenVariables);
            template.setAttribute("in_time_variables", inTimeVariables);



            template.setAttribute("in_token_expression", inTokenExpressionString);
            template.setAttribute("in_time_expression", inTimeExpressionString);

            template.setAttribute("raw_in_expression", String.format("%s@%s", arc.getInExpression(), arc.getInTimeExpression()));




        }

        if (arc.getArcDirection() == ArcDirection.Out || arc.getArcDirection() == ArcDirection.InOut){
            List<BindingVariable> outTokenVariables;
            List<BindingVariable> outTimeVariables;
            String outTokenExpressionString;
            String outTimeExpressionString;
            try {
                System.out.println("    >> Building OUT time expression:");
                outTokenExpressionString = BuildExpression(arc.getOutExpression().getExpressionTree());
                System.out.println("      >> Input expression: " + arc.getOutExpression().toString());
                System.out.println("      >> Expression built: " + outTokenExpressionString);


                outTimeExpressionString = BuildExpression(arc.getOutTimeExpression().getExpressionTree());
                System.out.println("      >> Input expression: " + arc.getOutTimeExpression().toString());
                System.out.println("      >> Expression built: " + outTimeExpressionString);
                
            } catch (VariableNotInitialized variableNotInitialized) {
                throw new GenerationException(variableNotInitialized);
            }


            outTokenVariables = PrepareVariables(arc.getOutExpression().getVariables());
            outTimeVariables = PrepareVariables(arc.getOutTimeExpression().getVariables());

            template.setAttribute("out_token_variables", outTokenVariables);
            template.setAttribute("out_time_variables", outTimeVariables);

            template.setAttribute("out_token_expression", outTokenExpressionString);
            template.setAttribute("out_time_expression", outTimeExpressionString);

            template.setAttribute("raw_out_expression", String.format("%s@%s", arc.getOutExpression(), arc.getOutTimeExpression()));

            
        }




        

        return template.toString();
    }

    @Override
    public String GenerateArcFileName(Arc arc){
        return String.format("%sTo%sArc.java", arc.getPlace().getName(), arc.getTransition().getName());
    }

    @Override
    public String GenerateSimulator(List<Place> places, List<Transition> transitions, List<Arc> arcs) throws GenerationException {
        final StringTemplate template = getCodeTemplates().getInstanceOf("SimulatorFile");

        List<String> placesNames;
        try {
            placesNames = Utils.ConvertAll(places, new Converter<Place, String>() {
                @Override
                public String Convert(Place place) {
                    return place.getName();
                }
            });
        } catch (Exception e) {
            throw new GenerationException(e);
        }

        List<String> transitionsNames;
        try {
            transitionsNames = Utils.ConvertAll(transitions, new Converter<Transition, String>() {
                @Override
                public String Convert(Transition transition) {
                    return transition.getName();
                }
            });
        } catch (Exception e) {
            throw new GenerationException(e);
        }

        template.setAttribute("package_name", getPackageName());
        template.setAttribute("places", placesNames);
        template.setAttribute("transitions", transitionsNames);

        try {
            Utils.ForEach(arcs, new Action<Arc>() {
                @Override
                public void PerformAction(Arc arc) {

                    template.setAttribute("arcs.{place,transition}", arc.getPlace().getName(), arc.getTransition().getName());
                }
            });
        } catch (Exception e) {
            throw new GenerationException(e);
        }

        return template.toString();
    }

    @Override
    public String GenerateSimulatorFileName(){
        return "RtcpNetSimulator.java";
    }

    String BuildGuardExpression(GuardExpression expression) throws GenerationException {
        if (expression.IsDefault())
            return "true";

        ExpressionNode node = expression.getExpressionTree();


        try {
            return BuildExpression(node);
        } catch (VariableNotInitialized variableNotInitialized) {
           throw new GenerationException(variableNotInitialized);
        }
    }

    private String BuildExpression(ExpressionNode node) throws VariableNotInitialized {

        switch (node.getNodeType()){
            case CONSTANT_INTEGER: return node.getConstantInteger().toString();
            case OPERATOR: return GetOperator(node.getOperatorType());
            case VARIABLE: {
                Variable var = node.getVariable();
                if (var.getName()== null){
                    if (var instanceof EnumVariable)
                        return String.format("%sEnum.%s", var.getColor().getName(), var.getValue().toString());
                    else
                        return var.getValue().toString();
                }
                else
                    return String.format("%s.getValue()", var.getName());
            }
            case EXPRESSION: {


                switch (node.getExpressionType()){
                    case ADDEXPR:
                    case ANDEXPR:
                    case ARITHEXPR:
                    case CMPEXPR:
                    case LOGICEXPR:
                    case MODEXPR:
                    case MULEXPR:
                    case OREXPR:
                    case UNARYEXPR:
                    {
                        StringBuilder expression = new StringBuilder();
                        for(int i=0; i<node.getSubNodes().size(); i++)
                        {
                            ExpressionNode subNode = node.getSubNodes().get(i);
                            if (subNode.getNodeType() == NodeType.OPERATOR)
                                expression.append(String.format(" %s ", BuildExpression(subNode)));
                            else
                                expression.append(BuildExpression(subNode));
                        }

                        return expression.toString();
                    }

                    case BLOCK:
                        return String.format("(%s)", BuildExpression(node.getSubNodes().get(0)));
                    case CONDEXPR:
                        return String.format("%s ? %s : %s",
                                BuildExpression(node.getSubNodes().get(0)),
                                BuildExpression(node.getSubNodes().get(1)),
                                BuildExpression(node.getSubNodes().get(2)));
                    case PRODUCT:
                        return BuildProductExpression(node);

                }

            }
            default: throw new NotImplementedException();
        }
    }

    String BuildProductExpression(ExpressionNode productNode) throws VariableNotInitialized{

        List<String> productInitParameters = new ArrayList<String>();
        for (ExpressionNode subNode : productNode.getSubNodes()) {
            productInitParameters.add(BuildExpression(subNode));
        }

        String expression;
        expression = String.format(
                "new %sToken(%s)",
                productNode.getColor().getName(),
                Utils.JoinStrings(", ", productInitParameters)
        );

        return expression;
    }

    String GetOperator(OperatorType operatorType){
        switch (operatorType){
            case AND: return "&&";
            case DIV: return "/";
            case EQ: return "==";
            case GT: return ">";
            case GTE: return ">=";
            case LT: return "<";
            case LTE: return "<=";
            case MINUS: return "-";
            case MOD: return "%";
            case MUL: return "*";
            case NEQ: return "!=";
            case NOT: return "!";
            case OR: return "||";
            case PLUS: return "+";
            default: throw new IllegalArgumentException();
        }
    }

    String GenerateEnum(EnumColor color, String templateName){
        StringTemplate template = getCodeTemplates().getInstanceOf(templateName);

        template.setAttribute("package_name", getPackageName());
        template.setAttribute("color_name",color.getName());
        template.setAttribute("idents", color.getEnumIdents());

        return template.toString();
    }

    private void CompileCode(String outputDir, String classPath) throws IOException {

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        if (javaCompiler == null){
            System.out.println("No java compiler found. Use java from jdk, not from jre!");
            System.exit(1);
        }
        else
        {
            System.out.println("Java compiler found: " + javaCompiler);
        }





        File srcDir = new File(outputDir, "src");
        File classDir = new File(outputDir, "class");






        if (!classDir.exists()){
            if (!classDir.mkdir())
                throw new IOException("Unable to create directory: "+ classDir.getAbsolutePath());
            classDir.deleteOnExit();
        }

        System.out.println("Compilation environment: ");
        System.out.println("  >> Source dir: " + srcDir.getAbsolutePath());
        System.out.println("  >> Classpath: " + classPath);
        System.out.println("  >> Class dir: " + classDir.getAbsolutePath());

        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(diagnosticCollector, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(srcDir.listFiles());
        List<String> options = new ArrayList<String>();
        options.addAll(Arrays.asList("-classpath", classPath));
        options.addAll(Arrays.asList("-d", classDir.getAbsolutePath()));
        options.addAll(Arrays.asList("-sourcepath", srcDir.getAbsolutePath()));
        JavaCompiler.CompilationTask compilationTask = javaCompiler.getTask(null, fileManager, diagnosticCollector, options, null, compilationUnits);
        boolean success = compilationTask.call();

        if (success)
            System.out.println("Source files compiled!");
        else{
            System.out.println("Compilation errors:");
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnosticCollector.getDiagnostics()) {
                System.out.println(diagnostic);

            }
            System.exit(1);
        }


    }

    private String GetClassPath(File libDir, final boolean forManifest) {

        List<File> libJarsList = Arrays.asList(
                libDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".jar");
                    }
                })
        );

        List<String> libJarsPathsList = null;
        try {

            libJarsPathsList = Utils.ConvertAll(libJarsList, new Converter<File, String>() {
                @Override
                public String Convert(File file) throws Exception {
                    if (forManifest)
                        file = new File(file.getParentFile().getName(), file.getName());
                    return file.toString();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String classPath = Utils.JoinStrings(
                forManifest ? " " : File.pathSeparator,
                libJarsPathsList
        );
        return classPath;
    }

    private void JarCode(String outputDir, File manifestFile) throws IOException {

        File classDir = new File(outputDir, "class");
        File outputJar = new File(outputDir, getPackageName()+".jar");
        if (outputJar.exists()){
            if (!outputJar.delete())
                throw new IOException("Unable to delete old jar file: " + outputJar.getAbsolutePath());
        }

        if(!outputJar.createNewFile())
            throw new IOException("Unable to create new jar file: " + outputJar.getAbsolutePath());

        File packageDir = classDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() && pathname.getName().equals(getPackageName());
            }
        })[0];



        File[] classFiles = packageDir.listFiles();

        List<File> files = new ArrayList<File>(Arrays.asList(classFiles));
        files.add(manifestFile);


        FileOutputStream fileOutputStream = new FileOutputStream(outputJar);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        JarOutputStream jarOutputStream = new JarOutputStream(bufferedOutputStream);

        for (File classFile : files) {
            System.out.println("Compressing file: " + classFile.getName());
            String jarEntryPath = String.format("%s/%s", classFile.getParentFile().getName(), classFile.getName());

            FileInputStream fileInputStream = new FileInputStream(classFile);
            int size = (int) classFile.length();
            byte[] buffer = new byte[size];
            int bytesRead = fileInputStream.read(buffer, 0, size);
            if (bytesRead != size)
                throw new IOException(String.format(
                        "Incorrect bytes read count. Read: %s, expected: %s",bytesRead, size
                ));

            JarEntry jarEntry = new JarEntry(jarEntryPath);
            jarOutputStream.putNextEntry(jarEntry);
            jarOutputStream.write(buffer, 0, size);
            jarOutputStream.closeEntry();

            fileInputStream.close();

        }

        jarOutputStream.flush();
        bufferedOutputStream.flush();
        fileOutputStream.flush();

        jarOutputStream.close();
        bufferedOutputStream.close();
        fileOutputStream.close();
    }

    private File GenerateManifest(String outputDir, String classPath) throws IOException {
        StringTemplate template = getCodeTemplates().getInstanceOf("Manifest");
        template.setAttribute("package_name", getPackageName());
        template.setAttribute("classpath", classPath);

        File manifestDir = new File(outputDir, "META-INF");
        if (!manifestDir.exists()){
            if(!manifestDir.mkdir())
                throw new IOException("Unable to create manifest directory: " + manifestDir.getAbsolutePath());
            manifestDir.deleteOnExit();
        }

        File manifestFile = new File(manifestDir, "MANIFEST.MF");
        

        Writer writer = new FileWriter(manifestFile);
        writer.write(template.toString());
        writer.write("\n");
        writer.flush();
        writer.close();

        return manifestFile;

    }

    @Override
    public void PrepareExecutable(String outputDir) throws IOException {
        File libDir = new File(outputDir, "lib");
        if (!libDir.exists())
            if(!libDir.mkdir())
                throw new IOException("Unable to create lib directory: " + libDir.getAbsolutePath());
        CopyRuntimeLib(outputDir);
        
        String classPath = GetClassPath(libDir,false);

        CompileCode(outputDir, classPath);

        classPath = GetClassPath(libDir, true);

        File manifestFile = GenerateManifest(outputDir, classPath);
        JarCode(outputDir, manifestFile);
    }

    private void ExtractLib(String outputDir) throws IOException {
        File libDir = new File(outputDir, "lib");
        if (!libDir.exists())
            if(!libDir.mkdir())
                throw new IOException("Unable to create lib directory: " + libDir.getAbsolutePath());

        List<String> jars = Arrays.asList("pkowalski-rtcp-runtime-1.0.jar", "pkowalski-rtcp-runtime-ui-console-1.0.jar", "pkowalski-rtcp-runtime-coverability-1.0.jar");
        for (String jar : jars) {
            System.out.println("Generating jar: " + jar);
            InputStream jarInputStream = getClass().getResourceAsStream("/" + jar);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(libDir, jar));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int c;
            do{
                c = jarInputStream.read();
                if (c >=0)
                    bufferedOutputStream.write(c);
            }while(c>=0);

            jarInputStream.close();
            bufferedOutputStream.flush();
            fileOutputStream.flush();

            bufferedOutputStream.close();
            fileOutputStream.close();
        }                                                                                     
        
    }

    private void CopyRuntimeLib(String outputDir) throws IOException {
        File libDir = new File(outputDir, "lib");
        if (!libDir.exists())
            if(!libDir.mkdir())
                throw new IOException("Unable to create lib directory: " + libDir.getAbsolutePath());

        File runtimeDir = new File(new File("lib"),"runtime");

        File[] runtimeJars = runtimeDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".jar");
            }
        });

        for (File runtimeJar : runtimeJars) {
            System.out.println(String.format(
                    "Copying jar %s to lib directory: %s",
                    runtimeJar,
                    libDir.getAbsolutePath()
            ));
            Utils.CopyFile(runtimeJar, new File(libDir, runtimeJar.getName()));
        }
        
    }
}
