/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor: Leonardo A. Hern�ndez R. - Agosto 2008 - Marzo 2009
 * Autor:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

import java.util.ArrayList;

/**
 * Clase que modela un analizador l�xico
 * 
 * @version 2.0 Modificada
 * @author Luisa Fernanda Cotte S�nchez
 * @author Yesid Shair Rosas Toro
 */

public class AnalizadorLexico {

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Extrae los tokens de un código fuente dado
	 * 
	 * @param cod
	 *            - código al cual se le van a extraer los tokens - !=null
	 * @return vector con los tokens
	 */
	public ArrayList<Token> extraerTokens(String cod) {
		Token token;
		ArrayList<Token> vectorTokens = new ArrayList<Token>();

		// El primer token se extrae a partir de posici�n cero
		int i = 0;

		// Ciclo para extraer todos los tokens
		while (i < cod.length()) {
			// Extrae el token de la posici�n i
			token = extraerSiguienteToken(cod, i);
			vectorTokens.add(token);
			i = token.darIndiceSiguiente();
		}
		return vectorTokens;
	}

	/**
	 * Extrae el token de la cadena cod a partir de la posici�n i, bas�ndose en el
	 * Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a extraer un token - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a extraer el token - i>=0
	 * @return token que se extrajo de la cadena
	 */
	public Token extraerSiguienteToken(String cod, int i) {
		Token token;

		// Intenta extraer un entero
		token = extraerEntero(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un real
		token = extraerReal(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un caracter
		token = extraerCaracter(cod, i);
		if (token != null)
			return token;

		// Intenta extraer una cadena
		token = extraerCadena(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un booleano
		token = extraerBooleano(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador aditivo
		token = extraerOperadorAritmetico(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador de asignaci�n
		token = extraerOperadorAsignacion(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador relacional
		token = extraerOperadorRelacional(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador logico
		token = extraerOperadorLogico(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un identificador
		token = extraerIdentificador(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un terminal
		token = extraerTerminal(cod, i);
		if (token != null)
			return token;

		// Intenta extraer una palabra reservada
		token = extraerPalabraReservada(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un tipo de dato
		token = extraerTipoDeDato(cod, i);
		if (token != null)
			return token;

		// Intenta extraer una asignaci�n de sentencia
		token = extraerAsignacionDeSentencia(cod, i);
		if (token != null)
			return token;

		// Intenta extraer una asignaci�n de agrupaci�n
		token = extraerAsignacionDeAgrupacion(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un comentario de l�nea
		token = extraerComentarioDeLinea(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un comentario de bloque
		token = extraerComentarioDeBloque(cod, i);
		if (token != null)
			return token;

		// Intenta extraer una secuencia de escape
		token = extraerSecuenciaDeEscape(cod, i);
		if (token != null)
			return token;

		// Extrae un token no reconocido
		token = extraerNoReconocido(cod, i);
		return token;
	}

	/**
	 * Intenta extraer un entero de la cadena cod a partir de la posici�n i,
	 * bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer un entero -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer un entero
	 *            - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posici�n dada no es un
	 *         entero. El Token se compone de el lexema, el tipo y la posici�n del
	 *         siguiente lexema.
	 */

	// Este m�todo usa el m�todo substring(), que se explica a continuaci�n:
	// x.substring( i, j ) retorna una nueva cadena que es una subcadena de la
	// cadena x.
	// La subcadena comienza en la posici�n i y
	// se extiende hasta el car�cter en la posici�n j-1.
	// Ejemplo: "universidad".substring(3,6) retorna "ver",

	public Token extraerEntero(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == '#') {
			j = i + 1;

			if (j < cod.length() && esDigito(cod.charAt(j))) {

				do {
					j++;
				} while (j < cod.length() && esDigito(cod.charAt(j)));

				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.ENTERO, j);
				return token;
			}
		}

		return null;
	}

	/**
	 * Intenta extraer un n�mero Real de la cadena cod a partir de la posici�n i,
	 * bas�ndose en el Aut��mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el operador aditivo -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el
	 *            numero real - 0<=i<codigo.length()
	 * @return el token numero real o NULL, si el token en la posici�n dada no es
	 *         un n�mero real.El Token se compone del lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	public Token extraerReal(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == '$') {

			j = i + 1;
			if (j < cod.length() && esDigito(cod.charAt(j))) {
				do {
					j++;
				} while (j < cod.length() && esDigito(cod.charAt(j)));

				if (j < cod.length() && cod.charAt(j) == '.') {

					j++;

					if (j < cod.length() && esDigito(cod.charAt(j))) {

						do {
							j++;
						} while (j < cod.length() && esDigito(cod.charAt(j)));

						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.REAL, j);
						return token;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Intenta extraer un caracter de la cadena cod a partir de la posici�n i,
	 * bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el caracter -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el
	 *            caracter - 0<=i<codigo.length()
	 * @return el token caracter o NULL, si el token en la posici�n dada no es un
	 *         caracter.El Token se compone de el lexema, el tipo y la posici�n del
	 *         siguiente lexema.
	 */
	public Token extraerCaracter(String cod, int i) {

		int j;

		if (cod.charAt(i) == '{') {
			j = i + 1;
			if (j < cod.length() && esSimbolo(cod.charAt(j))) {
				j++;
				if (j < cod.length() && cod.charAt(j) == '}') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.CARACTER, j);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer una cadena de la cadena cod a partir de la posici�n i,
	 * bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer la cadena -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer la cadena
	 *            - 0<=i<codigo.length()
	 * @return el token cadena o NULL, si el token en la posici�n dada no es una
	 *         cadena.El Token se compone de el lexema, el tipo y la posici�n del
	 *         siguiente lexema.
	 */
	public Token extraerCadena(String cod, int i) {

		int j;
		String lex;

		if (cod.charAt(i) == '|') {
			j = i + 1;

			// if (j < cod.length() && cod.charAt(j) == '|') {
			//
			// j++;
			// lex = cod.substring(i, j);
			// Token token = new Token(lex, Token.CADENA, j);
			// return token;
			// }

			if (j < cod.length() && esSimbolo(cod.charAt(j))) {

				do {
					j++;
				} while (j < cod.length() && cod.charAt(j) != '|' && esSimbolo(cod.charAt(j)));

				if (j < cod.length() && cod.charAt(j) == '|') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.CADENA, j);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un booleano de la cadena cod a partir de la posici�n i,
	 * bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el booleano -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el
	 *            booleano - 0<=i<codigo.length()
	 * @return el token booleano o NULL, si el token en la posici�n dada no es un
	 *         booleano.El Token se compone de el lexema, el tipo y la posici�n del
	 *         siguiente lexema.
	 */
	public Token extraerBooleano(String cod, int i) {

		if (cod.charAt(i) == '1' || cod.charAt(i) == 0) {
			String lex = cod.substring(i, i + 1);
			Token token = new Token(lex, Token.BOOLEANO, i + 1);
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un operador aditivo de la cadena cod a partir de la posici�n
	 * i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el operador aditivo -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el
	 *            operador aditivo - 0<=i<codigo.length()
	 * @return el token operador aditivo o NULL, si el token en la posici�n dada no
	 *         es un operador aditivo.El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	public Token extraerOperadorAritmetico(String cod, int i) {

		String lex;
		int j;

		if (cod.charAt(i) == '+') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '+') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORARITMETICO, j);
				return token;
			}
		}
		if (cod.charAt(i) == '-') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '-') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORARITMETICO, j);
				return token;
			}
		}
		if (cod.charAt(i) == '*') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '*') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORARITMETICO, j);
				return token;
			}
		}
		if (cod.charAt(i) == '/') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '/') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORARITMETICO, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador de asignaci�n de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el operador de
	 *            asignaci�n - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de asingaci�n - 0<=i<codigo.length()
	 * @return el token operador asignaci�n o NULL, si el token en la posici�n dada
	 *         no es un operador de asignaci�n. El Token se compone de el lexema, el
	 *         tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorAsignacion(String cod, int i) {

		if (cod.charAt(i) == '=') {
			int j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '|') {

				j++;
				String lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORASIGNACION, j);
				return token;

			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador realcional de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el operador
	 *            relacional - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el
	 *            operador relacional - 0<=i<codigo.length()
	 * @return el token operador relacional o NULL, si el token en la posici�n dada
	 *         no es un operador relacional.El Token se compone de el lexema, el
	 *         tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorRelacional(String cod, int i) {

		String lex;
		int j;

		if (cod.charAt(i) == '>') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '>') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
			if (j < cod.length() && cod.charAt(j) == '|') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
		}
		if (cod.charAt(i) == '<') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '<') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
			if (j < cod.length() && cod.charAt(j) == '|') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
		}
		if (cod.charAt(i) == '~') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '|') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
		}
		if (cod.charAt(i) == '|') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '|') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
		}

		return null;
	}

	/**
	 * Intenta extraer un operador de asignaci�n de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el operador de
	 *            asignaci�n - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de asingaci�n - 0<=i<codigo.length()
	 * @return el token operador asignaci�n o NULL, si el token en la posici�n dada
	 *         no es un operador de asignaci�n. El Token se compone de el lexema, el
	 *         tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorLogico(String cod, int i) {

		int j;
		String lex;

		if (cod.charAt(i) == '~') {

			lex = cod.substring(i, i + 1);
			Token token = new Token(lex, Token.OPERADORLOGICO, i + 1);
			return token;
		}
		if (cod.charAt(i) == 'o') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == 'r') {

				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORLOGICO, j);
				return token;

			}
		}
		if (cod.charAt(i) == 'a') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == 'n') {

				j++;

				if (j < cod.length() && cod.charAt(j) == 'd') {

					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADORLOGICO, j);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un identificador de la cadena cod a partir de la posici�n i,
	 * bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer un
	 *            identificador - 0<=indice<codigo.length()
	 * @return el token identificaror o NULL, si el token en la posici�n dada no es
	 *         un identificador. El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */

	public Token extraerIdentificador(String cod, int i) {

		int j;
		String lex;

		if (cod.charAt(i) == '@') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '@') {
				j++;

				if (j < cod.length() && esLetraMayuscula(cod.charAt(j))) {

					do {
						j++;
					} while (j < cod.length() && esLetra(cod.charAt(j)));

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.IDENTIFICADORDECLASE, j);
					return token;

				}
				if (j < cod.length() && esLetraMinuscula(cod.charAt(j))) {

					do {
						j++;
					} while (j < cod.length() && esLetra(cod.charAt(j)));

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.IDENTIFICADORDEMETODO, j);
					return token;

				}
			}
			if (j < cod.length() && esLetraMayuscula(cod.charAt(j))) {

				do {
					j++;
				} while (j < cod.length() && esLetraMayuscula(cod.charAt(j)));

				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.IDENTIFICADORDECONSTANTE, j);
				return token;

			}
			if (j < cod.length() && esLetraMinuscula(cod.charAt(j))) {

				do {
					j++;
				} while (j < cod.length() && (esDigito(cod.charAt(j)) || esLetraMinuscula(cod.charAt(j))));

				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.IDENTIFICADORDEVARIABLE, j);
				return token;

			}
		}
		return null;
	}

	/**
	 * Se defini� como terminal el salto de l�nea (\n)
	 */
	public Token extraerTerminal(String cod, int i) {

		String lex;
		int j;

		if (cod.charAt(i) == '\n') {
			j = i + 1;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.IDENTIFICADORDECLASE, j);
			return token;
		}

		return null;
	}

	/**
	 * Intenta extraer una palabra reservada de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer una palabra reservada
	 *            - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer una
	 *            palabra reservada - 0<=indice<codigo.length()
	 * @return el token palabra reservada o NULL, si el token en la posici�n dada no
	 *         es una palabra reservada. El Token se compone de el lexema, el tipo y
	 *         la posici�n del siguiente lexema.
	 */
	public Token extraerPalabraReservada(String cod, int i) {

		String lex;
		int j;

		// palabra reservada app (main)
		if (cod.charAt(i) == 'a') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'p') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'p') {

					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.PALABRARESERVADA, j);
					return token;
				}
			}
		}

		// Palabra reservada none (void)
		if (cod.charAt(i) == 'n') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'o') {
				j++;
				if (j < cod.charAt(j) && cod.charAt(i) == 'n') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'o') {

						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRARESERVADA, j);
						return token;
					}
				}
			}
		}

		// Palabra reservada mine (private)
		if (cod.charAt(i) == 'm') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'i') {
				j++;
				if (j < cod.charAt(j) && cod.charAt(i) == 'n') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'e') {

						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRARESERVADA, j);
						return token;
					}
				}
			}
		}

		// Palabra reservada ours (public)
		if (cod.charAt(i) == 'o') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'u') {
				j++;
				if (j < cod.charAt(j) && cod.charAt(i) == 'r') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 's') {

						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRARESERVADA, j);
						return token;
					}
				}
			}
		}

		// Palabra reservada prototype (class)
		if (cod.charAt(i) == 'p') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'r') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'o') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 't') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'o') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 't') {
								j++;
								if (j < cod.length() && cod.charAt(j) == 'y') {
									j++;
									if (j < cod.length() && cod.charAt(j) == 'p') {
										j++;
										if (j < cod.length() && cod.charAt(j) == 'e') {

											j++;
											lex = cod.substring(i, j);
											Token token = new Token(lex, Token.PALABRARESERVADA, j);
											return token;

										}
									}
								}
							}
						}
					}
				}
			}
		}

		// Palabra reservada insert (import)
		if (cod.charAt(i) == 'i') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'n') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 's') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'e') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'r') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 't') {

								j++;
								lex = cod.substring(i, j);
								Token token = new Token(lex, Token.PALABRARESERVADA, j);
								return token;
							}
						}
					}
				}
			}
		}

		// Palabra reservada box (package)
		if (cod.charAt(i) == 'b') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'o') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'x') {

					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.PALABRARESERVADA, j);
					return token;
				}
			}
		}

		// Palabra reservada constant (final)
		if (cod.charAt(i) == 'c') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'o') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'n') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 's') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 't') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'a') {
								j++;
								if (j < cod.length() && cod.charAt(j) == 'n') {
									j++;
									if (j < cod.length() && cod.charAt(j) == 't') {

										j++;
										lex = cod.substring(i, j);
										Token token = new Token(lex, Token.PALABRARESERVADA, j);
										return token;
									}
								}
							}
						}
					}
				}
			}
		}

		// Palabra reservada back (return)
		if (cod.charAt(i) == 'b') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'a') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'c') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'k') {

						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRARESERVADA, j);
						return token;
					}
				}
			}
		}

		// Palabra reservada stop (break)
		if (cod.charAt(i) == 's') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 't') {
				j++;
				if (j < cod.charAt(j) && cod.charAt(i) == 'o') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'p') {

						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRARESERVADA, j);
						return token;
					}
				}
			}
		}

		// Palabra reservada it (this)
		if (cod.charAt(i) == 't') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'h') {
				j++;
				if (j < cod.charAt(j) && cod.charAt(i) == 'i') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 's') {

						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRARESERVADA, j);
						return token;
					}
				}
			}
		}

		// Palabra reservada decision (if)
		if (cod.charAt(i) == 'd') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'e') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'c') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 's') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'i') {
								j++;
								if (j < cod.length() && cod.charAt(j) == 'o') {
									j++;
									if (j < cod.length() && cod.charAt(j) == 'n') {

										j++;
										lex = cod.substring(i, j);
										Token token = new Token(lex, Token.PALABRARESERVADA, j);
										return token;
									}
								}
							}
						}
					}
				}
			}
		}

		// Palabra reservada other (else/default)
		if (cod.charAt(i) == 'o') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 't') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'h') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'e') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'r') {

							j++;
							lex = cod.substring(i, j);
							Token token = new Token(lex, Token.PALABRARESERVADA, j);
							return token;
						}
					}
				}
			}
		}

		// Palabra reservada iterator (for)
		if (cod.charAt(i) == 'i') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 't') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'e') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'r') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'a') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 't') {
								j++;
								if (j < cod.length() && cod.charAt(j) == 'o') {
									j++;
									if (j < cod.length() && cod.charAt(j) == 'r') {

										j++;
										lex = cod.substring(i, j);
										Token token = new Token(lex, Token.PALABRARESERVADA, j);
										return token;
									}
								}
							}
						}
					}
				}
			}
		}

		// Palabra reservada during (while)
		if (cod.charAt(i) == 'd') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'u') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'r') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'n') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'g') {

								j++;
								lex = cod.substring(i, j);
								Token token = new Token(lex, Token.PALABRARESERVADA, j);
								return token;
							}
						}
					}
				}
			}
		}

		// Palabra reservada option (switch)
		if (cod.charAt(i) == 'o') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'p') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 't') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'o') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'n') {

								j++;
								lex = cod.substring(i, j);
								Token token = new Token(lex, Token.PALABRARESERVADA, j);
								return token;
							}
						}
					}
				}
			}
		}

		// Palabra reservada next (continue)
		if (cod.charAt(i) == 'n') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'e') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'x') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 't') {

						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRARESERVADA, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un tipo de dato de la cadena cod a partir de la posici�n i,
	 * bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer el tipo de dato -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer el tipo de
	 *            dato - 0<=i<codigo.length()
	 * @return el token tipo de dato o NULL, si el token en la posici�n dada no es
	 *         un operador relacional.El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	public Token extraerTipoDeDato(String cod, int i) {

		String lex;
		int j;

		// Tipo de dato (int) number
		if (cod.charAt(i) == 'n') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'u') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'm') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'b') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'e') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'r') {

								j++;
								lex = cod.substring(i, j);
								Token token = new Token(lex, Token.TIPODEDATO, j);
								return token;
							}
						}
					}
				}
			}
		}

		// Tipo de dato (double) decimal
		if (cod.charAt(i) == 'd') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'e') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'c') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'm') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'a') {
								j++;
								if (j < cod.length() && cod.charAt(j) == 'l') {

									j++;
									lex = cod.substring(i, j);
									Token token = new Token(lex, Token.TIPODEDATO, j);
									return token;
								}
							}
						}
					}
				}
			}
		}

		// Tipo de dato (char) symbol
		if (cod.charAt(i) == 's') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'y') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'm') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'b') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'o') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'l') {

								j++;
								lex = cod.substring(i, j);
								Token token = new Token(lex, Token.TIPODEDATO, j);
								return token;
							}
						}
					}
				}
			}
		}

		// Tipo de dato (String) message
		if (cod.charAt(i) == 'm') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'e') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 's') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 's') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'a') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'g') {
								j++;
								if (j < cod.length() && cod.charAt(j) == 'e') {

									j++;
									lex = cod.substring(i, j);
									Token token = new Token(lex, Token.TIPODEDATO, j);
									return token;
								}
							}
						}
					}
				}
			}
		}

		// Tipo de dato (boolean) logic
		if (cod.charAt(i) == 'l') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'o') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'g') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'c') {

							j++;
							lex = cod.substring(i, j);
							Token token = new Token(lex, Token.TIPODEDATO, j);
							return token;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer una asignaci�n de sentencia de la cadena cod a partir de la
	 * posici��n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer la asignaci�n de
	 *            sentencia - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer la
	 *            asignaci�n de sentencia - 0<=i<codigo.length()
	 * @return el token asignaci�n de sentencia o NULL, si el token en la posici�n
	 *         dada no es un operador relacional.El Token se compone de el lexema,
	 *         el tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerAsignacionDeSentencia(String cod, int i) {
		String lex;
		int j;

		if (cod.charAt(i) == '�') {

			j = i + 1;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.ASIGNACIONDESENTENCIAAPERTURA, j);
			return token;
		}

		if (cod.charAt(i) == '?') {

			j = i + 1;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.ASIGNACIONDESENTENCIACIERRE, j);
			return token;
		}

		return null;
	}

	/**
	 * Intenta extraer una asignaci�n de agrupaci�n de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer la asignaci�n de
	 *            agrupaci�n - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer la
	 *            asignaci�n de sentencia - 0<=i<codigo.length()
	 * @return el token asignaci�n de sentencia o NULL, si el token en la posici�n
	 *         dada no es una asignaci�n de agrupaci�n.El Token se compone de el
	 *         lexema, el tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerAsignacionDeAgrupacion(String cod, int i) {
		String lex;
		int j;

		if (cod.charAt(i) == '[') {

			j = i + 1;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.ASIGNACIONDEAGRUPACIONAPERTURA, j);
			return token;
		}

		if (cod.charAt(i) == ']') {

			j = i + 1;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.ASIGNACIONDEAGRUPACIONCIERRE, j);
			return token;
		}

		return null;
	}

	/**
	 * Intenta extraer un comentario de l�nea de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer un comentario de
	 *            l�nea - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer un
	 *            comentario de l�nea - 0<=i<codigo.length()
	 * 
	 * @return el token comentario de l�ena o NULL, si el token en la posici�n
	 *         dada no es un comentario de l�nea.El Token se compone de el lexema,
	 *         el tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerComentarioDeLinea(String cod, int i) {
		String lex;
		int j;

		if (cod.charAt(i) == '!') {

			j = i + 1;

			if (j < cod.length() && esSimbolo(cod.charAt(j))) {

				do {
					j++;
				} while (j < cod.length() && esSimbolo(cod.charAt(j)));

				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.COMENTARIODELINEA, j);
				return token;
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un comentario de bloque de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer un comentario de
	 *            bloque - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer un
	 *            comentario de bloque - 0<=i<codigo.length()
	 * 
	 * @return el token comentario de l�ena o NULL, si el token en la posici�n dada
	 *         no es un comentario de bloque.El Token se compone de el lexema, el
	 *         tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerComentarioDeBloque(String cod, int i) {
		String lex;
		int j;

		if (cod.charAt(i) == '(') {

			j = i + 1;

			if (j < cod.length() && esSimbolo(cod.charAt(j))) {

				do {
					j++;
				} while (j < cod.length() && esSimbolo(cod.charAt(j)) && cod.charAt(j) != ')');

				if (j < cod.length() && cod.charAt(j) == ')') {

					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.COMENTARIODEBLOQUE, j);
					return token;
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer una secuencia de escape de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a intentar extraer una secuencia de
	 *            escape - codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a intentar extraer una
	 *            secuencia de escape - 0<=i<codigo.length()
	 * 
	 * @return el token secuencia de escape o NULL, si el token en la posici�n dada
	 *         no es una secuencia de escape.El Token se compone de el lexema, el
	 *         tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerSecuenciaDeEscape(String cod, int i) {
		String lex;
		int j;

		// Secuencia de escape: salto de linea ^j (\n)
		if (cod.charAt(i) == '^') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'j') {

				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.SECUENCIADEESCAPE, j);
				return token;
			}
		}

		// Secuencia de escape: String ^| (\")
		if (cod.charAt(i) == '^') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '|') {

				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.SECUENCIADEESCAPE, j);
				return token;
			}
		}

		// Secuencia de escape: char abre ^{ (\')
		if (cod.charAt(i) == '^') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '{') {

				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.SECUENCIADEESCAPE, j);
				return token;
			}
		}

		// Secuencia de escape: char cierra ^} (\')
		if (cod.charAt(i) == '^') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '}') {

				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.SECUENCIADEESCAPE, j);
				return token;
			}
		}

		// Secuencia de escape: Gorrito ^^ (^)
		if (cod.charAt(i) == '^') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '^') {

				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.SECUENCIADEESCAPE, j);
				return token;
			}
		}

		return null;
	}

	/**
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posici�n i.
	 * Antes de utilizar este m�todo, debe haberse intentado todos los otros m�todos
	 * para los otros tipos de token
	 * 
	 * @param cod
	 *            - c�digo al cual se le va a extraer el token no reconocido -
	 *            codigo!=null
	 * @param i
	 *            - posici�n a partir de la cual se va a extraer el token no
	 *            reconocido - 0<=indice<codigo.length()
	 * @return el token no reconocido. El Token se compone de lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 * 
	 */
	public Token extraerNoReconocido(String cod, int i) {
		String lexema = cod.substring(i, i + 1);
		int j = i + 1;

		Token token = new Token(lexema, Token.NORECONOCIDO, j);
		return token;
	}

	/**
	 * Determina si un car�cter es un d�gito
	 * 
	 * @param caracter
	 *            - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea un d�gito o no
	 */
	public boolean esDigito(char caracter) {
		return caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un car�cter es una letra
	 * 
	 * @param caracter
	 *            - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea una letra o no
	 */
	public boolean esLetra(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}

	/**
	 * Determina si es cualquier s�mbolo
	 * 
	 * @param caracter
	 *            - Caracter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea un s�mbolo o no
	 */
	public boolean esSimbolo(char caracter) {
		return caracter >= 32 && caracter <= 165;
	}

	/**
	 * Determina si un car�cter es una letra mayuscula
	 * 
	 * @param caracter
	 *            - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea una letra o no
	 */
	public boolean esLetraMayuscula(char caracter) {
		return caracter >= 'A' && caracter <= 'Z';
	}

	/**
	 * Determina si un car�cter es una letra minuscula
	 * 
	 * @param caracter
	 *            - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea una letra o no
	 */
	public boolean esLetraMinuscula(char caracter) {
		return caracter >= 'a' && caracter <= 'z';
	}
}
