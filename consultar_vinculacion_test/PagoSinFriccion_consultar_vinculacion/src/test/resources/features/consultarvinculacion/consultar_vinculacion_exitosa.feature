#language: es
@E2E_VINCULACION

Característica: Servicio consultar vinculación por id con resultado exitoso
  yo como usuario de los servicios de Pagos sin friccion
  requiero consultar una vinculación por su id
  para obtener el detalle de la vinculación filtrada

  @ConsultarVinculacionExitosamente
  Esquema del escenario: Consultar de forma exitosa una vinculación filtrando por el id
    Dado que el usuario quiere consumir uno de los servicios de comision
    Cuando el utiliza el servicio de consultar vinculacion
      | idVinculacion   |
      | <idVinculacion> |
    Entonces el podra ver el resultado exitoso de la consulta realizada <escenario>

    Ejemplos:
      | escenario                                  | idVinculacion                        |
      | camposObligatorios                         | 0e233eb7-0b9c-4b5f-91bd-fa77332b7a5d |
      | todosLosCamposFijaSinExoneracion           | 51d26470-836c-4cc3-b86a-88d516ed3c2d |
      | todosLosCamposFijaConExoneracion           | ee620978-78bc-4f6b-bc92-7b6326b7657e |
      | todosLosCamposPorcentualSinExoneracion     | 9e44e91e-dcff-4419-82c8-78d27b8b3176 |
      | todosLosCamposPorcentualConExoneracion     | 6d5173c9-4a10-40c0-b1d1-88d176ade0ec |
      | camposObligatoriosPorcentualConExoneracion | 213080ad-f923-4e60-a066-72f76ba710bd |
      | camposObligatoriosPorcentualSinExoneracion | d7f185fd-5571-41be-af16-3b7fc6394caf |
      | camposObligatoriosPorcentualSinExoneracion | be7a6e28-fded-4ec3-8861-ef428b966571 |
      | camposObligatoriosFijaSinExoneracion       | b2d3b236-4c1d-445f-b4ed-79068e975a05 |
      | camposObligatoriosFijaConExoneracion       | ac623977-f34c-4c97-bbc2-c0d711f3b663 |
      | camposVinculacionRechazada                 | 3a4e26d6-fc6f-477c-a3ce-cc98c3fde9bb |

