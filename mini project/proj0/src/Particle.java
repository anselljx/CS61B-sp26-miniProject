import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Map;

public class Particle {
    public ParticleFlavor flavor;
    public int lifespan;

    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                   ParticleFlavor.PLANT, PLANT_LIFESPAN,
                   ParticleFlavor.FIRE, FIRE_LIFESPAN);

    public Particle(ParticleFlavor flavor) {
        this.flavor = flavor;
        if(this.flavor == ParticleFlavor.PLANT || this.flavor == ParticleFlavor.FLOWER || this.flavor == ParticleFlavor.FIRE){
            this.lifespan = LIFESPANS.get(this.flavor);
        }
        else{
            this.lifespan = -1;
        }
    }

    public Color color() {
        if (flavor == ParticleFlavor.EMPTY) {
            return Color.BLACK;
        }
        if (flavor == ParticleFlavor.SAND){
            return Color.YELLOW;
        }
        if (flavor == ParticleFlavor.BARRIER){
            return Color.GRAY;
        }
        if (flavor == ParticleFlavor.WATER){
            return Color.BLUE;
        }
        if (flavor == ParticleFlavor.FOUNTAIN){
            return Color.CYAN;
        }
        if (flavor == ParticleFlavor.FLOWER) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
            int r = 120 + (int) Math.round((255 - 120) * ratio);
            int g = 70 + (int) Math.round((141 - 70) * ratio);
            int b = 80 + (int) Math.round((161 - 80) * ratio);
            return new Color(r, g, b);
        }
        if (flavor == ParticleFlavor.PLANT) {
            double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
            int g = 120 + (int) Math.round((255 - 120) * ratio);
            return new Color(0, g, 0);
        }
        if (flavor == ParticleFlavor.FIRE) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
            int r = (int) Math.round(255 * ratio);
            return new Color(r, 0, 0);
        }
        return Color.GRAY;
    }

    //这里我遇到第一个问题：因为不知道moveInto到底想干嘛
    //是把当前，也就是current particle（this）转移到other，this清空
    public void moveInto(Particle other) {

        Particle self = this;

        other.flavor = this.flavor;
        other.lifespan = this.lifespan;

        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;

    }

    public void decrementLifespan(){
        if(this.lifespan > 0){
            this.lifespan -= 1;
        }
        if(this.lifespan == 0){
            this.flavor = ParticleFlavor.EMPTY;
            this.lifespan = -1;
        }
    }

    public void fall(Map<Direction, Particle> neighbors) {
        Particle p = neighbors.get(Direction.DOWN);
        //第二个问题出现，这里fall逻辑漏洞
        if(p!=null && p.flavor == ParticleFlavor.EMPTY){
            this.moveInto(p);
        }
    }

    public void flow(Map<Direction, Particle> neighbors) {
        double r = StdRandom.uniformInt(3);

        if(r==1){
            return;
        }
        else{
            if(r==2){
                Particle p = neighbors.get(Direction.LEFT);
                if(p.flavor == ParticleFlavor.EMPTY){
                    moveInto(p);
                }
            }
            else{
                Particle q = neighbors.get(Direction.RIGHT);
                if(q.flavor == ParticleFlavor.EMPTY){
                    moveInto(q);
                }
            }
        }
        return ;

    }

    public void grow(Map<Direction, Particle> neighbors) {
        double r = StdRandom.uniformInt(10);
        Particle pUP = neighbors.get(Direction.UP);
        Particle pLEFT = neighbors.get(Direction.LEFT);
        Particle pRIGHT = neighbors.get(Direction.RIGHT);

        if(r == 1 && pUP.flavor == ParticleFlavor.EMPTY){
            pUP.flavor = this.flavor;
            pUP.lifespan = this.lifespan;
        }
        if(r == 2 && pLEFT.flavor == ParticleFlavor.EMPTY){
            pLEFT.flavor = this.flavor;
            pLEFT.lifespan = this.lifespan;
        }
        if(r == 3 && pRIGHT.flavor == ParticleFlavor.EMPTY){
            pRIGHT.flavor = this.flavor;
            pRIGHT.lifespan = this.lifespan;
        }
        else{
            return;
        }


    }

    public void burn(Map<Direction, Particle> neighbors) {


        for(Particle p : neighbors.values()){
            double r = StdRandom.uniformInt(5);
            if(p.flavor == ParticleFlavor.PLANT || p.flavor == ParticleFlavor.FLOWER){
                if(r<2){
                    p.flavor = ParticleFlavor.FIRE;
                    p.lifespan = FIRE_LIFESPAN;
                }
            }
        }



    }

    public void action(Map<Direction, Particle> neighbors) {
        Particle p = neighbors.get(Direction.DOWN);

        if(p.flavor == ParticleFlavor.EMPTY && this.flavor != ParticleFlavor.BARRIER){
            //找到问题了，这里fall根本没调用。。。无语了
            fall(neighbors);
            return;
        }

        if(this.flavor == ParticleFlavor.BARRIER){
            return;
        }
        if(this.flavor == ParticleFlavor.WATER){
            flow(neighbors);
            return;
        }

        if(this.flavor == ParticleFlavor.PLANT || this.flavor == ParticleFlavor.FLOWER){
            grow(neighbors);
            return;
        }

        if(this.flavor == ParticleFlavor.FIRE){
            burn(neighbors);
        }

        return;
    }
}