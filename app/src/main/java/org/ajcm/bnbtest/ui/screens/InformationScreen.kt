package org.ajcm.bnbtest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color definitions matching the design
private val BrandGreen = Color(0xFF00A859)
private val BrandPurple = Color(0xFF6B2D83)
private val OffWhiteBackground = Color(0xFFF3F4F6)
private val CardLabelColor = Color(0xFF2D3748)
private val CardValueColor = Color(0xFF1A202C)
private val PlaceholderColor = Color(0xFFA0AEC0)
private val DividerColor = Color(0xFFE2E8F0)

/**
 * State container for the Information Screen input data.
 */
data class InformationFormState(
    val phoneNumber: String = "71234567",
    val idNumber: String = "412345",
    val complement: String = "1D"
)

@Composable
fun InformationScreen(
    modifier: Modifier = Modifier,
    initialState: InformationFormState = InformationFormState(),
    currentStep: Int = 1,
    totalSteps: Int = 4,
    onBackClick: () -> Unit = {},
    onNextClick: (InformationFormState) -> Unit = {}
) {
    var phoneNumber by remember { mutableStateOf(initialState.phoneNumber) }
    var idNumber by remember { mutableStateOf(initialState.idNumber) }
    var complement by remember { mutableStateOf(initialState.complement) }

    InformationContent(
        modifier = modifier,
        phoneNumber = phoneNumber,
        onPhoneNumberChange = { phoneNumber = it },
        idNumber = idNumber,
        onIdNumberChange = { idNumber = it },
        complement = complement,
        onComplementChange = { complement = it },
        currentStep = currentStep,
        totalSteps = totalSteps,
        onBackClick = onBackClick,
        onNextClick = {
            onNextClick(
                InformationFormState(
                    phoneNumber = phoneNumber,
                    idNumber = idNumber,
                    complement = complement
                )
            )
        }
    )
}

@Composable
fun InformationContent(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    idNumber: String,
    onIdNumberChange: (String) -> Unit,
    complement: String,
    onComplementChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    currentStep: Int = 1,
    totalSteps: Int = 4,
    onBackClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BrandGreen)
            .statusBarsPadding()
    ) {
        // Top Navigation Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Atrás",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(
                text = "Información",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Header Section with Step Progress & Instructions
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // Step Progress Indicator Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Purple Circle Badge with + icon
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .background(color = BrandPurple, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "PASO $currentStep / $totalSteps",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Información",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    // Custom Linear Progress Bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color.White)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction = currentStep.toFloat() / totalSteps.toFloat())
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(3.dp))
                                .background(BrandPurple)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Section Titles
            Text(
                text = "Ingresa tus datos",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "¡Únete a nuestra app hoy!\nCompleta los siguientes datos para comenzar a disfrutar de tu Bille.",
                color = Color.White.copy(alpha = 0.95f),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Body Sheet Section (Light Grey Background with Top Rounded Corners)
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            color = OffWhiteBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Form Fields Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        // Phone Field
                        InputFieldRow(
                            label = "Número de celular:",
                            value = phoneNumber,
                            onValueChange = onPhoneNumberChange,
                            placeholder = "Ingresa tu celular",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone,
                                imeAction = ImeAction.Next
                            )
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 1.dp,
                            color = DividerColor
                        )

                        // ID Field
                        InputFieldRow(
                            label = "Número de carnet:",
                            value = idNumber,
                            onValueChange = onIdNumberChange,
                            placeholder = "Ingresa tu carnet",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 1.dp,
                            color = DividerColor
                        )

                        // Complement Field
                        InputFieldRow(
                            label = "Complemento (opcional):",
                            value = complement,
                            onValueChange = onComplementChange,
                            placeholder = "Ej. 1D",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Next Button
                Button(
                    onClick = onNextClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandGreen,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Siguiente",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

/**
 * Custom Input Row displaying a bold label on the left and a right-aligned editable input field on the right.
 */
@Composable
private fun InputFieldRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = CardLabelColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(2f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = PlaceholderColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    color = CardValueColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End
                ),
                keyboardOptions = keyboardOptions,
                cursorBrush = SolidColor(BrandGreen),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun InformationScreenPreview() {
    InformationScreen()
}
