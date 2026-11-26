package com.example.di

import android.content.Context
import com.example.data.repository.ContentRepository
import com.example.data.repository.IContentRepository
import com.example.data.repository.IProgressRepository
import com.example.data.repository.ProgressRepository
import com.example.data.source.ContentDataSource
import com.example.data.source.LocalDataSource
import com.example.domain.CalculateNGainUseCase
import com.example.domain.CalculateProgressUseCase
import com.example.domain.GradeQuizUseCase
import com.example.domain.ReportExporter

class AppContainer(context: Context) {
    val localDataSource: LocalDataSource = LocalDataSource(context.applicationContext)
    val contentDataSource: ContentDataSource = ContentDataSource()

    val contentRepository: IContentRepository = ContentRepository(contentDataSource)
    val progressRepository: IProgressRepository = ProgressRepository(localDataSource)

    val gradeQuizUseCase: GradeQuizUseCase = GradeQuizUseCase()
    val calculateNGainUseCase: CalculateNGainUseCase = CalculateNGainUseCase()
    val calculateProgressUseCase: CalculateProgressUseCase = CalculateProgressUseCase()
    val reportExporter: ReportExporter = ReportExporter()
}
