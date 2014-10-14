grammar Expression;

options{
	output=AST;
}
tokens{
	IF='if';
	THEN='then';
	ELSE='else';
	
	PLUS='+';
	MINUS='-';
	MUL='*';
	MOD='mod';
	DIV='div';
	EQ='=';
	NEQ='<>';
	LTE='<=';
	LT='<';
	GTE='>=';
	GT='>';
	NOT='not';
	AND='and';
	OR='or';
	
	ID='IDENT';
	NUM='NUM';
	PROD='PRODUCT';
	
	UNARYEXPR='UNARYEXPR';
	MULEXPR='MULEXPR';
	MODEXPR='MODEXPR';
	ADDEXPR='ADDEXPR';
	ARITHEXPR='ARITHEXPR';
	CMPEXPR='CMPEXPR';
	OREXPR='NEGEXPR';
	ANDEXPR='ANDEXPR';
	LOGICEXPR='LOGICEXPR';
	BLOCK='BLOCK';
	CONDEXPR='CONDEXPR';
	
}


@lexer::header {
package pkowalski.rtcp.model.syntax;
}
@parser::header{
package pkowalski.rtcp.model.syntax;
}

parse	:
		(expression)?
	
	;
	
expression
	:
		orExpression
	;	

orExpression
	:
		orExpressionDef -> ^(OREXPR orExpressionDef)	
	;

orExpressionDef
	:
		andExpression (OR andExpression)*	
	;

andExpression
	:	
		andExpressionDef -> ^(ANDEXPR andExpressionDef)
	;
	
andExpressionDef
	:	
		cmpExpression (AND cmpExpression)*
	;
	
	
			
cmpExpression
	:
		cmpExpressionDef -> ^(CMPEXPR cmpExpressionDef)	
	;
cmpExpressionDef
	:	arithExpression ( EQ arithExpression | NEQ arithExpression  | LTE arithExpression | LT arithExpression | GTE arithExpression | GT arithExpression )?
	;
	
arithExpression
	:
		addExpression -> ^(ARITHEXPR addExpression)	
	;

addExpression
	:
		addExpressionDef -> ^(ADDEXPR addExpressionDef)	
	;
	
addExpressionDef
	:
		modExpression (PLUS modExpression | MINUS modExpression)*	
	;
	
modExpression
	:
		modExpressionDef -> ^(MODEXPR modExpressionDef)
	;
modExpressionDef
	:
		mulExpression (MOD mulExpression)*	
	;
	
mulExpression
	:
		mulExpressionDef -> ^(MULEXPR mulExpressionDef)
	;
mulExpressionDef
	:
		unaryExpression (MUL unaryExpression | DIV unaryExpression)*
	;
	
unaryExpression
	:	factor -> ^(UNARYEXPR factor)
	;	
	


factor	:
		(MINUS | NOT)? (ident | number | block | conditionalExpression)	
	;
	
conditionalExpression
	:
		conditionalExpressionDef -> ^(CONDEXPR conditionalExpressionDef )
	;
	
conditionalExpressionDef
	:	
		IF! orExpression THEN! expression ELSE! expression EOF!
	;
	
block	:
		blockDef -> ^(BLOCK blockDef)		
	;
	
blockDef:	
		'('! product ')'!
	;
	
	
ident	:	IDENT -> ^(ID IDENT)	
	;
	
number	:
		NUMBER -> ^(NUM NUMBER)	
	;
product	:
		productDef -> ^(PROD productDef)
	;
	
productDef	:	expression (','! expression)*	
	;
	

IDENT
	: ('a'..'z')('a'..'z' | 'A'..'Z' | DIGIT)*
	;

NUMBER	: (DIGIT)+ ;

WS : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

fragment DIGIT	: '0'..'9' ;