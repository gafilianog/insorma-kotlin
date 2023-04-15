package dev.gafilianog.insorma.data.local.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.gafilianog.insorma.data.model.User
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InsormaDatabaseTest : TestCase() {

//    private lateinit var userDao: UsersDao
//    private lateinit var db: InsormaDatabase
//
//    @Before
//    public override fun setUp() {
//        val ctx = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(ctx, InsormaDatabase::class.java).build()
//        userDao = db.usersDao
//    }
//
//    @After
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    fun addUserTest() = runBlocking {
//        val user = User(0, "john@mail.com", "john", "08123456789", "a3")
//        userDao.insertUser(user)
//        val getUser = userDao.getUserByUsername("john")
//        assertTrue(getUser?.emailAddress == user.emailAddress)
//    }
}