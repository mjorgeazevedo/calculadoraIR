package com.mjorgeazevedo.incomeTaxCalculator.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mjorgeazevedo.incomeTaxCalculator.entity.Asset;

public class AssetFactory {
	private static Map<String, Asset> assetMap = new HashMap<String, Asset>();
	
	public static Asset createAsset(String id) {
		if (assetMap.containsKey(id)) {
			return assetMap.get(id);
		} else {
			return new Asset(id, "", "");
		}
	}
	
	static {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader("src/main/resources/assets_info.csv");
			br = new BufferedReader(fr);
			String line;
			
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(";");
				
				if (fields[1].contains("/")) {
					String[] fields2 = fields[1].split("/");
					
					for (String s : fields2) {
						assetMap.put(s, new Asset(s, fields[0], fields[2]));
					}
				} else {
					assetMap.put(fields[1], new Asset(fields[1], fields[0], fields[2]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {}
			}
			
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {}
			}
		}
	}
}
