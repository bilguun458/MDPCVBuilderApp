package com.miu.curriculumvitae

import java.io.Serializable

class Person(
    var avatar: Int,
    var firstName: String,
    var lastName: String,
    var password: String,
    var profession: String,
    var about: String,
    var webs: String,
    var contact: Contact,
) : Serializable {
}