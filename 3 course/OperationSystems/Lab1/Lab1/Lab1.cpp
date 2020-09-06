#include <stdio.h>
#include "allocator.h"
#include "output.h"

int main(int argc, char* argv[]) {
    printf("Allocate memory pool.\n");
    printf("Result: %d\n", mem_pool_alloc(1536));
    printf("Result: %d\n", mem_pool_alloc(0));
    mem_dump();

    // Allocate blocks.
    printf("Allocate memory block #1.\n");
    void* arr1 = mem_alloc(100 * sizeof(int));

    mem_dump();

    printf("Allocate memory block #2.\n");
    void* arr2 = mem_alloc(100 * sizeof(int));

    mem_dump();

    printf("Allocate memory block #3.\n");
    void* arr3 = mem_alloc(100 * sizeof(int));

    mem_dump();

    printf("Allocate memory block #4.\n");
    void* arr4 = mem_alloc(100 * sizeof(int));
    mem_dump();

    printf("Realloc memory block #1\n");
    void* arr1_1 = mem_realloc(arr1, 92 * sizeof(int));
    printf("Realloc memory block #2\n");
    void* arr2_1 = mem_realloc(arr2, 101 * sizeof(int));
    printf("Realloc memory block #3\n");
    void* arr3_1 = mem_realloc(arr3, 101 * sizeof(int));

    if (arr1_1 == NULL || arr2_1 == NULL || arr3_1 == NULL) {
        perror("Relloc failed.");
    }

    if (arr1_1 != NULL) {
        printf("Free memory block #3.\n");
        mem_free(arr1_1);
        mem_dump();
    }

    if (arr3_1 != NULL) {
        printf("Free memory block #1.\n");
        mem_free(arr3_1);
        mem_dump();
    }

    if (arr2_1 != NULL) {
        printf("Free memory block #2.\n");
        mem_free(arr2_1);
        mem_dump();
    }


    printf("Free memory pool.\n");
    mem_pool_free();
    mem_pool_free();

    return 0;
}
