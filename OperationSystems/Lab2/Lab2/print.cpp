#include <stdio.h>
#include "allocator.h"


void mem_dump()
{
	printf("\nMemory Dump\n");
	printf("Global address: %p\n", global_memory);
	for (size_t i = 0; i < page_count; i++)
	{
		printf("-----------------------------\n");
		printf("Page: %i \nState: %3i \nAddress: %p\n", i, global_memory[i].state, global_memory[i].address);
		printf("Data: %s\n", (char*)global_memory[i].address);
		printf("-----------------------------\n");
	}
}