import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class PracticeFormPagePositiveTests extends BaseTest {
    private static final String firstName = "John";
    private static final String lastName = "Doe";
    private static final String email = "john.doe@example.com";
    private static final String gender = "Male";
    private static final String mobile = "1234567890";
    private static final String birthYear = "1980";
    private static final String birthMonth = "June";
    private static final int birthDay = 2;
    private static final String[] subjects = new String[] {"Computer Science", "Maths", "Physics"};
    private static final String[] hobbies = new String[] {"Sports", "Music"};
    private static final String filename = "test.jpg";
    private static final String address = "793 Arleen Street";
    private static final String state = "Haryana";
    private static final String city = "Panipat";

    private final PracticeFormPage page = new PracticeFormPage();
    private final PracticeFormPage.ResultModal resultModal = new PracticeFormPage.ResultModal();

    @Test
    void fillAllFieldsTest() {
        page.open()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .selectGender(gender)
                .setMobile(mobile)
                .selectDateOfBirth(birthYear, birthMonth, birthDay)
                .selectSubjects(subjects)
                .selectHobbies(hobbies)
                .uploadPicture(filename)
                .setCurrentAddress(address)
                .selectState(state)
                .selectCity(city)
                .clickSubmit();

        resultModal.shouldBeVisible()
                .shouldHaveTitle()
                .shouldHaveHeaders()
                .shouldHaveStudentName(firstName, lastName)
                .shouldHaveStudentEmail(email)
                .shouldHaveGender(gender)
                .shouldHaveMobile(mobile)
                .shouldHaveDateOfBirth(birthYear, birthMonth, birthDay)
                .shouldHaveSubjects(subjects)
                .shouldHaveHobbies(hobbies)
                .shouldHavePicture(filename)
                .shouldHaveAddress(address)
                .shouldHaveStateAndCity(state, city)
                .clickClose();
    }

    @Test
    void fillRequiredFieldsTest() {
        page.open()
                .setFirstName(firstName)
                .setLastName(lastName)
                .selectGender(gender)
                .setMobile(mobile)
                .clickSubmit();

        LocalDate today = LocalDate.now();
        String year = String.valueOf(today.getYear());
        String month = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = today.getDayOfMonth();

        resultModal.shouldBeVisible()
                .shouldHaveTitle()
                .shouldHaveHeaders()
                .shouldHaveStudentName(firstName, lastName)
                .shouldHaveGender(gender)
                .shouldHaveMobile(mobile)
                .shouldHaveDateOfBirth(year, month, day);

        resultModal.shouldHaveStudentEmail("")
                .shouldHaveSubjects("")
                .shouldHaveHobbies("")
                .shouldHavePicture("")
                .shouldHaveAddress("")
                .shouldHaveStateAndCity("", "")
                .clickClose();
    }
}
