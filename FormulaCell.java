package textExcel;

import java.util.ArrayList;

public class FormulaCell extends RealCell {
	
	private String formula;
	private Spreadsheet sheet;
	
	public FormulaCell(String formula, Spreadsheet sheet) {
		super(formula);
		this.formula = formula.substring(2, formula.length()-2);
		this.sheet = sheet;
	}
	
	public String abbreviatedCellText() {
		return String.format("%-10.10s", getDoubleValue());
	}
	
	public double getDoubleValue() {
		String[] formulaParts = formula.split(" ");
		
		if(formulaParts.length == 1) {
			return tryCatch(formula);
		}
		
		else {
			String formulaPart1 = formulaParts[0].toUpperCase();
			String formulaPart2 = formulaParts[1].toUpperCase();
			
			if(formulaPart1.equals("SUM") || formulaPart1.equals("AVG")) {
				
				double sum = 0, counter = 0;
				String[] startEnd = formulaPart2.split("-");
				
				Location startLoc = new SpreadsheetLocation(startEnd[0]);
				Location endLoc = new SpreadsheetLocation(startEnd[1]);
				
				for(int i = startLoc.getRow(); i<=endLoc.getRow(); i++) {
					
					for(int j = startLoc.getCol(); j<=endLoc.getCol(); j++) {
							
						sum += getCellValue(i, j);
						counter++;
					}
				}
				if(formulaPart1.equals("AVG")) {
					return sum/counter;
				}
				return sum;
			}
			else {
			//changed my mind im going forward
				double formulaResult = tryCatch(formulaPart1);
				for(int i =1; i<formulaParts.length-1; i+=2) {
					
					String sign = formulaParts[i];
					double val = tryCatch(formulaParts[i+1]);
					
					if(sign.equals("+")) {
						formulaResult += val;
					}
					
					else if(sign.equals("-")) {
						formulaResult -= val;
					}
					
					else if(sign.equals("*")) {
						formulaResult *= val;
					}
					
					else{
						formulaResult /= val;
					}	
				}
				return formulaResult;
			}
		}
	}
	public double tryCatch(String part) {
		double val = 0;
		try {
			val = Double.parseDouble(part);
		}
		catch(Exception e) {
			Location loc = new SpreadsheetLocation(part);
			String strVal = sheet.getCellContent(loc);
			val = Double.parseDouble(strVal);
		}
		return val;
	}
	public double getCellValue(int row, int col) {
		String letterPart = String.valueOf((char)(col+65)).toUpperCase();
		String numberPart = String.valueOf(row+1);
		
		Location currentLoc = new SpreadsheetLocation(letterPart + numberPart);
		
		return Double.parseDouble(sheet.getCellContent(currentLoc));
	}
}