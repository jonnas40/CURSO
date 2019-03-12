#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define MAX_STR_SIZE 64

int main(int argc, char const *argv[]){
    FILE *fp;
    fp = fopen(argv[1], "r");
    if(fp!=NULL){
        fscanf(fp, "Some String\n", &var);
        printf("%s\n", );
        
    }
    return EXIT_SUCCESS;
}
