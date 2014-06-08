package tqkey.android.entity

class User {

    String name

    boolean atHome

    String updatedAt

    User(Object user) {
        name = user.name
        atHome = user.athome
        updatedAt = user.updated_at
    }

    String getAtHomeText() {
        return atHome ? "いる" : "いない"
    }

    String getName() {
        return name == "naoty" ? name + "_k" : name
    }

}