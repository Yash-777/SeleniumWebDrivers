package io.github.yash777.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import io.github.yash777.driver.WebDriverException;
/**
 * Download the file and maintain locally.
 * 
 * @author yashwanth.m
 *
 */
public class FilesActions {
	protected String urlStr;
	protected byte[] buffer; 
	protected URL url;
	
	public FilesActions() {
	}
	
	public FilesActions( String urlStr ) {
		try {
			this.urlStr = urlStr;
			url = new URL( urlStr );
			buffer = new byte[1024];
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		String urlStr = "http://chromedriver.storage.googleapis.com/2.24/chromedriver_win32.zip";
		String chromeDriver = "E:\\chromedriver_stream.zip";
		
		new FilesActions(urlStr).downloadUsing_NIOChannel( chromeDriver );
	}

	/**
	 * Channels are designed to provide for bulk data transfers to and from NIO buffers.
	 * 
	 * https://en.wikipedia.org/wiki/Non-blocking_I/O_(Java)
	 * @param urlStr
	 * @param file
	 * @throws IOException
	 */
	public void downloadUsing_NIOChannel( String targetfile ) throws IOException {
		try {
			ReadableByteChannel rbc = Channels.newChannel( url.openStream() );
			
			// Getting file channels
			@SuppressWarnings("resource")
			FileChannel out = new FileOutputStream( new File( targetfile ) ).getChannel();
			// JavaVM does its best to do this as native I/O operations.
			out.transferFrom( rbc, 0, Long.MAX_VALUE );
			
			// Closing file channels will close corresponding stream objects as well.
			out.close();
			rbc.close();
		} catch ( javax.net.ssl.SSLHandshakeException e) {
			// Remote host closed connection during handshake - Check Internet.
		} catch ( FileNotFoundException e ) {
			throw new WebDriverException("\nInvalid URL : "+url);
		}
	}
	
	public void downloadUsing_FileUtils( String targetfile ) throws IOException {
		FileUtils.copyURLToFile( url, new File(targetfile) ); 
	}
	
	// http://commons.apache.org/proper/commons-io/description.html
	public static String readCloudFileAsString( String urlStr ) throws java.io.IOException {
		if( urlStr != null && urlStr != "" ) {
			
			java.io.InputStream s = null;
			String content = null;
			try {
				URL url = new URL( urlStr );
				s = (java.io.InputStream) url.getContent();
				content = IOUtils.toString(s, "UTF-8");
			} finally {
				if (s != null) s.close(); 
			}
			return content.toString();
		} else {
			throw new WebDriverException("\nInvalid Driver Mapping File URL : "+urlStr);
		}
	}
	
	public static void createDirectory( String folderName ) {
		File folder = new File( folderName );
		if ( !folder.exists()) {
			try {
				Files.createDirectories(Paths.get( folderName ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("Created Directory Sucess fully : "+folderName);
		} else {
			System.out.println("Directory available locally.");
		}
	}
}