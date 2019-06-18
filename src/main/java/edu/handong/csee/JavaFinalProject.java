package edu.handong.csee;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.handong.csee.Utils.*;

public class JavaFinalProject {
	
	private SaeamDataStructure<HashMap<String,ArrayList<ArrayList<String>>>> data = new SaeamDataStructure<HashMap<String,ArrayList<ArrayList<String>>>>();
	private SaeamDataStructure<HashMap<String,ArrayList<ArrayList<String>>>> data2 = new SaeamDataStructure<HashMap<String,ArrayList<ArrayList<String>>>>();
	public void setData(SaeamDataStructure<HashMap<String, ArrayList<ArrayList<String>>>> data) {
		this.data = data;
	}

	public void setData2(SaeamDataStructure<HashMap<String, ArrayList<ArrayList<String>>>> data2) {
		this.data2 = data2;
	}

	private String dataPath; // Zip file path
	private String resultPath; // the file path where the results are saved.
	private boolean help;
	
	public void run(String[] args) throws InterruptedException {
		Options options = createOptions();
		
		parseOptions(options, args);
		if (help){
			printHelp(options);
			return;
		}
		String rp[] = new String[2];
		rp[0] = resultPath.split(".csv")[0] + "1.csv";
		rp[1] = resultPath.split(".csv")[0] + "2.csv";
		//input output을 cli를 통해 입력받음
		Utils util = new Utils();
		
		util.getData(dataPath,data,data2);
		util.writeCSV(rp[1], 1, data);
		util.writeCSV(rp[0], 0, data2);
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			dataPath = cmd.getOptionValue("i");
			resultPath = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
	
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()     // this option is intended not to have an option value but just an option
				.argName("Output path")
				.required() // this is an optional option. So disabled required().
				.build());
		
		// add options by using OptionBuilder
				options.addOption(Option.builder("h").longOpt("help")
						.argName("Help")
				        .desc("Show a Help page")
				        .build());
		
		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Java Final Project";
		String footer ="";
		formatter.printHelp("JavaFinalProject", header, options, footer, true);
	}
}
