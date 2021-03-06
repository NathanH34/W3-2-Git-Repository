/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2018  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/**
*  C++ lexer definition.
*
* TODO: add recognition for #include statements
*
*/

package edu.odu.cs.cs350;


%%

%public
%class Scanner

%unicode

%line
%column
%type CPPToken

%{
  StringBuilder string = new StringBuilder();
  
  private CPPToken symbol(TokenKinds type) {
    return new CPPToken(type, yyline+1, yycolumn+1);
  }

  private CPPToken symbol(TokenKinds type, Object value) {
    return new CPPToken(type, value.toString(), yyline+1, yycolumn+1);
  }

  /** 
   * assumes correct representation of a long value for 
   * specified radix in scanner buffer from <code>start</code> 
   * to <code>end</code> 
   */
  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]
    
/* floating point literals */        
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING, CHARLITERAL

%% 

<YYINITIAL> {

  /* keywords */
  "alignas"                 { return symbol(TokenKinds.ALIGNAS); }
  "alignof"                 { return symbol(TokenKinds.ALIGNOF); }
  "and"                     { return symbol(TokenKinds.AND); }
  "and_eq"                  { return symbol(TokenKinds.AND_EQ); }
  "asm"                     { return symbol(TokenKinds.ASM); }
  "atomic_cancel"           { return symbol(TokenKinds.ATOMIC_CANCEL); }
  "atomic_commit"           { return symbol(TokenKinds.ATOMIC_COMMIT); }
  "atomic_noexcept"         { return symbol(TokenKinds.ATOMIC_NOEXCEPT); }
  "auto"                    { return symbol(TokenKinds.AUTO); }
  "bitand"                  { return symbol(TokenKinds.BITAND); }
  "bitor"                   { return symbol(TokenKinds.BITOR); }
  "bool"                    { return symbol(TokenKinds.BOOL); }
  "break"                   { return symbol(TokenKinds.BREAK); }
  "case"                    { return symbol(TokenKinds.CASE); }
  "catch"                   { return symbol(TokenKinds.CATCH); }
  "char"                    { return symbol(TokenKinds.CHAR); }
  "char8_t"                 { return symbol(TokenKinds.CHAR8_T); }
  "char16_t"                { return symbol(TokenKinds.CHAR16_T); }
  "char32_t"                { return symbol(TokenKinds.CHAR32_T); }
  "class"                   { return symbol(TokenKinds.CLASS); }
  "compl"                   { return symbol(TokenKinds.COMPL); }
  "concept"                 { return symbol(TokenKinds.CONCEPT); }
  "const"                   { return symbol(TokenKinds.CONST); }
  "consteval"               { return symbol(TokenKinds.CONSTEVAL); }
  "constexpr"               { return symbol(TokenKinds.CONSTEXPR); }
  "constinit"               { return symbol(TokenKinds.CONSTINIT); }
  "const_cast"              { return symbol(TokenKinds.CONST_CAST); }
  "continue"                { return symbol(TokenKinds.CONTINUE); }
  "co_await"                { return symbol(TokenKinds.CO_AWAIT); }
  "co_return"               { return symbol(TokenKinds.CO_RETURN); }
  "co_yield"                { return symbol(TokenKinds.CO_YIELD); }
  "decltype"                { return symbol(TokenKinds.DECLTYPE); }
  "default"                 { return symbol(TokenKinds.DEFAULT); }
  "delete"                  { return symbol(TokenKinds.DELETE); }
  "do"                      { return symbol(TokenKinds.DO); }
  "double"                  { return symbol(TokenKinds.DOUBLE); }
  "dynamic_cast"            { return symbol(TokenKinds.DYNAMIC_CAST); }
  "else"                    { return symbol(TokenKinds.ELSE); }
  "enum"                    { return symbol(TokenKinds.ENUM); }
  "explicit"                { return symbol(TokenKinds.EXPLICIT); }
  "export"                  { return symbol(TokenKinds.EXPORT); }
  "extern"                  { return symbol(TokenKinds.EXTERN); }
  "float"                   { return symbol(TokenKinds.FLOAT); }
  "for"                     { return symbol(TokenKinds.FOR); }
  "friend"                  { return symbol(TokenKinds.FRIEND); }
  "goto"                    { return symbol(TokenKinds.GOTO); }
  "if"                      { return symbol(TokenKinds.IF); }
  "inline"                  { return symbol(TokenKinds.INLINE); }
  "int"                     { return symbol(TokenKinds.INT); }
  "long"                    { return symbol(TokenKinds.LONG); }
  "mutable"                 { return symbol(TokenKinds.MUTABLE); }
  "namespace"               { return symbol(TokenKinds.NAMESPACE); }
  "new"                     { return symbol(TokenKinds.NEW); }
  "noexcept"                { return symbol(TokenKinds.NOEXCEPT); }
  "not"                     { return symbol(TokenKinds.NOT); }
  "not_eq"                  { return symbol(TokenKinds.NOT_EQ); }
  "nullptr"                 { return symbol(TokenKinds.NULLPTR); }
  "operator"                { return symbol(TokenKinds.OPERATOR); }
  "or"                      { return symbol(TokenKinds.OR); }
  "or_eq"                   { return symbol(TokenKinds.OR_EQ); }
  "private"                 { return symbol(TokenKinds.PRIVATE); }
  "protected"               { return symbol(TokenKinds.PROTECTED); }
  "public"                  { return symbol(TokenKinds.PUBLIC); }
  "reflexpr"                { return symbol(TokenKinds.REFLEXPR); }
  "register"                { return symbol(TokenKinds.REGISTER); }
  "reinterpret_cast"        { return symbol(TokenKinds.REINTERPRET_CAST); }
  "requires"                { return symbol(TokenKinds.REQUIRES); }
  "return"                  { return symbol(TokenKinds.RETURN); }
  "short"                   { return symbol(TokenKinds.SHORT); }
  "signed"                  { return symbol(TokenKinds.SIGNED); }
  "sizeof"                  { return symbol(TokenKinds.SIZEOF); }
  "static"                  { return symbol(TokenKinds.STATIC); }
  "static_assert"           { return symbol(TokenKinds.STATIC_ASSERT); }
  "static_cast"             { return symbol(TokenKinds.STATIC_CAST); }
  "struct"                  { return symbol(TokenKinds.STRUCT); }
  "switch"                  { return symbol(TokenKinds.SWITCH); }
  "synchronized"            { return symbol(TokenKinds.SYNCHRONIZED); }
  "template"                { return symbol(TokenKinds.TEMPLATE); }
  "this"                    { return symbol(TokenKinds.THIS); }
  "thread_local"            { return symbol(TokenKinds.THREAD_LOCAL); }
  "throw"                   { return symbol(TokenKinds.THROW); }
  "try"                     { return symbol(TokenKinds.TRY); }
  "typedef"                 { return symbol(TokenKinds.TYPEDEF); }
  "typeid"                  { return symbol(TokenKinds.TYPEID); }
  "typename"                { return symbol(TokenKinds.TYPENAME); }
  "union"                   { return symbol(TokenKinds.UNION); }
  "unsigned"                { return symbol(TokenKinds.UNSIGNED); }
  "using"                   { return symbol(TokenKinds.USING); }
  "virtual"                 { return symbol(TokenKinds.VIRTUAL); }
  "void"                    { return symbol(TokenKinds.VOID); }
  "volatile"                { return symbol(TokenKinds.VOLATILE); }
  "wchar_t"                 { return symbol(TokenKinds.WCHAR_T); }
  "while"                   { return symbol(TokenKinds.WHILE); }
  "xor"                     { return symbol(TokenKinds.XOR); }
  "xor_eq"                  { return symbol(TokenKinds.XOR_EQ); }

  /* boolean literals */
  "true"                         { return symbol(TokenKinds.BOOLEAN_LITERAL, true); }
  "false"                        { return symbol(TokenKinds.BOOLEAN_LITERAL, false); }
  
  /* null literal */
  "null"                         { return symbol(TokenKinds.NULL_LITERAL); }
  
  
  /* separators */
  "("                            { return symbol(TokenKinds.LPAREN); }
  ")"                            { return symbol(TokenKinds.RPAREN); }
  "{"                            { return symbol(TokenKinds.LBRACE); }
  "}"                            { return symbol(TokenKinds.RBRACE); }
  "["                            { return symbol(TokenKinds.LBRACK); }
  "]"                            { return symbol(TokenKinds.RBRACK); }
  ";"                            { return symbol(TokenKinds.SEMICOLON); }
  ","                            { return symbol(TokenKinds.COMMA); }
  "."                            { return symbol(TokenKinds.DOT); }
  
  /* operators */
  "="                            { return symbol(TokenKinds.EQ); }
  ">"                            { return symbol(TokenKinds.GT); }
  "<"                            { return symbol(TokenKinds.LT); }
  "!"                            { return symbol(TokenKinds.NOT); }
  "~"                            { return symbol(TokenKinds.COMPL); }
  "?"                            { return symbol(TokenKinds.QUESTION); }
  ":"                            { return symbol(TokenKinds.COLON); }
  "=="                           { return symbol(TokenKinds.EQEQ); }
  "<="                           { return symbol(TokenKinds.LTEQ); }
  ">="                           { return symbol(TokenKinds.GTEQ); }
  "!="                           { return symbol(TokenKinds.NOT_EQ); }
  "&&"                           { return symbol(TokenKinds.AND); }
  "||"                           { return symbol(TokenKinds.OR); }
  "++"                           { return symbol(TokenKinds.PLUSPLUS); }
  "--"                           { return symbol(TokenKinds.MINUSMINUS); }
  "+"                            { return symbol(TokenKinds.PLUS); }
  "-"                            { return symbol(TokenKinds.MINUS); }
  "*"                            { return symbol(TokenKinds.MULT); }
  "/"                            { return symbol(TokenKinds.DIV); }
  "&"                            { return symbol(TokenKinds.BITAND); }
  "|"                            { return symbol(TokenKinds.BITOR); }
  "^"                            { return symbol(TokenKinds.XOR); }
  "%"                            { return symbol(TokenKinds.MOD); }
  "<<"                           { return symbol(TokenKinds.LSHIFT); }
  ">>"                           { return symbol(TokenKinds.RSHIFT); }
  ">>>"                          { return symbol(TokenKinds.URSHIFT); }
  "+="                           { return symbol(TokenKinds.PLUSEQ); }
  "-="                           { return symbol(TokenKinds.MINUSEQ); }
  "*="                           { return symbol(TokenKinds.MULTEQ); }
  "/="                           { return symbol(TokenKinds.DIVEQ); }
  "&="                           { return symbol(TokenKinds.AND_EQ); }
  "|="                           { return symbol(TokenKinds.OR_EQ); }
  "^="                           { return symbol(TokenKinds.XOR_EQ); }
  "%="                           { return symbol(TokenKinds.MODEQ); }
  "<<="                          { return symbol(TokenKinds.LSHIFTEQ); }
  ">>="                          { return symbol(TokenKinds.RSHIFTEQ); }
  ">>>="                         { return symbol(TokenKinds.URSHIFTEQ); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }
  /* preprocessor symbols */
  "#define" |
  "#elif" |
  "#else" |
  "#endif" |
  "#error" |
  "#ifdef" |
  "#ifndef" |
  "#if" |
  "#import" |
  "#include" |
  "#line" |
  "#pragma" |
  "#undef" |
  "#using"
  	{ return symbol(TokenKinds.PREPROCESSOR, yytext()); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(TokenKinds.INTEGER_LITERAL, Integer.valueOf(Integer.MIN_VALUE)); }
  
  {DecIntegerLiteral}            { return symbol(TokenKinds.INTEGER_LITERAL, Integer.valueOf(yytext())); }
  {DecLongLiteral}               { return symbol(TokenKinds.INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {HexIntegerLiteral}            { return symbol(TokenKinds.INTEGER_LITERAL, Integer.valueOf((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return symbol(TokenKinds.INTEGER_LITERAL, new Long(parseLong(2, yylength()-1, 16))); }
 
  {OctIntegerLiteral}            { return symbol(TokenKinds.INTEGER_LITERAL, Integer.valueOf((int) parseLong(0, yylength(), 8))); }
  {OctLongLiteral}               { return symbol(TokenKinds.INTEGER_LITERAL, new Long(parseLong(0, yylength()-1, 8))); }
  
  {FloatLiteral}                 { return symbol(TokenKinds.FLOATING_POINT_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return symbol(TokenKinds.FLOATING_POINT_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return symbol(TokenKinds.FLOATING_POINT_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(TokenKinds.IDENTIFIER, yytext()); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(TokenKinds.STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val ); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, yytext().charAt(0)); }
  
  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\\'); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL); 
			                              int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
			                            return symbol(TokenKinds.CHARACTER_LITERAL, (char)val); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(TokenKinds.EOF); }