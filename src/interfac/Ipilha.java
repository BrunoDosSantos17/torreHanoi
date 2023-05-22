package interfac;

public interface Ipilha <G>{

    public void push(G v) throws Exception;

    public G pop() throws Exception;

    public G top() throws Exception;

    public boolean vazia() throws Exception;
    
    public void libera();
    
    public boolean cheia();
}
