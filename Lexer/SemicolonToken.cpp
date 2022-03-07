#include "token.h"
#include <string>
using namespace std;

class Semicolon : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 14;
	}

	string Token::toString()
	{
		return ";";
	}
};