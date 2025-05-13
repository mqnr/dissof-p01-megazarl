import csv
import json
import socket
import sys
import threading

HOST, PORT = "127.0.0.1", 6010

### Cargar datos ###


def cargar_rutas(ruta_csv):
    rutas = {}
    with open(ruta_csv, newline="", encoding="utf-8") as f:
        lector = csv.reader(f)
        next(lector, None)  # Salta encabezado
        for origen, destino, horas in lector:
            rutas[(origen, destino)] = float(horas)
    return rutas


### Conexiones de clientes ###


def atender(conn, rutas):
    with conn, conn.makefile() as f:
        for linea in f:  # Bloquea hasta que el cliente envía
            try:
                data = json.loads(linea)
                key = (
                    data.get("codigo_postal_origen"),
                    data.get("codigo_postal_destino"),
                )
                horas = rutas[key]  # KeyError si no existe
                resp = {"tiempo_estimado_horas": horas}
            except Exception as e:
                resp = {"error": str(e)}
            conn.sendall((json.dumps(resp) + "\n").encode())


### Servidor principal ###


def main():
    if len(sys.argv) < 2:
        print("Uso: python servicio_mapas_simulacion.py rutas.csv", file=sys.stderr)
        sys.exit(2)

    rutas = cargar_rutas(sys.argv[1])
    plural = "" if len(rutas) == 1 else "s"
    print(
        f"Cargada{plural} {len(rutas)} ruta{plural}. "
        f"Escuchando en {HOST}:{PORT}...",
        file=sys.stderr,
    )

    with socket.create_server((HOST, PORT)) as srv:
        while True:
            conn, _ = srv.accept()  # Bloquea hasta que llega un cliente
            threading.Thread(target=atender, args=(conn, rutas), daemon=True).start()


if __name__ == "__main__":
    main()
