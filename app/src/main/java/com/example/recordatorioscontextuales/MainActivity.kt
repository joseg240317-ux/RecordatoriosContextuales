package com.example.recordatorioscontextuales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recordatorioscontextuales.data.Priority
import com.example.recordatorioscontextuales.data.Reminder
import com.example.recordatorioscontextuales.data.ReminderState
import com.example.recordatorioscontextuales.data.SampleData
import com.example.recordatorioscontextuales.ui.theme.RecordatoriosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecordatoriosTheme {
                ReminderListScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderListScreen(reminders: List<Reminder> = SampleData.reminders) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Mis recordatorios",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Actividad 1 - Programación Mobile",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "${reminders.size} recordatorios simulados",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Datos modelados con Kotlin, Lists, Sets, Maps y sealed classes.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            items(reminders, key = { it.id }) { reminder ->
                ReminderCard(reminder)
            }
        }
    }
}

@Composable
private fun ReminderCard(reminder: Reminder) {
    val categoryColor = SampleData.categoryColors[reminder.category] ?: Color.Gray

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(categoryColor)
                    )
                    Text(
                        text = reminder.category.name,
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.labelLarge,
                        color = categoryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                StatusBadge(reminder.state)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = reminder.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            reminder.description?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Prioridad: ${reminder.priority.label}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium,
                    color = priorityColor(reminder.priority)
                )
                Text(
                    text = reminder.location ?: reminder.alertText ?: "Sin aviso adicional",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun StatusBadge(state: ReminderState) {
    val (background, foreground) = when (state) {
        ReminderState.Pending -> Color(0xFFFFF3CD) to Color(0xFF7A5B00)
        ReminderState.Completed -> Color(0xFFDFF4E5) to Color(0xFF146C2E)
        ReminderState.Snoozed -> Color(0xFFE8EAF6) to Color(0xFF3949AB)
    }

    Text(
        text = state.label,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(background)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        style = MaterialTheme.typography.labelMedium,
        color = foreground,
        fontWeight = FontWeight.SemiBold
    )
}

private fun priorityColor(priority: Priority): Color = when (priority) {
    Priority.High -> Color(0xFFC62828)
    Priority.Medium -> Color(0xFFE65100)
    Priority.Low -> Color(0xFF2E7D32)
}

@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
private fun ReminderListPreview() {
    RecordatoriosTheme {
        ReminderListScreen()
    }
}
