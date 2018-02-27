package io.github.yash777.driver;

/**
 * DriverExecutable enum contains the all the driver's names.
 * 
 * @author yashwanth.m
 *
 */
public enum DriverExecutable {
	FIREFOX_XPI {
		@Override public String toString() {
			return "webdriver.xpi";
			}
	},
	FIREFOX {
		@Override public String toString() {
			return "geckodriver.exe";
			}
	},
	FIREFOX_LINUX {
		@Override public String toString() {
			return "geckodriver";
			}
	},
	CHROME {
		@Override public String toString() {
			return "chromedriver.exe";
			}
	},
	CHROME_LINUX {
		@Override public String toString() {
			return "chromedriver";
			}
	},
	OPERA {
		@Override public String toString() {
			return "operadriver.exe";
			}
	},
	OPERA_LINUX {
		@Override public String toString() {
			return "operadriver";
			}
	},
	IEXPLORE {
		@Override public String toString() {
			return "IEDriverServer.exe";
			}
	}/*,
	EDGE {
		@Override public String toString() {
			return "MicrosoftWebDriver.exe";
			}
	}*/
}