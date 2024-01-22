#language: es
@E2E_PGI_VINCULACION_QR

Característica: Completar una vinculación al plan avanzado con estado Exitoso
  yo como usuario del portal de Generacion interna (PGI)
  requiero realizar una vinculación de comercios nuevos al servicio de QR
  para verificar el estado de la solictud de vinculacion quedo exitosa

  @GuardarVinculacionEstadoExitosa
  Esquema del escenario: Completar Vinculacion QR con Estado Exitosa
    Dado que el usuario se encuentra en el Portal Interno de Generacion
      | dispositivo   | navegador   |
      | <dispositivo> | <navegador> |
    Cuando el escoge el Plan Avanzado para Vinculacion QR <plan>
    Y el comienza el proceso de vinculacion con informacion validada exitosamente <estadoVinculacionId>
      | modelo     | actividadNeg   | personasNotificar | tipoDoc   | numDoc   | cantCuentas   | tipoCta   | numeroCta     | numAccionistas   |
      | <modeloId> | <actividadNeg> | <personasNotif>   | <tipoDoc> | <numDoc> | <cantCuentas> | <tipoCta> | <numeroCtaId> | <numAccionistas> |
    Y se complete el proceso de vinculacion con información para entrega de reporteria erronea
      | modelo     | escenario   | tipoComision     | valorComision     | exoneracion   | notificacion   | saldosConsolidados   | reporteTrxDiario   | estructuradoPor   | opcionEntrega   |
      | <modeloId> | <escenario> | <tipoComisionId> | <valorComisionId> | <exoneracion> | <notificacion> | <saldosConsolidados> | <reporteTrxDiario> | <estructuradoPor> | <opcionEntrega> |
    Entonces no se debe permitir avanzar al siguiente formulario

    Ejemplos:
      | dispositivo | navegador | plan          | modeloId     | actividadNeg     | personasNotif | tipoDoc | numDoc      | cantCuentas | tipoCta | numeroCtaId | numAccionistas | estadoVinculacionId | tipoComisionId | valorComisionId | exoneracion | notificacion | escenario             | saldosConsolidados | reporteTrxDiario | estructuradoPor            | opcionEntrega |
      | pc          | chrome    | Plan Avanzado | QR Estático  | Hogar            | 2             | CC      | 11111111111 | 1           | S       | 22222222222 | 2              | exitosa             | F              | 100             | No          | SI           | sinCorreoDestinatario | Si                 | Si               | documento titular          | Descargable   |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Hogar            | 2             | CC      | 11111111111 | 1           | S       | 22222222222 |                | exitosa             | F              | 38              | Si          | No           | correosNoCoinciden    | Si                 | Si               | documento titular y cuenta | Descargable   |
      | pc          | chrome    | Plan Avanzado | QR Mixto     | Moda y vestuario | 5             | NIT     | 11111111111 | 3           | S       | 22222222222 |                | exitosa             | S              | 0               | No          |              | eliminarCorreos       | No                 | Si               | documento titular          | Descargable   |
      | pc          | chrome    | Plan Avanzado | QR Mixto     | Mascotas         | 2             | CC      | 188990022   | 1           | S       | 22222222222 |                | exitosa             | P              | 2.95            | Si          |              | correoSinPunto        | Si                 | Si               | documento titular y cuenta | Descargable   |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Mascotas         | 3             | CC      | 188990022   | 2           | D       | 40613403820 | 1              | exitosa             | P              | 3.43            | Si          | Si           | correoSinArroba       | No                 | Si               | documento titular          | Descargable   |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Mercado          | 4             | CC      | 188990022   | 2           | S       | 22222222222 |                | exitosa             | P              | 10              | si          |              | correoConEspacio      | Si                 | Si               | documento titular y cuenta | Descargable   |
    

