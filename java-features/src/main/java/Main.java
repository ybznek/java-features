import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {


    public static void main(String[] args) {
        /* ad Java - streams a lambdy - obvykle pouziti - priklady, Optional, anotace notnull vs nullable - obvykle pouziti a priklady, predavani funkce misto objektu - ::getConnection() */
        lmbdaMethodRef();
        streams();
        nullable();

        new InitBlock();
        Examples.labels();
        Examples.link();
        Examples.computeIfAbsent();
        Examples.exampleOfVar();
        Examples.switchExample();
        Examples.streams();
        Examples.instanceOf();
        Examples.textBlocks();
        Examples.functions();
        Examples.record();
        Examples.sealed();
        Examples.inlineClass();
    }

    private static void nullable() {
        TestClassNullable test = new TestClassNullable("test");

        @NotNull
        String unsafe = test.getUnsafe();

        String safe = test.getSafe();
    }

    private static void streams() {
        List<String> input = List.of("alza", "czc", "tsbohemia");
        Optional<String> shortestName = input.stream().min(Comparator.comparingInt(String::length));

        Optional<String> s = shortestName.map(x -> x + x);
        shortestName.ifPresent(System.out::println);
        List<String> strings = shortestName.stream().toList();

        List<Integer> lengths1 = input.stream().map(String::length).collect(Collectors.toList());
        List<Integer> lengths2 = input.stream().map(String::length).toList(); // since 16
        List<String> precalculatedSpaces = IntStream.range(0, Integer.MAX_VALUE).mapToObj(" "::repeat).limit(10).toList();
    }


    private static void lmbdaMethodRef() {
        FuncInterface t1 = new FuncInterface() {
            @Override
            public void accept(String value) {
                System.out.println(value);
            }
        };

        FuncInterface t2 = value -> System.out.println(value);
        FuncInterface t3 = System.out::println;
    }


}
