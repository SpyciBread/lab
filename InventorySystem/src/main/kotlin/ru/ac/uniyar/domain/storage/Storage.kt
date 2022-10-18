package ru.ac.uniyar.domain.storage

import java.time.LocalDate
import java.util.UUID

data class Storage(
    val employeeRepository: EmployeeRepository,
    val equipmentRepository: EquipmentRepository,
    val rolePermissionsRepository: RolePermissionRepository
)

fun initializeRepositoryStorage(): Storage {
    //третье, добавили 3 роли и репозиторий
    /*val guestRole = RolePermissions(
        id = UUID.fromString("c21d0d5b-a738-4338-9358-2c71c5ac9fcd"),
        name = "Гость",
        listEquipment = false,
        listEmployee = false,
        showEquipmentItem = false,
        showEmployee = false
    )*/

    val adminRole = RolePermissions(
        id = UUID.fromString("6775efde-b354-4a5e-aa1f-774a2e2364d5"),
        name = "Администратор",
        listEquipment = true,
        listEmployee = true,
        showEquipmentItem = true,
        showEmployee = true
    )

    val employeeRole = RolePermissions(
        id = UUID.fromString("80ab25dc-c213-422f-95d0-855362275220"),
        name = "Сотрудник",
        listEquipment = false,
        listEmployee = true,
        showEquipmentItem = false,
        showEmployee = true
    )

        val petr = Employee(
        id = UUID.fromString("8333f4b1-322e-4baa-be42-05079d9fbb95"),
        roleId = UUID.fromString("6775efde-b354-4a5e-aa1f-774a2e2364d5"),
        name = "Пётр Васильевич Григорьев",
        login = "petr",
        phone = "+790000000001",
        password = "0001cc7621fa564a4d13dd5360092ba9200dab4b752aebe2fd9466dce3252d9c036fd51f113b0bd15c2107f294c882bd4d3f114656d1290f386124dd7a1131ca"
    )
    val ivan = Employee(
        id = UUID.fromString("c9096a41-b3c5-485a-8756-fd5056a8a944"),
        roleId = UUID.fromString("80ab25dc-c213-422f-95d0-855362275220"),
        name = "Иван Сергеевич Ушаков",
        login = "ivan",
        phone = "+790000000002",
        password = "0001cc7621fa564a4d13dd5360092ba9200dab4b752aebe2fd9466dce3252d9c036fd51f113b0bd15c2107f294c882bd4d3f114656d1290f386124dd7a1131ca"
    )
    val irina = Employee(
        id = UUID.fromString("8d93f93f-d491-45ed-ac94-66ad04a01e00"),
        roleId = UUID.fromString("80ab25dc-c213-422f-95d0-855362275220"),
        name = "Ирина Николаевна Кук",
        login = "irina",
        phone = "+790000000003",
        password = "0001cc7621fa564a4d13dd5360092ba9200dab4b752aebe2fd9466dce3252d9c036fd51f113b0bd15c2107f294c882bd4d3f114656d1290f386124dd7a1131ca"
    )
    val employeeRepository = EmployeeRepository(listOf(
        petr,
        ivan,
        irina,
    ))

    val printer = Equipment(
        id = UUID.fromString("8ab75a8b-c39c-4607-b253-5615e6e5e4db"),
        name = "Принтер HP LazerJet 1000",
        productId = "578-575-755-555",
        description = "Старый проверенный принтер",
        submissionDate = LocalDate.of(2010, 10, 15),
    )
    val phone = Equipment(
        id = UUID.fromString("2b985f47-dfcc-45a1-b41c-f4f01f8b2b6f"),
        name = "Samsung Galaxy S4",
        productId = "8845-3155-6655",
        description = "Мощный флагман",
        submissionDate = LocalDate.of(2015, 7, 6),
    )
    val rolePermissionsRepository = RolePermissionRepository(listOf(
        adminRole,
        employeeRole
    ))

    val equipmentRepository = EquipmentRepository(listOf(
        printer,
        phone,
    ))
    return Storage(
        employeeRepository = employeeRepository,
        equipmentRepository = equipmentRepository,
        rolePermissionsRepository = rolePermissionsRepository
    )
}
