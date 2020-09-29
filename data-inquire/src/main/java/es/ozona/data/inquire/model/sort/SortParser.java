package es.ozona.data.inquire.model.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class SortParser {
	public static final String PATTERN = ",*([\\+|\\-]?[a-zA-Z_][a-zA-Z0-9_]+)";

	private SortParser() {
		// TODO Auto-generated constructor stub
	}

	public static final Sort parse(String text) {
		List<Order> fields = new ArrayList<Order>(0);

		Pattern patter = Pattern.compile(PATTERN);
		Matcher matcher = patter.matcher(text);

		while (matcher.find()) {
			final String field = matcher.group();

			if (field.matches("\\+[a-zA-Z_][a-zA-Z0-9_]+")) {
				fields.add(new Order(Direction.ASC, field.substring(1)));
			} else if (field.matches("\\-[a-zA-Z_][a-zA-Z0-9_]+")) {
				fields.add(new Order(Direction.DESC, field.substring(1)));
			} else {
				fields.add(new Order(Direction.ASC, field));
			}
		}

		return Sort.by(fields);
	}

}
