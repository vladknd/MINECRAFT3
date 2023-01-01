package knd.minecraft3;

import org.bukkit.plugin.java.JavaPlugin;

public final class Minecraft3 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String playerId = player.getUniqueId().toString();

        Web3j web3j = Web3j.build(new HttpService()); // connect to Ethereum blockchain
        EthGetTransactionCount txCount = web3j.ethGetTransactionCount(playerId, DefaultBlockParameterName.LATEST).send();
        String playerAddress = txCount.getAccountAddress();

        getLogger().info(player.getName() + " broke a block with public address: " + playerAddress);
    }
}
