#language: es
@E2E_PGI_VINCULACION_QR

Característica: Completar una vinculación al plan avanzado con estado Exitoso
  yo como usuario del portal de Generacion interna (PGI)
  requiero realizar una vinculación de comercios nuevos al servicio de QR
  para verificar el estado de la solictud de vinculacion quedo exitosa

  @GuardarVinculacionEstadoExitosa
  Esquema del escenario: Completar Vinculacion QR con Estado Exitosa con revisión
    Dado que el usuario se encuentra en el Portal Interno de Generacion
      | dispositivo   | navegador   |
      | <dispositivo> | <navegador> |
    Cuando el escoge el Plan Avanzado para Vinculacion QR <plan>
    Y el comienza el proceso de vinculacion incluyendo una cuenta no apta <estadoVinculacionId>
      | modelo     | actividadNeg   | personasNotificar | tipoDoc   | numDoc     | cantCuentas   | tipoCta   | numeroCta   | numAccionistas   |
      | <modeloId> | <actividadNeg> | <personasNotif>   | <tipoDoc> | <numDocId> | <cantCuentas> | <tipoCta> | <numeroCta> | <numAccionistas> |
    Y se complete el proceso de vinculacion
      | modelo     | tipoComision     | valorComision     | exoneracion   | notificacion   | saldosConsolidados   | reporteTrxDiario   | estructuradoPor   | opcionEntrega   | cantCorreos   |
      | <modeloId> | <tipoComisionId> | <valorComisionId> | <exoneracion> | <notificacion> | <saldosConsolidados> | <reporteTrxDiario> | <estructuradoPor> | <opcionEntrega> | <cantCorreos> |
    Entonces el podra ver un mensaje de Vinculacion Exitosa

    Ejemplos:
      | dispositivo | navegador | plan          | modeloId    | actividadNeg | personasNotif | tipoDoc | numDocId    | cantCuentas | tipoCta | numeroCta   | numAccionistas | estadoVinculacionId | tipoComisionId | valorComisionId | exoneracion | notificacion | saldosConsolidados | reporteTrxDiario | estructuradoPor   | opcionEntrega | cantCorreos |
      | pc          | chrome    | Plan Avanzado | QR Estático | Deporte      | 3             | CC      | 11111111111 | 2           | S       | 22222222222 |                | exitosaConRevision  | P              | 2.99            |             | Si           | Si                 | Si               | documento titular | Descargable   | 2           |
    

