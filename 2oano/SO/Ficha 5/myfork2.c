#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    int pid =0;

    for (int i = 0; i < 4; i++){
        if ((pid = fork()) < 0){ printf("Fork failed\n"); }
        else if (pid == 0){
            printf("Son id: %d Parent id: %d\n", getpid(), getppid());
        }
        else {
            printf("%d ded\n", getpid());
        }
    }

    
    
    return EXIT_SUCCESS;
}