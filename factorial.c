#include <stdio.h>
#include <stdlib.h>
/*
	arbol de recursividad para factorial de 8

valores de regreso																		40320

																								(8,1)

valores de regreso				 336													 60															  2
 
										(8,6)													(5,3)															(2,1)

valores de regreso	8			  7		  6							  5		  4		  3

						(8,8)			(7,7)		(6,6)							(5,5)		(4,4)		(3,3)								


factorial(n)=23+3factorial(n/3)   => complejidad
factorial(n)-3factorial(n/3)=23
n=3^k;    =>    k=log3(n)
factorial(3^k)-3factorial((3^k)/3)=23
factorial(3^k)-3factorial(3^(k-1))=23
(x-3)(x-1)=0
x1=3; x2=1;
factorial(3^k)=C1*k^0*3^k + C2*k^0*1^k;
factorial(3^k)=C1*3^k + C2;
pero  k=log3(n)
factorial(n)=C1*3^(log3(n)) + C2;
factorial(1)=2
factorial(2)=5
factorial(3)=23 + factorial(1)=25

factorial(1)=C1*3^(log3(1)) + C2=2
factorial(3)=C1*3^(log3(3)) + C2=25
				_______________________
				2C1=23  => C1=23/2 => C2=2
entoces
factorial(n)=(23/2)*3^(log3(n))+2;     	=> COMPLEJIDAD SIN RECURSIVIDAD


concluciones
		con el algoritmo de divide y venseras puedes "distribuir " las tareas 
		para que de una tarea complicada se vallan aciendo tareas mas pequenas.
*/
double factorial(int upperLimit,int lowerLimit){
	/*
		los dos primeros return son el elemento CASO BASE 
	*/
	if(upperLimit==lowerLimit){//1
		return upperLimit;//1
	}
	if(upperLimit-1==lowerLimit){//2
		return upperLimit*lowerLimit;//2
	}
	int division=(upperLimit-lowerLimit)/3;//4 elemento DIVICION
	double u=upperLimit-division*2-2;//5
	if(u<lowerLimit){//1
		u=lowerLimit=1;//2
		
	}
	/*
		en el return se encuentran los elementos de DIVICION, RECURSIVIDAD Y COMBINACION.
	*/
	return factorial(upperLimit,upperLimit-division)*
			factorial((upperLimit-division-1),(upperLimit-division*2-1))*
			factorial(u,lowerLimit);//8
}
int main(int ari,char **arc){
	if(ari<2){
		printf("error execute as %s number\n",arc[0]);
		return 0;
	}
	int number=atoi(arc[1]);
	printf("factorial=%f\n",factorial(number,1));
	return 0;
}

