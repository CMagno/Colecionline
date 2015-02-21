package coleciolineserver;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author jesus
 */
public class Item implements Serializable {
    
    private int id;
    private int isbn;
    private String nome;
    private String autor;
    private String genero;
    private int paginas;
    private String descricao;
    private BufferedImage image;
    
    public Item (int isbn, String nome, String autor, String genero, String descricao, int paginas ){
        this.id = -1;
        this.isbn = isbn;
        this.nome = nome;
        this.autor = autor;
        this.genero = genero;
        this.paginas = paginas;
        this.descricao = descricao;
    }
    
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
