#language: es

Característica: Servicio consultar intencion de compra filtrando por idQr con resultado exitoso
  yo como usuario de los servicios de Pagos sin friccion
  requiero consultar una intencion de compra por su id qr
  para obtener la última intención de compra con estado Pendiente que tenga dicho qr

  Esquema del escenario: Consultar de forma exitosa una intencion de compra filtrando por id qr
    Dado que el usuario quiere consumir el servicio de consultar intencion por id qr
    Cuando se aplica el filtro id qr
      | idQr   |
      | <idQr> |
    Entonces el podra ver el resultado exitoso de la consulta filtrada por id qr <escenario>

    Ejemplos:
      | idQr                                 | escenario               |
      | ac1ed995-9372-4a78-9b53-c118048a978c | camposObligatorios      |
      | ede3f8e0-5b36-48cd-a316-cbc9613aaa99 | todosLosCampos          |
      | ff8f31a6-b9fb-4195-8bfa-74ff71d3c704 | todosLosCampos          |
      | 63ee1a3a-87e1-451c-87bf-4fc80509f501 | todosSinNumFidelidad    |
      | 6f4e92e0-4210-4fed-9ab6-896b4c8cbd8a | todosSinIdVentaComercio |




