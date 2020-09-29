package es.ozona.micro.core.interfaces.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class MCModelMapper extends ModelMapper {

	public <E, D> Map<String, String> getMappings(Class<E> sourceType, Class<D> destinationType) {

		TypeMap<E, D> typeMap = this.getTypeMap(sourceType, destinationType);
		final Map<String, String> propertyMappings = new HashMap<String, String>(0);

		if (typeMap == null) {
			typeMap = this.createTypeMap(sourceType, destinationType);
		}

		typeMap.getMappings().stream().forEach(m -> {
			Pattern pattern = Pattern.compile("\\[([\\w\\.]+) -> ([\\w\\.]+)\\]");
			Matcher matcher = pattern.matcher(m.toString());
			if (matcher.find()) {
				propertyMappings.put(
						//TODO: revisar como eliminar el remplezo de _ por .
						matcher.group(2).substring(destinationType.getSimpleName().length() +1).replaceAll("\\.", "_"),
						matcher.group(1).substring(sourceType.getSimpleName().length()+1).replaceAll("\\.", "_"));
			}
		});

		return propertyMappings;

	}

}
