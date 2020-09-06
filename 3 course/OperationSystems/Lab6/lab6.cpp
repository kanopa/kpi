#include<stdio.h>
#include "windows.h"
#include <iostream>

void new_func1(void);

int resultOfSum(int a, int b)
{
	return a + b;
}

int func1(int a, int b)
{
	int res = 0;
	int i;
	for (i = 0; i < 10; i++)
	{
		Sleep(1);
		if (i > 8)
			res = resultOfSum(a, b);
		if (res > 0)
			return res;
	}
	return res;
}
int func2(int a, int b, int c)
{
	int res = 0;
	int i;
	for (i = 0; i < 10; i++)
	{
		c++;
		if (c > b)
			res = func1(a, b);
		else
			res = func2(a, b, c);
		if (res > 0)
			return res;
	}

	return res;
}

int main(void)
{
	func2(35, 120, 28);

	return 0;
}
