#pragma once
#include <string>
#include "token.h"

class StringToken : public Token
{
public:
	bool equals(Object other final);

	int hashCode();

	string toString();
};
