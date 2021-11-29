package es.ozona.kayros.webapp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.util.resource.Labels;

public class ExportUtils {
	private static final Logger LOG = LoggerFactory.getLogger(ExportUtils.class);
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

		try {

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			workbook.write(buffer);
			workbook.close();

			byte[] bytes = buffer.toByteArray();
			InputStream inputStream = new ByteArrayInputStream(bytes);

			return inputStream;
			
		} catch (IOException e) {
			LOG.error("No se ha podido generar el fichero de exportación a Excel.", e);
		}

		return null;

	}

}
