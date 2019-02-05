package collections;


/**
 * Check src\test\collections\SimpleCollectionsTest.java
 *
 * All objects in Java inherit from java.lang.Object and
 * Object provides default implementations of:
 *  equals(), hashCode(), toString(), clone(), finalize(),
 *  notify(), notifyAll(), and wait()
 *  Url: https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html
 */
public class SimpleCollections
{
	public static final String TAG = "SimpleCollections";

	private int identifier;
	public SimpleCollections(){
		identifier = 0;
	}

	public SimpleCollections(int identifier){
		this.identifier = identifier;
	}

	@Override
	public String toString(){
		return TAG;
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof SimpleCollections)){
			return false;
		}

		return ((SimpleCollections) obj).identifier == this.identifier;
	}

	public int getIdentifier()
	{
		return identifier;
	}
}
