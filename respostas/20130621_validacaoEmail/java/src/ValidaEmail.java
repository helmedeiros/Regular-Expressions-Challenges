import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidaEmail {

	static String patternString = ""
			+ "^" // Deve ser o início da String.
			+ "[a-z]" // O primeiro caractere deve ser uma letra minúscula (Não tá valendo acentos aí).
			+ "[a-z0-9_]*" // Depois pode ser seguidos de 0 ou mais (*) letras, números e underscores.
			+ "(\\.[a-z])?" // Se tiver um ponto, deve ser imediatamente seguido de uma letra, mas esse cara é opcional (?).
			+ "[a-z0-9_]*" // Depois do ponto, pode haver uma ou mais letras, números e underscores.
			+ "@" // Arroba, deve ter, o que separa o nome do domínio.
			+ "[a-z]" // O domínio deve começar com uma letra.
			+ "[a-z0-9]*" // Depois pode ser seguido de zero ou mais letras e números.
			+ "\\.[a-z]+" // Após isso, deve ser seguido de um '.' seguido de uma ou mais letras (+)
			+ "(\\.[a-z]+)?" // PODE (?) ser seguido de mais um '.' obrigatoriamente seguido de uma ou mais letras.
			+ "$"; // Deve dar match no final da String, para não ficar assim 'eu@gmail.com FEITO!!!' (se não tivesse o $, ela daria true)
	
	public static void main(String[] args) throws IOException {
		final Pattern p = Pattern.compile(patternString);
		
		final File pode = new File("../valid.txt");
		final File naoPode = new File("../notValid.txt");
		
		validaEmailFromFile(p, pode, true);
		validaEmailFromFile(p, naoPode, false);
	}

	private static void validaEmailFromFile(final Pattern p, final File file, final boolean expected) throws IOException {
		final List<String> emails = readEmailsFromFile(file);
		
		for(String email: emails) {
			Matcher m = p.matcher(email);
			if(m.matches() != expected) {
				throw new RuntimeException("Regex match failed, expected " + expected + ", got " + (!expected) + " with input string '" + email + "'");
			}
		}
	}

	private static List<String> readEmailsFromFile(final File file) throws IOException {
		final List<String> toReturn = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null) {
				if(line.trim().length() > 0) {
					toReturn.add(line);
				}					
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				br.close();
			} catch(Exception e) {
				// Ignored
			}
		}
		
		return toReturn;
	}

}
