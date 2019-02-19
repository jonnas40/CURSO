#include<stdio.h>
#include<stdbool.h>

#define N 100

typedef struct group {
  int size;
  int max;
} Group;

void print_group(int p[], int id){
  int start = id;
  printf("%d ", start);
  while (p[id]!=start){
    printf("%d ", p[id]);
    id=p[id];
  }
}

Group find_group(int p[], int id, bool visited[]){
  Group g = {0, id};
  while (!visited[id]){
    visited[id]=true;
    if(id>g.max) g.max = id;
    g.size++;
    id=p[id];
  }
  return g;
}

int main() {
  int n, i;
  scanf("%d", &n);
  int p[N+1];
  bool visited [N+1];
  for (i=1; i<=n; i++){
    scanf("%d", &p[i]);
    visited [i]=false;
  }
  int ng = 0;
  Group g;
  for(i=1; i<=n; i++){
    g=find_group(p,i,visited);
    if(g.size>=3){
      printf("%d ", g.size);
      print_group(p, g.max);
      printf("\n");
    } else
      ng += g.size;
  }
  printf("%d\n", ng);
  return 0;
}
