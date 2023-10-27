package com.wikosac.galerihewan.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wikosac.galerihewan.R

@Composable
fun HitungBmiPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.bmi_intro),
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(1f),
            )
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var berat by remember { mutableStateOf("") }
                Text(
                    text = stringResource(id = R.string.berat_badan),
                    modifier = Modifier.fillMaxWidth(0.3f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                TextField(
                    value = berat,
                    onValueChange = { berat = it },
                    trailingIcon = { Text(text = "kg") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var tinggi by remember { mutableStateOf("") }
                Text(
                    text = stringResource(id = R.string.tinggi_badan),
                    modifier = Modifier.fillMaxWidth(0.3f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                TextField(
                    value = tinggi,
                    onValueChange = { tinggi = it },
                    trailingIcon = { Text(text = "cm") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.jenis_kelamin),
                    modifier = Modifier.fillMaxWidth(0.3f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    modifier = Modifier.selectableGroup(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val radioOptions = listOf(stringResource(id = R.string.pria), stringResource(id = R.string.wanita))
                    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
                    radioOptions.forEach { text ->
                        Row(
                            modifier = Modifier.selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) },
                                role = Role.RadioButton
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
            Button(
                onClick = {},
                contentPadding = PaddingValues(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(stringResource(id = R.string.hitung))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HitungBmiPagePreview() {
    HitungBmiPage()
}
