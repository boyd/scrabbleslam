import java.io.*;
import org.apache.commons.cli.*;
import scrabbleslam.ScrabbleSlam;

class Main {
	public static Options getOptions() {
		Options options = new Options();
		options.addOption( new Option( "h", "help", false, "print help" ) );
		options.addOption( new Option( "s", "start_word", false, "print random start word" ) );
		options.addOption( new Option( "a", "adjacent_word", true, "print words within 1 letter of entered word") );
		options.addOption( new Option( "pg", "print_graph_dot", true, "print .dot file of word graph") );

		return options;
	}
	public static void main(String[] args) throws ParseException {
		String dict_location = "../dictionaries/TWL06.txt";
		CommandLineParser parser = new GnuParser();
		Options options = Main.getOptions();

		CommandLine cmd = parser.parse( options, args);

		if (cmd.hasOption("help") ) {
			new HelpFormatter().printHelp("Main", options);
		} else if (cmd.hasOption("start_word")) {
			ScrabbleSlam game = new ScrabbleSlam(dict_location);
			System.out.println( game.pickRandomStartWord() );
		} else if (cmd.hasOption("adjacent_word")) {
			ScrabbleSlam game = new ScrabbleSlam(dict_location);
			System.out.println( game.adjacentWords(cmd.getOptionValue("adjacent_word") ) );
		} else if (cmd.hasOption("print_graph_dot")) {
			ScrabbleSlam game = new ScrabbleSlam(dict_location);
			System.out.println( game.dotGraph(cmd.getOptionValue("print_graph_dot")) );
		} 
	}
}
