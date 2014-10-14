// $ANTLR 3.0.1 Declarations.g 2010-11-13 15:38:54

package pkowalski.rtcp.model.syntax;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class DeclarationsLexer extends Lexer {
    public static final int TYPENAME=22;
    public static final int BOOLCOLOR=17;
    public static final int ALIASCOLOR=19;
    public static final int ELSE=12;
    public static final int NUMBER=25;
    public static final int BOOL=7;
    public static final int T29=29;
    public static final int INT=5;
    public static final int ENUMCOLOR=18;
    public static final int T28=28;
    public static final int NOT=15;
    public static final int AND=13;
    public static final int Tokens=37;
    public static final int EOF=-1;
    public static final int INTCOLOR=16;
    public static final int COLOR=4;
    public static final int IF=10;
    public static final int PRODUCTCOLOR=20;
    public static final int WS=27;
    public static final int THEN=11;
    public static final int PRODUCT=8;
    public static final int OR=14;
    public static final int NEG=26;
    public static final int VARLIST=21;
    public static final int IDENT=23;
    public static final int VAR=9;
    public static final int DIGIT=24;
    public static final int T34=34;
    public static final int T33=33;
    public static final int T36=36;
    public static final int T35=35;
    public static final int T30=30;
    public static final int T32=32;
    public static final int WITH=6;
    public static final int T31=31;
    public DeclarationsLexer() {;} 
    public DeclarationsLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "Declarations.g"; }

    // $ANTLR start COLOR
    public final void mCOLOR() throws RecognitionException {
        try {
            int _type = COLOR;
            // Declarations.g:6:7: ( 'color' )
            // Declarations.g:6:9: 'color'
            {
            match("color"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COLOR

    // $ANTLR start INT
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            // Declarations.g:7:5: ( 'int' )
            // Declarations.g:7:7: 'int'
            {
            match("int"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INT

    // $ANTLR start WITH
    public final void mWITH() throws RecognitionException {
        try {
            int _type = WITH;
            // Declarations.g:8:6: ( 'with' )
            // Declarations.g:8:8: 'with'
            {
            match("with"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WITH

    // $ANTLR start BOOL
    public final void mBOOL() throws RecognitionException {
        try {
            int _type = BOOL;
            // Declarations.g:9:6: ( 'bool' )
            // Declarations.g:9:8: 'bool'
            {
            match("bool"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BOOL

    // $ANTLR start PRODUCT
    public final void mPRODUCT() throws RecognitionException {
        try {
            int _type = PRODUCT;
            // Declarations.g:10:9: ( 'product' )
            // Declarations.g:10:11: 'product'
            {
            match("product"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRODUCT

    // $ANTLR start VAR
    public final void mVAR() throws RecognitionException {
        try {
            int _type = VAR;
            // Declarations.g:11:5: ( 'var' )
            // Declarations.g:11:7: 'var'
            {
            match("var"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end VAR

    // $ANTLR start IF
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            // Declarations.g:12:4: ( 'if' )
            // Declarations.g:12:6: 'if'
            {
            match("if"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IF

    // $ANTLR start THEN
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            // Declarations.g:13:6: ( 'then' )
            // Declarations.g:13:8: 'then'
            {
            match("then"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end THEN

    // $ANTLR start ELSE
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            // Declarations.g:14:6: ( 'else' )
            // Declarations.g:14:8: 'else'
            {
            match("else"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELSE

    // $ANTLR start AND
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            // Declarations.g:15:5: ( 'and' )
            // Declarations.g:15:7: 'and'
            {
            match("and"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AND

    // $ANTLR start OR
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            // Declarations.g:16:4: ( 'or' )
            // Declarations.g:16:6: 'or'
            {
            match("or"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OR

    // $ANTLR start NOT
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            // Declarations.g:17:5: ( 'not' )
            // Declarations.g:17:7: 'not'
            {
            match("not"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT

    // $ANTLR start INTCOLOR
    public final void mINTCOLOR() throws RecognitionException {
        try {
            int _type = INTCOLOR;
            // Declarations.g:18:10: ( 'INT' )
            // Declarations.g:18:12: 'INT'
            {
            match("INT"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INTCOLOR

    // $ANTLR start BOOLCOLOR
    public final void mBOOLCOLOR() throws RecognitionException {
        try {
            int _type = BOOLCOLOR;
            // Declarations.g:19:11: ( 'BOOL' )
            // Declarations.g:19:13: 'BOOL'
            {
            match("BOOL"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BOOLCOLOR

    // $ANTLR start ENUMCOLOR
    public final void mENUMCOLOR() throws RecognitionException {
        try {
            int _type = ENUMCOLOR;
            // Declarations.g:20:11: ( 'ENUM' )
            // Declarations.g:20:13: 'ENUM'
            {
            match("ENUM"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ENUMCOLOR

    // $ANTLR start ALIASCOLOR
    public final void mALIASCOLOR() throws RecognitionException {
        try {
            int _type = ALIASCOLOR;
            // Declarations.g:21:12: ( 'ALIAS' )
            // Declarations.g:21:14: 'ALIAS'
            {
            match("ALIAS"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ALIASCOLOR

    // $ANTLR start PRODUCTCOLOR
    public final void mPRODUCTCOLOR() throws RecognitionException {
        try {
            int _type = PRODUCTCOLOR;
            // Declarations.g:22:14: ( 'PRODUCT' )
            // Declarations.g:22:16: 'PRODUCT'
            {
            match("PRODUCT"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRODUCTCOLOR

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // Declarations.g:23:5: ( ':' )
            // Declarations.g:23:7: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // Declarations.g:24:5: ( ';' )
            // Declarations.g:24:7: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public final void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // Declarations.g:25:5: ( ',' )
            // Declarations.g:25:7: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public final void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // Declarations.g:26:5: ( '=' )
            // Declarations.g:26:7: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public final void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // Declarations.g:27:5: ( '(' )
            // Declarations.g:27:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public final void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // Declarations.g:28:5: ( ')' )
            // Declarations.g:28:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public final void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // Declarations.g:29:5: ( '..' )
            // Declarations.g:29:7: '..'
            {
            match(".."); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public final void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // Declarations.g:30:5: ( '|' )
            // Declarations.g:30:7: '|'
            {
            match('|'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public final void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // Declarations.g:31:5: ( '*' )
            // Declarations.g:31:7: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start IDENT
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            // Declarations.g:69:2: ( ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )* )
            // Declarations.g:69:4: ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            {
            // Declarations.g:69:4: ( 'a' .. 'z' )
            // Declarations.g:69:5: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            // Declarations.g:69:14: ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Declarations.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IDENT

    // $ANTLR start TYPENAME
    public final void mTYPENAME() throws RecognitionException {
        try {
            int _type = TYPENAME;
            // Declarations.g:73:2: ( ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )* )
            // Declarations.g:73:4: ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            {
            // Declarations.g:73:4: ( 'A' .. 'Z' )
            // Declarations.g:73:5: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }

            // Declarations.g:73:14: ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Declarations.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TYPENAME

    // $ANTLR start NUMBER
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            // Declarations.g:148:8: ( ( NEG )? ( DIGIT )+ )
            // Declarations.g:148:10: ( NEG )? ( DIGIT )+
            {
            // Declarations.g:148:10: ( NEG )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='-') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // Declarations.g:148:11: NEG
                    {
                    mNEG(); 

                    }
                    break;

            }

            // Declarations.g:148:16: ( DIGIT )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Declarations.g:148:17: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NUMBER

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // Declarations.g:150:4: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // Declarations.g:150:6: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // Declarations.g:150:6: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\t' && LA5_0<='\n')||(LA5_0>='\f' && LA5_0<='\r')||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Declarations.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


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

             channel = HIDDEN; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    // $ANTLR start DIGIT
    public final void mDIGIT() throws RecognitionException {
        try {
            // Declarations.g:152:16: ( '0' .. '9' )
            // Declarations.g:152:18: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end DIGIT

    // $ANTLR start NEG
    public final void mNEG() throws RecognitionException {
        try {
            // Declarations.g:153:14: ( '-' )
            // Declarations.g:153:16: '-'
            {
            match('-'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end NEG

    public void mTokens() throws RecognitionException {
        // Declarations.g:1:8: ( COLOR | INT | WITH | BOOL | PRODUCT | VAR | IF | THEN | ELSE | AND | OR | NOT | INTCOLOR | BOOLCOLOR | ENUMCOLOR | ALIASCOLOR | PRODUCTCOLOR | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | IDENT | TYPENAME | NUMBER | WS )
        int alt6=30;
        switch ( input.LA(1) ) {
        case 'c':
            {
            int LA6_1 = input.LA(2);

            if ( (LA6_1=='o') ) {
                int LA6_30 = input.LA(3);

                if ( (LA6_30=='l') ) {
                    int LA6_47 = input.LA(4);

                    if ( (LA6_47=='o') ) {
                        int LA6_64 = input.LA(5);

                        if ( (LA6_64=='r') ) {
                            int LA6_79 = input.LA(6);

                            if ( ((LA6_79>='0' && LA6_79<='9')||(LA6_79>='A' && LA6_79<='Z')||(LA6_79>='a' && LA6_79<='z')) ) {
                                alt6=27;
                            }
                            else {
                                alt6=1;}
                        }
                        else {
                            alt6=27;}
                    }
                    else {
                        alt6=27;}
                }
                else {
                    alt6=27;}
            }
            else {
                alt6=27;}
            }
            break;
        case 'i':
            {
            switch ( input.LA(2) ) {
            case 'n':
                {
                int LA6_31 = input.LA(3);

                if ( (LA6_31=='t') ) {
                    int LA6_48 = input.LA(4);

                    if ( ((LA6_48>='0' && LA6_48<='9')||(LA6_48>='A' && LA6_48<='Z')||(LA6_48>='a' && LA6_48<='z')) ) {
                        alt6=27;
                    }
                    else {
                        alt6=2;}
                }
                else {
                    alt6=27;}
                }
                break;
            case 'f':
                {
                int LA6_32 = input.LA(3);

                if ( ((LA6_32>='0' && LA6_32<='9')||(LA6_32>='A' && LA6_32<='Z')||(LA6_32>='a' && LA6_32<='z')) ) {
                    alt6=27;
                }
                else {
                    alt6=7;}
                }
                break;
            default:
                alt6=27;}

            }
            break;
        case 'w':
            {
            int LA6_3 = input.LA(2);

            if ( (LA6_3=='i') ) {
                int LA6_33 = input.LA(3);

                if ( (LA6_33=='t') ) {
                    int LA6_50 = input.LA(4);

                    if ( (LA6_50=='h') ) {
                        int LA6_66 = input.LA(5);

                        if ( ((LA6_66>='0' && LA6_66<='9')||(LA6_66>='A' && LA6_66<='Z')||(LA6_66>='a' && LA6_66<='z')) ) {
                            alt6=27;
                        }
                        else {
                            alt6=3;}
                    }
                    else {
                        alt6=27;}
                }
                else {
                    alt6=27;}
            }
            else {
                alt6=27;}
            }
            break;
        case 'b':
            {
            int LA6_4 = input.LA(2);

            if ( (LA6_4=='o') ) {
                int LA6_34 = input.LA(3);

                if ( (LA6_34=='o') ) {
                    int LA6_51 = input.LA(4);

                    if ( (LA6_51=='l') ) {
                        int LA6_67 = input.LA(5);

                        if ( ((LA6_67>='0' && LA6_67<='9')||(LA6_67>='A' && LA6_67<='Z')||(LA6_67>='a' && LA6_67<='z')) ) {
                            alt6=27;
                        }
                        else {
                            alt6=4;}
                    }
                    else {
                        alt6=27;}
                }
                else {
                    alt6=27;}
            }
            else {
                alt6=27;}
            }
            break;
        case 'p':
            {
            int LA6_5 = input.LA(2);

            if ( (LA6_5=='r') ) {
                int LA6_35 = input.LA(3);

                if ( (LA6_35=='o') ) {
                    int LA6_52 = input.LA(4);

                    if ( (LA6_52=='d') ) {
                        int LA6_68 = input.LA(5);

                        if ( (LA6_68=='u') ) {
                            int LA6_82 = input.LA(6);

                            if ( (LA6_82=='c') ) {
                                int LA6_90 = input.LA(7);

                                if ( (LA6_90=='t') ) {
                                    int LA6_93 = input.LA(8);

                                    if ( ((LA6_93>='0' && LA6_93<='9')||(LA6_93>='A' && LA6_93<='Z')||(LA6_93>='a' && LA6_93<='z')) ) {
                                        alt6=27;
                                    }
                                    else {
                                        alt6=5;}
                                }
                                else {
                                    alt6=27;}
                            }
                            else {
                                alt6=27;}
                        }
                        else {
                            alt6=27;}
                    }
                    else {
                        alt6=27;}
                }
                else {
                    alt6=27;}
            }
            else {
                alt6=27;}
            }
            break;
        case 'v':
            {
            int LA6_6 = input.LA(2);

            if ( (LA6_6=='a') ) {
                int LA6_36 = input.LA(3);

                if ( (LA6_36=='r') ) {
                    int LA6_53 = input.LA(4);

                    if ( ((LA6_53>='0' && LA6_53<='9')||(LA6_53>='A' && LA6_53<='Z')||(LA6_53>='a' && LA6_53<='z')) ) {
                        alt6=27;
                    }
                    else {
                        alt6=6;}
                }
                else {
                    alt6=27;}
            }
            else {
                alt6=27;}
            }
            break;
        case 't':
            {
            int LA6_7 = input.LA(2);

            if ( (LA6_7=='h') ) {
                int LA6_37 = input.LA(3);

                if ( (LA6_37=='e') ) {
                    int LA6_54 = input.LA(4);

                    if ( (LA6_54=='n') ) {
                        int LA6_70 = input.LA(5);

                        if ( ((LA6_70>='0' && LA6_70<='9')||(LA6_70>='A' && LA6_70<='Z')||(LA6_70>='a' && LA6_70<='z')) ) {
                            alt6=27;
                        }
                        else {
                            alt6=8;}
                    }
                    else {
                        alt6=27;}
                }
                else {
                    alt6=27;}
            }
            else {
                alt6=27;}
            }
            break;
        case 'e':
            {
            int LA6_8 = input.LA(2);

            if ( (LA6_8=='l') ) {
                int LA6_38 = input.LA(3);

                if ( (LA6_38=='s') ) {
                    int LA6_55 = input.LA(4);

                    if ( (LA6_55=='e') ) {
                        int LA6_71 = input.LA(5);

                        if ( ((LA6_71>='0' && LA6_71<='9')||(LA6_71>='A' && LA6_71<='Z')||(LA6_71>='a' && LA6_71<='z')) ) {
                            alt6=27;
                        }
                        else {
                            alt6=9;}
                    }
                    else {
                        alt6=27;}
                }
                else {
                    alt6=27;}
            }
            else {
                alt6=27;}
            }
            break;
        case 'a':
            {
            int LA6_9 = input.LA(2);

            if ( (LA6_9=='n') ) {
                int LA6_39 = input.LA(3);

                if ( (LA6_39=='d') ) {
                    int LA6_56 = input.LA(4);

                    if ( ((LA6_56>='0' && LA6_56<='9')||(LA6_56>='A' && LA6_56<='Z')||(LA6_56>='a' && LA6_56<='z')) ) {
                        alt6=27;
                    }
                    else {
                        alt6=10;}
                }
                else {
                    alt6=27;}
            }
            else {
                alt6=27;}
            }
            break;
        case 'o':
            {
            int LA6_10 = input.LA(2);

            if ( (LA6_10=='r') ) {
                int LA6_40 = input.LA(3);

                if ( ((LA6_40>='0' && LA6_40<='9')||(LA6_40>='A' && LA6_40<='Z')||(LA6_40>='a' && LA6_40<='z')) ) {
                    alt6=27;
                }
                else {
                    alt6=11;}
            }
            else {
                alt6=27;}
            }
            break;
        case 'n':
            {
            int LA6_11 = input.LA(2);

            if ( (LA6_11=='o') ) {
                int LA6_41 = input.LA(3);

                if ( (LA6_41=='t') ) {
                    int LA6_58 = input.LA(4);

                    if ( ((LA6_58>='0' && LA6_58<='9')||(LA6_58>='A' && LA6_58<='Z')||(LA6_58>='a' && LA6_58<='z')) ) {
                        alt6=27;
                    }
                    else {
                        alt6=12;}
                }
                else {
                    alt6=27;}
            }
            else {
                alt6=27;}
            }
            break;
        case 'I':
            {
            int LA6_12 = input.LA(2);

            if ( (LA6_12=='N') ) {
                int LA6_42 = input.LA(3);

                if ( (LA6_42=='T') ) {
                    int LA6_59 = input.LA(4);

                    if ( ((LA6_59>='0' && LA6_59<='9')||(LA6_59>='A' && LA6_59<='Z')||(LA6_59>='a' && LA6_59<='z')) ) {
                        alt6=28;
                    }
                    else {
                        alt6=13;}
                }
                else {
                    alt6=28;}
            }
            else {
                alt6=28;}
            }
            break;
        case 'B':
            {
            int LA6_13 = input.LA(2);

            if ( (LA6_13=='O') ) {
                int LA6_43 = input.LA(3);

                if ( (LA6_43=='O') ) {
                    int LA6_60 = input.LA(4);

                    if ( (LA6_60=='L') ) {
                        int LA6_75 = input.LA(5);

                        if ( ((LA6_75>='0' && LA6_75<='9')||(LA6_75>='A' && LA6_75<='Z')||(LA6_75>='a' && LA6_75<='z')) ) {
                            alt6=28;
                        }
                        else {
                            alt6=14;}
                    }
                    else {
                        alt6=28;}
                }
                else {
                    alt6=28;}
            }
            else {
                alt6=28;}
            }
            break;
        case 'E':
            {
            int LA6_14 = input.LA(2);

            if ( (LA6_14=='N') ) {
                int LA6_44 = input.LA(3);

                if ( (LA6_44=='U') ) {
                    int LA6_61 = input.LA(4);

                    if ( (LA6_61=='M') ) {
                        int LA6_76 = input.LA(5);

                        if ( ((LA6_76>='0' && LA6_76<='9')||(LA6_76>='A' && LA6_76<='Z')||(LA6_76>='a' && LA6_76<='z')) ) {
                            alt6=28;
                        }
                        else {
                            alt6=15;}
                    }
                    else {
                        alt6=28;}
                }
                else {
                    alt6=28;}
            }
            else {
                alt6=28;}
            }
            break;
        case 'A':
            {
            int LA6_15 = input.LA(2);

            if ( (LA6_15=='L') ) {
                int LA6_45 = input.LA(3);

                if ( (LA6_45=='I') ) {
                    int LA6_62 = input.LA(4);

                    if ( (LA6_62=='A') ) {
                        int LA6_77 = input.LA(5);

                        if ( (LA6_77=='S') ) {
                            int LA6_87 = input.LA(6);

                            if ( ((LA6_87>='0' && LA6_87<='9')||(LA6_87>='A' && LA6_87<='Z')||(LA6_87>='a' && LA6_87<='z')) ) {
                                alt6=28;
                            }
                            else {
                                alt6=16;}
                        }
                        else {
                            alt6=28;}
                    }
                    else {
                        alt6=28;}
                }
                else {
                    alt6=28;}
            }
            else {
                alt6=28;}
            }
            break;
        case 'P':
            {
            int LA6_16 = input.LA(2);

            if ( (LA6_16=='R') ) {
                int LA6_46 = input.LA(3);

                if ( (LA6_46=='O') ) {
                    int LA6_63 = input.LA(4);

                    if ( (LA6_63=='D') ) {
                        int LA6_78 = input.LA(5);

                        if ( (LA6_78=='U') ) {
                            int LA6_88 = input.LA(6);

                            if ( (LA6_88=='C') ) {
                                int LA6_92 = input.LA(7);

                                if ( (LA6_92=='T') ) {
                                    int LA6_94 = input.LA(8);

                                    if ( ((LA6_94>='0' && LA6_94<='9')||(LA6_94>='A' && LA6_94<='Z')||(LA6_94>='a' && LA6_94<='z')) ) {
                                        alt6=28;
                                    }
                                    else {
                                        alt6=17;}
                                }
                                else {
                                    alt6=28;}
                            }
                            else {
                                alt6=28;}
                        }
                        else {
                            alt6=28;}
                    }
                    else {
                        alt6=28;}
                }
                else {
                    alt6=28;}
            }
            else {
                alt6=28;}
            }
            break;
        case ':':
            {
            alt6=18;
            }
            break;
        case ';':
            {
            alt6=19;
            }
            break;
        case ',':
            {
            alt6=20;
            }
            break;
        case '=':
            {
            alt6=21;
            }
            break;
        case '(':
            {
            alt6=22;
            }
            break;
        case ')':
            {
            alt6=23;
            }
            break;
        case '.':
            {
            alt6=24;
            }
            break;
        case '|':
            {
            alt6=25;
            }
            break;
        case '*':
            {
            alt6=26;
            }
            break;
        case 'd':
        case 'f':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'q':
        case 'r':
        case 's':
        case 'u':
        case 'x':
        case 'y':
        case 'z':
            {
            alt6=27;
            }
            break;
        case 'C':
        case 'D':
        case 'F':
        case 'G':
        case 'H':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
            {
            alt6=28;
            }
            break;
        case '-':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt6=29;
            }
            break;
        case '\t':
        case '\n':
        case '\f':
        case '\r':
        case ' ':
            {
            alt6=30;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( COLOR | INT | WITH | BOOL | PRODUCT | VAR | IF | THEN | ELSE | AND | OR | NOT | INTCOLOR | BOOLCOLOR | ENUMCOLOR | ALIASCOLOR | PRODUCTCOLOR | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | IDENT | TYPENAME | NUMBER | WS );", 6, 0, input);

            throw nvae;
        }

        switch (alt6) {
            case 1 :
                // Declarations.g:1:10: COLOR
                {
                mCOLOR(); 

                }
                break;
            case 2 :
                // Declarations.g:1:16: INT
                {
                mINT(); 

                }
                break;
            case 3 :
                // Declarations.g:1:20: WITH
                {
                mWITH(); 

                }
                break;
            case 4 :
                // Declarations.g:1:25: BOOL
                {
                mBOOL(); 

                }
                break;
            case 5 :
                // Declarations.g:1:30: PRODUCT
                {
                mPRODUCT(); 

                }
                break;
            case 6 :
                // Declarations.g:1:38: VAR
                {
                mVAR(); 

                }
                break;
            case 7 :
                // Declarations.g:1:42: IF
                {
                mIF(); 

                }
                break;
            case 8 :
                // Declarations.g:1:45: THEN
                {
                mTHEN(); 

                }
                break;
            case 9 :
                // Declarations.g:1:50: ELSE
                {
                mELSE(); 

                }
                break;
            case 10 :
                // Declarations.g:1:55: AND
                {
                mAND(); 

                }
                break;
            case 11 :
                // Declarations.g:1:59: OR
                {
                mOR(); 

                }
                break;
            case 12 :
                // Declarations.g:1:62: NOT
                {
                mNOT(); 

                }
                break;
            case 13 :
                // Declarations.g:1:66: INTCOLOR
                {
                mINTCOLOR(); 

                }
                break;
            case 14 :
                // Declarations.g:1:75: BOOLCOLOR
                {
                mBOOLCOLOR(); 

                }
                break;
            case 15 :
                // Declarations.g:1:85: ENUMCOLOR
                {
                mENUMCOLOR(); 

                }
                break;
            case 16 :
                // Declarations.g:1:95: ALIASCOLOR
                {
                mALIASCOLOR(); 

                }
                break;
            case 17 :
                // Declarations.g:1:106: PRODUCTCOLOR
                {
                mPRODUCTCOLOR(); 

                }
                break;
            case 18 :
                // Declarations.g:1:119: T28
                {
                mT28(); 

                }
                break;
            case 19 :
                // Declarations.g:1:123: T29
                {
                mT29(); 

                }
                break;
            case 20 :
                // Declarations.g:1:127: T30
                {
                mT30(); 

                }
                break;
            case 21 :
                // Declarations.g:1:131: T31
                {
                mT31(); 

                }
                break;
            case 22 :
                // Declarations.g:1:135: T32
                {
                mT32(); 

                }
                break;
            case 23 :
                // Declarations.g:1:139: T33
                {
                mT33(); 

                }
                break;
            case 24 :
                // Declarations.g:1:143: T34
                {
                mT34(); 

                }
                break;
            case 25 :
                // Declarations.g:1:147: T35
                {
                mT35(); 

                }
                break;
            case 26 :
                // Declarations.g:1:151: T36
                {
                mT36(); 

                }
                break;
            case 27 :
                // Declarations.g:1:155: IDENT
                {
                mIDENT(); 

                }
                break;
            case 28 :
                // Declarations.g:1:161: TYPENAME
                {
                mTYPENAME(); 

                }
                break;
            case 29 :
                // Declarations.g:1:170: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 30 :
                // Declarations.g:1:177: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}