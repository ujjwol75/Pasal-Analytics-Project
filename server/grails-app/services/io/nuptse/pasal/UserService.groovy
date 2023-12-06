package io.nuptse.pasal


import io.nuptse.pasal.auth.Role
import io.nuptse.pasal.auth.User
import io.nuptse.pasal.auth.UserRole

class UserService {

    def currentUser(def username) {
        User user = User.findByUsername(username)
        def currentUser = [:]
        def tags = []
        currentUser.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png")
        currentUser.put("name", user?.firstName +" "+ user?.lastName)
        currentUser.put("firstName", user?.firstName)
        currentUser.put("lastName", user?.lastName)
        currentUser.put("title", "Expert Admin")
        currentUser.put("group", "Technology Department")
        currentUser.put("signature", "Be tolerant to diversity, tolerance is a virtue.")
        tags.add(["key": "0", "label": "Thoughtful"])
        currentUser.put("tags", tags)
        currentUser.put("userid", user?.id)
        currentUser.put("unreadCount", 10)
        if (user.authorities.any { it.authority == "ROLE_ADMIN" || "ROLE_SUPER_ADMIN" }) {
            currentUser.put('access', 'admin')
        } else {
            currentUser.put('access', 'user')
        }
        if (user.authorities.any { it.authority == "ROLE_SUPER_ADMIN" }) {
            currentUser.put('super', 'admin')
        } else {
            currentUser.put('super', 'user')
        }
        currentUser.put("email", user?.email)
        currentUser.put("notifyCount", 12)
        def province = [:]
        province.put('name', 'Bagmati')
        def city = [:]
        city.put('name', 'Kathmandu')
        currentUser.put('geographic', ['province': province, 'city': city])
        currentUser.put("country", "Nepal")
        currentUser.put("address", user?.address)
        currentUser.put("description", user?.description)
        currentUser.put("phone", user?.phone)
        currentUser.put("username", user?.username)
        def body = [:]
        body.put('success', true)
        body.put('data', currentUser)
        return body
    }

    def queryuser(def params) {
        log.info("Userpage params" + params)
        def users = User.createCriteria().list() {
            if (params.id) {
                eq 'id', Long.parseLong(params.id)
            }
            if (params.fullname) {
                or {
                    ilike 'firstName', '%' + params.fullname + '%'
                    ilike 'lastName', '%' + params.fullname + '%'
                }
            }
            if (params.avatarUrl) {
                ilike 'avatarUrl', '%' + params.avatarUrl + '%'
            }
            if (params.email) {
                ilike 'email', '%' + params.email + '%'
            }
            if (params.username) {
                ilike 'username', '%' + params.username + '%'
            }
            if (params.phone) {
                eq 'phone', params.phone
            }
            if (params.description) {
                ilike 'description', '%' + params.description + '%'
            }
            if (params.address) {
                ilike 'address', '%' + params.address + '%'
            }
            if (params.client) {
                client {
                    ilike 'name', '%' + params.client + '%'
                }
            }
//
////            eq 'client', client
            eq 'deleted', false
        }
        //println "Rendering ${userpages?.size()} Accounts of ${userpages?.class}"
        return users
    }

    def saveUser(def json) {
        log.info("user json $json")
        // log.info("user role is coming as $json.role")
        User user
        if (json.containsKey("key")) {
            log.info("key is $json.key")
            user = User.findById(json.key)
        } else {
            user = new User()
            user.password = json.password
        }

        user.username = json.username
        user.avatarUrl = json.avatar
        user.firstName = json.fname
        user.lastName = json.lname
        user.email = json.email
        user.phone = json.phone
        user.address = json.address
        user.description = json.description
        def client = json.client
        if (client instanceof String) {
            user.client = Client.findByName(json.client)
        } else {
            user.client = Client.findById(json.client)
        }
        user.enabled = json.enabled ? json.enabled : true
        user.accountExpired = json.accountExpired ? json.accountExpired : false
        user.accountLocked = json.accountLocked ? json.accountLocked : false
        user.passwordExpired = json.passwordExpired ? json.passwordExpired : false

        // log.info "user props ${user.properties}" // this instance is going to be saved
        try {
            user.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
        }

        // UserRole.create(user, Role.findByAuthority("ROLE_USER"), true)
        if (!json.containsKey("key")) {
            UserRole.create(user, Role.findByAuthority("ROLE_USER"), true)
        }
    }

    def get(def id) {
        return User.get(id)
    }

    def delete(def id) {
        log.info("id ${id}")
        def user = User.findById(id)
        user.deleted = true
        user.save(flush: true, failOnError: true)
    }

    def getUsersList() {
        def users = User.findAll()
        def result = []
        users.collect {
            result << ['label': it.username, 'value': it.id]
        }
        return result
    }

    def getRolesList() {
        def users = Role.findAll()
        def result = []
        users.collect {
            result << ['label': it.authority, 'value': it.id]
        }
        return result
    }
}
