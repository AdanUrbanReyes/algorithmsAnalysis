#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h> 
#define DIVISIONS 3
/*
	dividir el problema en tres subproblemas
	IMPORTANT NOTE:
		upperLimitX is the part more right of string
		lowerLimitX is the part more left of string
		example:
			string="15489"
			index   01234
				upperLimit =3
				lowerLimit=1
*/
double divideNumber(double one,double two){
	if(one<=1||two<=1)
		return one*two;
	return 9*divideNumber(one/DIVISIONS,two/DIVISIONS);
}
double stringToInteger(char *string,int ul,int ll,int size){//ul->upperLimit, ll->lowerLimit, size->strlen(string) (size is total characters for string)
	double number=0;
	size--;
	while(ll<ul){
		number+=((int)string[ll]-48)*pow(10,size-ll);
		ll++;
	}
	return number;
}
double divideDigitos(char *one,char *two,int ulo,int llo,int ult,int llt){//ulo->upperLimitOne, llo->lowerLimitOne, ult->upperLimitTwo, llt->lowerLimitTwo
	int so=ulo-llo;//so->sizeOne
	int st=ult-llt;//st->sizeTwo
	if(so<DIVISIONS&&st<DIVISIONS)//is obligatori that one and two are size lest than DIVISIONS "3"
		return stringToInteger(one,ulo,llo,strlen(one))*stringToInteger(two,ult,llt,strlen(two));
	if(so>=DIVISIONS&&st>=DIVISIONS){//divide los dos then se forman 9 rramas
		int sdo=so/DIVISIONS; //sdo->sizeDivicionsOne  "funcion piso"
		int sdt=st/DIVISIONS; //sdt->sizeDivicionsTwo  "funcion piso"
		if(sdo%DIVISIONS>1)
			sdo++;// "funcion techo"
		if(sdt%DIVISIONS>1)
			sdt++;// "funcion techo"
		return divideDigitos(one,two,llo+sdo,llo,llt+sdt,llt)+
				divideDigitos(one,two,llo+sdo*2,llo+sdo,llt+sdt,llt)+
				divideDigitos(one,two,ulo,llo+sdo*2,llt+sdt,llt)+

				divideDigitos(one,two,llo+sdo,llo,llt+sdt*2,llt+sdt)+
				divideDigitos(one,two,llo+sdo*2,llo+sdo,llt+sdt*2,llt+sdt)+
				divideDigitos(one,two,ulo,llo+sdo*2,llt+sdt*2,llt+sdt)+
			
				divideDigitos(one,two,llo+sdo,llo,ult,llt+sdt*2)+
				divideDigitos(one,two,llo+sdo*2,llo+sdo,ult,llt+sdt*2)+
				divideDigitos(one,two,ulo,llo+sdo*2,ult,llt+sdt*2);
	}
	if(so>=DIVISIONS){//just divide one then se forman 3 rramas
		int sdo=so/DIVISIONS; //sdo->sizeDivicionsOne  "funcion piso"
		if(sdo%DIVISIONS>1)
			sdo++;// "funcion techo"
		return divideDigitos(one,two,llo+sdo,llo,ult,llt)+
				divideDigitos(one,two,llo+sdo*2,llo+sdo,ult,llt)+
				divideDigitos(one,two,ulo,llo+sdo*2,ult,llt);
	}
	if(st>=DIVISIONS){//just divide two then se forman 3 rramas
		int sdt=st/DIVISIONS; //sdt->sizeDivicionsTwo  "funcion piso"
		if(sdt%DIVISIONS>1)
			sdt++;// "funcion techo"
		return divideDigitos(one,two,ulo,llo,llt+sdt,llt)+
				divideDigitos(one,two,ulo,llo,llt+sdt*2,llt+sdt)+
				divideDigitos(one,two,ulo,llo,ult,llt+sdt*2);
	}
	return 0;
}
int main(int ari,char **arc){
	if(ari<2){
		printf("error please execute as %s numberOne*numberTwo\n",arc[0]);
		return 0;
	}
	printf("%s*%s=%lf\n",arc[1],arc[2],divideNumber(atof(arc[1]),atof(arc[2])));	
	printf("%s*%s=%lf\n",arc[1],arc[2],divideDigitos(arc[1],arc[2],strlen(arc[1]),0,strlen(arc[2]),0));
	return 0;
}
