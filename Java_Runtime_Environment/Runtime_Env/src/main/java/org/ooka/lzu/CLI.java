package org.ooka.lzu;

import java.util.Scanner;
import org.apache.commons.cli.*;

public class CLI {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("a", "arg1", true, "First argument");
        options.addOption("b", "arg2", true, "Second argument");
        options.addOption("c", "arg3", true, "Third argument");

        CommandLineParser parser = new DefaultParser();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.print("Enter arg1 (lzu / component) ('exit' to quit): ");
            String arg1 = scanner.nextLine();
            if (arg1.equalsIgnoreCase("exit")) {
                exit = true;
                continue;
            }

            System.out.print("Enter arg2 (start/stop/load): ");
            String arg2 = scanner.nextLine();

            System.out.print("Enter arg3 (None/<.jar-path>/Component-ID): ");
            String arg3 = scanner.nextLine();

            try {
                CommandLine cmd = parser.parse(options, new String[]{"-a", arg1, "-b", arg2, "-c", arg3});
                arg1 = cmd.getOptionValue("a");
                arg2 = cmd.getOptionValue("b");
                arg3 = cmd.getOptionValue("c");
                System.out.println("Current runtime: " + RuntimeEnvironment.getInstance());
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
}
