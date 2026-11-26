package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.di.AppContainer
import com.example.model.BottomNavTab
import com.example.model.QuizKind
import com.example.model.ScreenDestination
import com.example.ui.components.AgriBottomNavigation
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.CaseStudyDetailScreen
import com.example.ui.screens.CaseStudyListScreen
import com.example.ui.screens.GalleryScreen
import com.example.ui.screens.LearningMapScreen
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.QuizHomeScreen
import com.example.ui.screens.QuizResultScreen
import com.example.ui.screens.QuizRunnerScreen
import com.example.ui.screens.ReportCardScreen
import com.example.ui.screens.UnitDetailScreen
import com.example.ui.theme.AgriBunTheme
import com.example.viewmodel.AppNavigationViewModel
import com.example.viewmodel.CaseStudyViewModel
import com.example.viewmodel.GalleryViewModel
import com.example.viewmodel.LearningMapViewModel
import com.example.viewmodel.OnboardingViewModel
import com.example.viewmodel.QuizViewModel
import com.example.viewmodel.ReportCardViewModel
import com.example.viewmodel.UnitDetailViewModel

@Composable
fun AgriBunApp(appContainer: AppContainer) {
    val navViewModel = remember { AppNavigationViewModel(appContainer.progressRepository) }
    val navState by navViewModel.navState.collectAsState()

    val systemDark = isSystemInDarkTheme()
    val isDark = navState.isDarkMode ?: systemDark

    AgriBunTheme(darkTheme = isDark) {
        val currentDestination = navState.currentDestination

        // Handle hardware Back button on sub-screens
        val canPop = navState.backStack.size > 1
        BackHandler(enabled = canPop) {
            navViewModel.popBack()
        }

        when (val dest = currentDestination) {
            is ScreenDestination.Onboarding -> {
                val onboardingViewModel = remember {
                    OnboardingViewModel(appContainer.progressRepository)
                }
                OnboardingScreen(
                    viewModel = onboardingViewModel,
                    onComplete = { takePretest ->
                        navViewModel.completeOnboarding()
                        if (takePretest) {
                            navViewModel.navigateTo(ScreenDestination.QuizRunner(QuizKind.PRETEST, null))
                        }
                    }
                )
            }

            is ScreenDestination.MainTabs -> {
                Scaffold(
                    bottomBar = {
                        AgriBottomNavigation(
                            selectedTab = navState.currentTab,
                            onTabSelected = { navViewModel.selectTab(it) }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (navState.currentTab) {
                            BottomNavTab.PETA -> {
                                val learningMapViewModel = remember {
                                    LearningMapViewModel(
                                        appContainer.contentRepository,
                                        appContainer.progressRepository,
                                        appContainer.calculateProgressUseCase
                                    )
                                }
                                LearningMapScreen(
                                    viewModel = learningMapViewModel,
                                    onOpenUnit = { unitId ->
                                        navViewModel.navigateTo(ScreenDestination.UnitDetail(unitId))
                                    },
                                    onOpenAbout = {
                                        navViewModel.navigateTo(ScreenDestination.About)
                                    }
                                )
                            }

                            BottomNavTab.MATERI -> {
                                // Default into Unit 1 or let student navigate units
                                val unitDetailViewModel = remember("unit-1-tab") {
                                    UnitDetailViewModel(
                                        unitId = "unit-1",
                                        initialSectionIndex = 0,
                                        contentRepository = appContainer.contentRepository,
                                        progressRepository = appContainer.progressRepository
                                    )
                                }
                                UnitDetailScreen(
                                    viewModel = unitDetailViewModel,
                                    onNavigateBack = { navViewModel.selectTab(BottomNavTab.PETA) },
                                    onStartCaseStudy = { caseStudyId ->
                                        navViewModel.navigateTo(ScreenDestination.CaseStudyDetail(caseStudyId))
                                    },
                                    onStartQuiz = { unitId ->
                                        navViewModel.navigateTo(ScreenDestination.QuizRunner(QuizKind.UNIT_QUIZ, unitId))
                                    }
                                )
                            }

                            BottomNavTab.GALERI -> {
                                val galleryViewModel = remember {
                                    GalleryViewModel(appContainer.contentRepository)
                                }
                                GalleryScreen(
                                    viewModel = galleryViewModel,
                                    onOpenAbout = {
                                        navViewModel.navigateTo(ScreenDestination.About)
                                    }
                                )
                            }

                            BottomNavTab.LATIHAN -> {
                                val caseStudies = remember { appContainer.contentRepository.getCaseStudies() }
                                val progressMap by appContainer.progressRepository.progressFlow.collectAsState()

                                CaseStudyListScreen(
                                    caseStudies = caseStudies,
                                    progressMap = progressMap,
                                    onSelectCaseStudy = { csId ->
                                        navViewModel.navigateTo(ScreenDestination.CaseStudyDetail(csId))
                                    },
                                    onOpenAbout = {
                                        navViewModel.navigateTo(ScreenDestination.About)
                                    }
                                )
                            }

                            BottomNavTab.RAPOR -> {
                                val reportCardViewModel = remember {
                                    ReportCardViewModel(
                                        appContainer.contentRepository,
                                        appContainer.progressRepository,
                                        appContainer.calculateProgressUseCase,
                                        appContainer.calculateNGainUseCase,
                                        appContainer.reportExporter
                                    )
                                }
                                ReportCardScreen(
                                    viewModel = reportCardViewModel,
                                    onOpenAbout = {
                                        navViewModel.navigateTo(ScreenDestination.About)
                                    }
                                )
                            }
                        }
                    }
                }
            }

            is ScreenDestination.UnitDetail -> {
                val unitDetailViewModel = remember(dest.unitId, dest.initialSectionIndex) {
                    UnitDetailViewModel(
                        unitId = dest.unitId,
                        initialSectionIndex = dest.initialSectionIndex,
                        contentRepository = appContainer.contentRepository,
                        progressRepository = appContainer.progressRepository
                    )
                }
                UnitDetailScreen(
                    viewModel = unitDetailViewModel,
                    onNavigateBack = { navViewModel.popBack() },
                    onStartCaseStudy = { caseStudyId ->
                        navViewModel.navigateTo(ScreenDestination.CaseStudyDetail(caseStudyId))
                    },
                    onStartQuiz = { unitId ->
                        navViewModel.navigateTo(ScreenDestination.QuizRunner(QuizKind.UNIT_QUIZ, unitId))
                    }
                )
            }

            is ScreenDestination.CaseStudyDetail -> {
                val caseStudyViewModel = remember(dest.caseStudyId) {
                    CaseStudyViewModel(
                        caseStudyId = dest.caseStudyId,
                        contentRepository = appContainer.contentRepository,
                        progressRepository = appContainer.progressRepository
                    )
                }
                CaseStudyDetailScreen(
                    viewModel = caseStudyViewModel,
                    onNavigateBack = { navViewModel.popBack() }
                )
            }

            is ScreenDestination.QuizRunner -> {
                val quizViewModel = remember(dest.kind, dest.unitId) {
                    QuizViewModel(
                        kind = dest.kind,
                        unitId = dest.unitId,
                        contentRepository = appContainer.contentRepository,
                        progressRepository = appContainer.progressRepository,
                        gradeQuizUseCase = appContainer.gradeQuizUseCase
                    )
                }
                QuizRunnerScreen(
                    viewModel = quizViewModel,
                    onNavigateBack = { navViewModel.popBack() },
                    onQuizCompleted = { result ->
                        navViewModel.popBack()
                        navViewModel.navigateTo(ScreenDestination.QuizResultScreen(result))
                    }
                )
            }

            is ScreenDestination.QuizResultScreen -> {
                QuizResultScreen(
                    result = dest.result,
                    onRetakeQuiz = {
                        navViewModel.popBack()
                        navViewModel.navigateTo(ScreenDestination.QuizRunner(dest.result.attempt.kind, dest.result.attempt.unitId))
                    },
                    onBackToQuizHome = {
                        navViewModel.popBack()
                        navViewModel.selectTab(BottomNavTab.RAPOR)
                    }
                )
            }

            is ScreenDestination.About -> {
                AboutScreen(
                    currentDarkMode = navState.isDarkMode,
                    onSetDarkMode = { navViewModel.toggleDarkMode(it) },
                    onNavigateBack = { navViewModel.popBack() }
                )
            }
        }
    }
}
