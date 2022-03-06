#include "token.h"
#include <string>
using namespace std;

class RightCurl : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 6;
	}

	string Token::toString()
	{
		return "}";
	}
};