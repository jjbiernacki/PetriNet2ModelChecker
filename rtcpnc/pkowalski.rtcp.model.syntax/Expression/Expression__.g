lexer grammar Expression;
@header {
package pkowalski.rtcp.model.syntax;
}

IF : 'if' ;
THEN : 'then' ;
ELSE : 'else' ;
PLUS : '+' ;
MINUS : '-' ;
MUL : '*' ;
MOD : 'mod' ;
DIV : 'div' ;
EQ : '=' ;
NEQ : '<>' ;
LTE : '<=' ;
LT : '<' ;
GTE : '>=' ;
GT : '>' ;
NOT : 'not' ;
AND : 'and' ;
OR : 'or' ;
ID : 'IDENT' ;
NUM : 'NUM' ;
PROD : 'PRODUCT' ;
UNARYEXPR : 'UNARYEXPR' ;
MULEXPR : 'MULEXPR' ;
MODEXPR : 'MODEXPR' ;
ADDEXPR : 'ADDEXPR' ;
ARITHEXPR : 'ARITHEXPR' ;
CMPEXPR : 'CMPEXPR' ;
OREXPR : 'NEGEXPR' ;
ANDEXPR : 'ANDEXPR' ;
LOGICEXPR : 'LOGICEXPR' ;
BLOCK : 'BLOCK' ;
CONDEXPR : 'CONDEXPR' ;
T39 : '(' ;
T40 : ')' ;
T41 : ',' ;

// $ANTLR src "Expression.g" 168
IDENT
	: ('a'..'z')('a'..'z' | 'A'..'Z' | DIGIT)*
	;

// $ANTLR src "Expression.g" 172
NUMBER	: (DIGIT)+ ;

// $ANTLR src "Expression.g" 174
WS : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

// $ANTLR src "Expression.g" 176
fragment DIGIT	: '0'..'9' ;