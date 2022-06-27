package textExcel;

public class RealCell implements Cell{
	private String num;
	public RealCell(String num) {
		this.num = num;
	}
	public String fullCellText() {
		return num; // Write that other code
	}
	public String abbreviatedCellText() {
		if(num.length()<10) {
			return String.format("%-10s", getDoubleValue());
		}
		else {
			return String.format("%10.10s", getDoubleValue());
		}
	}
	public double getDoubleValue() {
		if(num.indexOf(".")>0) {
			return Double.parseDouble(num);
		}
		else {
			return Integer.parseInt(num) * 1.0;
		}
	}
}
