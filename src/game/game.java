package game;

import estruPilha.pilhaCon;
import estruPilha.pilhaDina;
import interfac.Ipilha;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class game {

    private Ipilha pinoEsquerdo;
    private Ipilha pinoMeio;
    private Ipilha pinoDireito;
    private int jogadas;
    private int saida;
    private int destino;
    private final static int MIN = 31;
    private  Scanner br;
    private final boolean tipPilha;
    private boolean permVoltJogada;

    public game(boolean rez) throws Exception {
        this.tipPilha = rez;
        this.restart();
        br = new Scanner(System.in);
    }

    public void start() throws Exception {
        byte op;
        this.mostraJogo();
        try {
            do {
                System.out.println("Escolha sua jogada:\n"
                        + "1- Movimentar peça;\n"
                        + "2- Voltar uma jogada;\n"
                        + "3- Reiniciar\n"
                        + "4- Sair;");

                op = br.nextByte();
            } while (op < 1 || op > 4);

            switch (op) {
                case 1:
                    int valorSaida = this.moviPecaSaida();
                    this.moviPecaEntra(valorSaida);
                    break;
                case 2:
                    this.voltaJogada();
                    break;
                case 3:
                    System.out.println("\n Você retornara para o começo da sua jornada!!!");
                    this.restart();
                    this.start();
                    break;
                default:
                    this.fimJogo();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nEita!!! tem que colocar as opção acima e não escrever");
            br = new Scanner(System.in);
            this.start();
        }
    }

    private void voltaJogada() throws Exception {
        if (this.permVoltJogada) {
            System.out.println("\nVoltando uma jogada!!! ");
            this.jogadas--;
            int valor = this.valorPeca(this.destino);
            this.permVoltJogada = false;

            this.retiraPeca(this.destino);
            this.colocaPeca(this.saida, valor);
        } else {
            System.out.println("\n Você ja voltou uma jogada");
        }
        this.start();
    }

    private void fimJogo() {
        br.close();
        System.exit(0);
    }

    private void moviPecaEntra(int valor) throws Exception {
        String pinoDispo = this.retornaPinoDispo();
        do {
            System.out.print("Você deseja colocar em qual pino: \n"
                    + pinoDispo);
            this.destino = br.nextByte();
        } while (this.destino < 1
                || this.destino > 3
                || this.destino == this.saida);
        if (valor > this.valorPeca(this.destino)) {
            System.out.println("\n!!!!!!!!!!!!!!!!!ALERTA!!!!!!!!!!!!!!!!!");
            System.out.println("Peça maior em cima da menor, movimeto impossivel\n");
        } else {
            this.retiraPeca(this.saida);
            this.colocaPeca(this.destino, valor);
            this.jogadas++;
            this.permVoltJogada = true;
            System.out.println("\n*****************SUCESSO*****************");
            System.out.println("Peça movida com sucesso, jogadas realizadas: "
                    + this.jogadas + "\n");

        }
        if (this.jogadas >= MIN) {
            this.vencedor();
        }
        this.start();

    }

    private String retornaPinoDispo() {
        String pinoDispo = "";
        if (this.saida != 1) {
            pinoDispo += "1- Pino esquerdo \n";
        }
        if (this.saida != 2) {
            pinoDispo += "2- Pino meio \n";
        }
        if (this.saida != 3) {
            pinoDispo += "3- Pino direito \n";
        }
        return pinoDispo;
    }

    private int moviPecaSaida() throws Exception {
        int valorPeca;
        boolean pecaRemovivel;

        do {
            System.out.println("Você deseja tirar de qual pino: \n"
                    + "1- Pino esquerdo;\n"
                    + "2- Pino meio\n"
                    + "3- Pino direito");
            this.saida = br.nextByte();
            switch (this.saida) {
                case 1:
                    pecaRemovivel = this.pecaMov(this.pinoEsquerdo);
                    break;
                case 2:
                    pecaRemovivel = this.pecaMov(this.pinoMeio);
                    break;
                case 3:
                    pecaRemovivel = this.pecaMov(pinoDireito);
                    break;
                default:
                    pecaRemovivel = false;

            }
        } while (this.saida < 1 || this.saida > 3 || !pecaRemovivel);

        valorPeca = this.valorPeca(this.saida);

        this.mostraJogo();
        return valorPeca;
    }

    private boolean pecaMov(Ipilha mov) throws Exception {
        if (mov.vazia()) {
            System.out.println("Não foi possivel retirar");
            return false;
        }
        return true;
    }

    private void restart() throws Exception {
        if (this.tipPilha) {
            System.out.println("Pilha Continua");
            this.pinoEsquerdo = new pilhaCon();
            this.pinoMeio = new pilhaCon();
            this.pinoDireito = new pilhaCon();
        } else {
            System.out.println("Pilha Dinamica");
            this.pinoEsquerdo = new pilhaDina();
            this.pinoMeio = new pilhaDina();
            this.pinoDireito = new pilhaDina();
        }
        this.permVoltJogada = false;
        this.jogadas = 0;
        this.saida = 0;
        this.destino = 0;
        for (int i = 5; i > 0; i--) {
            this.pinoEsquerdo.push(i);
        }

    }

    private void mostraJogo() {
        String pinoEsquerdo = this.pinoEsquerdo.toString();
        String pinoMeio = this.pinoMeio.toString();
        String pinoDireito = this.pinoDireito.toString();

        System.out.println("\nPino esquerdo " + pinoEsquerdo + " \n"
                + "Pino meio     " + pinoMeio + " \n"
                + "Pino direito  " + pinoDireito + " \n");
    }

    private void retiraPeca(int pino) throws Exception {
        switch (pino) {
            case 1:
                this.pinoEsquerdo.pop();
                break;
            case 2:
                this.pinoMeio.pop();
                break;
            default:
                this.pinoDireito.pop();
                break;
        }
    }

    private int valorPeca(int peca) {
        int valorPe;
        try {
            switch (peca) {
                case 1:
                    valorPe = (int)this.pinoEsquerdo.top();
                    break;
                case 2:
                    valorPe = (int)this.pinoMeio.top();
                    break;
                default:
                    valorPe = (int)this.pinoDireito.top();
                    break;
            }
        } catch (Exception e) {
            valorPe = 6;
        }

        return valorPe;
    }

    private void colocaPeca(int pino, int valor) throws Exception {
        switch (pino) {
            case 1:
                this.pinoEsquerdo.push(valor);
                break;
            case 2:
                this.pinoMeio.push(valor);
                break;
            default:
                this.pinoDireito.push(valor);
                break;
        }
    }

    private void vencedor() throws Exception {
        String novoJogo;

        if (this.pinoDireito.cheia()) {
            System.out.println("\n*****************VENCEDOR*****************");
            br.reset();
            if (this.jogadas > MIN) {
                System.out.println("É... mas poderia ter feito melhor tipo em 31 jogadas");
            } else {
                System.out.println("Perfeito você é um gênio!!!");
            }

            do {
                System.out.println("\nVocê quer tentar de novo???? \n"
                        + "S - Sim\n"
                        + "N - Não;");
                novoJogo = br.next();
            } while (!novoJogo.equals("S") && !novoJogo.equals("N"));

            if (novoJogo.equals("S")) {
                this.start();
            } else {
                this.fimJogo();
            }

        }
    }
}
