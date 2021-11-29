package es.ozona.kayros.webapp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.util.resource.Labels;

public class ExportUtils {

	private static final String WORKING_TIMEPERIODS_TEXT = Labels.getLabel("timesheet.workingTimePeriods");
	private static final String YES_TEST = Labels.getLabel("general.yes");
	private static final String NO_TEXT = Labels.getLabel("general.no");

	private static final String FULL_PATTERN = "dd/MM/yyyy HH:mm:ss";
	private static final String PATTERN = "dd/MM/yyyy";

	private ExportUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static InputStream exportCSV(ArrayList<ArrayList<Object>> rows, ArrayList<String> headers) {

		try {

			SimpleDateFormat fullFormater = new SimpleDateFormat(FULL_PATTERN);
			SimpleDateFormat formater = new SimpleDateFormat(PATTERN);

			ByteArrayInputStream instr;

			StringWriter outstr = new StringWriter();

			CSVPrinter printer = new CSVPrinter(outstr, CSVFormat.DEFAULT.withQuote('"').withQuoteMode(QuoteMode.ALL)
					.withDelimiter(';').withHeader(headers.toArray(new String[headers.size()])));

			for (ArrayList<Object> row : rows) {

				Object[] array = row.toArray();

				for (int x = 0; x < array.length; x++) {

					if (array[x] instanceof Boolean) {

						if ((Boolean) array[x] == true) {

							array[x] = YES_TEST;

						} else {

							array[x] = NO_TEXT;

						}

					} else if (array[x] instanceof Date) {

						array[x] = fullFormater.format(array[x]);

					} else if (array[x] instanceof LocalDate) {

						array[x] = formater.format(
								Date.from(((LocalDate) array[x]).atStartOfDay(ZoneId.systemDefault()).toInstant()));

					}

				}

				printer.printRecord(array);

			}

			printer.flush();
			printer.close();

			instr = new ByteArrayInputStream(outstr.toString().getBytes("cp1252"));

			outstr.close();

			return instr;

		} catch (IOException e) {

			return null;

		}

	}

	public static InputStream exportXLSX(ArrayList<ArrayList<Object>> rows, ArrayList<String> headers) {
		SimpleDateFormat fullFormater = new SimpleDateFormat(FULL_PATTERN);
		SimpleDateFormat formater = new SimpleDateFormat(PATTERN);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(WORKING_TIMEPERIODS_TEXT);

		int rowNum = 0;
		Row headerRow = sheet.createRow(rowNum);
		int colNum = 0;
		for (String header : headers) {			
			Cell cell = headerRow.createCell(colNum++);
			cell.setCellValue(header);
		}

		rowNum++;

		for (List<Object> rowData : rows) {
			Row row = sheet.createRow(rowNum++);
			colNum = 0;
			for (Object data : rowData) {
				Cell cell = row.createCell(colNum);

				if (data instanceof Boolean) {

					if ((Boolean) data == true) {
						cell.setCellValue(YES_TEST);
					} else {
						cell.setCellValue(NO_TEXT);
					}
				} else if (data instanceof Date) {
					cell.setCellValue(fullFormater.format(data));
				} else if (data instanceof LocalDate) {
					cell.setCellValue(formater
							.format(Date.from(((LocalDate) data).atStartOfDay(ZoneId.systemDefault()).toInstant())));
				} else {
					cell.setCellValue(data != null? data.toString():"");
				}
				sheet.autoSizeColumn(colNum++);
			}
			
		}

		/*
		 * Object[][] datatypes = { {"Datatype", "Type", "Size(in bytes)"}, {"int",
		 * "Primitive", 2}, {"float", "Primitive", 4}, {"double", "Primitive", 8},
		 * {"char", "Primitive", 1}, {"String", "Non-Primitive", "No fixed size"} };
		 * 
		 * int rowNum = 0; System.out.println("Creating excel");
		 * 
		 * for (Object[] datatype : datatypes) { Row row = sheet.createRow(rowNum++);
		 * int colNum = 0; for (Object field : datatype) { Cell cell =
		 * row.createCell(colNum++); if (field instanceof String) {
		 * cell.setCellValue((String) field); } else if (field instanceof Integer) {
		 * cell.setCellValue((Integer) field); } } }
		 */
		try

		{

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			workbook.write(buffer);
			workbook.close();

			byte[] bytes = buffer.toByteArray();
			InputStream inputStream = new ByteArrayInputStream(bytes);

			return inputStream;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done");

		return null;

		/*
		 * Workbook workbook = new XSSFWorkbook();
		 * 
		 * try {
		 * 
		 * 
		 * 
		 * Sheet sheet = workbook.createSheet(WORKING_TIMEPERIODS_TEXT);
		 * 
		 * Row header = sheet.createRow(0);
		 * 
		 * for (int x = 0; x < headers.size(); x++) {
		 * 
		 * sheet.setColumnWidth(x, rows.size()); Cell cell = header.createCell(x);
		 * cell.setCellValue(headers.get(x));
		 * 
		 * }
		 * 
		 * for (int y = 0; y < rows.size(); y++) {
		 * 
		 * Row row = sheet.createRow(y + 1);
		 * 
		 * for (int z = 0; z < rows.get(y).size(); z++) {
		 * 
		 * Cell cell = row.createCell(z);
		 * 
		 * if (rows.get(y).get(z) instanceof Boolean) {
		 * 
		 * if ((Boolean) rows.get(y).get(z) == true) {
		 * 
		 * cell.setCellValue(YES_TEST);
		 * 
		 * } else {
		 * 
		 * cell.setCellValue(NO_TEXT);
		 * 
		 * }
		 * 
		 * } else if (rows.get(y).get(z) instanceof Date) {
		 * 
		 * cell.setCellValue(fullFormater.format(rows.get(y).get(z)));
		 * 
		 * } else if (rows.get(y).get(z) instanceof LocalDate) {
		 * 
		 * cell.setCellValue(formater.format(Date.from(((LocalDate)
		 * rows.get(y).get(z)).atStartOfDay(ZoneId.systemDefault()).toInstant())));
		 * 
		 * } else {
		 * 
		 * cell.setCellValue(rows.get(y).get(z) == null ? null :
		 * rows.get(y).get(z).toString());
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		 * 
		 * workbook.write(new FileOutputStream(new
		 * File("c:\\proxectos\\registro.xls"))); workbook.close();
		 * 
		 * 
		 * 
		 * buffer.flush(); buffer.close();
		 * 
		 * byte[] bytes = buffer.toByteArray(); InputStream inputStream = new
		 * ByteArrayInputStream(bytes);
		 * 
		 * buffer.close();
		 * 
		 * return inputStream;
		 * 
		 * } catch (IOException e) {
		 * 
		 * return null;
		 * 
		 * } finally {
		 * 
		 * try {
		 * 
		 * workbook.close();
		 * 
		 * } catch (IOException e) {
		 * 
		 * return null;
		 * 
		 * }
		 * 
		 * }
		 */
	}

}
