package com.example.recordatorioscontextuales

import com.example.recordatorioscontextuales.data.Category
import com.example.recordatorioscontextuales.data.Priority
import com.example.recordatorioscontextuales.data.Reminder
import com.example.recordatorioscontextuales.data.ReminderState
import com.example.recordatorioscontextuales.data.ReminderValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ReminderValidatorTest {
    private val category = Category(1, "Trabajo")
    private val categories = setOf(category)

    @Test
    fun validReminder_hasNoErrors() {
        val reminder = Reminder(
            id = 1,
            title = "Entregar actividad",
            category = category,
            priority = Priority.High,
            state = ReminderState.Pending
        )

        assertTrue(ReminderValidator.isValid(reminder, categories))
    }

    @Test
    fun blankTitle_isInvalid() {
        val reminder = Reminder(
            id = 2,
            title = "   ",
            category = category,
            priority = Priority.Low,
            state = ReminderState.Pending
        )

        assertFalse(ReminderValidator.isValid(reminder, categories))
    }
}
