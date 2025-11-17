package com.example;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppiumDemoTest {

    private AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setup() throws Exception {
        File app = new File("C:\\AppiumDemo\\ApiDemos-debug.apk");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("app", app.getAbsolutePath());
        caps.setCapability("newCommandTimeout", 300);

        System.out.println("🚀 Starting Appium session...");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);

        System.out.println("✅ Appium session started successfully!");
    }

    @Test
    public void demoTest() throws InterruptedException {
        System.out.println("✅ App launched successfully!");

        // Click "Views"
        MobileElement views = driver.findElementByAccessibilityId("Views");
        views.click();
        Thread.sleep(1000);

        // Click "Controls"
        MobileElement controls = driver.findElementByAccessibilityId("Controls");
        controls.click();
        Thread.sleep(1000);

        // Click "1. Light Theme"
        MobileElement lightTheme = driver.findElementByAccessibilityId("2. Dark Theme");
        lightTheme.click();
        Thread.sleep(1000);

        // Type text in input box
        MobileElement editText = driver.findElementById("io.appium.android.apis:id/edit");
        editText.sendKeys("Hello hai this is a test message");
        Thread.sleep(2000);

        System.out.println("✅ Successfully typed into the input field!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🧹 Appium session ended.");
        }
    }
}
