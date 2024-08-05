package com.example.statehoistingpreperations

import com.example.statehoistingpreperations.mapper.SimpleUser
import com.example.statehoistingpreperations.mapper.SimpleUser1
import com.example.statehoistingpreperations.mapper.UserMainModel
import com.example.statehoistingpreperations.mapper.toSimpleUser
import com.example.statehoistingpreperations.mapper.toSimpleUserUsingReflection
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserMapperReflectionNameTest {
    @Test
    fun mapUserToSimpleUserUsingExtensionFunction() {
        asserSimpleUser(buildUser().toSimpleUserUsingReflection())
    }

    private fun buildUser(): UserMainModel {
        return UserMainModel(
            id = 1,
            fname = "amir",
            lastName = "javeed",
            email = "email",
            phone = "052",
            profileIcon = "abc",
            business = "abc",
            city = "abc",
            building = "def",
            flatNo = "1",
            office = "abc"
        )
    }


    private fun asserSimpleUser(simpleUser: SimpleUser1) {

        assertEquals("javeed, amir", simpleUser.name)
        assertEquals("abc, def, 1", simpleUser.address)
        assertEquals("email, 052", simpleUser.contact)

    }
}