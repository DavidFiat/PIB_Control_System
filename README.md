# PIB_Control_System
Análisis
Se conoce como PIB a las siglas de “Producto Interno Bruto”. 
es el valor monetario de todos los bienes y servicios producidos en una determinada región,
durante un período determinado, normalmente un año.
Nuestro software consiste en mejorar el cálculo de este, para poder hacer un análisis de la economía
del país de una forma más regular y así anticipar posibles caídas económicas dentro del país.

https://github.com/DavidFiat/PIB_Control_System/blob/master/docs/An%C3%A1lisis-Dise%C3%B1o.pdf

Diseño
En nuestro diagrama de clase se evidencia que la clase principal de nuestro modelo es la clase Software. 
Teniendo esta una relación de ArrayList con la clase Country. 
Desde la misma clase Country hay una relación con la clase abstracta Citizien la cual es manejada como un ABB 
y con la clase abstracta Enterprise la cual es maneja como una lista enlazada simple y la clase abstracta Enterprise.
La clase Citizien a su vez es una clase padre de donde heredan las clases Adult, Child y Pet. 
La clase Enterprise a su vez es una clase padre donde heredan la clase abstracta ForProfitEnterprise
y la clase abstracta NonProfitEnterprise.
La clase ForProfitEnterprise también es una clase padre de la cual heredan las clases 
SupermarketChain, Financial, Transport y Enterprise, 
donde la clase Transport tiene una relación con la clase Vehicle la cual es manejada como un ABB 
y la clase Enterprise tiene una relación con Employee la cual también es manejada como una lista doblemente enlazada. 
La clase NonProfitEnterprise también es una clase padre de las cuales hereda la clase Fundation, Cooperative y Education.

https://github.com/DavidFiat/PIB_Control_System/blob/master/docs/Diagrama%20de%20Clases.pdf

Vídeo:
https://youtu.be/WQuVpWHOt1M
