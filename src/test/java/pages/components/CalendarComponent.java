package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement element;

    public CalendarComponent(SelenideElement element) {
        this.element = element;
    }

    public void setDate(String year, String month, int day) {
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);

        String formattedDay = String.format("%03d", day); // 1 -> "001"
        $(".react-datepicker__day--" + formattedDay + ":not(.react-datepicker__day--outside-month)").click();
    }

    public void click() {
        element.click();
    }
}
