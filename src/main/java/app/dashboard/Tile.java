package app.dashboard;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

import app.asset.*;

public class Tile implements Serializable {
    String name;
    int id;
    List<DashboardAsset> assetList;

    // Default constructor
    // Sets the default id and name attribute
    // Instantiates the assetList
    public Tile() {
        name = "Default.";
        id = 0;
        assetList = new ArrayList<DashboardAsset>();

    }
    
    // Overloaded constructor
    // Sets the id and name attribute to the input parameter
    // Instantiates the assetList
    public Tile(int tileID, String tileName) {
        id = tileID;
        name = tileName;
        assetList = new ArrayList<DashboardAsset>();
    }

    // Returns a List of Asset objects contained in this Tile
    public List<DashboardAsset> getAssets() {
        return assetList;
    }

    // Returns a List of asset names contained in this tile
    public List<String> getAssetNames() {
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < assetList.size(); i++) {
            names.add(assetList.get(i).getName());
        }
        return names;
    }

    // Returns the Asset object with specified id
    public DashboardAsset getAsset(int id) {
        for (int i = 0; i < assetList.size(); i++) {
            if (id == assetList.get(i).getId())
                return assetList.get(i);
        }
        return null; // should be an error if not found
    }

    // Returns the name attribute
    public String getName() {
        return name;
    }

    // Returns the id attribute
    public int getId() {
        return id;
    }

    // Creates a new Asset object to contained in this tile
    // Generically defined, because each asset is created differently
    public void addAsset(int id, String name) {

    }

    // Creates a new AssetImage object
    // Adds the AssetImage to assetList
    public void addAssetImage(int id, String name, String path, int[] size, int[] position) {
        AssetImage image = new AssetImage(id, name, path, size, position);
        assetList.add((DashboardAsset) image);
    }

    // Creates a new AssetLink object
    // Adds the AssetLink to assetList
    public void addAssetLink(int id, String name, String path) {
	AssetLink link = new AssetLink(id, name, path);
	assetList.add((DashboardAsset) link);
    }
    
    // Creates a new AssetList object
    // Adds the AssetList to assetList
    public void addAssetList(int id, String name, String type) {
	// determine list type
	char listType;
	if ( type.equals("ordered") )
	    listType = 'o';
	else // type.equals("unordered")
	    listType = 'u';
	
	AssetList list = new AssetList(id, name, listType);
	assetList.add((DashboardAsset) list);
	
    }

    // creates a new AssetNote object
    // ads the AssetNote to assetList
    public void addAssetNote(int id, String name) {
	AssetNote note = new AssetNote(id, name);
	assetList.add((DashboardAsset) note);
    }
    
    public String display() {
        // return assetList.toString();

        String retStr = "";
        retStr += "<div name='" + this.id + "' style='border:1px solid black'>";
        for (int i = 0; i < assetList.size(); i++) {
            retStr += assetList.get(i).display();
        }
        retStr += "</div>";
        retStr += "<br>";
        return retStr;
    }
}
