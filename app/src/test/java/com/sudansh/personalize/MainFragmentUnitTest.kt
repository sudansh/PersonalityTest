package com.sudansh.personalize

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainFragmentUnitTest {

    private val gender = arrayOf("male", "female", "others")
    private val isGenderImportant = arrayOf("not important", "important", "very important")
    private val havingChildrenWithPartner = arrayOf("yes", "maybe", "no")

    @Test
    fun testGender() {
        assertEquals("male", gender[0])
        assertEquals("female", gender[1])
        assertEquals("others", gender[2])
    }

    @Test
    fun testGenderImportant() {
        assertTrue(("not important" == isGenderImportant[0]))
        assertTrue(("important" == isGenderImportant[1]))
        assertTrue(("very important" == isGenderImportant[2]))
    }

    @Test
    fun testPartner() {
        assertEquals("yes", havingChildrenWithPartner[0])
        assertEquals("maybe", havingChildrenWithPartner[1])
        assertEquals("no", havingChildrenWithPartner[2])
    }

}
