/**
 * 
 */
package extrator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JN Marcos
 *
 */
public class Extrator {
	//nome do usuário no computador
	public static String NOME_USUARIO = "JN Marcos";
	public static String CAMINHO_PASTAS = "\\Documents\\";
	public static int NUMERO_FERRAMENTAS = 2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String caminhos[] = new String[NUMERO_FERRAMENTAS + 1];
		
		//crie arquivos para cada ferramenta em pastas separadas
		//dê o mesmo nome para o arquivo na outra pasta correspondente, com
		//o mesmo número de frases. Se tiver 10 frases em output1.txt em Stanford
		//deve ter um arquivo chamado output1.txt com 10 frases em Spacy
		//cada um com os resultados na sua devida formatação
		caminhos[0] = "C:\\Users\\" + NOME_USUARIO + CAMINHO_PASTAS + "Stanford\\";
		caminhos[1] = "C:\\Users\\" + NOME_USUARIO + CAMINHO_PASTAS + "Spacy\\";
		caminhos[2] = "C:\\Users\\" + NOME_USUARIO + CAMINHO_PASTAS + "Resultados\\";

		
		//"Cria-se" os Files que apontam para as pastas dos outputs
		File pastas[] = new File[NUMERO_FERRAMENTAS + 1];
		pastas[0] = new File(caminhos[0]);
		pastas[1] = new File(caminhos[1]);
		pastas[2] = new File(caminhos[2]);
		
		if (!pastas[NUMERO_FERRAMENTAS].exists()){ //Se pasta de resultados não existe, cria
			pastas[NUMERO_FERRAMENTAS].mkdir();
		}

		File[] arquivos;

		//Arquivo de leitura a partir do caminhoTemporario (string)
		FileReader[] fr = null;
		fr = new FileReader[NUMERO_FERRAMENTAS];
		BufferedReader[] br = null;
		br  = new BufferedReader[NUMERO_FERRAMENTAS];

		
		FileWriter fw = null;
		BufferedWriter bw = null;

		String linha;
		String resultado[] = new String[NUMERO_FERRAMENTAS];
		String documento = "";


		//Conjunto de SOMENTE arquivos de uma pasta com outputs a serem limpos
		//O número de arquivos em CADA PASTA deve ser O MESMO
		arquivos = pastas[0].listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile();
			}
		});



		String nomeArquivo = "";
		String caminhoLeitura = "";
		File f = null;

		List<Sentenca> s = null;
		//Lê-se a primeira linha
		for (int j = 0; j < arquivos.length; j++){
			for (int i = 0; i < NUMERO_FERRAMENTAS; i++) {
				nomeArquivo = arquivos[j].getName();

				caminhoLeitura = caminhos[i] + nomeArquivo;
				f = new File(caminhoLeitura);

				//Cria-se o buffer para ler do arquivo
				try {
					fr[i] = new FileReader(f);
					br[i] = new BufferedReader(fr[i]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				linha = "";
				try {
					linha = br[i].readLine();
					resultado[i] = "";

					while(linha != null){
						resultado[i] = resultado[i] + " " + linha;
						linha = br[i].readLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				resultado[i] = resultado[i].trim();

				switch(i) {
				case 0:
					s = segmentacaoStanford(resultado[0]);
					break;
				case 1:
					segmentacaoSpacy(resultado[1], s);
					break;
				default:
				}

			}
			documento = XML(s, nomeArquivo);
			
			System.out.println(documento);
			
			try {
				fw = new FileWriter(new File(caminhos[NUMERO_FERRAMENTAS] + nomeArquivo + ".xml"));
				bw = new BufferedWriter(fw);
				bw.write(documento);
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}



	}

	public static String[] triagemSpacy(String[] tokens) {
		List<String> t = new ArrayList<>();
		String buffer = "";
		for (int i = 0; i < tokens.length - 1; i++) {//exceto os últimos, que são chunkings	
			if (tokens[i].contains("-")) {
				t.remove(i-1);
				buffer+= tokens[i].split("u\\Q'\\E")[1].split("\',")[0]
						+ tokens[i + 1].split("u\\Q'\\E")[1].split("\',")[0];
				tokens[i-1] = tokens[i-1].replaceFirst("u\\Q'\\E[A-Za-z]+\\Q',\\E",
						"u'" + buffer + "',");
				t.add(tokens[i-1]);
				//System.out.println(tokens[i-1]);
				i++;
			} else {
				buffer = tokens[i].split("u\\Q'\\E")[1].split("\',")[0];
				t.add(tokens[i]);
			}
		}
		
		t.add(tokens[tokens.length - 1]);
		return t.toArray(new String[t.size()]);
	}
	
	public static void segmentacaoSpacy(String texto, List<Sentenca> s) {
		texto = texto.replace("ï»¿", "");
		String[] sentencas = texto.split("\\Q)\\E  ");
		String[] tokens;
		String[] chunking;
		
		List<Chunk> chunkList;
		Token t = null;
		Chunk c = null;
		
		for (int i = 0; i < sentencas.length; i++) {
			tokens = sentencas[i].split("(378|0)L\\Q)\\E ");
			if (sentencas[i].contains("-")) tokens = triagemSpacy(tokens);
			chunkList = new ArrayList<>();
			for (int j = 0; j < tokens.length; j++) {			
				if (j < tokens.length - 1) {//até penúltimo
					t = s.get(i).getTokens().get(j);
					t.setCkOt(tokens[j].split("u\\Q'\\E")[2].split("\',")[0]);
					t.setTipo(tokens[j].split("u\\Q'\\E")[3].split("\\Q',\\E")[0]);					
				} else {
					chunking = tokens[tokens.length - 1].split("\\Q) (\\E");
					for (int k = 0; k < chunking.length; k++) {
						c = new Chunk();
						c.setIdChunking(Integer.toString(k+1));
						c.setIdSentenca(Integer.toString(i+1));
						String st = chunking[k].split("u\\Q'\\E")[1];
						st = st.replace("\', ", "").trim();
						c.setTexto(st);
						c.setTipo(chunking[k].split("u\\Q'\\E")[3]);
						
						for (int l = 0; l < tokens.length - 1; l++) {
							if (st.contains(s.get(i).getTokens().get(l).getTexto())) {
								c.addIndice(l+1);
							}
						}
						chunkList.add(c);
					}
					
				}				
			}
			s.get(i).setChunks(chunkList);
		}
	}

	public static List<Sentenca> segmentacaoStanford(String texto) {
		List<Sentenca> sentencaList = new ArrayList<>();
		String[] sentencas = texto.split("Sentence #\\d");
		String frase;
		String[] tokens;
		String[] infos;
		Sentenca s;
		Token t;
		List<Token> tokensList;
		for(int i = 1; i < sentencas.length; i ++) {
			frase = sentencas[i].split("Tokens:")[0].split("tokens\\Q):\\E")[1].trim();
			s = new Sentenca(frase);
			tokens = sentencas[i].split("\\Q[\\ETex");
			tokensList = new ArrayList<>();

			for (int j = 1; j < tokens.length; j++) {
				infos = tokens[j].split(" ");
				t = new Token(Integer.toString(i), Integer.toString(j));
				t.setTexto(infos[0].split("=")[1].trim());
				t.setCharOffsetBegin(infos[1].split("=")[1].trim());
				t.setCharOffsetEnd(infos[2].split("=")[1].trim());
				t.setPOS(infos[3].split("=")[1].trim());
				t.setLema(infos[4].split("=")[1].trim());
				t.setNER(infos[5].split("=")[1].split("]")[0].trim());
				tokensList.add(t);
			}

			s.setTokens(tokensList);
			sentencaList.add(s);
		}
		return sentencaList;
	}

	public static String XML(List<Sentenca> s, String nomeArquivo) {
		String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"\r\n" + 
				"-<doc filename=\"" + nomeArquivo +  ".xml\">\r\n" + 
				"\r\n" + "\r\n" + "<sentences>" + "\r\n";

		String textoIntermediario = "";
		String sentenca;
		for (int i = 0 ; i < s.size(); i++) {
			sentenca = "<sentence text=\"" + s.get(i).getTexto() + "\" s_id=\"" + (i+1) + 
					"\" has_ne=\"" + "TEM DE VER COMO PEGAR ESSA INFO DEPOIS" + "\">\n";
			for (int j = 0; j < s.get(i).getTokens().size(); j++) {
				sentenca += s.get(i).getTokens().get(j).toString();
			}	
			
			sentenca+= "\n<chunkings>\n";
			for (int j = 0; j < s.get(i).getChunks().size(); j++) {
				sentenca+= s.get(i).getChunks().get(j).toString();
			}
			sentenca+="</chunkings>\n";
			sentenca += "</sentence>\n";
			textoIntermediario+= sentenca;
		}
		String XMLfim = "</sentences>\r\n" + "\r\n" + "</doc>";
		return XML + textoIntermediario + XMLfim;
	}

}
