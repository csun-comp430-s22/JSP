#include "token.h"
#include <string>
using namespace std;

class If : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 2;
	}

	string Token::toString()
	{
		return "if";
	}
};