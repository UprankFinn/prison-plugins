package dev.uprank.prison.crate;

import java.util.HashMap;
import java.util.Map;

public class CrateManager {

    public Map<String, Crate> crates = new HashMap<>();

    public CrateManager() {

        this.addCrate(new Crate("REWARD_KEY", "REWARD_KEY", CrateType.MINE_KEY));
        this.addCrate(new Crate("VOTE_KEY", "VOTE_KEY", CrateType.VOTE_KEY));
        this.addCrate(new Crate("CURRENT_KEY", "CURRENT_KEY", CrateType.CURRENT_KEY));
        this.addCrate(new Crate("OP_KEY", "OP_KEY", CrateType.OP_KEY));

    }

    public void addCrate(Crate crate) {
        this.crates.put(crate.name(), crate);
    }

    public Crate getCrate(String crate) {
        if (this.crates.containsKey(crate)) return this.crates.get(crate);
        return null;
    }

    public Map<String, Crate> getCrates() {
        return this.crates;
    }
}
