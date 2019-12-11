/** This class serves as the model class for the abstracted data with getters, setters, constructors and toStrig() function. 
 * 
 * @author NP
 *
 */
public class President {
	
	// private data members
	private String yearBegin;
	private String name;
	private String yearEnd;
	
	// public getters and setters
	public String getYearBegin() {
		return yearBegin;
	}
	public void setYearBegin(String yearBegin) {
		this.yearBegin = yearBegin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYearEnd() {
		return yearEnd;
	}
	public void setYearEnd(String yearEnd) {
		this.yearEnd = yearEnd;
	}
	
   // default constructor
	public President() {
		yearBegin = "";
		name = "";
		yearEnd="";
	}
	
	// Public non-default constructor for arrayList
	public President(String yearBegin, String name, String yearEnd) {
		
		setYearBegin(yearBegin);
		setName(name);
		setYearEnd(yearEnd);
	}
	
	// toString function to print the final ArrayList in a formated way. 
	public String toString() {
		
		String result = String.format("%-40s %-25s %-15s ", name, yearBegin, yearEnd);
		
		return  result;
	}
}
