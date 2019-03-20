package abbrobot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;





public class AbbRobotService {
	
	private String username = "Default User";
	private String password = "robotics";
	
	private String ip = "192.168.125.1";
//	private String lc = "localhost";
	
	private int AbbRobotStatus = 0;
	private int Virtual1Status = 0;
	private int Virtual2Status = 0;
	
	private DigestScheme robotAuth(){
    	DigestScheme digestScheme = new DigestScheme();
    	digestScheme.overrideParamter("realm", "validusers@robapi.abb");
    	return digestScheme;
	}
	
	
	
	private void initiateSendOrder(String manufactoringName,int value, String ipId, boolean log) throws ClientProtocolException, IOException {
		
		CloseableHttpClient client = HttpClients.createDefault();
		//set the URL for the specified variable and suitable action parameter
		String url = "http://"+checkRobotIp(ipId)+"/rw/rapid/symbol/data/RAPID/T_ROB1/mMoverob/"+manufactoringName+"?action=set";
		//use the POST verb
		HttpPost post = new HttpPost(url);
		
		List<NameValuePair> Param = new ArrayList<NameValuePair>();
		Param.add(new BasicNameValuePair("value",""+value));
		post.setEntity(new UrlEncodedFormEntity(Param));
		
		HttpHost targetHost = new HttpHost(new URL(url).getHost(),new URL(url).getPort(),new URL(url).getProtocol());
		
		HttpClientContext context = HttpClientContext.create();
    	
    	CredentialsProvider credPro = new BasicCredentialsProvider();
    	credPro.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username,password));
    	
    	AuthCache authCache = new BasicAuthCache();
    	
    	authCache.put(targetHost, robotAuth());
    	
    	context.setCredentialsProvider(credPro);
    	context.setAuthCache(authCache);
    	//execute and get the response
		HttpResponse response = client.execute(targetHost,post,context);
		
		
		if(log){
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		}
		
	}
	
	
	protected int initiateGetOrder(String manufactoringName, String ipId, boolean log) throws ClientProtocolException, IOException{
		
		CloseableHttpClient client = HttpClients.createDefault();
		//set the URL for the specified variable
		URL url = new URL("http://"+checkRobotIp(ipId)+"/rw/rapid/symbol/data/RAPID/T_ROB1/"+manufactoringName);
    	HttpHost targetHost = new HttpHost(url.getHost(),url.getPort(),url.getProtocol());    	
    	
    	HttpClientContext context = HttpClientContext.create();
    	    	
    	CredentialsProvider credPro = new BasicCredentialsProvider();
    	credPro.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username,password));
    	
    	AuthCache authCache = new BasicAuthCache();
    	
    	authCache.put(targetHost, robotAuth());
    	
    	context.setCredentialsProvider(credPro);
    	context.setAuthCache(authCache);
    	//use the GET verb
    	HttpGet httpGet = new HttpGet(url.getPath());
    	//execute and get the response
    	CloseableHttpResponse res = client.execute(targetHost, httpGet,context);
    	
    	
    	StringBuffer result = new StringBuffer();
    	
    	try {
        	BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        	String line = "";
        	while ((line = rd.readLine()) != null) {
        		result.append(line);
        	}
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			res.close();
		}
    	
    	DocumentBuilder builder;
    	Document doc;
    	String value = null;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    	InputSource src = new InputSource();
	    	src.setCharacterStream(new StringReader(result.toString()));

	    	doc = builder.parse(src);
	    	value = doc.getElementsByTagName("span").item(0).getTextContent();

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.close();
		/*if(log==true){
			System.out.println("initiateGetCoord "+value);
		}*/
    	return Integer.parseInt(value);
	}
	
	
	
	
	private String checkRobotIp(String ipId){
		switch(ipId){
			case "0":
				ipId= "192.168.125.1";
				break;
			case "1":
				ipId= "192.168.153.142";
				break;
			case "2":
				ipId = "localhost";
		}
		return ipId;
	}
	
	public String getCoord(String ipId) throws ClientProtocolException, IOException  {
		return "{\"orderId\":\""+initiateGetOrder("orderId", ipId, true)+"\","
				+ "\"productId\":\""+initiateGetOrder("productId", ipId, true)+"\","
				+ "\"quatity\":\""+initiateGetOrder("quatity", ipId, true)+"\","
				+ "\"timestamp\":"+System.currentTimeMillis()+"}";
	}
	
	public String getamountRead(String ipId) throws ClientProtocolException, IOException  {
		return "{\"amountRead\":\""+initiateGetOrder("amountRead", ipId, true)+"}";
	}
	
	public String sendCoord(ManualFcatoringModel coords) throws ClientProtocolException, IOException, AuthenticationException{
		
		initiateSendOrder("orderId", coords.getOrderId(), coords.getIpId(), false);
		initiateSendOrder("productId", coords.getProductId(), coords.getIpId(), false);
		initiateSendOrder("quatity", coords.getQuatity(), coords.getIpId(), false);
		return "The product order has been sent to ABB!";
	}
	
	public String checkCoords(ManualFcatoringModel coords) throws ClientProtocolException, IOException{
		if(initiateGetOrder("orderId",coords.getIpId(), false)==coords.getOrderId()&&
				initiateGetOrder("productId", coords.getIpId(), false)==coords.getProductId()&&
				initiateGetOrder("quatity", coords.getIpId(), false)==coords.getQuatity()){
			return "ABB robot has received product order succssfully!"; 
		}
		else{
			return "ABB robot hasn't received product order succssfully!";
		}
	}
	
	public boolean checkConn(String ipId) throws IOException{
		System.out.println("checkconn" + AbbRobotStatus+Virtual1Status+Virtual2Status);
		boolean robotReach = false;
		int status=0;
		switch(ipId){
			case "0":
				status = AbbRobotStatus;
				break;
			case "1":
				status = Virtual1Status;
				break;
			case "2":
				status = Virtual2Status;
				break;
		}
		
		if(status==0){
			InetAddress addr = InetAddress.getByName(checkRobotIp(ipId));
			robotReach = addr.isReachable(5000);
		}
		
    	System.out.println(ipId+" "+	robotReach);
    	return robotReach;
	}
	
	public void robotConn(String ipId, String action){
		System.out.println(action);
		int stt;
		if(action!="dis"){
			stt = 1;
		}
		else {
			stt = 0;
		}
		System.out.println(stt);
		switch(ipId){
			case "0":
				AbbRobotStatus = stt;
				break;
			case "1":
				Virtual1Status = stt;
				break;
			case "2":
				Virtual2Status = stt;
				break;
		}
		System.out.println("robotConn"+AbbRobotStatus+Virtual1Status+Virtual2Status);
	}
}
