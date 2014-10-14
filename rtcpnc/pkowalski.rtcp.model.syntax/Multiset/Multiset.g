grammar Multiset;


options{
	output=AST;
}
tokens{
	ITEM;
	ITEMPARTS;
}


@lexer::header {
package pkowalski.rtcp.model.syntax;
}
@parser::header{
package pkowalski.rtcp.model.syntax;
}




parse	:
		multisetdef? EOF!
	;
	
multisetdef
	:	
		itemdef ('+'! itemdef)* 
	;
itemdef	:	
		item -> ^(ITEM item)
	;
	
item	:
		NUMBER* '('! itemvaldef ')'!	
	;
itemvaldef
	:
		itemval -> ^(ITEMPARTS itemval)	
	;
	
itemval	:
		ident (','! ident)*
	;
	
ident	:
		(IDENT | NUMBER)
	;
	
IDENT
	: ('a'..'z')('a'..'z' | 'A'..'Z' | DIGIT)*
	;


TYPENAME
	: ('A'..'Z')('a'..'z' | 'A'..'Z' | DIGIT)*
	;


NUMBER	: (DIGIT)+ ;

WS : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

fragment DIGIT	: '0'..'9' ;