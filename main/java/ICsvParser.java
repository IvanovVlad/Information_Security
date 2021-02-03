import java.io.IOException;

public interface ICsvParser<T> {
    T parse (String path) throws IOException;
}
