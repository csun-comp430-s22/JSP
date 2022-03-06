#include "token.h"
#include <string>
using namespace std;

class LeftParen : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 3;
	}

	string Token::toString()
	{
		return "(";
	}
};