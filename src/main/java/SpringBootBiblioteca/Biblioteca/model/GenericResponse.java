package SpringBootBiblioteca.Biblioteca.model;


public class GenericResponse<T> {

    private String response;
    private T data;

    public GenericResponse(String response, T data) {
        this.response = response;
        this.data = data;
    }

    public String getResponse() {
        return this.response;
    }

    public T getData() {
        return this.data;
    }

}

