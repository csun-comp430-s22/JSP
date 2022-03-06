#pragma once
#include <string>
class Token
{
public:
	//virtual void equals() = 0;
	virtual bool equals() = 0;
	virtual int hashCode() = 0;
	virtual string toString() = 0;
};

