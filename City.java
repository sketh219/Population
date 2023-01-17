/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Saketh Korada 
 *	@since 1/12/22
 */
public class City implements Comparable<City> {
	
	// fields
	private String name;
	private String stateName;
	private String type;
	private int population;
	
	// constructor
	
	public City(String name, String stateName, String type, int population)
	{
		this.name = name;
		this.stateName = stateName;
		this.type = type;
		this.population = population;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	 public int compareTo(City other)
	 {
		 if(this.population - other.population != 0) return this.population - other.population;
		 else if(! this.stateName.equals(other.stateName)) return this.stateName.compareTo(other.stateName);
		 else return this.name.compareTo(other.name);
		 
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equal(City other)
	 {
		 if(this.name.equals(other.name) && this.stateName.equals(other.stateName))return true;
		 return false;
	}
	
	/**	Accessor methods */
	
	public String getName(){ return name;}
	public String getState() {return stateName;}
	public String getType() {return type;}
	public int getPop(){ return population;}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, name, type,
						population);
	}
}
