package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ModalComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private final SelenideElement formWrapper = $(".practice-form-wrapper");
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement mobileInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateDropdown = $("#state");
    private final SelenideElement cityDropdown = $("#city");
    private final SelenideElement submitButton = $("#submit");

    public PracticeFormPage open() {
        Selenide.open("/automation-practice-form");

        formWrapper.shouldHave(text("Practice Form"));
        formWrapper.shouldHave(text("Student Registration Form"));

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public PracticeFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public PracticeFormPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public PracticeFormPage setMobile(String mobile) {
        mobileInput.setValue(mobile);
        return this;
    }

    public PracticeFormPage selectDateOfBirth(String year, String month, int day) {
        CalendarComponent calendar = new CalendarComponent(dateOfBirthInput);
        calendar.click();
        calendar.setDate(year, month, day);
        return this;
    }

    public PracticeFormPage selectSubjects(String... subjects) {
        for (String subject : subjects) {
            subjectsInput.setValue(subject).pressEnter();
        }
        return this;
    }

    public PracticeFormPage selectHobbies(String... hobbies) {
        for (String hobby : hobbies) {
            hobbiesWrapper.$(byText(hobby)).click();
        }
        return this;
    }

    public PracticeFormPage uploadPicture(String filename) {
        uploadPictureInput.uploadFromClasspath(filename);
        return this;
    }

    public PracticeFormPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public PracticeFormPage selectState(String state) {
        stateDropdown.click();
        stateDropdown.$$("div")
                .filterBy(text(state))
                .first()
                .click();
        return this;
    }

    public PracticeFormPage selectCity(String city) {
        cityDropdown.click();
        cityDropdown.$$("div")
                .filterBy(text(city))
                .first()
                .click();
        return this;
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public static class ResultModal {
        private static final String MODAL_TITLE = "Thanks for submitting the form";
        private static final String LABEL_HEADER = "Label";
        private static final String VALUES_HEADER = "Values";
        private static final String STUDENT_NAME_LABEL = "Student Name";
        private static final String STUDENT_EMAIL_LABEL = "Student Email";
        private static final String GENDER_LABEL = "Gender";
        private static final String MOBILE_LABEL = "Mobile";
        private static final String DATE_OF_BIRTH_LABEL = "Date of Birth";
        private static final String SUBJECTS_LABEL = "Subjects";
        private static final String HOBBIES_LABEL = "Hobbies";
        private static final String PICTURE_LABEL = "Picture";
        private static final String ADDRESS_LABEL = "Address";
        private static final String STATE_AND_CITY_LABEL = "State and City";

        private final ModalComponent modal = new ModalComponent();

        public ResultModal shouldHaveTitle() {
            modal.shouldHaveTitle(MODAL_TITLE);
            return this;
        }

        public ResultModal shouldHaveHeaders() {
            modal.shouldHaveHeaders(LABEL_HEADER, VALUES_HEADER);
            return this;
        }

        public ResultModal shouldHaveStudentName(String firstName, String lastName) {
            modal.shouldHaveField(STUDENT_NAME_LABEL, firstName + " " + lastName);
            return this;
        }

        public ResultModal shouldHaveStudentEmail(String email) {
            modal.shouldHaveField(STUDENT_EMAIL_LABEL, email);
            return this;
        }

        public ResultModal shouldHaveGender(String gender) {
            modal.shouldHaveField(GENDER_LABEL, gender);
            return this;
        }

        public ResultModal shouldHaveMobile(String mobile) {
            modal.shouldHaveField(MOBILE_LABEL, mobile);
            return this;
        }

        public ResultModal shouldHaveDateOfBirth(String year, String month, int day) {
            String formattedDate = day + " " + month + "," + year;
            modal.shouldHaveField(DATE_OF_BIRTH_LABEL, formattedDate);
            return this;
        }

        public ResultModal shouldHaveSubjects(String... subjects) {
            modal.shouldHaveField(SUBJECTS_LABEL, String.join(", ", subjects));
            return this;
        }

        public ResultModal shouldHaveHobbies(String... hobbies) {
            modal.shouldHaveField(HOBBIES_LABEL, String.join(", ", hobbies));
            return this;
        }

        public ResultModal shouldHavePicture(String filename) {
            modal.shouldHaveField(PICTURE_LABEL, filename);
            return this;
        }

        public ResultModal shouldHaveAddress(String address) {
            modal.shouldHaveField(ADDRESS_LABEL, address);
            return this;
        }

        public ResultModal shouldHaveStateAndCity(String state, String city) {
            modal.shouldHaveField(STATE_AND_CITY_LABEL, state + " " + city);
            return this;
        }

        public ResultModal shouldBeVisible() {
            modal.shouldBeVisible();
            return this;
        }

        public void shouldNotBeVisible() {
            modal.shouldNotBeVisible();
        }

        public void clickClose() {
            modal.clickClose();
        }
    }
}
