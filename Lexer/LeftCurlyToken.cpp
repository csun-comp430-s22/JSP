#include "token.h"
#include <string>
using namespace std;

class LeftCurl : public Token{
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 5;
	}

	string Token::toString()
	{
		return "{";
	}
};