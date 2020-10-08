package es.ozona.kayros.webapp.utils;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class ExportUtils {

	public static PipedReader exportCSV(CSVFormat fileType, ArrayList<ArrayList<Object>> rows, ArrayList<String> headers) {

		try {

			PipedWriter outstr = new PipedWriter();
			PipedReader instr = new PipedReader(outstr, 15000);

			CSVPrinter printer = new CSVPrinter(outstr, fileType.withHeader(headers.toArray(new String[headers.size()])));

			for (ArrayList<Object> row : rows) {

				Object[] array = row.toArray();

				printer.printRecord(array);

			}

			printer.flush();
			printer.close();
			outstr.close();

			return instr;

		} catch (IOException e) {

			return null;

		}

	}

}
