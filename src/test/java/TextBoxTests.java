import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests extends BaseTest {
    private static final String name = "Alex";
    private static final String email = "alex@example.com";
    private static final String currentAddress = "721 Broadway, New York, NY 10003, USA";
    private static final String permanentAddress = "59 Sycamore Street, San Francisco, CA 94107, USA";

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillAllFieldsTest() {
        textBoxPage
                .open()
                .setUserName(name)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .clickSubmit();

        textBoxPage
                .shouldHaveUserName(name)
                .shouldHaveEmail(email)
                .shouldHaveCurrentAddress(currentAddress)
                .shouldHavePermanentAddress(permanentAddress);
    }
}
