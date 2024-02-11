package com.wikosac.galerihewan.ui.page

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wikosac.galerihewan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HitungBmiPage() {
    var berat by remember { mutableStateOf("") }
    var tinggi by remember { mutableStateOf("") }
    val radioOptions =
        listOf(stringResource(id = R.string.pria), stringResource(id = R.string.wanita))
    var (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    var bmi by remember { mutableStateOf(0f) }
    var kategoriId by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var beratError by remember { mutableStateOf(false) }
    var tinggiError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(36.dp)
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
            OutlinedTextField(
                value = berat,
                onValueChange = {
                    berat = it
                    beratError = false
                },
                label = { Text(text = stringResource(id = R.string.berat_badan)) },
                trailingIcon = {
                    if (!beratError) {
                        Text(text = "kg")
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Warning,
                            contentDescription = "Error",
                            tint = MaterialTheme.colorScheme.error,
                        )
                    }
                },
                singleLine = true,
                supportingText = {
                    if (beratError) {
                        Text(text = "Tidak boleh kosong", color = MaterialTheme.colorScheme.error)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.primary)
            )
            OutlinedTextField(
                value = tinggi,
                onValueChange = {
                    tinggi = it
                    tinggiError = false
                },
                label = { Text(text = stringResource(id = R.string.tinggi_badan)) },
                trailingIcon = {
                    if (!tinggiError) {
                        Text(text = "cm")
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Warning,
                            contentDescription = "Error",
                            tint = MaterialTheme.colorScheme.error,
                        )
                    }
                },
                singleLine = true,
                supportingText = {
                    if (tinggiError) {
                        Text(text = "Tidak boleh kosong", color = MaterialTheme.colorScheme.error)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.primary)
            )
            OutlinedTextField(
                value = "",
                onValueChange = { },
                readOnly = true,
                label = { },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
                trailingIcon = {
                    Row(
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            modifier = Modifier.selectable(
                                selected = (selectedOption == radioOptions[0]),
                                onClick = { onOptionSelected(radioOptions[0]) },
                                role = Role.RadioButton
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedOption == radioOptions[0],
                                onClick = null
                            )
                            Text(
                                text = radioOptions[0],
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Row(
                            modifier = Modifier.selectable(
                                selected = (selectedOption == radioOptions[1]),
                                onClick = { onOptionSelected(radioOptions[1]) },
                                role = Role.RadioButton
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedOption == radioOptions[1],
                                onClick = null
                            )
                            Text(
                                text = radioOptions[1],
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            )
            Button(
                onClick = {
                    if (berat.isEmpty()) {
                        beratError = true
                    } else if (tinggi.isEmpty()) {
                        tinggiError = true
                    }
                    bmi = hitungBmi(berat, tinggi, selectedOption, context)
                    kategoriId = getKategoriId(bmi, selectedOption)
                },
                contentPadding = PaddingValues(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(stringResource(id = R.string.hitung))
            }
        }
        Divider(thickness = 1.dp)
        if (bmi != 0f) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.bmi_x, bmi))
                Text(
                    text = stringResource(
                        id = R.string.kategori_x,
                        stringResource(id = kategoriId)
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        berat = ""
                        tinggi = ""
                        selectedOption = ""
                        onOptionSelected("")
                        bmi = 0f
                        kategoriId = 0
                        focusManager.clearFocus()
                    },
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(stringResource(id = R.string.reset))
                }
            }
        }
    }
}

private fun hitungBmi(berat: String, tinggi: String, gender: String, context: Context): Float {
    if (berat.isBlank()) {
        Toast.makeText(context, context.getText(R.string.berat_invalid), Toast.LENGTH_SHORT).show()
        return 0f
    } else if (tinggi.isBlank()) {
        Toast.makeText(context, context.getText(R.string.tinggi_invalid), Toast.LENGTH_SHORT).show()
        return 0f
    } else if (gender.isBlank()) {
        Toast.makeText(context, context.getText(R.string.gender_invalid), Toast.LENGTH_SHORT).show()
        return 0f
    }

    val mTinggi = tinggi.toFloat() / 100
    return berat.toFloat() / (mTinggi * mTinggi)
}

private fun getKategoriId(bmi: Float, radioOption: String): Int {
    return if (radioOption == "Pria") {
        when {
            bmi < 20.5 -> R.string.kurus
            bmi >= 27.0 -> R.string.gemuk
            else -> R.string.ideal
        }
    } else {
        when {
            bmi < 18.5 -> R.string.kurus
            bmi >= 25.0 -> R.string.gemuk
            else -> R.string.ideal
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HitungBmiPagePreview() {
    HitungBmiPage()
}
