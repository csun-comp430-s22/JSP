#include "token.h"
#include <string>
using namespace std;

class While : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 13;
	}

	string Token::toString()
	{
		return "while";
	}
};