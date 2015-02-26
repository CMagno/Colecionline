package colecionlineserver;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesus
 */
public class Item{
    
    private int id;
    private int isbn;
    private int qtde;
    private String nome;
    private String autor;
    private String genero;
    private int paginas;
    private String descricao;
    private BufferedImage image;
    
    public Item (int isbn, String nome, String autor, String genero, String descricao, int paginas, int qtde ){
        this.id = -1;
        this.isbn = isbn;
        this.nome = nome;
        this.autor = autor;
        this.genero = genero;
        this.paginas = paginas;
        this.descricao = descricao;
        this.qtde = qtde;
    }
    
    public Item (int id, int isbn, String nome, String autor, String genero, String descricao, int paginas, int qtde){
        this.id = id;
        this.isbn = isbn;
        this.nome = nome;
        this.autor = autor;
        this.genero = genero;
        this.paginas = paginas;
        this.descricao = descricao;
        this.qtde = qtde;
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

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
    
    public static void writeItem(DataOutputStream o, Item i){
        try {
            o.writeUTF(i.getNome());
            o.writeUTF(i.getAutor());
            o.writeUTF(i.getGenero());
            o.writeUTF(i.getDescricao());
            o.writeInt(i.getId());
            o.writeInt(i.getIsbn());
            o.writeInt(i.getPaginas());
            o.writeInt(i.getQtde());
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Item readItem(DataInputStream i){
        String nome, autor, genero, descricao;
        int id, isbn, paginas, qtde;
        try {
            nome = i.readUTF();
            autor = i.readUTF();
            genero = i.readUTF();
            descricao = i.readUTF();
            id = i.readInt();
            isbn = i.readInt();
            paginas = i.readInt();
            qtde = i.readInt();
            return new Item(id, isbn, nome, autor, genero, descricao, paginas, qtde);
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
