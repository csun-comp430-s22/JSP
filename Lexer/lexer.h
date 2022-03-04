#pragma once

enum Tokenizers
{
	Token_IDENTIFIER,

	//Literals
	Token_NUM,
	Token_INT,
	Token_BOOL,
	
	//Operators
	Token_PLUS,
	Token_MINUS,
	Token_ASTERICK,
	Token_SLASH,
	
	//Seperators
	Token_LEFTPAREN,
	Token_LEFTCURLY,
	Token_RIGHTPAREN,
	Token_RIGHTCURLY,
	Token_SEMICOLON,
	Token_PERIOD,
	
	//Keywords
	Token_IF,
	Token_ELSE,
	Token_RETURN,
	Token_PRINT,
	Token_WHILE,
	Token_BREAK,
	
};