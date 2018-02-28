### SeleniumWebDrivers
Automatic management of Selenium Driver Executable's in run-time for Java. Which automates the required driver form cloud into your machine.


```html
https://oss.sonatype.org/content/repositories/snapshots/<groupId>/<artifactId>
    /<version>/<artifactId>-<version>-<inceptionYear.Month.Date>.TimeZone.pom
    
https://oss.sonatype.org/content/repositories/snapshots/io/github/Yash-777/SeleniumWebDrivers
    /0.0.1-SNAPSHOT/SeleniumWebDrivers-0.0.1-*****.pom
```


|   Browser   | Driver Cloud URL |
|  :---  |  :---:  |
| Firefox <sup>[Old Versions](http://ftp.mozilla.org/pub/firefox/releases/)</sup> | [Mozilla GeckoDriver](https://github.com/mozilla/geckodriver/releases) \| [XPI Extension](https://github.com/Yash-777/SeleniumDrives/raw/master/py/selenium/webdriver/firefox/) |
| Google Chrome <sup>[Old Versions](https://www.slimjet.com/chrome/google-chrome-old-version.php)</sup> | ChromeDriver [Releases](http://chromedriver.storage.googleapis.com/index.html) <sup>[S3](http://chromedriver.storage.googleapis.com)</sup> |
| Opera | OperaChromiumDriver [Releases](https://github.com/operasoftware/operachromiumdriver/releases) |
| Internet Explorer |  Internet Explorer [Driver Server](http://selenium-release.storage.googleapis.com) <sup>[CHANGELOG](https://raw.githubusercontent.com/SeleniumHQ/selenium/master/cpp/iedriverserver/CHANGELOG)</sup> |
| Microsoft Edge | [Microsoft WebDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver) |


-----

### SeleniumWebDrivers API

SeleniumWebDrivers is used for driver automation. If the driver is not available in the Temporary path then it is going to download from the cloud and saves to Temporary/Drivers directory and servers from that location. 

SeleniumWebDrivers is open source, released under the terms of [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0).

###### Usage

In order to use SeleniumWebDrivers in a Maven project, you need to add the following dependency in your pom.xml (Java 7 or higher required):

```xml
<properties>
	<SeleniumWebDrivers>0.0.1-SNAPSHOT</SeleniumWebDrivers>
	<jarchivelib>0.7.1</jarchivelib>
</properties>
<dependencies>
	<dependency>
	  <groupId>io.github.Yash-777</groupId>
	  <artifactId>SeleniumWebDrivers</artifactId>
	  <version>0.0.1-SNAPSHOT</version>
	</dependency>
	<dependency>
	  <groupId>org.rauschig</groupId>
	  <artifactId>jarchivelib</artifactId>
	  <version>${jarchivelib}</version>
	  <scope>compile</scope>
	</dependency>
</dependencies>
```

Adding the dependency as system scope and refer to it by its full path.

```xml
<!-- ${basedir} represents the directory containing pom.xml.
<systemPath>${project.basedir}/lib/SeleniumWebDrivers.jar</systemPath>
-->
<dependencies>
	<dependency>
	  <groupId>io.github.Yash-777</groupId>
	  <artifactId>SeleniumWebDrivers</artifactId>
	  <version>0.0.1-SNAPSHOT</version>
	    <scope>system</scope>
	    <systemPath>E://SeleniumWebDrivers.jar</systemPath>
	</dependency>
</dependencies>
```

##### [SeleniumHQ](https://www.seleniumhq.org/download/)

Selenium Server Standalone [2X] - [Maven](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-server-standalone), [AWS](http://selenium-release.storage.googleapis.com)

###### Example

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
