/**
 * The DataParser class will have function that will parse the data extracted from the URL and produces an arrayList of objects. 
 * @author NP
 *
 */
import java.util.ArrayList;

public class DataParser {
/**
 * The function convertTotalHtmlToPrez takes in the raw data from the url in html format and converts it into ArrayList of objects. 
 * @param totalHtml
 * @return
 */
	public static ArrayList<President> convertTotalHtmlToPrez(String totalHtml)
	{
		// declare ArrayList of president objects.
		ArrayList<President> listOfPrezs = new ArrayList<President>();
		
	   String[] presidentString= totalHtml.split("<tr>"); // split the data with <tr> 
	   
	   int prezNum = 0;    // initialize the index of the object to 0. 
	   
	   for (String str: presidentString ) // iterate through the array
	   {
		   /* based on inspect element from the website , following if statement parses the data for accurate representation by getting rid of unwanted 
		    * angular brackets and html notations based on trail-error process.
		   */
		   if (prezNum != 0 && prezNum != 1&& prezNum < 47) 
			   
		   {
			   // split the element string with <td> to get the table data
			   String[] elemntString= str.split("<td>");  
			   
			   int currentcol = 1;  // initialize current column to 1. 
			   President prez = new President(); 
			   
			   // following for loop handles the outOfBoundException
			   
			   for(String elm: elemntString ) 
			   {
				  // index of -1 represented of bound exception. 
				  int index = -1;  // index is used for the president and bIndex in used for Start term and end terms. 
				  int bIndex = -1;
				  index = elm.indexOf("\">") + "\">".length();
				  
				  // following index of exception variables are used to get rid of unnecessary leading and trailing elements attached to main data. 
				  
				   int firstIndexOFException = elm.indexOf("</a></td>");
				   int secondIndexOFException = elm.indexOf(" </td>");
				   int thirdIndexOFException = elm.indexOf("	</td></tr>");
				   int fourthIndexOFException = elm.indexOf("</td>");
				   
				   String outputString = ""; // initialize output string as empty string. 
				   
				   if(elm.length() > index && index != -1) {
					   // filtering the values for columns of start(column 2) and end term (column 3). 
					   bIndex = elm.indexOf("<b>  ") + "<b>  ".length();
					   
					   // following if-else statements for bIndex tries to eradicate the unwanted elements from columns 2 and 3 such as <tr>, which were not removed earlier.
					   if (bIndex != -1 && elm.indexOf("a href") == -1) 
						   
					   {
						   if(secondIndexOFException != -1) {
							   outputString = elm.substring(bIndex,secondIndexOFException);
						   }
						   else if (thirdIndexOFException != -1) {
							   
							   outputString = elm.substring(bIndex,thirdIndexOFException);
						   }
						   else if (fourthIndexOFException != -1){
							   
							   outputString = elm.substring(bIndex,fourthIndexOFException);
						   }
						  
						   else {
							   outputString = elm.substring(bIndex);
						   } 
						   
					   }
					   else {
						   // this will get rid of unwanted elements from column 1 i.e. president list. 
						   if(firstIndexOFException != -1) {
							   outputString = elm.substring(index,firstIndexOFException); 
						   }
						   
						   else {
							   outputString = elm.substring(index);
						   }
					   }
					   
					   String outputCopyNoSpaces = outputString;
					   // this will remove unwanted spaces from the output string 
					   if(outputCopyNoSpaces.replace(" ", "").length() > 2) 
						   
					   if(currentcol == 1) // if column no. is 1, get the name of president from the Arraylist. 
					   {
					   prez.setName(outputString);
					   }
					   if(currentcol == 2) // if column no. is 2. get the begin term of the corresponding president. 
					   {
						   prez.setYearBegin(outputString);
					   }
					   if(currentcol == 3) // if column no. is 3. get the End term of the corresponding president.
					   {
						   prez.setYearEnd(outputString);
					   }
					   currentcol++; // increment the column count
				   }
			   }
			   listOfPrezs.add(prez); 
		   }
		   prezNum++;   // increment the index of elements in President ArrayList
	   }
	   
		return listOfPrezs; // return final ArrayList of the president
	}

}
