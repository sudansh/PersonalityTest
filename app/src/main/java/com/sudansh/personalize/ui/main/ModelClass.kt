package com.sudansh.personalize.ui.main

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuestionData : RealmObject() {
    var questions: RealmList<Questions>? = null
    var categories: RealmList<String>? = null

    @PrimaryKey
    var id: String? = ""
}


open class Questions : RealmObject() {
    var question: String? = null
    var question_type: QuestionType? = null
    var category: String? = null

}


open class QuestionType : RealmObject() {
    var options: RealmList<String>? = null
    var type: String? = null
    var selectedValue = ""
}