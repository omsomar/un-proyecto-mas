#language: es
@E2E_MODIFICAR_VINCULACION

Característica: Modificar vinculacion con campos invalidos
  Yo como analista de calidad necesito validar que el servicio reponda erroneamente
  cuando se le envie informacion invalidad para garantizar la valides de la informacion

  @Vinculacion_sin_campos_obligatorios
  Esquema del escenario: Enviar informacion sin campos obligatorios
    Dado que el usuario quiere consumir el servicio de modificar vinculacion
    Cuando el usuario realice la peticion al servicio
      |idVinculation |commissionType|commissionValueFixed|commissionValuePercentage|customStop|exonerationCommissionStartDate|exonerationCommissionEndDate|exonerationCommissionType|exonerationCommissionValueFixed|exonerationCommissionValuePercentage|sterlingPath|channelQr|
      |<idVinculation> |<commissionType>|<commissionValueFixed>|<commissionValuePercentage>|<customStop>|<exonerationCommissionStartDate>|<exonerationCommissionEndDate>|<exonerationCommissionType>|<exonerationCommissionValueFixed>|<exonerationCommissionValuePercentage>|<sterlingPath>|<channelQr>|
    Entonces el  podra comprobar que el servicio responde erroneamente al servicio

    Ejemplos:
      |idVinculation                        |commissionType|commissionValueFixed|commissionValuePercentage|customStop|exonerationCommissionStartDate|exonerationCommissionEndDate|exonerationCommissionType|exonerationCommissionValueFixed|exonerationCommissionValuePercentage|sterlingPath|channelQr|
      ##Sin ningun campo
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |              |                    |                         |          |Vacia                         |Vacia                       |                         |                               |                                    |            |         |
      ##Cambiar F - P - P -F
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |P             |10,00               |                         |          |                              |                            |                         |                               |                                    |            |         |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |F             |                    |10.00                    |          |                              |                            |                         |                               |                                    |            |         |
      ##DE fijo a porcentual sin valor de cambio y sin fecha expiracion
      |6b332f94-61eb-4186-a391-05161629ac38 |F             |1000,00             |                         |          |Vacia                         |Vacia                       |P                        |                               |10,00                               |            |         |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |F             |1000,00             |                         |          |Llena                         |Vacia                       |P                        |                               |10,00                               |            |         |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |F             |1000,00             |                         |          |Llena                         |Llena                       |P                        |                               |                                    |            |         |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |F             |1000,00             |                         |          |Llena                         |Llena                       |                         |                               |10,00                               |            |         |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |F             |1000,00             |                         |          |Vacia                         |Llena                       |P                        |                               |10,00                               |            |         |
      #DE porcentual a fijo sin valor de cambio y sin fecha expiracion
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |P             |                    |19,00                    |          |Vacia                         |Vacia                       |F                        |1500,00                        |                                    |            |         |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |P             |                    |19,00                    |          |Llena                         |Vacia                       |F                        |1500,00                        |                                    |            |         |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |P             |                    |19,00                    |          |Llena                         |Llena                       |F                        |                               |                                    |            |         |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |P             |                    |19,00                    |          |Llena                         |Llena                       |                         |1500.00                        |                                    |            |         |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |P             |                    |19,00                    |          |Vacia                         |Llena                       |F                        |1500.00                        |                                    |            |         |
      ##Sin channel
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |P             |                    |                         |          |                              |                            |                         |                               |                                    |            |Vacia    |
      |9e44e91e-dcff-4419-82c8-78d27b8b3176 |F             |                    |                         |          |                              |                            |                         |                               |                                    |            |Vacia    |

