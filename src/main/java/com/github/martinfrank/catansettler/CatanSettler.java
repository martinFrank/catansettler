package com.github.martinfrank.catansettler;

import com.github.martinfrank.cli.CommandList;
import com.github.martinfrank.cli.CommandProvider;
import com.github.martinfrank.cli.Response;

import java.io.PrintStream;

public class CatanSettler implements CommandProvider {

    private final CatanSettlerCommandProvider commandProvider;

    CatanSettler(PrintStream out) {
        commandProvider = new CatanSettlerCommandProvider(this);
    }

    @Override
    public CommandList getCommands() {
        return commandProvider.getCommands();
    }

    public Response show() {

        return Response.success();
    }

}
