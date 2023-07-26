package com.example.clear.utils.commonComponents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.OrangeYellow3
import com.example.clear.utils.fonts.FontFamilyClear


@Composable
fun ShowAlertDialogBox(
    showDialogBox: MutableState<Boolean>,
    title: String,
    content: String,
    confirmText: String,
    cancelString: String,
    onClick: () -> Unit
) {
    if (showDialogBox.value) {
        AlertDialog(
            onDismissRequest = { showDialogBox.value = false },
            title = {
                Text(
                    text = title,
                    style = TextStyle(fontFamily = FontFamilyClear.fontSemiBold, fontSize = 18.sp)
                )
            },
            text = {
                Text(
                    text = content,
                    style = TextStyle(fontFamily = FontFamilyClear.fontRegular, fontSize = 15.sp)
                )
            },
            confirmButton = {
                Button(colors = ButtonDefaults.buttonColors(containerColor = LightRed),
                    onClick = {
                        onClick.invoke()
                    }
                ) {
                    Text(
                        confirmText,
                        style = TextStyle(fontFamily = FontFamilyClear.fontMedium, fontSize = 15.sp)
                    )
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = OrangeYellow3),
                    onClick = { showDialogBox.value = false }
                ) {
                    Text(
                        cancelString,
                        style = TextStyle(
                            fontFamily = FontFamilyClear.fontRegular,
                            fontSize = 15.sp
                        )
                    )
                }
            }
        )
    }
}
