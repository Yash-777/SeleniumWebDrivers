package io.github.yash777.driver;

/**
 * CloudURLs enum maintains the all the cloud URL's of the browsers.
 * @author yashwanth.m
 *
 */
enum CloudURLs {
	FIREFOX_XPI {
		@Override public String toString() {
			return "https://github.com/Yash-777/SeleniumDrives/raw/master/py/selenium/webdriver/firefox/";
			}
	},
	FIREFOX {
		@Override public String toString() {
			return "https://github.com/mozilla/geckodriver/releases";
			}
	},
	CHROME {
		@Override public String toString() {
			return "http://chromedriver.storage.googleapis.com";
			}
	},
	OPERA {
		@Override public String toString() {
			return "https://github.com/operasoftware/operachromiumdriver/releases";
			}
	},
	IEXPLORE {
		@Override public String toString() {
			return "http://selenium-release.storage.googleapis.com";
			}
	},
	EDGE {
		@Override public String toString() {
			return "https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver";
			}
	}
}