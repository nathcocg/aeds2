/*palindromo: palavra ou frase que pode ser lido da esquerda para a direita ou vice-versa*/
//se chegar na palavra FIM, tem que parar
//qnd houve um \n, o programa lea próxima palavra
//todas as letras devem ser minusculas ou maiusculas para ser palindromo
//qnd o programa le a palavra "fim", ele para de funcionar

#include <stdio.h>
#include <string.h>
#include <stdlib.h>


int main(){
    char entrada[1000];
    char *end;
    int n, i, verifica = 1;
    do{
        int v = 0;
        fgets(entrada, sizeof(entrada), stdin); //stdin: dispositivo de entrada padrão (normalmente, o teclado -> ver como trocar para a entarda pronta)
        if((end = strchr(entrada, '\n'))!= NULL){ // verifica se o caractere \n está presente na string lida
            *end = 0; // substitui o \n da string por 0, para garantir que a string foi encerrada corretamente
        }
        n = strlen(entrada);
        if(strcmp(entrada, "FIM")==0){
                //verifica = 0;
                return 0;
        }
        for(i = 0; i< n; i++){
            if(entrada[i] != entrada[n-i-1]){
                v = 1;
            }
        }
        if(v == 0){
            printf("SIM\n");
        }
        else {
            printf("NAO\n");
        }
    }while(verifica != 0);
}
