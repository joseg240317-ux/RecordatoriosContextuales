package com.example.recordatorioscontextuales.data

data class Category(
    val id: Int,
    val name: String
)

sealed class Priority(val label: String) {
    data object High : Priority("Alta")
    data object Medium : Priority("Media")
    data object Low : Priority("Baja")
}

sealed class ReminderState(val label: String) {
    data object Pending : ReminderState("Pendiente")
    data object Completed : ReminderState("Completado")
    data object Snoozed : ReminderState("Pospuesto")
}

data class Reminder(
    val id: Int,
    val title: String,
    val description: String? = null,
    val category: Category,
    val priority: Priority,
    val state: ReminderState,
    val location: String? = null,
    val alertText: String? = null
)
