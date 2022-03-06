package Model;

import java.util.Hashtable;

/**
 * Container class storing a table of Model objects that are present in the system.
 */
public class Models {

    private Hashtable<String,Model> models;

    /**
     * Constructor of the class, initializes the Hash table that will store the Model objects.
     * The key of the Hashtable is a string that is the name of the Model, while the
     * value is the Model object itself.
     */
    public Models() {
        this.models = new Hashtable<String, Model>();
    }

    /**
     * Adding a new Model into the system.
     * @param id    The name of the Model to be stored.
     * @param model The Model object to be stored.
     */
    public void put(String id, Model model){
        models.put(id, model);
    }

    /**
     * Gets an existing Model from the system by its name.
     * @param id    Name of the Model to be retrieved.
     * @return      The retrieved Model object.
     */
    public Model get(String id){
        return models.get(id);
    }

}
