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
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.domain.GradeQuizUseCase.UserAnswer
import com.example.model.ExamAttempt
import com.example.model.LearningUnit
import com.example.model.QuizKind
import com.example.model.QuizResult
import com.example.model.UiState
import com.example.ui.components.AgriTopAppBar
import com.example.ui.components.ErrorStateView
import com.example.ui.components.LoadingStateView
import com.example.ui.components.QuestionRenderer
import com.example.viewmodel.QuizSessionData
import com.example.viewmodel.QuizViewModel

@Composable
fun QuizHomeScreen(
    units: List<LearningUnit>,
    attempts: List<ExamAttempt>,
    onStartQuiz: (kind: QuizKind, unitId: String?) -> Unit,
    onOpenAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AgriTopAppBar(
                title = "Kuis & Asesmen Belajar",
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
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Quiz,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Kriteria Ketercapaian (KKTP: 75)",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Kerjakan Pretest, 4 Kuis Formatif Unit, dan Posttest untuk mengukur peningkatan pemahaman (N-Gain).",
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                }
            }

            // Pretest Card
            item {
                val pretestAttempt = attempts.filter { it.kind == QuizKind.PRETEST }.maxByOrNull { it.takenAt }
                QuizHubCard(
                    title = "Pretest (Asesmen Diagnostik Awal)",
                    subtitle = "10 Soal campuran TP 1–4 untuk mengukur pemahaman awal Anda.",
                    attempt = pretestAttempt,
                    badgeLabel = "Diagnostik",
                    badgeColor = MaterialTheme.colorScheme.secondary,
                    onStart = { onStartQuiz(QuizKind.PRETEST, null) }
                )
            }

            // Unit Quizzes
            item {
                Text(
                    text = "KUIS FORMATIF PER UNIT (8 SOAL)",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(units) { unit ->
                val unitAttempt = attempts.filter { it.unitId == unit.id }.maxByOrNull { it.takenAt }
                QuizHubCard(
                    title = "Kuis ${unit.tp.number}: ${unit.title}",
                    subtitle = "8 Soal pilihan ganda, menjodohkan & menyusun urutan materi ${unit.tp.label}.",
                    attempt = unitAttempt,
                    badgeLabel = "Unit ${unit.tp.number}",
                    badgeColor = MaterialTheme.colorScheme.primary,
                    onStart = { onStartQuiz(QuizKind.UNIT_QUIZ, unit.id) }
                )
            }

            // Posttest Card
            item {
                val posttestAttempt = attempts.filter { it.kind == QuizKind.POSTTEST }.maxByOrNull { it.takenAt }
                QuizHubCard(
                    title = "Posttest (Asesmen Sumatif Akhir)",
                    subtitle = "10 Soal paralel untuk menghitung efektivitas belajar N-Gain Anda.",
                    attempt = posttestAttempt,
                    badgeLabel = "Sumatif Akhir",
                    badgeColor = Color(0xFFE65100),
                    onStart = { onStartQuiz(QuizKind.POSTTEST, null) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun QuizHubCard(
    title: String,
    subtitle: String,
    attempt: ExamAttempt?,
    badgeLabel: String,
    badgeColor: Color,
    onStart: () -> Unit
) {
    val isPassed = attempt != null && attempt.score >= 75

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = badgeColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = badgeLabel,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = badgeColor
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                if (attempt != null) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isPassed) Color(0xFFE8F5E9) else Color(0xFFFFF3E0)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Icon(
                                imageVector = if (isPassed) Icons.Default.CheckCircle else Icons.Default.Star,
                                contentDescription = null,
                                tint = if (isPassed) Color(0xFF2E7D32) else Color(0xFFF57C00),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Nilai: ${attempt.score} (${if (isPassed) "Tuntas" else "Belum"})",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (isPassed) Color(0xFF2E7D32) else Color(0xFFF57C00)
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onStart,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (attempt != null) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(if (attempt != null) "Ulangi Kuis" else "Mulai Kuis")
                }
            }
        }
    }
}

@Composable
fun QuizRunnerScreen(
    viewModel: QuizViewModel,
    onNavigateBack: () -> Unit,
    onQuizCompleted: (QuizResult) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is UiState.Loading -> LoadingStateView()
        is UiState.Error -> ErrorStateView(message = state.message, onRetry = onNavigateBack)
        is UiState.Success -> {
            val data = state.data
            if (data.result != null) {
                onQuizCompleted(data.result)
            } else {
                QuizRunnerContent(
                    data = data,
                    onNavigateBack = onNavigateBack,
                    onSelectMc = { viewModel.selectMultipleChoiceOption(it) },
                    onSetMatching = { l, r -> viewModel.selectMatchingPair(l, r) },
                    onMoveOrder = { f, t -> viewModel.moveOrderItem(f, t) },
                    onNext = { viewModel.nextQuestion() },
                    onPrev = { viewModel.prevQuestion() },
                    onShowSubmitDialog = { viewModel.showSubmitDialog(it) },
                    onSubmit = { viewModel.submitQuiz() },
                    modifier = modifier
                )
            }
        }
        UiState.Empty -> LoadingStateView()
    }
}

@Composable
private fun QuizRunnerContent(
    data: QuizSessionData,
    onNavigateBack: () -> Unit,
    onSelectMc: (Int) -> Unit,
    onSetMatching: (String, String) -> Unit,
    onMoveOrder: (Int, Int) -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    onShowSubmitDialog: (Boolean) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentQ = data.currentQuestion ?: return
    val qNumber = data.currentIndex + 1

    Scaffold(
        topBar = {
            AgriTopAppBar(
                title = data.kind.title,
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
            // Step header bar
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
                        text = "Soal $qNumber dari ${data.totalQuestions}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = "${data.answeredCount} terjawab",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { qNumber.toFloat() / data.totalQuestions },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Question body
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val ans = data.userAnswers[currentQ.id]
                val mcSelected = (ans as? UserAnswer.MultipleChoice)?.selectedIndex
                val matchPairs = (data.matchingDrafts[currentQ.id] ?: emptyMap())
                val orderedList = data.orderingDrafts[currentQ.id] ?: emptyList()

                QuestionRenderer(
                    question = currentQ,
                    selectedMcIndex = mcSelected,
                    onSelectMc = onSelectMc,
                    userPairs = matchPairs,
                    onPairSelected = onSetMatching,
                    orderedList = orderedList,
                    onMoveOrderItem = onMoveOrder
                )

                Spacer(modifier = Modifier.height(32.dp))
            }

            // Bottom Buttons
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
                        enabled = !data.isFirstQuestion,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Sebelumnya")
                    }

                    if (!data.isLastQuestion) {
                        Button(
                            onClick = onNext,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Berikutnya")
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    } else {
                        Button(
                            onClick = { onShowSubmitDialog(true) },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            modifier = Modifier.testTag("finish_quiz_button")
                        ) {
                            Text("Kirim Jawaban")
                        }
                    }
                }
            }
        }
    }

    // Submit confirmation dialog
    if (data.showSubmitConfirmDialog) {
        AlertDialog(
            onDismissRequest = { onShowSubmitDialog(false) },
            title = { Text("Kirimkan Jawaban Kuis?") },
            text = {
                Text(
                    text = "Anda telah menjawab ${data.answeredCount} dari ${data.totalQuestions} soal. Nilai dan pembahasan akan langsung ditampilkan."
                )
            },
            confirmButton = {
                Button(onClick = onSubmit) {
                    Text("Ya, Kumpulkan")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { onShowSubmitDialog(false) }) {
                    Text("Periksa Lagi")
                }
            }
        )
    }
}
