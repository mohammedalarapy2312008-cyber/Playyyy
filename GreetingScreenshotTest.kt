package com.example

import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.example.data.model.BookingPost
import com.example.ui.components.ScheduleCard
import com.example.ui.theme.MyApplicationTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel8, sdk = [36])
class GreetingScreenshotTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun greeting_screenshot() {
    val post = BookingPost(
        id = "test_post",
        groupId = "g1",
        pitchName = "ملاعب بالم هيلز",
        matchDateTime = 1700000000000L,
        formattedDateTime = "الجمعة 8:30 مساءً",
        requiredPlayers = 10,
        hourlyRate = 250.0,
        hoursCount = 2.0,
        notes = "الحضور قبل الميعاد",
        deadlineTimestamp = 1700000000000L,
        creatorUserId = "u1"
    )

    composeTestRule.setContent {
      MyApplicationTheme {
        ScheduleCard(post = post)
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/greeting.png")
  }
}
