package odooActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propertiesConfiguration {

	private String host = "";
	private int port = 0;
	private String tinydb = "";
	private String login = "";
	private String password = "";
	private String endpoint1="";
	private String endpoint2="";
	private String auth="";
	private String execute="";
	private String pre="";
	private String module="";
	private String filter="";
	private String reference="";
	private String deadline_start="";
	private String product="";
	private String quantity="";
	private String material_availity="";
	private String state="";
	private String id="";
	private String stateInfo1="";
	private String stateInfo2="";
	private String stateInfo3="";
	private String stateInfo4="";
	private String read="";
	private String write="";
	private String finished_quantity="";
	
	
	
	public String getFinished_quantity() {
		return finished_quantity;
	}

	public propertiesConfiguration() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out

			
			this.tinydb = prop.getProperty("database");
			this.login = prop.getProperty("dbuser");
			this.password = prop.getProperty("dbpassword");
			this.host = prop.getProperty("host");
			this.port = Integer.parseInt(prop.getProperty("port"));
			this.endpoint1 = prop.getProperty("endpoint1");
			this.endpoint2 = prop.getProperty("endpoint2");
			this.execute = prop.getProperty("execute");
			this.pre=prop.getProperty("pre");
			this.auth=prop.getProperty("auth");
			
			this.module = prop.getProperty("module");
			this.filter = prop.getProperty("filter");
			this.reference = prop.getProperty("reference");
			this.deadline_start=prop.getProperty("deadline_start");
			this.product=prop.getProperty("product");
			
			this.quantity = prop.getProperty("quantity");
			this.material_availity = prop.getProperty("material_availity");
			this.state=prop.getProperty("state");
			this.id=prop.getProperty("id");
			
			this.stateInfo1 = prop.getProperty("stateInfo1");
			this.stateInfo2 = prop.getProperty("stateInfo2");
			this.stateInfo3=prop.getProperty("stateInfo3");
			this.stateInfo4=prop.getProperty("stateInfo4");
			
			this.read=prop.getProperty("read");
			this.write=prop.getProperty("write");
			this.finished_quantity=prop.getProperty("finished_quantity");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public String getRead() {
		return read;
	}

	public String getWrite() {
		return write;
	}

	public String getStateInfo1() {
		return stateInfo1;
	}

	public String getStateInfo2() {
		return stateInfo2;
	}

	public String getStateInfo3() {
		return stateInfo3;
	}

	public String getStateInfo4() {
		return stateInfo4;
	}

	public String getHost() {
		return host;
	}

	public String getModule() {
		return module;
	}

	public String getFilter() {
		return filter;
	}

	public String getReference() {
		return reference;
	}

	public String getDeadline_start() {
		return deadline_start;
	}

	public String getProduct() {
		return product;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getMaterial_availity() {
		return material_availity;
	}

	public String getState() {
		return state;
	}

	public String getId() {
		return id;
	}

	public int getPort() {
		return port;
	}

	public String getTinydb() {
		return tinydb;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	public String getEndpoint1() {
		return endpoint1;
	}

	public String getEndpoint2() {
		return endpoint2;
	}

	public String getExecute() {
		return execute;
	}

	public String getPre() {
		return pre;
	}
	public String getAuth() {
		return auth;
	}



	

}
