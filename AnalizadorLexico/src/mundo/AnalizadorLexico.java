/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 * $Id$
 * Universidad del Quindï¿½o (Armenia - Colombia)
 * Programa de Ingenierï¿½a de Sistemas y Computaciï¿½n
 *
 * Asignatura: Teorï¿½a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor: Leonardo A. Hernï¿½ndez R. - Agosto 2008 - Marzo 2009
 * Autor:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

import java.util.ArrayList;

/**
 * Clase que modela un analizador lÃ©xico
 */

public class AnalizadorLexico {

	// -----------------------------------------------------------------
	// Mï¿½todos
	// -----------------------------------------------------------------

	/**
	 * Extrae los tokens de un cÃ³digo fuente dado
	 * 
	 * @param cod
	 *            - cÃ³digo al cual se le van a extraer los tokens - !=null
	 * @return vector con los tokens
	 */
	public ArrayList<Token> extraerTokens(String cod) {
		Token token;
		ArrayList<Token> vectorTokens = new ArrayList<Token>();

		// El primer token se extrae a partir de posiciï¿½n cero
		int i = 0;

		// Ciclo para extraer todos los tokens
		while (i < cod.length()) {
			// Extrae el token de la posiciï¿½n i
			token = extraerSiguienteToken(cod, i);
			vectorTokens.add(token);
			i = token.darIndiceSiguiente();
		}
		return vectorTokens;
	}

	/**
	 * Extrae el token de la cadena cod a partir de la posiciï¿½n i, basï¿½ndose en
	 * el Autï¿½mata
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a extraer un token - codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a extraer el token - i>=0
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

		// Intenta extraer un operador de asignaciï¿½n
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

		// Extrae un token no reconocido
		token = extraerNoReconocido(cod, i);
		return token;
	}

	/**
	 * Intenta extraer un entero de la cadena cod a partir de la posiciï¿½n i,
	 * basï¿½ndose en el Autï¿½mata
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a intentar extraer un entero -
	 *            codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a intentar extraer un
	 *            entero - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posiciï¿½n dada no es un
	 *         entero. El Token se compone de el lexema, el tipo y la posiciï¿½n del
	 *         siguiente lexema.
	 */

	// Este mï¿½todo usa el mï¿½todo substring(), que se explica a continuaciï¿½n:
	// x.substring( i, j ) retorna una nueva cadena que es una subcadena de la
	// cadena x.
	// La subcadena comienza en la posiciï¿½n i y
	// se extiende hasta el carï¿½cter en la posiciï¿½n j-1.
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
	 * Intenta extraer un número Real de la cadena cod a partir de la posiciï¿½n i,
	 * basï¿½ndose en el Autï¿½mata
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a intentar extraer el operador aditivo
	 *            - codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a intentar extraer el
	 *            numero real - 0<=i<codigo.length()
	 * @return el token numero real o NULL, si el token en la posiciï¿½n dada no es
	 *         un número real.El Token se compone del lexema, el tipo y la
	 *         posiciï¿½n del siguiente lexema.
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

					do {
						j++;
					} while (j < cod.length() && esDigito(cod.charAt(j)));

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.REAL, j);
					return token;
				}
			}
		}

		return null;
	}

	/**
	 * Intenta extraer un caracter de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer el caracter -
	 *            codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer el
	 *            caracter - 0<=i<codigo.length()
	 * @return el token caracter o NULL, si el token en la posición dada no es un
	 *         caracter.El Token se compone de el lexema, el tipo y la posición del
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
	 * Intenta extraer una cadena de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer la cadena -
	 *            codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer la cadena
	 *            - 0<=i<codigo.length()
	 * @return el token cadena o NULL, si el token en la posición dada no es una
	 *         cadena.El Token se compone de el lexema, el tipo y la posición del
	 *         siguiente lexema.
	 */
	public Token extraerCadena(String cod, int i) {

		int j;
		String lex;

		if (cod.charAt(i) == '|') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '|') {

				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.CADENA, j);
				return token;
			}

			if (j < cod.length() && esSimbolo(cod.charAt(j))) {

				do {
					j++;
				} while (j < cod.length() && cod.charAt(j) != '|' && esSimbolo(cod.charAt(j)));

				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.CADENA, j);
				return token;

			}
		}
		return null;
	}

	/**
	 * Intenta extraer un booleano de la cadena cod a partir de la posición i,
	 * basándose en el Autómata
	 * 
	 * @param cod
	 *            - código al cual se le va a intentar extraer el booleano -
	 *            codigo!=null
	 * @param i
	 *            - posición a partir de la cual se va a intentar extraer el
	 *            booleano - 0<=i<codigo.length()
	 * @return el token booleano o NULL, si el token en la posición dada no es un
	 *         booleano.El Token se compone de el lexema, el tipo y la posición del
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
	 * Intenta extraer un operador aditivo de la cadena cod a partir de la
	 * posiciï¿½n i, basï¿½ndose en el Autï¿½mata
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a intentar extraer el operador aditivo
	 *            - codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a intentar extraer el
	 *            operador aditivo - 0<=i<codigo.length()
	 * @return el token operador aditivo o NULL, si el token en la posiciï¿½n dada
	 *         no es un operador aditivo.El Token se compone de el lexema, el tipo y
	 *         la posiciï¿½n del siguiente lexema.
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
	 * Intenta extraer un operador de asignaciï¿½n de la cadena cod a partir de la
	 * posiciï¿½n i, basï¿½ndose en el Autï¿½mata
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a intentar extraer el operador de
	 *            asignaciï¿½n - codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a intentar extraer el
	 *            operador de asingaciï¿½n - 0<=i<codigo.length()
	 * @return el token operador asignaciï¿½n o NULL, si el token en la posiciï¿½n
	 *         dada no es un operador de asignaciï¿½n. El Token se compone de el
	 *         lexema, el tipo y la posiciï¿½n del siguiente lexema.
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
	 * posiciï¿½n i, basï¿½ndose en el Autï¿½mata
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a intentar extraer el operador
	 *            relacional - codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a intentar extraer el
	 *            operador relacional - 0<=i<codigo.length()
	 * @return el token operador relacional o NULL, si el token en la posiciï¿½n
	 *         dada no es un operador relacional.El Token se compone de el lexema,
	 *         el tipo y la posiciï¿½n del siguiente lexema.
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
		}
		if (cod.charAt(i) == '<') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '<') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
		}
		if (cod.charAt(i) == '>') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == '|') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORRELACIONAL, j);
				return token;
			}
		}
		if (cod.charAt(i) == '<') {
			j = i + 1;

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
	 * Intenta extraer un operador de asignaciï¿½n de la cadena cod a partir de la
	 * posiciï¿½n i, basï¿½ndose en el Autï¿½mata
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a intentar extraer el operador de
	 *            asignaciï¿½n - codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a intentar extraer el
	 *            operador de asingaciï¿½n - 0<=i<codigo.length()
	 * @return el token operador asignaciï¿½n o NULL, si el token en la posiciï¿½n
	 *         dada no es un operador de asignaciï¿½n. El Token se compone de el
	 *         lexema, el tipo y la posiciï¿½n del siguiente lexema.
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
	 * Intenta extraer un identificador de la cadena cod a partir de la posiciï¿½n
	 * i, basï¿½ndose en el Autï¿½mata
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a intentar extraer un
	 *            identificador - 0<=indice<codigo.length()
	 * @return el token identificaror o NULL, si el token en la posiciï¿½n dada no
	 *         es un identificador. El Token se compone de el lexema, el tipo y la
	 *         posiciï¿½n del siguiente lexema.
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
					Token token = new Token(lex, Token.IDENTIFICADOR, j);
					return token;

				}
				if (j < cod.length() && esLetraMinuscula(cod.charAt(j))) {

					do {
						j++;
					} while (j < cod.length() && esLetra(cod.charAt(j)));

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.IDENTIFICADOR, j);
					return token;

				}
			}
			if (j < cod.length() && esLetraMayuscula(cod.charAt(j))) {

				do {
					j++;
				} while (j < cod.length() && esLetraMayuscula(cod.charAt(j)));

				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.IDENTIFICADOR, j);
				return token;

			}
			if (j < cod.length() && esLetraMinuscula(cod.charAt(j))) {

				do {
					j++;
				} while (j < cod.length() && (esDigito(cod.charAt(j)) || esLetraMinuscula(cod.charAt(j))));

				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.IDENTIFICADOR, j);
				return token;

			}
		}
		return null;
	}

	/**
	 * 
	 */
	public Token extraerTerminal(String cod, int i) {

		String lex;
		int j;

		if (cod.charAt(i) == '\n') {
			j = i + 1;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.IDENTIFICADOR, j);
			return token;
		}

		return null;
	}

	/**
	 * 
	 */
	public Token extraerAgrupaciones(String cod, int i) {

		return null;
	}

	/**
	 * Intenta extraer una palabra reservada de la cadena cod a partir de la
	 * posiciï¿½n i, basï¿½ndose en el Autï¿½mata
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a intentar extraer una palabra
	 *            reservada - codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a intentar extraer una
	 *            palabra reservada - 0<=indice<codigo.length()
	 * @return el token palabra reservada o NULL, si el token en la posiciï¿½n dada
	 *         no es una palabra reservada. El Token se compone de el lexema, el
	 *         tipo y la posiciï¿½n del siguiente lexema.
	 */
	public Token extraerPalabraReservada(String cod, int i) {

		String lex;
		int j;

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

		// Palabra reservada back (return/break)
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
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posiciï¿½n i.
	 * Antes de utilizar este mï¿½todo, debe haberse intentado todos los otros
	 * mï¿½todos para los otros tipos de token
	 * 
	 * @param cod
	 *            - cï¿½digo al cual se le va a extraer el token no reconocido -
	 *            codigo!=null
	 * @param i
	 *            - posiciï¿½n a partir de la cual se va a extraer el token no
	 *            reconocido - 0<=indice<codigo.length()
	 * @return el token no reconocido. El Token se compone de lexema, el tipo y la
	 *         posiciï¿½n del siguiente lexema.
	 * 
	 */
	public Token extraerNoReconocido(String cod, int i) {
		String lexema = cod.substring(i, i + 1);
		int j = i + 1;
		Token token = new Token(lexema, Token.NORECONOCIDO, j);
		return token;
	}

	/**
	 * Determina si un carï¿½cter es un dï¿½gito
	 * 
	 * @param caracter
	 *            - Carï¿½cter que se va a analizar - caracter!=null,
	 * @return true o false segï¿½n el carï¿½cter sea un dï¿½gito o no
	 */
	public boolean esDigito(char caracter) {
		return caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un carï¿½cter es una letra
	 * 
	 * @param caracter
	 *            - Carï¿½cter que se va a analizar - caracter!=null,
	 * @return true o false segï¿½n el carï¿½cter sea una letra o no
	 */
	public boolean esLetra(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}

	/**
	 * Determina si es cualquier símbolo
	 * 
	 * @param caracter
	 *            - Caracter que se va a analizar - caracter!=null,
	 * @return true o false segï¿½n el carï¿½cter sea un símbolo o no
	 */
	public boolean esSimbolo(char caracter) {
		return caracter >= 32 && caracter <= 126;
	}

	/**
	 * Determina si un carï¿½cter es una letra mayuscula
	 * 
	 * @param caracter
	 *            - Carï¿½cter que se va a analizar - caracter!=null,
	 * @return true o false segï¿½n el carï¿½cter sea una letra o no
	 */
	public boolean esLetraMayuscula(char caracter) {
		return caracter >= 'A' && caracter <= 'Z';
	}

	/**
	 * Determina si un carï¿½cter es una letra minuscula
	 * 
	 * @param caracter
	 *            - Carï¿½cter que se va a analizar - caracter!=null,
	 * @return true o false segï¿½n el carï¿½cter sea una letra o no
	 */
	public boolean esLetraMinuscula(char caracter) {
		return caracter >= 'a' && caracter <= 'z';
	}
}
