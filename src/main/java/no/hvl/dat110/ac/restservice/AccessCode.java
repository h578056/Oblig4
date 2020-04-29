package no.hvl.dat110.ac.restservice;

import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AccessCode {

	private int[] accesscode = {1,2}; // default access code
	
	public AccessCode() {
		
	}

	public int[] getAccesscode() {
		return accesscode;
	}

	public void setAccesscode(int[] accesscode) {
		this.accesscode = accesscode;
	}

	@Override
	public String toString() {
		return "AccessCode [accesscode=" + Arrays.toString(accesscode) + "]";
	}

}
