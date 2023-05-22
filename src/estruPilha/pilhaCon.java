package estruPilha;

import interfac.Ipilha;

/**
 *
 * @author bruno
 */
public class pilhaCon<G> implements Ipilha<G> {

    private final G vet[];
    protected int n;

    public pilhaCon() {
        super();
        this.vet = (G[]) new Object[5];

    }

    @Override
    public void push(G v) throws Exception {
        this.vet[n] = v;
        this.n++;
    }

    @Override
    public G pop() throws Exception {
        Integer objAnte = 0;
        if (this.vazia()) {
            throw new UnsupportedOperationException("Pino vazio");
        } else {
            objAnte = (Integer) this.top();
            vet[n - 1] = null ;
            this.n--;
        }

        return (G)objAnte;
    }

    @Override
    public G top() throws Exception {
        G objAnte;
        if (this.vazia()) {
            throw new UnsupportedOperationException("Pino vazio");
        } else {
            objAnte = this.vet[n - 1];
        }
        return objAnte;
    }

    public String toString() {
        String vetor = "";
        for (int i = 0; i < 5; i++) {
            if (this.vet[i] == null) {
                vetor = vetor + "| ";
            } else {
                vetor = vetor + this.vet[i];
            }
        }
        return vetor;
    }

    @Override
    public boolean vazia() throws Exception {
        return this.n == 0;
    }

    @Override
    public void libera() {
        this.n = 0;
    }

    @Override
    public boolean cheia() {
        return this.n == 5;
    }

}
