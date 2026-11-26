package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfographicView(
    infographicId: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            when (infographicId) {
                "agribusiness_subsystems", "subsystems_flow" -> SubsystemsInfographic()
                "poac_cycle" -> PoacCycleInfographic()
                "soil_ph" -> SoilPhInfographic()
                "generative_vs_vegetative", "vegetative_methods" -> GenerativeVegetativeInfographic()
                "k3lh_safety", "maintenance_5t" -> K3lhSafetyInfographic()
                else -> CommodityHighlightsInfographic()
            }
        }
    }
}

@Composable
private fun SubsystemsInfographic() {
    Text(
        text = "DIAGRAM 4 PILAR SUBSISTEM AGRIBISNIS",
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    )
    Spacer(modifier = Modifier.height(12.dp))

    val items = listOf(
        Triple("1. Hulu (Upstream)", "Benih unggul, pupuk, alsintan", Icons.Default.Inventory2),
        Triple("2. Usahatani (On-Farm)", "Penanaman, perawatan & panen kebun", Icons.Default.Agriculture),
        Triple("3. Hilir (Downstream)", "Pengolahan pabrik & pemasaran hasil", Icons.Default.Factory),
        Triple("4. Penunjang (Supporting)", "Bank KUR, koperasi, litbang, PPL", Icons.Default.AccountBalance)
    )

    items.forEachIndexed { index, (title, desc, icon) ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    }
}

@Composable
private fun PoacCycleInfographic() {
    Text(
        text = "ALUR FUNGSI MANAJEMEN PERKEBUNAN (POAC)",
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    )
    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val steps = listOf(
            "P" to "Planning\n(Rencana)",
            "O" to "Organizing\n(Bagi Tugas)",
            "A" to "Actuating\n(Eksekusi)",
            "C" to "Controlling\n(Evaluasi)"
        )
        steps.forEach { (char, label) ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center
                        ),
                        maxLines = 2
                    )
                }
            }
        }
    }
}

@Composable
private fun SoilPhInfographic() {
    Text(
        text = "SPEKTRUM DERAJAT KEASAMAN TANAH (pH)",
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Tanaman perkebunan (Sawit, Karet, Kakao, Kopi) paling optimal di pH 5.5 - 6.5.",
        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
    )
    Spacer(modifier = Modifier.height(12.dp))

    // Colored gradient bar
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFFE53935), // Masam merah
                        Color(0xFFFFB300), // Sedang kuning
                        Color(0xFF43A047), // Netral hijau optimal
                        Color(0xFF1E88E5)  // Basa biru
                    )
                )
            )
    )
    Spacer(modifier = Modifier.height(4.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("pH 4.0\n(Sangat Masam)", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp))
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = MaterialTheme.colorScheme.primary
        ) {
            Text(
                "★ ZONA OPTIMAL (5.5 - 6.5)",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
        Text("pH 8.5\n(Alkali/Basa)", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp))
    }
}

@Composable
private fun GenerativeVegetativeInfographic() {
    Text(
        text = "PERBANDINGAN PEMBIAKAN TANAMAN",
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    )
    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.weight(1f)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "🌱 GENERATIF (BIJI)",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text("• Perakaran tunggang kokoh", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                Text("• Tahan angin & rebah", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                Text("• Sifat beragam (segregasi)", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                Text("• Panen lebih lama", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
            }
        }

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.weight(1f)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "🌿 VEGETATIF (STEK/OKULASI)",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text("• Sifat 100% sama induk", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                Text("• Cepat panen (TBM singkat)", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                Text("• Mutu getah/buah seragam", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
                Text("• Butuh keahlian okulasi", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp))
            }
        }
    }
}

@Composable
private fun K3lhSafetyInfographic() {
    Text(
        text = "CHECKLIST KESELAMATAN KERJA & APD (K3LH)",
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    )
    Spacer(modifier = Modifier.height(10.dp))

    val checks = listOf(
        "Helm Keselamatan (Safety Helmet) saat panen pohon tinggi",
        "Masker Respirator & Sarung Tangan Nitril untuk herbisida",
        "Sepatu Bot Bertutup Baja (Safety Shoes) anti-duri & ular",
        "SOP Zero Burning (Pembukaan lahan tanpa bakar)"
    )

    checks.forEach { text ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 3.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
            )
        }
    }
}

@Composable
private fun CommodityHighlightsInfographic() {
    Text(
        text = "KOMODITAS PERKEBUNAN UNGGULAN INDONESIA",
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        listOf("☕ Kopi", "🌳 Karet", "🍵 Teh", "🌴 Sawit", "🍫 Kakao").forEach {
            Text(it, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
        }
    }
}
