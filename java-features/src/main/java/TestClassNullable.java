import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

public class TestClassNullable {
    @Null
    private final String str;

    TestClassNullable(@Nullable String str) {
        this.str = str;
    }

    @NotNull
    public String getSafe() {
        return Objects.requireNonNull(str);
    }

    @Nullable
    public String getUnsafe() {
        return str;
    }

    @NotNull
    public String getWrong() {
        return str;
    }


}
