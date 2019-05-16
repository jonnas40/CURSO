#include <errno.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int count = 0;  
static void handler_parent(){ count++; printf("%d: Parent received signal / count: %d\n", getpid(), count); }

static void handler_child(){ printf("%d: Child received signal\n", getpid()); }

int main(int argc, char* argv[]) {
    
    pid_t pid;
    
    if (signal(SIGUSR1, handler_parent) == SIG_ERR){ 
        printf("signal error\n");
    }
    
    if (signal(SIGUSR2, handler_child) == SIG_ERR){
        printf("signal error\n");
    }

    if ((pid = fork()) < 0){
        printf("fork u\n");
    }
    else if (pid > 0) {
        /* parent’s code */
        //kill(pid, SIGUSR2);
        pause();
        pause();
        pause();
    }
    else {
        /* child’s code */
        kill(getppid(), SIGUSR1);
        sleep(1);
        kill(getppid(), SIGUSR1);
        sleep(1);
        kill(getppid(), SIGUSR1);
        //pause();
    }
}