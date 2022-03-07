#include "token.h"
#include <string>
using namespace std;

class Minus : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 8;
	}

	string Token::toString()
	{
		return "-";
	}
};