package nl.ica.breas.burgernet.backend.hulpobjecten;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * 
 * Filename: RestHelper.java <br/>
 * Description for the class RestHelper:
 * 
 * Example usage:
 * 
 * <pre>
 * 
 * </pre>
 * 
 * @author rodmidde
 * @version
 * 
 *          Copyright (c) 2011 Avisi, All rights reserved.
 * 
 */
public final class RestHelper {
	/**
	 * Empty private constructor to avoid instantiating the class.
	 * 
	 */
	private RestHelper() {
	}

	/**
	 * Executes a get method on the given url.
	 * 
	 * @param url The url to send the get method to
	 * @param maxLength maximale lengte
	 *            
	 * @return String response
	 * @throws IOException
	 *             caused by an unavailable webserver
	 */
	public static String doGet(String url, int maxLength) throws IOException {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		setHeaders(getMethod);

		int status = httpClient.executeMethod(getMethod);
		if (status == HttpStatus.SC_OK) {
			return getMethod.getResponseBodyAsString(maxLength);
		} else {
			throw new IOException("Cannot execute get-method: " + status);
		}
	}
	/**
	 * Executes a get method on the given url
	 * 
	 * @param url
	 *            The url to send the get method to
	 * @return String response
	 * @throws IOException
	 *             caused by an unavailable webserver
	 */
	public static String doGet(String url) throws IOException {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		setHeaders(getMethod);

		int status = httpClient.executeMethod(getMethod);
		if (status == HttpStatus.SC_OK) {
			return getMethod.getResponseBodyAsString();
		} else {
			throw new IOException("Cannot execute get-method: " + status);
		}
	}

	/**
	 * This sets the headers.
	 * @param httpMethod POST or GET.
	 */
	private static void setHeaders(HttpMethodBase httpMethod) {
		httpMethod.setRequestHeader("Accept", "application/json");
		httpMethod.setRequestHeader("Content-Type", "application/json");
	}

}
