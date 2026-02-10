package kr.android.invitationlistapp.ui.theme

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun ThemeToggleSwitch(
    isDark: Boolean,
    onToggleDark: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(
        targetState = isDark,
        label = "ThemeTransition"
    )

    val trackColor by transition.animateColor(
        transitionSpec = {
            tween(
                durationMillis = 1000,
                easing = LinearOutSlowInEasing
            )
        },
        label = "TrackColor"
    ) { dark ->
        if (dark) Color(0xFF2F5B7C) else Color(0xFFFF8707)
    }

    val rotation by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 1100,
                easing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
            )
        },
        label = "Rotation"
    ) { dark ->
        if (dark) -270f else 0f
    }

    val scale by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing
            )
        },
        label = "Scale"
    ) { dark ->
        if (dark) 1.12f else 1.04f
    }

    Switch(
        checked = isDark,
        onCheckedChange = onToggleDark,
        modifier = modifier.height(30.dp),
        colors = SwitchDefaults.colors(
            checkedTrackColor = trackColor,
            uncheckedTrackColor = trackColor,
            checkedThumbColor = Color.White,
            uncheckedThumbColor = Color.White
        ),
        thumbContent = {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .graphicsLayer {
                        rotationZ = rotation
                        scaleX = scale
                        scaleY = scale
                    },
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = isDark,
                    transitionSpec = {
                        fadeIn(
                            tween(
                                durationMillis = 500,
                                delayMillis = 250
                            )
                        ) + scaleIn(
                            tween(500)
                        ) togetherWith
                                fadeOut(
                                    tween(400)
                                ) + scaleOut(
                            tween(400)
                        )
                    },
                    label = "IconMorph"
                ) { dark ->
                    Icon(
                        imageVector = if (dark)
                            Icons.Filled.NightsStay
                        else
                            Icons.Filled.WbSunny,
                        contentDescription = null,
                        tint = if (dark)
                            Color(0xFFFFE082)
                        else
                            Color(0xFFF16D08),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    )
}