/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindï¿½o (Armenia - Colombia)
 * Programa de Ingenierï¿½a de Sistemas y Computaciï¿½n
 *
 * Asignatura: Teorï¿½a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor: Leonardo A. Hernï¿½ndez R. - Agosto 2008, sep 2013.
 * Autor: 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

/**
 * Clase que modela un token
 */

public class Token {
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	/**
	 * Constantes para modelar los posibles tipos de token que se van a analizar
	 */
	final public static String ENTERO = "Entero";
	final public static String REAL = "Real";
	final public static String CARACTER = "Caracter";
	final public static String CADENA = "Cadena";
	final public static String BOOLEANO = "Booleano";
	final public static String OPERADORARITMETICO = "Operador aritmético";
	final public static String OPERADORASIGNACION = "Operador de asignación";
	final public static String OPERADORRELACIONAL = "Operador relacional";
	final public static String OPERADORLOGICO = "Operador logico";
	final public static String IDENTIFICADORDECLASE = "Identificador de clase";
	final public static String IDENTIFICADORDEMETODO = "Identificador de métodos";
	final public static String IDENTIFICADORDECONSTANTE = "Identificador de constantes";
	final public static String IDENTIFICADORDEVARIABLE = "Identificador de variable";
	final public static String TERMINAL = "Terminal";
	final public static String PALABRARESERVADA = "Palabra reservada";
	final public static String TIPODEDATO = "Tipo de dato";
	final public static String ASIGNACIONDESENTENCIAAPERTURA = "Asignación de sentencia de apertura";
	final public static String ASIGNACIONDESENTENCIACIERRE = "Asignación de sentencia de cierre";
	final public static String ASIGNACIONDEAGRUPACIONAPERTURA = "Asignación de agrupación de apertura";
	final public static String ASIGNACIONDEAGRUPACIONCIERRE = "Asignación de agrupación de cierre";
	final public static String COMENTARIODELINEA = "Comentario de línea";
	final public static String COMENTARIODEBLOQUE = "Comentario de bloque";
	final public static String NORECONOCIDO = "No reconocido";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	/**
	 * Lexema
	 */
	private String lexema;

	/**
	 * tipo
	 */
	private String tipo;

	/**
	 * posiciï¿½n del siguiente lexema
	 */
	private int indiceSiguiente;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	/**
	 * Constructor de un token
	 * 
	 * @param elLexema
	 *            - cadena - laCadena != null
	 * @param elTipo
	 *            - tipo del token - elTipo != null
	 * @param elIndiceSiguiente
	 *            - posiciï¿½n del siguiente token - laPosicionSiguiente > 0
	 */
	public Token(String elLexema, String elTipo, int elIndiceSiguiente) {
		lexema = elLexema;
		tipo = elTipo;
		indiceSiguiente = elIndiceSiguiente;
	}

	// -----------------------------------------------------------------
	// Mï¿½todos
	// -----------------------------------------------------------------

	/**
	 * Entrega la informaciï¿½n del token
	 * 
	 * @return Descripciï¿½n del token
	 */
	public String darDescripcion() {
		return "Token: " + lexema + "     Tipo: " + tipo + "     ï¿½ndice del siguiente: " + indiceSiguiente;
	}

	/**
	 * Mï¿½todo que retorna el lexema del token
	 * 
	 * @return el lexema del token
	 */
	public String darLexema() {
		return lexema;
	}

	/**
	 * Mï¿½todo que retorna la posiciï¿½n del siguiente lexema
	 * 
	 * @return posiciï¿½n del siguiente token
	 */
	public int darIndiceSiguiente() {
		return indiceSiguiente;
	}

	/**
	 * Mï¿½todo que retorna el tipo del token
	 * 
	 * @return el tipo del token
	 */
	public String darTipo() {
		return tipo;
	}

}
