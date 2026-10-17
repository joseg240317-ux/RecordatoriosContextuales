package com.example.recordatorioscontextuales.data

import androidx.compose.ui.graphics.Color

object SampleData {
    private val work = Category(1, "Trabajo")
    private val study = Category(2, "Estudio")
    private val personal = Category(3, "Personal")
    private val shopping = Category(4, "Compras")

    val categories: Set<Category> = setOf(work, study, personal, shopping)

    val categoryColors: Map<Category, Color> = mapOf(
        work to Color(0xFF3559E0),
        study to Color(0xFF7656C9),
        personal to Color(0xFF00897B),
        shopping to Color(0xFFE67E22)
    )

    val reminders: List<Reminder> = listOf(
        Reminder(
            id = 1,
            title = "Enviar informe semanal",
            description = "Revisar pendientes antes del envío.",
            category = work,
            priority = Priority.High,
            state = ReminderState.Pending,
            alertText = "Hoy 18:00"
        ),
        Reminder(
            id = 2,
            title = "Repasar Kotlin y Compose",
            category = study,
            priority = Priority.Medium,
            state = ReminderState.Pending,
            location = "Biblioteca"
        ),
        Reminder(
            id = 3,
            title = "Comprar alimentos",
            category = shopping,
            priority = Priority.Low,
            state = ReminderState.Snoozed,
            location = "Supermercado"
        ),
        Reminder(
            id = 4,
            title = "Confirmar turno",
            category = personal,
            priority = Priority.Medium,
            state = ReminderState.Completed
        )
    )

    val pendingReminders: List<Reminder> = reminders.filter {
        it.state == ReminderState.Pending
    }

    val highPriorityReminders: List<Reminder> = reminders.filter {
        it.priority == Priority.High
    }

    val validReminders: List<Reminder> = reminders.filter {
        ReminderValidator.isValid(it, categories)
    }
}
