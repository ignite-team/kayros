package es.ozona.kayros.webapp.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.junit.jupiter.api.Test;

public class ExportUtilsTest {

    ArrayList<ArrayList<Object>> rows = new ArrayList<ArrayList<Object>>();
    ArrayList<String> headers = new ArrayList<String>();

	@Test
	public void givenStreamWith4Rows1HeaderWhenExportCSVThenBufferedReaderReadLineEquals5() {
		
		ArrayList<Object> row1 = new ArrayList<Object>();
		ArrayList<Object> row2 = new ArrayList<Object>();
		ArrayList<Object> row3 = new ArrayList<Object>();
		ArrayList<Object> row4 = new ArrayList<Object>();

		row1.add("R1-1");
		row1.add("R1-2");
		row1.add("R1-3");
		row1.add("R1-4");
				
		row2.add("R2-1");
		row2.add("R2-2");
		row2.add("R2-3");
		row2.add("R2-4");
		
		row3.add("R3-1");
		row3.add("R3-2");
		row3.add("R3-3");
		row3.add("R3-4");
		
		row4.add("R4-1");
		row4.add("R4-2");
		row4.add("R4-3");
		row4.add("R4-4");

		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		rows.add(row4);
		
        headers.add("Header 1");
        headers.add("Header 2");
        headers.add("Header 3");
        headers.add("Header 4");

		try {	
		
			Reader reader = ExportUtils.exportCSV(CSVFormat.DEFAULT, rows, headers);

			BufferedReader br = new BufferedReader(reader);
			
			int counter = 0;
	
			while (br.readLine() != null) {
					
				counter += 1;
					
			}
			
			assertThat(counter).isEqualTo(5);

		} catch (IOException e) {
		
			e.printStackTrace();

		}
		
	}
	
	@Test
	public void givenHeaderWith4ColumnsWhenExportCSVThenHeadersLengthEquals4() {

		ArrayList<Object> row1 = new ArrayList<Object>();
		ArrayList<Object> row2 = new ArrayList<Object>();
		ArrayList<Object> row3 = new ArrayList<Object>();
		ArrayList<Object> row4 = new ArrayList<Object>();

		row1.add("R1-1");
		row1.add("R1-2");
		row1.add("R1-3");
		row1.add("R1-4");
				
		row2.add("R2-1");
		row2.add("R2-2");
		row2.add("R2-3");
		row2.add("R2-4");
		
		row3.add("R3-1");
		row3.add("R3-2");
		row3.add("R3-3");
		row3.add("R3-4");
		
		row4.add("R4-1");
		row4.add("R4-2");
		row4.add("R4-3");
		row4.add("R4-4");

		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		rows.add(row4);
		
        headers.add("Header 1");
        headers.add("Header 2");
        headers.add("Header 3");
        headers.add("Header 4");

		try {
			
			Reader reader = ExportUtils.exportCSV(CSVFormat.DEFAULT, rows, headers);

			BufferedReader br = new BufferedReader(reader);
			
			String line = br.readLine();
			
			String[] columns = line.split(",");
	
			assertThat(columns.length).isEqualTo(4);

		} catch (IOException e) {
			
			e.printStackTrace();			
			
		}

	}
	
	@Test
	public void givenHeaderWith4ColumnsWhenExportCSVthenHeadersAreSorted() {

		ArrayList<Object> row1 = new ArrayList<Object>();
		ArrayList<Object> row2 = new ArrayList<Object>();
		ArrayList<Object> row3 = new ArrayList<Object>();
		ArrayList<Object> row4 = new ArrayList<Object>();

		row1.add("R1-1");
		row1.add("R1-2");
		row1.add("R1-3");
		row1.add("R1-4");
				
		row2.add("R2-1");
		row2.add("R2-2");
		row2.add("R2-3");
		row2.add("R2-4");
		
		row3.add("R3-1");
		row3.add("R3-2");
		row3.add("R3-3");
		row3.add("R3-4");
		
		row4.add("R4-1");
		row4.add("R4-2");
		row4.add("R4-3");
		row4.add("R4-4");

		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		rows.add(row4);
		
        headers.add("Header 1");
        headers.add("Header 2");
        headers.add("Header 3");
        headers.add("Header 4");

		try {
			
			Reader reader = ExportUtils.exportCSV(CSVFormat.DEFAULT, rows, headers);

			BufferedReader br = new BufferedReader(reader);
			
			String line = br.readLine();
			
			String[] columns = line.split(",");
							
			assertThat(columns[0]).isEqualTo("Header 1");
			assertThat(columns[1]).isEqualTo("Header 2");
			assertThat(columns[2]).isEqualTo("Header 3");
			assertThat(columns[3]).isEqualTo("Header 4");

		} catch (IOException e) {
			
			e.printStackTrace();

		}	
		
	}
	
}
