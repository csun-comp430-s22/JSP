#include "token.h"
#include <string>
using namespace std;

class String : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 16;
	}

	string Token::toString()
	{
		return "string";
	}
};
