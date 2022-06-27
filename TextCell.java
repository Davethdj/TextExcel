package textExcel;

public class TextCell implements Cell{
	private String value;
	public TextCell(String str) {
		value = str;
	}
	public String fullCellText() {
		return value;
	}
	public String abbreviatedCellText() {
		if(value.length() > 13) {
			return value.substring(1,11);
		}
		else {
			String noQuotes = value.substring(1,value.length()-1);
			return String.format("%-10s", noQuotes);
		}
	}
}