package no.hvl.dat110.ac.restservice;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AccessLog {
	
	// atomic integer used to obtain identifiers for each access entry
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// TODO: add an access entry to the log for the provided message and return assigned id
	public int add(String message) {
		int id = 0;
		if(log!=null) {
			id= cid.getAndIncrement();
		}
		AccessEntry ae= new AccessEntry(id , message); 
		log.put(id, ae);
		return id;
	}
		
	// TODO: retrieve a specific access entry from the log
	public AccessEntry get(int id) {
		AccessEntry ae=null;
		if(log!=null) {
			ae= log.get(id);
		}
		return ae;
		
	}
	
	// TODO: clear the access entry log
	public void clear() {
		log.clear();
	}
	
	// TODO: return JSON representation of the access log
	public String toJson () {
		String jsonString= null;
    	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    	if(!log.isEmpty()) {
    		 jsonString = gson.toJson(log);
    	}else {
    		jsonString="{\"Message\" : \"Error log is empty\"}";
    	}
    	
    	 return jsonString;
    }
}
