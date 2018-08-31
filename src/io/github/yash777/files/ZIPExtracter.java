package io.github.yash777.files;

import java.io.File;
import java.io.IOException;

import org.rauschig.jarchivelib.ArchiveEntry;
import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.ArchiveStream;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;

/**
 * ZIPExtracter class is to extract the contents of a ZIP file.
 * <p>Example <a href="https://stackoverflow.com/a/48788415/5081877">Post</a></p>
 * 
 * @author yashwanth.m
 *
 */
public class ZIPExtracter extends FilesActions {
	public ZIPExtracter() {
	}
	public ZIPExtracter(String urlStr) {
		super(urlStr);
	}

	public static void main(String[] args) {
		String zipfilePath = 
				"E:/Selenium_Server/geckodriver-v0.19.0-linux64.tar.gz";
				//"E:/Selenium_Server/operadriver_win64.zip";
				//"E:/Selenium_Server/geckodriver-v0.19.0-win32.zip";
		String outdir = "E:/Selenium_Server/";
		
		try {
			String exratctFileList = new ZIPExtracter().exratctFileList(zipfilePath, outdir );
			System.out.println("Final File Name : "+ exratctFileList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * This method is used for reading files in the ZIP file format.
	 * Includes support for both compressed and uncompressed entries.
	 * 
	 * <P>File Separator: The system-dependent default name-separator character.
	 * This field is initialized to contain the first character of the value of the system property file.separator.
	 * On UNIX systems the value of this field is '/'; on Microsoft Windows systems it is '\\'.
	 * 
	 * @param zipfilePath « zip file to extract
	 * @param outdir      « extract zip file in this folder
	 * @return the driver file path.
	 * @throws IOException « Signals that an I/O exception of some sort has occurred.
	 */
	public String exratctFileList( String zipfilePath, String outdir ) throws IOException {
		String outpath = null;
		File archive = new File( zipfilePath );
		File destinationDir = new File( outdir );
		
		Archiver archiver = null;
		if( zipfilePath.endsWith(".zip") ) {
			archiver = ArchiverFactory.createArchiver( ArchiveFormat.ZIP );
		} else if ( zipfilePath.endsWith(".tar.gz") ) {
			archiver = ArchiverFactory.createArchiver( ArchiveFormat.TAR, CompressionType.GZIP );
		}
		archiver.extract(archive, destinationDir);
		
		ArchiveStream stream = archiver.stream( archive );
		ArchiveEntry entry;
		
		while( (entry = stream.getNextEntry()) != null ) {
			String entryName = entry.getName();
			System.out.println("Entery Name : "+ entryName );
			
			if( outdir.endsWith( File.separator ) || outdir.endsWith( "/" ) ) {
				outpath = outdir + entryName;
			} else {
				outpath = outdir + File.separator + entryName;
			}
			
			System.out.println( outpath );
			
			if( !( entryName.endsWith( File.separator ) || entryName.endsWith( "/" ) ) 
					&& entryName.toUpperCase().contains("DRIVER") ) {
				break;
			}
		}
		stream.close();
		
		return outpath;
	}
	
}