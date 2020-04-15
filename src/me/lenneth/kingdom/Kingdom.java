package me.lenneth.kingdom;

import me.lenneth.kingdom.events.ClaimEvent;
import me.lenneth.kingdom.events.MiningEvent;
import me.lenneth.kingdom.events.SkillEvent;
import me.lenneth.kingdom.utils.BlockMetaData;
import me.lenneth.kingdom.utils.Claims;

import java.util.ArrayList;
import java.util.HashMap;

public class Kingdom {
    public HashMap<BlockMetaData, Long> blockMetaData = new HashMap<>();
    public ArrayList<BlockMetaData> claimPoints = new ArrayList<BlockMetaData>();
    public ArrayList<Claims> claims = new ArrayList<Claims>();

    public SkillEvent skillEvent;
    public MiningEvent miningEvent;
    public ClaimEvent claimEvent;
    public Claims claim;
}
