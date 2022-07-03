//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class EcommerceSignUp {
//
//    public static void main(String[] args) throws InterruptedException {
//
//        // 1 -
//      String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe\\";
//      System.out.println(chromePath);
//        System.setProperty("webdriver.chrome.driver", chromePath
//                );
//
//        // 2 - New object
//        WebDriver driver=new ChromeDriver();
//
//        // 3 - Navigate to ecommerce website
//        String url="http://automationpractice.com/index.php";
//        driver.navigate().to(url);
//        driver.manage().window().maximize();
//        Thread.sleep(3000);
//
//       // 4 - Close Driver
//        driver.close();
//    }
//}

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class EcommerceTest {
    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe\\";
        System.out.println(chromePath);
        System.setProperty("webdriver.chrome.driver", chromePath
        );
        driver = new ChromeDriver();
        String url = "http://automationpractice.com/index.php";
        driver.navigate().to(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);


        // Click on Sign in
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        driver.findElement(By.id("email")).sendKeys("sahar@testt.com");
        driver.findElement(By.name("SubmitLogin")).click();
        String passwordError = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
        Assert.assertEquals(passwordError, "Password is required.");
        Thread.sleep(3000);


        // Create a new account user
        // Enter email address
        driver.findElement(By.cssSelector("[name='email_create']")).sendKeys("salma@test.com");
        // Click on "Create an account"
        driver.findElement(By.xpath("//button[@name=\"SubmitCreate\"]")).click();
        Thread.sleep(3000);

        // Select Title
        driver.findElement(By.xpath("//input[@id='id_gender2']")).click();
        driver.findElement(By.name("customer_firstname")).sendKeys("Sahar");
        driver.findElement(By.name("customer_lastname")).sendKeys("Khaled");
        driver.findElement(By.id("passwd")).sendKeys("12OMRAN");


        // Enter your address
        driver.findElement(By.id("company")).sendKeys("test");
        driver.findElement(By.id("address1")).sendKeys("Test 81/1,2nd cross");
        driver.findElement(By.id("city")).sendKeys("XYZ");

        // Select State
        WebElement stateDropdown = driver.findElement(By.name("id_state"));
        Select oSelect = new Select(stateDropdown);
        oSelect.selectByValue("4");

        driver.findElement(By.name("postcode")).sendKeys("15675");

        // Select Country
        WebElement countryDropDown = driver.findElement(By.name("id_country"));
        Select oSelectC = new Select(countryDropDown);
        oSelectC.selectByVisibleText("United States");

        //Enter Mobile Number"//input[@id='id_gender1']"
        driver.findElement(By.id("phone_mobile")).sendKeys("231414141");
        driver.findElement(By.xpath("//input[@name='alias']")).clear();
        driver.findElement(By.xpath("//input[@name='alias']")).sendKeys("home");
        driver.findElement(By.name("submitAccount")).click();
        String userText = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).getText();

        // Validate that user has created
        if (userText.contains("Sahar")) {
            System.out.println("User Verified,Test case Passed");
        } else {
            System.out.println("User Verification Failed,Test case Failed");
        }

        // select blouse sub category
        Actions actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        WebElement womenTab = driver.findElement(By.linkText("WOMEN"));
        WebElement blouseTab = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[2]/a"));
        actions.moveToElement(womenTab).moveToElement(blouseTab).click().perform();
        Thread.sleep(3000);

        // Get Product Name // select resulted product
        String ProductName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[2]/ul[1]/li[1]/div[1]/div[2]/h5[1]/a[1]")).getText();
        System.out.println(ProductName);
        driver.findElement(By.id("search_query_top")).sendKeys(ProductName);
        driver.findElement(By.name("submit_search")).click();

        // Get Name of Searched Product
        String SearchProductName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[2]/ul[1]/li[1]/div[1]/div[2]/h5[1]/a[1]")).getText();

        // Verify that correct Product is displaying after search
        if (ProductName.equalsIgnoreCase(SearchProductName)) {
            System.out.println(" Matched;Test Case Passed");
        } else {
            System.out.println(" NotMatched;Test Case Failed");
        }



        // test payment process
        WebElement SecondImg = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img"));
        WebElement MoreBtn = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[2]"));
        Actions action = new Actions(driver);
        actions.moveToElement(SecondImg).moveToElement(MoreBtn).click().perform();

        // Change quantity by 2
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys("2");

        // Select size as M
        WebElement selectSize = driver.findElement(By.xpath("//*[@id='group_1']"));
        Select oneOption = new Select(selectSize);
        oneOption.selectByVisibleText("M");

        // Select Color
        driver.findElement(By.id("color_11")).click();

        // Click on add to cart
        driver.findElement(By.xpath("//p[@id='add_to_cart']//span[.='Add to cart']")).click();

        // Click on proceed
        driver.findElement(By.xpath("/html//div[@id='layer_cart']//a[@title='Proceed to checkout']/span")).click();
        //Checkout page Proceed
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/p[2]/a[1]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/form/p/button/span")).click();

        // Agree terms&Conditions
        driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/p/button/span")).click();

        // Click on by bank wire
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
        // Confirm the order
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/form/p/button/span")).click();
        // Get Text
        String ConfirmationText = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p/strong\n")).getText();
//        String ConfirmationText = driver.findElement(By.xpath("//div[@id='center_column']/p[@class='alert alert-success']")).getText();

        // Verify that Product is ordered
        if (ConfirmationText.contains("complete")) {
            System.out.println("Your order has been completed.: Test Case Passed");
        } else {
            System.out.println("Your order was not fulfilled.: Test Case Failed");
        }



//        // authenticate again
//        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();
//        driver.findElement(By.linkText("Sign in")).click();
//        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
//        driver.findElement(By.id("email")).sendKeys("sahar@testt.com");
//        driver.findElement(By.id("passwd")).sendKeys("12OMRAN");
//        driver.findElement(By.name("SubmitLogin")).click();
//        String userPass = driver.findElement(By.xpath("//*[@id='passwd']")).getText();
//
//        if (userPass.equals("12OMRAN")) {
//            System.out.println("User password correct,Test case Passed");
//        } else {
//            System.out.println("User password failed,Test case Failed");
//        }
//
//        Thread.sleep(3000);


    }

}