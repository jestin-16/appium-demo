package com.example;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SelendroidLoginTest {

    AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        System.out.println("🚀 Starting Appium session for Selendroid login demo...");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        // ✅ Correct APK location
        caps.setCapability(MobileCapabilityType.APP, "C:\\AppiumDemo\\selendroid-test-app.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("✅ Selendroid App launched!");
    }

    @Test
    public void loginTest() {
        System.out.println("🔹 Typing username...");

        driver.findElementById("io.selendroid.testapp:id/my_text_field")
                .sendKeys("testUser");

        System.out.println("🔹 Clicking the button...");
        driver.findElementById("io.selendroid.testapp:id/buttonTest")
                .click();

        System.out.println("🔹 Reading the popup result...");

        String message = driver.findElementById("android:id/message")
                .getText();

        System.out.println("📌 Dialog says: " + message);

        Assert.assertTrue(message.contains("testUser"),
                "Username not found in confirmation!");

        System.out.println("✅ Login Test Passed!");

        driver.findElementById("android:id/button1").click();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Session closed.");
        }
    }
}
