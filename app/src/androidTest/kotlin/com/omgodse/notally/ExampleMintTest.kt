package com.omgodse.notally

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.omgodse.notally.activities.MainActivity
import junit.framework.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mint.MINT
import org.mint.MINTRule
import org.mint.android.rule.BasicRules
import org.mint.android.rule.MultiplicativeRule
import org.mint.junit.runner.MintClassRunner

@RunWith(MintClassRunner::class)
class ExampleMintTest {
    private val scrollingAndClickDeprioritizeRule: MultiplicativeRule =
         MultiplicativeRule(
                description = "De-prioritized the scrolling to click action",
                action = BasicRules.scrollingClickableRule().action,
                pred = BasicRules.scrollingClickableRule().predicate(),
                prio = BasicRules.xprio("0.01")
            )

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Rule
    @JvmField
    var mint = MINTRule(
        MINT.DefaultBuilder
            .withRule(scrollingAndClickDeprioritizeRule)
            .build { e -> fail(e) }
    )

    @Test
    fun mintExampleTestRun() {
        mint.explore()
    }
}
