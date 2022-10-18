package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Settings
import ru.ac.uniyar.domain.storage.Storage

class OperationStorage(
    storage: Storage,
    settings: Settings
) {
    val fetchPermissionsViaIdOperation: FetchPermissionsViaIdOperation  = FetchPermissionsViaIdOperation(
        storage.rolePermissionsRepository
    )
    val fetchEmployeeViaUserIdOperation: FetchEmployeeViaUserIdOperation = FetchEmployeeViaUserIdOperation(
            storage
    )

    val fetchEmployeeOperation: FetchEmployeeOperation = FetchEmployeeOperationImplementation(
        storage.employeeRepository,
    )

    val fetchEquipmentOperation: FetchEquipmentOperation = FetchEquipmentOperationImplementation(
        storage.equipmentRepository,
    )

    val listEmployeesOperation: ListEmployeesOperation = ListEmployeesOperationImplementation(
        storage.employeeRepository,
    )

    val listEquipmentOperation: ListEquipmentOperation = ListEquipmentOperationImplementation(
        storage.equipmentRepository,
    )

    val editEmployeeOperation: EditEmployeeOperation = EditEmployeeOperationImplemation(
        storage.employeeRepository,)

    val authenticateUserViaLoginQuery: AuthenticateUserViaLoginQuery = AuthenticateUserViaLoginQuery(
        storage, settings
    )
}
