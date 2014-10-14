// $ANTLR 3.0.1 Declarations.g 2010-11-13 15:38:53

package pkowalski.rtcp.model.syntax;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class DeclarationsParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COLOR", "INT", "WITH", "BOOL", "PRODUCT", "VAR", "IF", "THEN", "ELSE", "AND", "OR", "NOT", "INTCOLOR", "BOOLCOLOR", "ENUMCOLOR", "ALIASCOLOR", "PRODUCTCOLOR", "VARLIST", "TYPENAME", "IDENT", "DIGIT", "NUMBER", "NEG", "WS", "':'", "';'", "','", "'='", "'('", "')'", "'..'", "'|'", "'*'"
    };
    public static final int TYPENAME=22;
    public static final int BOOLCOLOR=17;
    public static final int ALIASCOLOR=19;
    public static final int ELSE=12;
    public static final int NUMBER=25;
    public static final int BOOL=7;
    public static final int INT=5;
    public static final int ENUMCOLOR=18;
    public static final int NOT=15;
    public static final int AND=13;
    public static final int EOF=-1;
    public static final int INTCOLOR=16;
    public static final int COLOR=4;
    public static final int IF=10;
    public static final int PRODUCTCOLOR=20;
    public static final int WS=27;
    public static final int THEN=11;
    public static final int PRODUCT=8;
    public static final int NEG=26;
    public static final int OR=14;
    public static final int VARLIST=21;
    public static final int IDENT=23;
    public static final int VAR=9;
    public static final int DIGIT=24;
    public static final int WITH=6;

        public DeclarationsParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "Declarations.g"; }


    public static class parse_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start parse
    // Declarations.g:38:1: parse : ( colordef | vardef )* ;
    public final parse_return parse() throws RecognitionException {
        parse_return retval = new parse_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        colordef_return colordef1 = null;

        vardef_return vardef2 = null;



        try {
            // Declarations.g:39:2: ( ( colordef | vardef )* )
            // Declarations.g:39:4: ( colordef | vardef )*
            {
            root_0 = (Object)adaptor.nil();

            // Declarations.g:39:4: ( colordef | vardef )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==COLOR) ) {
                    alt1=1;
                }
                else if ( (LA1_0==VAR) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // Declarations.g:39:5: colordef
            	    {
            	    pushFollow(FOLLOW_colordef_in_parse176);
            	    colordef1=colordef();
            	    _fsp--;

            	    adaptor.addChild(root_0, colordef1.getTree());

            	    }
            	    break;
            	case 2 :
            	    // Declarations.g:39:16: vardef
            	    {
            	    pushFollow(FOLLOW_vardef_in_parse180);
            	    vardef2=vardef();
            	    _fsp--;

            	    adaptor.addChild(root_0, vardef2.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end parse

    public static class vardef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start vardef
    // Declarations.g:50:1: vardef : VAR varslistdef ':' vartype ';' ;
    public final vardef_return vardef() throws RecognitionException {
        vardef_return retval = new vardef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VAR3=null;
        Token char_literal5=null;
        Token char_literal7=null;
        varslistdef_return varslistdef4 = null;

        vartype_return vartype6 = null;


        Object VAR3_tree=null;
        Object char_literal5_tree=null;
        Object char_literal7_tree=null;

        try {
            // Declarations.g:51:2: ( VAR varslistdef ':' vartype ';' )
            // Declarations.g:51:4: VAR varslistdef ':' vartype ';'
            {
            root_0 = (Object)adaptor.nil();

            VAR3=(Token)input.LT(1);
            match(input,VAR,FOLLOW_VAR_in_vardef200); 
            VAR3_tree = (Object)adaptor.create(VAR3);
            root_0 = (Object)adaptor.becomeRoot(VAR3_tree, root_0);

            pushFollow(FOLLOW_varslistdef_in_vardef203);
            varslistdef4=varslistdef();
            _fsp--;

            adaptor.addChild(root_0, varslistdef4.getTree());
            char_literal5=(Token)input.LT(1);
            match(input,28,FOLLOW_28_in_vardef205); 
            pushFollow(FOLLOW_vartype_in_vardef208);
            vartype6=vartype();
            _fsp--;

            adaptor.addChild(root_0, vartype6.getTree());
            char_literal7=(Token)input.LT(1);
            match(input,29,FOLLOW_29_in_vardef210); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end vardef

    public static class vartype_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start vartype
    // Declarations.g:54:1: vartype : TYPENAME ;
    public final vartype_return vartype() throws RecognitionException {
        vartype_return retval = new vartype_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TYPENAME8=null;

        Object TYPENAME8_tree=null;

        try {
            // Declarations.g:54:9: ( TYPENAME )
            // Declarations.g:54:11: TYPENAME
            {
            root_0 = (Object)adaptor.nil();

            TYPENAME8=(Token)input.LT(1);
            match(input,TYPENAME,FOLLOW_TYPENAME_in_vartype222); 
            TYPENAME8_tree = (Object)adaptor.create(TYPENAME8);
            adaptor.addChild(root_0, TYPENAME8_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end vartype

    public static class varslistdef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start varslistdef
    // Declarations.g:57:1: varslistdef : varslist -> ^( VARLIST[\"VARLIST\"] varslist ) ;
    public final varslistdef_return varslistdef() throws RecognitionException {
        varslistdef_return retval = new varslistdef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        varslist_return varslist9 = null;


        RewriteRuleSubtreeStream stream_varslist=new RewriteRuleSubtreeStream(adaptor,"rule varslist");
        try {
            // Declarations.g:58:2: ( varslist -> ^( VARLIST[\"VARLIST\"] varslist ) )
            // Declarations.g:58:4: varslist
            {
            pushFollow(FOLLOW_varslist_in_varslistdef234);
            varslist9=varslist();
            _fsp--;

            stream_varslist.add(varslist9.getTree());

            // AST REWRITE
            // elements: varslist
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 58:13: -> ^( VARLIST[\"VARLIST\"] varslist )
            {
                // Declarations.g:58:16: ^( VARLIST[\"VARLIST\"] varslist )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(adaptor.create(VARLIST, "VARLIST"), root_1);

                adaptor.addChild(root_1, stream_varslist.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end varslistdef

    public static class varslist_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start varslist
    // Declarations.g:62:1: varslist : ( varitem ) ( ',' varitem )* ;
    public final varslist_return varslist() throws RecognitionException {
        varslist_return retval = new varslist_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal11=null;
        varitem_return varitem10 = null;

        varitem_return varitem12 = null;


        Object char_literal11_tree=null;

        try {
            // Declarations.g:63:2: ( ( varitem ) ( ',' varitem )* )
            // Declarations.g:63:4: ( varitem ) ( ',' varitem )*
            {
            root_0 = (Object)adaptor.nil();

            // Declarations.g:63:4: ( varitem )
            // Declarations.g:63:5: varitem
            {
            pushFollow(FOLLOW_varitem_in_varslist258);
            varitem10=varitem();
            _fsp--;

            adaptor.addChild(root_0, varitem10.getTree());

            }

            // Declarations.g:63:14: ( ',' varitem )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==30) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Declarations.g:63:15: ',' varitem
            	    {
            	    char_literal11=(Token)input.LT(1);
            	    match(input,30,FOLLOW_30_in_varslist262); 
            	    pushFollow(FOLLOW_varitem_in_varslist265);
            	    varitem12=varitem();
            	    _fsp--;

            	    adaptor.addChild(root_0, varitem12.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end varslist

    public static class varitem_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start varitem
    // Declarations.g:65:1: varitem : IDENT ;
    public final varitem_return varitem() throws RecognitionException {
        varitem_return retval = new varitem_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT13=null;

        Object IDENT13_tree=null;

        try {
            // Declarations.g:65:9: ( IDENT )
            // Declarations.g:65:11: IDENT
            {
            root_0 = (Object)adaptor.nil();

            IDENT13=(Token)input.LT(1);
            match(input,IDENT,FOLLOW_IDENT_in_varitem277); 
            IDENT13_tree = (Object)adaptor.create(IDENT13);
            adaptor.addChild(root_0, IDENT13_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end varitem

    public static class colordef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start colordef
    // Declarations.g:76:1: colordef : COLOR colorname '=' colorclass ';' ;
    public final colordef_return colordef() throws RecognitionException {
        colordef_return retval = new colordef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLOR14=null;
        Token char_literal16=null;
        Token char_literal18=null;
        colorname_return colorname15 = null;

        colorclass_return colorclass17 = null;


        Object COLOR14_tree=null;
        Object char_literal16_tree=null;
        Object char_literal18_tree=null;

        try {
            // Declarations.g:76:9: ( COLOR colorname '=' colorclass ';' )
            // Declarations.g:77:3: COLOR colorname '=' colorclass ';'
            {
            root_0 = (Object)adaptor.nil();

            COLOR14=(Token)input.LT(1);
            match(input,COLOR,FOLLOW_COLOR_in_colordef352); 
            COLOR14_tree = (Object)adaptor.create(COLOR14);
            root_0 = (Object)adaptor.becomeRoot(COLOR14_tree, root_0);

            pushFollow(FOLLOW_colorname_in_colordef355);
            colorname15=colorname();
            _fsp--;

            adaptor.addChild(root_0, colorname15.getTree());
            char_literal16=(Token)input.LT(1);
            match(input,31,FOLLOW_31_in_colordef357); 
            pushFollow(FOLLOW_colorclass_in_colordef360);
            colorclass17=colorclass();
            _fsp--;

            adaptor.addChild(root_0, colorclass17.getTree());
            char_literal18=(Token)input.LT(1);
            match(input,29,FOLLOW_29_in_colordef362); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end colordef

    public static class colorclass_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start colorclass
    // Declarations.g:80:1: colorclass : ( booldef -> ^( BOOLCOLOR booldef ) | intdef -> ^( INTCOLOR intdef ) | enumdef -> ^( ENUMCOLOR enumdef ) | aliasdef -> ^( ALIASCOLOR aliasdef ) | productdef -> ^( PRODUCTCOLOR productdef ) );
    public final colorclass_return colorclass() throws RecognitionException {
        colorclass_return retval = new colorclass_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        booldef_return booldef19 = null;

        intdef_return intdef20 = null;

        enumdef_return enumdef21 = null;

        aliasdef_return aliasdef22 = null;

        productdef_return productdef23 = null;


        RewriteRuleSubtreeStream stream_aliasdef=new RewriteRuleSubtreeStream(adaptor,"rule aliasdef");
        RewriteRuleSubtreeStream stream_productdef=new RewriteRuleSubtreeStream(adaptor,"rule productdef");
        RewriteRuleSubtreeStream stream_intdef=new RewriteRuleSubtreeStream(adaptor,"rule intdef");
        RewriteRuleSubtreeStream stream_enumdef=new RewriteRuleSubtreeStream(adaptor,"rule enumdef");
        RewriteRuleSubtreeStream stream_booldef=new RewriteRuleSubtreeStream(adaptor,"rule booldef");
        try {
            // Declarations.g:81:2: ( booldef -> ^( BOOLCOLOR booldef ) | intdef -> ^( INTCOLOR intdef ) | enumdef -> ^( ENUMCOLOR enumdef ) | aliasdef -> ^( ALIASCOLOR aliasdef ) | productdef -> ^( PRODUCTCOLOR productdef ) )
            int alt3=5;
            switch ( input.LA(1) ) {
            case BOOL:
                {
                alt3=1;
                }
                break;
            case INT:
                {
                alt3=2;
                }
                break;
            case WITH:
                {
                alt3=3;
                }
                break;
            case TYPENAME:
                {
                alt3=4;
                }
                break;
            case PRODUCT:
                {
                alt3=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("80:1: colorclass : ( booldef -> ^( BOOLCOLOR booldef ) | intdef -> ^( INTCOLOR intdef ) | enumdef -> ^( ENUMCOLOR enumdef ) | aliasdef -> ^( ALIASCOLOR aliasdef ) | productdef -> ^( PRODUCTCOLOR productdef ) );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Declarations.g:82:4: booldef
                    {
                    pushFollow(FOLLOW_booldef_in_colorclass380);
                    booldef19=booldef();
                    _fsp--;

                    stream_booldef.add(booldef19.getTree());

                    // AST REWRITE
                    // elements: booldef
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 82:12: -> ^( BOOLCOLOR booldef )
                    {
                        // Declarations.g:82:15: ^( BOOLCOLOR booldef )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(adaptor.create(BOOLCOLOR, "BOOLCOLOR"), root_1);

                        adaptor.addChild(root_1, stream_booldef.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 2 :
                    // Declarations.g:83:4: intdef
                    {
                    pushFollow(FOLLOW_intdef_in_colorclass393);
                    intdef20=intdef();
                    _fsp--;

                    stream_intdef.add(intdef20.getTree());

                    // AST REWRITE
                    // elements: intdef
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 83:12: -> ^( INTCOLOR intdef )
                    {
                        // Declarations.g:83:15: ^( INTCOLOR intdef )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(adaptor.create(INTCOLOR, "INTCOLOR"), root_1);

                        adaptor.addChild(root_1, stream_intdef.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 3 :
                    // Declarations.g:84:4: enumdef
                    {
                    pushFollow(FOLLOW_enumdef_in_colorclass407);
                    enumdef21=enumdef();
                    _fsp--;

                    stream_enumdef.add(enumdef21.getTree());

                    // AST REWRITE
                    // elements: enumdef
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 84:12: -> ^( ENUMCOLOR enumdef )
                    {
                        // Declarations.g:84:15: ^( ENUMCOLOR enumdef )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(adaptor.create(ENUMCOLOR, "ENUMCOLOR"), root_1);

                        adaptor.addChild(root_1, stream_enumdef.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 4 :
                    // Declarations.g:85:4: aliasdef
                    {
                    pushFollow(FOLLOW_aliasdef_in_colorclass420);
                    aliasdef22=aliasdef();
                    _fsp--;

                    stream_aliasdef.add(aliasdef22.getTree());

                    // AST REWRITE
                    // elements: aliasdef
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 85:13: -> ^( ALIASCOLOR aliasdef )
                    {
                        // Declarations.g:85:16: ^( ALIASCOLOR aliasdef )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(adaptor.create(ALIASCOLOR, "ALIASCOLOR"), root_1);

                        adaptor.addChild(root_1, stream_aliasdef.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 5 :
                    // Declarations.g:86:4: productdef
                    {
                    pushFollow(FOLLOW_productdef_in_colorclass433);
                    productdef23=productdef();
                    _fsp--;

                    stream_productdef.add(productdef23.getTree());

                    // AST REWRITE
                    // elements: productdef
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 86:15: -> ^( PRODUCTCOLOR productdef )
                    {
                        // Declarations.g:86:18: ^( PRODUCTCOLOR productdef )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(adaptor.create(PRODUCTCOLOR, "PRODUCTCOLOR"), root_1);

                        adaptor.addChild(root_1, stream_productdef.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end colorclass

    public static class colorname_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start colorname
    // Declarations.g:89:1: colorname : TYPENAME ;
    public final colorname_return colorname() throws RecognitionException {
        colorname_return retval = new colorname_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TYPENAME24=null;

        Object TYPENAME24_tree=null;

        try {
            // Declarations.g:90:2: ( TYPENAME )
            // Declarations.g:90:4: TYPENAME
            {
            root_0 = (Object)adaptor.nil();

            TYPENAME24=(Token)input.LT(1);
            match(input,TYPENAME,FOLLOW_TYPENAME_in_colorname453); 
            TYPENAME24_tree = (Object)adaptor.create(TYPENAME24);
            adaptor.addChild(root_0, TYPENAME24_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end colorname

    public static class booldef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start booldef
    // Declarations.g:102:1: booldef : BOOL WITH '(' boolwhenfalse ',' boolwhentrue ')' ;
    public final booldef_return booldef() throws RecognitionException {
        booldef_return retval = new booldef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token BOOL25=null;
        Token WITH26=null;
        Token char_literal27=null;
        Token char_literal29=null;
        Token char_literal31=null;
        boolwhenfalse_return boolwhenfalse28 = null;

        boolwhentrue_return boolwhentrue30 = null;


        Object BOOL25_tree=null;
        Object WITH26_tree=null;
        Object char_literal27_tree=null;
        Object char_literal29_tree=null;
        Object char_literal31_tree=null;

        try {
            // Declarations.g:103:2: ( BOOL WITH '(' boolwhenfalse ',' boolwhentrue ')' )
            // Declarations.g:104:3: BOOL WITH '(' boolwhenfalse ',' boolwhentrue ')'
            {
            root_0 = (Object)adaptor.nil();

            BOOL25=(Token)input.LT(1);
            match(input,BOOL,FOLLOW_BOOL_in_booldef476); 
            WITH26=(Token)input.LT(1);
            match(input,WITH,FOLLOW_WITH_in_booldef479); 
            char_literal27=(Token)input.LT(1);
            match(input,32,FOLLOW_32_in_booldef482); 
            pushFollow(FOLLOW_boolwhenfalse_in_booldef485);
            boolwhenfalse28=boolwhenfalse();
            _fsp--;

            adaptor.addChild(root_0, boolwhenfalse28.getTree());
            char_literal29=(Token)input.LT(1);
            match(input,30,FOLLOW_30_in_booldef487); 
            pushFollow(FOLLOW_boolwhentrue_in_booldef490);
            boolwhentrue30=boolwhentrue();
            _fsp--;

            adaptor.addChild(root_0, boolwhentrue30.getTree());
            char_literal31=(Token)input.LT(1);
            match(input,33,FOLLOW_33_in_booldef492); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end booldef

    public static class boolwhenfalse_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start boolwhenfalse
    // Declarations.g:106:1: boolwhenfalse : IDENT ;
    public final boolwhenfalse_return boolwhenfalse() throws RecognitionException {
        boolwhenfalse_return retval = new boolwhenfalse_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT32=null;

        Object IDENT32_tree=null;

        try {
            // Declarations.g:107:2: ( IDENT )
            // Declarations.g:107:4: IDENT
            {
            root_0 = (Object)adaptor.nil();

            IDENT32=(Token)input.LT(1);
            match(input,IDENT,FOLLOW_IDENT_in_boolwhenfalse503); 
            IDENT32_tree = (Object)adaptor.create(IDENT32);
            adaptor.addChild(root_0, IDENT32_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end boolwhenfalse

    public static class boolwhentrue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start boolwhentrue
    // Declarations.g:109:1: boolwhentrue : IDENT ;
    public final boolwhentrue_return boolwhentrue() throws RecognitionException {
        boolwhentrue_return retval = new boolwhentrue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT33=null;

        Object IDENT33_tree=null;

        try {
            // Declarations.g:110:2: ( IDENT )
            // Declarations.g:110:4: IDENT
            {
            root_0 = (Object)adaptor.nil();

            IDENT33=(Token)input.LT(1);
            match(input,IDENT,FOLLOW_IDENT_in_boolwhentrue513); 
            IDENT33_tree = (Object)adaptor.create(IDENT33);
            adaptor.addChild(root_0, IDENT33_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end boolwhentrue

    public static class intdef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start intdef
    // Declarations.g:113:1: intdef : INT WITH intlow '..' inthigh ;
    public final intdef_return intdef() throws RecognitionException {
        intdef_return retval = new intdef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INT34=null;
        Token WITH35=null;
        Token string_literal37=null;
        intlow_return intlow36 = null;

        inthigh_return inthigh38 = null;


        Object INT34_tree=null;
        Object WITH35_tree=null;
        Object string_literal37_tree=null;

        try {
            // Declarations.g:114:2: ( INT WITH intlow '..' inthigh )
            // Declarations.g:114:4: INT WITH intlow '..' inthigh
            {
            root_0 = (Object)adaptor.nil();

            INT34=(Token)input.LT(1);
            match(input,INT,FOLLOW_INT_in_intdef524); 
            WITH35=(Token)input.LT(1);
            match(input,WITH,FOLLOW_WITH_in_intdef527); 
            pushFollow(FOLLOW_intlow_in_intdef530);
            intlow36=intlow();
            _fsp--;

            adaptor.addChild(root_0, intlow36.getTree());
            string_literal37=(Token)input.LT(1);
            match(input,34,FOLLOW_34_in_intdef532); 
            pushFollow(FOLLOW_inthigh_in_intdef535);
            inthigh38=inthigh();
            _fsp--;

            adaptor.addChild(root_0, inthigh38.getTree());

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end intdef

    public static class intlow_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start intlow
    // Declarations.g:117:1: intlow : NUMBER ;
    public final intlow_return intlow() throws RecognitionException {
        intlow_return retval = new intlow_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER39=null;

        Object NUMBER39_tree=null;

        try {
            // Declarations.g:117:8: ( NUMBER )
            // Declarations.g:117:10: NUMBER
            {
            root_0 = (Object)adaptor.nil();

            NUMBER39=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_intlow546); 
            NUMBER39_tree = (Object)adaptor.create(NUMBER39);
            adaptor.addChild(root_0, NUMBER39_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end intlow

    public static class inthigh_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start inthigh
    // Declarations.g:119:1: inthigh : NUMBER ;
    public final inthigh_return inthigh() throws RecognitionException {
        inthigh_return retval = new inthigh_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER40=null;

        Object NUMBER40_tree=null;

        try {
            // Declarations.g:119:9: ( NUMBER )
            // Declarations.g:119:11: NUMBER
            {
            root_0 = (Object)adaptor.nil();

            NUMBER40=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_inthigh555); 
            NUMBER40_tree = (Object)adaptor.create(NUMBER40);
            adaptor.addChild(root_0, NUMBER40_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end inthigh

    public static class enumdef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start enumdef
    // Declarations.g:122:1: enumdef : WITH enumlist ;
    public final enumdef_return enumdef() throws RecognitionException {
        enumdef_return retval = new enumdef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WITH41=null;
        enumlist_return enumlist42 = null;


        Object WITH41_tree=null;

        try {
            // Declarations.g:123:2: ( WITH enumlist )
            // Declarations.g:123:4: WITH enumlist
            {
            root_0 = (Object)adaptor.nil();

            WITH41=(Token)input.LT(1);
            match(input,WITH,FOLLOW_WITH_in_enumdef568); 
            pushFollow(FOLLOW_enumlist_in_enumdef571);
            enumlist42=enumlist();
            _fsp--;

            adaptor.addChild(root_0, enumlist42.getTree());

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end enumdef

    public static class enumlist_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start enumlist
    // Declarations.g:125:1: enumlist : enumitem ( '|' enumitem )* ;
    public final enumlist_return enumlist() throws RecognitionException {
        enumlist_return retval = new enumlist_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal44=null;
        enumitem_return enumitem43 = null;

        enumitem_return enumitem45 = null;


        Object char_literal44_tree=null;

        try {
            // Declarations.g:125:9: ( enumitem ( '|' enumitem )* )
            // Declarations.g:126:3: enumitem ( '|' enumitem )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_enumitem_in_enumlist581);
            enumitem43=enumitem();
            _fsp--;

            adaptor.addChild(root_0, enumitem43.getTree());
            // Declarations.g:126:12: ( '|' enumitem )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==35) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Declarations.g:126:14: '|' enumitem
            	    {
            	    char_literal44=(Token)input.LT(1);
            	    match(input,35,FOLLOW_35_in_enumlist585); 
            	    pushFollow(FOLLOW_enumitem_in_enumlist588);
            	    enumitem45=enumitem();
            	    _fsp--;

            	    adaptor.addChild(root_0, enumitem45.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end enumlist

    public static class enumitem_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start enumitem
    // Declarations.g:128:1: enumitem : IDENT ;
    public final enumitem_return enumitem() throws RecognitionException {
        enumitem_return retval = new enumitem_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT46=null;

        Object IDENT46_tree=null;

        try {
            // Declarations.g:128:9: ( IDENT )
            // Declarations.g:128:11: IDENT
            {
            root_0 = (Object)adaptor.nil();

            IDENT46=(Token)input.LT(1);
            match(input,IDENT,FOLLOW_IDENT_in_enumitem600); 
            IDENT46_tree = (Object)adaptor.create(IDENT46);
            adaptor.addChild(root_0, IDENT46_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end enumitem

    public static class aliasdef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start aliasdef
    // Declarations.g:131:1: aliasdef : TYPENAME ;
    public final aliasdef_return aliasdef() throws RecognitionException {
        aliasdef_return retval = new aliasdef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TYPENAME47=null;

        Object TYPENAME47_tree=null;

        try {
            // Declarations.g:132:2: ( TYPENAME )
            // Declarations.g:132:4: TYPENAME
            {
            root_0 = (Object)adaptor.nil();

            TYPENAME47=(Token)input.LT(1);
            match(input,TYPENAME,FOLLOW_TYPENAME_in_aliasdef611); 
            TYPENAME47_tree = (Object)adaptor.create(TYPENAME47);
            adaptor.addChild(root_0, TYPENAME47_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end aliasdef

    public static class productdef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start productdef
    // Declarations.g:136:1: productdef : PRODUCT productslist ;
    public final productdef_return productdef() throws RecognitionException {
        productdef_return retval = new productdef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PRODUCT48=null;
        productslist_return productslist49 = null;


        Object PRODUCT48_tree=null;

        try {
            // Declarations.g:137:2: ( PRODUCT productslist )
            // Declarations.g:137:4: PRODUCT productslist
            {
            root_0 = (Object)adaptor.nil();

            PRODUCT48=(Token)input.LT(1);
            match(input,PRODUCT,FOLLOW_PRODUCT_in_productdef624); 
            pushFollow(FOLLOW_productslist_in_productdef627);
            productslist49=productslist();
            _fsp--;

            adaptor.addChild(root_0, productslist49.getTree());

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end productdef

    public static class productslist_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start productslist
    // Declarations.g:140:1: productslist : ( productitem ) ( '*' productitem )+ ;
    public final productslist_return productslist() throws RecognitionException {
        productslist_return retval = new productslist_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal51=null;
        productitem_return productitem50 = null;

        productitem_return productitem52 = null;


        Object char_literal51_tree=null;

        try {
            // Declarations.g:141:2: ( ( productitem ) ( '*' productitem )+ )
            // Declarations.g:141:4: ( productitem ) ( '*' productitem )+
            {
            root_0 = (Object)adaptor.nil();

            // Declarations.g:141:4: ( productitem )
            // Declarations.g:141:5: productitem
            {
            pushFollow(FOLLOW_productitem_in_productslist640);
            productitem50=productitem();
            _fsp--;

            adaptor.addChild(root_0, productitem50.getTree());

            }

            // Declarations.g:141:18: ( '*' productitem )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==36) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Declarations.g:141:20: '*' productitem
            	    {
            	    char_literal51=(Token)input.LT(1);
            	    match(input,36,FOLLOW_36_in_productslist645); 
            	    pushFollow(FOLLOW_productitem_in_productslist648);
            	    productitem52=productitem();
            	    _fsp--;

            	    adaptor.addChild(root_0, productitem52.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end productslist

    public static class productitem_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start productitem
    // Declarations.g:143:1: productitem : TYPENAME ;
    public final productitem_return productitem() throws RecognitionException {
        productitem_return retval = new productitem_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TYPENAME53=null;

        Object TYPENAME53_tree=null;

        try {
            // Declarations.g:144:2: ( TYPENAME )
            // Declarations.g:144:4: TYPENAME
            {
            root_0 = (Object)adaptor.nil();

            TYPENAME53=(Token)input.LT(1);
            match(input,TYPENAME,FOLLOW_TYPENAME_in_productitem661); 
            TYPENAME53_tree = (Object)adaptor.create(TYPENAME53);
            adaptor.addChild(root_0, TYPENAME53_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end productitem


 

    public static final BitSet FOLLOW_colordef_in_parse176 = new BitSet(new long[]{0x0000000000000212L});
    public static final BitSet FOLLOW_vardef_in_parse180 = new BitSet(new long[]{0x0000000000000212L});
    public static final BitSet FOLLOW_VAR_in_vardef200 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_varslistdef_in_vardef203 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_vardef205 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_vartype_in_vardef208 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_vardef210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPENAME_in_vartype222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varslist_in_varslistdef234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varitem_in_varslist258 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_varslist262 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_varitem_in_varslist265 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_IDENT_in_varitem277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_colordef352 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_colorname_in_colordef355 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_colordef357 = new BitSet(new long[]{0x00000000004001E0L});
    public static final BitSet FOLLOW_colorclass_in_colordef360 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_colordef362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booldef_in_colorclass380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intdef_in_colorclass393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumdef_in_colorclass407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aliasdef_in_colorclass420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_productdef_in_colorclass433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPENAME_in_colorname453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_booldef476 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_WITH_in_booldef479 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_booldef482 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_boolwhenfalse_in_booldef485 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_booldef487 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_boolwhentrue_in_booldef490 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_booldef492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_boolwhenfalse503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_boolwhentrue513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_intdef524 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_WITH_in_intdef527 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_intlow_in_intdef530 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_intdef532 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_inthigh_in_intdef535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_intlow546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_inthigh555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WITH_in_enumdef568 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_enumlist_in_enumdef571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumitem_in_enumlist581 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_enumlist585 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_enumitem_in_enumlist588 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_IDENT_in_enumitem600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPENAME_in_aliasdef611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRODUCT_in_productdef624 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_productslist_in_productdef627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_productitem_in_productslist640 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_productslist645 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_productitem_in_productslist648 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_TYPENAME_in_productitem661 = new BitSet(new long[]{0x0000000000000002L});

}