#include <stdio.h>

int main()
{
	char str[20];

	printf("Pick your favorite pizza out of the four: Pepperoni, Gorgonzola, Hawaiian, Supreme \n");
	scanf("%s", &str);
	{
		if (strcmp ("Pepperoni", str)== 0) {
		printf("One order of Pepperoni pizza coming right up! \n");
	}
	else if (strcmp("Gorgonzola", str) == 0) {
		printf("One order of Gorgonzola pizza coming right up! \n");
	}
	else if (strcmp("Hawaiian", str) == 0) {
		printf("One order of Hawaiian pizza coming right up! \n");
	}
	else if (strcmp("Supreme", str) == 0) {
		printf("One order of Supreme pizza coming right up! \n");
	}
	else {
		printf("That's either not from the choice, or you didn't even ask for pizza... \n");
	}
	}
	return 0;
	}