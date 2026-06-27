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

	// A expressão é bem simples, o problema é que com a necessidade de suportar espaços, ela fica um pouco ilegível.
	// Aqui seria o mesmo pattern, sem suporte a espaços: ^(.)?(.)?(.)(.)\4?\3\2?\1?$
	
	// Agora sim, o pattern completo, com suporte a espaços: ^([^\s])?\s?([^\s])?\s?([^\s])\s?([^\s])\s?\4?\s?\3\s?\2?\s?\1?$
	
	// A explicação:
	static String patternString = ""
			+ "^" // Início de String, para evitar que dê match em "123321" na String "Teste 123321"
			+ "([^\\s])?" // O primeiro caractere, opcional, pois em palíndromos menores que quatro caracteres ele não ocorre. Não pode ser um caractere de espaço.
			+ "\\s?" // Espaço opcional.
			+ "([^\\s])?" // O segundo caractere (ou, o primeiro de palíndromos de 3 caracteres), opcional também, para o caso de palíndromos menores. Não pode ser um caractere de espaço.
			+ "\\s?" // Espaço opcional.
			+ "([^\\s])" // O terceiro caractere (ou, o primeiro de palíndromos de 2 caracteres, o segundo do de 3), este não é opcional, visto que sempre teremos ao mínimo 2 caracteres "iniciais". Não pode ser um caractere de espaço. 
			+ "\\s?" // Espaço opcional
			+ "([^\\s])" // O quarto caractere (ou o segundo de palíndromos de 2 caracteres, o terceiro do de 3), obrigatório pelo mesmo motivo do terceiro caractere. Não pode ser um espaço.
			+ "\\s?" // Espaço opcional
			+ "\\4?" // O "rematch" do quarto caractere. Opcional, pois em palíndromos o "caractere do meio" pode apacer só uma vez. Ex: ovo
			+ "\\s?" // Espaço opcional
			+ "\\3" // O "rematch" do terceiro caractere. Não opcional, visto que este sempre aparecerá em palíndromos de 2 letras ou mais.
			+ "\\s?" // Espaço opcional
			+ "\\2?" // O "rematch" do segundo caractere. Opcional, já que em palíndromos com menos de 3 caracteres ele não existe.
			+ "\\s?" // Espaço opcional
			+ "\\1?" // O "rematch" do primeiro caractere. Opcional, já que em palíndromos com menos de 4 caracteres ele não existe.
			+ "$"; // Fim da String, para evitar que dê match em "123321" na String "123321 Teste"

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
