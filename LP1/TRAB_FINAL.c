//====================================================================================
//                        TRABALHO DE LP1 -- MESAS E PEDIDOS
// ALUNO: LUCAS DUNHÃO DE CARVALHO
//
//FOI UTILIZADO O COMANDO SYSTEM("CLS") PARA LIMPAR A TELA
//SE FOR UTILIZAR O LINUX TROCAR POR SYSTEM("CLEAR")
//FEITO NO 
//====================================================================================
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define LIMPAR_TELA system("cls"); //TROCAR PARA "CLEAR" CASO SEJA LINUX,"CLS" SE FOR WINDOWS.
//=================================================================================
// STRUCTS
    //VETOR 1---------------------
    typedef struct vet_cardapio{
        int cod_prt;
        int cod_beb;
        char nome_prato[20];
        char nome_bebida[20];
        double preco_prato;
        double preco_bebida;
    }Cardapio;
    //VETOR 2---------------------
    typedef struct vet_garcom{
        int cod_gar;
        char nome_garcom[20];
    }Garcom;

    //LISTA DOS GARÇONS 
    typedef struct list_gar{
        int cod_gar;
        char nome_garcom[20];
        struct list_gar *prox;
    }list_gar;

    //LISTA DAS BEBIDAS
    typedef struct list_beb{
        int cod_beb;
        char nome_bebida[20];
        double preco_bebida;
        struct list_beb *prox;
    }list_beb;

    //LISTA DOS PRATOS
    typedef struct list_prt{
        int cod_prt;
        char nome_prato[20];
        double preco_prato;
        struct list_prt *prox;
    }list_prt;


    // LISTA --> MESAS
    typedef struct Mesas{
        int num_mesa;
        int cod_gar;
        double tot_ped;
        struct Mesas *ant;
        struct Mesas *prox;
        struct Pedidos *pont;
        
    } mesas;

    // LISTA --> PEDIDOS 
    typedef struct Pedidos{
        int cod_ped;
        int qtd_ped;
        struct Pedidos *prox;
    }pedidos;
//==================================================================================
// FUNÇÕES QUE UTILIZAREI
/*

*/
// ##########################-->BLOCO DAS FUNÇÕES<--#################################


//++++++++++++++++++++++++++++++ FUNÇÕES DE BUSCA +++++++++++++++++++++++++++++++++++
//VERIFICAR SE A MESA EXISTE
int Busca_Mesas(mesas **inicio_mesas,int n){
    int bool=0;
    mesas *aux;
    aux=*inicio_mesas;
    while(aux!=NULL){
        if(aux->num_mesa==n){
            bool = 1;
            break;
        }else{
            aux=aux->prox;
        }
        
    }
    return bool;
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//+++++++++++++++++++++++++++++++++ FUNÇÕES DE IMPRIMIR +++++++++++++++++++++++++++++++
//IMPRIMIR OS PRATOS
void Imprime_pratos(list_prt **pnt){
    list_prt *aux;
    aux=*pnt;
    printf(" ------------>  Cardapio dos Pratos  <-----------------\n");
    while(aux!=NULL){
        printf("-------------------------------\n");
        printf("Nome: %s ---- Cod: %d ---- Valor: R$ %1.2f",aux->nome_prato,aux->cod_prt,aux->preco_prato);
        printf("\n");
        printf("\n");
        aux=aux->prox;
    }
    printf("---------------------------------------------------------\n");
}
//IMPRIMIR AS BEBIDAS
void Imprime_bebidas(list_beb **pnt){
    list_beb *aux;
    aux=*pnt;
    printf(" ------------>  Cardapio das Bebidas  <-----------------\n");
    while(aux!=NULL){
        printf("-------------------------------\n");
        printf("Nome: %s ---- Cod: %d ---- Valor: R$ %1.2f",aux->nome_bebida,aux->cod_beb,aux->preco_bebida);
        printf("\n");
        printf("\n");
        aux=aux->prox;
    }
    printf("---------------------------------------------------------\n");
}
//IMPRIME OS GARÇONS
void Imprime_Garcons(list_gar **pnt){
    list_gar *aux;
    aux=*pnt;
    printf(" ------------>  Lista dos Garcons  <-----------------\n");
    while(aux!=NULL){
        printf("-------------------------------\n");
        printf("Nome: %s ---- Cod: %d ",aux->nome_garcom,aux->cod_gar);
        printf("\n");
        printf("\n");
        aux=aux->prox;
    }
    printf("---------------------------------------------------------\n");
}


double Nota_fiscal(pedidos **inicio,list_beb **inicio_beb,list_prt **inicio_prt){
    pedidos *aux;
    list_beb *auxB;
    list_prt *auxP;
    aux=*inicio;
    auxB=*inicio_beb;
    auxP=*inicio_prt;
    double soma=0;
    int p=0;
    
    while(aux!=NULL){
        
        aux=aux->prox;
        p++;
        
    }
    printf("Numero de pedidos: %d\n",p);
    aux=*inicio;
    printf("---------------> Pedidos Feitos <--------------\n");
    while(aux!=NULL){
        
        if(aux->cod_ped<11){    
            while(auxP!=NULL){
                
                if(aux->cod_ped==auxP->cod_prt){
                    printf("--> Nome do prato: %s\n",auxP->nome_prato);
                    soma+= auxP->preco_prato;
                    break;
                }
                auxP=auxP->prox;
            }
        }    
        if(aux->cod_ped>10){
            while(auxB!=NULL){
                if(aux->cod_ped==auxB->cod_beb){
                    printf("--> Nome da bebida: %s\n",auxB->nome_bebida);
                    soma+= auxB->preco_bebida;
                    break;
                }
                auxB=auxB->prox;
            }
        }     

        aux=aux->prox;
    }
        
        
    return soma;   
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//LISTA DAS BEBIDAS
void Insere_bebidas(list_beb **inicio_beb,int cod,char nome[10],double valor){
    list_beb *aux,*novo;
    aux=*inicio_beb;
    novo=malloc(sizeof(list_beb));
    if(aux==NULL){
        novo->prox=NULL;
        novo->cod_beb=cod;
        strcpy(novo->nome_bebida,nome);
        novo->preco_bebida=valor;
        *inicio_beb=novo;
    }else{
        novo->prox=*inicio_beb;
        novo->cod_beb=cod;
        strcpy(novo->nome_bebida,nome);
        novo->preco_bebida=valor;
        *inicio_beb=novo;

    }
}

//LISTA DOS PRATOS
void Insere_pratos(list_prt **inicio_prt,int cod,char nome[],double valor){
    list_prt *aux,*novo;
    aux=*inicio_prt;
    novo=malloc(sizeof(list_prt));
    if(aux==NULL){
        novo->prox=NULL;
        novo->cod_prt=cod;
        strcpy(novo->nome_prato,nome);
        novo->preco_prato=valor;
        *inicio_prt=novo;
    }else{
        novo->prox=*inicio_prt;
        novo->cod_prt=cod;
        strcpy(novo->nome_prato,nome);
        novo->preco_prato=valor;
        *inicio_prt=novo;

    }
}

//LISTA DOS GARÇONS
void Insere_garcom(list_gar **inicio_gar, int cod,char nome[]){
    list_gar *aux,*novo;
    aux=*inicio_gar;
    novo=malloc(sizeof(list_gar));
    if(aux==NULL){
        novo->prox=NULL;
        novo->cod_gar=cod;
        strcpy(novo->nome_garcom,nome);
        *inicio_gar=novo;
    }else{
        novo->prox=*inicio_gar;
        novo->cod_gar=cod;
        strcpy(novo->nome_garcom,nome);
        *inicio_gar=novo;

    }
}


//++++++++++++++++++++++++++++++ FUNÇÕES DE INSERIR +++++++++++++++++++++++++++++
//INSERIR AS MESAS
void Insere_Mesas(mesas **inicio_mesas,int num, int cod){
    mesas *novo;
    mesas *aux,*pAnt;
    aux=*inicio_mesas;
    novo=malloc(sizeof(mesas));

    if(*inicio_mesas==NULL){
        novo->ant=NULL;
        novo->prox=NULL;
        novo->num_mesa=num;
        novo->cod_gar=cod;
        novo->pont=NULL;
        *inicio_mesas=novo;
    }else{
        while(aux!=NULL && aux->num_mesa<num){
        pAnt = aux;
        aux=aux->prox;
        }
        if(aux==*inicio_mesas){
            novo->ant=NULL;
            aux->ant=novo;
            novo->prox=aux;
            novo->num_mesa=num;
            novo->cod_gar=cod;
            novo->pont=NULL;
            *inicio_mesas=novo;
        }else{
            novo->prox=pAnt->prox;
            novo->ant=pAnt;
            pAnt->prox=novo;
            if(aux!=NULL){
                aux->ant=novo;
            }
            novo->num_mesa=num;
            novo->cod_gar=cod;
            novo->pont=NULL;     
        }
    }
}


//INSERIR PEDIDOS
int Pedido(mesas **inicio_mesas,int n,list_beb **Pb,list_prt **Pp){
    mesas *aux;
    pedidos *novo;
    list_beb *Pbeb;
    list_prt *Pprt;
    Pbeb=*Pb;
    Pprt=*Pp;
    int i=0,escolha=0;
    int prt=0,beb=0;
    aux=*inicio_mesas;
    novo = malloc(sizeof(pedidos));
    while(aux!=NULL){
        if(aux->num_mesa==n){
            printf("Sua mesa foi encontrada\n");
            printf("\n");
            printf("----------------\n");
            i = 1;
            break;
        }else{
            aux=aux->prox;
        }
    }
    if(i==1){
        if(aux->pont==NULL){
            printf("Qual o seu pedido?\n");
            printf("1 - Pratos\n");
            printf("2 - Bebidas\n");
            scanf("%d",&escolha);
            printf("-------------------------\n");
            if(escolha!=1 && escolha!=2){
                return 0;
                printf("Opçao invalida\n");
            }
            if(escolha==1){
                printf("-------------------------\n");
                printf("Codigo entre 1 e 10\n");
                printf("Digite o codigo do prato: ");
                scanf("%d",&prt);
                printf("-------------------------\n");
                if(prt>10){
                    printf("Pedido invalido\n");
                    return 0;
                }
                while(Pprt!=NULL){
                    if(Pprt->cod_prt==prt){
                        novo->cod_ped=Pprt->cod_prt;
                        printf("Prato Escolhido: %s\n ",Pprt->nome_prato);
                        break;
                    }else{
                        Pprt=Pprt->prox;
                    }
                } 
            }
            if(escolha==2){
                printf("-------------------------\n");
                printf("Codigo entre 11 e 20\n");
                printf("Digite o codigo da bebida: ");
                scanf("%d",&beb);
                printf("-------------------------\n");
                if(beb<11){
                    printf("Pedido invalido\n");
                    return 0;
                }
                while(Pbeb!=NULL){
                    if(Pbeb->cod_beb==beb){
                        novo->cod_ped=Pbeb->cod_beb;
                        printf("Bebida escolhida: %s\n ",Pbeb->nome_bebida);
                        break;
                    }else{
                        Pbeb=Pbeb->prox;
                    }
                } 
            }
            novo->qtd_ped=1;
            aux->pont=novo;
            novo->prox=NULL;

        }else{
            printf("Qual o seu pedido?\n");
            printf("1 - Pratos\n");
            printf("2 - Bebidas\n");
            scanf("%d",&escolha);
            printf("-------------------------\n");
            if(escolha==1){
                printf("-------------------------\n");
                printf("Codigo entre 1 e 10\n");
                printf("Digite o codigo do prato: ");
                scanf("%d",&prt);
                printf("-------------------------\n");
                if(prt>10){
                    printf("Pedido invalido\n");
                    return 0;
                }
                while(Pprt!=NULL){
                    if(Pprt->cod_prt==prt){
                        novo->cod_ped=prt;
                        printf("Prato Escolhido: %s\n ",Pprt->nome_prato);
                        break;
                    }else{
                        Pprt=Pprt->prox;
                    }
                } 
            }
            if(escolha==2){
                printf("-------------------------\n");
                printf("Codigo entre 11 e 20\n");
                printf("Digite o codigo da bebida: ");
                scanf("%d",&beb);
                printf("-------------------------\n");
                if(beb<11){
                    printf("Pedido invalido\n");
                    return 0;
                }
                while(Pbeb!=NULL){
                    if(Pbeb->cod_beb==beb){
                        novo->cod_ped=beb;
                        printf("Bebida escolhida: %s\n ",Pbeb->nome_bebida);
                        break;
                    }else{
                        Pbeb=Pbeb->prox;
                    }
                } 
            }
            novo->qtd_ped=1;
            novo->prox=aux->pont;
            aux->pont=novo;
            
        }
        printf("\n");
        printf("Pedido computado!\n");
        printf("\n");
    }else{
        printf("=======================\n");
        printf("Mesa nao encontrada\n");
        printf("=======================\n");
    }
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


//+++++++++++++++++++--> FUNÇÕES DE DELEÇÃO <--++++++++++++++++++++++++++
//FUNÇÃO DE FECHAR CONTA
int Deletar_Mesas(mesas **inicio_mesas,list_gar **inicio_gar,list_beb **inicio_beb,list_prt **inicio_prt){
    mesas *aux,*ant,*deleta;
    list_gar *gar;
    aux=*inicio_mesas;
    ant=*inicio_mesas;
    deleta=*inicio_mesas;
    
    gar=*inicio_gar;
    

    int i=0,n=0,j=0,p=1;
    double soma=0;
    printf("Digite o numero da mesa: ");
    scanf("%d",&n);
    while(aux!=NULL){
        if(aux->num_mesa==n){
            i=1;
            break;
        }else{
            aux=aux->prox;
        }
    }
    if(i==0){
        printf("Mesa nao encontrada.\n");
        return 0;
    }
    
    
    if(i==1){
        
        while(gar!=NULL){
            if(aux->cod_gar==gar->cod_gar){
                printf("--> Nome do garcom: %s\n",gar->nome_garcom);
                break;
            }else{
                gar=gar->prox;
            }
        }

        soma = Nota_fiscal(&aux->pont,inicio_beb,inicio_prt);
        
        aux->tot_ped=soma;
        printf("Valor total: %1.2f",aux->tot_ped);
        printf("\n");
        printf("------------------------------\n");
        if(aux->ant==NULL){
            *inicio_mesas=aux->prox;
        }else{
            aux->ant->prox=aux->prox;
        }
        if(aux->prox!=NULL){
            aux->prox->ant=aux->ant;
        }
        free(aux);
    }
    return 0;
}
void Deletar_Tudo(mesas **inicio_mesas){
    mesas *aux,*deleta;
    aux = *inicio_mesas;
    deleta = aux;
    
    while (aux!=NULL)
    {
        aux=aux->prox;
        free (deleta);
        deleta = aux;
    }
    printf("----->  Lista das mesas apagada\n");
}
void Deletar_List_prt(list_prt **inicio_prt){
    list_prt *aux,*deleta;
    aux = *inicio_prt;
    deleta = aux;
    
    while (aux!=NULL)
    {
        aux=aux->prox;
        free (deleta);
        deleta = aux;
    }
    printf("----->  Lista de pratos apagada\n");
}
void Deletar_List_beb(list_beb **inicio_beb){
    list_beb *aux,*deleta;
    aux = *inicio_beb;
    deleta = aux;
    
    while (aux!=NULL)
    {
        aux=aux->prox;
        free (deleta);
        deleta = aux;
    }
    printf("----->  Lista de bebidas apagada\n");
}
void Deletar_List_gar(list_gar **inicio_gar){
    list_gar *aux,*deleta;
    aux = *inicio_gar;
    deleta = aux;
    
    while (aux!=NULL)
    {
        aux=aux->prox;
        free (deleta);
        deleta = aux;
    }
    printf("----->  Lista de garcons apagada\n");
}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//#########################-->PROGRAMA PRINCIPAL<--##################################
int main(){

    //INICIALIZANDO AS LISTAS
    mesas *inicio_mesas=NULL;
    list_beb *inicio_beb=NULL;
    list_gar *inicio_gar=NULL;
    list_prt *inicio_prt=NULL;
    
    
    Cardapio cardapio[10];
    Garcom garcom[10];
    //===================================================================================
    // ATRIBUICAOO DOS VALORES DOS VETORES
    // VETOR FOI USADO APENAS PARA SER MAIS FACIL ESCREVER NOS ARQUIVOS.
    // TODAS AS FUNCOES ENVOLVENDO AS COMIDAS E GARCONS FORAM COM ARQUIVO OU LISTAS ENCADEADAS. 
    // ---->Vetor 1<----
    cardapio[0].cod_prt=1;cardapio[0].cod_beb=11;strcpy(cardapio[0].nome_prato,"Frango");strcpy(cardapio[0].nome_bebida, "Coca-Cola");cardapio[0].preco_prato=10.00;cardapio[0].preco_bebida=3.00;
    cardapio[1].cod_prt=2;cardapio[1].cod_beb=12;strcpy(cardapio[1].nome_prato,"Carne");strcpy(cardapio[1].nome_bebida, "Guarana");cardapio[1].preco_prato=8.50;cardapio[1].preco_bebida=3.00;
    cardapio[2].cod_prt=3;cardapio[2].cod_beb=13;strcpy(cardapio[2].nome_prato,"Peixe");strcpy(cardapio[2].nome_bebida, "Itaipava");cardapio[2].preco_prato=15.00;cardapio[2].preco_bebida=5.00;
    cardapio[3].cod_prt=4;cardapio[3].cod_beb=14;strcpy(cardapio[3].nome_prato,"Pizza");strcpy(cardapio[3].nome_bebida, "Vinho");cardapio[3].preco_prato=17.20;cardapio[3].preco_bebida=8.00;
    cardapio[4].cod_prt=5;cardapio[4].cod_beb=15;strcpy(cardapio[4].nome_prato,"Hamburguer");strcpy(cardapio[4].nome_bebida, "Soda");cardapio[4].preco_prato=21.30;cardapio[4].preco_bebida=3.00;
    cardapio[5].cod_prt=6;cardapio[5].cod_beb=16;strcpy(cardapio[5].nome_prato,"Sushi");strcpy(cardapio[5].nome_bebida, "Suco Natural");cardapio[5].preco_prato=30.00;cardapio[5].preco_bebida=4.00;
    cardapio[6].cod_prt=7;cardapio[6].cod_beb=17;strcpy(cardapio[6].nome_prato,"Salada");strcpy(cardapio[6].nome_bebida, "Brahma");cardapio[6].preco_prato=13.95;cardapio[6].preco_bebida=5.00;
    cardapio[7].cod_prt=8;cardapio[7].cod_beb=18;strcpy(cardapio[7].nome_prato,"Costela");strcpy(cardapio[7].nome_bebida, "Milk Shake");cardapio[7].preco_prato=19.50;cardapio[7].preco_bebida=10.00;
    cardapio[8].cod_prt=9;cardapio[8].cod_beb=19;strcpy(cardapio[8].nome_prato,"Batata Frita");strcpy(cardapio[8].nome_bebida, "Ice Tea");cardapio[8].preco_prato=12.35;cardapio[8].preco_bebida=3.50;
    cardapio[9].cod_prt=10;cardapio[9].cod_beb=20;strcpy(cardapio[9].nome_prato,"Cebola Frita");strcpy(cardapio[9].nome_bebida, "Mate");cardapio[9].preco_prato=12.00;cardapio[9].preco_bebida=3.00;
    //---->Vetor 2<----
    garcom[0].cod_gar=0;strcpy(garcom[0].nome_garcom, "Joao");
    garcom[1].cod_gar=1;strcpy(garcom[1].nome_garcom, "Julio");
    garcom[2].cod_gar=2;strcpy(garcom[2].nome_garcom, "Matheus");
    garcom[3].cod_gar=3;strcpy(garcom[3].nome_garcom, "Lucas");
    garcom[4].cod_gar=4;strcpy(garcom[4].nome_garcom, "Ricardo");
    garcom[5].cod_gar=5;strcpy(garcom[5].nome_garcom, "Leonardo");
    garcom[6].cod_gar=6;strcpy(garcom[6].nome_garcom, "Gabriel");
    garcom[7].cod_gar=7;strcpy(garcom[7].nome_garcom, "Eduardo");
    garcom[8].cod_gar=8;strcpy(garcom[8].nome_garcom, "Jose");
    garcom[9].cod_gar=9;strcpy(garcom[9].nome_garcom, "Marcio");
    //===================================================================================
    
    
    //===================================================================================
    //CRIANDO OS ARQUIVO DOS GARÇONS E DAS COMIDAS 
    FILE *arq_car;
    arq_car=fopen("cardapio.bin","wb");
    FILE *arq_gar;
    arq_gar=fopen("garçom.bin","wb");
    int i=0;
    for(i==0;i<=9;i++){
        
        fwrite(&cardapio[i],sizeof(Cardapio),1,arq_car);
        Insere_pratos(&inicio_prt,cardapio[i].cod_prt,cardapio[i].nome_prato,cardapio[i].preco_prato);
        Insere_bebidas(&inicio_beb,cardapio[i].cod_beb,cardapio[i].nome_bebida,cardapio[i].preco_bebida);
        fwrite(&garcom[i],sizeof(Garcom),1,arq_gar);
        Insere_garcom(&inicio_gar,garcom[i].cod_gar,garcom[i].nome_garcom);
    }
    
    
    fclose(arq_car);
    fclose(arq_gar);
    //===================================================================================
    
    


    
    
    



    //===================================================================================
    
    int escolha;
    // VARIÁVEIS DO CASE 1
    int case1_mesa=0,case1_gar=0;
    // VARIÁVEIS DO CASE 2
    int case2=0;
    // VARIÁVEIS DO CASE 3
    int case3=0,case3_P_B=0,case3_escolha_cod=0,case3_mesa=0,case3_gar=0;
    //VARIÁVEIS DO CASE 5
    int case5_escolha=0;
    
    
    
    
    
    

    //----------------------------
    //OPÇÕES DE ESCOLHA-----------
    //----------------------------
    printf("O que deseja?\n");
    printf("1 - Uma mesa.\n");
    printf("2 - Fechar a conta.\n");
    printf("3 - Fazer um pedido.\n");
    printf("4 - Lista de garcons.\n");
    printf("5 - Olhar o cardapio.\n");
    printf("6- Sair.\n");
    printf("=================\n");
    scanf("%d",&escolha);
    printf("=================\n");
    printf("\n");
    while(escolha<8){
        switch(escolha){
            case 1:
                printf("=================================\n");
                printf("\n");
                printf("Digite o numero da mesa: ");
                scanf("%d",&case1_mesa);
                printf("Digite o codigo do garcom: ");
                scanf("%d",&case1_gar);
                printf("\n");
                printf("=================================\n");
                LIMPAR_TELA;
                Insere_Mesas(&inicio_mesas,case1_mesa,case1_gar);break;
            //-----------------------------------------------------
            case 2:
                printf("=================================\n");
                printf("\n");

                LIMPAR_TELA;
                Deletar_Mesas(&inicio_mesas,&inicio_gar,&inicio_beb,&inicio_prt);
                break;
                
            //-----------------------------------------------------
            case 3:
                printf("=================================\n");
                printf("\n");
                printf("Digite o codigo da sua mesa.\n");
                scanf("%d",&case3);
                printf("\n");
                printf("=================================\n");
                LIMPAR_TELA;
                Pedido(&inicio_mesas,case3,&inicio_beb,&inicio_prt);
                break;
            
            //-----------------------------------------------------
            case 4:
                LIMPAR_TELA;
                Imprime_Garcons(&inicio_gar);break;
            
            //-----------------------------------------------------
            case 5: 
                printf("=================================\n");
                printf("\n");
                printf("Deseja ver qual cardapio?\n");
                printf("1 - Pratos\n");
                printf("2 - Bebidas\n");
                scanf("%d",&case5_escolha);
                LIMPAR_TELA;
                switch(case5_escolha){
                    case 1:
                        Imprime_pratos(&inicio_prt);
                        break;
                    case 2:
                        Imprime_bebidas(&inicio_beb);
                        break;
                    }
                break;
            //-----------------------------------------------------
            case 6: 
                LIMPAR_TELA;
                Deletar_Tudo(&inicio_mesas);        
                Deletar_List_beb(&inicio_beb);
                Deletar_List_prt(&inicio_prt);
                Deletar_List_gar(&inicio_gar);
                break;

        }
        if(escolha==6){
            printf("------------------------------------\n");
            printf("        Programa finalizado \n");
            printf("------------------------------------\n");
            break;
        }
        
        printf("O que deseja?\n");
        printf("1 - Uma mesa.\n");
        printf("2 - Fechar a conta.\n");
        printf("3 - Fazer um pedido.\n");
        printf("4 - Lista de garcons.\n");
        printf("5 - Olhar o cardapio.\n");
        printf("6- Sair.\n");
        scanf("%d",&escolha);
    }
}