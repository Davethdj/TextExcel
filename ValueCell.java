package textExcel;

public class ValueCell extends RealCell {
	private double dub;
	public ValueCell(String num) {
		super(num);
		this.dub = Double.parseDouble(num);
	}
	public double getDoubleValue(){
		return dub;
	}
}	
