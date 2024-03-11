import java.io.Serializable;

//Enum to set object type
enum Type{
	ERROR,
	STANDARD
}
public class Message implements Serializable {
	protected Type type;
    protected String data;
    protected static int id;

    //Default Constructor
    public Message(){
        this.type = null;
        this.data = null;
        id++;
    }
    
    //Override Constructor
    public Message(Type type,String data){
    	this.type = type;
    	this.data = data;
    	id++;
    }

    public Type getType(){
    	return type;
    }

    public String getData(){
    	return data;
    }
    public int getId() {
    	return id;
    }
}
