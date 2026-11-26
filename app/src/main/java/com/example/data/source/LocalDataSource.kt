package com.example.data.source

import android.content.Context
import android.content.SharedPreferences
import com.example.model.AppSettings
import com.example.model.ExamAttempt
import com.example.model.QuizKind
import com.example.model.UnitProgress
import com.example.model.UserProfile
import org.json.JSONArray
import org.json.JSONObject

class LocalDataSource(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("agribun.v1.prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_PROFILE = "agribun.v1.profile"
        private const val KEY_PROGRESS = "agribun.v1.progress"
        private const val KEY_ATTEMPTS = "agribun.v1.attempts"
        private const val KEY_SETTINGS = "agribun.v1.settings"
    }

    fun getProfile(): UserProfile {
        return try {
            val jsonStr = prefs.getString(KEY_PROFILE, null) ?: return UserProfile()
            val json = JSONObject(jsonStr)
            UserProfile(
                name = json.optString("name", ""),
                className = json.optString("className", ""),
                createdAt = json.optLong("createdAt", System.currentTimeMillis())
            )
        } catch (e: Exception) {
            UserProfile()
        }
    }

    fun saveProfile(profile: UserProfile) {
        try {
            val json = JSONObject().apply {
                put("name", profile.name)
                put("className", profile.className)
                put("createdAt", profile.createdAt)
            }
            prefs.edit().putString(KEY_PROFILE, json.toString()).apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getProgress(): Map<String, UnitProgress> {
        return try {
            val jsonStr = prefs.getString(KEY_PROGRESS, null) ?: return emptyMap()
            val obj = JSONObject(jsonStr)
            val result = mutableMapOf<String, UnitProgress>()
            val keys = obj.keys()
            while (keys.hasNext()) {
                val unitId = keys.next()
                val unitObj = obj.getJSONObject(unitId)
                val sectionsArray = unitObj.optJSONArray("sectionsRead") ?: JSONArray()
                val sectionsSet = mutableSetOf<String>()
                for (i in 0 until sectionsArray.length()) {
                    sectionsSet.add(sectionsArray.getString(i))
                }
                val bestScore = if (unitObj.has("bestScore") && !unitObj.isNull("bestScore")) {
                    unitObj.getInt("bestScore")
                } else null

                result[unitId] = UnitProgress(
                    unitId = unitId,
                    sectionsRead = sectionsSet,
                    caseStudyDone = unitObj.optBoolean("caseStudyDone", false),
                    bestScore = bestScore
                )
            }
            result
        } catch (e: Exception) {
            emptyMap()
        }
    }

    fun saveProgress(progressMap: Map<String, UnitProgress>) {
        try {
            val rootObj = JSONObject()
            progressMap.forEach { (unitId, progress) ->
                val unitObj = JSONObject().apply {
                    put("unitId", progress.unitId)
                    val arr = JSONArray()
                    progress.sectionsRead.forEach { arr.put(it) }
                    put("sectionsRead", arr)
                    put("caseStudyDone", progress.caseStudyDone)
                    if (progress.bestScore != null) {
                        put("bestScore", progress.bestScore)
                    } else {
                        put("bestScore", JSONObject.NULL)
                    }
                }
                rootObj.put(unitId, unitObj)
            }
            prefs.edit().putString(KEY_PROGRESS, rootObj.toString()).apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAttempts(): List<ExamAttempt> {
        return try {
            val jsonStr = prefs.getString(KEY_ATTEMPTS, null) ?: return emptyList()
            val arr = JSONArray(jsonStr)
            val list = mutableListOf<ExamAttempt>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val kindStr = obj.optString("kind", QuizKind.PRETEST.name)
                val kind = try {
                    QuizKind.valueOf(kindStr)
                } catch (e: Exception) {
                    QuizKind.PRETEST
                }
                list.add(
                    ExamAttempt(
                        id = obj.getString("id"),
                        kind = kind,
                        unitId = if (obj.has("unitId") && !obj.isNull("unitId")) obj.getString("unitId") else null,
                        score = obj.getInt("score"),
                        correct = obj.getInt("correct"),
                        total = obj.getInt("total"),
                        takenAt = obj.optLong("takenAt", System.currentTimeMillis())
                    )
                )
            }
            list
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun saveAttempt(attempt: ExamAttempt) {
        try {
            val current = getAttempts().toMutableList()
            current.add(0, attempt)
            val arr = JSONArray()
            current.forEach { item ->
                val obj = JSONObject().apply {
                    put("id", item.id)
                    put("kind", item.kind.name)
                    put("unitId", item.unitId ?: JSONObject.NULL)
                    put("score", item.score)
                    put("correct", item.correct)
                    put("total", item.total)
                    put("takenAt", item.takenAt)
                }
                arr.put(obj)
            }
            prefs.edit().putString(KEY_ATTEMPTS, arr.toString()).apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getSettings(): AppSettings {
        return try {
            val jsonStr = prefs.getString(KEY_SETTINGS, null) ?: return AppSettings()
            val obj = JSONObject(jsonStr)
            val darkMode = if (obj.has("darkMode") && !obj.isNull("darkMode")) obj.getBoolean("darkMode") else null
            AppSettings(
                darkMode = darkMode,
                onboardingDone = obj.optBoolean("onboardingDone", false),
                guideViewed = obj.optBoolean("guideViewed", false)
            )
        } catch (e: Exception) {
            AppSettings()
        }
    }

    fun saveSettings(settings: AppSettings) {
        try {
            val obj = JSONObject().apply {
                if (settings.darkMode != null) {
                    put("darkMode", settings.darkMode)
                } else {
                    put("darkMode", JSONObject.NULL)
                }
                put("onboardingDone", settings.onboardingDone)
                put("guideViewed", settings.guideViewed)
            }
            prefs.edit().putString(KEY_SETTINGS, obj.toString()).apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun clearAllData() {
        prefs.edit().clear().apply()
    }
}
