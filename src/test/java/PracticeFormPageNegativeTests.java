import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

public class PracticeFormPageNegativeTests extends BaseTest {
    private final PracticeFormPage page = new PracticeFormPage();
    private final PracticeFormPage.ResultModal resultModal = new PracticeFormPage.ResultModal();

    @Test
    void emptyRequiredFieldsShowsErrorTest() {
        page.open()
                .selectGender("Female")
                .setCurrentAddress("Some address")
                .clickSubmit();

        resultModal.shouldNotBeVisible();
    }

    @Test
    void invalidMobileFormatShowsErrorTest() {
        page.open()
                .setMobile("123") // should have 10 digits
                .clickSubmit();

        resultModal.shouldNotBeVisible();
    }

    @Test
    void invalidEmailFormatShowsErrorTest() {
        PracticeFormPage page = new PracticeFormPage();
        page.open()
                .setEmail("f@f") // should have domain
                .clickSubmit();

        resultModal.shouldNotBeVisible();
    }
}
