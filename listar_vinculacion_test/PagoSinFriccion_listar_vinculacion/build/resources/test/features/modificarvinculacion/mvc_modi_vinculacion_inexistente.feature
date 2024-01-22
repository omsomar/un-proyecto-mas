#language: es
@E2E_MODIFICAR_VINCULACION

Característica: Modificar vinculacion con campos invalidos
  Yo como analista de calidad necesito validar que el servicio reponda erroneamente
  cuando se le envie informacion invalidad para garantizar la valides de la informacion

  @Vinculacion_inexistente
  Esquema del escenario: Validar campos invalidos
    Dado que el usuario quiere consumir el servicio de modificar vinculacion
    Cuando el usuario realice la peticion al servicio
      |idVinculation |commissionType|commissionValueFixed|commissionValuePercentage|customStop|exonerationCommissionStartDate|exonerationCommissionEndDate|exonerationCommissionType|exonerationCommissionValueFixed|exonerationCommissionValuePercentage|sterlingPath|channelQr|
      |<idVinculation> |<commissionType>|<commissionValueFixed>|<commissionValuePercentage>|<customStop>|<exonerationCommissionStartDate>|<exonerationCommissionEndDate>|<exonerationCommissionType>|<exonerationCommissionValueFixed>|<exonerationCommissionValuePercentage>|<sterlingPath>|<channelQr>|
    Entonces el  podra comprobar que el servicio responde erroneamente al servicio

    Ejemplos:
      |idVinculation                        |commissionType|commissionValueFixed|commissionValuePercentage|customStop|exonerationCommissionStartDate|exonerationCommissionEndDate|exonerationCommissionType|exonerationCommissionValueFixed|exonerationCommissionValuePercentage|sterlingPath|channelQr|
       ##DE fijo a porcentual sin valor de cambio y sin fecha expiracion
      |1111111c-1111-1111-1111-111111111111 |F             |1000,00             |                         |          |Llena                         |Llena                       |P                        |                               |10,00                               |            |         |
      |1111111c-1111-1111-1111-111111111111 |F             |1000,00             |                         |          |Llena                         |Llena                       |P                        |                               |10,00                               |            |         |
      #DE porcentual a fijo sin valor de cambio y sin fecha expiracion
      |1111111c-1111-1111-1111-111111111111 |P             |                    |19,00                    |          |Llena                         |Llena                       |F                        |1500,00                        |                                    |            |API      |
      |1111111c-1111-1111-1111-111111111111 |P             |                    |19,00                    |          |Llena                         |Llena                       |F                        |1500,00                        |                                    |            |API      |
