import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
//import sun.jvm.hotspot.runtime.StringDedupThread;

public class dropdowns {

    public static void main(String[] args) {
        String selectURL = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";
        String jqueryURL = "https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo";

        //launch browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        Page page = browser.newPage();

        page.navigate(selectURL);

        Locator dayLocator = page.locator("select#select-demo");

        //select by value
        dayLocator.selectOption("Wednesday");
        Locator result = page.locator("p.selected-value");
        System.out.println(result.textContent());
        assertThat(result).containsText("Wednesday");

        //select by label
        dayLocator.selectOption(new SelectOption().setValue("Friday"));
        System.out.println(result.textContent());
        assertThat(result).containsText("Friday");
        //select by index
        dayLocator.selectOption(new SelectOption().setIndex(2));

        //select multiple
        Locator states = page.locator("select[name='States']");
        states.selectOption(new String[] {"New York", "Ohio"});

        Locator options = states.locator("option");
        System.out.println(options.count());
        List<String> allInnerTexts = options.allInnerTexts();
        allInnerTexts.forEach(option -> System.out.println(option));

        //select Jquery
        page.navigate(jqueryURL);
        Locator country = page.locator("span.select2-container").first();
        country.click();
        Locator list = page.locator("span.select2-results ul li",
                new Page.LocatorOptions().setHasText("Hong Kong"));
        list.click();
        Locator files = page.locator("select[name='files']");
        files.selectOption("Ruby");

        //practice
        Locator practice = page.locator("(//span[@class='select2-selection__rendered'])[2]");
        practice.click();
        Locator anotherCountry = page.locator("//li[text()='Virgin Islands']");
        anotherCountry.click();
        System.out.println(practice.textContent());
        assertThat(practice).containsText("Virgin Islands");

        page.close();
        browser.close();
        playwright.close();
    }
}
