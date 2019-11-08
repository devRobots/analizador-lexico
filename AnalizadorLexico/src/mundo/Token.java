/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor: Leonardo A. Hern�ndez R. - Agosto 2008, sep 2013.
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
	final public static String OPERADORARITMETICO = "Operador aritm�tico";
	final public static String OPERADORASIGNACION = "Operador de asignaci�n";
	final public static String OPERADORRELACIONAL = "Operador relacional";
	final public static String OPERADORLOGICO = "Operador logico";
	final public static String IDENTIFICADORDECLASE = "Identificador de clase";
	final public static String IDENTIFICADORDEMETODO = "Identificador de m�todos";
	final public static String IDENTIFICADORDECONSTANTE = "Identificador de constantes";
	final public static String IDENTIFICADORDEVARIABLE = "Identificador de variable";
	final public static String TERMINAL = "Terminal";
	final public static String PALABRARESERVADA = "Palabra reservada";
	final public static String TIPODEDATO = "Tipo de dato";
	final public static String ASIGNACIONDESENTENCIAAPERTURA = "Asignaci�n de sentencia de apertura";
	final public static String ASIGNACIONDESENTENCIACIERRE = "Asignaci�n de sentencia de cierre";
	final public static String ASIGNACIONDEAGRUPACIONAPERTURA = "Asignaci�n de agrupaci�n de apertura";
	final public static String ASIGNACIONDEAGRUPACIONCIERRE = "Asignaci�n de agrupaci�n de cierre";
	final public static String COMENTARIODELINEA = "Comentario de l�nea";
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
	 * posici�n del siguiente lexema
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
	 *            - posici�n del siguiente token - laPosicionSiguiente > 0
	 */
	public Token(String elLexema, String elTipo, int elIndiceSiguiente) {
		lexema = elLexema;
		tipo = elTipo;
		indiceSiguiente = elIndiceSiguiente;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Entrega la informaci�n del token
	 * 
	 * @return Descripci�n del token
	 */
	public String darDescripcion() {
		return "Token: " + lexema + "     Tipo: " + tipo + "     �ndice del siguiente: " + indiceSiguiente;
	}

	/**
	 * M�todo que retorna el lexema del token
	 * 
	 * @return el lexema del token
	 */
	public String darLexema() {
		return lexema;
	}

	/**
	 * M�todo que retorna la posici�n del siguiente lexema
	 * 
	 * @return posici�n del siguiente token
	 */
	public int darIndiceSiguiente() {
		return indiceSiguiente;
	}

	/**
	 * M�todo que retorna el tipo del token
	 * 
	 * @return el tipo del token
	 */
	public String darTipo() {
		return tipo;
	}

}
