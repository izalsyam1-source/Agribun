package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.Commodity
import com.example.model.GlossaryTerm
import com.example.model.UiState
import com.example.ui.components.AgriTopAppBar
import com.example.ui.components.EmptyStateView
import com.example.ui.components.LoadingStateView
import com.example.viewmodel.GalleryData
import com.example.viewmodel.GallerySubTab
import com.example.viewmodel.GalleryViewModel

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel,
    onOpenAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AgriTopAppBar(
                title = "Galeri & Glosarium",
                canNavigateBack = false,
                onAboutClick = onOpenAbout
            )
        },
        modifier = modifier
    ) { innerPadding ->
        when (val state = uiState) {
            is UiState.Loading -> LoadingStateView()
            is UiState.Error -> EmptyStateView("Kesalahan", state.message)
            is UiState.Success -> {
                GalleryContent(
                    data = state.data,
                    onSwitchSubTab = { viewModel.switchSubTab(it) },
                    onSelectCommodity = { viewModel.selectCommodity(it) },
                    onToggleCompare = { viewModel.toggleCompareCommodity(it) },
                    onClearCompare = { viewModel.clearComparison() },
                    onSearchQuery = { viewModel.updateSearchQuery(it) },
                    onFilterLetter = { viewModel.filterByLetter(it) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            UiState.Empty -> LoadingStateView()
        }
    }
}

@Composable
private fun GalleryContent(
    data: GalleryData,
    onSwitchSubTab: (GallerySubTab) -> Unit,
    onSelectCommodity: (Commodity?) -> Unit,
    onToggleCompare: (Commodity) -> Unit,
    onClearCompare: () -> Unit,
    onSearchQuery: (String) -> Unit,
    onFilterLetter: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = data.activeSubTab.ordinal,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Tab(
                selected = data.activeSubTab == GallerySubTab.KOMODITAS,
                onClick = { onSwitchSubTab(GallerySubTab.KOMODITAS) },
                text = { Text("5 Komoditas Perkebunan", fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = data.activeSubTab == GallerySubTab.GLOSARIUM,
                onClick = { onSwitchSubTab(GallerySubTab.GLOSARIUM) },
                text = { Text("Glosarium Istilah (A–Z)", fontWeight = FontWeight.Bold) }
            )
        }

        when (data.activeSubTab) {
            GallerySubTab.KOMODITAS -> {
                CommoditiesTabContent(
                    commodities = data.commodities,
                    compareList = data.compareList,
                    onSelect = onSelectCommodity,
                    onToggleCompare = onToggleCompare
                )
            }
            GallerySubTab.GLOSARIUM -> {
                GlossaryTabContent(
                    terms = data.glossaryTerms,
                    searchQuery = data.searchQuery,
                    activeLetter = data.activeLetterFilter,
                    onSearchQuery = onSearchQuery,
                    onFilterLetter = onFilterLetter
                )
            }
        }
    }

    // Modal Detail Dialog
    if (data.selectedCommodity != null) {
        CommodityDetailDialog(
            commodity = data.selectedCommodity,
            onDismiss = { onSelectCommodity(null) }
        )
    }

    // Side-by-side comparison Dialog
    if (data.isComparing && data.compareList.size == 2) {
        ComparisonDialog(
            c1 = data.compareList[0],
            c2 = data.compareList[1],
            onDismiss = onClearCompare
        )
    }
}

@Composable
private fun CommoditiesTabContent(
    commodities: List<Commodity>,
    compareList: List<Commodity>,
    onSelect: (Commodity) -> Unit,
    onToggleCompare: (Commodity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Comparison instruction banner
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Compare,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Fitur Bandingkan Komoditas",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = if (compareList.isEmpty()) "Pilih 2 tombol '+ Bandingkan' untuk melihat tabel perbandingan berdampingan."
                               else "Dipilih: ${compareList.joinToString { it.name }} (${compareList.size}/2)",
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            items(commodities) { commodity ->
                val isCompared = compareList.any { it.id == commodity.id }
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(commodity) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = commodity.emoji, fontSize = 36.sp)
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = commodity.name,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = commodity.latinName,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontStyle = FontStyle.Italic,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                            }

                            // Compare toggle button
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isCompared) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier
                                    .clickable { onToggleCompare(commodity) }
                                    .padding(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    if (isCompared) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                    }
                                    Text(
                                        text = if (isCompared) "Dipilih" else "+ Bandingkan",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = if (isCompared) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = commodity.description,
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "💡 ${commodity.funFact}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontSize = 11.sp
                                ),
                                maxLines = 1,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Buka Detail →",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CommodityDetailDialog(
    commodity: Commodity,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = commodity.emoji, fontSize = 32.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = commodity.name,
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = commodity.latinName,
                                style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.primary)
                            )
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(text = commodity.description, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("Syarat Tumbuh & Agroklimat")
                commodity.growthRequirements.forEach {
                    BulletText(it)
                }

                Spacer(modifier = Modifier.height(14.dp))
                SectionHeader("Metode Pembiakan Utama")
                Text(text = commodity.propagation, style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.height(14.dp))
                SectionHeader("Tahap Produksi & Budidaya")
                commodity.productionStages.forEach {
                    BulletText(it)
                }

                Spacer(modifier = Modifier.height(14.dp))
                SectionHeader("Penanganan Pascapanen")
                commodity.postHarvest.forEach {
                    BulletText(it)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "✨ Fakta Menarik",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = commodity.funFact,
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSecondaryContainer)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tutup")
                }
            }
        }
    }
}

@Composable
private fun ComparisonDialog(
    c1: Commodity,
    c2: Commodity,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚖️ Perbandingan Komoditas",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Table Header
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Aspek",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${c1.emoji} ${c1.name}",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.weight(1.5f)
                    )
                    Text(
                        text = "${c2.emoji} ${c2.name}",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary),
                        modifier = Modifier.weight(1.5f)
                    )
                }

                ComparisonRow("Nama Latin", c1.latinName, c2.latinName)
                ComparisonRow("Pembiakan", c1.propagation, c2.propagation)
                ComparisonRow("Syarat Iklim", c1.growthRequirements.firstOrNull() ?: "-", c2.growthRequirements.firstOrNull() ?: "-")
                ComparisonRow("Pascapanen Kunci", c1.postHarvest.firstOrNull() ?: "-", c2.postHarvest.firstOrNull() ?: "-")

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tutup Perbandingan")
                }
            }
        }
    }
}

@Composable
private fun ComparisonRow(aspect: String, val1: String, val2: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(text = aspect, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), modifier = Modifier.weight(1f))
        Text(text = val1, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp), modifier = Modifier.weight(1.5f))
        Text(text = val2, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp), modifier = Modifier.weight(1.5f))
    }
}

@Composable
private fun GlossaryTabContent(
    terms: List<GlossaryTerm>,
    searchQuery: String,
    activeLetter: String,
    onSearchQuery: (String) -> Unit,
    onFilterLetter: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQuery,
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            placeholder = { Text("Cari istilah (mis. hulu, okulasi, K3LH, pH)...") },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("glossary_search_input")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Alphabet chips
        val letters = listOf("SEMUA") + ('A'..'Z').map { it.toString() }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            letters.forEach { letter ->
                val isSelected = letter == activeLetter
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable { onFilterLetter(letter) }
                ) {
                    Text(
                        text = letter,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Terms count
        Text(
            text = "Ditemukan ${terms.size} istilah",
            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(terms) { item ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.term,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.primaryContainer
                            ) {
                                Text(
                                    text = item.category,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = item.definition, style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp))

                        if (item.example != null) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Contoh di kebun: ${item.example}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontStyle = FontStyle.Italic,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun BulletText(text: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
        Text("• ", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text(text, style = MaterialTheme.typography.bodySmall)
    }
}
