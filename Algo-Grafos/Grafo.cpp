#include <bits/stdc++.h>
using namespace std;

int G[1010][1010],tam_f=0,n;
vector<vector<int> > F;
int cor[1010];

// =====================================================
// BIPARTICAO
// =====================================================
void colore(int x){ 
	
	cor[x] = 0; 
	
	vector<int> fila; 
	fila.push_back(x); 
	
	int pos = 0; 
	
	while(pos < (int)fila.size()){ 
		
		int atual = fila[pos]; 
		pos++;
		
		
        for(int j = 0; j < n; j++){
            if(G[atual][j]){
                if(cor[j] == -1){ 
                    cor[j] = 1 - cor[atual]; 			
                    fila.push_back(j); 
                }
            }
		}
	}
}

bool checa_bipartido(){
	
	for(int i = 0;i < n;i++){
		if(cor[i] == -1){       
			colore(i);         
		}
	}
	
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if(G[i][j]){
			    if(cor[i] == cor[j]) return false; 
            }
		}
	}
	
	return true; 
}

// =====================================================
// PREENCHIMENTO DA MATRIZ
// =====================================================
int Fill_Matriz(int n){
    
    int x,y;
    bool verificado = false;

    memset(G,0,sizeof(G));
    memset(cor, -1, sizeof(cor));
    
    do{
        cout << "Digite as arestas!" << endl;
        cin >> x >> y;
        if(x>=0 && y>=0){
            G[x][y]=1;
            G[y][x]=1;
        }
        
    }while((x > -1 && x < n) && (y > -1 && y < n));

}

int Print(int M[1010][1010], int n){
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cout << M[i][j] << " ";
        }
        cout << endl;
    }
}

int Save_File(int n,int M[1010][1010],string filename){

    fstream myFile;
    myFile.open(filename, fstream::out);

    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            myFile << M[i][j] << " ";
        }
        myFile << endl;
    }

    myFile.close();
}

int Read_File(string filename){
    
    char c;
    int tmp;
    vector<int> aux;

    ifstream myFile;
    myFile.open(filename);

    memset(G,0,sizeof(G));
    memset(cor, -1, sizeof(cor));

    myFile.get(c);
    while(myFile){
        while(c != '\n') {
            if(isdigit(c)){
                tmp = c - 48 ;
                aux.push_back(tmp); 
                tam_f++;
            } 
            myFile.get(c); 
        }
        F.push_back(aux);
        

        aux.clear();
        myFile.get(c);
    }

    tam_f = sqrt(tam_f);

    

    for(int i=0; i<tam_f;i++) {
        for(int j=0;j<tam_f;j++) G[i][j] = F[i][j];
    }

    myFile.close();
    n = tam_f;
}



// =====================================================
// MAIN
// =====================================================

int main(void){

    int op;
    string Filename;
    bool ok = false;
    

    cout << "O que deseja fazer?" << endl;
    cout << "1 - Preencher a Matriz" << endl;
    cout << "2 - Imprimir" << endl;
    cout << "3 - Salvar em arquivo" << endl;
    cout << "4 - Ler o arquivo" << endl;
    cout << "5 - Verificar se o grafo eh bipartido" << endl;
    cout << "6 - Finalizar Programa" << endl;
    cout << "Escolha: ";
    cin >> op;
    

    if(op == 6) return 0;

    while(op != 6){
        switch(op){

            case 1: system("clear");
                    cout << endl;
                    cout << endl;
                    cout << "Quantos elementos por linha tem a matriz?" << endl;
                    cout << "Num de elementos: ";
                    cin >> n;
                    Fill_Matriz(n);
                    ok = true;
                    cout << endl;
                    cout << endl;
                    break;
                    

            case 2: 
                    system("clear");
                    cout << endl;
                    cout << endl;
                    if(ok) Print(G,n);
                    else cout << "Nao existe matriz preenchida ainda!" << endl;
                    cout << endl;
                    cout << endl;
                    break;

            case 3: system("clear");
                    cout << endl;
                    cout << endl;
                    cout << "Digite o nome do arquivo: ";
                    cin >> Filename;
                    if(ok) Save_File(n,G,Filename);
                    else cout << "Nao existe matriz preenchida ainda!" << endl;
                    cout << endl;
                    cout << endl;
                    break;

            case 4: system("clear");
                    cout << endl;
                    cout << endl;
                    cout << "Digite o nome do arquivo: ";
                    cin >> Filename;
                    Read_File(Filename);
                    ok = true;
                    cout << endl;
                    cout << endl;
                    break;

            case 5: system("clear");
                    cout << endl << endl << (checa_bipartido() ? "Eh bipartido!":"Nao eh bipartido") << endl << endl;
                    break;

            case 6: return 0;

            //case 7: return 0;
                        
        }
        
        cout << "O que deseja fazer?" << endl;
        cout << "1 - Preencher a Matriz" << endl;
        cout << "2 - Imprimir" << endl;
        cout << "3 - Salvar em arquivo" << endl;
        cout << "4 - Ler o arquivo" << endl;
        cout << "5 - Verificar se o grafo eh bipartido" << endl;
        cout << "6 - Finalizar Programa" << endl;
        cout << "Escolha: ";
        cin >> op;
    }
    
}