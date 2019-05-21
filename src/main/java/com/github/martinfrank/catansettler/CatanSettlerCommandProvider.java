package com.github.martinfrank.catansettler;

import com.github.martinfrank.catansettler.command.ShowCommand;
import com.github.martinfrank.cli.CommandList;
import com.github.martinfrank.cli.CommandProvider;
import com.github.martinfrank.cli.DefaultCommandList;

class CatanSettlerCommandProvider implements CommandProvider {

    private final DefaultCommandList mapping;

    CatanSettlerCommandProvider(CatanSettler catanSettler) {
        mapping = new DefaultCommandList();
        mapping.add(new ShowCommand(catanSettler));
    }

    @Override
    public CommandList getCommands() {
        return mapping;
    }
}
