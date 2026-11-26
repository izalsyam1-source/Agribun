package com.example.data.source

import com.example.data.source.content.CaseStudiesContent
import com.example.data.source.content.CommoditiesContent
import com.example.data.source.content.GlossaryContent
import com.example.data.source.content.QuizQuestionsContent
import com.example.data.source.content.UnitsContent
import com.example.model.CaseStudy
import com.example.model.Commodity
import com.example.model.GlossaryTerm
import com.example.model.LearningUnit
import com.example.model.Question
import com.example.model.QuizKind

class ContentDataSource {

    fun getUnits(): List<LearningUnit> = UnitsContent.units

    fun getUnitById(unitId: String): LearningUnit? {
        return UnitsContent.units.find { it.id == unitId }
    }

    fun getCommodities(): List<Commodity> = CommoditiesContent.commodities

    fun getCommodityById(id: String): Commodity? {
        return CommoditiesContent.commodities.find { it.id == id }
    }

    fun getGlossaryTerms(): List<GlossaryTerm> = GlossaryContent.terms

    fun getCaseStudies(): List<CaseStudy> = CaseStudiesContent.caseStudies

    fun getCaseStudyById(id: String): CaseStudy? {
        return CaseStudiesContent.caseStudies.find { it.id == id }
    }

    fun getQuestionsForUnit(unitId: String): List<Question> {
        return when (unitId) {
            "unit-1" -> QuizQuestionsContent.unit1Questions
            "unit-2" -> QuizQuestionsContent.unit2Questions
            "unit-3" -> QuizQuestionsContent.unit3Questions
            "unit-4" -> QuizQuestionsContent.unit4Questions
            else -> emptyList()
        }
    }

    fun getPretestQuestions(): List<Question> = QuizQuestionsContent.pretestQuestions

    fun getPosttestQuestions(): List<Question> = QuizQuestionsContent.posttestQuestions

    fun getQuestionsByQuiz(kind: QuizKind, unitId: String?): List<Question> {
        return when (kind) {
            QuizKind.PRETEST -> getPretestQuestions()
            QuizKind.POSTTEST -> getPosttestQuestions()
            QuizKind.UNIT_QUIZ -> unitId?.let { getQuestionsForUnit(it) } ?: emptyList()
        }
    }
}
