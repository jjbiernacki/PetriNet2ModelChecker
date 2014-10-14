// $ANTLR 3.0.1 Multiset.g 2010-05-09 23:59:16

package pkowalski.rtcp.model.syntax;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"ALL"})
public class MultisetLexer extends Lexer {
    public static final int TYPENAME=9;
    public static final int WS=10;
    public static final int ITEM=4;
    public static final int ITEMPARTS=5;
    public static final int NUMBER=6;
    public static final int IDENT=7;
    public static final int T11=11;
    public static final int T12=12;
    public static final int T13=13;
    public static final int DIGIT=8;
    public static final int T14=14;
    public static final int Tokens=15;
    public static final int EOF=-1;
    public MultisetLexer() {;} 
    public MultisetLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "Multiset.g"; }

    // $ANTLR start T11
    public final void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // Multiset.g:6:5: ( '+' )
            // Multiset.g:6:7: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // Multiset.g:7:5: ( '(' )
            // Multiset.g:7:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // Multiset.g:8:5: ( ')' )
            // Multiset.g:8:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // Multiset.g:9:5: ( ',' )
            // Multiset.g:9:7: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start IDENT
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            // Multiset.g:52:2: ( ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )* )
            // Multiset.g:52:4: ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            {
            // Multiset.g:52:4: ( 'a' .. 'z' )
            // Multiset.g:52:5: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            // Multiset.g:52:14: ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Multiset.g:
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
            // Multiset.g:57:2: ( ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )* )
            // Multiset.g:57:4: ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            {
            // Multiset.g:57:4: ( 'A' .. 'Z' )
            // Multiset.g:57:5: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }

            // Multiset.g:57:14: ( 'a' .. 'z' | 'A' .. 'Z' | DIGIT )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Multiset.g:
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
            // Multiset.g:61:8: ( ( DIGIT )+ )
            // Multiset.g:61:10: ( DIGIT )+
            {
            // Multiset.g:61:10: ( DIGIT )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Multiset.g:61:11: DIGIT
            	    {
            	    mDIGIT(); 

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
            // Multiset.g:63:4: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
            // Multiset.g:63:6: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            {
            // Multiset.g:63:6: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\t' && LA4_0<='\n')||(LA4_0>='\f' && LA4_0<='\r')||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Multiset.g:
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
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
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
            // Multiset.g:65:16: ( '0' .. '9' )
            // Multiset.g:65:18: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end DIGIT

    public void mTokens() throws RecognitionException {
        // Multiset.g:1:8: ( T11 | T12 | T13 | T14 | IDENT | TYPENAME | NUMBER | WS )
        int alt5=8;
        switch ( input.LA(1) ) {
        case '+':
            {
            alt5=1;
            }
            break;
        case '(':
            {
            alt5=2;
            }
            break;
        case ')':
            {
            alt5=3;
            }
            break;
        case ',':
            {
            alt5=4;
            }
            break;
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt5=5;
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
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
            alt5=6;
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
            alt5=7;
            }
            break;
        case '\t':
        case '\n':
        case '\f':
        case '\r':
        case ' ':
            {
            alt5=8;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T11 | T12 | T13 | T14 | IDENT | TYPENAME | NUMBER | WS );", 5, 0, input);

            throw nvae;
        }

        switch (alt5) {
            case 1 :
                // Multiset.g:1:10: T11
                {
                mT11(); 

                }
                break;
            case 2 :
                // Multiset.g:1:14: T12
                {
                mT12(); 

                }
                break;
            case 3 :
                // Multiset.g:1:18: T13
                {
                mT13(); 

                }
                break;
            case 4 :
                // Multiset.g:1:22: T14
                {
                mT14(); 

                }
                break;
            case 5 :
                // Multiset.g:1:26: IDENT
                {
                mIDENT(); 

                }
                break;
            case 6 :
                // Multiset.g:1:32: TYPENAME
                {
                mTYPENAME(); 

                }
                break;
            case 7 :
                // Multiset.g:1:41: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 8 :
                // Multiset.g:1:48: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}