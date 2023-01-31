import java.util.List;

public interface Translator {
	
	public List<String> translateWord(String word);
	public List<String> translateText(String[] sentence);

}
