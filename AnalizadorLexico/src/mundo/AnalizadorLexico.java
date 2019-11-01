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
 * Clase que modela un analizador léxico
 */

public class AnalizadorLexico {
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Extrae los tokens de un código fuente dado
     * @param cod - código al cual se le van a extraer los tokens - !=null
     * @return vector con los tokens
     */
    public ArrayList<Token> extraerTokens( String cod )
    {
    	Token token;
    	ArrayList<Token> vectorTokens = new ArrayList<Token>();

	    // El primer token se extrae a partir de posici�n cero
    	int i = 0;

    	// Ciclo para extraer todos los tokens
    	while( i < cod.length() )
		{
	        // Extrae el token de la posici�n i
			token = extraerSiguienteToken( cod, i);
	        vectorTokens.add( token );
	        i = token.darIndiceSiguiente();
    	}
		return vectorTokens;
    }

    /**
     * Extrae el token de la cadena cod a partir de la posici�n i, bas�ndose en el Aut�mata
     * @param cod - c�digo al cual se le va a extraer un token - codigo!=null
     * @param i - posici�n a partir de la cual se va a extraer el token  - i>=0
     * @return token que se extrajo de la cadena
     */
    public Token extraerSiguienteToken( String cod, int i )
    {
    	Token token;

		// Intenta extraer un entero
		token = extraerEntero( cod, i);
		if ( token != null )
			return token;
    	
    	// Intenta extraer un operador aditivo
		token = extraerOperadorAditivo( cod, i);
		if ( token != null )
			return token;

		// Intenta extraer un operador de asignaci�n
		token = extraerOperadorAsignacion( cod, i);
		if ( token != null )
			return token;

		// Intenta extraer un identificador
		token = extraerIdentificador( cod, i);
		if ( token != null )
			return token;
			
		// Extrae un token no reconocido
		token = extraerNoReconocido( cod, i);
		return token;
    }

    /**
     * Intenta extraer un entero de la cadena cod a partir de la posici�n i,
     * bas�ndose en el Aut�mata
     * @param cod - c�digo al cual se le va a intentar extraer un entero - codigo!=null
     * @param i - posici�n a partir de la cual se va a intentar extraer un entero  - 0<=indice<codigo.length()
     * @return el token entero o NULL, si el token en la posici�n dada no es un entero. El Token se compone de 
     * el lexema, el tipo y la posici�n del siguiente lexema.
     */
	
    // Este m�todo usa el m�todo substring(), que se explica a continuaci�n:
    // x.substring( i, j ) retorna una nueva cadena que es una subcadena de la cadena x.
    // La subcadena comienza en la posici�n i y
    // se extiende hasta el car�cter en la posici�n j-1.
    // Ejemplo: "universidad".substring(3,6) retorna "ver",
	
	
	public Token extraerEntero ( String cod, int i)
	{
		
		int j;
		String lex;
		if( cod.charAt(i)=='#' ){
			j=i+1;
			if( j<cod.length() && esDigito(cod.charAt(j)) ){		
			    do
			    	j++;
			    while (  j<cod.length( ) && esDigito(cod.charAt(j)) );
		        lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.ENTERO, j );
				return token;			
			}
		}
		
		return null;
	}

    /**
     * Intenta extraer un operador aditivo de la cadena cod a partir de la posici�n i,
     * bas�ndose en el Aut�mata
     * @param cod - c�digo al cual se le va a intentar extraer el operador aditivo  - codigo!=null
     * @param i - posici�n a partir de la cual se va a intentar extraer el operador aditivo  - 0<=i<codigo.length()
     * @return el token operador aditivo o NULL, si el token en la posici�n dada no es un operador aditivo.El Token se compone de 
     * el lexema, el tipo y la posici�n del siguiente lexema.
     */
	public Token extraerOperadorAditivo ( String cod, int i )
	{
		if( cod.charAt(i) =='+' || cod.charAt(i) =='-'){
			int j=i+1;
	        String lex =  cod.substring( i, j);			    
			Token token = new Token( lex, Token.OPERADORADITIVO, j );
			return token;
		}
		return null;
	}

    /**
     * Intenta extraer un operador de asignaci�n de la cadena cod a partir de la posici�n i,
     * bas�ndose en el Aut�mata
     * @param cod - c�digo al cual se le va a intentar extraer el operador de asignaci�n  - codigo!=null
     * @param i - posici�n a partir de la cual se va a intentar extraer el operador de asingaci�n  - 0<=i<codigo.length()
     * @return el token operador asignaci�n o NULL, si el token en la posici�n dada no es un operador de asignaci�n. El Token se compone de 
     * el lexema, el tipo y la posici�n del siguiente lexema.
     */
	public Token extraerOperadorAsignacion ( String cod, int i )
	{
		if( cod.charAt(i) =='<'){
			int j=i+1;
			if( j<cod.length() && (cod.charAt(j) =='<' || cod.charAt(j) =='-' ) ){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='<' ){		
					j++;
			        String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.OPERADORASIGNACION, j );
					return token;
				}
			}
		}
		return null;
	}
	
    /**
     * Intenta extraer un identificador de la cadena cod a partir de la posici�n i,
     * bas�ndose en el Aut�mata
     * @param cod - c�digo al cual se le va a intentar extraer un identficador - codigo!=null
     * @param i - posici�n a partir de la cual se va a intentar extraer un identificador  - 0<=indice<codigo.length()
     * @return el token identificaror o NULL, si el token en la posici�n dada no es un identificador. El Token se compone de 
     * el lexema, el tipo y la posici�n del siguiente lexema.
     */
		
	public Token extraerIdentificador ( String cod, int i)
	{
		if( cod.charAt(i)=='_' ){
			int j=i+1;
			while( j<cod.length() && esLetra(cod.charAt(j)) )		
			    	j++;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.IDENTIFICADOR, j );
			return token;			
		}	
		return null;
	}

    /**
     * Extraer un lexema no reconocido de la cadena cod a partir de la posici�n i.
     * Antes de utilizar este m�todo, debe haberse intentado todos los otros m�todos para los otros tipos de token
     * @param cod - c�digo al cual se le va a extraer el token no reconocido - codigo!=null
     * @param i - posici�n a partir de la cual se va a extraer el token no reconocido  - 0<=indice<codigo.length()
     * @return el token no reconocido. El Token se compone de lexema, el tipo y la posici�n del siguiente lexema.

     */
	public Token extraerNoReconocido ( String cod, int i)
	{
		String lexema =  cod.substring( i, i + 1);
		int j=i+1;
		Token token = new Token( lexema, Token.NORECONOCIDO, j );
		return token;
	}
	
	/**
     * Determina si un car�cter es un d�gito
     * @param caracter - Car�cter que se va a analizar - caracter!=null,
     * @return true o false seg�n el car�cter sea un d�gito o no
     */
	public boolean esDigito (char caracter )
	{
		return  caracter >= '0' && caracter <= '9';
	}

	/**
     * Determina si un car�cter es una letra
     * @param caracter - Car�cter que se va a analizar - caracter!=null,
     * @return true o false seg�n el car�cter sea una letra o no
     */
	public boolean esLetra (char caracter )
	{
		return  (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}

}
