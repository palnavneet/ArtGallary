package com.app.exibhitease.presentation.settings.components

import androidx.compose.material3.SwitchDefaults
import androidx.compose.ui.res.colorResource

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.exibhitease.R


@Composable
fun AiPlannerSettings(
    isChecked: Boolean,
    displayText : String,
    displayIcon : Painter,
    onCheckChange: (Boolean) -> Unit
) {
    val poppins_semiBold = FontFamily(Font(R.font.poppins_semi_bold))
    val poppins_Bold = FontFamily(Font(R.font.poppins_bold))
    val poppins_regular = FontFamily(Font(R.font.poppins_regular))
    val poppins_light = FontFamily(Font(R.font.poppins_light))
    val poppins_medium = FontFamily(Font(R.font.poppins_medium))

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(30.dp),
                painter = displayIcon,
                contentDescription = null,
                tint = colorResource(id = R.color.element_icon_settings)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 5.dp),
                text = displayText,
                fontSize = 15.sp,
                fontFamily = poppins_Bold,
                color = colorResource(id = R.color.element_icon_settings)
            )
        }
        SwitchM3(
            modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.CenterEnd),
            isChecked = isChecked,
            onCheckChange = {
                onCheckChange(it)
            }
        )
    }
}
@Composable
private fun SwitchM3(
    modifier: Modifier = Modifier,
    isChecked : Boolean,
    onCheckChange : (Boolean) -> Unit
){

    Switch(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = {
            onCheckChange(it)
        },
        colors = SwitchDefaults.colors(
            checkedBorderColor = colorResource(id = R.color.system_color_blue),
            uncheckedBorderColor = colorResource(id = R.color.element_switch),
            checkedThumbColor = colorResource(id = R.color.system_color_white),
            uncheckedThumbColor = colorResource(id = R.color.system_color_white),
            checkedTrackColor = colorResource(id = R.color.system_color_blue),
            uncheckedTrackColor = colorResource(id = R.color.element_switch)
        )
    )
}