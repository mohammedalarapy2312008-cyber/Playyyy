package com.example

import com.example.data.model.BookingPost
import com.example.data.model.Group
import com.example.data.model.PlayerTier
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testBookingPostCalculations() {
        val post = BookingPost(
            id = "test_post",
            groupId = "group_1",
            pitchName = "ملاعب الدقي",
            matchDateTime = System.currentTimeMillis() + 86400000L,
            formattedDateTime = "الجمعة 8:00 مساءً",
            requiredPlayers = 10,
            hourlyRate = 200.0,
            hoursCount = 2.0,
            notes = "شوز ترتان",
            deadlineTimestamp = System.currentTimeMillis() + 43200000L,
            creatorUserId = "u1"
        )

        // Total cost: 200 * 2 = 400
        assertEquals(400.0, post.totalCost, 0.01)

        // Fixed cost: 400 / 10 = 40
        assertEquals(40.0, post.fixedCostPerPlayer, 0.01)

        // Live cost with 8 players: 400 / 8 = 50
        assertEquals(50.0, post.liveCostPerPlayer(8), 0.01)

        // Live cost with 0 players returns full totalCost (400)
        assertEquals(400.0, post.liveCostPerPlayer(0), 0.01)
    }

    @Test
    fun testPlayerTiersFromPoints() {
        assertEquals(PlayerTier.RISING, PlayerTier.fromPoints(100))
        assertEquals(PlayerTier.RISING, PlayerTier.fromPoints(349))
        assertEquals(PlayerTier.PRO, PlayerTier.fromPoints(350))
        assertEquals(PlayerTier.PRO, PlayerTier.fromPoints(550))
        assertEquals(PlayerTier.ELITE, PlayerTier.fromPoints(551))
        assertEquals(PlayerTier.ELITE, PlayerTier.fromPoints(800))
        assertEquals(PlayerTier.LEGENDARY, PlayerTier.fromPoints(801))
        assertEquals(PlayerTier.LEGENDARY, PlayerTier.fromPoints(1200))
    }

    @Test
    fun testGroupMembershipAndAdminChecks() {
        val group = Group(
            id = "g1",
            name = "جروب الجمعة",
            description = "ماتش الجمعة الأسبوعي",
            isPublic = true,
            inviteCode = "JOMAA-2025",
            adminUserIds = "u1,u2",
            memberUserIds = "u1,u2,u3,u4",
            pendingUserIds = "u5,u6"
        )

        assertTrue(group.isAdmin("u1"))
        assertTrue(group.isAdmin("u2"))
        assertFalse(group.isAdmin("u3"))

        assertTrue(group.isMember("u3"))
        assertTrue(group.isMember("u4"))
        assertFalse(group.isMember("u5"))

        assertTrue(group.isPending("u5"))
        assertTrue(group.isPending("u6"))
        assertFalse(group.isPending("u1"))
    }
}

