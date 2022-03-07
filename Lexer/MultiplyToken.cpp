#include "token.h"
#include <string>
using namespace std;

class Multiply : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 9;
	}

	string Token::toString()
	{
		return "*";
	}
};