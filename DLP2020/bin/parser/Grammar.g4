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
  
sentencia  returns[Sentencia ast]
   :  		'print' expr ';'						{$ast = new Print($expr.ast);}
   |	    'printsp' expr ';'						{$ast = new PrintSp($expr.ast);}
   |		'println' expr ';'						{$ast = new PrintLn($expr.ast);}
   |		expr '=' expr ';'						{$ast = new Asignacion($ctx.expr(0),$ctx.expr(1));}
   |		'if' '(' expr ')' '{' listaSentencias '}' 'else' '{' listaSentencias '}'   {$ast = new IfElse($expr.ast,$ctx.listaSentencias(0).list,$ctx.listaSentencias(1).list);}
   |		'if' '(' expr ')' '{' listaSentencias '}'								   {$ast = new If($expr.ast,$listaSentencia.list);}		
   |		'while' '(' expr ')' '{' listaSentencias '}'							   {$ast = new While($expr.ast,$listaSentencia.list);}
   |		IDENT '(' argumentosOpc ')' ';'											   {$ast = new InvocacionProcedimiento($IDENT,$argumentosOpc.list);}	
   |		'return' ';'								{$ast = new Return();}							
   |		'return' expr ';'							{$ast = new Return($expr.ast);}							
   |		'read' expr ';'								{$ast = new Read($expr.ast);}							
   ;
  
expr returns[Expresion ast]
  :     IDENT										{$ast = new Variable($IDENT);}
  |		INT_CONSTANT								{$ast = new CTE_Entera($INT_CONSTANT);}
  |		REAL_CONSTANT								{$ast = new CTE_REAL($REAL_CONSTANT);}
  | 	CHAR_CONSTANT								{$ast = new CTE_Char($CHAR_CONSTANT);}
  |     expr '[' expr ']'							{$ast = new AccesoArray($ctx.expr(0),$ctx.expr(1));}
  |		'(' expr ')' 								{$ast = $expr.ast;}
  |  	expr '.' IDENT 	 							{$ast = new AccesoStruct($ctx.expr(0),$IDENT);}
  |		'cast' '<' tipo '>' '(' expr ')' 			{$ast = new ExpresionCast($tipo.ast, $ctx.expr(0));}
  | 	'-' expr									{$ast = new ExprMenosUnario($ctx.expr);}
  |		'!' expr 									{$ast = new ExprNot($ctx.expr);}
  | 	expr op=('*'|'/') expr						{$ast = new ExprAritmetica($ctx.expr(0),$op.text,$ctx.expr(1));}
  | 	expr op=('+'|'-') expr						{$ast = new ExprAritmetica($ctx.expr(0),$op.text,$ctx.expr(1));}
  |		expr op=('=='|'>='|'<='|'!='|'<'|'>') expr	{$ast = new ExprComparacion($ctx.expr(0),$op.text,$ctx.expr(1));}
  | 	expr op=('&&'|'||') expr					{$ast = new ExprLogica($ctx.expr(0),$op.text,$ctx.expr(1));}
  | 	IDENT '(' argumentosOpc ')'					{$ast = new ExpresionInvocacion($IDENT, $argumentosOpc.list);}
  ;
  
argumentosOpc returns[List<Expresion> list = new ArrayList<Expresion>()]
   :  		 argumentos {$list = $argumentos.ast;}
   |
   ; 

argumentos returns[List<Expresion> ast = new ArrayList<Expresion>()]
   :        p1 = expr {$ast.add($p1.ast);} (',' p2=expr {$ast.add($p2.ast);})*
   ;  

