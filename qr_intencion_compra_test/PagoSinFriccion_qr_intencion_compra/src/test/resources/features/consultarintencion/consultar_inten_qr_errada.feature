#language: es

Característica: Servicio consultar intencion de compra filtrando por idQr con resultado erróneo
  yo como usuario de los servicios de Pagos sin friccion
  requiero realizar una consulta de una intencion de compra por su id qr de forma errónea
  para verificar el fallo esperado al incumplir con la debida estructura de la peticion

  Esquema del escenario: Consultar de forma errónea una intencion de compra filtrando por id qr
    Dado que el usuario quiere consumir el servicio de consultar intencion por id qr
    Cuando se aplica el filtro id qr
      | idQr   | escenario   |
      | <idQr> | <escenario> |
    Entonces el podra ver el resultado erroneo de la consulta filtrada por id qr <escenario>

    Ejemplos:
      | idQr                                 | escenario               |
      |                                      | qrVacio                 |
      | 3456&##0696l-49302*                  | qrInvalido              |
      | ede3f8e09                            | qrInvalido              |
      |                                      | sinQr                   |
      | 82c8e96d-09b7-458d-ba4a-745722907433 | recursoErroneo          |
      | 50cb1420-c895-47e8-bd24-5faee802d86c | qrSinNingunaIntencion   |
      | da8c9058-a09f-457d-8091-6181bfbc73ee | qrSinintencionPendiente |


