package no.hvl.dat110.ac.restservice;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.post;
import static spark.Spark.delete;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static AccessLog accesslog = null;
	static AccessCode accesscode = null;
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		// objects for data stored in the service
		
		accesslog = new AccessLog();
		accesscode  = new AccessCode();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		// for basic testing purposes
		get("/accessdevice/hello", (req, res) -> {
			
		 	Gson gson = new Gson();
		 	
		 	return gson.toJson("IoT Access Control Device");
		});
		
		// TODO: implement the routes required for the access control service
		// as per the HTTP/REST operations describined in the project description
		post("/accessdevice/log/", (req, res) ->{
			Gson gson = new Gson();
			//System.out.println("--------------- prøv post log--------------");
			String str=req.body();
			//System.out.println(str+"/*/*/*/*/*/*//*/*/*/");
			//HashMap msg=gson.fromJson(req.body(), HashMap.class);//generell, kan hente message ut fra mapen
			//System.out.println(msg);
			AccessMessage am= gson.fromJson(str, AccessMessage.class);//her må req.body være på samme form som klassen
			//System.out.println(am.toString());
			//System.out.println(am.toString());
			int idAm=accesslog.add(am.getMessage());
			//System.out.println(idAm);
			//System.out.println(accesslog.get(idAm));
		 	return gson.toJson(accesslog.get(idAm));
		});
		get("/accessdevice/log/", (req, res) ->{
			Gson gson = new Gson();
			//System.out.println(accesslog.toJson() );
			//System.out.println("--------------- prøv log inn--------------");
		 	return	accesslog.toJson() ;//gson.toJson(accesslog.get(accesscode.getAccesscode()[0]));
		});
		get("/accessdevice/log/:id", (req, res) ->{
			Gson gson = new Gson();
			//System.out.println("--------------- prøv log id--------------");
			//System.out.println(req.body());
			String idTxt = req.params(":id");
			//System.out.println(id);
			//HashMap msg=gson.fromJson(req.body(), HashMap.class);//generell, kan hente message ut fra mapen
			//System.out.println(msg);
			//AccessMessage am= gson.fromJson(req.body(), AccessMessage.class);//her må req.body være på samme form som klassen
			//System.out.println(am.toString());
				int id= Integer.parseInt(idTxt);
				gson.toJson(accesslog.get(id));
		 	return gson.toJson(accesslog.get(id));
		});
		put("/accessdevice/code/", (req, res) ->{
			Gson gson = new Gson();
			//System.out.println("--------------- prøv put code--------------");
			accesscode=gson.fromJson(req.body(), AccessCode.class);
			System.out.println(accesscode);
		 	return req.body();
		});
		get("/accessdevice/code/", (req, res) ->{
			Gson gson = new Gson();
			//System.out.println("--------------- prøv get code--------------");
			String jsonString= null;
			if(accesscode!=null) {
				jsonString = gson.toJson(accesscode);
			}
		 	return jsonString;//gson.toJson(accesslog.get(accesscode.getAccesscode()[0]));
		});
		delete("/accessdevice/log/", (req, res) ->{
			//System.out.println("--------------- prøv delete inn--------------");
			accesslog.clear();
		 	return accesslog.toJson();//gson.toJson(accesslog.get(accesscode.getAccesscode()[0]));
		});
    }
    
}
