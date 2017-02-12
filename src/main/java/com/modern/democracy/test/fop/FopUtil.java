package com.modern.democracy.test.fop;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

/**
 * The Class FopUtil.
 *
 * @version $Id: $
 */
public class FopUtil {

	// ===========================================
	// Public Members
	// ===========================================

	// ===========================================
	// Private Members
	// ===========================================

	/** The MSG_CREATING_PDF_ERROR. */
	private static final String MSG_CREATING_PDF_ERROR = "Error creating PDF, details=%s";

	/** The Constant FOP_CONFIG_FILE. */
	private static final String FOP_CONFIG_FILE = "fop.xconf";
	
	/** The XSL_FILE. */
	private static final String XSL_FILE = "pdf-report.xsl";
	
	/** The XML_FILE. */
	private static final String XML_FILE = "dataset.xml";
	
	/** The HEADER_FILE. */
	private static final String HEADER_FILE = "header-footer.xsl";

	// ===========================================
	// Static initialisers
	// ===========================================

	// ===========================================
	// Constructors
	// ===========================================

	// ===========================================
	// Public Methods
	// ===========================================

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		/**
		 * Creating the pdf as a byte array allows it to be streamed to a browser
		 */
		OutputStream outStream = null;

		try {
			outStream = new BufferedOutputStream(new FileOutputStream(new File("myfile.pdf")));
			byte[] padAsByteArray = FopUtil.createPdf(getXMLString(XML_FILE), getXMLString(XSL_FILE));  
			IOUtils.write(padAsByteArray, outStream);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			IOUtils.closeQuietly(outStream);
		}

	}

	/**
	 * Creates the pdf.
	 *
	 * @param xml the xml
	 * @param xslt the xslt
	 * @return the byte[]
	 */
	public static byte[] createPdf(String xml, String xslt) {

		byte[] byteArray = null;
		ByteArrayOutputStream outStream = null;

		try {
			Source xmlSource = getXmlSource(xml);
			Source xsltSource = getXmlSource(xslt);

			/**
			 *  Create an instance of fop factory
			 */
			FopFactory fopFactory = FopFactory.newInstance(getConfigFile());

			/**
			 *  Create the output stream
			 */
			outStream = new ByteArrayOutputStream();

			/**
			 *  Create transformer from TransformerFactory
			 */
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setURIResolver(new MyResolver(getXMLString(HEADER_FILE)));
			Transformer xslfoTransformer = transformerFactory.newTransformer(xsltSource);

			/**
			 *  Construct fop with desired output format
			 */
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, outStream);

			/**
			 *  Resulting SAX events (the generated FO) must be piped through to FOP
			 */
			Result res = new SAXResult(fop.getDefaultHandler());

			/**
			 * Transform the xml to a pdf
			 */
			xslfoTransformer.transform(xmlSource, res);

			/**
			 * return the pdf bytes
			 */
			byteArray = outStream.toByteArray();

		} catch (TransformerConfigurationException e) {
			handleException(e);
		} catch (FOPException e) {
			handleException(e);
		} catch (TransformerException e) {
			handleException(e);
		} catch (SAXException e) {
			handleException(e);
		} catch (IOException e) {
			handleException(e);
		} catch (URISyntaxException e) {
			handleException(e);
		} finally {
			IOUtils.closeQuietly(outStream);
		}

		return byteArray;
	}

	
	//read from xml
	private static String getXMLString(String filename) throws IOException {
		
		URL url = FopUtil.class.getClassLoader().getResource(filename); 
		File file = new File(url.getPath());

		Reader fileReader = new FileReader(file);
		BufferedReader bufReader = new BufferedReader(fileReader); 
		StringBuilder sb = new StringBuilder(); 
		String line = bufReader.readLine(); 
		
		while( line != null){ 
			sb.append(line).append("\n"); 
			line = bufReader.readLine(); 
		} 
		String xml2String = sb.toString(); 
		bufReader.close();

		return xml2String;		
	}

	// ===========================================
	// Protected Methods
	// ===========================================

	// ===========================================
	// Private Methods
	// ===========================================

	/**
	 * Gets the config file.
	 *
	 * @return the config file
	 * @throws URISyntaxException the URI syntax exception
	 */
	private static File getConfigFile() throws URISyntaxException {
		URL url = FopUtil.class.getClassLoader().getResource(FOP_CONFIG_FILE);
		File file = new File(url.getPath());
		return file;
	}

	/**
	 * Gets the xml source.
	 *
	 * @param xml the xml
	 * @return the xml source
	 */
	private static StreamSource getXmlSource(final String xml) {
		return new StreamSource(new StringReader(xml));
	}

	/**
	 * Handle exception.
	 *
	 * @param e the e
	 */
	private static void handleException(Exception e) {
		System.out.println(e.getMessage());
		throw new ApacheFopException(String.format(MSG_CREATING_PDF_ERROR, e.getMessage()), e);
	}
}


/*
 * To handle imports in the xsl file
 * 
 * */
class MyResolver implements URIResolver {
    String xsltStr;

    public MyResolver(String xsltStr) {
      this.xsltStr = xsltStr;
    }

    public Source resolve(String href,String base) {

        return new StreamSource(new StringReader(this.xsltStr));
    }
  }
