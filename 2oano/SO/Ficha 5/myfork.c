#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
int main(int argc, char* argv[]) {
    int pid;

    if ((pid = fork()) < 0){ 
        printf("Fork failed\n"); 
    }
    else if (pid == 0){
        printf("Son id: %d Parent id: %d\n", getpid(), getppid());
    }
    else {
        printf("Parent\n");
    }

    if ((pid = fork()) < 0){ 
        printf("Fork failed\n"); 
    }
    else if (pid == 0){
        printf("Son id: %d Parent id: %d\n", getpid(), getppid());
    }
    else {
        printf("Parent\n");
    }


    if ((pid = fork()) < 0){ 
        printf("Fork failed\n"); 
    }
    else if (pid == 0){
        printf("Son id: %d Parent id: %d\n", getpid(), getppid());
    }
    else {
        printf("Parent\n");
    }

    return EXIT_SUCCESS;
}