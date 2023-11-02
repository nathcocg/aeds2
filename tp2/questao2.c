#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define max 100
#define MAX 100000
#define playersMAX 3922

struct players{
    int id;
    char name[max];
    int height;
    int weight;
    char college[max];
    int birth;
    char city[max];
    char state[max];
};

struct players start_player(){
    struct players p;
    p.id = -1;
    strcpy(p.name, "");
    p.height = -1;
    p.weight = -1;
    strcpy(p.college, "");
    p.birth = -1;
    strcpy(p.city, "");
    strcpy(p.state, "");
    return p;
}

void read(struct players *p, char line[]){//le uma linha do arquivo e a atribui a um jogador através do seu ponteiro
   int comma[8];
    int j = 0;
    for(int i = 0; i<strlen(line); i++){
        if(line[i] == ','){
            comma[j] = i;
            j++;
        }
    }
    p->id = atoi(line);
    strncpy(p->name, line + comma[0] + 1, comma[1]-comma[0]-1);
    p->name[comma[1] - comma[0] - 1] = '\0';
    p->height = atoi(line + comma[1]+1);
    p->weight = atoi(line + comma[2]+1);
    strncpy(p->college, line + comma[3]+1, comma[4]-comma[3]-1);
    p->college[comma[4]-comma[3]-1] = '\0';
    p->birth = atoi(line + comma[4] + 1);
    strncpy(p->city, line + comma[5] + 1, comma[6] - comma[5] - 1);
    p->city[comma[6] - comma[5] - 1] = '\0';
    if(strlen(line)-comma[6]==2){
        strncpy(p->state, "nao informado", 13);
    } else {
        strncpy(p->state, line + comma[6] + 1, strlen(line) - comma[6] - 1);
        p->state[strlen(line) - comma[6] - 1] = '\0';
    }
}

void placing_players(struct players p[]){ //le o arquivo, cria um jogador para cada linha e atribui suas características
    FILE *arq;
    arq = fopen("players.csv", "r");
    char line[MAX];
    fgets(line, MAX, arq);//le a primeira linha e ignora
    char *f = fgets(line, MAX, arq);
    int i = 0;
    while (f != NULL && i < playersMAX) {//enquanto houver linhas para serem lidas e não chegar ao total máximo de jogadores presentes no arquivo (3922)
        p[i] = start_player();//cria um jogador para a linha
        read(&p[i], line); //le a linha e atribui seus valores ao jogador criado
        i++;//passa para o próximo jogador vazio
        f = fgets(line, MAX, arq);//le a proxima linha e repete o processo
    }
    fclose(arq);
}

void print(struct players p[], int i){
    printf("[");
    if(p[i].id == -1){
        printf("nao informado");
    } else {
        printf("%d", p[i].id);
    }
    printf(" ## ");
    if(strcmp(p[i].name, "")==0){
        printf("nao informado");
    } else{
        printf("%s", p[i].name);
    }
    printf(" ## ");
    if(p[i].height == -1){
        printf("nao informado");
    } else {
        printf("%d", p[i].height);
    }
    printf(" ## ");
    if(p[i].weight == -1){
        printf("nao informado");
    } else {
        printf("%d", p[i].weight);
    }
    printf(" ## ");
    if(p[i].birth == -1){
        printf("nao informado");
    } else {
        printf("%d", p[i].birth);
    }
    printf(" ## ");
    if(strcmp(p[i].college, "")==0){
        printf("nao informado");
    } else{
        printf("%s", p[i].college);
    }
    printf(" ## ");
    if(strcmp(p[i].city, "")==0){
        printf("nao informado");
    }else{
        printf("%s", p[i].city);
    }
    printf(" ## ");
    if(strcmp(p[i].state, "")==0){
        printf("nao informado");
    }else{
        for(int cont=0;cont<strlen(p[i].state);cont++){
          if(p[i].state[cont]=='\n'){
            cont=strlen(p[i].state);
          } else{
            printf("%c",p[i].state[cont]);
          }
        }
    }
    printf("]\n");
}

struct players clone(struct players p){
    struct players c;
    c.id = p.id;
    strcpy(c.name, p.name);
    c.height = p.height;
    c.weight = p.weight;
    c.birth = p.birth;
    strcpy(c.college, p.college);
    strcpy(c.city, p.city);
    strcpy(c.state, p.state);
    return c;
}

bool isEnd(char entry[MAX]){
    return entry[0]=='F'&&entry[1]=='I'&&entry[2]=='M'&&entry[3]=='\0';
}

int main(int argc, char *argv[]){
    struct players players[playersMAX];//₢ria um vetor para TODOS os jogadores do arquivo
    placing_players(players);//cria um jogador com base em uma linha do arquivo para cada posição do vetor
    char entry [MAX];
    scanf(" %[^\n]",entry);//le a primeira linha válida do arquivo
    while(!isEnd(entry)){
        int j = atoi(entry);
        print(players, j);
        scanf(" %[^\n]",entry);
    }
    return 0;
}