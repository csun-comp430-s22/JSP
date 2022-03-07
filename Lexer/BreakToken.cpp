#include "token.h"
#include <string>
using namespace std;

class Break : public Token {
	bool Token::equals()
	{

	}

	int Token::hashCode()
	{
		return 11;
	}

	string Token::toString()
	{
		return "break";
	}
};