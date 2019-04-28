#include <sys/wait.h>
#include <sys/socket.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#define SOCK0 0
#define SOCK1 1
#define DATA0 "In every walk with nature..."
#define DATA1 "...one receives far more than he seeks."
/* by John Muir */

void upper_string(char []);

int main(int argc, char* argv[]) {
    int sockets[2];
    char buf[1024];
    pid_t pid;
    char line[1024];
    char dummy[1024];

    
    if (socketpair(AF_UNIX, SOCK_STREAM, 0, sockets) < 0) {
        perror("opening stream socket pair");
        exit(1);
    }
    
    if ((pid = fork()) < 0) {
        perror("fork");
        return EXIT_FAILURE;
    }
    else if (pid == 0) {
        /* this is the child */
        close(sockets[SOCK0]);
        
        if (read(sockets[SOCK1], buf, sizeof(buf)) < 0)
            perror("reading stream message");
       
        printf("message from %d-->%s\n", getppid(), buf);
        
        
        FILE *fp;
        fp = fopen(argv[1], "r");
        if(fp!=NULL){
            while(fscanf(fp, "%s", dummy)!=EOF){
                strcat(line, " ");
                strcat(line, dummy);
            }
            strcat(line, "\n");
            fclose(fp);
        }

        if (write(sockets[SOCK1], line, sizeof(line)) < 0)
            perror("writing stream message");
        
        close(sockets[SOCK1]);
        /* leave gracefully */
        return EXIT_SUCCESS;
    }
    else {
        /* this is the parent */
        
        close(sockets[SOCK1]);
        
        if (write(sockets[SOCK0], DATA0, sizeof(DATA0)) < 0)
            perror("writing stream message");
        
        if (read(sockets[SOCK0], buf, sizeof(buf)) < 0)
            perror("reading stream message");

        upper_string(buf);
        
        printf("message from %d-->%s\n", pid, buf);
        close(sockets[SOCK0]);
        /* wait for child and exit */
        
        if (waitpid(pid, NULL, 0) < 0) {
            perror("did not catch child exiting");
            return EXIT_FAILURE;
        }
        return EXIT_SUCCESS;
    }
}

void upper_string(char s[]) {
   int c = 0;
   
   while (s[c] != '\0') {
      if (s[c] >= 'a' && s[c] <= 'z') {
         s[c] = s[c] - 32;
      }
      c++;
   }
}