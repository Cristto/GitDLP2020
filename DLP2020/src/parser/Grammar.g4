grammar Grammar
	;
import Lexicon
	;

start: definiciones EOF
	;
	
definiciones
	: definicion*
	;
	
	
definicion                                                 
	:       'var' IDENT ':' tipo ';'      											          						  	
	|		'struct' IDENT '{' campos '}' ';'							  	    	
	|		IDENT '(' parametrosOpc ')' '{' varLocales listaSentencias '}'         	
	|		IDENT '(' parametrosOpc ')' ':' tipo '{' varLocales listaSentencias '}' 
	;
	
parametrosOpc 
    :      parametros 
    |
    ;

parametros
    :		parametro(',' p2=parametro)*
    ; 
    
parametro: IDENT ':' tipo  
	;
  

varLocales 
	: varLocal*
	; 

varLocal: 'var' IDENT ':' tipo ';'  
	;

campos 
	:  campo*
	;

campo
  : IDENT ':' tipo ';'
  ;

tipoSimple
  :        'int'   
  |	       'char'  
  |	       'float'  
  ;
	
tipo 
  :  		tipoSimple               
  |   		'[' INT_CONSTANT ']' tipo  
  |	  		IDENT					 	
  ;
  

  
listaSentencias 
	: sentencia*
	;  
  
sentencia  
   :  		'print' expr ';'
   |	    'printsp' expr ';'
   |		'println' expr ';'
   |		expr '=' expr ';'
   |		'if' '(' expr ')' '{' listaSentencias '}' 'else' '{' listaSentencias '}'
   |		'if' '(' expr ')' '{' listaSentencias '}'								
   |		'while' '(' expr ')' '{' listaSentencias '}'							
   |		IDENT '(' argumentosOpc ')' ';'											
   |		'return' ';'															
   |		'return' expr ';'														
   |		'read' expr ';'															
   ;
  
expr 
  :     IDENT
  |		INT_CONSTANT
  |		REAL_CONSTANT
  | 	CHAR_CONSTANT
  |     expr '[' expr ']'
  |		'(' expr ')' 	
  |  	expr '.' IDENT 	 
  |		'cast' '<' tipo '>' '(' expr ')' 
  | 	'-' expr						
  |		'!' expr 						
  | 	expr ('*'|'/') expr			
  | 	expr ('+'|'-') expr			
  |		expr ('=='|'>='|'<='|'!='|'<'|'>') expr
  | 	expr ('&&'|'||') expr					
  | 	IDENT '(' argumentosOpc ')'	
  ;
  
argumentosOpc 
   :  		 argumentos 
   |
   ; 

argumentos 
   :       expr(',' expr)*
   ;  

