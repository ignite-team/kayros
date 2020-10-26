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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.util.resource.Labels;

public class ExportUtils {

	private static final String workingTimePeriodsText = Labels.getLabel("timesheet.workingTimePeriods");
	private static final String yesText = Labels.getLabel("general.yes");
	private static final String noText = Labels.getLabel("general.no");

	private static final String fullPattern = "dd/MM/yyyy HH:mm:ss";
	private static final String pattern = "dd/MM/yyyy";
	private static final SimpleDateFormat fullFormater = new SimpleDateFormat(fullPattern);
	private static final SimpleDateFormat formater = new SimpleDateFormat(pattern);

	public static InputStream exportCSV(ArrayList<ArrayList<Object>> rows, ArrayList<String> headers) {

		try {

			ByteArrayInputStream instr;

			StringWriter outstr = new StringWriter();

			CSVPrinter printer = new CSVPrinter(outstr,
					CSVFormat.DEFAULT.withQuote('"').withQuoteMode(QuoteMode.ALL).withDelimiter(';').withHeader(headers.toArray(new String[headers.size()])));

			for (ArrayList<Object> row : rows) {

				Object[] array = row.toArray();

				for (int x = 0; x < array.length; x++) {

					if (array[x] instanceof Boolean) {

						if ((Boolean) array[x] == true) {

							array[x] = yesText;

						} else {

							array[x] = noText;

						}

					} else if (array[x] instanceof Date) {

						array[x] = fullFormater.format(array[x]);

					} else if (array[x] instanceof LocalDate) {

						array[x] = formater.format(Date.from(((LocalDate) array[x]).atStartOfDay(ZoneId.systemDefault()).toInstant()));

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

		try {

			Workbook workbook = new XSSFWorkbook();

			Sheet sheet = workbook.createSheet(workingTimePeriodsText);

			Row header = sheet.createRow(0);

			for (int x = 0; x < headers.size(); x++) {

				sheet.setColumnWidth(x, rows.size());
				Cell cell = header.createCell(x);
				cell.setCellValue(headers.get(x));

			}

			for (int y = 0; y < rows.size(); y++) {

				Row row = sheet.createRow(y + 1);

				for (int z = 0; z < rows.get(y).size(); z++) {

					Cell cell = row.createCell(z);

					if (rows.get(y).get(z) instanceof Boolean) {

						if ((Boolean) rows.get(y).get(z) == true) {

							cell.setCellValue(yesText);

						} else {

							cell.setCellValue(noText);

						}

					} else if (rows.get(y).get(z) instanceof Date) {

						cell.setCellValue(fullFormater.format(rows.get(y).get(z)));

					} else if (rows.get(y).get(z) instanceof LocalDate) {

						cell.setCellValue(formater.format(Date.from(((LocalDate) rows.get(y).get(z)).atStartOfDay(ZoneId.systemDefault()).toInstant())));

					} else {

						cell.setCellValue(rows.get(y).get(z) == null ? null : rows.get(y).get(z).toString());

					}

				}

			}

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			workbook.write(buffer);
			workbook.close();

			byte[] bytes = buffer.toByteArray();
			InputStream inputStream = new ByteArrayInputStream(bytes);

			buffer.close();

			return inputStream;

		} catch (IOException e) {

			return null;

		}

	}

}
