package textExcel;
import java.util.Scanner;
// Update this file with your own code.
public class Spreadsheet implements Grid{
	private Cell[][] grid;
	public Spreadsheet() {
		//sets the grid to the empty grid which is arr1
		grid = blankGrid();
	}
	public String processCommand(String command){
		//plan on making methods depending on different cases
		String[] comm = command.split(" ", 3);
		if(command.equals("")) {
			return "";
		}
		//commands for specific cells \/ checks to see whether the comm0 is a location 
		//since those are the types of commands for specfic cells
		else if(comm[0].length() == 2 || comm[0].length() == 3) {
			//creates a cell location object
			if(comm.length>1) {
				return setCellContent(comm);
			}
			//returning a cell value
			else {
				Location result = new SpreadsheetLocation(comm[0]);
				Cell cell = getCell(result);
				return cell.fullCellText();
			}
		}
		//make this else if later and add if (upperCaseCommand.indexOf("clear") > 0)
		else {
			//clear a specific cell
			if(comm.length > 1) {
				String cell = comm[1];
				Location result = new SpreadsheetLocation(cell);
				grid[result.getRow()][result.getCol()]= new EmptyCell();
				return getGridText();
			}
			//clear the whole grid
			else {			
				//sets empty grid to the 2D Cell Array
				this.grid = blankGrid();
				return getGridText();
			}
		}
	}
	public String setCellContent(String[] parts) {
		Location location = new SpreadsheetLocation(parts[0]);
		String content = parts[2];
		if(content.indexOf("\"") >= 0){
			Cell cell = new TextCell(content);	
			grid[location.getRow()][location.getCol()]= cell;
			return getGridText();
		}
		else if(content.indexOf("%") >= 0) {
			Cell cell = new PercentCell(content.substring(0,content.length()-1));	
			grid[location.getRow()][location.getCol()]= cell;
			return getGridText();
		}
		else if(content.indexOf("(") >= 0) {
			Cell cell = new FormulaCell(content, this);	
			grid[location.getRow()][location.getCol()]= cell;
			return getGridText();
		}
		else if(content.indexOf(".") >= 0) {
			Cell cell = new RealCell(content);	
			grid[location.getRow()][location.getCol()]= cell;
			return getGridText();
		}
		else {
			Cell cell = new ValueCell(content);	
			grid[location.getRow()][location.getCol()]= cell;
			return getGridText();
		}
		
	}
	public String getGridText(){
		String theGrid = "";
		//first row
		for(char i = 'A'; i <='L'; i++ ) {
			if(i == 65) {
				//first section of row
				theGrid += "   |" + i;
			}
			else {
				//rest of the row
				theGrid += "         |" + i;
			}
		}
		//caps the end of the row
		theGrid += "         |";
		//the remaining rows
		for(int i = 1; i<grid.length+1; i++) {
			//first section of each row(the number portion)
			theGrid += String.format("\n" + "%-3s|",(i));
			//the actual text from each cell
			for(int j= 0; j<grid[0].length; j++) {
				//formattedText makes sure that each cell is 10 spaces long
				String formattedText = grid[i-1][j].abbreviatedCellText();
				theGrid +=  String.format("%-10.10s", formattedText);
				//caps the end of the row
				if(j<grid[0].length) {
					theGrid += "|";
				}
			}
		}
		theGrid += "\n";
		return theGrid;
	}
	public int getRows(){
		return 20;
	}
	public int getCols(){
		return 12;
	}
	public Cell[][] blankGrid(){
		//makes a new grid that is empty
		Cell[][] blank = new Cell[20][12];
		for(int i=0; i<blank.length; i++) {
			for(int j=0; j<blank[1].length; j++) {
				blank[i][j] = new EmptyCell();
			}
		}
		return blank;
	}
	public Cell getCell(Location loc){
		return grid[loc.getRow()][loc.getCol()];
	}
	public String getCellContent(Location coord) {
		Cell cell = getCell(coord);
		if(cell instanceof RealCell){
			return cell.abbreviatedCellText();
		}
		return cell.fullCellText();
	}
	public static void main(String args[]) {
		System.out.print(Double.parseDouble("5.0        "));
	}
}