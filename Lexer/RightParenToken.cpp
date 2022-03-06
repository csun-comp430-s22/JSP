#include "token.h"
#include <string>
using namespace std;

class RightParen : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 4;
	}

	string Token::toString()
	{
		return ")";
	}
};