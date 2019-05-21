package com.github.martinfrank.catansettler;

import com.github.martinfrank.cli.CommandLineInterpreter;

import java.io.PrintStream;

public class App {

    public static void main(String[] args) {
        @SuppressWarnings("squid:S106") //we're NOT logging exceptions into here, it's an console app
                PrintStream out = System.out;
        CatanSettler catansettler = new CatanSettler(out);
        CommandLineInterpreter commandLineInterpreter = new CommandLineInterpreter(catansettler, System.in, out);
        commandLineInterpreter.start();
    }
}
