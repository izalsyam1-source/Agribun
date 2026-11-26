package com.example.data.repository

import com.example.data.source.ContentDataSource
import com.example.model.CaseStudy
import com.example.model.Commodity
import com.example.model.GlossaryTerm
import com.example.model.LearningUnit
import com.example.model.Question
import com.example.model.QuizKind

interface IContentRepository {
    fun getUnits(): List<LearningUnit>
    fun getUnit(id: String): LearningUnit?
    fun getCommodities(): List<Commodity>
    fun getCommodity(id: String): Commodity?
    fun getGlossary(): List<GlossaryTerm>
    fun searchGlossary(query: String, letterFilter: String?): List<GlossaryTerm>
    fun getCaseStudies(): List<CaseStudy>
    fun getCaseStudy(id: String): CaseStudy?
    fun getQuizQuestions(kind: QuizKind, unitId: String?): List<Question>
}

class ContentRepository(
    private val dataSource: ContentDataSource = ContentDataSource()
) : IContentRepository {

    override fun getUnits(): List<LearningUnit> = dataSource.getUnits()

    override fun getUnit(id: String): LearningUnit? = dataSource.getUnitById(id)

    override fun getCommodities(): List<Commodity> = dataSource.getCommodities()

    override fun getCommodity(id: String): Commodity? = dataSource.getCommodityById(id)

    override fun getGlossary(): List<GlossaryTerm> = dataSource.getGlossaryTerms()

    override fun searchGlossary(query: String, letterFilter: String?): List<GlossaryTerm> {
        var list = dataSource.getGlossaryTerms()
        if (!letterFilter.isNullOrBlank() && letterFilter != "SEMUA") {
            list = list.filter { it.term.startsWith(letterFilter, ignoreCase = true) }
        }
        if (query.isNotBlank()) {
            val q = query.trim().lowercase()
            list = list.filter {
                it.term.lowercase().contains(q) ||
                it.definition.lowercase().contains(q) ||
                it.category.lowercase().contains(q)
            }
        }
        return list.sortedBy { it.term }
    }

    override fun getCaseStudies(): List<CaseStudy> = dataSource.getCaseStudies()

    override fun getCaseStudy(id: String): CaseStudy? = dataSource.getCaseStudyById(id)

    override fun getQuizQuestions(kind: QuizKind, unitId: String?): List<Question> {
        return dataSource.getQuestionsByQuiz(kind, unitId)
    }
}
