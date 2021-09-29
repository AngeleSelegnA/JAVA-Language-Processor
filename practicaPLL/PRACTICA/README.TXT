En el archivo contenido en la carpeta principal llamado probar practica pl.txt se encuentran todos los comandos necesarios para ejecutar el proyecto
adecuadamente y poder probar cada uno de los ficheros proporcionados para ver el comportamiento de la práctica en distintos aspectos.
A la hora de probar la práctica, hemos generado una serie de archivos, cada uno enfocado en probar una parte concreta de los errores/expresiones,
así como archivos generales de un programa tipo y un archivo específico para todo lo que tiene que ver con webassembly.

- erroresSintact.txt y erroresSintac2.txt: archivo con errores sintácticos y recuperación de los mismos
- erroresSem1.txt : archivo con errores semánticos y recuperación de los mismos

- programa.txt : archivo sin errores que contiene la mayor parte de las cosas implementadas al menos hasta la parte anterior a generación de código



ADVERTENCIA: todos los archivos anteriores DARÁN ERRORES EN WEBASSEMBLY POR TENER STRUCTS Y VECTORES. 

Ahora procedemos con los programas que funcionarán correctamente al generar código.

- expresiones.txt : prueba del correcto funcionamiento tanto de las operaciones numéricas (+, -, *, %, /) como booleanas (&&, ||)

- progCódigo : archivo apto para generar código de webassembly correcto, sin structs, enums ni vectores

La salida de probar cada uno de los archivos está incluida como outNombreArchivo.txt para más comodidad.