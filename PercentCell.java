package textExcel;

public class PercentCell extends RealCell{
	public PercentCell(String num) {
		super(num);
	}
	public String fullCellText() {
		return Double.toString(getDoubleValue() / 100);
	}
	public String abbreviatedCellText() {
		return String.format("%-10s", (int)getDoubleValue() + "%");
	}
}
