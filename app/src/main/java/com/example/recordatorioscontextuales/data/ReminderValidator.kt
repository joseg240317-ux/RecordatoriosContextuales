package com.example.recordatorioscontextuales.data

object ReminderValidator {
    fun validate(reminder: Reminder, validCategories: Set<Category>): List<String> {
        val errors = mutableListOf<String>()

        if (reminder.title.isBlank()) {
            errors += "El título no puede estar vacío."
        }
        if (reminder.title.length > 80) {
            errors += "El título no puede superar los 80 caracteres."
        }
        if (reminder.category !in validCategories) {
            errors += "La categoría debe existir en el conjunto de categorías válidas."
        }
        if (reminder.location != null && reminder.location.isBlank()) {
            errors += "La ubicación opcional debe contener texto o ser null."
        }
        if (reminder.alertText != null && reminder.alertText.isBlank()) {
            errors += "El aviso opcional debe contener texto o ser null."
        }

        return errors.toList()
    }

    fun isValid(reminder: Reminder, validCategories: Set<Category>): Boolean =
        validate(reminder, validCategories).isEmpty()
}
