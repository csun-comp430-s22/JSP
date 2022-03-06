#include "token.h"
#include <string>
using namespace std;

class Else : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 7;
	}

	string Token::toString()
	{
		return "else";
	}
};