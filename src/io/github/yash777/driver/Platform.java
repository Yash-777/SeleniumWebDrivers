package com.github.yash777.driver;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.ProtectionDomain;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Platform class is to List out system properties.
 * 
os.name = Windows 10
os.version = 10.0
os.arch = amd64
sun.arch.data.model = 64
sun.desktop = windows
defaultCharset=windows-1252

 * @author yashwanth.m
 *
 */
public enum Platform {
	
	OSName () {
		@Override public String toString() {
			return System.getProperty("os.name").toUpperCase();
		}
	},
	JavaBit () {
		@Override public String toString() {
			System.out.println("Java Installed Bit Version : "+ System.getProperty("sun.arch.data.model"));
			System.out.println("Java Installed Architecture : "+ System.getProperty("os.arch"));
			return System.getProperty("sun.arch.data.model");
		}
	},
	codePath () {
		@Override public String toString() {
			ProtectionDomain protectionDomain = Platform.class.getProtectionDomain();
			final File codePath = new File( protectionDomain.getCodeSource().getLocation().getPath() );
			System.out.println("Source CODE Path : "+ codePath.getPath());
			try {
				String decodedPath = URLDecoder.decode(codePath.getPath(), "UTF-8");
				return decodedPath;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return codePath.getPath();
		}
	},
	classPathJarVersion () {
		@Override public String toString() {
			String classpath = System.getProperty("java.class.path");
			String[] classpathEntries = classpath.split( File.pathSeparator );
			String[] library = new String[ classpathEntries.length ];
			String version = null;
			for ( int i = 0, j = 0; i < classpathEntries.length; i++ ) {
				if( !classpathEntries[i].equals( codePath ) 
						&& ( classpathEntries[i].contains("selenium-java-") || 
							 classpathEntries[i].contains("selenium-server-standalone-") ) ) {
					library[ j++ ] = classpathEntries[i];
					System.out.format("%d : %s \n", i, classpathEntries[i] );
					
					version = classpathEntries[i];
					int n = version.lastIndexOf("/");
					version = version.substring(n+1, version.length());
					
					// selenium-java-2.53.0.jar || selenium-server-standalone-3.5.0.jar
					Matcher m = Pattern.compile("(\\d+.\\d+.\\d+)").matcher( version );
					if( m.find() ) {
						return m.group(1);
					}
				}
			}
			return version;
		}
	},
	tempPath () {
		@Override public String toString() {
			try {
				File temp = File.createTempFile("dummy", ".tmp");
				String absolutePath = temp.getAbsolutePath();
				String tempFilePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
				return tempFilePath;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return System.getProperty("java.io.tmpdir");
		}
	};
}
