package es.ozona.kayros.webapp.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExportUtilsTest {

	private ArrayList<ArrayList<Object>> rows;
	private ArrayList<String> headers;
	private ArrayList<Object> row1;
	private ArrayList<Object> row2;
	private ArrayList<Object> row3;
	private ArrayList<Object> row4;
	private String splitter;

	@BeforeEach
	public void init() {

		splitter = ",";

		rows = new ArrayList<ArrayList<Object>>();
		headers = new ArrayList<String>();
		row1 = new ArrayList<Object>();
		row2 = new ArrayList<Object>();
		row3 = new ArrayList<Object>();
		row4 = new ArrayList<Object>();

		row1.add("R1-1");
		row1.add("R1-2");
		row1.add("R1-3");
		row1.add(null);

		row2.add("R2-1");
		row2.add("R2-2");
		row2.add("R2-3");
		row2.add(null);

		row3.add("R3-1");
		row3.add("R3-2");
		row3.add("R3-3");
		row3.add(null);

		row4.add("R4-1");
		row4.add("R4-2");
		row4.add("R4-3");
		row4.add(null);

		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		rows.add(row4);

		headers.add("Header 1");
		headers.add("Header 2");
		headers.add("Header 3");
		headers.add(null);

	}

	@Test
	public void given4HeadersAnd4RowsWith4Columns_whenExportUtilsExportCSV_thenReturn5Lines() {

		try {

			InputStream input = ExportUtils.exportCSV(CSVFormat.DEFAULT, rows, headers);

			BufferedReader br = new BufferedReader(new InputStreamReader(input));

			int counter = 0;

			while (br.readLine() != null) {

				counter += 1;

			}

			assertThat(counter).isEqualTo(rows.size() + 1);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@Test
	public void given4HeadersAnd4RowsWith4Columns_whenExportUtilsExportXLSX_thenReturn5Lines() {

		try {

			InputStream input = ExportUtils.exportXLSX(rows, headers);

			Workbook workbook = new XSSFWorkbook(input);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			int counter = 0;

			while (iterator.hasNext()) {

				counter += 1;
				iterator.next();

			}

			assertThat(counter).isEqualTo(rows.size() + 1);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
