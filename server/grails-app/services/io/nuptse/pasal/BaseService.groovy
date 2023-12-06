package io.nuptse.pasal


import grails.gorm.transactions.Transactional
import io.nuptse.pasal.auth.Role
import io.nuptse.pasal.auth.User
import io.nuptse.pasal.auth.UserRole
import io.nuptse.pasal.product.Category

import javax.servlet.http.HttpServletRequest

@Transactional
class BaseService {

    /**
     * Extract the client form request
     * @param request
     * @return
     */
    def getClientFromRequest(HttpServletRequest request) {
        String requestHostHeader = request.getHeader('Host')
        //println "requestHostHeader $requestHostHeader"
        def requestHost = requestHostHeader.substring(0, requestHostHeader.indexOf("."))
        if (requestHost.indexOf("-") > 0) {
            requestHost = requestHost.split("-")[0]
        }
        return getClientInstance( requestHost )
    }

    /*
    * We are extracting hostname from request and checking tis client
    * */
    def getClientInstance(String hostname) {
        Client client = Client.findByName(hostname)
        if(client) return client
        return null
    }

    def createUserAndClient(){
        Client nuptse = Client.findByName("nuptse")
        Client demo = Client.findByName("demo")
        if(!nuptse) {
            println "Creating initial client Nuptse "
            nuptse = new Client(code: "001",
                    name: "nuptse",
                    url:"nuptse.pasal.io",
                    hasVendor: true,
                    hasCustomer: true,
                    hasInventory: true,
                    country: "Nepal",
                    address: "Shree Margha, Lazimpat",
                    phone : 977-014412042,
                    email: "info@nuptsetechnologies.com",
                    displayName: "Nuptse Technologies",
                    vat : "AAC12312312312",
                    pan : "66434354345"
            )
            saveClient(nuptse)
            //println "client = $nuptse" + " created"
        }
        if(!demo) {
            println "Creating initial Client Demo"
            demo = new Client(code: "002",
                    name: "demo",
                    url:"demo.pasal.io",
                    hasVendor: true,
                    hasCustomer: true,
                    hasInventory: true,
                    country: "Nepal",
                    address: "Shree Margha, Lazimpat",
                    phone : 977-014412042,
                    email: "info@nuptsetechnologies.com",
                    displayName: "Demo Technologies",
                    vat : "AAC12312312312",
                    pan : "66434354345"

            )
            saveClient(demo)
            //println "client = $demo" + " created"
        }

        User admin = User.findByUsername("admin")
        User user = User.findByUsername("user")
        User userDemo = User.findByUsername("demo")
        User superAdmin = User.findByUsername("super")

        if(!admin) {
            println "Creating initial user admin"
            admin = new User( name:"Admin",
                    username: "admin",
                    password: "admin@1",
                    firstName: "admin",
                    lastName: "last",
                    country: "Nepal",
                    address: "Nuptse Colony, Anamnagar",
                    phone: "977-9812128833",
                    client: nuptse
            )
            //admin.save(flush:true, failOnError:true)
            saveUser(admin)
        }

        if(!superAdmin) {
            println "Creating initial user superAdmin"
            superAdmin = new User( name:"Super Admin",
                    username: "super",
                    password: "super@1",
                    firstName: "Super",
                    lastName: "Admin",
                    country: "Nepal",
                    address: "Nuptse Colony, Anamnagar",
                    phone: "977-9812128833",
                    client: nuptse
            )
            //superAdmin.save(flush:true, failOnError:true)
            saveUser(superAdmin)
        }

        if(!user) {
            println "Creating initial user user"
            user = new User( name:"User",
                    username: "user",
                    password: "user@1",
                    firstName: "User",
                    lastName: "Normal",
                    country: "Nepal",
                    address: "Nuptse Colony, Anamnagar",
                    phone: "977-9812128833",
                    client: nuptse
            )
            saveUser(user)
            //user.save(flush:true, failOnError:true)
        }

        if(!userDemo) {
            println "Creating initial user userDemo ..."
            userDemo = new User( name:"userDemo",
                    username: "demo",
                    password: "demo@1",
                    firstName: "User",
                    lastName: "Demo",
                    country: "Nepal",
                    address: "Nuptse Colony, Anamnagar",
                    phone: "977-9812128833",
                    client: demo
            )
            saveUser(userDemo)
            // println "User userDemo = $userDemo" + " created"
        }
    }

    def assignUserRoles(){
        User admin = User.findByUsername("admin")
        //println "admin = $admin roles -> ${admin.roles}"
        User user = User.findByUsername("user")
        User userDemo = User.findByUsername("demo")
        User superAdmin = User.findByUsername("super")

        def adminRole = Role.findByAuthority("ROLE_ADMIN")
        def superAdminRole = Role.findByAuthority("ROLE_SUPER_ADMIN")
        def userRole = Role.findByAuthority("ROLE_USER")

        def adminUserRole =  UserRole.findByUserAndRole(admin, adminRole) // admin?.roles
        def userUserRole = UserRole.findByUserAndRole(user, userRole) //  user?.roles
        def superAdminUserRole = UserRole.findByUserAndRole(superAdmin, superAdminRole) // superAdmin?.roles
        def demoUserRole = UserRole.findByUserAndRole(userDemo, adminRole) // userDemo?.roles

        if( !adminUserRole ) {
            //UserRole.create(admin, adminRole)
            initUserRole(admin, adminRole)
        }

        if( !userUserRole ) {
            //UserRole.create(user, userRole)
            initUserRole(user, userRole)
        }

        if( !superAdminUserRole ) {
            //UserRole.create(superAdmin, superAdminRole)
            initUserRole(superAdmin, superAdminRole)
        }

        if( !demoUserRole ) {
            //UserRole.create(userDemo, adminRole)
            initUserRole(userDemo, adminRole)
        }
    }

    def saveUser(User user){
        user.save(flush:true, failOnError:true)
    }

    def saveClient(Client client){
        client.save(flush:true, failOnError:true)
    }

    def initUserRole(User user, Role role){
        println "creating user role for ${user} role = $role"
        UserRole.create(user, role, true)
    }

    def createAuthorities() {
        def authorities = ['ROLE_USER':'user','ROLE_ADMIN':'admin','ROLE_SUPER_ADMIN':'superadmin']
        authorities.each {k,v->
            if(!Role.findByAuthority(k)) {
                new Role(authority: k, name: v).save(flush:true, failOnError:true)
            }
        }
    }

    def createCategories() {
        def categories = ['Book': 'Books ', 'Grocery': 'Eateries']
        categories.each {k, v ->
            if(!Category.findByName(k)) {
                new Category(name: k, description: v, client: Client.findByName("nuptse")).save(flush:true, failOnError:true)
            }
        }
    }

    def createVendors() {
        def vendors = ['Ram': 'Ram Suppliers', 'ABC': 'ABC Vendor']
        vendors.each {k, v->
            if(!Vendor.findByName(k)) {
                new Vendor(name: k, description: v, client: Client.findByName("nuptse")).save(flush:true, failOnError:true)
            }
        }
    }
}
