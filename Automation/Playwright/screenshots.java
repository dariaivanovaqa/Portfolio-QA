import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ScreenshotCaret;

import java.nio.file.Paths;
import java.util.Arrays;

public class screenshots {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        //screenshots
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
        page.screenshot(screenshotOptions.setPath(Paths.get("./snaps/scr.png")));

        //full page screenshots
        page.screenshot(screenshotOptions.setFullPage(true).setPath(Paths.get("./snaps/fullPage.jpg")));

        //locator screenshot
        Locator bookBtn = page.locator("//button[text()='Book a Demo']");
        bookBtn.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/locator.png")));
        //region locator screenshot
        Locator header = page.locator("#header");
        header.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/region.png")));

        //masking locator

        Locator input = page.locator("input#user-message");
//        input.type("text");
        input.scrollIntoViewIfNeeded();
//        page.screenshot(screenshotOptions.setPath(Paths.get("./snaps/input.jpg"))
//                .setFullPage(false)
//                .setMask(Arrays.asList(input))
//        );

        //caret show/hide

        input.click();
        page.screenshot(new Page.ScreenshotOptions().setCaret(ScreenshotCaret.HIDE)
                .setPath(Paths.get("./snaps/caret.png")));
        page.screenshot(new Page.ScreenshotOptions().setCaret(ScreenshotCaret.INITIAL)
                .setPath(Paths.get("./snaps/caretInitial.png")));

        page.close();
        playwright.close();

    }

}
