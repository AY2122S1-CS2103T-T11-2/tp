package lingogo.logic.parser;

import static java.util.Objects.requireNonNull;
import static lingogo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static lingogo.logic.parser.CliSyntax.PREFIX_INDEX_LIST;
import static lingogo.logic.parser.CliSyntax.PREFIX_LANGUAGE_TYPE;

import java.util.List;

import lingogo.commons.core.index.Index;
import lingogo.logic.commands.FilterCommand;
import lingogo.logic.commands.FilterCommand.FilterBuilder;
import lingogo.logic.parser.exceptions.ParseException;
import lingogo.model.flashcard.Phrase;


/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LANGUAGE_TYPE, PREFIX_INDEX_LIST);

        FilterBuilder filterBuilder = new FilterBuilder();


        if (argMultimap.getValue(PREFIX_LANGUAGE_TYPE).isPresent()) {
            Phrase languageType = ParserUtil.parsePhrase(argMultimap.getValue(PREFIX_LANGUAGE_TYPE).get());
            filterBuilder.setLanguageType(languageType);
        }


        if (argMultimap.getValue(PREFIX_INDEX_LIST).isPresent()) {
            List<Index> indexList = ParserUtil.parseIndices(argMultimap.getValue(PREFIX_INDEX_LIST).get());
            filterBuilder.setIndexList(indexList);
        }

        if (!filterBuilder.isAnyFieldFiltered()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }


        return new FilterCommand(filterBuilder);
    }


}