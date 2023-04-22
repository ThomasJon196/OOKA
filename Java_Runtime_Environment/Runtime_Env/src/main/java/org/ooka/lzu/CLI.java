package org.ooka.lzu;

import org.apache.commons.cli.*;

public class CLI {
    public static void main(String[] args) throws ParseException {
        // Options options = new Options();
        // options.addOption("a", "arg1", true, "First integer argument");
        // options.addOption("b", "arg2", true, "Second integer argument");

        // CommandLineParser parser = new DefaultParser();

        // try {
        // CommandLine cmd = parser.parse(options, args);

        // int arg1 = Integer.parseInt(cmd.getOptionValue("a"));
        // int arg2 = Integer.parseInt(cmd.getOptionValue("b"));

        // int sum = arg1 + arg2;

        // System.out.println("Sum of " + arg1 + " and " + arg2 + " is " + sum);
        // } catch (ParseException e) {
        // System.out.println("Error: " + e.getMessage());
        // HelpFormatter formatter = new HelpFormatter();
        // formatter.printHelp("MyCLITool", options);
        // }

        Options options = new Options();
        options.addOption("a", "arg1", true, "First argument");
        options.addOption("b", "arg2", true, "Second argument");
        options.addOption("c", "arg2", true, "Second argument");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            String arg1 = cmd.getOptionValue("a");
            String arg2 = cmd.getOptionValue("b");
            String arg3 = cmd.getOptionValue("c");

            if (arg1.equals("lzu")) {
                if (arg2.equals("start")) {
                    RuntimeEnvironment.startRuntime();
                }else if (arg2.equals("stop")) {
                    RuntimeEnvironment.stopRuntime();
                }
            } else if (arg1.equals("component")) {
                if (arg2.equals("load")){
                    RuntimeEnvironment.getInstance().deployComponent(arg3);
                } else if(arg2.equals("start")) {
                    RuntimeEnvironment.getInstance().startComponent(arg3);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("MyCLITool", options);
        }
    }
}
