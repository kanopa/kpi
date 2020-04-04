#include "allocator.h"

mem_page* global_memory;
size_t page_count;

void initialise_memory(size_t size)
{
	global_memory = (mem_page*)calloc(memory, 1);
	page_count = size / (page_size + info);

	for (size_t i = 0; i < page_count; i++)
	{
		global_memory[i].address = (void*)((unsigned long int)global_memory + page_count * info + i * page_size);
		global_memory[i].size = 0;
		global_memory[i].state = 0;
	}

}

void* mem_alloc(size_t size)
{
	void* address;
	size_t counter = 0;
	size_t firstpage = 0;
	size_t pages_alloc = (double)size / page_size + 0.5;
	size_t i;
	if (size <= page_size)
	{
		for (i = 0; i < page_count; i++)
		{
			if (global_memory[i].state == 0 || (global_memory[i].state == 1 && size <= page_size / 2))
			{
				address = (void*)((size_t)(global_memory[i].address) + (global_memory[i].state == 1 ? page_size / 2 : 0));
				if (size > page_size / 2)
				{
					global_memory[i].state = 2;
					global_memory[i].size = 1;
				}
				else
				{
					global_memory[i].state = global_memory[i].state == 1 ? 2 : 1;
					global_memory[i].size = 0;
				}
				return address;
			}
		}
	}
	else
	{
		for (i = 0; i < page_count; i++)
		{
			if (global_memory[i].state == 0)
			{
				counter += 1;
				firstpage = i - counter + 1;
				if (counter == pages_alloc)
				{
					for (size_t j = firstpage; j < firstpage + counter; j++)
					{
						global_memory[j].state = 3;
					}
					global_memory[firstpage].size = pages_alloc * page_size;
					address = global_memory[firstpage].address;
					return address;
				}
			}
			else
			{
				counter = 0;
				firstpage = 0;
			}
		}
	}
	return NULL;
}

void* mem_realloc(void* address, size_t size)
{
	void* new_address;
	size_t size_of_block = (size_t)address - (size_t)global_memory - info * page_count;
	size_t started_block_page = size_of_block / page_size;
	if (global_memory[started_block_page].state == 3)
		size_of_block = global_memory[started_block_page].size;
	else if (global_memory[started_block_page].size != 0)
		size_of_block = global_memory[started_block_page].size * page_size;
	else
		size_of_block = page_size / 2;

	new_address = mem_alloc(size);
	if (new_address)
	{
		memmove(new_address, address, size_of_block > size ? size : size_of_block);
		mem_free(address);
		return new_address;
	}
	return NULL;
}

void mem_free(void* address)
{
	size_t size_of_block = (size_t)address - (size_t)global_memory - info * page_count;
	size_t started_block_page = size_of_block / page_size;
	size_t page_state = global_memory[started_block_page].state;
	if (page_state == 1)
		global_memory[started_block_page].state = 0;
	else if (page_state == 2)
	{
		if (global_memory[started_block_page].size == 0)
			global_memory[started_block_page].state = 1;
		else
			global_memory[started_block_page].state = 0;
	}

	else if (page_state == 3)
		for (size_t i = started_block_page; i < global_memory[started_block_page].size / page_size; i++)
			global_memory[i].state = 0;
	std::memset(address, 0, global_memory[started_block_page].size == 0 ? page_size / 2 : global_memory[started_block_page].size);
	global_memory[started_block_page].size = 0;
	address = NULL;


}