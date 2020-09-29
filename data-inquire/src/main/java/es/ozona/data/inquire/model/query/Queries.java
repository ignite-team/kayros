package es.ozona.data.inquire.model.query;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Queries {

	private Queries() {

	}

	public static enum Syntax {
		INFIX("([\\w_]+)[\\:<>~!]", "[\\-\\+]?([\\w_]+)"); // , RSQL

		private String queryRegexp;
		private String sortRegexp;

		private Syntax(String queryRegexp, String sortRegexp) {
			this.queryRegexp = queryRegexp;
			this.sortRegexp = sortRegexp;
		}

		public String getQueryRegexp() {
			return queryRegexp;
		}

		public String getSortRegexp() {
			return sortRegexp;
		}
	}

	public static final QueryObject map(QueryObject queryObject, Map<String, String> propertyMappings, Syntax syntax) {
		final SimpleQueryObject qo = new SimpleQueryObject();
		qo.setQuery(map(queryObject.getQuery(), propertyMappings, syntax.getQueryRegexp()));
		qo.setSort(map(queryObject.getSort(), propertyMappings, syntax.getSortRegexp()));
		qo.setSize(queryObject.getSize());
		qo.setPage(queryObject.getPage());
		return qo;
	}

	private static String map(String text, Map<String, String> mappings, String regexp) {

		if (text == null) {
			return text;
		}
		
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(text);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group().replace(matcher.group(1), mappings.get(matcher.group(1))));
		}

		matcher.appendTail(sb);

		return sb.toString();

	}

}
