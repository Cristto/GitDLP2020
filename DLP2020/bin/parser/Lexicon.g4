lexer grammar Lexicon
	;

INT_CONSTANT: [0-9]+;

REAL_CONSTANT: [0-9]+'.'[0-9]+;

CHAR_CONSTANT: '\'' ( '\\' INT_CONSTANT | '\\n' | '\\r' | '\\t' | .) '\'';

IDENT: [a-zA-Z��_][a-zA-Z��0-9_]*;

LINE_COMMENT
	: '//' .*? ('\n' | EOF) -> skip
	;

MULTILINE_COMMENT
	: '/*' .*? '*/' -> skip
	;

WHITESPACE
	: [ \t\r\n]+ -> skip
	;
