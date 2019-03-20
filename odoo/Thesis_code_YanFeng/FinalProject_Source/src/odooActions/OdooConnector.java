package odooActions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class OdooConnector {

	String endpoint2 = "";
	String pre = "";
	String execute = "";
	String login = "";
	String module = "";
	String filter = "";

	String reference = "";
	String id = "";
	String material_availity = "";
	String quantity = "";
	String state = "";
	String product = "";
	String deadline_start = "";

	String host = "";
	int port = 0;
	String tinydb = "";
	String password = "";
	int uid = 0;
	int cid_length = 0;
	String obS = "";
	double obO = 0;
	int obI = 0;

	String stateInfo1 = "";
	String stateInfo2 = "";
	String stateInfo3 = "";
	String stateInfo4 = "";

	String read = "";
	String write = "";
	String finished_quantity = "";
	String endpoint1 = "";
	String auth = "";

	XmlRpcClient objClient;
	XmlRpcClientConfigImpl objStartConfig;
	
	XmlRpcClientConfigImpl xmlrpcConfigAuthenticate;
	XmlRpcClient xmlrpcAuthenticate;
	ArrayList<Map<Object, Object>> records;
	List<Object> paramList;
	List<Object> condiState;
	Object[] cid;
	List<Object> argsList;
	Map<Object, Object> fieldMap;
	List<Object> fieldList;
	StringBuilder resultData;
	List<Object> configList;
	Map<Object, Object> paramMap;
	ArrayList<ManufacturingOrders> orders;

	private void initalProprerty() throws MalformedURLException {
		propertiesConfiguration property = new propertiesConfiguration();
		endpoint2 = property.getEndpoint2();
		pre = property.getPre();
		execute = property.getExecute();
		login = property.getLogin();
		module = property.getModule();
		filter = property.getFilter();
		endpoint1 = property.getEndpoint1();
		auth = property.getAuth();

		reference = property.getReference();
		id = property.getId();
		material_availity = property.getMaterial_availity();
		quantity = property.getQuantity();
		state = property.getState();
		product = property.getProduct();
		deadline_start = property.getDeadline_start();

		host = property.getHost();
		port = property.getPort();
		tinydb = property.getTinydb();
		password = property.getPassword();

		stateInfo1 = property.getStateInfo1();
		stateInfo2 = property.getStateInfo2();
		stateInfo3 = property.getStateInfo3();
		stateInfo4 = property.getStateInfo4();

		read = property.getRead();
		write = property.getWrite();

		objClient = new XmlRpcClient();
		objStartConfig = new XmlRpcClientConfigImpl();

		records = new ArrayList<Map<Object, Object>>();
		paramList = new ArrayList<Object>();
		condiState = new ArrayList<Object>();
		cid = new Object[] {};
		argsList = new ArrayList<Object>();
		fieldMap = new HashMap<Object, Object>();
		fieldList = new ArrayList<Object>();
		resultData = new StringBuilder();
		configList = new ArrayList<Object>();
		paramMap = new HashMap<Object, Object>();
		orders = new ArrayList<ManufacturingOrders>();

		xmlrpcAuthenticate = new XmlRpcClient();
		xmlrpcConfigAuthenticate = new XmlRpcClientConfigImpl();

		finished_quantity = property.getFinished_quantity();
		uid = this.pro_authenticate();
		objStartConfig.setServerURL(new URL(pre, host, port, endpoint2));
		objClient.setConfig(objStartConfig);
	}

	public Object[] getIdsFromManfactoring() throws MalformedURLException {

		// grt propertities from the property files
		initalProprerty();
		try {
		configList.clear();
		paramMap.clear();
		paramList.clear();

		configList.add(tinydb);
		configList.add(uid);
		configList.add(password);
		configList.add(module);
		configList.add(filter);

		paramList.add(condiState);
		configList.add(paramList);
		cid = (Object[]) objClient.execute(execute, configList);
		
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cid;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ManufacturingOrders> getDataFromManufactoring() throws MalformedURLException {

		try {

			cid = this.getIdsFromManfactoring();

			for (int i = 0; i < cid.length; i++) {
				argsList.add(cid[i]);
			}

			fieldList.add(reference);
			fieldList.add(id);
			fieldList.add(deadline_start);
			fieldList.add(product);
			fieldList.add(quantity);
			fieldList.add(material_availity);
			fieldList.add(state);
			fieldList.add(finished_quantity);
			fieldMap.put("fields", fieldList);

			paramList.clear();
			configList.clear();
			paramList.add(argsList);
			configList.add(tinydb);
			configList.add(uid);
			configList.add(password);
			configList.add(module);
			configList.add(read);
			configList.add(paramList);
			configList.add(fieldMap);

			cid_length = cid.length;

			for (int i = 0; i < cid_length; i++) {
				records.add((Map<Object, Object>) ((Object[]) objClient.
						execute(execute, configList))[i]);
			}

			for (int i = 0; i < cid_length; i++) {
				ManufacturingOrders order = new ManufacturingOrders();
				Map<Object, Object> record = null;
				Object[] obs = new Object[] {};
				Set<Object> keys = null;
				record = records.get(i);
				keys = record.keySet();

				for (Object key : keys) {

					String key_value = key.toString();
					if (key_value.equals(deadline_start)) {
						obS = (String) record.get(key);
						order.setDeadline_start(obS);

					} else if (key_value.equals(reference)) {
						obS = (String) record.get(key);
						order.setReferernce(obS);

					} else if (key_value.equals(product)) {
						if (record.get(key) instanceof Object[]) {
							obs = (Object[]) record.get(key);
							order.setProduct(obs[1].toString());
						}

					} else if (key_value.equals(quantity)) {
						obO = (Double) record.get(key);
						order.setQuantity(obO);

					} else if (key_value.equals(finished_quantity)) {
						obO = (Double) record.get(key);
						order.setFinished_quantity(obO);

					} else if (key_value.equals(material_availity)) {
						obS = (String) record.get(key);
						order.setMaterial_availity(obS);

					} else if (key_value.equals(state)) {
						obS = (String) record.get(key);
						order.setState(obS);

					} else if (key_value.equals(id)) {
						obI = (Integer) record.get(key);
						order.setId(obI);

					}
				}

				order.setAmount(order.getQuantity(), order.getFinished_quantity());

				orders.add(order);
			}

		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return orders;

	}

	public String updateToProgress(int idTochange) throws MalformedURLException {

		initalProprerty();
		try {
			List<Object> paramList = new ArrayList<Object>();

			configList.clear();
			paramMap.clear();
			paramList.clear();

			configList.add(tinydb);
			configList.add(uid);
			configList.add(password);
			configList.add(module);
			configList.add(write);

			paramMap.put(state, stateInfo2);

			List<Object> id = new ArrayList<Object>();
			id.add(idTochange);
			paramList.add(id);
			paramList.add(paramMap);
			configList.add(paramList);

			objClient.execute(execute, configList);

			resultData.append("Updated to 'In process' suceessfully!");
			return resultData.toString();

		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}

	public String updateToDone(int idTochange) throws MalformedURLException {

		initalProprerty();
		try {
			List<Object> paramList = new ArrayList<Object>();

			configList.clear();
			paramMap.clear();
			paramList.clear();

			configList.add(tinydb);
			configList.add(uid);
			configList.add(password);
			configList.add(module);
			configList.add(write);

			paramMap.put(state, stateInfo3);

			List<Object> id = new ArrayList<Object>();
			id.add(idTochange);
			paramList.add(id);
			paramList.add(paramMap);
			configList.add(paramList);

			objClient.execute(execute, configList);

			resultData.append("Updated to 'Done' suceessfully!");
			return resultData.toString();

		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}

	public int pro_authenticate() {

		xmlrpcConfigAuthenticate.setEnabledForExtensions(true);
		try {
			xmlrpcConfigAuthenticate.setServerURL(new URL(pre, host, port, endpoint1));
			xmlrpcAuthenticate.setConfig(xmlrpcConfigAuthenticate);

			int uid = (int) xmlrpcAuthenticate.execute(xmlrpcConfigAuthenticate, auth,
					Arrays.asList(tinydb, login, password, Collections.emptyMap()));
			return uid;
		} catch (XmlRpcException e) {
			System.out.println("Exception: " + e.getMessage());
			return -1;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return -2;
		}

	}

}
