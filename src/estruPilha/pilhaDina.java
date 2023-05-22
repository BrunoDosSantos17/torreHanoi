/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruPilha;

import interfac.Ipilha;

/**
 *
 * @author bruno
 */
public class pilhaDina <G> implements Ipilha <G> {

    private peca fim, ini;
    protected int n;

    public pilhaDina() {
        super();
        this.fim = null;
        this.ini = null;
    }

    public void push(G v) throws Exception {
        peca objetoAdd = new peca(null, v);
        if (this.vazia()) {
            this.ini = objetoAdd;
            this.fim = objetoAdd;

        } else {
            this.fim.setProx(objetoAdd);
            this.fim = objetoAdd;
        }
        this.n++;
    }

    @Override
    public G pop() throws Exception {
        G valorObj = (G)fim.getInfo();
        peca eleGener = this.ini;

        if (this.vazia()) {
            System.out.println("Lista vazia");
        } else {
            if (this.n != 1) {
                while (eleGener.getProx().getProx() != null) {
                    eleGener = eleGener.getProx();
                }
                eleGener.setProx(null);
                this.fim.setProx(eleGener);
                this.fim = eleGener;
                this.n--;
            } else {
                this.libera();
            }

        }
        return valorObj;
    }

    @Override
    public G top() throws Exception {
        return (G)this.fim.getInfo();
    }

    @Override
    public void libera() {
        this.fim = null;
        this.ini = null;
        this.n = 0;
    }

    public String toString() {
        String vetor = "";
        peca eleGener = this.ini;
        for (int i = 0; i < 5; i++) {
            if (eleGener != null) {
                vetor = vetor + " " + eleGener.getInfo();
                eleGener = eleGener.getProx();
            } else {
                vetor = vetor + " | ";
            }

        }
        return vetor;
    }

    @Override
    public boolean vazia() throws Exception {
        return this.n == 0;
    }

    @Override
    public boolean cheia() {
        return this.n == 5;
    }
}
