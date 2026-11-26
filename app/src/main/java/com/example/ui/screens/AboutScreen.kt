package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AgriTopAppBar

@Composable
fun AboutScreen(
    currentDarkMode: Boolean?,
    onSetDarkMode: (Boolean?) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AgriTopAppBar(
                title = "Tentang AgriBun",
                canNavigateBack = true,
                onNavigateBack = onNavigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // App Identity Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "AgriBun v1.0",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                            Text(
                                text = "Media Belajar Agribisnis Perkebunan SMK (Fase E)",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "AgriBun dirancang khusus sebagai media belajar mandiri bagi murid kelas X SMK program keahlian Agribisnis Tanaman, mata pelajaran Dasar-dasar Agribisnis Tanaman Perkebunan. Berjalan penuh tanpa koneksi internet (offline-first) dengan integrasi studi kasus autentik dan asesmen N-Gain.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            lineHeight = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }

            // Dark Mode Setting Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Brightness4,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Pengaturan Tema Tampilan",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = currentDarkMode == null,
                            onClick = { onSetDarkMode(null) },
                            label = { Text("Sistem") },
                            leadingIcon = if (currentDarkMode == null) {
                                { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null
                        )
                        FilterChip(
                            selected = currentDarkMode == false,
                            onClick = { onSetDarkMode(false) },
                            label = { Text("Terang") },
                            leadingIcon = if (currentDarkMode == false) {
                                { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null
                        )
                        FilterChip(
                            selected = currentDarkMode == true,
                            onClick = { onSetDarkMode(true) },
                            label = { Text("Gelap") },
                            leadingIcon = if (currentDarkMode == true) {
                                { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null
                        )
                    }
                }
            }

            // Capaian Pembelajaran (CP) Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "CAPAIAN PEMBELAJARAN (CP)",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = "Rujukan: Keputusan Kepala BSKAP No. 033/H/KR/2022",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Pada akhir fase E, murid mampu memahami proses bisnis dan manajemen agribisnis tanaman perkebunan secara menyeluruh; mengidentifikasi faktor-faktor yang memengaruhi produksi tanaman; menerapkan teknik pembiakan tanaman perkebunan secara generatif dan vegetatif; serta melaksanakan proses produksi, panen, pascapanen, dan penerapan Keselamatan, Kesehatan Kerja dan Lingkungan Hidup (K3LH).",
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "TUJUAN PEMBELAJARAN (TP):",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "• TP 1: Memahami sistem dan manajemen agribisnis perkebunan dari hulu hingga hilir serta fungsi POAC.\n" +
                               "• TP 2: Menganalisis faktor tanah, iklim, benih unggul, dan strategi pengendalian OPT.\n" +
                               "• TP 3: Menguasai teknik pembiakan tanaman generatif dan vegetatif (okulasi, sambung, stek).\n" +
                               "• TP 4: Menerapkan prosedur pemeliharaan, kriteria matang panen, pengolahan pascapanen bernilai tambah, dan standar K3LH.",
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp)
                    )
                }
            }

            // Credits Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "KREDIT & PENGEMBANGAN",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "• Rancangan: LK-1 Idea Card 'AgriBun'\n" +
                               "• Peruntukan: Murid & Guru Agribisnis Tanaman Perkebunan SMK\n" +
                               "• Standar Kurikulum: Kurikulum Merdeka SMK Fase E\n" +
                               "• Arsitektur: Android Native Clean MVVM (Jetpack Compose)",
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
