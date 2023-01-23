package com.miu.curriculumvitae

import java.io.Serializable

class Contact(
    var phone: String,
    var email: String,
    var facebook: String,
    var twitter: String
) : Serializable {
}