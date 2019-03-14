#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char* argv[]) {

    struct stat info;
    int i=1;
    while(argv[i] != NULL){
        if (argc < 1) {
            fprintf(stderr, "usage: %s file\n", argv[0]);
            return EXIT_FAILURE;
        }
        
        if (lstat(argv[i], &info) == -1) {
            fprintf(stderr, "fsize: Can’t stat %s\n", argv[i]);
            return EXIT_FAILURE;
        }
        
        printf("%s size: %d bytes, disk_blocks: %d, owner: %d, last changed: %s\n", argv[i], (int)info.st_size, (int)info.st_blocks, info.st_uid, ctime(&info.st_mtime));

        i++;
    }

    return EXIT_SUCCESS;

}