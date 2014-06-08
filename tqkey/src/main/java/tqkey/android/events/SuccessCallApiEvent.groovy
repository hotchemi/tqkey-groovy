package tqkey.android.events

import groovy.json.JsonSlurper
import tqkey.android.entity.User;

class SuccessCallApiEvent {

    List<User> users

    SuccessCallApiEvent(String content) {
        users = new ArrayList<User>()
        new JsonSlurper().parseText(content).users.each {
            users.add(new User(it))
        }
    }

}