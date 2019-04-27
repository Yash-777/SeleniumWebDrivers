### SeleniumWebDrivers
Automatic management of Selenium Driver Executable's in run-time for Java. Which automates the required driver form cloud into your machine. So, that the driver will allow WebDriver to [communicate with and control](https://developer.mozilla.org/en-US/docs/Learn/Tools_and_testing/Cross_browser_testing/Your_own_automation_environment) the browser.


### SeleniumWebDrivers API <img alt="SeleniumWebDrivers" align="middle" src="https://raw.githubusercontent.com/Yash-777/SeleniumWebDrivers/master/docs/SeleniumWebDrivers.png" width="80" height="80">


SeleniumWebDrivers is used for driver automation. If the driver is not available in the Temporary path then it is going to download from the cloud and saves to Temporary/Drivers directory and servers from that location. 

SeleniumWebDrivers is open source, released under the terms of [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0).


|   Browser   | Driver Cloud URL |  [Layout Engine](https://en.wikipedia.org/wiki/Web_browser_engine)  |   [JavaScript Engine](https://en.wikipedia.org/wiki/JavaScript_engine)  |
|  :---  |  :---:  |  ---:  |  :---:  |
| <img alt="Firefox Logo, 2017.svg" src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Firefox_Logo%2C_2017.svg/64px-Firefox_Logo%2C_2017.svg.png" width="40" height="40" > Firefox <sup>[releases](http://ftp.mozilla.org/pub/firefox/releases/)</sup> | [GeckoDriver](https://github.com/mozilla/geckodriver/releases) \| [XPI Extension](https://github.com/Yash-777/SeleniumDrives/raw/master/py/selenium/webdriver/firefox/) | [Gecko](https://en.wikipedia.org/wiki/Gecko_(software)) | [SpiderMonkey](https://en.wikipedia.org/wiki/SpiderMonkey) |
| <img alt="Google Chrome icon (September 2014).svg" src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Google_Chrome_icon_%28September_2014%29.svg/64px-Google_Chrome_icon_%28September_2014%29.svg.png" width="40" height="40"> Google Chrome <sup>[releases](https://www.slimjet.com/chrome/google-chrome-old-version.php)</sup> | ChromeDriver [Releases](http://chromedriver.storage.googleapis.com/index.html) <sup>[S3](http://chromedriver.storage.googleapis.com)</sup> | [Blink](https://en.wikipedia.org/wiki/Blink_(web_engine)) ([WebKit](https://en.wikipedia.org/wiki/WebKit) on iOS) | [V8](https://en.wikipedia.org/wiki/Chrome_V8) |
| <img alt="Opera 2015 icon.svg" src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Opera_2015_icon.svg/64px-Opera_2015_icon.svg.png" width="40" height="40"> Opera | OperaChromiumDriver [Releases](https://github.com/operasoftware/operachromiumdriver/releases) | [Blink](https://en.wikipedia.org/wiki/Blink_(web_engine)) (formerly [Presto](https://en.wikipedia.org/wiki/Presto_(layout_engine))) | V8 |
| <img alt="Small &quot;e&quot; letter with a blue aureola" src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/Internet_Explorer_10%2B11_logo.svg/65px-Internet_Explorer_10%2B11_logo.svg.png" width="41" height="40"> Internet Explorer |  [IEDriverServer](http://selenium-release.storage.googleapis.com) <sup>[CHANGELOG](https://raw.githubusercontent.com/SeleniumHQ/selenium/master/cpp/iedriverserver/CHANGELOG)</sup> | [Trident](https://en.wikipedia.org/wiki/Trident_(layout_engine)) | [Chakra (JScript9)](https://en.wikipedia.org/wiki/Chakra_(JScript_engine)) |
| <img alt="Microsoft Edge logo.svg" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d6/Microsoft_Edge_logo.svg/60px-Microsoft_Edge_logo.svg.png" width="40" height="40"> Microsoft Edge | [Microsoft WebDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver) | [EdgeHTML](https://en.wikipedia.org/wiki/EdgeHTML) | [Chakra (JavaScript)](https://en.wikipedia.org/wiki/Chakra_(JScript_engine)) |
| <img alt="Apple Safari 8.0 Icon" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Safari_browser_logo.svg/64px-Safari_browser_logo.svg.png" width="40" height="40"> Safari <sup>[10](https://webkit.org/blog/6900/webdriver-support-in-safari-10/)</sup> | SafariDriver.safariextz <sup>[2.48](http://selenium-release.storage.googleapis.com/index.html?path=2.48/)</sup> | [WebKit](https://en.wikipedia.org/wiki/WebKit) | [Nitro](https://en.wikipedia.org/wiki/WebKit#JavaScriptCore) |

> Note: The SafariDriver is implemented as a Safari browser extension. The driver inverts the traditional client/server relationship and communicates with the WebDriver client using WebSockets.

-----

###### Usage

In order to use SeleniumWebDrivers in a Maven project, you need to add the following dependency in your pom.xml (Java 7 or higher required):

You can dowlod form any of these Central Repositories like <img width="100px" src="https://search.maven.org/assets/images/logo.svg?v=1">, <img width="110px" src="https://mvnrepository.com/assets/images/392dffac024b9632664e6f2c0cac6fe5-logo.png">

 * Sonatype nexus <sup>Central [Repository](https://search.maven.org/search?q=g:io.github.Yash-777%20a:SeleniumWebDrivers), [Snapshot](https://oss.sonatype.org/content/repositories/snapshots/io/github/Yash-777/SeleniumWebDrivers/), [`pom`](https://search.maven.org/artifact/io.github.Yash-777/SeleniumWebDrivers/1.0.0/jar)</sup>
 * Maven Repository <sup>Central [Repository](https://repo.maven.apache.org/maven2/io/github/Yash-777/SeleniumWebDrivers/), [`pom`](https://mvnrepository.com/artifact/io.github.Yash-777/SeleniumWebDrivers/1.0.0)</sup>

```xml
<dependency>
    <groupId>io.github.Yash-777</groupId>
    <artifactId>SeleniumWebDrivers</artifactId>
    <version>1.0.0</version>
</dependency>
```

##### [SeleniumHQ](https://www.seleniumhq.org/download/)

Selenium is an umbrella project encapsulating a variety of tools and 
libraries enabling web browser automation. Selenium specifically 
provides infrastructure for the [W3C WebDriver specification](https://dvcs.w3.org/hg/webdriver/raw-file/tip/webdriver-spec.html) 
— a platform and language-neutral coding interface compatible with all 
major web browsers.

Selenium Documentation

* [User Manual](http://docs.seleniumhq.org/docs/)
* [Java](http://seleniumhq.github.io/selenium/docs/api/java/index.html) and jar (`Selenium Server Standalone [2X, 3X]` - [Maven](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-server-standalone), [AWS](http://selenium-release.storage.googleapis.com) )

Default [Version Mappling file](https://raw.githubusercontent.com/Yash-777/SeleniumDriverAutomation/master/Drivers/versions-mapping) from the GITHUB.
If the Default `Version Mappling file` doesnt contain latest mapping information then you can download the mapping file and upadate with new mapping's, then you can pass it to get the updated driver.


###### Example Usage

Either use default Version Mappling file (OR) Provide Version pack information as parameter.

```java
package io.github.yash777.driver;

Drivers drivers = new Drivers();
/** Auto Map driver version from browser version */
String CH51 = drivers.getDriverPath(Browser.CHROME, 51, "");
String FF46 = drivers.getDriverPath(Browser.FIREFOX, 46, "");
String FF55 = drivers.getDriverPath(Browser.FIREFOX, 55, "");
String OP50 = drivers.getDriverPath(Browser.OPERA, 50, "");
String OP48 = drivers.getDriverPath(Browser.OPERA, 28, "");

/** You can specify the driver version */
String FF_V19 = drivers.getDriverPath(Browser.FIREFOX, 48, "v0.19.1");
String CH_2_24 = drivers.getDriverPath(Browser.CHROME, 51, "2.24");

/** IE Driver form selenium version pack.
 * Exception « io.github.yash777.driver.WebDriverException: Selenium is not added to your project build path.
 */
String IE_SeleniumVer = drivers.getDriverPath(Browser.IEXPLORE, 11, "");
```
Download the mapplig file and update it with new mapping information. So, that applicaton uses new mapping information.

* If you maintain **file over server**, then use the below sample code.

```java
String cloud_VersionMappingURL = "https://raw.githubusercontent.com/Yash-777/SeleniumDriverAutomation/master/Drivers/versions-mapping";
DriverVersions.setVersionMappingFileURL( cloud_VersionMappingURL );
```

* If you maintain the **file in local machine**, then use the below sample code.

```java
System.setProperty("selenium.drivers.mappingfile", "D:/DriversMapping.txt");
```

Samples to add drvier files to Selenium.

```java
/** After getting the driver path you can set it as System property */
// Firefox
//F_Version XPI - List is > 17,24,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47.
System.setProperty(FirefoxDriver.SystemProperty.DRIVER_XPI_PROPERTY, FF46);
//SELENIUM-3 Mozilla GeckoDriver
System.setProperty("webdriver.gecko.driver", FF55);

// Google Chrome
System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, CH51);
// Opera  
System.setProperty("webdriver.opera.driver", OP50);
// Internet Explorer
System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, IE_SeleniumVer);  
```

If you are getting an exception like `javax.net.ssl.SSLException: Received fatal alert: protocol_version` then [refer to this POST](https://stackoverflow.com/a/49556107/5081877).
