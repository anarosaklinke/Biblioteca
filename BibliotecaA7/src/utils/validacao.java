package utils;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import model.Livro;
import service.ClassificacaoService;
import service.ServiceFactory;

public class validacao {

    public static String formatString(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return str;
    }

    public static String formatString_E(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        str = str.replaceAll("[^a-zZ-Z1-9 ]", "");
        return str;
    }

    public static boolean isDateValid(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate d = LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }

    public static Livro valida(long idIsbn, String autores, String isbn,
            String semelhante, String data, String titulo) {

        Livro livro = null;

        if (autores.isEmpty()
                || isbn.isEmpty()
                || semelhante.isEmpty()
                || data.isEmpty()
                || titulo.isEmpty()) {
            return livro;
        } else {
            livro = new Livro(idIsbn);
            livro.setAutores(autores);
            livro.setSemelhantes(semelhante);
            livro.setDataPublicacao(data);
            livro.setTitulo(titulo);
            livro.setIsbn(isbn);
        }

        livro.setIdClassificacao(1);

        return livro;
    }

    public static Livro validaLivro(long idIsbn, String autoresTemp, String isbnTemp,
            String semelhanteTemp, String dataTemp, String tituloTemp, String classTemp) {

        Livro livro = null;

        if (autoresTemp.isEmpty()
                || isbnTemp.isEmpty()
                || dataTemp.isEmpty()
                || tituloTemp.isEmpty()) {
            return livro;
        } else {
            autoresTemp = validacao.formatString(autoresTemp);
            isbnTemp = validacao.formatString(isbnTemp);
            semelhanteTemp = validacao.formatString(semelhanteTemp);
            dataTemp = validacao.formatString(dataTemp);
            tituloTemp = validacao.formatString(tituloTemp);

            ClassificacaoService entity = ServiceFactory.getClassificacaoService();

            long idClass = entity.verifica(classTemp);

            livro = new Livro(idIsbn);
            livro.setAutores(autoresTemp);
            livro.setSemelhantes(semelhanteTemp);
            livro.setDataPublicacao(dataTemp);
            livro.setTitulo(tituloTemp);
            livro.setIsbn(isbnTemp);
            livro.setIdClassificacao(idClass);

        }

        return livro;
    }

}
