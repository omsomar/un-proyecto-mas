#language: es

Característica: Servicio consultar intencion de compra con resultado exitoso
  yo como usuario de los servicios de Pagos sin friccion
  requiero consultar una intencion de compra
  para verificar su correcto funcionamiento

  Esquema del escenario: Consultar intencion de compra inexistente
    Dado que el usuario quiere consumir uno de los servicios del modelo estatico dinamico
    Cuando el utiliza el servicio de consultar intencion de compra <id_intencion>
    Entonces el podra ver el resultado fallido en la consulta del recurso inexistente <codigoError>
    Ejemplos:
      | id_intencion                           | codigoError |
      |                                        | 400         |
      | 0a48434f-e217-4581-9c7c-6acf7688f085ff | 400         |
      | 1a58e75a-15f7-4cd3-919e-1bc441819b00   | 409         |
      | 0ce30143-c217-46ab-b9bb-e65bcbe6731d   | 409         |
      | 444ssad-435-csry3-9000                 | 400         |
      | *                                      | 400         |
      | -                                      | 400         |
      | _                                      | 400         |
      | #34#                                   | 400         |
      | %                                      | 400         |
      | \|                                     | 400         |
      | "                                      | 400         |
      | '                                      | 400         |

