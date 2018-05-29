/*
 * (C) Copyright 2018 Yashwanth Merugu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.yash777.driver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.yash777.files.FilesActions;
import io.github.yash777.files.ZIPExtracter;

/**
 * This class makes driver automation. If the driver is not available in the Temporary path
 * then it is going to download from the cloud and saves to Temporary/Drivers directory and
 * servers from that location.
 * 
 * <UL> DRIVERS:
 * <LI> <B>Firefox</B> - <I>Firefox driver is included in the selenium-server-standalone.jar 
 * available in the downloads</I></LI>
 * <LI> <B>Internet Explorer</B> - <I>The InternetExplorerDriver is a standalone server 
 * which implements WebDriver's wire protocol.</I>
 * <p><b>Note:</b> Use only 32 bit IE driver to increase performance in SendKeys, Screenshot and Links.</p></LI>
 * <LI> <B>Chrome</B> - <I>ChromeDriver is a standalone server which implements WebDriver's 
 * wire protocol for Chromium.</I></LI>
 * <LI> <B>Opera</B> - <I>OperaDriver is a vendor-supported WebDriver implementation developed
 *  by Opera Software and volunteers that implements WebDriver API for Opera.</I>
 * 	<UL> <B><a href="https://stackoverflow.com/a/31586683/5081877" >OperaChromiumDriver:</a></B>
 * 		<LI>OperaChromiumDriver is a WebDriver implementation derived from ChromeDriver 
 *  and adapted by Opera. http://www.opera.com/docs/history/</LI>
 *  </UL>
 * </LI>
 * <LI> <B>Safari</B> - <I>The SafariDriver is implemented as a Safari browser extension.
 * The driver inverts the traditional client/server relationship and communicates with the 
 * WebDriver client using WebSockets.</I></LI>
 * 
 * @author yashwanth.m
 *
 */
public final class Drivers extends DriverVersions {
	public Drivers() {
	}
	
	public static void main(String[] args) throws IOException {
		
		Drivers drivers = new Drivers();
		/*String chVersion = "2.21";
		String chromePatern = "(^\\d+.\\d+\\d+$)";
		String chromePatternMsg = "Selected Chrome Driver version should be in format of '2.24'.";
		
		String ffVersion = "v.19.1";
		String firefoxPatern = "(^v\\d+.\\d+\\d+.\\d+$)";
		String firefoxPatternMsg = "Selected Firefox Driver version should be in format of 'v0.19.1'.";
		Drivers drivers = new Drivers();
		System.out.println( drivers.validatePattern(chromePatern, chVersion, chromePatternMsg) );
		System.out.println( drivers.validatePattern(firefoxPatern, ffVersion, firefoxPatternMsg) );*/
		
		System.out.println(System.getProperty("os.name"));
		if( Platform.OSName.toString().contains("WIN") ) {
			System.out.println("====");
		}
		
		// System.setProperty("javax.net.debug", "all");
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		//System.setProperty("selenium.drivers.mappingfile", "D:/DriversMapping.txt");
		
		System.out.println("Chiper : "+ System.getProperty("https.protocols") );
		String path = null;
		//path = drivers.getDriverPath(Browser.FIREFOX, 41, "");
		//path = drivers.getDriverPath(Browser.FIREFOX, 48, "v0.19.1");
		//path = drivers.getDriverPath(Browser.CHROME, 51, "2.24");
		//path = drivers.getDriverPath(Browser.IEXPLORE, 11, "");
		path = drivers.getDriverPath(Browser.OPERA, 50, "");
		//path = drivers.getDriverPath(Browser.OPERA, 28, "");
			//path = drivers.getSeleniumVersionPath("2.53.0");
			//path = drivers.getFireFoxExe_Path("v0.19.1");
			//path = drivers.getIExplorerExe_Path();
		System.out.println("Path : "+path);
		
	}
	protected boolean validatePattern(String pattern, String str, String errMsg) {
		Matcher matcher = Pattern.compile( pattern ).matcher( str );
		if( matcher.find() ) {
			System.out.println("String : "+ matcher.group(1));
		} else {
			throw new WebDriverException( errMsg );
		}
		return true;
	}
	
	public String getDriverPath(Browser browser, int version, String versionPack) throws WebDriverException, IOException {
		
		if( versionPack == null || versionPack == "" ) {
			String jsonStr = null;
			String property = System.getProperty("selenium.drivers.mappingfile");
			if( property != null ) { // java.nio.file.NoSuchFileException:
				System.out.println( "Mapping File Path : "+ property );
				jsonStr = new String(Files.readAllBytes(Paths.get( property )));
			} else {
				try {
					jsonStr = FilesActions.readCloudFileAsString( versionMappingFileURL );
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			json_UnKnown_Format( jsonStr );
		}
		
		switch( browser ) {
			case FIREFOX:
				if( version > 23 && version < 48 ) {
					// 24 - 47
					return getFireFoxXPI_Path( Integer.valueOf( version ).toString() );
				} else if( version >= 48 ) {
					// 48 and above.
					try {
						return getFireFoxExe_Path( version, versionPack );
					} catch (IOException e) {
						throw new WebDriverException("Unable to download the file.");
					}
				} else {
					String msg = "Please select Firefox Browser version above 24.";
					System.err.println( msg );
					throw new WebDriverException( msg );
				}
			case IEXPLORE:
				if( Platform.OSName.toString().contains("WIN") ) {
					return getIExplorerExe_Path();
				} else {
					throw new WebDriverException( "Only Windows Operating system contains IE browser." );
				}
			case CHROME:
				if( version >= 40 ) {
					return getChromeExe_Path(version, versionPack);
				} else {
					String msg = "Please select Chrome Browser version above 40.";
					throw new WebDriverException( msg );
				}
			case OPERA:
				if( version == 28 || version == 29 || version > 44 ) {
					return getOperaExe_Path(version, versionPack);
				} else {
					String msg = "select Opera Browser version of 28,29,45,46,47,48,49,50.\n\t"
							+ "Or Provide the version package to download.";
					throw new WebDriverException( msg );
				}
		}	
		return null;
	}
	
	protected String getLastSlashValue( String locator ) {
		int n = locator.lastIndexOf("/");
		return locator.substring(n+1, locator.length());
	}
	protected String getSeleniumVersionPath( String seleniumVersion ) {
		Matcher m = Pattern.compile("(\\d+.\\d+).(\\d+)").matcher(seleniumVersion);
		String IE_Pack = null;
		if( m.find() ) {
			IE_Pack = m.group(1);
		}
		System.out.println("IE Selenium Package : "+IE_Pack);
		return IE_Pack;
	}
	private String getDownloadedExePath( String driverZIPURL, String zipFileFolder, String zipFile ) throws IOException {
		new FilesActions( driverZIPURL ).downloadUsing_NIOChannel( zipFile );
		return new ZIPExtracter().exratctFileList( zipFile, zipFileFolder);
	}
	
	protected String getIExplorerExe_Path() throws IOException {
		String seleniumVersion = Platform.classPathJarVersion.toString();
		System.out.println("Selenium Version : "+ seleniumVersion);
		if( seleniumVersion == null || seleniumVersion == "" ) {
			throw new WebDriverException("Selenium is not added to your project build path.");
		}
		String IE_Pack = getSeleniumVersionPath(seleniumVersion);
		
		String zipFileFolder, driverZIPURL, driverEXEPath = null;
		
		StringBuilder ZIPFile = new StringBuilder( Platform.tempPath+"/Drivers/" );
		if( IE_Pack != null) {
			
			// Use only 32 bit IE driver to increase performance in SendKeys, Screenshot and Links.
			if ( Platform.JavaBit.toString().contains("64") ) { // IEDriverServer_x64_2.53.0.zip
				driverZIPURL = String.format("%s/%s/IEDriverServer_x64_%s.zip",
								CloudURLs.IEXPLORE.toString(), IE_Pack, seleniumVersion);
				zipFileFolder = ZIPFile.toString() + "IExplore/64/"+seleniumVersion;
			} else { // IEDriverServer_Win32_2.53.0.zip
				driverZIPURL = String.format("%s/%s/IEDriverServer_Win32_%s.zip",
								CloudURLs.IEXPLORE.toString(), IE_Pack, seleniumVersion);
				zipFileFolder = ZIPFile.toString() + "IExplore/32/"+seleniumVersion;
			}
			
			FilesActions.createDirectory( zipFileFolder );
			
			File ieFile = new File( zipFileFolder+"/"+DriverExecutable.IEXPLORE);
			driverEXEPath = ieFile.getAbsolutePath();
			if ( !ieFile.exists() ) {
				System.out.println("Downloading IExplore Driver Extension from server...\n\t"+driverZIPURL);
				String zipFile = zipFileFolder+"/"+getLastSlashValue(driverZIPURL);
				driverEXEPath = getDownloadedExePath(driverZIPURL, zipFileFolder, zipFile);
			}
		}
		return driverEXEPath;
	}
	
	protected String getChromeExe_Path( int version, String versionPack ) throws IOException {
		StringBuilder executable = new StringBuilder( Platform.tempPath+"/Drivers/Chrome/" );
		if( versionPack != null && "".equals(versionPack) ) {
			versionPack = DriverVersions.chrome.get(version);
			System.out.println("Chrome Version package : "+versionPack);
			if( versionPack != null ) {
				executable.append( versionPack );
			} else {
				throw new WebDriverException("Unkwon version pack, Make entry of version and package in"
						+ "DriverVersions.chrome(versionNumber, VersionPackage) and execute again.");
			}
		} else {
			executable.append(versionPack);
		}
		
		FilesActions.createDirectory( executable.toString() );
		String zipFileFolder = executable.toString();
		
		executable.append("/");
		if( Platform.OSName.toString().contains("WIN") ) {
			executable.append( DriverExecutable.CHROME );
		} else {
			executable.append( DriverExecutable.CHROME_LINUX );
		}
		
		File chFile = new File( executable.toString() );
		String driverEXEPath = chFile.getAbsolutePath();
		if ( !chFile.exists() ) {
			String cloudURL = null;
			if( Platform.OSName.toString().contains("WIN") ) {
				cloudURL = CloudURLs.CHROME.toString()+"/%s/chromedriver_win32.zip";
			} else if ( Platform.OSName.toString().contains("MAC") ) { // MAC
				cloudURL = CloudURLs.CHROME.toString()+"/%s/chromedriver_mac32.zip";
			} else if ( Platform.OSName.toString().contains("LI") ) { // Linux 32,64
				if ( Platform.JavaBit.toString().contains("64") ) {
					cloudURL = CloudURLs.CHROME.toString()+"/%s/chromedriver_linux64.zip";
				} else {
					cloudURL = CloudURLs.CHROME.toString()+"/%s/chromedriver_linux32.zip";
				}
			} else {
				throw new WebDriverException("Unknown Operating system to get chrome driver.");
			}
			String driverZIPURL = String.format( cloudURL, versionPack);
			System.out.println("Downloading Chrome Driver Executable from server...\n\t"+ driverZIPURL);
			
			String zipFile = zipFileFolder+"/"+getLastSlashValue(driverZIPURL);
			driverEXEPath = getDownloadedExePath(driverZIPURL, zipFileFolder, zipFile);
		} else {
			System.out.println("Locally Chrome Driver Executable is available.");
		}
		
		System.out.println("Exe File : "+driverEXEPath);
		return driverEXEPath;
	}
	protected String getOperaExe_Path( int version, String versionPack ) throws IOException {
		StringBuilder executable = new StringBuilder( Platform.tempPath+"/Drivers/Opeara/" );
		if( versionPack != null && "".equals(versionPack) ) {
			versionPack = DriverVersions.opera.get(version);
			System.out.println("Opera Version package : "+versionPack);
			if( versionPack != null ) {
				executable.append( versionPack );
			} else {
				throw new WebDriverException("Unkwon version pack, Make entry of version and package in"
						+ "DriverVersions.opera(versionNumber, VersionPackage) and execute again.");
			}
		} else {
			executable.append(versionPack);
		}
		
		FilesActions.createDirectory( executable.toString() );
		String zipFileFolder = executable.toString();
		
		executable.append("/");
		
		if( versionPack.startsWith("v.") ) {
			if( Platform.OSName.toString().contains("WIN") ) {
				if ( Platform.JavaBit.toString().contains("64") ) {
					executable.append("operadriver_win64/");
				} else {
					executable.append("operadriver_win32/");
				}
				
			} else if ( Platform.OSName.toString().contains("MAC") ) { // MAC
				executable.append("operadriver_mac64/");
			} else if ( Platform.OSName.toString().contains("LI") ) { // Linux 32,64
				executable.append("operadriver_linux64/");
			}
		}
		/*int n = zipfilePath.lastIndexOf("/");
		String ZipFile = zipfilePath.substring(n+1, zipfilePath.length());
		if ( ZipFile.contains(".")) {
			ZipFile = ZipFile.substring(0, n-1);
		}
		System.out.println("Folder Name : "+ ZipFile );*/
		
		if( Platform.OSName.toString().contains("WIN") ) {
			executable.append( DriverExecutable.OPERA );
		} else {
			executable.append( DriverExecutable.OPERA_LINUX );
		}
		
		File opFile = new File( executable.toString() );
		String driverEXEPath = opFile.getAbsolutePath();
		if ( !opFile.exists() ) {
			String cloudURL = null;
			if( Platform.OSName.toString().contains("WIN") ) {
				if ( Platform.JavaBit.toString().contains("64") ) {
					cloudURL = CloudURLs.OPERA.toString()+"/download/%s/operadriver_win64.zip";
				} else {
					cloudURL = CloudURLs.OPERA.toString()+"/download/%s/operadriver_win32.zip";
				}
				
			} else if ( Platform.OSName.toString().contains("MAC") ) { // MAC
				cloudURL = CloudURLs.OPERA.toString()+"/download/%s/operadriver_mac64.zip";
			} else if ( Platform.OSName.toString().contains("LI") ) { // Linux 32,64
				cloudURL = CloudURLs.OPERA.toString()+"/download/%s/operadriver_linux64.zip";
			} else {
				throw new WebDriverException("Unknown Operating system to get Opera driver.");
			}
			
			String driverZIPURL = String.format( cloudURL, versionPack );
			System.out.println("Downloading Opera Driver Executable from server...\n\t"+ driverZIPURL);
			
			String zipFile = zipFileFolder+"/"+getLastSlashValue(driverZIPURL);
			driverEXEPath = getDownloadedExePath(driverZIPURL, zipFileFolder, zipFile);
		} else {
			System.out.println("Locally Opera Driver Executable is available.");
		}
		
		System.out.println("Exe File : "+driverEXEPath);
		return driverEXEPath;
	}
	protected String getFireFoxExe_Path( int version, String versionPack ) throws IOException {
		StringBuilder executable = new StringBuilder( Platform.tempPath+"/Drivers/FireFox/" );
		if( versionPack != null && "".equals(versionPack) ) {
			versionPack = DriverVersions.firefox.get(version);
			System.out.println("Firefox Version package : "+versionPack);
			if( versionPack != null ) {
				executable.append( versionPack );
			} else {
				throw new WebDriverException("Unkwon version pack, Make entry of version and package in"
						+ "DriverVersions.firefox(versionNumber, VersionPackage) and execute again.");
			}
		} else {
			executable.append(versionPack);
		}
		
		FilesActions.createDirectory( executable.toString() );
		String zipFileFolder = executable.toString();
		
		executable.append("/");
		
		if( Platform.OSName.toString().contains("WIN") ) {
			executable.append( DriverExecutable.FIREFOX );
		} else {
			executable.append( DriverExecutable.FIREFOX_LINUX );
		}
		
		File ffFile = new File( executable.toString() );
		String driverEXEPath = ffFile.getAbsolutePath();
		if ( !ffFile.exists() ) {
			String cloudURL = null;
			if( Platform.OSName.toString().contains("WIN") ) {
				if ( Platform.JavaBit.toString().contains("64") ) {
					cloudURL = CloudURLs.FIREFOX.toString()+"/download/%s/geckodriver-%s-win64.zip";
				} else {
					cloudURL = CloudURLs.FIREFOX.toString()+"/download/%s/geckodriver-%s-win32.zip";
				}
				
			} else if ( Platform.OSName.toString().contains("MAC") ) { // MAC
				cloudURL = CloudURLs.FIREFOX.toString()+"/download/%s/geckodriver-%s-macos.tar.gz";
			} else if ( Platform.OSName.toString().contains("LI") ) { // Linux 32,64
				if ( Platform.JavaBit.toString().contains("64") ) {
					cloudURL = CloudURLs.FIREFOX.toString()+"/download/%s/geckodriver-%s-linux64.tar.gz";
				} else {
					cloudURL = CloudURLs.FIREFOX.toString()+"/download/%s/geckodriver-%s-linux32.tar.gz";
				}
			} else {
				throw new WebDriverException("Unknown Operating system to get Opera driver.");
			}
			
			String driverZIPURL = String.format( cloudURL, versionPack, versionPack);
			System.out.println("Downloading FireFox Driver Executable from server...\n\t"+ driverZIPURL);
			
			String zipFile = zipFileFolder+"/"+getLastSlashValue(driverZIPURL);
			driverEXEPath = getDownloadedExePath(driverZIPURL, zipFileFolder, zipFile);
		} else {
			System.out.println("Locally FireFox Driver Executable is available.");
		}
		
		System.out.println("Exe File : "+driverEXEPath);
		return driverEXEPath;
	}
	
	protected String getFireFoxXPI_Path( String version ) throws WebDriverException {
		StringBuffer extensionURL = new StringBuffer();
		extensionURL.append(CloudURLs.FIREFOX_XPI);
		
		StringBuilder ZIPFile = new StringBuilder( Platform.tempPath+"/Drivers/" );
		ZIPFile.append("FireFox/");
		
		String firefoxFile = null, firefoxPack = null;
		String[] array53_1 = {"46", "47"};
		String[] array53 = {"39", "40", "41", "42", "43", "44", "45"};
		String[] array47 = {"34", "35", "36", "37", "38"};
		String[] array44 = {"24", "29", "31", "32", "33"};
		
		for(String i : array53_1) {
			if(version.equals(i)) {
				firefoxPack = "2.53.1";
				System.out.println("FF 2.53.1 Pack. FF version b/w [46 ~ 47]");
			}
		}
		for(String i : array53) {
			if(version.equals(i)) {
				firefoxPack = "2.53";
				System.out.println("FF 2.53 Pack. FF version b/w [39 ~ 45]");
			}
		}
		for(String i : array47) {
			if(version.equals(i)) {
				firefoxPack = "2.47";
				System.out.println("FF 2.47 Pack. FF version b/w [34 ~ 38]");
			}
		}
		for(String i : array44) {
			if(version.equals(i)) {
				firefoxPack = "2.44";
				System.out.println("FF 2.44 Pack. FF version b/w [24 ~ 33]");
			}
		}
		if( firefoxPack != null ) {
			String ZIPFolder = ZIPFile.append( firefoxPack ).toString();
			firefoxFile = ZIPFolder+"/"+DriverExecutable.FIREFOX_XPI.toString();
			FilesActions.createDirectory( ZIPFolder );
			extensionURL.append( firefoxPack +"/"+DriverExecutable.FIREFOX_XPI);
			File ffFile = new File( firefoxFile );
			try {
				if ( !ffFile.exists() ) {
					System.out.println("Downloading FireFox Driver Extension from server...");
					new FilesActions( extensionURL.toString() ).downloadUsing_NIOChannel( firefoxFile );
				} else {
					System.out.println("Locally FireFox Driver Extension is available.");
				}
				return firefoxFile;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			String msg = "Please choose Firefox XPI versions from [24,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47]";
			System.err.println( msg );
			throw new WebDriverException( msg );
		}
		return null;
	}
	

}
