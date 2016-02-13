#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>
#define MATRIZES 2
#define RANDOM 9
int **getMemoryMatriz(int rows,int columns){
	int r,**memory=(int **)malloc(sizeof(int *)*rows);
	for(r=0;r<rows;r++)
		memory[r]=(int *)malloc(sizeof(int)*columns);
	return memory;
}
void printMatriz(int **matriz,int rows,int columns){//rows and columns are of matriz
	int r,c;
	for(r=0;r<rows;r++){
		for(c=0;c<columns;c++){
			printf("%7d ",matriz[r][c]);
		}
		printf("\n");
	}
}
int getUpper(int *dimensions,int size){//size of array integers dimensions, this method return upper integer of array dimensions
	if(size==1)
		return dimensions[0];
	int i,upper=dimensions[0];
	for(i=1;i<size;i++){
		if(upper<dimensions[i]){
			upper=dimensions[i];
		}
	}
	return upper;
}
int getPow2(int number){
	int i=1,pow2=0;
	while(pow2<number){
		pow2=pow(2,i);
		i++;
	}
	return pow2;
}
int getPow3(int number){//this method return integer that is potencia of 3 but this potencia is same or mayor that number e.g. number=2 pow3 deveria valer 3 other example number=5 pow3 valdria 9
	int i=1,pow3=0;
	while(pow3<number){
		pow3=pow(3,i);
		i++;
	}
	return pow3;
}
void fillMatriz(int **matriz,int rowLower,int columnLower,int rowUpper,int columnUpper,int fillNumber){//this method fill matriz with fillNumbers in range rowLower to rowUpper and columnLower to columnUpper
	int r,c;
	for(r=rowLower;r<rowUpper;r++){
		for(c=columnLower;c<columnUpper;c++){
			matriz[r][c]=fillNumber;
		}
	}
}
int ** getMatriz(int row,int columns,int pow2){//this method return matriz cuadrada size pow2*pow2 but just fill with number entered by keyboard of user row filas and columns columnas
//	srand(time(NULL));
	int r,c,**matriz=getMemoryMatriz(pow2,pow2);
	for(r=0;r<row;r++){
		for(c=0;c<columns;c++){			
			printf("ingrese valor para [%d][%d]\n",r,c);
			scanf("%d",&matriz[r][c]);	
	//		matriz[r][c]=(random()%RANDOM);
		}
	}
	fillMatriz(matriz,row,columns,pow2,pow2,0);
	return matriz;
}
void fillMatrizWithOtherMatriz(int **md,int **mo,int rsmd,int csmd,int rsmo,int csmo,int rtc,int ctc){//md:matriz destino, mo:matriz origen rsmd:rows start matriz destino,
	// csmd:columns start matriz destino, rsmo: rows start matriz origen, csmo: columns start matriz one, rtc: rows to copy, ctc: columns to copy
	int rd,cd,ro,co,rt=rsmo+rtc,ct=csmo+ctc;//rt row tope,ct column tope
	for(rd=rsmd,ro=rsmo;ro<rt;rd++,ro++){
		for(cd=csmd,co=csmo;co<ct;cd++,co++){
			md[rd][cd]=mo[ro][co];
		}
	}
}
int ** plusMatrizes(int **mo,int **mt,int rsmo,int csmo,int rsmt,int csmt,int rtp,int ctp){//mo:matri one, mt:matriz two rsmo:rows start matriz one,
	// csmo:columns start matriz one, rsmt: rows start matriz two, csmt: columns start matriz two, rtp: rows to plus, ctp: columns to plus	
	int ro,co,rt,ct,rr,cr;
	int **response=getMemoryMatriz(rtp,ctp);
	for(rr=0,ro=rsmo,rt=rsmt;rr<rtp;ro++,rt++,rr++){
		for(cr=0,co=csmo,ct=csmt;cr<ctp;co++,ct++,cr++){
			response[rr][cr]=mo[ro][co]+mt[rt][ct];
		}
	}
	return response;
}
int **multiplication(int **mo,int **mt,int rsmo,int csmo,int rsmt,int csmt,int s){//mo:matriz one, mt:matriz two, rsmo:row start matriz one, csmo column start matriz one, rsmt row start matriz two, csmt column start matriz two, s size of matriz then this method just accept squared matrizes
	int **response=getMemoryMatriz(s,s);
	if(s==1){
		response[0][0]=mo[rsmo][csmo]*mt[rsmt][csmt];
		return response;
	}
	s/=3;
	//int ** plusMatrizes(int **mo,int **mt,int rsmo,int csmo,int rsmt,int csmt,int rtp,int ctp)
	//void fillMatrizWithOtherMatriz(int **md,int **mo,int rsmd,int csmd,int rsmo,int csmo,int rtc,int ctc);
	int **part;
	
	part=plusMatrizes(plusMatrizes(multiplication(mo,mt,rsmo,csmo,rsmt,csmt,s),multiplication(mo,mt,rsmo,csmo+s,rsmt+s,csmt,s),0,0,0,0,s,s),
	multiplication(mo,mt,rsmo,csmo+s*2,rsmt+s*2,csmt,s),0,0,0,0,s,s);
	fillMatrizWithOtherMatriz(response,part,0,0,0,0,s,s);
	//printMatriz(part,s,s);
	
	part=plusMatrizes(plusMatrizes(multiplication(mo,mt,rsmo,csmo,rsmt,csmt+s,s),multiplication(mo,mt,rsmo,csmo+s,rsmt+s,csmt+s,s),0,0,0,0,s,s),
	multiplication(mo,mt,rsmo,csmo+s*2,rsmt+s*2,csmt+s,s),0,0,0,0,s,s);
	fillMatrizWithOtherMatriz(response,part,0,s,0,0,s,s);
	//printMatriz(part,s,s);
	
	part=plusMatrizes(plusMatrizes(multiplication(mo,mt,rsmo,csmo,rsmt,csmt+s*2,s),multiplication(mo,mt,rsmo,csmo+s,rsmt+s,csmt+s*2,s ),0,0,0,0,s,s),
	multiplication(mo,mt,rsmo,csmo+s*2,rsmt+s*2,csmt+s*2,s),0,0,0,0,s,s);
	fillMatrizWithOtherMatriz(response,part,0,s*2,0,0,s,s);
	//printMatriz(part,s,s);

	part=plusMatrizes(plusMatrizes(multiplication(mo,mt,rsmo+s,csmo,rsmt,csmt,s ),multiplication(mo,mt,rsmo+s,csmo+s,rsmt+s,csmt,s),0,0,0,0,s,s),
	multiplication(mo,mt,rsmo+s,csmo+s*2,rsmt+s*2,csmt,s),0,0,0,0,s,s);
	fillMatrizWithOtherMatriz(response,part,s,0,0,0,s,s);
	//printMatriz(part,s,s);
	
	part=plusMatrizes(plusMatrizes(multiplication(mo,mt,rsmo+s,csmo,rsmt,csmt+s,s ),multiplication(mo,mt,rsmo+s,csmo+s,rsmt+s,csmt+s,s),0,0,0,0,s,s),
	multiplication(mo,mt,rsmo+s,csmo+s*2,rsmt+s*2,csmt+s,s),0,0,0,0,s,s);
	fillMatrizWithOtherMatriz(response,part,s,s,0,0,s,s);
	//printMatriz(part,s,s);
	
	part=plusMatrizes(plusMatrizes(multiplication(mo,mt,rsmo+s,csmo,rsmt,csmt+s*2,s ),multiplication(mo,mt,rsmo+s,csmo+s,rsmt+s,csmt+s*2,s ),0,0,0,0,s,s),
	multiplication(mo,mt,rsmo+s,csmo+s*2,rsmt+s*2,csmt+s*2,s ),0,0,0,0,s,s);
	fillMatrizWithOtherMatriz(response,part,s,s*2,0,0,s,s);
	//printMatriz(part,s,s);

	part=plusMatrizes(plusMatrizes(multiplication(mo,mt,rsmo+s*2,csmo,rsmt,csmt,s ),multiplication(mo,mt,rsmo+s*2,csmo+s,rsmt+s,csmt,s ),0,0,0,0,s,s),
	multiplication(mo,mt,rsmo+s*2,csmo+s*2,rsmt+s*2,csmt,s),0,0,0,0,s,s);
	fillMatrizWithOtherMatriz(response,part,s*2,0,0,0,s,s);
	//printMatriz(part,s,s);
	
	part=plusMatrizes(plusMatrizes(multiplication(mo,mt,rsmo+s*2,csmo,rsmt,csmt+s,s ),multiplication(mo,mt,rsmo+s*2,csmo+s,rsmt+s,csmt+s,s),0,0,0,0,s,s),
	multiplication(mo,mt,rsmo+s*2,csmo+s*2,rsmt+s*2,csmt+s,s),0,0,0,0,s,s);
	fillMatrizWithOtherMatriz(response,part,s*2,s,0,0,s,s);
	//printMatriz(part,s,s);
	
	part=plusMatrizes(plusMatrizes(multiplication(mo,mt,rsmo+s*2,csmo,rsmt,csmt+s*2,s ),multiplication(mo,mt,rsmo+s*2,csmo+s,rsmt+s,csmt+s*2,s ),0,0,0,0,s,s),
	multiplication(mo,mt,rsmo+s*2,csmo+s*2,rsmt+s*2,csmt+s*2,s ),0,0,0,0,s,s);
	fillMatrizWithOtherMatriz(response,part,s*2,s*2,0,0,s,s);
	//printMatriz(part,s,s);
	return response;
}
int main(int ari,char **arc){//./a.out 5,3 4,3
	if(ari<3){
		printf("error execute like %s rowsOneMatriz,columnsOneMatriz rowsTwoMatriz,columnsTwoMatriz\n",arc[0]);
		return 1;
	}
	int upper,*dimensions=(int *)malloc(sizeof(int)*(MATRIZES*2)); //dimensions containts rows and columns of matrices one and two
	dimensions[0]=arc[1][0]-48; //set rows matriz one
	dimensions[1]=arc[1][2]-48; //set columns matriz one
	dimensions[2]=arc[2][0]-48; //set rows matriz two
	dimensions[3]=arc[2][2]-48; //set columns matriz two
	upper=getUpper(dimensions,MATRIZES*2);//get upper number of array integer dimension
	int pow3=getPow3(upper);//get pontencia of 3 same or mayor that upper
	printf("\t\t\tenter values for matriz one\n");
	int **matrizOne=getMatriz(dimensions[0],dimensions[1],pow3);
	printf("\t\t\tenter values for matriz two\n");
	int **matrizTwo=getMatriz(dimensions[2],dimensions[3],pow3);
	printf("\t\t\tmatriz one\n");
	//printMatriz(matrizOne,dimensions[0],dimensions[1]);
	printMatriz(matrizOne,pow3,pow3);
	printf("\t\t\tmatriz two\n");
	//printMatriz(matrizTwo,dimensions[2],dimensions[3]);
	printMatriz(matrizTwo,pow3,pow3);
	int **response=multiplication(matrizOne,matrizTwo,0,0,0,0,pow3);
	printf("\t\t\tresponse\n");
	printMatriz(response,dimensions[0],dimensions[3]);
	printMatriz(response,pow3,pow3);
	return 0;
}

