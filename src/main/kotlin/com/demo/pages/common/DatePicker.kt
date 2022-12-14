package com.demo.pages.common

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selectors.byText
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DatePicker {
    private val wrapper: SelenideElement = `$`(".flatpickr-calendar.open")

    //Month-related
    private val monthPicker: SelenideElement = `$`(".flatpickr-monthDropdown-months")

    //Year-related
    private val yearPicker: SelenideElement = `$`(".numInputWrapper")
    private val previousYear: String = ".arrowDown"
    private val nextYear: String = ".arrowUp"
    private val currentYear: String = ".cur-year"

    //Day-related
    private val dayContainer: SelenideElement = `$`(".dayContainer")
    private val dayCollection: ElementsCollection = `$$`(".flatpickr-day")
    private val previousMonthDay: String = "prevMonthDay"
    private val nextMonthDay: String = "nextMonthDay"

    fun `wait while ready`() {
        wrapper.shouldBe(visible)
        monthPicker.shouldBe(visible, enabled)
        yearPicker.shouldBe(visible, enabled)
        dayContainer.shouldBe(visible)
    }

    fun `select year`(date: LocalDate) {
        val year = date.year.toString()
        `validate year picker readiness`()
        while (yearPicker.`$`(currentYear).`val`()?.compareTo(year) != 0) {
            yearPicker.`$`(previousYear).click()
        }
    }

    fun `select month`(date: LocalDate) {
        `wait while ready`()
        monthPicker.click()
        `$`(byText(date.month.toString().lowercase().replaceFirstChar(Char::titlecase)))
            .click()
    }

    fun `select day`(date: LocalDate) {
        `wait while ready`()
        dayCollection
            .filterNot { it.has(cssClass(previousMonthDay)) || it.has(cssClass(nextMonthDay)) }
            .first {
                it.text().contentEquals(date.dayOfMonth.toString())
            }
            .click()
    }

    private fun `validate year picker readiness`() {
        `wait while ready`()
        yearPicker.hover()
        yearPicker.`$`(previousYear).shouldBe(visible, enabled)
        yearPicker.`$`(nextYear).shouldBe(visible, enabled)
    }
}