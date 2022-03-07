#include "token.h"
#include <string>
using namespace std;

class Print : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 15;
	}

	string Token::toString()
	{
		return "print";
	}
};