package io.nuptse.pasal.auth


class AuthService {

    boolean transactional = true
    static ThreadLocal<User> currentUser = new ThreadLocal<User>()
}
