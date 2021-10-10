package lingogo.logic.parser;

import static lingogo.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static lingogo.testutil.Assert.assertThrows;
import static lingogo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lingogo.logic.parser.exceptions.ParseException;
import lingogo.model.flashcard.Phrase;

public class ParserUtilTest {
    private static final String INVALID_PHRASE = "Go0d Morning";
    private static final String VALID_PHRASE = "Good Morning";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_FLASHCARD, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_FLASHCARD, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parsePhrase_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhrase((String) null));
    }

    @Test
    public void parsePhrase_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhrase(INVALID_PHRASE));
    }

    @Test
    public void parsePhrase_validValueWithoutWhitespace_returnsPhrase() throws Exception {
        Phrase expectedPhrase = new Phrase(VALID_PHRASE);
        assertEquals(expectedPhrase, ParserUtil.parsePhrase(VALID_PHRASE));
    }

    @Test
    public void parsePhrase_validValueWithWhitespace_returnsTrimmedPhrase() throws Exception {
        String phraseWithWhitespace = WHITESPACE + VALID_PHRASE + WHITESPACE;
        Phrase expectedPhrase = new Phrase(VALID_PHRASE);
        assertEquals(expectedPhrase, ParserUtil.parsePhrase(phraseWithWhitespace));
    }

}
