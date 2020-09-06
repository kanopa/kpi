#include <iostream>
#include <cstring>

#define memory 1340
#define page_size 256
#define info 12


typedef struct s_page {
    void* address;
    char state;
    size_t size;
} mem_page;

void initialise_memory(size_t size);

void* mem_alloc(size_t size);

void* mem_realloc(void* address, size_t size);

void mem_free(void* address);

void mem_dump();

extern mem_page* global_memory;

extern size_t page_count;
