/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruPilha;

/**
 *
 * @author bruno
 */
public class peca <G>{

    private G info;
    private peca prox;

    public peca(peca prox, G info) {
        this.prox = prox;
        this.info = info;
    }

    peca getProx() {
        return prox;
    }

    void setProx(peca prox) {
        this.prox = prox;
    }

    G getInfo() {
        return info;
    }

    void setInfo(G info) {
        this.info = info;
    }
}
