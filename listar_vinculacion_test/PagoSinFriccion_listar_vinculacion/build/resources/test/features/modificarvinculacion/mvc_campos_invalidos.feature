#language: es
@E2E_MODIFICAR_VINCULACION

  Característica: Modificar vinculacion con campos invalidos
    Yo como analista de calidad necesito validar que el servicio reponda erroneamente
    cuando se le envie informacion invalidad para garantizar la valides de la informacion

  @Campos_invalidos
  Esquema del escenario: Validar campos invalidos
    Dado que el usuario quiere consumir el servicio de modificar vinculacion
    Cuando el usuario realice la peticion al servicio
      |idVinculation |commissionType|commissionValueFixed|commissionValuePercentage|customStop|exonerationCommissionStartDate|exonerationCommissionEndDate|exonerationCommissionType|exonerationCommissionValueFixed|exonerationCommissionValuePercentage|sterlingPath|channelQr|
      |<idVinculation> |<commissionType>|<commissionValueFixed>|<commissionValuePercentage>|<customStop>|<exonerationCommissionStartDate>|<exonerationCommissionEndDate>|<exonerationCommissionType>|<exonerationCommissionValueFixed>|<exonerationCommissionValuePercentage>|<sterlingPath>|<channelQr>|
    Entonces el  podra comprobar que el servicio responde erroneamente al servicio

    Ejemplos:

      |idVinculation                        |commissionType|commissionValueFixed|commissionValuePercentage|customStop|exonerationCommissionStartDate|exonerationCommissionEndDate|exonerationCommissionType|exonerationCommissionValueFixed|exonerationCommissionValuePercentage|sterlingPath|channelQr|
      # Fijo Y porcentual
      |d56b1299-f248-45da-980e-6e0a357c4151 |F             |-122,00             |                         |          |vacia                         |vacia                       |                         |                               |                                    |            |         |
      |d56b1299-f248-45da-980e-6e0a357c4151 |P             |                    |-10,00                   |          |vacia                         |vacia                       |                         |                               |                                    |            |         |
      |d56b1299-f248-45da-980e-6e0a357c4151 |P             |                    |10,002                   |          |vacia                         |vacia                       |                         |                               |                                    |            |         |
      |d56b1299-f248-45da-980e-6e0a357c4151 |Fijo          |122,00              |                         |          |vacia                         |vacia                       |                         |                               |                                    |            |         |
      |d56b1299-f248-45da-980e-6e0a357c4151 |Porcentual    |                    |10,00                    |          |vacia                         |vacia                       |                         |                               |                                    |            |         |
      #Fechas invalidas
      |d56b1299-f248-45da-980e-6e0a357c4151 |P             |                    |10,00                    |          |la/fecha/dehoy                |la/fecha/demañana           |                         |                               |                                    |            |         |
      |d56b1299-f248-45da-980e-6e0a357c4151 |F             |122,00              |                         |          |llena                         |02/02/2021                  |                         |                               |                                    |            |         |
      #Campos exconeracion invalidos
      |d56b1299-f248-45da-980e-6e0a357c4151 |P             |                    |10,00                    |          |llena                         |llena                       |F                        |-122,00                        |                                    |            |         |
      |d56b1299-f248-45da-980e-6e0a357c4151 |F             |122,00              |                         |          |llena                         |llena                       |P                        |                               |-10,00                              |            |         |
      |d56b1299-f248-45da-980e-6e0a357c4151 |P             |                    |10,00                    |          |llena                         |llena                       |Fijo                     |122,00                         |                                    |            |         |
      |d56b1299-f248-45da-980e-6e0a357c4151 |F             |122,00              |                         |          |llena                         |llena                       |Porcentual               |                               |10,00                               |            |         |
      #Topes invalidos y otros invalidos
      |d56b1299-f248-45da-980e-6e0a357c4151 |F             |122,00              |                         |-100000   |llena                         |llena                       |P                        |                               |10,00                               |            |         |
       ##ChannerlQr invalido O ya existente
#      |6dfadd11-7cfd-46d5-a877-96c5924fca92 |P             |                    |                         |          |                              |                            |                         |                               |                                    |            |API36    |
#      |d56b1299-f248-45da-980e-6e0a357c4151 |F             |                    |                         |          |                              |                            |                         |                               |                                    |            |API45    |
