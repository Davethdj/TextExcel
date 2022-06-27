 package textExcel;

public class EmptyCell implements Cell {
	private String value;
	public EmptyCell() {
		value = "";
	}
	public String abbreviatedCellText() {
		return "          ";// text for spreadsheet cell display, must be exactly length 10
	}
	public String fullCellText() {
		return value;// text for individual cell inspection, not truncated or padded
	}
}