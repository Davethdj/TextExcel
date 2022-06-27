package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location{
	private String location;
    public SpreadsheetLocation(String str) {
    	location = str.toUpperCase();
    }
    public int getRow(){
        return Integer.parseInt(location.substring(1))-1;
    }
    @Override
    public int getCol(){
        return (int)(location.charAt(0)-65);
    }

}
