// $ANTLR 3.0.1 Expression.g 2010-05-29 14:12:19

package pkowalski.rtcp.model.syntax;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"ALL"})
public class ExpressionLexer extends Lexer {
    public static final int LT=15;
    public static final int MOD=10;
    public static final int GTE=16;
    public static final int ELSE=6;
    public static final int NUMBER=36;
    public static final int MULEXPR=25;
    public static final int ANDEXPR=31;
    public static final int NOT=18;
    public static final int MODEXPR=26;
    public static final int MINUS=8;
    public static final int OREXPR=30;
    public static final int ID=21;
    public static final int AND=19;
    public static final int Tokens=42;
    public static final int EOF=-1;
    public static final int T41=41;
    public static final int MUL=9;
    public static final int LTE=14;
    public static final int T40=40;
    public static final int UNARYEXPR=24;
    public static final int CMPEXPR=29;
    public static final int NUM=22;
    public static final int IF=4;
    public static final int CONDEXPR=34;
    public static final int NEQ=13;
    public static final int WS=38;
    public static final int THEN=5;
    public static final int LOGICEXPR=32;
    public static final int BLOCK=33;
    public static final int OR=20;
    public static final int PROD=23;
    public static final int GT=17;
    public static final int IDENT=35;
    public static final int PLUS=7;
    public static final int ADDEXPR=27;
    public static final int ARITHEXPR=28;
    public static final int DIGIT=37;
    public static final int T39=39;
    public static final int EQ=12;
    public static final int DIV=11;
    public ExpressionLexer() {;} 
    public ExpressionLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "Expression.g"; }

    // $ANTLR start IF
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            // Expression.g:6:4: ( 'if' )
            // Expression.g:6:6: 'if'
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
            // Expression.g:7:6: ( 'then' )
            // Expression.g:7:8: 'then'
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
            // Expression.g:8:6: ( 'else' )
            // Expression.g:8:8: 'else'
            {
            match("else"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELSE

    // $ANTLR start PLUS
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            // Expression.g:9:6: ( '+' )
            // Expression.g:9:8: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUS

    // $ANTLR start MINUS
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            // Expression.g:10:7: ( '-' )
            // Expression.g:10:9: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MINUS

    // $ANTLR start MUL
    public final void mMUL() throws RecognitionException {
        try {
            int _type = MUL;
            // Expression.g:11:5: ( '*' )
            // Expression.g:11:7: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MUL

    // $ANTLR start MOD
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            // Expression.g:12:5: ( 'mod' )
            // Expression.g:12:7: 'mod'
            {
            match("mod"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MOD

    // $ANTLR start DIV
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            // Expression.g:13:5: ( 'div' )
            // Expression.g:13:7: 'div'
            {
            match("div"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DIV

    // $ANTLR start EQ
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            // Expression.g:14:4: ( '=' )
            // Expression.g:14:6: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQ

    // $ANTLR start NEQ
    public final void mNEQ() throws RecognitionException {
        try {
            int _type = NEQ;
            // Expression.g:15:5: ( '<>' )
            // Expression.g:15:7: '<>'
            {
            match("<>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NEQ

    // $ANTLR start LTE
    public final void mLTE() throws RecognitionException {
        try {
            int _type = LTE;
            // Expression.g:16:5: ( '<=' )
            // Expression.g:16:7: '<='
            {
            match("<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LTE

    // $ANTLR start LT
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            // Expression.g:17:4: ( '<' )
            // Expression.g:17:6: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LT

    // $ANTLR start GTE
    public final void mGTE() throws RecognitionException {
        try {
            int _type = GTE;
            // Expression.g:18:5: ( '>=' )
            // Expression.g:18:7: '>='
            {
            match(">="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GTE

    // $ANTLR start GT
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            // Expression.g:19:4: ( '>' )
            // Expression.g:19:6: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GT

    // $ANTLR start NOT
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            // Expression.g:20:5: ( 'not' )
            // Expression.g:20:7: 'not'
            {
            match("not"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT

    // $ANTLR start AND
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            // Expression.g:21:5: ( 'and' )
            // Expression.g:21:7: 'and'
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
            // Expression.g:22:4: ( 'or' )
            // Expression.g:22:6: 'or'
            {
            match("or"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OR

    // $ANTLR start ID
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            // Expression.g:23:4: ( 'IDENT' )
            // Expression.g:23:6: 'IDENT'
            {
            match("IDENT"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ID

    // $ANTLR start NUM
    public final void mNUM() throws RecognitionException {
        try {
            int _type = NUM;
            // Expression.g:24:5: ( 'NUM' )
            // Expression.g:24:7: 'NUM'
            {
            match("NUM"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NUM

    // $ANTLR start PROD
    public final void mPROD() throws RecognitionException {
        try {
            int _type = PROD;
            // Expression.g:25:6: ( 'PRODUCT' )
            // Expression.g:25:8: 'PRODUCT'
            {
            match("PRODUCT"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PROD

    // $ANTLR start UNARYEXPR
    public final void mUNARYEXPR() throws RecognitionException {
        try {
            int _type = UNARYEXPR;
            // Expression.g:26:11: ( 'UNARYEXPR' )
            // Expression.g:26:13: 'UNARYEXPR'
            {
            match("UNARYEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end UNARYEXPR

    // $ANTLR start MULEXPR
    public final void mMULEXPR() throws RecognitionException {
        try {
            int _type = MULEXPR;
            // Expression.g:27:9: ( 'MULEXPR' )
            // Expression.g:27:11: 'MULEXPR'
            {
            match("MULEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MULEXPR

    // $ANTLR start MODEXPR
    public final void mMODEXPR() throws RecognitionException {
        try {
            int _type = MODEXPR;
            // Expression.g:28:9: ( 'MODEXPR' )
            // Expression.g:28:11: 'MODEXPR'
            {
            match("MODEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MODEXPR

    // $ANTLR start ADDEXPR
    public final void mADDEXPR() throws RecognitionException {
        try {
            int _type = ADDEXPR;
            // Expression.g:29:9: ( 'ADDEXPR' )
            // Expression.g:29:11: 'ADDEXPR'
            {
            match("ADDEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ADDEXPR

    // $ANTLR start ARITHEXPR
    public final void mARITHEXPR() throws RecognitionException {
        try {
            int _type = ARITHEXPR;
            // Expression.g:30:11: ( 'ARITHEXPR' )
            // Expression.g:30:13: 'ARITHEXPR'
            {
            match("ARITHEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ARITHEXPR

    // $ANTLR start CMPEXPR
    public final void mCMPEXPR() throws RecognitionException {
        try {
            int _type = CMPEXPR;
            // Expression.g:31:9: ( 'CMPEXPR' )
            // Expression.g:31:11: 'CMPEXPR'
            {
            match("CMPEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CMPEXPR

    // $ANTLR start OREXPR
    public final void mOREXPR() throws RecognitionException {
        try {
            int _type = OREXPR;
            // Expression.g:32:8: ( 'NEGEXPR' )
            // Expression.g:32:10: 'NEGEXPR'
            {
            match("NEGEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OREXPR

    // $ANTLR start ANDEXPR
    public final void mANDEXPR() throws RecognitionException {
        try {
            int _type = ANDEXPR;
            // Expression.g:33:9: ( 'ANDEXPR' )
            // Expression.g:33:11: 'ANDEXPR'
            {
            match("ANDEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ANDEXPR

    // $ANTLR start LOGICEXPR
    public final void mLOGICEXPR() throws RecognitionException {
        try {
            int _type = LOGICEXPR;
            // Expression.g:34:11: ( 'LOGICEXPR' )
            // Expression.g:34:13: 'LOGICEXPR'
            {
            match("LOGICEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LOGICEXPR

    // $ANTLR start BLOCK
    public final void mBLOCK() throws RecognitionException {
        try {
            int _type = BLOCK;
            // Expression.g:35:7: ( 'BLOCK' )
            // Expression.g:35:9: 'BLOCK'
            {
            match("BLOCK"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BLOCK

    // $ANTLR start CONDEXPR
    public final void mCONDEXPR() throws RecognitionException {
        try {
            int _type = CONDEXPR;
            // Expression.g:36:10: ( 'CONDEXPR' )
            // Expression.g:36:12: 'CONDEXPR'
            {
            match("CONDEXPR"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CONDEXPR

    // $ANTLR start T39
    public final void mT39() throws RecognitionException {
        try {
            int _type = T39;
            // Expression.g:37:5: ( '(' )
            // Expression.g:37:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T39

    // $ANTLR start T40
    public final void mT40() throws RecognitionException {
        try {
            int _type = T40;
            // Expression.g:38:5: ( ')' )
            // Expression.g:38:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T40

    // $ANTLR start T41
    public final void mT41() throws RecognitionException {
        try {
            int _type = T41;
            // Expression.g:39:5: ( ',' )
            // Expression.g:39:7: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T41

    // $ANTLR start IDENT
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            // Expression.g:169:2: ( ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )* )
            // Expression.g:169:4: ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            {
            // Expression.g:169:4: ( 'a' .. 'z' )
            // Expression.g:169:5: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            // Expression.g:169:14: ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Expression.g:
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

    // $ANTLR start NUMBER
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            // Expression.g:172:8: ( ( DIGIT )+ )
            // Expression.g:172:10: ( DIGIT )+
            {
            // Expression.g:172:10: ( DIGIT )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Expression.g:172:11: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
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
            // Expression.g:174:4: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // Expression.g:174:6: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // Expression.g:174:6: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||(LA3_0>='\f' && LA3_0<='\r')||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Expression.g:
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
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
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
            // Expression.g:176:16: ( '0' .. '9' )
            // Expression.g:176:18: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end DIGIT

    public void mTokens() throws RecognitionException {
        // Expression.g:1:8: ( IF | THEN | ELSE | PLUS | MINUS | MUL | MOD | DIV | EQ | NEQ | LTE | LT | GTE | GT | NOT | AND | OR | ID | NUM | PROD | UNARYEXPR | MULEXPR | MODEXPR | ADDEXPR | ARITHEXPR | CMPEXPR | OREXPR | ANDEXPR | LOGICEXPR | BLOCK | CONDEXPR | T39 | T40 | T41 | IDENT | NUMBER | WS )
        int alt4=37;
        switch ( input.LA(1) ) {
        case 'i':
            {
            int LA4_1 = input.LA(2);

            if ( (LA4_1=='f') ) {
                int LA4_30 = input.LA(3);

                if ( ((LA4_30>='0' && LA4_30<='9')||(LA4_30>='A' && LA4_30<='Z')||(LA4_30>='a' && LA4_30<='z')) ) {
                    alt4=35;
                }
                else {
                    alt4=1;}
            }
            else {
                alt4=35;}
            }
            break;
        case 't':
            {
            int LA4_2 = input.LA(2);

            if ( (LA4_2=='h') ) {
                int LA4_31 = input.LA(3);

                if ( (LA4_31=='e') ) {
                    int LA4_53 = input.LA(4);

                    if ( (LA4_53=='n') ) {
                        int LA4_60 = input.LA(5);

                        if ( ((LA4_60>='0' && LA4_60<='9')||(LA4_60>='A' && LA4_60<='Z')||(LA4_60>='a' && LA4_60<='z')) ) {
                            alt4=35;
                        }
                        else {
                            alt4=2;}
                    }
                    else {
                        alt4=35;}
                }
                else {
                    alt4=35;}
            }
            else {
                alt4=35;}
            }
            break;
        case 'e':
            {
            int LA4_3 = input.LA(2);

            if ( (LA4_3=='l') ) {
                int LA4_32 = input.LA(3);

                if ( (LA4_32=='s') ) {
                    int LA4_54 = input.LA(4);

                    if ( (LA4_54=='e') ) {
                        int LA4_61 = input.LA(5);

                        if ( ((LA4_61>='0' && LA4_61<='9')||(LA4_61>='A' && LA4_61<='Z')||(LA4_61>='a' && LA4_61<='z')) ) {
                            alt4=35;
                        }
                        else {
                            alt4=3;}
                    }
                    else {
                        alt4=35;}
                }
                else {
                    alt4=35;}
            }
            else {
                alt4=35;}
            }
            break;
        case '+':
            {
            alt4=4;
            }
            break;
        case '-':
            {
            alt4=5;
            }
            break;
        case '*':
            {
            alt4=6;
            }
            break;
        case 'm':
            {
            int LA4_7 = input.LA(2);

            if ( (LA4_7=='o') ) {
                int LA4_33 = input.LA(3);

                if ( (LA4_33=='d') ) {
                    int LA4_55 = input.LA(4);

                    if ( ((LA4_55>='0' && LA4_55<='9')||(LA4_55>='A' && LA4_55<='Z')||(LA4_55>='a' && LA4_55<='z')) ) {
                        alt4=35;
                    }
                    else {
                        alt4=7;}
                }
                else {
                    alt4=35;}
            }
            else {
                alt4=35;}
            }
            break;
        case 'd':
            {
            int LA4_8 = input.LA(2);

            if ( (LA4_8=='i') ) {
                int LA4_34 = input.LA(3);

                if ( (LA4_34=='v') ) {
                    int LA4_56 = input.LA(4);

                    if ( ((LA4_56>='0' && LA4_56<='9')||(LA4_56>='A' && LA4_56<='Z')||(LA4_56>='a' && LA4_56<='z')) ) {
                        alt4=35;
                    }
                    else {
                        alt4=8;}
                }
                else {
                    alt4=35;}
            }
            else {
                alt4=35;}
            }
            break;
        case '=':
            {
            alt4=9;
            }
            break;
        case '<':
            {
            switch ( input.LA(2) ) {
            case '=':
                {
                alt4=11;
                }
                break;
            case '>':
                {
                alt4=10;
                }
                break;
            default:
                alt4=12;}

            }
            break;
        case '>':
            {
            int LA4_11 = input.LA(2);

            if ( (LA4_11=='=') ) {
                alt4=13;
            }
            else {
                alt4=14;}
            }
            break;
        case 'n':
            {
            int LA4_12 = input.LA(2);

            if ( (LA4_12=='o') ) {
                int LA4_40 = input.LA(3);

                if ( (LA4_40=='t') ) {
                    int LA4_57 = input.LA(4);

                    if ( ((LA4_57>='0' && LA4_57<='9')||(LA4_57>='A' && LA4_57<='Z')||(LA4_57>='a' && LA4_57<='z')) ) {
                        alt4=35;
                    }
                    else {
                        alt4=15;}
                }
                else {
                    alt4=35;}
            }
            else {
                alt4=35;}
            }
            break;
        case 'a':
            {
            int LA4_13 = input.LA(2);

            if ( (LA4_13=='n') ) {
                int LA4_41 = input.LA(3);

                if ( (LA4_41=='d') ) {
                    int LA4_58 = input.LA(4);

                    if ( ((LA4_58>='0' && LA4_58<='9')||(LA4_58>='A' && LA4_58<='Z')||(LA4_58>='a' && LA4_58<='z')) ) {
                        alt4=35;
                    }
                    else {
                        alt4=16;}
                }
                else {
                    alt4=35;}
            }
            else {
                alt4=35;}
            }
            break;
        case 'o':
            {
            int LA4_14 = input.LA(2);

            if ( (LA4_14=='r') ) {
                int LA4_42 = input.LA(3);

                if ( ((LA4_42>='0' && LA4_42<='9')||(LA4_42>='A' && LA4_42<='Z')||(LA4_42>='a' && LA4_42<='z')) ) {
                    alt4=35;
                }
                else {
                    alt4=17;}
            }
            else {
                alt4=35;}
            }
            break;
        case 'I':
            {
            alt4=18;
            }
            break;
        case 'N':
            {
            int LA4_16 = input.LA(2);

            if ( (LA4_16=='U') ) {
                alt4=19;
            }
            else if ( (LA4_16=='E') ) {
                alt4=27;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( IF | THEN | ELSE | PLUS | MINUS | MUL | MOD | DIV | EQ | NEQ | LTE | LT | GTE | GT | NOT | AND | OR | ID | NUM | PROD | UNARYEXPR | MULEXPR | MODEXPR | ADDEXPR | ARITHEXPR | CMPEXPR | OREXPR | ANDEXPR | LOGICEXPR | BLOCK | CONDEXPR | T39 | T40 | T41 | IDENT | NUMBER | WS );", 4, 16, input);

                throw nvae;
            }
            }
            break;
        case 'P':
            {
            alt4=20;
            }
            break;
        case 'U':
            {
            alt4=21;
            }
            break;
        case 'M':
            {
            int LA4_19 = input.LA(2);

            if ( (LA4_19=='U') ) {
                alt4=22;
            }
            else if ( (LA4_19=='O') ) {
                alt4=23;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( IF | THEN | ELSE | PLUS | MINUS | MUL | MOD | DIV | EQ | NEQ | LTE | LT | GTE | GT | NOT | AND | OR | ID | NUM | PROD | UNARYEXPR | MULEXPR | MODEXPR | ADDEXPR | ARITHEXPR | CMPEXPR | OREXPR | ANDEXPR | LOGICEXPR | BLOCK | CONDEXPR | T39 | T40 | T41 | IDENT | NUMBER | WS );", 4, 19, input);

                throw nvae;
            }
            }
            break;
        case 'A':
            {
            switch ( input.LA(2) ) {
            case 'D':
                {
                alt4=24;
                }
                break;
            case 'N':
                {
                alt4=28;
                }
                break;
            case 'R':
                {
                alt4=25;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( IF | THEN | ELSE | PLUS | MINUS | MUL | MOD | DIV | EQ | NEQ | LTE | LT | GTE | GT | NOT | AND | OR | ID | NUM | PROD | UNARYEXPR | MULEXPR | MODEXPR | ADDEXPR | ARITHEXPR | CMPEXPR | OREXPR | ANDEXPR | LOGICEXPR | BLOCK | CONDEXPR | T39 | T40 | T41 | IDENT | NUMBER | WS );", 4, 20, input);

                throw nvae;
            }

            }
            break;
        case 'C':
            {
            int LA4_21 = input.LA(2);

            if ( (LA4_21=='M') ) {
                alt4=26;
            }
            else if ( (LA4_21=='O') ) {
                alt4=31;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( IF | THEN | ELSE | PLUS | MINUS | MUL | MOD | DIV | EQ | NEQ | LTE | LT | GTE | GT | NOT | AND | OR | ID | NUM | PROD | UNARYEXPR | MULEXPR | MODEXPR | ADDEXPR | ARITHEXPR | CMPEXPR | OREXPR | ANDEXPR | LOGICEXPR | BLOCK | CONDEXPR | T39 | T40 | T41 | IDENT | NUMBER | WS );", 4, 21, input);

                throw nvae;
            }
            }
            break;
        case 'L':
            {
            alt4=29;
            }
            break;
        case 'B':
            {
            alt4=30;
            }
            break;
        case '(':
            {
            alt4=32;
            }
            break;
        case ')':
            {
            alt4=33;
            }
            break;
        case ',':
            {
            alt4=34;
            }
            break;
        case 'b':
        case 'c':
        case 'f':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt4=35;
            }
            break;
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
            alt4=36;
            }
            break;
        case '\t':
        case '\n':
        case '\f':
        case '\r':
        case ' ':
            {
            alt4=37;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( IF | THEN | ELSE | PLUS | MINUS | MUL | MOD | DIV | EQ | NEQ | LTE | LT | GTE | GT | NOT | AND | OR | ID | NUM | PROD | UNARYEXPR | MULEXPR | MODEXPR | ADDEXPR | ARITHEXPR | CMPEXPR | OREXPR | ANDEXPR | LOGICEXPR | BLOCK | CONDEXPR | T39 | T40 | T41 | IDENT | NUMBER | WS );", 4, 0, input);

            throw nvae;
        }

        switch (alt4) {
            case 1 :
                // Expression.g:1:10: IF
                {
                mIF(); 

                }
                break;
            case 2 :
                // Expression.g:1:13: THEN
                {
                mTHEN(); 

                }
                break;
            case 3 :
                // Expression.g:1:18: ELSE
                {
                mELSE(); 

                }
                break;
            case 4 :
                // Expression.g:1:23: PLUS
                {
                mPLUS(); 

                }
                break;
            case 5 :
                // Expression.g:1:28: MINUS
                {
                mMINUS(); 

                }
                break;
            case 6 :
                // Expression.g:1:34: MUL
                {
                mMUL(); 

                }
                break;
            case 7 :
                // Expression.g:1:38: MOD
                {
                mMOD(); 

                }
                break;
            case 8 :
                // Expression.g:1:42: DIV
                {
                mDIV(); 

                }
                break;
            case 9 :
                // Expression.g:1:46: EQ
                {
                mEQ(); 

                }
                break;
            case 10 :
                // Expression.g:1:49: NEQ
                {
                mNEQ(); 

                }
                break;
            case 11 :
                // Expression.g:1:53: LTE
                {
                mLTE(); 

                }
                break;
            case 12 :
                // Expression.g:1:57: LT
                {
                mLT(); 

                }
                break;
            case 13 :
                // Expression.g:1:60: GTE
                {
                mGTE(); 

                }
                break;
            case 14 :
                // Expression.g:1:64: GT
                {
                mGT(); 

                }
                break;
            case 15 :
                // Expression.g:1:67: NOT
                {
                mNOT(); 

                }
                break;
            case 16 :
                // Expression.g:1:71: AND
                {
                mAND(); 

                }
                break;
            case 17 :
                // Expression.g:1:75: OR
                {
                mOR(); 

                }
                break;
            case 18 :
                // Expression.g:1:78: ID
                {
                mID(); 

                }
                break;
            case 19 :
                // Expression.g:1:81: NUM
                {
                mNUM(); 

                }
                break;
            case 20 :
                // Expression.g:1:85: PROD
                {
                mPROD(); 

                }
                break;
            case 21 :
                // Expression.g:1:90: UNARYEXPR
                {
                mUNARYEXPR(); 

                }
                break;
            case 22 :
                // Expression.g:1:100: MULEXPR
                {
                mMULEXPR(); 

                }
                break;
            case 23 :
                // Expression.g:1:108: MODEXPR
                {
                mMODEXPR(); 

                }
                break;
            case 24 :
                // Expression.g:1:116: ADDEXPR
                {
                mADDEXPR(); 

                }
                break;
            case 25 :
                // Expression.g:1:124: ARITHEXPR
                {
                mARITHEXPR(); 

                }
                break;
            case 26 :
                // Expression.g:1:134: CMPEXPR
                {
                mCMPEXPR(); 

                }
                break;
            case 27 :
                // Expression.g:1:142: OREXPR
                {
                mOREXPR(); 

                }
                break;
            case 28 :
                // Expression.g:1:149: ANDEXPR
                {
                mANDEXPR(); 

                }
                break;
            case 29 :
                // Expression.g:1:157: LOGICEXPR
                {
                mLOGICEXPR(); 

                }
                break;
            case 30 :
                // Expression.g:1:167: BLOCK
                {
                mBLOCK(); 

                }
                break;
            case 31 :
                // Expression.g:1:173: CONDEXPR
                {
                mCONDEXPR(); 

                }
                break;
            case 32 :
                // Expression.g:1:182: T39
                {
                mT39(); 

                }
                break;
            case 33 :
                // Expression.g:1:186: T40
                {
                mT40(); 

                }
                break;
            case 34 :
                // Expression.g:1:190: T41
                {
                mT41(); 

                }
                break;
            case 35 :
                // Expression.g:1:194: IDENT
                {
                mIDENT(); 

                }
                break;
            case 36 :
                // Expression.g:1:200: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 37 :
                // Expression.g:1:207: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}