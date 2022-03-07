#include "token.h"
#include <string>
using namespace std;

class Return : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 12;
	}

	string Token::toString()
	{
		return "return";
	}
};