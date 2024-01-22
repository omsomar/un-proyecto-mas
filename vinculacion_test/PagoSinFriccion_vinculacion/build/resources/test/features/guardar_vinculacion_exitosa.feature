#language: es
@E2E_GUARDAR_VINCULACION_PLAN_AVANZADO

Característica: Verificar que se guarde en base de datos el registro de la vinculación al plan avanzado
  yo como usuario del portal de Generacion interna (PIG)
  requiero realizar una vinculación de comercios nuevos al servicio de QR
  para verificar que la vinculación se realice y almacece exitosamente en la base de datos

  @GuardarVinculacionEstadoExitosa
  Esquema del escenario: Guardar Vinculacion QR con Estado Exitosa
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
    Entonces el podra ver el resultado exitoso en el registro de la vinculación
      | modelo     | actividadNeg   | personasNotificar | tipoDoc   | numDoc   | cantCuentas   | tipoCta   | numeroCta     | numAccionistas   | tipoComision     | valorComision     | exoneracion   | notificacion   | saldosConsolidados   | reporteTrxDiario   | estructuradoPor   | opcionEntrega   | cantCorreos   |
      | <modeloId> | <actividadNeg> | <personasNotif>   | <tipoDoc> | <numDoc> | <cantCuentas> | <tipoCta> | <numeroCtaId> | <numAccionistas> | <tipoComisionId> | <valorComisionId> | <exoneracion> | <notificacion> | <saldosConsolidados> | <reporteTrxDiario> | <estructuradoPor> | <opcionEntrega> | <cantCorreos> |

    Ejemplos:
      | dispositivo | navegador | plan          | modeloId     | actividadNeg     | personasNotif | tipoDoc | numDoc      | cantCuentas | tipoCta | numeroCtaId | numAccionistas | estadoVinculacionId | tipoComisionId | valorComisionId | exoneracion | notificacion | saldosConsolidados | reporteTrxDiario | estructuradoPor            | opcionEntrega | cantCorreos |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Hogar            | 4             | CC      | 11111111111 | 3           | S       | 22222222222 | 2              | exitosa             | P              | 9.99            | No          | SI           | Si                 | Si               | documento titular          | Descargable   | 2           |
      | pc          | chrome    | Plan Avanzado | QR Estático  | Mascotas         | 2             | CC      | 134546557   | 1           | S       | 22222222222 | 1              | exitosa             | F              | 100             | Si          | Si           | Si                 | Si               | documento titular y cuenta | Descargable   | 2           |
      | pc          | chrome    | Plan Avanzado | QR Mixto     | Mascotas         | 3             | CC      | 1224354564  | 5           | S       | 22222222222 | 3              | exitosa             | F              | 190             | Si          | No           | No                 | Si               | documento titular          | SFTP          |             |
      | pc          | chrome    | Plan Avanzado | QR Mixto     | Mascotas         | 1             | CC      | 1224354564  |             | S       | 22222222222 |                | exitosa             | F              | 36              | Si          | No           | No                 | Si               | documento titular y cuenta | SFTP          |             |
      | pc          | chrome    | Plan Avanzado | QR Estático  | Mascotas         | 1             | CC      | 11111111111 |             | S       | 22222222222 |                | exitosa             | P              | 1.54            | Si          |              | No                 | No               |                            |               |             |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Mercado          | 1             | CC      | 33448899    | 2           | S       | 22222222222 | 1              | exitosa             | P              | 2.37            | Si          | Si           | No                 | Si               | documento titular          | Descargable   | 3           |
      | pc          | chrome    | Plan Avanzado | QR Mixto     | Moda y vestuario | 5             | NIT     | 11111111111 | 4           | S       | 22222222222 |                | exitosa             | S              | 0               | No          | Si           | Si                 | No               |                            |               |             |
    

