package fishy.spinplugin;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class SpinEffect {
    private final LivingEntity target;
    private Integer duration;
    private final float spinDegrees;


    public SpinEffect(LivingEntity target, Integer duration, float spinDegrees) {
        this.target = target;
        this.duration = duration;
        this.spinDegrees = spinDegrees;
        addToList();
    }

    public boolean run() {
        if (target == null) return true;
        Location targetLocation = target.getLocation();
        float targetYaw = targetLocation.getYaw();

        float newYaw = (targetYaw + spinDegrees) % 360;
        targetLocation.setYaw(newYaw);
        target.teleport(targetLocation);

        World w = target.getWorld();
        w.spawnParticle(Particle.SPELL, target.getEyeLocation(), 1);

        return false;
    }

    static private final List<SpinEffect> spinEffectEntityList = new ArrayList<SpinEffect>();

    private boolean addToList() {
        if (target == null || duration < 1 || spinDegrees == 0) return true;
        spinEffectEntityList.add(this);
        return false;
    }

    static public boolean runEffect() {
        if (spinEffectEntityList.isEmpty()) return true;
        for (SpinEffect effect : spinEffectEntityList) effect.run();
        return false;
    }

    static public boolean decrementDurations() {
        if (spinEffectEntityList.isEmpty()) return true;
        for (SpinEffect effect : spinEffectEntityList) effect.duration -= 1;
        spinEffectEntityList.removeIf(next -> next.duration < 1);
        return false;
    }

    static public boolean listIsEmpty() {
        return spinEffectEntityList.isEmpty();
    }
}