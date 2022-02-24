// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if (nextOpenBracket == -1) {
                break;
            }
            if (nextOpenBracket != 0 && markdown.charAt(nextOpenBracket - 1) == '!') {
                currentIndex++;
                continue;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if (nextCloseBracket == -1) {
                break;
            }

            /* Snippet 3
            int lineBreak = markdown.indexOf("\n", nextOpenBracket);
            int nextLineBreak = markdown.indexOf("\n", lineBreak + 1);
            if (lineBreak != -1 && lineBreak < nextCloseBracket && nextLineBreak < nextCloseBracket) {
                currentIndex++;
                continue;
            }
            */

            /* Snippet 2
            int tempCloseBracket = markdown.indexOf("]", nextCloseBracket + 1);
            if (tempCloseBracket != -1 && tempCloseBracket < markdown.indexOf("(", nextCloseBracket) && markdown.charAt(tempCloseBracket + 1) == '(') {
                nextCloseBracket = tempCloseBracket;
            }

            int tempOpenBracket = markdown.indexOf("[", nextOpenBracket + 1);
            if (tempOpenBracket != -1 && tempOpenBracket < nextCloseBracket && markdown.indexOf("(", tempOpenBracket) < nextCloseBracket) {
                currentIndex++;
                continue;
            }
            */

            /* Snippet 1
            int backTick = markdown.indexOf("`", nextOpenBracket);
            int nextBackTick = markdown.indexOf("`", backTick + 1);
            if (backTick != -1 && backTick < nextCloseBracket && (nextBackTick == -1 || nextBackTick > nextCloseBracket)) {
                currentIndex++;
                continue;
            }
            */

            if (markdown.indexOf("(", nextCloseBracket) != nextCloseBracket + 1) {
                break;
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if (openParen == -1) {
                break;
            }
            int closeParen = markdown.indexOf(")", openParen);
            if (closeParen == -1 || (markdown.indexOf("[", openParen) != -1 && closeParen > markdown.indexOf("[", openParen))) {
                break;
            }

            /* Snippet 2
            int tempCloseParen = markdown.indexOf(")", closeParen + 1);
            while (tempCloseParen != - 1 && (tempCloseParen < markdown.indexOf("[", closeParen) && tempCloseParen < markdown.indexOf("]", closeParen) || markdown.indexOf("[", closeParen) == -1)) {
                closeParen = markdown.indexOf(")", closeParen + 1);
                tempCloseParen = markdown.indexOf(")", closeParen + 1);
            }
            */

            toReturn.add(markdown.substring(openParen + 1, closeParen).trim());
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}