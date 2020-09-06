#include "allocator.h"

int main()
{
    initialise_memory(memory);

    void* data = mem_alloc(300);
    memmove(data, "Block #1", info);

    void* data1 = mem_alloc(200);
    memmove(data1, "Block #2", info);

    void* data2 = mem_alloc(200);
    memmove(data2, "Block #3", info);

    void* data3 = mem_alloc(600);
    memmove(data3, "Block #4", info);

    mem_dump();
    mem_realloc(data2, 100);
    mem_free(data);
    mem_dump();
    return 0;
}