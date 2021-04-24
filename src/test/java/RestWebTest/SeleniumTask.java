package RestWebTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SeleniumTask extends Configuration {

    private WebDriver driver;




    @Test(priority = 1)
    private void start() {
        System.setProperty("webdriver.chrome.driver", DriverPath);
        //System.setProperty("webdriver.chrome.driver","Resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://finanzen.check24.de/accounts/d/kreditkarte/result.html"); // step 1
        driver.manage().window().maximize();


    }

    @Test(priority = 2)
    public void checkCookie() {
        boolean cookiePresent = false;
        for (Cookie ck : driver.manage().getCookies()) {
            if (ck.getName().equals("ppset")) {
                cookiePresent = true;
                Assert.assertEquals(ck.getValue().equals("kreditkarte"), true);
                System.out.println("cookie ppset=kreditkarte");


            }

        }
    }


    @Test(priority = 3)
    private void weiter() {
        WebElement AcceptButton = driver.findElement(By.xpath("//*[text()='Akzeptieren']"));

        AcceptButton.click();


        boolean product = driver.findElement(By.xpath("//*[text()='Barclaycard Visa']")).isDisplayed();
        Assert.assertEquals(product, true, "Text Present");
        System.out.println("Barclaycard Visa Record Present");


        WebElement weiter = driver.findElement(By.xpath("//*[@id=\"200007\"]/div[5]/div[3]/a"));

        weiter.click();

    }


    @Test(priority = 4)
    private void FillEmail() {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cl_login\"]")));
        WebElement Email = driver.findElement(By.xpath("//*[@id=\"cl_login\"]"));

        // Email.click();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", Email);
        Email.sendKeys("abc@gmail.com");

        WebElement weiter = driver.findElement(By.xpath("//*[@id=\"c24-uli-login-btn\"]"));
        jse.executeScript("arguments[0].click()", weiter);
        // weiter.click();

    }

    @Test(priority = 5)
    private void Gast() {
        // WebDriverWait wait = new WebDriverWait (driver, 15);

        //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cl_login\"]")));
        //  WebElement link = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/div/div/unified-login/div/div/div[3]/div[3]/a"));

        WebElement link = driver.findElement(By.xpath("//*[text()='als Gast fortfahren']"));


        // js.executeScript("arguments[0].scrollIntoView();", link);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", link);

    }

    @Test(priority = 6)
    private void Weiter() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement weiter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/div/div/a")));
        Actions actions = new Actions(driver);
        actions.moveToElement(weiter);
        actions.moveToElement(weiter).click().perform();


    }


    @Test(priority = 7)
    private void FieldValidation() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement GENDER_FEMALE = driver.findElement(By.id("GENDER_FEMALE"));
        WebElement GENDER_MALE = driver.findElement(By.id("GENDER_MALE"));
        String text1 = GENDER_FEMALE.getAttribute("value");
        String text2 = GENDER_MALE.getAttribute("value");
        if (text1.isEmpty() && text2.isEmpty()) {
            WebElement weiter = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[1]/div[1]/div[2]/div[2]"));
            boolean Anrede = weiter.getText().equals("Bitte wählen Sie Ihre Anrede aus.");
            System.out.println(weiter.getText());
            Assert.assertTrue(Anrede, "success");
        }

        WebElement GIVEN_NAME = driver.findElement(By.id("GIVEN_NAME"));
        String text = GIVEN_NAME.getAttribute("value");
        if (text.isEmpty()) {
            WebElement vornamen = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[1]/div[2]/div[2]/div[2]"));
            boolean vornamen1 = vornamen.getText().equals("Bitte geben Sie Ihren Vornamen an.");
            System.out.println(vornamen.getText());
            Assert.assertTrue(vornamen1, "success");
            //System.out.println("input box is empty");
        }

        WebElement LAST_NAME = driver.findElement(By.id("LAST_NAME"));
        String LAST_NAME1 = LAST_NAME.getAttribute("value");
        if (LAST_NAME1.isEmpty()) {
            WebElement Nachnamen = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[1]/div[2]/div[2]/div[3]"));
            boolean Nachnamen1 = Nachnamen.getText().equals("Bitte geben Sie Ihren Nachnamen an.");
            System.out.println(Nachnamen.getText());
            Assert.assertTrue(Nachnamen1, "success");
        }

        WebElement DATE_OF_BIRTH = driver.findElement(By.id("DATE_OF_BIRTH"));
        String DATE_OF_BIRTH1 = DATE_OF_BIRTH.getAttribute("value");
        if (DATE_OF_BIRTH1.isEmpty()) {
            WebElement Geburtsdatum = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[1]/div[3]/div[2]/div[2]"));
            boolean Geburtsdatum1 = Geburtsdatum.getText().equals("Bitte geben Sie Ihr Geburtsdatum an.");
            System.out.println(Geburtsdatum.getText());
            Assert.assertTrue(Geburtsdatum1, "success");
        }


        WebElement PLACE_OF_BIRTH = driver.findElement(By.id("PLACE_OF_BIRTH"));
        String PLACE_OF_BIRTH1 = PLACE_OF_BIRTH.getAttribute("value");
        if (PLACE_OF_BIRTH1.isEmpty()) {
            WebElement Geburtsort = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[1]/div[4]/div[2]/div[2]"));
            boolean Geburtsort1 = Geburtsort.getText().equals("Bitte geben Sie Ihren Geburtsort an.");
            System.out.println(Geburtsort.getText());
            Assert.assertTrue(Geburtsort1, "success");

        }


        WebElement MARITAL_STATUS = driver.findElement(By.id("MARITAL_STATUS"));
        String MARITAL_STATUS1 = MARITAL_STATUS.getAttribute("value");
        if (MARITAL_STATUS1.isEmpty()) {
            WebElement Familienstand = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[1]/div[5]/div[2]/div[2]"));
            boolean Familienstand1 = Familienstand.getText().equals("Bitte wählen Sie Ihren Familienstand aus.");
            System.out.println(Familienstand.getText());
            Assert.assertTrue(Familienstand1, "success");

        }


        WebElement NATIONALITY = driver.findElement(By.id("NATIONALITY"));
        String NATIONALITY1 = NATIONALITY.getAttribute("value");
        if (NATIONALITY1.isEmpty()) {
            WebElement Staatsangehörigkeit = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[1]/div[6]/div[2]/div[2]"));
            boolean Staatsangehörigkeit1 = Staatsangehörigkeit.getText().equals("Bitte wählen Sie Ihre Staatsangehörigkeit aus.");
            System.out.println(Staatsangehörigkeit.getText());
            Assert.assertTrue(Staatsangehörigkeit1, "success");

        }


        WebElement POSTAL_CODE = driver.findElement(By.id("POSTAL_CODE"));
        String POSTAL_CODE1 = POSTAL_CODE.getAttribute("value");
        if (POSTAL_CODE1.isEmpty()) {
            WebElement Postleitzahl = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[2]/div[1]/div[2]/div[2]"));
            boolean Postleitzahl1 = Postleitzahl.getText().equals("Bitte geben Sie Ihre Postleitzahl an.");
            System.out.println(Postleitzahl.getText());
            Assert.assertTrue(Postleitzahl1, "success");

            WebElement Wohnort = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[2]/div[1]/div[2]/div[3]"));
            boolean Wohnort1 = Wohnort.getText().equals("Bitte überprüfen Sie Ihren Wohnort.");
            System.out.println(Wohnort.getText());
            Assert.assertTrue(Wohnort1, "success");

        }


        WebElement STREET = driver.findElement(By.name("STREET"));
        String STREET1 = STREET.getAttribute("value");
        if (STREET1.isEmpty()) {
            WebElement Meldeadresse = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[2]/div[2]/div[2]/div[2]"));
            boolean Meldeadresse1 = Meldeadresse.getText().equals("Bitte geben Sie Ihre Meldeadresse an.");
            System.out.println(Meldeadresse.getText());
            Assert.assertTrue(Meldeadresse1, "success");

        }


        WebElement HOUSE_NUMBER = driver.findElement(By.id("HOUSE_NUMBER"));
        String HOUSE_NUMBER1 = HOUSE_NUMBER.getAttribute("value");
        if (HOUSE_NUMBER1.isEmpty()) {
            WebElement Hausnummer = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[2]/div[2]/div[2]/div[3]"));
            boolean Hausnummer1 = Hausnummer.getText().equals("Bitte geben Sie die Hausnummer Ihrer Meldeadresse an.");
            System.out.println(Hausnummer.getText());
            Assert.assertTrue(Hausnummer1, "success");

        }


        WebElement HOUSING_SITUATION = driver.findElement(By.id("HOUSING_SITUATION"));
        String HOUSING_SITUATION1 = HOUSING_SITUATION.getAttribute("value");
        if (HOUSING_SITUATION1.isEmpty()) {
            WebElement Wohnsituation = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[2]/div[3]/div[2]/div[2]"));
            boolean Wohnsituation1 = Wohnsituation.getText().equals("Bitte wählen Sie Ihre aktuelle Wohnsituation aus.");
            System.out.println(Wohnsituation.getText());
            Assert.assertTrue(Wohnsituation1, "success");
        }

        WebElement PHONENUMBER_MOBILE = driver.findElement(By.id("PHONENUMBER_MOBILE"));
        String PHONENUMBER_MOBILE1 = PHONENUMBER_MOBILE.getAttribute("value");
        if (PHONENUMBER_MOBILE1.isEmpty()) {
            WebElement Mobilnummer = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[3]/div[1]/div[2]/div[2]"));
            boolean Mobilnummer1 = Mobilnummer.getText().equals("Für eventuelle Rückfragen benötigen wir Ihre deutsche Mobilnummer.");
            System.out.println(Mobilnummer.getText());
            Assert.assertTrue(Mobilnummer1, "success");
        }

        WebElement CONTACT_EMAIL = driver.findElement(By.id("CONTACT_EMAIL"));
        String CONTACT_EMAIL1 = CONTACT_EMAIL.getAttribute("value");
        if (CONTACT_EMAIL1.isEmpty()) {
            WebElement EMail = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/section[3]/div[2]/div[2]/div[2]"));
            boolean EMail1 = EMail.getText().equals("Für den Versand der Antragsbestätigung benötigen wir Ihre E-Mail-Adresse.");
            System.out.println(EMail.getText());
            Assert.assertTrue(EMail1, "success");
        }
    }


    @Test(priority = 8)
    private void FillFields() throws InterruptedException {


        WebElement C = driver.findElement(By.id("GENDER_MALE"));

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", C);

        WebElement GIVEN_NAME = driver.findElement(By.id("GIVEN_NAME"));
        GIVEN_NAME.sendKeys("Abbas");
        WebElement LAST_NAME = driver.findElement(By.id("LAST_NAME"));
        LAST_NAME.sendKeys("Ali");
        WebElement DATE_OF_BIRTH = driver.findElement(By.id("DATE_OF_BIRTH"));
        DATE_OF_BIRTH.sendKeys("22.02.1982");


        WebElement PLACE_OF_BIRTH = driver.findElement(By.id("PLACE_OF_BIRTH"));
        PLACE_OF_BIRTH.sendKeys("Zurich");
        WebElement CONTACT_EMAIL = driver.findElement(By.id("CONTACT_EMAIL"));

        CONTACT_EMAIL.sendKeys("abc@gmail.com");


        WebElement POSTAL_CODE = driver.findElement(By.id("POSTAL_CODE"));
        POSTAL_CODE.sendKeys("81829");

        WebElement STREET = driver.findElement(By.name("STREET"));
        STREET.sendKeys("Willy-Brandt-Allee");
        WebElement HOUSE_NUMBER = driver.findElement(By.id("HOUSE_NUMBER"));
        HOUSE_NUMBER.sendKeys("19");

        WebElement PHONENUMBER_MOBILE = driver.findElement(By.id("PHONENUMBER_MOBILE"));
        PHONENUMBER_MOBILE.sendKeys("+4915205455890");
        Select MARITAL = new Select(driver.findElement(By.id("MARITAL_STATUS")));
        MARITAL.selectByVisibleText("ledig");
        Thread.sleep(1000);
        Select NATIONALITY1 = new Select(driver.findElement(By.id("NATIONALITY")));
        NATIONALITY1.selectByVisibleText("Deutschland");
        Thread.sleep(1000);
        Select HOUSING_SITUATION = new Select(driver.findElement(By.id("HOUSING_SITUATION")));
        HOUSING_SITUATION.selectByVisibleText("Eigenheim");
        Thread.sleep(1000);
        WebElement weiter2 = driver.findElement(By.xpath("//*[@id=\"application-form\"]/div/div[3]/form/div/div/a"));

        weiter2.click();
        Thread.sleep(1000);

    }

    @Test(priority = 9)
    private void next() {

        String URL = driver.getCurrentUrl();
        System.out.println(URL);

        boolean NextPage = driver.findElement(By.xpath("//*[text()='Sie haben es fast geschafft!']")).isDisplayed();
        Assert.assertEquals(NextPage, true, "Next Page");
        System.out.println("Personal Information submitted and next page appeared without any error");


    }



}