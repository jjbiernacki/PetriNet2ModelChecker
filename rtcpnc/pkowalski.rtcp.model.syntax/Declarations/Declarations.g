grammar Declarations;

options{
    output=AST;
}
    
tokens {
COLOR = 'color';
INT = 'int';
WITH = 'with';
BOOL = 'bool';
PRODUCT = 'product';
VAR = 'var';
IF = 'if';
THEN = 'then';
ELSE = 'else';
AND = 'and';
OR = 'or';
NOT = 'not';
INTCOLOR = 'INT';
BOOLCOLOR = 'BOOL';
ENUMCOLOR = 'ENUM';
ALIASCOLOR = 'ALIAS';
PRODUCTCOLOR = 'PRODUCT';
VARLIST;
}

@lexer::header {
package pkowalski.rtcp.model.syntax;
}
@parser::header{
package pkowalski.rtcp.model.syntax;
}




parse
	:	(colordef | vardef)*
	;




/*TYPEDEF
	: (INT_COLOR | BOOL_COLOR | ENUM_COLOR | ALIAS_COLOR | PRODUCT_COLOR)*
	;
*/

vardef	
	:	VAR^ varslistdef ':'! vartype ';'!
	;
	
vartype	:	TYPENAME
	;
	
varslistdef
	:	varslist -> ^(VARLIST["VARLIST"] varslist)
	;
	
	
varslist
	:	(varitem) (','! varitem)* 
	;
varitem	:	IDENT
	;

IDENT
	: ('a'..'z')('a'..'z' | 'A'..'Z' | DIGIT)*
	;
	
TYPENAME
	: ('A'..'Z')('a'..'z' | 'A'..'Z' | DIGIT)*
	;
	
colordef:
		COLOR^ colorname '='! colorclass ';'!	
	;
	
colorclass
	:	
	  booldef -> ^(BOOLCOLOR booldef)
	| intdef  -> ^(INTCOLOR intdef)
	| enumdef -> ^(ENUMCOLOR enumdef)
	| aliasdef -> ^(ALIASCOLOR aliasdef)
	| productdef -> ^(PRODUCTCOLOR productdef)
	;
	
colorname
	:	TYPENAME
	;
	

	
/*
COLORDEC:	
		COLOR WHITESPACE TYPENAME
	;
*/
	
	
booldef
	:
		BOOL! WITH! '('! boolwhenfalse ','! boolwhentrue ')'!
	;
boolwhenfalse
	:	IDENT
	;
boolwhentrue
	:	IDENT
	;

intdef
	:	INT! WITH! intlow '..'! inthigh
	;
	
intlow	:	NUMBER
	;
inthigh	:	NUMBER
	;
	
enumdef	
	:	WITH! enumlist
	;
enumlist:
		enumitem ( '|'! enumitem )*	
	;
enumitem:	IDENT
	;

aliasdef
	:	TYPENAME
	;

	
productdef
	:	PRODUCT! productslist
	;
	
productslist
	:	(productitem) ( '*'! productitem )+
	;
productitem
	:	TYPENAME
	;
	

NUMBER	: (NEG)?(DIGIT)+ ;

WS : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

fragment DIGIT	: '0'..'9' ;
fragment NEG	: '-';