package sourcecode.model;

import java.util.concurrent.ThreadLocalRandom;

public class Dado {
    private int minlados;
    private int maxlados;

    public Dado(int minlados, int maxlados){
        this.minlados = minlados;
        this.maxlados = maxlados;
    }

    public int rolarDado(){
        int randomNum = ThreadLocalRandom.current().nextInt(minlados, maxlados + 1);
        return randomNum;
    }

    public int getMinlados() {
        return minlados;
    }

    public void setMinlados(int minlados) {
        this.minlados = minlados;
    }

    public int getMaxlados() {
        return maxlados;
    }

    public void setMaxlados(int maxlados) {
        this.maxlados = maxlados;
    }
}
