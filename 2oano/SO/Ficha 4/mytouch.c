#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/uio.h>

int main(int argc, char* argv[]) {

    if (argc != 2) {
        fprintf(stderr, "usage: %s file\n", argv[0]);
        return EXIT_FAILURE;
    }
        
    FILE *fp;
    fp = fopen(argv[1], "w");
    if(fp!=NULL){

    }
    else{
        open(argv[1], O_RDWR|O_CREAT, 0644);
        printf("File created\n");
    } 

    return EXIT_SUCCESS;

}