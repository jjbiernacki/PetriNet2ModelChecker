package pl.edu.agh.petrinet2modelchecker.generator.nuxmv;

/**
 * Stałe znakowe programu
 * @author abiernacka, jbiernacki
 *
 */
public class StrRes {

	public static final String DEFAULT_MODULE_NAME = "main";
	public static final String DEFAULT_STATE_NAME = "s";
	public static final String MODULE = "MODULE";
	public static final String VAR = "VAR";
    public static final String IVAR = "IVAR";
	public static final String ASSIGN = "ASSIGN";
	public static final String BOOLEAN = "boolean";
	public static final String INTEGER = "0..";
	public static final String INIT = "init";
	public static final String CASE = "case";
	public static final String ESAC = "esac";
	public static final String NEXT = "next";
	
	
	public enum Boolean {
		FALSE("FALSE"), TRUE("TRUE");
		
		private final String name;
		
		private Boolean(final String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	};
}
