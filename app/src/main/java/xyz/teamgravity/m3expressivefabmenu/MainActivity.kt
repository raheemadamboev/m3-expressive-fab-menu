package xyz.teamgravity.m3expressivefabmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.TaskAlt
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.util.fastForEach
import xyz.teamgravity.m3expressivefabmenu.ui.theme.M3ExpressiveFABMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            M3ExpressiveFABMenuTheme {
                var expanded by remember { mutableStateOf(false) }

                Scaffold(
                    floatingActionButton = {
                        FloatingActionButtonMenu(
                            expanded = expanded,
                            button = {
                                ToggleFloatingActionButton(
                                    checked = expanded,
                                    onCheckedChange = {
                                        expanded = it
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (expanded) Icons.Rounded.Close else Icons.Rounded.Add,
                                        contentDescription = null,
                                        tint = if (expanded) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                        ) {
                            MENU.fastForEach { item ->
                                FloatingActionButtonMenuItem(
                                    onClick = {
                                        expanded = false
                                    },
                                    text = {
                                        Text(
                                            text = item.text
                                        )
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    Box(
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}

private val MENU = listOf(
    FabMenuItem(
        text = "Event",
        icon = Icons.Rounded.Event
    ),
    FabMenuItem(
        text = "Task",
        icon = Icons.Rounded.TaskAlt
    ),
    FabMenuItem(
        text = "Reminder",
        icon = Icons.Rounded.Notifications
    )
)

data class FabMenuItem(
    val text: String,
    val icon: ImageVector
)