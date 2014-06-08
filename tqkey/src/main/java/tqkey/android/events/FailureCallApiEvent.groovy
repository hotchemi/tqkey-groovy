package tqkey.android.events

class FailureCallApiEvent {

    def statusCode

    FailureCallApiEvent(int statusCode) {
        this.statusCode = statusCode
    }

}