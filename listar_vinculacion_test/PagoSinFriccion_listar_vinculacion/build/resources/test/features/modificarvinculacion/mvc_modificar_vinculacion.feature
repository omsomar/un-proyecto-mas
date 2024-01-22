#language: es
@E2E_MODIFICAR_VINCULACION

Característica: Modificar vinculación de forma exitosa
  Yo como analista de calidad necesito validar que el servicio modificar parámetros de comisión por vinculació reponda exitosamente
  para garantizar el cumplimiento de los requisitos del negocio

  @Modificar_Vinculacion
  Esquema del escenario: Modificar parámetros de vinculación de forma exitosa
    Dado que el usuario quiere consumir el servicio de modificar vinculacion
    Cuando el usuario realice la peticion al servicio
      |idVinculation |commissionType|commissionValueFixed|commissionValuePercentage|customStop|exonerationCommissionStartDate|exonerationCommissionEndDate|exonerationCommissionType|exonerationCommissionValueFixed|exonerationCommissionValuePercentage|sterlingPath|channelQr|
      |<idVinculation> |<commissionType>|<commissionValueFixed>|<commissionValuePercentage>|<customStop>|<exonerationCommissionStartDate>|<exonerationCommissionEndDate>|<exonerationCommissionType>|<exonerationCommissionValueFixed>|<exonerationCommissionValuePercentage>|<sterlingPath>|<channelQr>|
    Entonces el podra ver que el servicio responde con la modificacion exitosa
      |tipovalidacion|
      |<tipovalidacion>|

    Ejemplos:
      |tipovalidacion  |idVinculation                        |commissionType|commissionValueFixed|commissionValuePercentage|customStop|exonerationCommissionStartDate|exonerationCommissionEndDate|exonerationCommissionType|exonerationCommissionValueFixed|exonerationCommissionValuePercentage|sterlingPath|channelQr|
      ##Agregar channel
      |tercerescenario |5a57ea65-77e9-4365-bc58-5d155bb8e919 |P             |                    |12,00                    |          |Vacia                         |Vacia                       |                         |                               |                                    |            |Llena      |
       ##Agregar tope personalizados
      |cuartoescenario |5a57ea65-77e9-4365-bc58-5d155bb8e919 |P             |                    |13,00                    |13000     |Vacia                         |Vacia                       |                         |                               |                                    |            |Vacia      |
       ##DE fijo a exoneracion porcentual
      |primerescenario |9e44e91e-dcff-4419-82c8-78d27b8b3176 |F             |1000,00             |                         |          |Llena                         |Llena                       |P                        |                               |2,00                                |            |Vacia      |
      ##De fijo a exoneracion fija
      |primerescenario |9e44e91e-dcff-4419-82c8-78d27b8b3176 |F             |100,00              |                         |          |Llena                         |Llena                       |F                        |200,00                         |                                    |            |Vacia      |
       ##Cambiar commision actual F-P P-F
      |segundoescenario|b54d114f-7c95-4117-85ef-30312bbee1df |F             |199,00              |                         |          |Vacia                         |Vacia                       |                         |                               |                                    |            |Vacia      |
      |segundoescenario|b54d114f-7c95-4117-85ef-30312bbee1df |P             |                    |14,00                    |          |Vacia                         |Vacia                       |                         |                               |                                    |            |Vacia      |
       #DE porcentual a Exoneracion fija
      |primerescenario |0e233eb7-0b9c-4b5f-91bd-fa77332b7a5d |P             |                    |11,00                    |          |Llena                         |Llena                       |F                        |110,00                         |                                    |            |Vacia      |
       ##De porcentual a exoneracion porcentual
      |primerescenario |0e233eb7-0b9c-4b5f-91bd-fa77332b7a5d |P             |                    |16,00                    |          |Llena                         |Llena                       |P                        |                               |10,00                               |            |Vacia      |
      ##Agregar una vinculacion sin commision
      |primerescenario |9e44e91e-dcff-4419-82c8-78d27b8b3176 |F             |100,00              |                         |          |Llena                         |Llena                       |S                        |0                              |0                                   |            |Vacia      |
      |segundoescenario|b54d114f-7c95-4117-85ef-30312bbee1df |S             |0                   |0                        |          |Vacia                         |Vacia                       |                         |                               |                                    |            |Vacia      |

