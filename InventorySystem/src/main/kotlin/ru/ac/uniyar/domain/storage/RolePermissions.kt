package ru.ac.uniyar.domain.storage

import java.util.*
//второе
data class RolePermissions(
    val id: UUID,
    val name: String,
    val listEquipment: Boolean,
    val listEmployee: Boolean,
    val showEquipmentItem: Boolean,
    val showEmployee: Boolean
    ){
    companion object{
        val ANONYMOUS_ROLE = RolePermissions(
            id = UUID.fromString("c21d0d5b-a738-4338-9358-2c71c5ac9fcd"),
            name = "Гость",
            listEquipment = false,
            listEmployee = false,
            showEquipmentItem = false,
            showEmployee = false
        )
    }
}
