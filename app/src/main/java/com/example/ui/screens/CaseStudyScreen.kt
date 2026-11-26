package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CaseStudy
import com.example.model.UiState
import com.example.model.UnitProgress
import com.example.ui.components.AgriTopAppBar
import com.example.ui.components.ErrorStateView
import com.example.ui.components.LoadingStateView
import com.example.ui.components.QuestionFeedbackBanner
import com.example.ui.components.QuestionRenderer
import com.example.viewmodel.CaseStudyData
import com.example.viewmodel.CaseStudyViewModel

@Composable
fun CaseStudyListScreen(
    caseStudies: List<CaseStudy>,
    progressMap: Map<String, UnitProgress>,
    onSelectCaseStudy: (caseStudyId: String) -> Unit,
    onOpenAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AgriTopAppBar(
                title = "Studi Kasus & Latihan",
                canNavigateBack = false,
                onAboutClick = onOpenAbout
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Latihan Pemecahan Masalah Autentik",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Setiap kasus memuat skenario perkebunan realistis dengan soal pilihan ganda, menjodohkan, dan menyusun urutan.",
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                }
            }

            items(caseStudies) { cs ->
                val isDone = progressMap[cs.unitId]?.caseStudyDone == true

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectCaseStudy(cs.id) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = MaterialTheme.colorScheme.primaryContainer
                            ) {
                                Text(
                                    text = cs.tp.label,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }

                            if (isDone) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = Color(0xFF2E7D32),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Tuntas",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFF2E7D32),
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = cs.title,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = cs.scenario,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 18.sp
                            ),
                            maxLines = 3
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${cs.questions.size} Pertanyaan Praktik",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Button(
                                onClick = { onSelectCaseStudy(cs.id) },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(if (isDone) "Ulangi Kasus" else "Mulai Pecahkan")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CaseStudyDetailScreen(
    viewModel: CaseStudyViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is UiState.Loading -> LoadingStateView()
        is UiState.Error -> ErrorStateView(message = state.message, onRetry = onNavigateBack)
        is UiState.Success -> {
            CaseStudyDetailContent(
                data = state.data,
                onNavigateBack = onNavigateBack,
                onSelectMc = { qId, opt -> viewModel.selectMultipleChoiceOption(qId, opt) },
                onSetMatching = { qId, l, r -> viewModel.setMatchingPair(qId, l, r) },
                onMoveOrder = { qId, f, t -> viewModel.reorderItem(qId, f, t) },
                onSubmit = { viewModel.submitCurrentAnswer() },
                onRetry = { qId -> viewModel.retryQuestion(qId) },
                onNext = { viewModel.nextQuestion() },
                onPrev = { viewModel.prevQuestion() },
                modifier = modifier
            )
        }
        UiState.Empty -> LoadingStateView()
    }
}

@Composable
private fun CaseStudyDetailContent(
    data: CaseStudyData,
    onNavigateBack: () -> Unit,
    onSelectMc: (String, Int) -> Unit,
    onSetMatching: (String, String, String) -> Unit,
    onMoveOrder: (String, Int, Int) -> Unit,
    onSubmit: () -> Unit,
    onRetry: (String) -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentQ = data.currentQuestion ?: return
    val qIndex = data.currentQuestionIndex + 1

    Scaffold(
        topBar = {
            AgriTopAppBar(
                title = data.caseStudy.title,
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
        ) {
            // Step Progress Bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Soal $qIndex dari ${data.totalQuestions}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    if (data.isCompleted) {
                        Text(
                            text = "✓ Kasus Selesai",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32)
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { qIndex.toFloat() / data.totalQuestions },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Scrollable question body
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Scenario Card
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "📖 SKENARIO KASUS:",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = data.caseStudy.scenario,
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp)
                        )
                    }
                }

                // Question renderer
                QuestionRenderer(
                    question = currentQ,
                    selectedMcIndex = data.multipleChoiceAnswers[currentQ.id],
                    onSelectMc = { onSelectMc(currentQ.id, it) },
                    userPairs = data.matchingAnswers[currentQ.id] ?: emptyMap(),
                    onPairSelected = { l, r -> onSetMatching(currentQ.id, l, r) },
                    orderedList = data.orderingAnswers[currentQ.id] ?: emptyList(),
                    onMoveOrderItem = { f, t -> onMoveOrder(currentQ.id, f, t) }
                )

                // Feedback Banner if submitted
                if (data.isCurrentQuestionSubmitted) {
                    QuestionFeedbackBanner(
                        isCorrect = data.isCurrentQuestionCorrect,
                        explanation = currentQ.explanation,
                        onRetry = { onRetry(currentQ.id) }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Bottom action buttons
            Surface(
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = onPrev,
                        enabled = data.currentQuestionIndex > 0,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Sebelumnya")
                    }

                    if (!data.isCurrentQuestionSubmitted) {
                        Button(
                            onClick = onSubmit,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("submit_case_question_button")
                        ) {
                            Text("Periksa Jawaban")
                        }
                    } else {
                        if (data.currentQuestionIndex < data.totalQuestions - 1) {
                            Button(
                                onClick = onNext,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text("Lanjut Soal Berikutnya")
                            }
                        } else {
                            Button(
                                onClick = onNavigateBack,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text("Selesai & Kembali")
                            }
                        }
                    }
                }
            }
        }
    }
}
