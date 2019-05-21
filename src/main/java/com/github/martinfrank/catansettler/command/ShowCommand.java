package com.github.martinfrank.catansettler.command;

import com.github.martinfrank.catansettler.CatanSettler;
import com.github.martinfrank.cli.Command;
import com.github.martinfrank.cli.Response;

import java.util.List;

public class ShowCommand extends Command<CatanSettler> {

    public ShowCommand(CatanSettler catanSettler) {
        super(catanSettler, "show");
    }

    @Override
    public Response execute(List<String> parameter) {
        return getApplication().show();
    }
}
