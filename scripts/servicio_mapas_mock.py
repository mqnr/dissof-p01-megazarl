import json
import sys

#verifica si esta recibiendo los parametros y el nombre del scrip
#en caso de que supere o no alcanze los parametros necesarios 
if len(sys.argv) != 3:
    print("error: datos insuficientes o sobrantes")
    sys.exit(1)

#guarda los JSON en la memoria temporalmente
direccion1_json = sys.argv[1]
direccion2_json = sys.argv[2]

#convierte los JSON en diccionarios de python 
try:
    direccion1 = json.loads(direccion1_json)
    direccion2 = json.loads(direccion2_json)
except json.JSONDecodeError as e:
    print("error: no se pude convertir en diccionario de python")
    sys.exit(1)
  
#el metodo calcular tiempo recibe 2 parametros
#si las direcciones coinciden, regresara un tiempo estimado
def calcularTiempo(direccion1, direccion2):
    if direccion1 == "ITSON" and direccion2 == "Hermosillo":
        tiempoEstimado = 3
    elif direccion1 == "" and direccion2 == "":
        tiempoEstimado = 4     
    else:
        tiempoEstimado = "error: no se pudo calcular el tiempo"
        return tiempoEstimado
tiempoEstimado = calcularTiempo(direccion1, direccion2)

#convierte el tiempoEstimado a un JSON y guarda el JSON en un archivo
with open("tiempoEstimado.json", "w") as archivo:
    json.dumps({"tiempo_estimado": tiempoEstimado},archivo)
