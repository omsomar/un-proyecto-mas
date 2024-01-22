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
    Y se complete el proceso de vinculacion
      | modelo     | tipoComision     | valorComision     | exoneracion   | notificacion   | saldosConsolidados   | reporteTrxDiario   | estructuradoPor   | opcionEntrega   | cantCorreos   |
      | <modeloId> | <tipoComisionId> | <valorComisionId> | <exoneracion> | <notificacion> | <saldosConsolidados> | <reporteTrxDiario> | <estructuradoPor> | <opcionEntrega> | <cantCorreos> |
    Entonces el podra ver un mensaje de Vinculacion Exitosa

    Ejemplos:
      | dispositivo | navegador | plan          | modeloId     | actividadNeg     | personasNotif | tipoDoc | numDoc      | cantCuentas | tipoCta | numeroCtaId | numAccionistas | estadoVinculacionId | tipoComisionId | valorComisionId | exoneracion | notificacion | saldosConsolidados | reporteTrxDiario | estructuradoPor            | opcionEntrega | cantCorreos |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Hogar            | 2             | CC      | 11111111111 | 1           | S       | 22222222222 | 2              | exitosa             | F              | 100             | No          | SI           | Si                 | Si               | documento titular          | Descargable   | 3           |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Hogar            | 2             | CC      | 11111111111 | 1           | S       | 22222222222 |                | exitosa             | F              | 38              | Si          | No           | Si                 | Si               | documento titular y cuenta | Descargable   | 2           |
      | pc          | chrome    | Plan Avanzado | QR Estático  | Mascotas         | 3             | CC      | 11111111111 | 4           | S       | 22222222222 | 3              | exitosa             | P              | 1.54            | Si          | si           | No                 | Si               | documento titular y cuenta | SFTP          |             |
      | pc          | chrome    | Plan Avanzado | QR Mixto     | Moda y vestuario | 5             | NIT     | 11111111111 | 3           | S       | 22222222222 |                | exitosa             | S              | 0               | No          |              | No                 | No               |                            |               |             |
    

