lexer grammar Multiset;
@header {
package pkowalski.rtcp.model.syntax;
}

T11 : '+' ;
T12 : '(' ;
T13 : ')' ;
T14 : ',' ;

// $ANTLR src "Multiset.g" 51
IDENT
	: ('a'..'z')('a'..'z' | 'A'..'Z' | DIGIT)*
	;


// $ANTLR src "Multiset.g" 56
TYPENAME
	: ('A'..'Z')('a'..'z' | 'A'..'Z' | DIGIT)*
	;


// $ANTLR src "Multiset.g" 61
NUMBER	: (DIGIT)+ ;

// $ANTLR src "Multiset.g" 63
WS : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

// $ANTLR src "Multiset.g" 65
fragment DIGIT	: '0'..'9' ;