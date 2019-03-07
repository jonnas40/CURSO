#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define MAX_STR_SIZE 64

int main(int argc, char* argv[]) {
    char* p1 = (char*)malloc(MAX_STR_SIZE * sizeof(char));
    char* p2 = (char*)malloc(MAX_STR_SIZE * sizeof(char));
    char* nor = (char*)malloc(MAX_STR_SIZE * sizeof(char));
    char* fir = (char*)malloc(MAX_STR_SIZE * sizeof(char));
    char* sec = (char*)malloc(MAX_STR_SIZE * sizeof(char));
    //char* oco = (char*)malloc(MAX_STR_SIZE * sizeof(char));
    char* test = (char*)malloc(MAX_STR_SIZE * sizeof(char));
    strcpy(nor, argv[1]);
    strcpy(fir, argv[1]);
    strcpy(sec, argv[2]);
    printf("first to lower case: ");
    for(int i = 0; i < strlen(nor); i++){
        putchar(tolower(nor[i]));
    }
    printf("\n");
    int counter = 0;
    //test = strdup(sec);
    if(strstr(sec, fir) != NULL){
        printf("contains\n");
        test = strstr(sec, fir);
        counter++;
        strcpy(test, &test[strlen(fir)]);
        while(strstr(test, fir) != NULL){
            counter++;
            test = strstr(sec, fir);
            strcpy(test, &test[strlen(fir)]);
        }
        printf("ocorre %d vezes\n", counter);
    }
    else
        printf("not contains\n");
    
    int result = strcmp(argv[1], argv[2]);
    if (result == 0)
        printf("the strings are the same\n");
    else if (result < 0)
        printf("%s < %s\n", argv[1], argv[2]);
    else    
        printf("%s > %s\n", argv[2], argv[1]);
    strcpy(p1, argv[1]);
    strcpy(p2, argv[2]);
    printf("p1 holds:%s\n", p1);
    printf("p2 holds:%s\n", p2);
    strcat(p1,p2);
    printf("p1 holds:%s\n", p1);
    strcat(p2,p1);
    printf("p2 holds:%s\n", p2);
    return EXIT_SUCCESS;
}