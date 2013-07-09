import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaPalindromo {

	// A express�o � bem simples, o problema � que com a necessidade de suportar espa�os, ela fica um pouco ileg�vel.
	// Aqui seria o mesmo pattern, sem suporte a espa�os: ^(.)?(.)?(.)(.)\\4?\\3\\2?\\1?$
	
	// Agora sim, o pattern completo, com suporte a espa�os: ^(.)?\\s?(.)?\\s?(.)\\s?(.)\\s?\\4?\\s?\\3\\s?\\2?\\s?\\1?$
	
	// A explica��o:
	static String patternString = ""
			+ "^" // In�cio de String, para evitar que d� match em "123321" na String "Teste 123321"
			+ "(.)?" // O primeiro caractere, opcional, pois em pal�ndromos menores que quatro caracteres ele n�o ocorre.
			+ "\\s?" // Espa�o opcional.
			+ "(.)?" // O segundo caractere (ou, o primeiro de pal�ndromos de 3 caracteres), opcional tamb�m, para o caso de pal�ndromos menores.
			+ "\\s?" // Espa�o opcional.
			+ "([^\\s])" // O terceiro caractere (ou, o primeiro de pal�ndromos de 2 caracteres, o segundo do de 3), este n�o � opcional, visto que sempre teremos ao m�nimo 2 caracteres "iniciais". N�o pode ser um caractere de espa�o. 
			+ "\\s?" // Espa�o opcional
			+ "([^\\s])" // O quarto caractere (ou o segundo de pal�ndromos de 2 caracteres, o terceiro do de 3), obrigat�rio pelo mesmo motivo do terceiro caractere. N�o pode ser um espa�o.
			+ "\\s?" // Espa�o opcional
			+ "\\4?" // O "rematch" do quarto caractere. Opcional, pois em pal�ndromos o "caractere do meio" pode apacer s� uma vez. Ex: ovo
			+ "\\s?" // Espa�o opcional
			+ "\\3" // O "rematch" do terceiro caractere. N�o opcional, visto que este sempre aparecer� em pal�ndromos de 2 letras ou mais.
			+ "\\s?" // Espa�o opcional
			+ "\\2?" // O "rematch" do segundo caractere. Opcional, j� que em pal�ndromos com menos de 3 caracteres ele n�o existe.
			+ "\\s?" // Espa�o opcional
			+ "\\1?" // O "rematch" do primeiro caractere. Opcional, j� que em pal�ndromos com menos de 4 caracteres ele n�o existe.
			+ "$"; // Fim da String, para evitar que d� match em "123321" na String "123321 Teste"

	public static void main(String[] args) throws IOException {
		final Pattern p = Pattern.compile(patternString);

		final File pode = new File("../valid.txt");
		final File naoPode = new File("../notValid.txt");

		validaEmailFromFile(p, pode, true);
		validaEmailFromFile(p, naoPode, false);
		System.out.println("Done!");
	}

	private static void validaEmailFromFile(final Pattern p, final File file,
			final boolean expected) throws IOException {
		final List<String> emails = readEmailsFromFile(file);

		for (String email : emails) {
			Matcher m = p.matcher(email);
			if (m.matches() != expected) {
				throw new RuntimeException("Regex match failed, expected "
						+ expected + ", got " + (!expected)
						+ " with input string '" + email + "'");
			}
		}
	}

	private static List<String> readEmailsFromFile(final File file)
			throws IOException {
		final List<String> toReturn = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().length() > 0) {
					toReturn.add(line);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				// Ignored
			}
		}

		return toReturn;
	}
}
