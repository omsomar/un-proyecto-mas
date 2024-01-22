#language: es

Característica: Servicio consultar intencion de compra con resultado exitoso
  yo como usuario de los servicios de Pagos sin friccion
  requiero consultar una intencion de compra
  para verificar su correcto funcionamiento

  Esquema del escenario: Consultar intencion de compra con estructura completa
    Dado que el usuario quiere consumir uno de los servicios del modelo estatico dinamico
    Cuando el utiliza el servicio de consultar intencion de compra <idIntencion>
    Entonces el podra ver el resultado exitoso en la consulta de la intencion
      | valorIntencion   | idVentaComercio   | idQr   | numFidelidad   | estado   | razonCancelacion   | descripcion   | fechaHoraIntencion   | ultimaFechaActualizacion   |
      | <valorIntencion> | <idVentaComercio> | <idQr> | <numFidelidad> | <estado> | <razonCancelacion> | <descripcion> | <fechaHoraIntencion> | <ultimaFechaActualizacion> |
    Ejemplos:
      | valorIntencion | idVentaComercio | idQr                                 | numFidelidad    | estado   | razonCancelacion | descripcion                                               | idIntencion                          | fechaHoraIntencion         | ultimaFechaActualizacion |
      | 10000000.00    | 199092          | 31e5bcea-5edb-4b08-b781-37abb68c1f3b | puntos colombia | EXITOSA  |                  | pago por caja # 10TIENDAS JUMBOAtendido por: Manuel Rojas | 1a58e75a-15f7-4cd3-919e-1bc441819b62 | 2021-07-02T17:48:37.632    | 2021-07-02T17:48:38.056  |
      | 15600.00       |                 | 117c4e9d-f4e1-406b-bc76-ea5272fee6ea |                 | EXPIRADA |                  |                                                           | 9bdd4f9c-21bb-43e6-a249-403ef2eff724 | 2021-08-12T16:39:38.497453 | 2021-08-12T16:54:44.4457 |


