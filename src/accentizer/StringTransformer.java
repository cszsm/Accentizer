package accentizer;

/**
 * Created by zscse on 2016. 03. 21..
 */
class StringTransformer {

    public String pad(String text, int window) {
        String paddedText = "";
        char padding = '_';

        for (int i = 0; i < window - 1; i++) {
            paddedText += padding;
        }

        paddedText += ' ' + text + '\n' + ' ';

        for (int i = 0; i < window - 1; i++) {
            paddedText += padding;
        }

        return paddedText;
    }

    public char normalize(char c) {
        if (Character.isWhitespace(c)) {
            return ' ';
        }
        if (Character.isDigit(c)) {
            return '0';
        }
        if (isPunctuation(c)) {
            return '_';
        }
        if (Character.isAlphabetic(c)) {
            return Character.toLowerCase(c);
        }
        return '*';
    }

    private boolean isPunctuation(char c) {
        String punctuations = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        for (int i = 0; i < punctuations.length(); i++) {
            if (c == punctuations.charAt(i)) {
                return true;
            }
        }
        return false;
    }
}
