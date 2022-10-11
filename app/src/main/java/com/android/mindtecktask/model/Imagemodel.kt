package com.android.mindtecktask.model

class Imagemodel(
    var id: Int,
    var name: String?,
    var image: Int
) {
    var imageid: Int = id
    var imagename: String? = name
    var imagepath: Int = image


    var setterPropertyForid: Int
        get() = imageid
        set(value) {
            imageid = value
        }


    var setterPropertyForname: String?
        get() = imagename
        set(value) {
            imagename = value
        }

    var setterPropertyForimagepath: Int
        get() = imagepath
        set(value) {
            imagepath = value
        }

}

