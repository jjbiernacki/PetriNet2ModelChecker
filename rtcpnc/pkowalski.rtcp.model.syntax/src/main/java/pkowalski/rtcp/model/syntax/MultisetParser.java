// $ANTLR 3.0.1 Multiset.g 2010-05-09 23:59:16

package pkowalski.rtcp.model.syntax;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

@SuppressWarnings({"ALL"})
public class MultisetParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ITEM", "ITEMPARTS", "NUMBER", "IDENT", "DIGIT", "TYPENAME", "WS", "'+'", "'('", "')'", "','"
    };
    public static final int TYPENAME=9;
    public static final int WS=10;
    public static final int ITEM=4;
    public static final int ITEMPARTS=5;
    public static final int NUMBER=6;
    public static final int IDENT=7;
    public static final int DIGIT=8;
    public static final int EOF=-1;

        public MultisetParser(TokenStream input) {
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
    public String getGrammarFileName() { return "Multiset.g"; }


    public static class parse_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start parse
    // Multiset.g:23:1: parse : ( multisetdef )? EOF ;
    public final parse_return parse() throws RecognitionException {
        parse_return retval = new parse_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF2=null;
        multisetdef_return multisetdef1 = null;


        Object EOF2_tree=null;

        try {
            // Multiset.g:23:7: ( ( multisetdef )? EOF )
            // Multiset.g:24:3: ( multisetdef )? EOF
            {
            root_0 = (Object)adaptor.nil();

            // Multiset.g:24:3: ( multisetdef )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==NUMBER||LA1_0==12) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // Multiset.g:24:3: multisetdef
                    {
                    pushFollow(FOLLOW_multisetdef_in_parse56);
                    multisetdef1=multisetdef();
                    _fsp--;

                    adaptor.addChild(root_0, multisetdef1.getTree());

                    }
                    break;

            }

            EOF2=(Token)input.LT(1);
            match(input,EOF,FOLLOW_EOF_in_parse59); 

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

    public static class multisetdef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start multisetdef
    // Multiset.g:27:1: multisetdef : itemdef ( '+' itemdef )* ;
    public final multisetdef_return multisetdef() throws RecognitionException {
        multisetdef_return retval = new multisetdef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal4=null;
        itemdef_return itemdef3 = null;

        itemdef_return itemdef5 = null;


        Object char_literal4_tree=null;

        try {
            // Multiset.g:28:2: ( itemdef ( '+' itemdef )* )
            // Multiset.g:29:3: itemdef ( '+' itemdef )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_itemdef_in_multisetdef75);
            itemdef3=itemdef();
            _fsp--;

            adaptor.addChild(root_0, itemdef3.getTree());
            // Multiset.g:29:11: ( '+' itemdef )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Multiset.g:29:12: '+' itemdef
            	    {
            	    char_literal4=(Token)input.LT(1);
            	    match(input,11,FOLLOW_11_in_multisetdef78); 
            	    pushFollow(FOLLOW_itemdef_in_multisetdef81);
            	    itemdef5=itemdef();
            	    _fsp--;

            	    adaptor.addChild(root_0, itemdef5.getTree());

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
    // $ANTLR end multisetdef

    public static class itemdef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start itemdef
    // Multiset.g:31:1: itemdef : item -> ^( ITEM item ) ;
    public final itemdef_return itemdef() throws RecognitionException {
        itemdef_return retval = new itemdef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        item_return item6 = null;


        RewriteRuleSubtreeStream stream_item=new RewriteRuleSubtreeStream(adaptor,"rule item");
        try {
            // Multiset.g:31:9: ( item -> ^( ITEM item ) )
            // Multiset.g:32:3: item
            {
            pushFollow(FOLLOW_item_in_itemdef96);
            item6=item();
            _fsp--;

            stream_item.add(item6.getTree());

            // AST REWRITE
            // elements: item
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 32:8: -> ^( ITEM item )
            {
                // Multiset.g:32:11: ^( ITEM item )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(adaptor.create(ITEM, "ITEM"), root_1);

                adaptor.addChild(root_1, stream_item.next());

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
    // $ANTLR end itemdef

    public static class item_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start item
    // Multiset.g:35:1: item : ( NUMBER )* '(' itemvaldef ')' ;
    public final item_return item() throws RecognitionException {
        item_return retval = new item_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NUMBER7=null;
        Token char_literal8=null;
        Token char_literal10=null;
        itemvaldef_return itemvaldef9 = null;


        Object NUMBER7_tree=null;
        Object char_literal8_tree=null;
        Object char_literal10_tree=null;

        try {
            // Multiset.g:35:6: ( ( NUMBER )* '(' itemvaldef ')' )
            // Multiset.g:36:3: ( NUMBER )* '(' itemvaldef ')'
            {
            root_0 = (Object)adaptor.nil();

            // Multiset.g:36:3: ( NUMBER )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==NUMBER) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Multiset.g:36:3: NUMBER
            	    {
            	    NUMBER7=(Token)input.LT(1);
            	    match(input,NUMBER,FOLLOW_NUMBER_in_item117); 
            	    NUMBER7_tree = (Object)adaptor.create(NUMBER7);
            	    adaptor.addChild(root_0, NUMBER7_tree);


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            char_literal8=(Token)input.LT(1);
            match(input,12,FOLLOW_12_in_item120); 
            pushFollow(FOLLOW_itemvaldef_in_item123);
            itemvaldef9=itemvaldef();
            _fsp--;

            adaptor.addChild(root_0, itemvaldef9.getTree());
            char_literal10=(Token)input.LT(1);
            match(input,13,FOLLOW_13_in_item125); 

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
    // $ANTLR end item

    public static class itemvaldef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start itemvaldef
    // Multiset.g:38:1: itemvaldef : itemval -> ^( ITEMPARTS itemval ) ;
    public final itemvaldef_return itemvaldef() throws RecognitionException {
        itemvaldef_return retval = new itemvaldef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        itemval_return itemval11 = null;


        RewriteRuleSubtreeStream stream_itemval=new RewriteRuleSubtreeStream(adaptor,"rule itemval");
        try {
            // Multiset.g:39:2: ( itemval -> ^( ITEMPARTS itemval ) )
            // Multiset.g:40:3: itemval
            {
            pushFollow(FOLLOW_itemval_in_itemvaldef139);
            itemval11=itemval();
            _fsp--;

            stream_itemval.add(itemval11.getTree());

            // AST REWRITE
            // elements: itemval
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 40:11: -> ^( ITEMPARTS itemval )
            {
                // Multiset.g:40:14: ^( ITEMPARTS itemval )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(adaptor.create(ITEMPARTS, "ITEMPARTS"), root_1);

                adaptor.addChild(root_1, stream_itemval.next());

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
    // $ANTLR end itemvaldef

    public static class itemval_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start itemval
    // Multiset.g:43:1: itemval : ident ( ',' ident )* ;
    public final itemval_return itemval() throws RecognitionException {
        itemval_return retval = new itemval_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal13=null;
        ident_return ident12 = null;

        ident_return ident14 = null;


        Object char_literal13_tree=null;

        try {
            // Multiset.g:43:9: ( ident ( ',' ident )* )
            // Multiset.g:44:3: ident ( ',' ident )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_ident_in_itemval161);
            ident12=ident();
            _fsp--;

            adaptor.addChild(root_0, ident12.getTree());
            // Multiset.g:44:9: ( ',' ident )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==14) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Multiset.g:44:10: ',' ident
            	    {
            	    char_literal13=(Token)input.LT(1);
            	    match(input,14,FOLLOW_14_in_itemval164); 
            	    pushFollow(FOLLOW_ident_in_itemval167);
            	    ident14=ident();
            	    _fsp--;

            	    adaptor.addChild(root_0, ident14.getTree());

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
    // $ANTLR end itemval

    public static class ident_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start ident
    // Multiset.g:47:1: ident : ( IDENT | NUMBER ) ;
    public final ident_return ident() throws RecognitionException {
        ident_return retval = new ident_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set15=null;

        Object set15_tree=null;

        try {
            // Multiset.g:47:7: ( ( IDENT | NUMBER ) )
            // Multiset.g:48:3: ( IDENT | NUMBER )
            {
            root_0 = (Object)adaptor.nil();

            set15=(Token)input.LT(1);
            if ( (input.LA(1)>=NUMBER && input.LA(1)<=IDENT) ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set15));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_ident182);    throw mse;
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
    // $ANTLR end ident


 

    public static final BitSet FOLLOW_multisetdef_in_parse56 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse59 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_itemdef_in_multisetdef75 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_11_in_multisetdef78 = new BitSet(new long[]{0x0000000000001040L});
    public static final BitSet FOLLOW_itemdef_in_multisetdef81 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_item_in_itemdef96 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_item117 = new BitSet(new long[]{0x0000000000001040L});
    public static final BitSet FOLLOW_12_in_item120 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_itemvaldef_in_item123 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_item125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_itemval_in_itemvaldef139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ident_in_itemval161 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_itemval164 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_ident_in_itemval167 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_set_in_ident182 = new BitSet(new long[]{0x0000000000000002L});

}