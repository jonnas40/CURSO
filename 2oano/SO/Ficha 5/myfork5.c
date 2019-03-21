#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>

#define DELIM  " "

int main(int argc, char* argv[]) {
    char buf[1024];
    char* command;
    pid_t pid;
    char* hist[100];
    
    /* do this until you get a ^C or a ^D */
    for( ; ; ) {
        int h=0;
        
        /* give prompt, read command and null terminate it */
        fprintf(stdout, "$ ");
        if((command = fgets(buf, sizeof(buf), stdin)) == NULL)
            break;
        command[strlen(buf) - 1] = '\0';

        /* call fork and check return value */
        if((pid = fork()) < 0) {
            fprintf(stderr, "%s: can’t fork command: %s\n",
            argv[0], strerror(errno));
            continue;
        } else if(pid == 0) {
            /* child */
            char* args[16];
            int i = 0;
            args[i] = strtok(command, DELIM);
            if ( (strcmp(args[i],"exit"))==0 ) return 0;
            while( (args[++i] = strtok(NULL, DELIM)) != NULL)
               ;
            if (execvp(args[0], args) < 0){
                /* if I get here "execvp" failed */
                fprintf(stderr, "%s: couldn’t exec %s: %s\n", argv[0], buf, strerror(errno));
                /* terminate with error to be caught by parent */
                exit(EXIT_FAILURE);
            }
            strcpy(hist[h], args[0]);
            strcat(hist[h], args);
            h++;
        }
        
        /* shell waits for command to finish before giving prompt again */
        if ((pid = waitpid(pid, NULL, 0)) < 0)
            fprintf(stderr, "%s: waitpid error: %s\n",argv[0], strerror(errno));
    }
    exit(EXIT_SUCCESS);
}