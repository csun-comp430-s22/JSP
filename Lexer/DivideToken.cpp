#include "token.h"
#include <string>
using namespace std;

class Divide : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 10;
	}

	string Token::toString()
	{
		return "/";
	}
};