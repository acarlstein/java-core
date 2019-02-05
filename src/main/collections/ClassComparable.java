package collections;

import java.util.UUID;

public class ClassComparable implements Comparable<ClassComparable>
{
	String identifier;
	int value = 0;

	public ClassComparable(int value){
		this.identifier = UUID.randomUUID().toString();
		this.value = value;
	}

	public ClassComparable(int value, String identifier){
		this.identifier = identifier;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof ClassComparable)){
			return false;
		}
		return (((ClassComparable) obj).identifier == this.identifier);
	}

	/**
	 *
	 * @param other
	 * @return
	 * this < other = -1
	 * this == other = 0
	 * this > other = +1
	 */
	public int compareTo(ClassComparable other){
		return this.value - other.value;
	}

	public String toString(){
		return new StringBuffer()
			.append("[")
			.append(identifier)
			.append(":")
			.append(value)
			.append("]")
			.toString();
	}

}
