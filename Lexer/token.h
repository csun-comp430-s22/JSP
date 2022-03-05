#pragma once
class Token
{
public:
	virtual void equals() = 0;
	virtual void hashCode() = 0;
	virtual void toString() = 0;
};
