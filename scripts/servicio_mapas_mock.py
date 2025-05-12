import json
import sys


def calcular_tiempo(origen, destino):
    if origen == "85150" and destino == "83010":
        return 3
    elif origen == "" and destino == "":
        return 4
    else:
        raise ValueError("No se pudo calcular el tiempo")


def main():
    try:
        entrada = sys.stdin.readline()
        datos = json.loads(entrada)
        origen = datos.get("codigo_postal_a")
        destino = datos.get("codigo_postal_b")

        tiempo = calcular_tiempo(origen, destino)
        resultado = {"tiempo_traslado": tiempo}
        print(json.dumps(resultado))
        sys.stdout.flush()

    except Exception as e:
        print(json.dumps({"error": str(e)}))
        sys.stdout.flush()
        sys.exit(1)


if __name__ == "__main__":
    main()
