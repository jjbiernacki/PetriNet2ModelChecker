package pl.edu.agh.petrinet2modelchecker.model.pt;

import java.util.Random;

/**
 * Miejsce w sieciach Petriego
 * @author abiernacka, jbiernacki
 *
 */
public class PTPlace implements Comparable<PTPlace> {

	private final String name;
	
	public PTPlace(final String name) {
		this.name = name;
	}

	/**
	 * Zwraca nazwę miejsca
	 * @return Nazwa miejsca
	 */
	public String getName() {
		return name;
	}
	
	private boolean hasWrongCharacters() {
		for(int i=0; i<name.length(); i++) {
			if((name.charAt(i) >= 'a' && name.charAt(i) <= 'z') || (name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') || (name.charAt(i) >= '0' && name.charAt(i) <= '9')
					|| name.charAt(i) == '_' || name.charAt(i) == '$' || name.charAt(i) == '-' || name.charAt(i) == '#') {
				if(i == 0 && (name.charAt(i) == '$' || name.charAt(i) == '-' || name.charAt(i) == '#' || (name.charAt(i) >= '0' && name.charAt(i) <= '9'))) {
					return true;
				} else {
					continue;
				}
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * Zwraca nazwę, która jest poprawna do zapisu w NuXMV
	 * @return
	 */
	public String getValidNuXMVName() {
		if(isKeyWord()) {
			return "_" + name;
		}
		if(name == null || name.length() == 0 || hasWrongCharacters()) {
			Random random = new Random(30);
			String name = "place";
			name += String.valueOf(random.nextInt(10));
			name += String.valueOf(random.nextInt(10));
			name += String.valueOf(random.nextInt(10));
			return name;
		}
		return name;
	}

	@Override
	public int compareTo(PTPlace other) {
		return name.compareTo(other.getName());
	}
	
	@Override
	public boolean equals(Object other) {
		return name.equals(((PTPlace)other).getName());
	}
	
	@Override
	public String toString() {
		return "Place: " + name;
	}
	
	private boolean isKeyWord() {
		String [] keywords = {"MODULE", "DEFINE", "MDEFINE", "CONSTANTS", "VAR", "IVAR", "FROZENVAR", "INIT", "TRANS", "INVAR", "SPEC", "CTLSPEC", "LTLSPEC", "PSLSPEC", "COMPUTE", "NAME", "INVARSPEC", "FAIRNESS", "JUSTICE", "COMPASSION", "ISA", "ASSIGN", "CONSTRAINT", "SIMPWFF", "CTLWFF",
				"LTLWFF", "PSLWFF", "COMPWFF", "IN", "MIN", "MAX", "MIRROR", "PRED",
				"PREDICATES", "process", "array", "of", "boolean", "integer", "real", "word", "word1", "bool",
				"signed", "unsigned", "extend", "resize", "sizeof", "uwconst", "swconst", "EX", "AX", "EF", "AF",
				"EG", "AG", "E", "F", "O", "G", "H", "X", "Y", "Z", "A", "U", "S", "V", "T", "BU", "EBF", "ABF", "EBG", "ABG", "case",
				"esac", "mod", "next", "init", "union", "in", "xor", "xnor", "self", "TRUE", "FALSE", "count"};
		for(String keyword: keywords) {
			if(keyword.toUpperCase().equals(name.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
}
