package br.univille.sistema.exceptions;

public class ValorInvalidoException extends Exception{
    // Mensagem de erro + Campo + Exception original
    private String fieldName;

    // Sobrecarga de construtor;
    public ValorInvalidoException(String message, Exception origin) {
        // super(); se refere à SuperClasse Exception
        super(message, origin); // Chama o construtor da classe "pai"(neste caso, o "pai" é a classe Exception)
        this.fieldName = null;
    }

    public ValorInvalidoException(String message, Exception origin, String fieldName) {
        this(message, origin); // Chama o construtor da propria classe
        this.fieldName = fieldName;
    }

    public String getNomeCampo() {
        return fieldName;
    }

    public void setNomeCampo(String fieldName) {
        this.fieldName = fieldName;
    }    
}
